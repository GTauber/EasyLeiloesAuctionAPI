package pb.auctionservice.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import pb.auctionservice.models.entity.Bid;

public interface BidRepository extends R2dbcRepository<Bid, Long> {

}
