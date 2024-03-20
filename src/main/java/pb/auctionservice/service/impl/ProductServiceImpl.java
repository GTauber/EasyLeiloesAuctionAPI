package pb.auctionservice.service.impl;

import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Service;
import pb.auctionservice.models.dto.ProductDto;
import pb.auctionservice.models.entity.Product;
import pb.auctionservice.repository.ProductRepository;
import pb.auctionservice.service.ProductService;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ConversionService conversionService;

    @Override
    public Mono<ProductDto> createProduct(ProductDto productDto) {
        log.info("Creating product: [{}]", productDto);
        return productRepository.save(Objects.requireNonNull(conversionService.convert(productDto, Product.class)))
            .map(product -> Objects.requireNonNull(conversionService.convert(product, ProductDto.class)));
    }

    @Override
    public Mono<List<ProductDto>> getProductByUserId(Long userId) {
        log.info("Retrieving products for user: [{}]", userId);
        return productRepository.findAllByUserId(userId)
            .collectList()
            .map(Objects.requireNonNull(this::convertResponse));
    }

    @Override
    public Mono<List<ProductDto>> getAllProducts() {
        return productRepository.findAll()
            .collectList()
            .map(Objects.requireNonNull(this::convertResponse));
    }

    @SuppressWarnings("unchecked")
    private List<ProductDto> convertResponse(List<Product> products) {
        var sourceType = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(Product.class));
        var targetType = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(ProductDto.class));
        return (List<ProductDto>) conversionService.convert(products, sourceType, targetType);
    }

}
