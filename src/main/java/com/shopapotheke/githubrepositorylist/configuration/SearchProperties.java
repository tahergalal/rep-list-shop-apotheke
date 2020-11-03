package com.shopapotheke.githubrepositorylist.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "search")
@Getter
@Setter
public class SearchProperties {
    private String endpoint;
}
