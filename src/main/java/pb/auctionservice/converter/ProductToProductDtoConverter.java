package pb.auctionservice.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import pb.auctionservice.models.dto.ProductDto;
import pb.auctionservice.models.entity.Product;

@Component
public class ProductToProductDtoConverter implements Converter<Product, ProductDto> {

    @Override
    public ProductDto convert(@NonNull Product product) {
        var productDto = ProductDto.builder().build();
        BeanUtils.copyProperties(product, productDto);
        return productDto;
    }
}
