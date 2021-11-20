package es.organlist.model.dto.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * The type Product apidto.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductAPIDTO {
    /**
     * The Id.
     */
    private BigDecimal id;
    /**
     * The Slug.
     */
    private String slug;
    private DetailsAPIDTO details;
    private ImagesAPIDTO photos;
    /**
     * The Limit.
     */
    private int limit;
    /**
     * The Badges.
     */
    private BadgesAPIDTO badges;
    /**
     * The Packaging.
     */
    private String packaging;
    /**
     * The Published.
     */
    private boolean published;
    /**
     * The Share url.
     */
    private String share_url;
    /**
     * The Thumbnail.
     */
    private String thumbnail;
    /**
     * The Categories.
     */
    private List<ProductCategoryAPIDTO> categories;

    //private List<CategoriesLevelAPIDTO> categories;
    //private List<String> categories;
    //private List<Object> categories;
//    private Object categories;
    /**
     * The Display name.
     */
    private String display_name;
    /**
     * The Price instructions.
     */
    private PriceDataAPIDTO price_instructions;
}
