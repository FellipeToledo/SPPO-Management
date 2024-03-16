package com.sppo.locationtracker.controller;

import com.sppo.locationtracker.model.response.LocationTrackerResponse;
import com.sppo.locationtracker.service.LocationTrackerClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

/**
 * @author Fellipe Toledo
 */
@RestController
@RequestMapping("/sppo")
@RequiredArgsConstructor
public class LocationTrackerController {
    private final LocationTrackerClient client;

    @GetMapping("/location-tracker")
    @ResponseBody
    public Stream<LocationTrackerResponse> fetchDataFromApi() {
        try {
            // Call the ApiService to fetch data from the API
            return client.fetchDataPeriodically();
        } catch (Exception ignored) {
        }
        return null ;
    }
}
