package com.shopapotheke.githubrepositorylist.exceptions;

public class InternalServerException extends RuntimeException {

    private static final long serialVersionUID = -6111247185874311483L;

    InternalServerException() {
        super("An Internal server error has occurred  please try again later");
    }
}
