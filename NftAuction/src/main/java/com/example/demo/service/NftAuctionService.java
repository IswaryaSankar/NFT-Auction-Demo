package com.example.demo.service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hedera.hashgraph.sdk.PrecheckStatusException;
import com.hedera.hashgraph.sdk.ReceiptStatusException;

@Service
public interface NftAuctionService {

	ResponseEntity<?> ImageOfNft(MultipartFile multipartImage) throws IOException, TimeoutException, PrecheckStatusException, ReceiptStatusException;

}
