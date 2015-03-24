package com.ethanaa.essential.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "T_USE_CASE")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UseCase implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;	
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "category", nullable = false)
	private UseCaseCategory category;
	
	@Size(max = 50)
	@Column(name = "name", length = 50, unique = true)	
	private String name;
	
	@Size(max = 256)
	@Column(name = "description", length = 256)	
	private String description;
	
	@OneToMany(mappedBy = "useCase")	
	private List<OilApplication> applications = new ArrayList<>();	
}
