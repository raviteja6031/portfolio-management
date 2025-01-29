package com.portfolio.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.model.Portfolio;
import com.portfolio.repository.PortfolioRepository;

@Service
public class PortfolioService {

	@Autowired
	PortfolioRepository portfolioRepository;
	
	private static Logger logger = LoggerFactory.getLogger(PortfolioService.class);
	
	public Portfolio getById(int portfolioId) {
		logger.info("LOCATION: PortfolioService, FUNCATION: getById, STATUS: SUCCESS");
		return portfolioRepository.findById(portfolioId).get();
	}
}
