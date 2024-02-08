package pb.auctionservice.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import pb.auctionservice.models.dto.ProductDto;
import pb.auctionservice.models.entity.Product;

@Component
public class ProductDtoToProductConverter implements Converter<ProductDto, Product> {

    @Override
    public Product convert(@NonNull ProductDto productDto) {
        var product = new Product();
        BeanUtils.copyProperties(productDto, product);
        return product;
    }
}
