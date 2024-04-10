package pb.auctionservice.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import pb.auctionservice.models.dto.BidDto;
import pb.auctionservice.models.entity.Bid;

@Component
public class BidToBidDto implements Converter<Bid, BidDto> {

    @Override
    public BidDto convert(@NonNull Bid bid) {
        var bidDto = BidDto.builder().build();
        BeanUtils.copyProperties(bid, bidDto);
        return bidDto;
    }
}
