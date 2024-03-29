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
import pb.auctionservice.config.security.authentication.JWTAuthentication;
import pb.auctionservice.models.dto.AuctionDto;
import pb.auctionservice.models.entity.Response;
import pb.auctionservice.service.AuctionService;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auction")
@AllArgsConstructor
@Slf4j
public class AuctionController {

    private final AuctionService auctionService;

    @PostMapping(consumes = MimeTypeUtils.APPLICATION_JSON_VALUE, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public Mono<Response<AuctionDto>> createAuction(@RequestBody @Valid AuctionDto auctionDto, JWTAuthentication jwtAuth) {
        return auctionService.createAuction(auctionDto, jwtAuth.getUserUuid())
            .map(auction -> Response.<AuctionDto>builder()
                .status(CREATED)
                .statusCode(CREATED.value())
                .message("Auction created successfully")
                .data(Map.of("Auction", auction))
                .build());
    }

    @GetMapping(path = "/{userUuid}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public Mono<Response<List<AuctionDto>>> getAuctionsByUuid(JWTAuthentication jwtAuth, @PathVariable String userUuid) {
        return auctionService.getAuctionsByUserUuid(userUuid == null ? jwtAuth.getUserUuid() : userUuid)
            .map(auctionDtoList -> Response.<List<AuctionDto>>builder()
                .status(CREATED)
                .statusCode(CREATED.value())
                .message("Auctions retrieved successfully")
                .data(Map.of("Auctions", auctionDtoList))
                .build()
            );
    }

    @GetMapping(path = "/other/{uuid}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public Mono<Response<List<AuctionDto>>> getOtherAuctionsDifferentThanUuid(@PathVariable String uuid) {
        return auctionService.getOtherAuctions(uuid)
            .map(auctionDtoList -> Response.<List<AuctionDto>>builder()
                .status(CREATED)
                .statusCode(CREATED.value())
                .message("Auctions retrieved successfully")
                .data(Map.of("Auctions", auctionDtoList))
                .build()
            );
    }

    @GetMapping(produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public Mono<Response<List<AuctionDto>>> findAllAuctions() {
        return auctionService.getAllAuctions()
            .map(auctionDtoList -> Response.<List<AuctionDto>>builder()
                .status(CREATED)
                .statusCode(CREATED.value())
                .message("Auctions retrieved successfully")
                .data(Map.of("Auctions", auctionDtoList))
                .build()
            );
    }
}
