package com.portfolio.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.model.MutualFundDetails;
import com.portfolio.model.Portfolio;
import com.portfolio.repository.MutualFundDetailsRepository;

@Service
@Transactional
public class MutualFundDetailsService {

	@Autowired 
	MutualFundDetailsRepository mutualFundDetailsRepository;
	
	@Autowired
	PortfolioService portfolioService;
	
	private static Logger logger = LoggerFactory.getLogger(MutualFundDetailsService.class);
	
	public MutualFundDetails getById(int mfDetailsId) {
		logger.info("LOCATION: MutualFundDetailsService, FUNCATION: getById, STATUS: SUCCESS");
		return mutualFundDetailsRepository.findById(mfDetailsId).get();
	}
	
	public List<MutualFundDetails> getByPid(Portfolio portfolio){
		logger.info("LOCATION: MutualFundDetailsService, FUNCATION: getByPid, STATUS: SUCCESS");
		return mutualFundDetailsRepository.findByPortfolio(portfolio);
	}
	
	public void deleteById(int mutualFundDetailsId) {
		logger.info("LOCATION: MutualFundDetailsService, FUNCATION: deleteById, STATUS: SUCCESS");
		mutualFundDetailsRepository.deleteById(mutualFundDetailsId);
	}
	
	public MutualFundDetails update(MutualFundDetails mutualFundDetails) {
		logger.info("LOCATION: MutualFundDetailsService, FUNCATION: update, STATUS: SUCCESS");
		return mutualFundDetailsRepository.save(mutualFundDetails);
	}
}
