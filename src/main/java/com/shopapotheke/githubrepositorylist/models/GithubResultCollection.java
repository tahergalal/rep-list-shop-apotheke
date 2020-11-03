package com.shopapotheke.githubrepositorylist.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class GithubResultCollection implements Serializable {
    private static final long serialVersionUID = 95180889618907592L;
    private int total_count;
    private boolean incomplete_results;
    private List<GithubResult> items;

}
