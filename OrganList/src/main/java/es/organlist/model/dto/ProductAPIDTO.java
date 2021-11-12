package es.organlist.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String id;
    /**
     * The Slug.
     */
    private String slug;
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
    /**
     * The Display name.
     */
    private String display_name;
    /**
     * The Price instructions.
     */
    private PriceDataAPIDTO price_instructions;
}
