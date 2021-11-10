package es.organlist.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Ternary category apidto.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TernaryCategoryAPIDTO {
    /**
     * The Id.
     */
    private int id;
    /**
     * The Name.
     */
    private String name;
    /**
     * The Level.
     */
    private int level;
    /**
     * The Order.
     */
    private int order;
}
