package pb.auctionservice.service;

import pb.auctionservice.models.dto.AuctionDto;
import reactor.core.publisher.Mono;

public interface AuctionService {

    Mono<AuctionDto> createAuction(AuctionDto auctionDto);
}
