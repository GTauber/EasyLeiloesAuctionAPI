package pb.auctionservice.models.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BidDto {

    private Long id;
    private Long auctionId;
    private String bidderUuid;
    private Double amount;

}
