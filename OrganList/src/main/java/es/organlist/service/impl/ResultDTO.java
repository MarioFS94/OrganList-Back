package es.organlist.service.impl;

import es.organlist.model.dto.CategoryAPIDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultDTO {

    public Integer next;
    public int count;
    public List<CategoryAPIDTO> results;
    public Integer previous;
}
