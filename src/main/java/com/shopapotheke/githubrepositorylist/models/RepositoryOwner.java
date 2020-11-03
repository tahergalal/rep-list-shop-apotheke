package com.shopapotheke.githubrepositorylist.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@ToString
class RepositoryOwner implements Serializable {

    private static final long serialVersionUID = 8774645711121289325L;
    private String avatar_url;
    private String url;
    private String followersUrl;
    private String followingUrl;
    private String gistsUrl;
    private String starredUrl;
    private String subscriptionsUrl;
    private String organizationsUrl;
    private String reposUrl;
    private String eventsUrl;
    private String receivedEventsUrl;
    private String Type;
    private boolean siteAdmin;
}
