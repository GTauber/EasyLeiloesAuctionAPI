package pb.auctionservice.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static pb.auctionservice.fixture.FixtureFactory.validProductDto;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import pb.auctionservice.config.security.authentication.JWTAuthentication;
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
        var jwtAuth = new JWTAuthentication("easyAuth", "userName", "exampleToken", 0L, "uuid", List.of(new SimpleGrantedAuthority("USER")));
        var response = productController.createProduct(validProductDto(), jwtAuth).block();
        assertNotNull(response);
    }
}