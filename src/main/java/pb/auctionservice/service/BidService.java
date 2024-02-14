package pb.auctionservice.service;

import pb.auctionservice.models.dto.BidDto;
import reactor.core.publisher.Mono;

public interface BidService {

    Mono<BidDto> makeBid(BidDto bidDto);

}
