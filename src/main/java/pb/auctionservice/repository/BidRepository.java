package pb.auctionservice.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import pb.auctionservice.models.entity.Bid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BidRepository extends R2dbcRepository<Bid, Long> {

    Flux<Bid> findAllByAuctionId(Long auctionId);

    @Query("SELECT * FROM bid WHERE amount = (SELECT MAX(amount) FROM bid WHERE auction_id = :auctionId) AND auction_id = :auctionId")
    Mono<Bid> findBidWithHighestAmountByAuctionId(Long auctionId);

}
