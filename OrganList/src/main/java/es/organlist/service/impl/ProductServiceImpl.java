package es.organlist.service.impl;

import es.organlist.model.dto.api.CategoryAPIDTO;
import es.organlist.model.dto.api.ProductAPIDTO;
import es.organlist.model.dto.api.ResultAPIDTO;
import es.organlist.model.dto.api.SubcategoryAPIDTO;
import es.organlist.utils.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.util.UriComponentsBuilder;
import org.webjars.NotFoundException;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Product service.
 */
@Service
@Slf4j
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
     * @param lang the lang
     * @return the products categories
     */
    public List<CategoryAPIDTO> getProductsCategories(String lang) {
        return callProductCategoriesEndpoint(lang);
    }

    /**
     * Gets all products.
     *
     * @param lang the lang
     * @return the all products
     */
    public List<ProductAPIDTO> getAllProducts(String lang) {
        List<CategoryAPIDTO> list = callProductCategoriesEndpoint(lang);
        return getProducts(list, lang);
    }

    /**
     * Call product categories endpoint.
     *
     * @param lang the lang
     * @return the list
     */
    private List<CategoryAPIDTO> callProductCategoriesEndpoint(String lang) {
        URI uri = UriComponentsBuilder
                .fromHttpUrl(rootMercadonaAPI + "/api/v1_1/categories/?lang=" + lang)
                .buildAndExpand().toUri();

        ResultAPIDTO response = this.webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(ResultAPIDTO.class)
                .block();
        List<CategoryAPIDTO> list = response != null ? response.getResults() : new ArrayList<>();

        Util.checkEmptyList(list.isEmpty(), "No hay productos");

        return list;
    }

    /**
     * Gets products.
     *
     * @param categories the categories
     * @param lang       the lang
     * @return the products
     */
    private List<ProductAPIDTO> getProducts(List<CategoryAPIDTO> categories, String lang) {
        List<ProductAPIDTO> products = new ArrayList<>();

        for (CategoryAPIDTO category : categories) {
            for (SubcategoryAPIDTO subcategory : category.getCategories()) {
                //Sacamos la subcategorias
                CategoryAPIDTO categoryObj = getProductsCategoryById(subcategory.getId(), lang);
                //sacamos los productos de cada categoria
                List<ProductAPIDTO> categoryProducts = getProductsByCategory(categoryObj);
                products.addAll(categoryProducts);

            }
        }

        Util.checkEmptyList(products.isEmpty(), "No hay productos");

        return products;
    }

    /**
     * Gets products category by id.
     *
     * @param categoryId the category
     * @param lang       the lang
     * @return the product category by id
     */
    public CategoryAPIDTO getProductsCategoryById(Integer categoryId, String lang) {
        return callProductsCategoryByIdEndpoint(categoryId, lang);
    }

    /**
     * Call products category by id endpoint.
     *
     * @param categoryId the category id
     * @param lang       the lang
     * @return the category apidto
     */
    private CategoryAPIDTO callProductsCategoryByIdEndpoint(Integer categoryId, String lang) {
        URI uri = UriComponentsBuilder
                .fromHttpUrl(rootMercadonaAPI + "/api/v1_1/categories/" + categoryId + "/?lang=" + lang)
                .buildAndExpand().toUri();

        return this.webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(CategoryAPIDTO.class)
                .block();
    }

    /**
     * Gets products by category.
     *
     * @param categoryId the category id
     * @param lang       the lang
     * @return the products by category
     */
    public List<ProductAPIDTO> getProductsByCategory(Integer categoryId, String lang) {
        CategoryAPIDTO response = callProductsCategoryByIdEndpoint(categoryId, lang);
        List<ProductAPIDTO> productsCategory = getProductsByCategory(response);

        Util.checkEmptyList(productsCategory.isEmpty(), "No hay productos en esta categoría");

        return productsCategory;
    }

    /**
     * Gets products.
     *
     * @param category the category
     * @return the products
     */
    private List<ProductAPIDTO> getProductsByCategory(CategoryAPIDTO category) {
        List<ProductAPIDTO> products = new ArrayList<>();
        for (SubcategoryAPIDTO subcategory : category.getCategories()) {
            products.addAll(subcategory.getProducts());
        }
        return products;
    }

    /**
     * Gets product by id.
     *
     * @param productId the productId
     * @param lang      the lang
     * @return the product by id
     */
    public ProductAPIDTO getProductById(Integer productId, String lang) {
        ProductAPIDTO response = null;

        URI uri = UriComponentsBuilder
                .fromHttpUrl(rootMercadonaAPI + "/api/v1_1/products/" + productId + "/?lang=" + lang)
                .buildAndExpand().toUri();

        try{
            response = this.webClient.get()
                    .uri(uri)
                    .retrieve()
                    .bodyToMono(ProductAPIDTO.class)
                    .block();
        } catch (WebClientResponseException e) {
            log.info("Is an error {}", e.getStatusCode());
            if (HttpStatus.NOT_FOUND != e.getStatusCode()){
                throw new NotFoundException("404 custom");
            }
        }

        return response;
    }
}
