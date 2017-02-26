package com.company;

import com.company.entities.User;
import com.company.factories.UserFactory;
import com.company.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import sun.tools.jar.CommandLine;

import java.util.Collection;

@SpringBootApplication
public class MedicalAppApplication {
	private static final Logger logger = LoggerFactory.getLogger(MedicalAppApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MedicalAppApplication.class, args);
	}

	@Bean(name = "messageSource")
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageBundle = new ReloadableResourceBundleMessageSource();
		messageBundle.setBasename("classpath:messages/messages");
		messageBundle.setDefaultEncoding("UTF-8");
		return messageBundle;
	}

	@Bean
	public CommandLineRunner init(UserRepository userRepository) {
		return (evt) -> {
			for (int i=0; i<20; i++) {
				userRepository.save(UserFactory.newUser());
			}

			Page<User> userList = userRepository.findAll(new PageRequest(0, 20));
			logger.info("------------------------------------------");
			for (User user: userList) {
				logger.info(user.toString());
			}

			User user = userRepository.findOne(16L);
			logger.info("------------------------------------------");
			logger.info("Find one: " + user.toString());

			user.setName("Luis");
			user.setLastName("Troya");
			userRepository.save(user);
			logger.info("------------------------------------------");
			logger.info("Updating one: " + user.toString());

			logger.info("------------------------------------------");
			logger.info("Number of users: " + userRepository.count());
		};
	}
}
