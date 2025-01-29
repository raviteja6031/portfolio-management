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

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.portfolio.model.Portfolio;
import com.portfolio.model.ShareDetails;
import com.portfolio.repository.ShareDetailsRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class ShareDetailsServiceTest {

	@InjectMocks
	private ShareDetails shareDetails;
	
	@Mock
	private ShareDetailsRepository shareDetailsRepository;
	
	@InjectMocks
	private ShareDetailsService shareDetailsService;

	@Test
	void testGetById() {
		shareDetails.setCount(6);
		shareDetails.setShareDetailsId(201);
		shareDetails.setShareName("DLF");
		Optional<ShareDetails> optSd = Optional.of(shareDetails);
		when(shareDetailsRepository.findById(201)).thenReturn(optSd);
		Optional<ShareDetails> optSd1 = Optional.of(shareDetailsService.getById(201));
		assertEquals(optSd, optSd1);
		verify(shareDetailsRepository).findById(201);
	}
	
	@Test
	void testGetByIdNegative() {
		ShareDetails sd = null;
		Optional<ShareDetails> optSd = Optional.ofNullable(sd);
		when(shareDetailsRepository.findById(201)).thenReturn(optSd);
		assertThrows(NoSuchElementException.class, () -> {
			shareDetailsService.getById(201);
		});
		verify(shareDetailsRepository).findById(201);
	}
	
	@Test
	void testGetByPortfolioId() {
		Portfolio p = new Portfolio();
		p.setPortfolioId(101);
		shareDetails.setCount(6);
		shareDetails.setShareDetailsId(201);
		shareDetails.setShareName("DLF");
		List<ShareDetails> listSd = new ArrayList<>();
		listSd.add(shareDetails);
		when(shareDetailsRepository.findByPortfolio(p)).thenReturn(listSd);
		List<ShareDetails> listSd1 = shareDetailsService.getByPortfolioId(p);
		assertEquals(listSd, listSd1);
		verify(shareDetailsRepository).findByPortfolio(p);
	}
	
	@Test
	void testGetByPortfolioIdNegative() {
		Portfolio p = new Portfolio();
		p.setPortfolioId(101);
		List<ShareDetails> listSd = Collections.emptyList();
		when(shareDetailsRepository.findByPortfolio(p)).thenReturn(listSd);
		List<ShareDetails> listSd1 = shareDetailsService.getByPortfolioId(p);
		assertEquals(listSd, listSd1);
		verify(shareDetailsRepository).findByPortfolio(p);
	}
	
	@Test
	void testDeleteById() throws Exception {
		int shareDetailsId = 301;
		shareDetailsService.deleteById(shareDetailsId);
		verify(shareDetailsRepository,times(1)).deleteById(shareDetailsId);
	}
	
	@Test
	void testUpdate() throws Exception {
		shareDetails.setCount(6);
		shareDetails.setShareDetailsId(201);
		shareDetails.setShareName("DLF");
		when(shareDetailsRepository.save(shareDetails)).thenReturn(shareDetails);
		ShareDetails sd = shareDetailsService.update(shareDetails);
		assertEquals(shareDetails,sd);
		verify(shareDetailsRepository).save(shareDetails);
	}

}
