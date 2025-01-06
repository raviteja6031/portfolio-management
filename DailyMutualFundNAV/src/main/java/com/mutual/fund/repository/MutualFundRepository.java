package com.mutual.fund.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mutual.fund.model.MutualFund;

@Repository
public interface MutualFundRepository extends JpaRepository<MutualFund, Integer> {

	MutualFund findByMutualFundName(String mutualFundName);
}
