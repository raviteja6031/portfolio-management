package com.portfolio.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.portfolio.model.MutualFundDetails;
import com.portfolio.model.Portfolio;
import com.portfolio.repository.MutualFundDetailsRepository;

@ExtendWith(MockitoExtension.class)
class MutualFundDetailsServiceTest {
	
	@InjectMocks
	private MutualFundDetails mutualFundDetails;
	
	@Mock
	private MutualFundDetailsRepository mutualFundDetailsRepository;
	
	@InjectMocks
	private MutualFundDetailsService mutualFundDetailsService;

	@Test
	void testGetById() throws Exception {
		MutualFundDetails mutualFundDetail = new MutualFundDetails();
		mutualFundDetail.setMfDetailsId(301);
		mutualFundDetail.setMutualFundName("DSP Government Securities Fund - Direct Plan - Growth");
		mutualFundDetail.setCount(3);
		Optional<MutualFundDetails> optMf = Optional.of(mutualFundDetail);
		when(mutualFundDetailsRepository.findById(301)).thenReturn(optMf);
		Optional<MutualFundDetails> optMf1 = Optional.of(mutualFundDetailsService.getById(301));
		assertEquals(optMf,optMf1);
		verify(mutualFundDetailsRepository).findById(301);
	}
	
	@Test
	void testGetByIdNegative() throws Exception {
		MutualFundDetails mf = null;
		Optional<MutualFundDetails> optMf = Optional.ofNullable(mf);
		when(mutualFundDetailsRepository.findById(301)).thenReturn(optMf);
		assertThrows(NoSuchElementException.class, () -> {
			mutualFundDetailsService.getById(301);
		});
		verify(mutualFundDetailsRepository).findById(301);
	}
	
	@Test
	void testGetByPid() throws Exception {
		Portfolio p = new Portfolio();
		p.setPortfolioId(101);
		MutualFundDetails mutualFundDetail = new MutualFundDetails();
		mutualFundDetail.setMfDetailsId(301);
		mutualFundDetail.setMutualFundName("DSP Government Securities Fund - Direct Plan - Growth");
		mutualFundDetail.setCount(3);
		List<MutualFundDetails> mfs = new ArrayList<>();
		mfs.add(mutualFundDetail);
		when(mutualFundDetailsRepository.findByPortfolio(p)).thenReturn(mfs);
		List<MutualFundDetails> mfs1 = mutualFundDetailsService.getByPid(p);
		assertEquals(mfs,mfs1);
		verify(mutualFundDetailsRepository).findByPortfolio(p);
	}

	@Test
	void testGetByPidNegative() throws Exception {
		Portfolio p = new Portfolio();
		p.setPortfolioId(101);
		List<MutualFundDetails> mfs = Collections.emptyList();
		when(mutualFundDetailsRepository.findByPortfolio(p)).thenReturn(mfs);
		List<MutualFundDetails> mfs1 = mutualFundDetailsService.getByPid(p);
		assertEquals(mfs, mfs1);
		verify(mutualFundDetailsRepository).findByPortfolio(p);
	}
	
	@Test
	void testDeleteById() throws Exception {
		int MfDetailsId = 301;
		mutualFundDetailsService.deleteById(MfDetailsId);
		verify(mutualFundDetailsRepository,times(1)).deleteById(MfDetailsId);
	}
	
	@Test
	void testUpdate() throws Exception {
		MutualFundDetails mutualFundDetail = new MutualFundDetails();
		mutualFundDetail.setMfDetailsId(301);
		mutualFundDetail.setMutualFundName("DSP Government Securities Fund - Direct Plan - Growth");
		mutualFundDetail.setCount(3);
		when(mutualFundDetailsRepository.save(mutualFundDetail)).thenReturn(mutualFundDetail);
		MutualFundDetails mfDetail = mutualFundDetailsService.update(mutualFundDetail);
		assertEquals(mutualFundDetail,mfDetail);
		verify(mutualFundDetailsRepository).save(mutualFundDetail);
	}
	
}
