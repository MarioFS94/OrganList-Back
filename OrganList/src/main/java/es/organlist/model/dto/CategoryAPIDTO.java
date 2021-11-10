package es.organlist.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * The type Categories dto.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryAPIDTO {

    /**
     * The Id.
     */
    private int id;
    /**
     * The Name.
     */
    private String name;
    /**
     * The Order.
     */
    private int order;
    /**
     * The Layout.
     */
    private int layout;
    /**
     * The Published.
     */
    private boolean published;
    /**
     * The Categories.
     */
    private List<Object> categories;
    /**
     * The Is extended.
     */
    private boolean is_extended;
}
