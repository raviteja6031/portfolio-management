package com.mutual.fund.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutual.fund.exceptions.MutualFundWrongDetail;
import com.mutual.fund.model.MutualFund;
import com.mutual.fund.repository.MutualFundRepository;

@Service
public class MutualFundService {

	@Autowired
	MutualFundRepository mutualFundRepository;

	private static List<MutualFund> mutualFunds = new ArrayList<>();

	private static Logger logger = LoggerFactory.getLogger(MutualFundService.class);

	@PostConstruct
	public void initDB() {
		mutualFunds.add(new MutualFund(201, "ICICI Prudential Technology Fund - Direct Plan - Growth", 5023.47));
		mutualFunds.add(new MutualFund(202, "ICICI Prudential Technology Fund", 3509.55));
		mutualFunds.add(new MutualFund(203, "PGIM India Midcap Opportunities Fund - Direct Plan - Growth", 2157.03));
		mutualFunds.add(new MutualFund(204, "Quant Small Cap Fund - Direct Plan-Growth", 4066.56));
		mutualFunds.add(new MutualFund(205, "DSP Government Securities Fund - Direct Plan - Growth", 550.77));
		mutualFundRepository.saveAll(mutualFunds);
	}

	public MutualFund getByMutualFundName(String mutualFundName) throws MutualFundWrongDetail {
		MutualFund mutualFund = mutualFundRepository.findByMutualFundName(mutualFundName);
		if (mutualFund == null) {
			logger.info("MF Token validation: SUCCESS. WrongMutualFundDetail in get-by-Name");
			throw new MutualFundWrongDetail();
		} else {
			logger.info(
					"Token validated for Mutual Fund NAV - SUCCESS. Mutual Fund Detail - MFDetail - RestService -SUCCESS");
			return mutualFund;
		}
	}

	public MutualFund getByMutualFundId(int mutualFundId) throws MutualFundWrongDetail {
		Optional<MutualFund> mutualFund = mutualFundRepository.findById(mutualFundId);
		if (mutualFund.isPresent()) {
			logger.info("MF Token validation: SUCCESS. MF found by ID");
			return mutualFund.get();
		} else {
			logger.info("MF Token validation: SUCCESS. WrongMutualFundDetail in get-by-ID");
			throw new MutualFundWrongDetail();
		}
	}
	
}
