package pb.auctionservice.service;

import java.util.List;
import pb.auctionservice.models.dto.ProductDto;
import reactor.core.publisher.Mono;

public interface ProductService {

    Mono<ProductDto> createProduct(ProductDto productDto);

    Mono<List<ProductDto>> getProductByUserId(Long userId);

    Mono<List<ProductDto>> getAllProducts();

    Mono<List<ProductDto>> getAllProductsDifferentThanUserUuid(Long userId);


    Mono<ProductDto> getProductById(Long productId, Long currentUserId);

    Mono<ProductDto> updateProduct(ProductDto productDto);
}
