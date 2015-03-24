package com.ethanaa.essential.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.ethanaa.essential.config.Constants;
import com.ethanaa.essential.domain.Authority;
import com.ethanaa.essential.domain.Oil;
import com.ethanaa.essential.domain.OilApplication;
import com.ethanaa.essential.domain.OilInfoItem;
import com.ethanaa.essential.domain.Section;
import com.ethanaa.essential.domain.UseCase;
import com.ethanaa.essential.domain.User;
import com.ethanaa.essential.repository.AuthorityRepository;
import com.ethanaa.essential.service.OilService;
import com.ethanaa.essential.service.UserService;

@Component
@Profile({Constants.SPRING_PROFILE_DEVELOPMENT})
public class H2DataInitializer implements InitializingBean {

	private UserService userService;
	private AuthorityRepository authorityRepository;
	private OilService oilService;
	
	@Autowired
	public H2DataInitializer(UserService userService, 
			AuthorityRepository authorityRepository, 
			OilService oilService) {
		
		this.userService = userService;
		this.authorityRepository = authorityRepository;
		this.oilService = oilService;		
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		
		List<Authority> authorities = Arrays.asList(new Authority[] {
				new Authority("ROLE_USER"),
				new Authority("ROLE_ADMIN")				
		});
		
		authorities = authorityRepository.save(authorities);
		
		Map<String, User> users = new HashMap<>();
		users.put("admin", userService.createUser("admin", "admin", "AdminFirst", "AdminLast", "admin@essential.com", true));
		users.put("user", userService.createUser("user", "user", "UserFirst", "UserLast", "user@essential.com", true));
		users.put("ethanaa", userService.createUser("ethanaa", "ethanaa", "Ethan", "Anderson", "ethanaa@gmail.com", true));
		
		users.get("admin").addAuthorities(authorities.get(1));
		users.get("ethanaa").addAuthorities(authorities.get(1));
		
		Map<String, Oil> oils = new HashMap<>();
		oils.put("agar", oilService.createOil("Agar"));
		oils.put("ajwain", oilService.createOil("Ajwain"));
		oils.put("angelica", oilService.createOil("Angelica Root"));
		oils.put("anise", oilService.createOil("Anise"));
		
		Map<String, UseCase> useCases = new HashMap<>();
		useCases.put("headache", new UseCase());
		
		oilService.addOilInfoItems(oils.get("agar"), 
				new OilInfoItem(Section.USES, "Modern Day Uses", "here is some markdown text"), 
				new OilInfoItem(Section.SPECIES, "Aquilaria species that produce agarwood", "Aquilaria acuminata, found in Papua New Guinea"));
		
		oilService.addOilInfoItems(oils.get("anise"),
				new OilInfoItem(Section.CLASSIFICATION, "", "Kingdom: Plantae"));
		
		oilService.addOilApplications(oils.get("agar"), new OilApplication());
				
	}

}
