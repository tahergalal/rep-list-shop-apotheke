package com.shopapotheke.githubrepositorylist.exceptions;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

import static org.springframework.http.HttpStatus.Series.*;

// Handler to handle exceptions that can happen when using the rest api
@Component
public class RestTemplateErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        return (
                clientHttpResponse.getStatusCode().series() == CLIENT_ERROR
                        || clientHttpResponse.getStatusCode().series() == SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {

        if (clientHttpResponse.getStatusCode().series() == SERVER_ERROR) {
            throw new InternalServerException();
        } else if (clientHttpResponse.getStatusCode().series() == CLIENT_ERROR) {
            throw new ClientException();
        } else if (clientHttpResponse.getStatusCode().series() != SUCCESSFUL) {
            throw new RuntimeException();
        }


    }
}
