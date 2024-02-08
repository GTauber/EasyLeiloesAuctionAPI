package pb.auctionservice.service.impl;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.ConversionService;
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
            .map(this::convertResponse);
    }

    private ProductDto convertResponse(Product product) {
        var productDto = ProductDto.builder().build();
        BeanUtils.copyProperties(product, productDto);
        return productDto;
    }
}
