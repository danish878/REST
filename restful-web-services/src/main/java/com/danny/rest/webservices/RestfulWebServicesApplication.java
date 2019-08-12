package com.danny.rest.webservices;

import com.danny.rest.webservices.dto.Post;
import com.danny.rest.webservices.dto.User;
import com.danny.rest.webservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Date;
import java.util.Locale;

@SpringBootApplication
public class RestfulWebServicesApplication implements CommandLineRunner {

    @Autowired
    private UserRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(RestfulWebServicesApplication.class, args);
    }

    @Bean
    public LocaleResolver localeResolver() {
//		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
        localeResolver.setDefaultLocale(Locale.US);
        return localeResolver;
    }

//	Alternatively configured in application.properties
//	@Bean
//	public ResourceBundleMessageSource messageSource() {
//		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//		messageSource.setBasename("messages");
//		return messageSource;
//	}


    @Override
    public void run(String... args) throws Exception {
        User user = new User("Danny", new Date());
        Post post = new Post("description1");
        post.setUser(user);

        user.getPosts().add(post);

        post = new Post("description2");
        post.setUser(user);

        user.getPosts().add(post);

        post = new Post("description3");
        post.setUser(user);

        user.getPosts().add(post);

        repository.save(user);

        user = new User("Dandi", new Date());
        repository.save(user);

        user = new User("Totha", new Date());
        repository.save(user);
    }
}
