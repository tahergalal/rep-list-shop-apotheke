package com.shopapotheke.githubrepositorylist.api;

import com.shopapotheke.githubrepositorylist.models.*;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@RequestMapping("/repositories")
public interface SearchGithubRepositoriesAPI {

    @GetMapping("/filterRepositories")
    @ApiResponse(description = "Will be used to search repositories on github and order the results depending" +
            "as asc or desc depending on the input value it also supports a pagination allowing for obtaining futher" +
            "pages if required")
    @PageableWithDirectionAsQueryParameter
    ResponseEntity<GithubResultCollection> filterRepositories(

            @RequestParam(name = "filterDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date filterDate,

            @RequestParam(name = "language", required = false) ProgramingLanguages programingLanguages,
            @Parameter(hidden = true) PageableWithDirection pageable);

    @GetMapping("/topRepositories")
    @ApiResponse(description = "Will be used to search repositories on github and provide the top stared repositories" +
            "filtered by date and/or language the size of the pages is a set defined value")
    ResponseEntity<GithubResultCollection> topRepositories(

            @RequestParam(name = "filterDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date filterDate,

            @RequestParam(name = "language", required = false) ProgramingLanguages programingLanguages,
            @RequestParam(name = "Page Size") PageSize pageSize
    );

}
