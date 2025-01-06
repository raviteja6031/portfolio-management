package com.mutual.fund.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.SpyBean;

import com.mutual.fund.exceptions.MutualFundWrongDetail;
import com.mutual.fund.model.MutualFund;
import com.mutual.fund.repository.MutualFundRepository;

@ExtendWith(MockitoExtension.class)
class MutualFundServiceTest {
	
	@InjectMocks
	private MutualFund mutualFund;
	
	@Mock
	private MutualFundRepository mutualFundRepository;
	
	@InjectMocks
	private MutualFundService mutualFundService;
	
	@Test
	void testGetByMutualFundName() throws Exception {
		String mutualFundName = "Nippon Value Fund (G)";
		MutualFund mf = new MutualFund(mutualFundName, 250.47);
		when(mutualFundRepository.findByMutualFundName(mutualFundName)).thenReturn(mf);
		MutualFund mf1 = mutualFundService.getByMutualFundName(mutualFundName);
		assertEquals(mf, mf1);
		verify(mutualFundRepository).findByMutualFundName(mutualFundName);
	}
	
	@Test
	void testGetByMutualFundId() throws Exception {
		int mutualFundId = 201;
		MutualFund mf = new MutualFund(mutualFundId,"Nippon Value Fund (G)", 250.47);
		Optional<MutualFund> mfOpt = Optional.of(mf);
		when(mutualFundRepository.findById(mutualFundId)).thenReturn(mfOpt);
		MutualFund mf1 = mutualFundService.getByMutualFundId(mutualFundId);
		Optional<MutualFund> mfOpt1 = Optional.of(mf1);
		assertEquals(mfOpt, mfOpt1);
		verify(mutualFundRepository).findById(mutualFundId);
	}
	
	@Test
	void testGetByMutualFundNameNegative() throws Exception {
		String mutualFundName = "SomeOtherName";
		when(mutualFundRepository.findByMutualFundName(mutualFundName)).thenReturn(null);
		assertThrows(MutualFundWrongDetail.class, () -> {
			mutualFundService.getByMutualFundName(mutualFundName);
		});
		verify(mutualFundRepository).findByMutualFundName(mutualFundName);
	}
	
	@Test
	void testGetByMutualFundIdNegative() throws Exception, NullPointerException {
		int mutualFundId = 201;
		when(mutualFundRepository.findById(mutualFundId)).thenReturn(null);
		assertThrows(NullPointerException.class, () -> {
			mutualFundService.getByMutualFundId(mutualFundId);
		});
		verify(mutualFundRepository).findById(mutualFundId);
	}

}


//@Test
//@DisplayName("Test invalid findIPTreatmentPackageByName() of IPTreatmentPackageService")
//public void testgetAllChainOfProvidersTestException() throws PolicyNotFoundException {
//	
//	when(policyrepo.findAllByPolicyIdOrderByLocation(1)).thenReturn(new ArrayList<ProviderPolicy>());
//	assertThrows(PolicyNotFoundException.class, () -> {
//		policyServiceImpl.findAllByPolicyIdOrderByLocation(1);
//	});
//	
//	verify(policyrepo).findAllByPolicyIdOrderByLocation(1);
//}
