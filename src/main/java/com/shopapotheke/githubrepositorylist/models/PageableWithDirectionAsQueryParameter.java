package com.shopapotheke.githubrepositorylist.models;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Parameters({@Parameter(
        in = ParameterIn.QUERY,
        description = "One-based page index (1..N)",
        name = "page",
        content = {@Content(
                schema = @Schema(
                        type = "integer",
                        defaultValue = "1"
                )
        )}
), @Parameter(
        in = ParameterIn.QUERY,
        description = "The size of the page to be returned",
        name = "size",
        content = {@Content(
                schema = @Schema(
                        type = "integer",
                        defaultValue = "20"
                )
        )}
), @Parameter(
        in = ParameterIn.QUERY,
        description = "Sort direction expected asc|desc",
        name = "sortDirection",
        content = {@Content(
                schema = @Schema(
                        type = "string"

                )

        )}
)})

public @interface PageableWithDirectionAsQueryParameter {
}
