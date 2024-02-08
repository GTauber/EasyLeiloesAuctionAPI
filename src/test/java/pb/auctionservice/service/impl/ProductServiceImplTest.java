package pb.auctionservice.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static pb.auctionservice.fixture.FixtureFactory.validProduct;
import static pb.auctionservice.fixture.FixtureFactory.validProductDto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;
import pb.auctionservice.models.dto.ProductDto;
import pb.auctionservice.models.entity.Product;
import pb.auctionservice.repository.ProductRepository;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ConversionService conversionService;

    @Test
    void createProduct() {
        when(productRepository.save(any())).thenReturn(Mono.just(validProduct()));
        when(conversionService.convert(any(ProductDto.class), eq(Product.class))).thenReturn(validProduct());
        var response = productService.createProduct(validProductDto()).block();
        assertNotNull(response);

    }
}