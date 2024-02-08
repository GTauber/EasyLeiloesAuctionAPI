package pb.auctionservice.controller;

import static org.springframework.http.HttpStatus.CREATED;

import jakarta.validation.Valid;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
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
    public Mono<Response<ProductDto>> createProduct(@RequestBody @Valid ProductDto productDto) {
        return productService.createProduct(productDto)
            .map(product -> Response.<ProductDto>builder()
                .status(CREATED)
                .statusCode(CREATED.value())
                .message("Product created successfully")
                .data(Map.of("Product", product))
                .build());
    }

}
