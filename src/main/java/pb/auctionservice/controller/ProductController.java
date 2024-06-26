package pb.auctionservice.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pb.auctionservice.config.security.authentication.JWTAuthentication;
import pb.auctionservice.models.dto.ProductDto;
import pb.auctionservice.models.entity.Response;
import pb.auctionservice.service.ProductService;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/product")
@AllArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @PostMapping(consumes = MimeTypeUtils.APPLICATION_JSON_VALUE, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public Mono<Response<ProductDto>> createProduct(@RequestBody @Valid ProductDto productDto, JWTAuthentication authentication) {
        productDto.setUserId(authentication.getUserId());
        return productService.createProduct(productDto)
            .map(product -> Response.<ProductDto>builder()
                .status(CREATED)
                .statusCode(CREATED.value())
                .message("Product created successfully")
                .data(Map.of("Product", product))
                .build());
    }

    @GetMapping(produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public Mono<Response<List<ProductDto>>> getAllProducts(boolean adminRequest, JWTAuthentication authentication) {
        return Mono.just(adminRequest)
            .filter(admin -> admin)
            .flatMap(adm -> productService.getAllProducts())
            .switchIfEmpty(productService.getProductByUserId(authentication.getUserId()))
            .map(products -> Response.<List<ProductDto>>builder()
                .status(OK)
                .statusCode(OK.value())
                .message("Products retrieved successfully")
                .data(Map.of("Products", products))
                .build());
    }

    @GetMapping(path = "/{productId}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public Mono<Response<ProductDto>> getProduct(JWTAuthentication authentication, @PathVariable Long productId) {
        return productService.getProductById(productId, authentication.getUserId())
            .map(product -> Response.<ProductDto>builder()
                .status(OK)
                .statusCode(OK.value())
                .message("Product retrieved successfully")
                .data(Map.of("Product", product))
                .build());
    }

    @PutMapping(consumes = MimeTypeUtils.APPLICATION_JSON_VALUE, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public Mono<Response<ProductDto>> updateProduct(@RequestBody @Valid ProductDto productDto) {
        return productService.updateProduct(productDto)
            .map(product -> Response.<ProductDto>builder()
                .status(OK)
                .statusCode(OK.value())
                .message("Product updated successfully")
                .data(Map.of("Product", product))
                .build());
    }

}
