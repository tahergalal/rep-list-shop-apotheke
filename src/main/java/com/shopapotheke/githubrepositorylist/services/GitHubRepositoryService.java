package com.shopapotheke.githubrepositorylist.services;

import com.shopapotheke.githubrepositorylist.configuration.SearchProperties;
import com.shopapotheke.githubrepositorylist.models.GithubResultCollection;
import com.shopapotheke.githubrepositorylist.models.PageableWithDirection;
import com.shopapotheke.githubrepositorylist.models.ProgramingLanguages;
import com.shopapotheke.githubrepositorylist.utils.Constants;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static com.shopapotheke.githubrepositorylist.utils.Constants.*;

@Service
public class GitHubRepositoryService {
    private final RestTemplate template;
    private final SearchProperties searchProperties;


    //
    public GitHubRepositoryService(RestTemplate template, SearchProperties searchProperties) {
        this.template = template;
        this.searchProperties = searchProperties;
    }


    /**
     * @param filterDate         the date used to filter can be null
     * @param programingLanguage the programing language comming from the list of programming languages can also be null
     * @param pageable           The pagination values that can be used to paginate and to set the order
     * @return
     */
    public GithubResultCollection getGithubRepositories(Date filterDate, ProgramingLanguages programingLanguage, PageableWithDirection pageable) {


        UriBuilder uriBuilder = UriComponentsBuilder.fromUriString(searchProperties.getEndpoint());

        URI uri = uriBuilder
                .queryParam("q", buildQuery(filterDate, programingLanguage))
                .queryParam("per_page", pageable.getSize())
                .queryParam("page", pageable.getPage())
                .queryParam("sort", "stars")
                .queryParam("order", pageable.getSortDirection())
                .build();


        RequestEntity entity = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();


        ResponseEntity<GithubResultCollection> result = template.exchange(entity, GithubResultCollection.class);

        return result.getBody();

    }


    /**
     * @param filterDate         the date used to filter can be null
     * @param programingLanguage the programing language comming from the list of programming languages can also be null
     * @return a {@link String} representation of the query that should be executed
     */
    private String buildQuery(Date filterDate, ProgramingLanguages programingLanguage) {

        if (filterDate == null && programingLanguage == null) {
            // we have no filter therefore we want to search for everything
            return Constants.EMPTY_QUERY;
        } else {

            StringBuilder query = new StringBuilder();

            // check if date is set
            if (filterDate != null) {
                LocalDate localDate = filterDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                // convert the date to iso date (Assumption we want to filter by date only no date time)
                query.append(CREATED_DATE_FIELD).append(FIELD_SEPERATOR).append(GREATER_THAN_AND_EQUALS).append(DateTimeFormatter.ISO_DATE.format(localDate));
            }

            // check extra if programming language filter is set
            if (programingLanguage != null) {
                // check if this is an addition to date and if so add a + sign as this is required by the API
                if (query.length() != 0) {
                    query.append(Constants.ADDITIONAL_QUERY);
                }

                // finally set the value
                query.append(LANGUAGE_FIELD_NAME).append(FIELD_SEPERATOR).append(programingLanguage.getProgramingLanguage());

            }
            // return the query representation as a string
            return query.toString();
        }
    }
}
