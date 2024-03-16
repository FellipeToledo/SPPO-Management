package com.sppo.locationtracker.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sppo.locationtracker.exception.JsonContentResponseErrorHandler;
import com.sppo.locationtracker.model.response.LocationTrackerResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @author Fellipe Toledo
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class LocationTrackerClient {
    @Value("${api.endpoint}")
    private String apiUrl;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Scheduled(fixedRate = 60000) // 60000 milliseconds = 1 minute
    public Stream<LocationTrackerResponse> fetchDataPeriodically() {
        log.info("Fetching data...");
        try {
            restTemplate.setErrorHandler(new JsonContentResponseErrorHandler());
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);

            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                // Extract All Data from API
                String responseData = (responseEntity.getBody());
                LocationTrackerResponse[] response = objectMapper.readValue(responseData, LocationTrackerResponse[].class);
                // Map to store the highest DateTime for each order
                Map<String, Long> currentTimeForOrder = new HashMap<>();

                // Iterate over the array to find the highest number for each order
                for (LocationTrackerResponse locationTrackerResponse : response) {
                    String order = locationTrackerResponse.getOrdem();
                    long dateTime = locationTrackerResponse.getDatahora();
                    currentTimeForOrder.put(order, Math.max(currentTimeForOrder.getOrDefault(order, Long.MIN_VALUE), dateTime));
                }

                Stream<LocationTrackerResponse> locationTrackerNow = Arrays
                        .stream(response)
                        .filter(obj -> obj.getDatahora() == currentTimeForOrder.get(obj.getOrdem()));

                long count = Arrays.stream(response)
                        .filter(obj -> obj.getDatahora() == currentTimeForOrder.get(obj.getOrdem()))
                        .count();

                // Print the count of objects with the highest number for each order
                System.out.println("Number of objects in current time: " + count);
                log.info("Search accomplished!");
                return locationTrackerNow;
            } else {
                System.err.println("Failed to fetch data from the API. Status code: " + responseEntity.getStatusCode());
            }
        } catch (Exception e) {
            System.err.println("Error fetching data from the API: " + e.getMessage());
        }
        return null;
    }
}