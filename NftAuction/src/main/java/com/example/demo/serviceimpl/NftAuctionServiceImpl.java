package com.example.demo.serviceimpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.NftImageDetails;
import com.example.demo.repo.NftAuctionServiceRepo;
import com.example.demo.response.MessageResponse;
import com.example.demo.service.NftAuctionService;
import com.hedera.hashgraph.sdk.AccountId;
import com.hedera.hashgraph.sdk.Client;
import com.hedera.hashgraph.sdk.Hbar;
import com.hedera.hashgraph.sdk.PrecheckStatusException;
import com.hedera.hashgraph.sdk.PrivateKey;
import com.hedera.hashgraph.sdk.ReceiptStatusException;
import com.hedera.hashgraph.sdk.TokenCreateTransaction;
import com.hedera.hashgraph.sdk.TokenId;
import com.hedera.hashgraph.sdk.TokenSupplyType;
import com.hedera.hashgraph.sdk.TokenType;
import com.hedera.hashgraph.sdk.TransactionReceipt;
import com.hedera.hashgraph.sdk.TransactionResponse;

import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;

@Service
public class NftAuctionServiceImpl implements NftAuctionService {

	@Autowired
	Environment env;

	@Autowired
	NftAuctionServiceRepo repo;

	@Override
	public ResponseEntity<?> ImageOfNft(MultipartFile multipartImage) throws IOException, TimeoutException, PrecheckStatusException, ReceiptStatusException {
		IPFS ipfs = new IPFS("/dnsaddr/ipfs.infura.io/tcp/5001/https");
		MerkleNode response;
		String savedPdfFilePath = userDocumentUpload(multipartImage, env.getProperty("file.path"),
				multipartImage.getOriginalFilename());
		try {
			NamedStreamable.InputStreamWrapper is = new NamedStreamable.InputStreamWrapper(
					new FileInputStream(savedPdfFilePath));
			response = ipfs.add(is).get(0);

		} catch (IOException ex) {

			throw new RuntimeException("Error whilst communicating with the IPFS node", ex);
		}
		Client client = Client.forTestnet();
		client.setOperator(AccountId.fromString(Objects.requireNonNull("0.0.26060726")), PrivateKey.fromString(Objects.requireNonNull(
				"302e020100300506032b657004220420cc84d7a2119f7c32b3303460845a57ddf9aded9009f53b5af4e5fd92fd87b8cf")));
		TokenCreateTransaction transaction = new TokenCreateTransaction()
		        .setTokenName("test")
		        .setTokenSymbol("T")
		        .setSupplyType(TokenSupplyType.FINITE)
		        .setTokenType(TokenType.NON_FUNGIBLE_UNIQUE)
		        .setTreasuryAccountId(AccountId.fromString(Objects.requireNonNull("0.0.46839320")))
		       // .setInitialSupply(5000)
		        .setDecimals(0)
		        .setAdminKey(PrivateKey.fromString(Objects.requireNonNull(
		    			"302e020100300506032b657004220420cc84d7a2119f7c32b3303460845a57ddf9aded9009f53b5af4e5fd92fd87b8cf")).getPublicKey())
		        .setMaxTransactionFee(new Hbar(30)); //Change the default max transaction fee

		//Build the unsigned transaction, sign with admin private key of the token, sign with the token treasury private key, submit the transaction to a Hedera network
		TransactionResponse txResponse = transaction.freezeWith(client).sign(PrivateKey.fromString(Objects.requireNonNull(
				"302e020100300506032b657004220420cc84d7a2119f7c32b3303460845a57ddf9aded9009f53b5af4e5fd92fd87b8cf"))).sign(PrivateKey.fromString(Objects.requireNonNull(
						"302e020100300506032b657004220420bc388d1eb0c8413c75d1d27a07c6f78881fa9d1864df526e75de778f7ee772cb"))).execute(client);

		//Request the receipt of the transaction
		TransactionReceipt receipt = txResponse.getReceipt(client);

		//Get the token ID from the receipt
		TokenId tokenId = receipt.tokenId;

		System.out.println("The new token ID is " + tokenId);

		NftImageDetails nftImageDetails = NftImageDetails.builder().walletId("0.0.46839320").privateKey(
				"302e020100300506032b657004220420bc388d1eb0c8413c75d1d27a07c6f78881fa9d1864df526e75de778f7ee772cb")
				.ipfsUrl("https://ipfs.io/ipfs/" + "" + response.hash.toBase58()).imgName(multipartImage.getName())
				.previousOwner(null).build();
		repo.save(nftImageDetails);
		return ResponseEntity.ok(new MessageResponse("success", nftImageDetails, HttpStatus.OK));
	}

	private String userDocumentUpload(MultipartFile file, String filePath, String originalFilename) throws IOException {
		FileInputStream reader = null;
		FileOutputStream writer = null;
		String path = null;
		// String dbPath = null;
		try {

			File files = new File(filePath);

			if (files.exists()) {
				files.delete();
			}
			if (!files.exists()) {
				files.mkdirs();
			}
			String imgfilePath = filePath + File.separator + originalFilename;
			path = imgfilePath;
			// dbPath = imgfilePath;
			System.out.println(" file path : " + path);
			// System.out.println("dbPath : " + dbPath);
			byte[] buffer = new byte[1000];
			File outputFile = new File(path);

			int totalBytes = 0;
			outputFile.createNewFile();
			reader = (FileInputStream) file.getInputStream();
			writer = new FileOutputStream(outputFile);

			int bytesRead = 0;
			while ((bytesRead = reader.read(buffer)) != -1) {
				writer.write(buffer);
				totalBytes += bytesRead;
			}
			reader.close();
			writer.close();

		} catch (IOException e) {
			path = null;
			// dbPath = null;
			reader.close();
			writer.close();
			e.printStackTrace();
		} finally {

		}
		return path;

	}

}
