package com.portfolio.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.model.Portfolio;
import com.portfolio.model.ShareDetails;
import com.portfolio.repository.ShareDetailsRepository;

@Service
@Transactional
public class ShareDetailsService {

	@Autowired
	ShareDetailsRepository shareDetailsRepository;
	
	@Autowired
	PortfolioService portfolioService;
	
	private static Logger logger = LoggerFactory.getLogger(ShareDetailsService.class);
	
	public ShareDetails getById(int shareDetailsId) {
		logger.info("LOCATION: ShareDetailsService, FUNCATION: getById, STATUS: SUCCESS");
		return shareDetailsRepository.findById(shareDetailsId).get();
	}
	
	public List<ShareDetails> getByPortfolioId(Portfolio portfolio) {
		logger.info("LOCATION: ShareDetailsService, FUNCATION: getByPortfolioId, STATUS: SUCCESS");
		return shareDetailsRepository.findByPortfolio(portfolio);
	}
	
	public void deleteById(int shareDetailsId) {
		logger.info("LOCATION: ShareDetailsService, FUNCATION: deleteById, STATUS: SUCCESS");
		shareDetailsRepository.deleteById(shareDetailsId);
	}
	
	public ShareDetails update(ShareDetails shareDetails) {
		logger.info("LOCATION: ShareDetailsService, FUNCATION: update, STATUS: SUCCESS");
		return shareDetailsRepository.save(shareDetails);
	}
}
