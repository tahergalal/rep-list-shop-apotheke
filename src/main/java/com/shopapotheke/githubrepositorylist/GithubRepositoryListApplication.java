package com.shopapotheke.githubrepositorylist;

import com.shopapotheke.githubrepositorylist.exceptions.RestTemplateErrorHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class GithubRepositoryListApplication {

    public static void main(String[] args) {
        SpringApplication.run(GithubRepositoryListApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restBuilder) {

        return restBuilder.errorHandler(new RestTemplateErrorHandler()).build();
    }

}
