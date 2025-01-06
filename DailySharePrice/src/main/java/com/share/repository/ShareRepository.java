package com.share.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.share.model.Share;

public interface ShareRepository extends JpaRepository<Share, Integer> {

	Share findByShareName(String shareName);
}
