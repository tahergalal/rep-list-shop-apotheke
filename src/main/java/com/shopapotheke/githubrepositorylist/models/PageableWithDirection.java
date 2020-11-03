package com.shopapotheke.githubrepositorylist.models;

import com.shopapotheke.githubrepositorylist.exceptions.UnsuportedOrderException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@NotNull
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class PageableWithDirection {
    private String sortDirection = "asc";
    private int page;
    private int size;

    public PageableWithDirection(@NotNull @Min(1L) int page, @NotNull @Min(1L) @Max(2000L) int size, String sortDirection) {
        super();
        this.page = page;
        this.size = size;
        this.sortDirection = sortDirection;
    }


    public void setSortDirection(String sortDirection) {
        if (sortDirection.toLowerCase().trim().equals("asc") || sortDirection.toLowerCase().trim().equals("desc")) {
            this.sortDirection = sortDirection;
        } else {
            throw new UnsuportedOrderException();
        }
    }
}
