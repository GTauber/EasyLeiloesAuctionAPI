package pb.auctionservice.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import pb.auctionservice.models.entity.Product;

public interface ProductRepository extends R2dbcRepository<Product, Long> {

}
