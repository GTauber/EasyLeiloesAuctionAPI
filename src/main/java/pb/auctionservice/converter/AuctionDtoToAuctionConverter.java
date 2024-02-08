package pb.auctionservice.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import pb.auctionservice.enums.AuctionStatus;
import pb.auctionservice.models.dto.AuctionDto;
import pb.auctionservice.models.entity.Auction;

@Component
public class AuctionDtoToAuctionConverter implements Converter<AuctionDto, Auction> {

    @Override
    public Auction convert(@NonNull AuctionDto auctionDto) {
        var auction = Auction.builder()
            .auctionStatus(AuctionStatus.ACTIVE)
            .build();
        BeanUtils.copyProperties(auctionDto, auction);
        return auction;
    }
}
