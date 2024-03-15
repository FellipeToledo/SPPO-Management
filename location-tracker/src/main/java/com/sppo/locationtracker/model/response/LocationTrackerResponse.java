package com.sppo.locationtracker.model.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Fellipe Toledo
 */
@Getter
@Setter
public class LocationTrackerResponse {

    private String ordem;
    private String latitude;
    private String longitude;
    private long datahora;
    private float velocidade;
    private String linha;
    private long datahoraenvio;
    private long datahoraservidor;
}
