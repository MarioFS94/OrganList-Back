package es.organlist.service.impl;

import es.organlist.model.dto.CategoryAPIDTO;
import es.organlist.model.dto.ProductAPIDTO;
import es.organlist.model.dto.SubcategoryAPIDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

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
        URI uri = UriComponentsBuilder
                .fromHttpUrl(rootMercadonaAPI + "/api/v1_1/categories/?lang=" + lang)
                .buildAndExpand().toUri();

        ResultDTO response = this.webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(ResultDTO.class)
                .block();
        List<CategoryAPIDTO> list = response != null ? response.getResults() : new ArrayList<>();
        List<ProductAPIDTO> productList = getProducts(list, lang);

        return list;
    }

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

        return products;
    }

    /**
     * Gets products category by id.
     *
     * @param categoryId the category
     * @param lang       the lang
     * @return the products category by id
     */
    public CategoryAPIDTO getProductsCategoryById(Integer categoryId, String lang) {

        URI uri;
       /* if (Objects.isNull(category)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id de categoría no informado");
        }*/
        try {
            uri = UriComponentsBuilder
                    .fromHttpUrl(rootMercadonaAPI + "/api/v1_1/categories/" + categoryId + "/?lang=" + lang)
                    .buildAndExpand().toUri();
        } catch (Exception ex) {
            log.debug("URL mal formada.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "URL mal formada " + ex);
        }

/*try{

}catch (WebException ex)
{
    if (ex.Status == WebExceptionStatus.ProtocolError && ex.Response != null)
    {
        var resp = (HttpWebResponse)ex.Response;
        if (resp.StatusCode == HttpStatusCode.NotFound) // HTTP 404
        {
            //Handle it
        }
    }
    //Handle it
}*/
        CategoryAPIDTO response = this.webClient.get()
                .uri(uri)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, responseStatus -> {
                    log.debug("Id de categoría no informado");
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id de categoría no informado");
                })
                .bodyToMono(CategoryAPIDTO.class)
                .block();

        List<ProductAPIDTO> productList = getProductsByCategory(response);

        return response;
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
            for (ProductAPIDTO productAPIDTO : subcategory.getProducts()) {//subcategory.getCategories()
                products.add(productAPIDTO);
            }
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
        URI uri = UriComponentsBuilder
                .fromHttpUrl(rootMercadonaAPI + "/api/v1_1/products/" + productId + "/?lang=" + lang)
                .buildAndExpand().toUri();

        ProductAPIDTO response = this.webClient.get()
                .uri(uri)
                .retrieve()
//                .onStatus(HttpStatus::is4xxClientError, responseStatus -> {
//                    log.debug("Id de categoría no informado");
//                    if (responseStatus.rawStatusCode() == 404) {
//                        //throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Recurso no encontrado");
//                        throw new Error("Recurso no encontrado");
//                    }
//                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id de categoría no informado");
//                })
                .bodyToMono(ProductAPIDTO.class)
                .block();

        return response;
    }
}
