package es.organlist.model.dto.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Badges apidto.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BadgesAPIDTO {
    /**
     * The Is water.
     */
    private boolean is_water;
    /**
     * The Requires age check.
     */
    private boolean requires_age_check;
}
