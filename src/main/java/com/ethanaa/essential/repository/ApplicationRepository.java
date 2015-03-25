package com.ethanaa.essential.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ethanaa.essential.domain.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

}
