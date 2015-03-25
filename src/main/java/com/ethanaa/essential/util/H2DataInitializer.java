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
import com.ethanaa.essential.domain.Application;
import com.ethanaa.essential.domain.ApplicationCategory;
import com.ethanaa.essential.domain.Authority;
import com.ethanaa.essential.domain.Oil;
import com.ethanaa.essential.domain.OilApplication;
import com.ethanaa.essential.domain.OilImage;
import com.ethanaa.essential.domain.OilInfoItem;
import com.ethanaa.essential.domain.OilReview;
import com.ethanaa.essential.domain.Section;
import com.ethanaa.essential.domain.UseCase;
import com.ethanaa.essential.domain.UseCaseCategory;
import com.ethanaa.essential.domain.User;
import com.ethanaa.essential.repository.AuthorityRepository;
import com.ethanaa.essential.service.ApplicationService;
import com.ethanaa.essential.service.OilService;
import com.ethanaa.essential.service.UseCaseService;
import com.ethanaa.essential.service.UserService;

@Component
@Profile({Constants.SPRING_PROFILE_DEVELOPMENT})
public class H2DataInitializer implements InitializingBean {

	private AuthorityRepository authorityRepository;
	private ApplicationService applicationService;
	private UseCaseService useCaseService;
	private UserService userService;
	private OilService oilService;
	
	@Autowired
	public H2DataInitializer(AuthorityRepository authorityRepository,
			ApplicationService applicationService,
			UseCaseService useCaseService,
			UserService userService, 
			OilService oilService) {
		
		this.authorityRepository = authorityRepository;
		this.applicationService = applicationService;
		this.useCaseService = useCaseService;
		this.userService = userService;
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
		users.put("admin", userService.createUser("admin", "admin", "AdminFirst", "AdminLast", "admin@essential.com", true, authorities.get(0), authorities.get(1)));
		users.put("user", userService.createUser("user", "user", "UserFirst", "UserLast", "user@essential.com", true));
		users.put("ethanaa", userService.createUser("ethanaa", "ethanaa", "Ethan", "Anderson", "ethanaa@gmail.com", true));
		
		Map<String, Oil> oils = new HashMap<>();
		oils.put("agar", oilService.createOil("Agar"));
		oils.put("ajwain", oilService.createOil("Ajwain"));
		oils.put("angelica", oilService.createOil("Angelica Root"));
		oils.put("anise", oilService.createOil("Anise"));
		
		Map<String, UseCase> useCases = new HashMap<>();
		useCases.put("headache", useCaseService.createUseCase("Headache", "Pain in the head", UseCaseCategory.PAIN));
		useCases.put("constipation", useCaseService.createUseCase("Constipation", "Not producing regular bowel movements", UseCaseCategory.DIGESTIVE));		
		
		Map<String, Application> applications = new HashMap<>();
		applications.put("waft", applicationService.createApplication("Waft", "Waft off of the top and breathe in through the nose and mouth", ApplicationCategory.INHALATION));
		applications.put("apply", applicationService.createApplication("Apply", "Prepare a salve and apply to the affected area", ApplicationCategory.SALVE));
		applications.put("drink", applicationService.createApplication("Drink", "Create a tincture and drink it", ApplicationCategory.TINCTURE));		
		
		oilService.addOilInfoItems(oils.get("agar"),
				
				new OilInfoItem(Section.USES, "Modern Day Uses", 
						"here is some markdown text"),
						
				new OilInfoItem(Section.SPECIES, "Aquilaria species that produce agarwood", 
						"Aquilaria acuminata, found in Papua New Guinea"),
						
				new OilInfoItem(Section.CHARACTERISTICS, "Visible nature",
						"Apears brownish-green in color", 1));
		
		oilService.addOilInfoItems(oils.get("anise"),
				
				new OilInfoItem(Section.CLASSIFICATION, "Scientific", 
						"Kingdom: Plantae"));
		
		oilService.addOilApplications(oils.get("agar"),				
				new OilApplication(useCases.get("headache"), applications.get("waft")),
				new OilApplication(useCases.get("constipation"), applications.get("drink")));
		
		oilService.addOilReviews(oils.get("anise"),
				
				new OilReview(users.get("ethanaa"), 7.5f, 
						"liked the smell."),
						
				new OilReview(users.get("user"), 1.0f, 
						"tasted like liquorice. yuck."));
		
		oilService.addOilImages(oils.get("agar"),
				new OilImage("Agar", "Agar", "Agar in its natural habitat", "agar.png"));
	}

}
