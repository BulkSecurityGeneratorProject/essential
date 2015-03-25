package com.ethanaa.essential.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ethanaa.essential.domain.UseCase;

public interface UseCaseRepository extends JpaRepository<UseCase, Long> {

}
