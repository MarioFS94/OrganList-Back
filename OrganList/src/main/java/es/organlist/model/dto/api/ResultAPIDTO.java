package es.organlist.model.dto.api;

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
public class ResultAPIDTO {

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
