package com.shopapotheke.githubrepositorylist.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@EqualsAndHashCode
@ToString
@Getter
@Setter
public class RepositoryLicense implements Serializable {

    private static final long serialVersionUID = 5058299387094659096L;
    private String key;
    private String name;
    private String spdxId;
    private String url;
    private String nodeId;

}
