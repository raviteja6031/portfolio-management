package com.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portfolio.model.Portfolio;

public interface PortfolioRepository extends JpaRepository<Portfolio, Integer> {

}
