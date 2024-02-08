package pb.auctionservice.models.dto;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonInclude(NON_NULL)
public class ProductDto {

    private Long id;
    private String name;
    private String description;
    private String model;
    private String specifications;
    private String imageUrl;

}
