package pb.auctionservice.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import pb.auctionservice.models.entity.Auction;

public interface AuctionRepository extends R2dbcRepository<Auction, Long> {

}
