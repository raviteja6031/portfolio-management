package com.share.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.share.exceptions.WrongShareDetailsException;
import com.share.model.Share;
import com.share.repository.ShareRepository;

@Service
public class ShareService {

	@Autowired
	ShareRepository shareRepository;

	private static Logger logger = LoggerFactory.getLogger(ShareService.class);

	public Share getByShareName(String shareName) throws WrongShareDetailsException {
		Share share = shareRepository.findByShareName(shareName);
		if (share == null) {
			logger.info("Wrong Share Details Exception BY NAME");
			throw new WrongShareDetailsException();
		} else {
			logger.info("Getting share by name + Share is not null");
			return share;
		}
	}

	public Share getByShareId(int shareId) throws WrongShareDetailsException {
		Optional<Share> share = shareRepository.findById(shareId);
		if (!share.isPresent()) {
			logger.info("Wrong Share Details Exception BY ID");
			throw new WrongShareDetailsException();
		} else {
			logger.info("Getting share by id + Share is not null");
			return share.get();
		}
	}
}
