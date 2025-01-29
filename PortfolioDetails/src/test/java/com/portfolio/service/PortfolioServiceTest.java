package com.portfolio.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.portfolio.model.Portfolio;
import com.portfolio.repository.PortfolioRepository;


@ExtendWith(MockitoExtension.class)
class PortfolioServiceTest {
	
	@InjectMocks
	private Portfolio portfolio;
	
	@Mock
	private PortfolioRepository portfolioRepository;
	
	@InjectMocks
	private PortfolioService portfolioService;

	@Test
	void testGetById() throws Exception {
		Portfolio p = new Portfolio();
		p.setPortfolioId(101);
		Optional<Portfolio> optPortfolio = Optional.of(p);
		when(portfolioRepository.findById(101)).thenReturn(optPortfolio);
		Optional<Portfolio> optPortfolio1 = Optional.of(portfolioService.getById(101));
		assertEquals(optPortfolio, optPortfolio1);
		verify(portfolioRepository).findById(101);
	}
	
	@Test
	void testGetByIdNegative() throws Exception {
		Portfolio p = null;
		Optional<Portfolio> optPortfolio = Optional.ofNullable(p);
		when(portfolioRepository.findById(101)).thenReturn(optPortfolio);
		assertThrows(NoSuchElementException.class, () -> {
			portfolioService.getById(101);
		});
		verify(portfolioRepository).findById(101);
	}

}
