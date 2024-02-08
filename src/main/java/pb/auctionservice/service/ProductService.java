package pb.auctionservice.service;

import pb.auctionservice.models.dto.ProductDto;
import reactor.core.publisher.Mono;

public interface ProductService {

    Mono<ProductDto> createProduct(ProductDto productDto);


}
