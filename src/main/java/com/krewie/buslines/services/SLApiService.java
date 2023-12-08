package com.krewie.buslines.services;

import com.krewie.buslines.models.external.JourneyResponse;
import com.krewie.buslines.models.external.StopPointResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class SLApiService {

    private final WebClient webClient;
    @Value(value = "${app.sl.apikey}")
    private String apiKey;

    @Autowired
    public SLApiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("https://api.sl.se/api2/linedata.json")
                .build();
    }

    public Mono<JourneyResponse> getJourneyAlongLines() {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("model", "journeypatternpointonline")
                        .queryParam("key", apiKey)
                        .queryParam("DefaultTransportModeCode", "BUS")
                        .build())
                .retrieve()
                .bodyToMono(JourneyResponse.class);
    }

    public Mono<StopPointResponse> getStopPoints() {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("model", "stop")
                        .queryParam("key", apiKey)
                        .queryParam("DefaultTransportModeCode", "BUS")
                        .build())
                .retrieve()
                .bodyToMono(StopPointResponse.class);
    }


}
