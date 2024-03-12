package pb.auctionservice.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import pb.auctionservice.models.dto.BidDto;
import pb.auctionservice.models.entity.Bid;

@Component
public class BidDtoToBidConverter implements Converter<BidDto, Bid> {

    @Override
    public Bid convert(@NonNull BidDto bidDto) {
        var bid = Bid.builder()
            .build();
        BeanUtils.copyProperties(bidDto, bid);
        return bid;
    }
}
