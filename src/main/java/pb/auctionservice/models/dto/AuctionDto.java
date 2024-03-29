package pb.auctionservice.models.dto;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.FutureOrPresent;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import pb.auctionservice.enums.AuctionStatus;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuctionDto {

    private Long id;
    private Long productId;
    @JsonProperty(access = READ_ONLY)
    private String auctionOwnerUuid;
    @FutureOrPresent(message = "Date should be today or past today")
    private LocalDateTime auctionStartDate;
    private LocalDateTime auctionEndDate;
    private AuctionStatus auctionStatus;
    private Double initialPrice;

}
