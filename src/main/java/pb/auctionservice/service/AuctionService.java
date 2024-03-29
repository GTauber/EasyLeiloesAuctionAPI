package pb.auctionservice.service;

import java.util.List;
import pb.auctionservice.models.dto.AuctionDto;
import reactor.core.publisher.Mono;

public interface AuctionService {

    Mono<AuctionDto> createAuction(AuctionDto auctionDto, String userUuid);

    Mono<List<AuctionDto>> getAllAuctions();

    Mono<List<AuctionDto>> getAuctionsByUserUuid(String userUuid);

    Mono<List<AuctionDto>> getOtherAuctions(String uuid);
}
