package com.shopapotheke.githubrepositorylist.services;

import com.shopapotheke.githubrepositorylist.configuration.SearchProperties;
import com.shopapotheke.githubrepositorylist.exceptions.UnsuportedOrderException;
import com.shopapotheke.githubrepositorylist.models.GithubResult;
import com.shopapotheke.githubrepositorylist.models.GithubResultCollection;
import com.shopapotheke.githubrepositorylist.models.PageableWithDirection;
import com.shopapotheke.githubrepositorylist.models.ProgramingLanguages;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class GitHubRepositoryServiceTests {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private SearchProperties searchProperties;

    private UriBuilder uriBuilder;
    private PageableWithDirection pagable = new PageableWithDirection(1, 20, "desc");


    private GitHubRepositoryService repositoryService;

    private GithubResultCollection githubResultCollection;

    @BeforeAll
    public void createResult() {
        githubResultCollection = new GithubResultCollection();
        githubResultCollection.setIncomplete_results(false);
        List<GithubResult> gitHubResultList = new ArrayList();
        GithubResult result = new GithubResult();
        result.setUrl("test-url");
        gitHubResultList.add(result);


        githubResultCollection.setItems(gitHubResultList);
        githubResultCollection.setTotal_count(1);

    }

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn("https://localhost/search/repositories").when(searchProperties).getEndpoint();
        repositoryService = new GitHubRepositoryService(restTemplate, searchProperties);
        uriBuilder = UriComponentsBuilder.fromUriString(searchProperties.getEndpoint());
    }

    @Test
    public void TestWithoutDateAndLanguage() {

        URI uri = uriBuilder
                .queryParam("q", "NONE")
                .queryParam("per_page", pagable.getSize())
                .queryParam("page", pagable.getPage())
                .queryParam("sort", "stars")
                .queryParam("order", pagable.getSortDirection())
                .build();


        RequestEntity entity = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();


        Mockito.doReturn(new ResponseEntity<>(new GithubResultCollection(), HttpStatus.OK))
                .when(restTemplate).exchange(entity, GithubResultCollection.class);

        GithubResultCollection githubResultCollection = repositoryService.getGithubRepositories(null, null, pagable);
        assertThat(githubResultCollection).isNotNull();
//        assertThat(githubResultCollection.getItems().size()).isEqualTo(1);
    }

    @Test
    public void TestWithDate() throws ParseException {
        String date = "2019-01-10";


        URI uri = uriBuilder
                .queryParam("q", "created:>=" + date)
                .queryParam("per_page", pagable.getSize())
                .queryParam("page", pagable.getPage())
                .queryParam("sort", "stars")
                .queryParam("order", "desc")
                .build();
        System.out.println(uri);
        RequestEntity entity = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();

        Mockito
                .doReturn(new ResponseEntity<>(new GithubResultCollection(), HttpStatus.OK))
                .when(restTemplate).exchange(entity, GithubResultCollection.class);
        GithubResultCollection githubResultCollection = repositoryService
                .getGithubRepositories(new SimpleDateFormat("yyyy-MM-dd").parse(date), null, pagable);


        assertThat(githubResultCollection).isNotNull();
//        assertThat(githubResultCollection.getItems().size()).isEqualTo(1);

    }


    @Test
    public void TestWithDateAndLanguage() throws ParseException {
        String date = "2019-01-10";


        URI uri = uriBuilder
                .queryParam("q", "created:>=" + date + "+language:" + ProgramingLanguages.JAVA.getProgramingLanguage())
                .queryParam("per_page", pagable.getSize())
                .queryParam("page", pagable.getPage())
                .queryParam("sort", "stars")
                .queryParam("order", "desc")
                .build();
        System.out.println(uri);
        RequestEntity entity = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();

        Mockito
                .doReturn(new ResponseEntity<>(new GithubResultCollection(), HttpStatus.OK))
                .when(restTemplate).exchange(entity, GithubResultCollection.class);
        GithubResultCollection githubResultCollection = repositoryService
                .getGithubRepositories(new SimpleDateFormat("yyyy-MM-dd").parse(date), ProgramingLanguages.JAVA, pagable);


        assertThat(githubResultCollection).isNotNull();
//        assertThat(githubResultCollection.getItems().size()).isEqualTo(1);

    }


    @Test
    public void TestOrderDirectionASC() {
        pagable.setSortDirection("asc");

        URI uri = uriBuilder
                .queryParam("q", "NONE")
                .queryParam("per_page", pagable.getSize())
                .queryParam("page", pagable.getPage())
                .queryParam("sort", "stars")
                .queryParam("order", pagable.getSortDirection())
                .build();


        RequestEntity entity = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();


        Mockito.doReturn(new ResponseEntity<>(new GithubResultCollection(), HttpStatus.OK))
                .when(restTemplate).exchange(entity, GithubResultCollection.class);

        GithubResultCollection githubResultCollection = repositoryService.getGithubRepositories(null, null, pagable);
        assertThat(githubResultCollection).isNotNull();
//        assertThat(githubResultCollection.getItems().size()).isEqualTo(1);
    }

    @Test
    public void expectExceptionFromOrder() {

        assertThrows(UnsuportedOrderException.class, () -> {
            pagable.setSortDirection("test");
        });
    }

//    @Test
//    public void InternalServerError() {
//
//        pagable.setSortDirection("asc");
//
//        URI uri = uriBuilder
//                .queryParam("q", "NONE")
//                .queryParam("per_page", pagable.getSize())
//                .queryParam("page", pagable.getPage())
//                .queryParam("sort", "stars")
//                .queryParam("order", pagable.getSortDirection())
//                .build();
//
//
//        RequestEntity entity = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();
//
//
//        Mockito.doReturn(new ResponseEntity<>(null, HttpStatus.METHOD_NOT_ALLOWED))
//                .when(restTemplate).exchange(entity, GithubResultCollection.class);
//
//        assertThrows(InternalServerException.class, () -> {
//            GithubResultCollection githubResultCollection = repositoryService.getGithubRepositories(null, null, pagable);
//        });
//    }
}
