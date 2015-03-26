package com.ethanaa.essential.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ethanaa.essential.domain.OilBlend;

public interface OilBlendRepository extends JpaRepository<OilBlend, Long> {

	public List<OilBlend> findByOrderByNameAsc();
	public Page<OilBlend> findByOrderByNameAsc(Pageable pageable);
}
