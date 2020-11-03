package com.shopapotheke.githubrepositorylist.exceptions;

public class UnsuportedOrderException extends RuntimeException {
    private static final long serialVersionUID = -6105504673676408119L;

    public UnsuportedOrderException() {
        super("The provided value is unsupported please use either asc or desc");
    }
}
