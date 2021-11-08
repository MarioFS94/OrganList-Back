package es.organlist.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.net.URI;
import java.util.List;
import java.util.function.Function;

@Service
public class ProductServiceImpl {

    @Value("${mercadona.base-url}")
    private String rootMercadonaAPI;

    private final WebClient webClient;

    @Autowired
    public ProductServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl(rootMercadonaAPI)
                //.clientConnector(new ReactorClientHttpConnector(HttpClient.create().followRedirect(true)))
                .build();
    }

    public Object getProducts() {
        URI uri = UriComponentsBuilder
                .fromHttpUrl(rootMercadonaAPI + "/api/v1_1/categories/") //api/public/categories
                .buildAndExpand().toUri();
        return this.webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }
}
