package es.organlist.model.dto.api;

import lombok.Data;

@Data
public class DetailsAPIDTO {
    private String brand;
    private String origin;
    private String legal_name;
    private String description;
    private String mandatory_mentions;
    private String usage_instructions;
    private String storage_instructions;
}
