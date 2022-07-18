package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.NftImageDetails;

public interface NftAuctionServiceRepo extends JpaRepository<NftImageDetails, String>{

}
