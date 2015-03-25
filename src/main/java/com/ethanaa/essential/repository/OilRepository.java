package com.ethanaa.essential.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ethanaa.essential.domain.Oil;

public interface OilRepository extends JpaRepository<Oil, Long> {

	public List<Oil> findByOrderByNameAsc();
	public Page<Oil> findByOrderByNameAsc(Pageable pageable);
	
}
