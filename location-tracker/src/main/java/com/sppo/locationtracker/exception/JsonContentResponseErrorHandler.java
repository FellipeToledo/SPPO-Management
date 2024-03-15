package com.sppo.locationtracker.exception;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.net.URI;
import java.util.Objects;

/**
 * @author Fellipe Toledo
 */
public class JsonContentResponseErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return !Objects.requireNonNull(response.getHeaders().getContentType()).isCompatibleWith(MediaType.APPLICATION_JSON);
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {}

    @Override
    public void handleError(URI url, HttpMethod method, ClientHttpResponse response) throws IOException {
        ResponseErrorHandler.super.handleError(url, method, response);
    }
}
