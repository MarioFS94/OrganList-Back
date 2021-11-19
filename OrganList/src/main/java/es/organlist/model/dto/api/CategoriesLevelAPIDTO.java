package es.organlist.model.dto.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoriesLevelAPIDTO {

    public List<ProductCategoryAPIDTO> category_level_0;
    /*public List<String> category_level_1;
    public List<String> category_level_2;*/
}
