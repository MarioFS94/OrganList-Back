package es.organlist.model.dto.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Price data apidto.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceDataAPIDTO {
    /**
     * The Iva.
     */
    private int iva;
    /**
     * The Is new.
     */
    private boolean is_new;
    /**
     * The Is pack.
     */
    private boolean is_pack;
    /**
     * The Pack size.
     */
    private double pack_size;
    /**
     * The Unit name.
     */
    private String unit_name;
    /**
     * The Unit size.
     */
    private double unit_size;
    /**
     * The Bulk price.
     */
    private String bulk_price;
    /**
     * The Unit price.
     */
    private String unit_price;
    /**
     * The Approx size.
     */
    private boolean approx_size;
    /**
     * The Size format.
     */
    private String size_format;
    /**
     * The Total units.
     */
    private int total_units;
    /**
     * The Unit selector.
     */
    private boolean unit_selector;
    /**
     * The Bunch selector.
     */
    private boolean bunch_selector;
    /**
     * The Drained weight.
     */
    private Object drained_weight;
    /**
     * The Selling method.
     */
    private int selling_method;
    /**
     * The Price decreased.
     */
    private boolean price_decreased;
    /**
     * The Reference price.
     */
    private String reference_price;
    /**
     * The Min bunch amount.
     */
    private double min_bunch_amount;
    /**
     * The Reference format.
     */
    private String reference_format;
    /**
     * The Increment bunch amount.
     */
    private double increment_bunch_amount;
}
