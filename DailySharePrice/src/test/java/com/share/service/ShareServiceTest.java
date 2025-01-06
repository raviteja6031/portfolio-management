package com.share.service;

import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.share.exceptions.WrongShareDetailsException;
import com.share.model.Share;
import com.share.repository.ShareRepository;
import org.mockito.Mockito;

@ExtendWith(MockitoExtension.class)
class ShareServiceTest {
	
	@InjectMocks
	private Share share;
	
	@Mock
	private ShareRepository shareRepository;
	
	@Autowired
	@InjectMocks
	private ShareService shareService;
	
	@Spy
	private ShareService shareService1;

	@Test
	void testDailySharePriceByName() throws Exception {
		when(shareRepository.findByShareName("DSL")).thenReturn(share);
		Share sh = shareService.getByShareName("DSL");
		assertEquals(share,sh);
		verify(shareRepository).findByShareName("DSL");
	}
	
	@Test
	void testDailySharePriceById() throws Exception {
		Optional<Share> optional = Optional.of(share);
		when(shareRepository.findById(101)).thenReturn(optional);
		Optional<Share> sh = Optional.of(shareService.getByShareId(101));
		assertEquals(optional,sh);
		verify(shareRepository).findById(101);
	}
	
	@Test
	void testDailySharePriceByNameNegative() throws WrongShareDetailsException {
		Share share1=null;
		when(shareRepository.findByShareName("DSLP")).thenReturn(share1);
		assertThrows(WrongShareDetailsException.class, () -> {
			shareService.getByShareName("DSLP");
		});
		verify(shareRepository,times(1)).findByShareName("DSLP");
	}
	
	@Test
	void testDailySharePriceByIdNegative() throws Exception {
		Share share1=null;
		Optional<Share> sh = Optional.ofNullable(share1);
		when(shareRepository.findById(201)).thenReturn(sh);
		assertThrows(WrongShareDetailsException.class, () -> {
			shareService.getByShareId(201);
		});
		verify(shareRepository,times(1)).findById(201);
	}

}
