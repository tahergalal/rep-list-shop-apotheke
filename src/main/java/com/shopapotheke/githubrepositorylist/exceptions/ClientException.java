package com.shopapotheke.githubrepositorylist.exceptions;

public class ClientException extends RuntimeException {

    private static final long serialVersionUID = 5673457608653317150L;

    public ClientException() {
        super("An error occured within the client please revise your input and try again");
    }
}
