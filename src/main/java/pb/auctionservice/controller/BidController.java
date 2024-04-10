package pb.auctionservice.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pb.auctionservice.models.dto.BidDto;
import pb.auctionservice.models.entity.Response;
import pb.auctionservice.service.BidService;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/bid")
@AllArgsConstructor
@Slf4j
public class BidController {

    private final BidService bidService;

    @PostMapping(consumes = MimeTypeUtils.APPLICATION_JSON_VALUE, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public Mono<Response<BidDto>> makeBid(@RequestBody @Valid BidDto bidDto) {
        return bidService.makeBid(bidDto)
            .map(bid -> Response.<BidDto>builder()
                .status(CREATED)
                .statusCode(CREATED.value())
                .message("Bid created successfully")
                .data(Map.of("Bid", bid))
                .build());
    }

    @GetMapping(path = "/auctionId/{auctionId}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public Mono<Response<List<BidDto>>> getBidsByAuctionId(@PathVariable Long auctionId) {
        return bidService.getBidsByAuctionId(auctionId)
            .map(bid -> Response.<List<BidDto>>builder()
                .status(OK)
                .statusCode(OK.value())
                .message("Bids retrieved successfully")
                .data(Map.of("Bids", bid))
                .build());
    }

}
