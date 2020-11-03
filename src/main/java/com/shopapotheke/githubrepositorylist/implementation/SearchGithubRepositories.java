package com.shopapotheke.githubrepositorylist.implementation;

import com.shopapotheke.githubrepositorylist.api.SearchGithubRepositoriesAPI;
import com.shopapotheke.githubrepositorylist.models.GithubResultCollection;
import com.shopapotheke.githubrepositorylist.models.PageSize;
import com.shopapotheke.githubrepositorylist.models.PageableWithDirection;
import com.shopapotheke.githubrepositorylist.models.ProgramingLanguages;
import com.shopapotheke.githubrepositorylist.services.GitHubRepositoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class SearchGithubRepositories implements SearchGithubRepositoriesAPI {
    private final GitHubRepositoryService gitHubRepositoryService;

    public SearchGithubRepositories(GitHubRepositoryService gitHubRepositoryService) {
        this.gitHubRepositoryService = gitHubRepositoryService;
    }


    @Override
    public ResponseEntity<GithubResultCollection> filterRepositories(Date filterDate, ProgramingLanguages programingLanguages, PageableWithDirection pageable) {
        return ResponseEntity.ok(gitHubRepositoryService.getGithubRepositories(filterDate, programingLanguages, pageable));
    }

    @Override
    public ResponseEntity<GithubResultCollection> topRepositories(Date filterDate, ProgramingLanguages programingLanguages, PageSize pageSize) {
        PageableWithDirection pageable = new PageableWithDirection(1, pageSize.getPageSize(), "desc");
        return ResponseEntity.ok(gitHubRepositoryService.getGithubRepositories(filterDate, programingLanguages, pageable));
    }
}
