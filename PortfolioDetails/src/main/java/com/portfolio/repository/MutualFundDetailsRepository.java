package com.portfolio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portfolio.model.MutualFundDetails;
import com.portfolio.model.Portfolio;

public interface MutualFundDetailsRepository extends JpaRepository<MutualFundDetails, Integer> {

	List<MutualFundDetails> findByPortfolio(Portfolio portfolioId);
}
