package com.example.demo.service;

import com.example.demo.entity.Restaurant;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DistanceMatrixService {
    private static final Logger logger = LoggerFactory.getLogger(DistanceMatrixService.class);
    private static final String API_URL = "https://api.olamaps.io/routing/v1/distanceMatrix";

    @Value("${api.client-id}")
    private String apiKey;

    public Map<Long, LocalTime> getDistanceMatrix(String origin, List<Restaurant> nearbyRestaurants, String token) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        String destinations = concatLatLong(nearbyRestaurants);

        HttpUrl.Builder urlBuilder = HttpUrl.parse(API_URL).newBuilder()
                .addQueryParameter("origins", origin)
                .addQueryParameter("destinations", destinations)
                .addQueryParameter("mode", "driving")
                .addQueryParameter("api_key", this.apiKey);


        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .addHeader("Authorization", "Bearer " + token)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            System.out.println(response);
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> responseBody = mapper.readValue(response.body().string(), Map.class);

            Map<Long, LocalTime> restaurantDeliveryTimes = new HashMap<>();

            List<Map<String, Object>> rows = (List<Map<String, Object>>) responseBody.get("rows");

//
            if (rows != null && !rows.isEmpty()) {
                List<Map<String, Object>> elements = (List<Map<String, Object>>) rows.get(0).get("elements");


                for (int i = 0; i < nearbyRestaurants.size(); i++) {
                    Restaurant restaurant = nearbyRestaurants.get(i);
                    Map<String, Object> element = elements.get(i);

                    Object durationObj = element.get("duration");
                    if (durationObj instanceof Integer) {
                        Integer durationInSeconds = (Integer) durationObj;
                        if (durationInSeconds != null) {
                            LocalTime deliveryTime = LocalTime.ofSecondOfDay(durationInSeconds);

                            restaurantDeliveryTimes.put(restaurant.getId(), deliveryTime);
                        }
                    } else {
                        logger.error("Unexpected type for 'duration': " + durationObj);
                    }
                }
            }

            return restaurantDeliveryTimes;
        }
    }

    private String concatLatLong(List<Restaurant> nearbyRestaurants) {
        StringBuilder latLongBuilder = new StringBuilder();

        for (Restaurant restaurant : nearbyRestaurants) {
            double latitude = restaurant.getLatitude();
            double longitude = restaurant.getLongitude();

            latLongBuilder.append(latitude)
                    .append(",")
                    .append(longitude)
                    .append("|");
        }

        if (latLongBuilder.length() > 0) {
            latLongBuilder.setLength(latLongBuilder.length() - 1);
        }

        return latLongBuilder.toString();
    }
}
