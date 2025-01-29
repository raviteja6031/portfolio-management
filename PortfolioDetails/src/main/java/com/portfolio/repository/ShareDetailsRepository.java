package com.portfolio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portfolio.model.Portfolio;
import com.portfolio.model.ShareDetails;

public interface ShareDetailsRepository extends JpaRepository<ShareDetails, Integer> {

	List<ShareDetails> findByPortfolio(Portfolio portfolioId);
	void removeByPortfolio(Portfolio portfolio);
}
