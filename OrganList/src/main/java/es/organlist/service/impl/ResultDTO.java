package es.organlist.service.impl;

import es.organlist.model.dto.CategoryAPIDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * The type Result dto.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultDTO {

    /**
     * The Next.
     */
    private Integer next;
    /**
     * The Count.
     */
    private int count;
    /**
     * The Results.
     */
    private List<CategoryAPIDTO> results;
    /**
     * The Previous.
     */
    private Integer previous;
}
