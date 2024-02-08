package pb.auctionservice.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static pb.auctionservice.fixture.FixtureFactory.validProductDto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pb.auctionservice.service.ProductService;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @Test
    void createProduct() {
        when(productService.createProduct(any())).thenReturn(Mono.just(validProductDto()));
        var response = productController.createProduct(validProductDto()).block();
        assertNotNull(response);
    }
}