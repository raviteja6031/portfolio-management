package com.portfolio.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "portfolio")
public class Portfolio {

	@Override
	public int hashCode() {
		return Objects.hash(mutualFundDetails, portfolioId, shareDetails);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Portfolio other = (Portfolio) obj;
		return Objects.equals(mutualFundDetails, other.mutualFundDetails) && portfolioId == other.portfolioId
				&& Objects.equals(shareDetails, other.shareDetails);
	}

	@Id
	@Column(name = "portfolio_id", nullable = false)
	private int portfolioId;
	@Column(name = "share_details")
	@OneToMany(mappedBy = "portfolio")
	private List<ShareDetails> shareDetails;
	@OneToMany(mappedBy = "portfolio")
	@Column(name = "mutual_fund_details")
	private List<MutualFundDetails> mutualFundDetails;

	@Override
	public String toString() {
		return "Portfolio [portfolioId=" + portfolioId + ", shareDetails=" + shareDetails + ", mutualFundDetails="
				+ mutualFundDetails + "]";
	}

	public Portfolio() {
		super();
	}

	public Portfolio(int portfolioId, List<ShareDetails> shareDetails, List<MutualFundDetails> mutualFundDetails) {
		super();
		this.portfolioId = portfolioId;
		this.shareDetails = shareDetails;
		this.mutualFundDetails = mutualFundDetails;
	}

	public int getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(int portfolioId) {
		this.portfolioId = portfolioId;
	}

	public List<ShareDetails> getshareDetails() {
		return shareDetails;
	}

	public void setshareDetails(List<ShareDetails> shareDetails) {
		this.shareDetails = shareDetails;
	}

	public List<MutualFundDetails> getMutualFundDetails() {
		return mutualFundDetails;
	}

	public void setMutualFundDetails(List<MutualFundDetails> mutualFundDetails) {
		this.mutualFundDetails = mutualFundDetails;
	}

}
