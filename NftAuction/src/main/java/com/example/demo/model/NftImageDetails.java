package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "nft_image_details")
@Data
@Builder
public class NftImageDetails {

	@Id
	@GenericGenerator(name = "uuid-gen", strategy = "uuid2")
	@GeneratedValue(generator = "uuid-gen")
	private String id;
	private String imgName;
	private String creatorName;
	private String walletId;
	private String privateKey;
	private double price;
	private double royalty;
	private String ipfsUrl;
	private String previousOwner;
	private String currentOwner;
//	@Column(name = "created_date")
//	private DateTime createdDate;

}
