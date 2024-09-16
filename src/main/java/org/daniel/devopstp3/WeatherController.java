package org.daniel.devopstp3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin("*")
@RequestMapping("/clima")
public class WeatherController {
    @GetMapping
    public Mono<String> getClima(@RequestParam(name = "latitude", defaultValue = "0.0") Double latitude,
                                  @RequestParam(name = "longitude", defaultValue = "0.0") Double longitude) {
        WebClient client =  WebClient.builder().baseUrl("https://api.open-meteo.com/v1/forecast").build();
        return client.get().uri(uriBuilder -> uriBuilder
                    .queryParam("latitude", latitude.toString())
                    .queryParam("longitude", longitude.toString())
                    .build())
            .retrieve()
            .bodyToMono(String.class);
    }
}
