package com.example.demo.request;

import org.joda.time.DateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NftImageRequest {

	
	private String id;
	private String imgName;
	private String creatorName;
	private String walletId;
	private String  privateKey;
	private double  price;
	private double  royalty;
	private String ipfsUrl;
	private String previousOwner;
	private String currentOwner;
	private DateTime creationDate;
}
