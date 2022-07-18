package com.example.demo.controller;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.NftAuctionService;
import com.hedera.hashgraph.sdk.PrecheckStatusException;
import com.hedera.hashgraph.sdk.ReceiptStatusException;

@RestController
@CrossOrigin
@RequestMapping("/api/NftAuction")
public class NftAuctionController {

	@Autowired
	NftAuctionService Service;
	
	
	@PostMapping("/image/nft/creation")
	public ResponseEntity<?> ImageOfNft(@RequestParam(name = "image", value = "image", required = true) MultipartFile multipartImage)
					throws IOException, TimeoutException, PrecheckStatusException, ReceiptStatusException {
		return Service.ImageOfNft( multipartImage);
	}
	
	
	
}
