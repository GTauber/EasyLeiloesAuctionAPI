package pb.auctionservice.service;

import java.util.List;
import pb.auctionservice.models.dto.BidDto;
import reactor.core.publisher.Mono;

public interface BidService {

    Mono<BidDto> makeBid(BidDto bidDto);

    Mono<List<BidDto>> getBidsByAuctionId(Long auctionId);
}
