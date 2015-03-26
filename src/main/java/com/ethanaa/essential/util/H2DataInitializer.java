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
import com.ethanaa.essential.domain.ImageType;
import com.ethanaa.essential.domain.IngredientUnit;
import com.ethanaa.essential.domain.Oil;
import com.ethanaa.essential.domain.OilApplication;
import com.ethanaa.essential.domain.OilBlend;
import com.ethanaa.essential.domain.OilBlendApplication;
import com.ethanaa.essential.domain.OilBlendIngredient;
import com.ethanaa.essential.domain.OilBlendReview;
import com.ethanaa.essential.domain.OilImage;
import com.ethanaa.essential.domain.OilInfoItem;
import com.ethanaa.essential.domain.OilReview;
import com.ethanaa.essential.domain.Section;
import com.ethanaa.essential.domain.UseCase;
import com.ethanaa.essential.domain.UseCaseCategory;
import com.ethanaa.essential.domain.User;
import com.ethanaa.essential.repository.AuthorityRepository;
import com.ethanaa.essential.service.ApplicationService;
import com.ethanaa.essential.service.OilBlendService;
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
	private OilBlendService oilBlendService;
	
	@Autowired
	public H2DataInitializer(AuthorityRepository authorityRepository,
			ApplicationService applicationService,
			UseCaseService useCaseService,
			UserService userService, 
			OilService oilService,
			OilBlendService oilBlendService) {
		
		this.authorityRepository = authorityRepository;
		this.applicationService = applicationService;
		this.useCaseService = useCaseService;
		this.userService = userService;
		this.oilService = oilService;
		this.oilBlendService = oilBlendService;
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
		
		Map<String, OilBlend> oilBlends = new HashMap<>();
		oilBlends.put("midnight", oilBlendService.createOilBlend("Midnight Madness", users.get("admin")));
		oilBlends.put("brain", oilBlendService.createOilBlend("Brain Power", users.get("admin")));		
		oilBlends.put("awaken", oilBlendService.createOilBlend("Awaken", users.get("admin")));				
		
		Map<String, UseCase> useCases = new HashMap<>();
		useCases.put("headache", useCaseService.createUseCase("Headache", "Pain in the head", UseCaseCategory.PAIN));
		useCases.put("constipation", useCaseService.createUseCase("Constipation", "Not producing regular bowel movements", UseCaseCategory.DIGESTIVE));
		useCases.put("insomnia", useCaseService.createUseCase("Insomnia", "Unable to sleep", UseCaseCategory.SLEEP_DISORDER));
		
		Map<String, Application> applications = new HashMap<>();
		applications.put("waft", applicationService.createApplication("Waft", "Waft off of the top and breathe in through the nose and mouth", ApplicationCategory.INHALATION));
		applications.put("apply", applicationService.createApplication("Apply", "Prepare a salve and apply to the affected area", ApplicationCategory.SALVE));
		applications.put("drink", applicationService.createApplication("Drink", "Create a tincture and drink it", ApplicationCategory.TINCTURE));		
		
		/**
		 * Build oils
		 */
		
		oilService.addOilInfoItems(oils.get("agar"),
				
				new OilInfoItem(Section.USES, "Modern Day Uses", 
						"here is some markdown text"),
						
				new OilInfoItem(Section.SPECIES, "Aquilaria species that produce agarwood", 
						"Aquilaria acuminata, found in Papua New Guinea"),
						
				new OilInfoItem(Section.CHARACTERISTICS, "Appearance",
						"Appears brownish-green in color", 1),
						
				new OilInfoItem(Section.CHARACTERISTICS, "Taste",
						"Tastes like bark", 2),
		
				new OilInfoItem(Section.CHARACTERISTICS, "Smell",
						"Smells like bark", 2),
						
				new OilInfoItem(Section.CHARACTERISTICS, "History",
						"")
		);		
		
		oilService.addOilInfoItems(oils.get("anise"),
				
				new OilInfoItem(Section.CLASSIFICATION, "Scientific", 
						"Kingdom: Plantae")
		);
		
		oilService.addOilApplications(oils.get("agar"),				
				new OilApplication(useCases.get("headache"), applications.get("waft")),
				new OilApplication(useCases.get("constipation"), applications.get("drink"))
		);
		
		oilService.addOilReviews(oils.get("anise"),
				
				new OilReview(users.get("ethanaa"), 7.5f, 
						"liked the smell."),
						
				new OilReview(users.get("user"), 1.0f, 
						"tasted like liquorice. yuck.")
		);
		
		oilService.addOilImages(oils.get("agar"),
				new OilImage(ImageType.OIL_ICON,  "AgarOil", "Agar Oil", "Agar Oil", "wood.png"),
				new OilImage(ImageType.OIL_EXTRA, "Agarwood", "1st Grade Agarwood", "Agarwood", "agar-wood.jpg")
		);
		
		/**
		 * Build oil blends
		 */
		
		oilBlendService.addOilBlendIngredients(oilBlends.get("midnight"), 
				new OilBlendIngredient(oils.get("agar"), 1.0f, IngredientUnit.DROPS, ""),
				new OilBlendIngredient(oils.get("anise"), 2.0f, IngredientUnit.DROPS, "")
		);
		
		oilBlendService.addOilBlendApplications(oilBlends.get("midnight"), 
				new OilBlendApplication(useCases.get("insomnia"), applications.get("waft"))
		);
		
		oilBlendService.addOilBlendReviews(oilBlends.get("midnight"),
				
				new OilBlendReview(users.get("admin"), 8.8f,						
						"Takes about an hour to feel the effects"),
						
				new OilBlendReview(users.get("user"), 2.3f,						
						"Didn't work for me and the ingredients are pricey")
		);
	}

}
