package es.organlist.service.impl;

import es.organlist.model.dto.CategoryAPIDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * The type Product service.
 */
@Service
public class ProductServiceImpl {

    /**
     * Url base de la API
     */
    @Value("${mercadona.base-url}")
    private String rootMercadonaAPI;

    /**
     * webclient library
     */
    private final WebClient webClient;

    /**
     * Instantiates a new Product service.
     *
     * @param webClientBuilder the web client builder
     */
    @Autowired
    public ProductServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl(rootMercadonaAPI)
                .build();
    }

    /**
     * Gets products categories.
     *
     * @return the products categories
     */
    public List<CategoryAPIDTO> getProductsCategories() {
        URI uri = UriComponentsBuilder
                .fromHttpUrl(rootMercadonaAPI + "/api/v1_1/categories/")
                .buildAndExpand().toUri();
        ResultDTO response = this.webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(ResultDTO.class)
                .block();
        return response.getResults();
    }

    public CategoryAPIDTO getProductsSubcategoriesByCategory(Integer category, String lang) {
        URI uri = UriComponentsBuilder
                .fromHttpUrl(rootMercadonaAPI + "/api/v1_1/categories/"+ category +"/?lang=" + lang)
                .buildAndExpand().toUri();
        CategoryAPIDTO response = this.webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(CategoryAPIDTO.class)
                .block();
        return response;
    }
}
