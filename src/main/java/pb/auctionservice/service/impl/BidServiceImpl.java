package pb.auctionservice.service.impl;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import pb.auctionservice.models.dto.BidDto;
import pb.auctionservice.models.entity.Bid;
import pb.auctionservice.repository.BidRepository;
import pb.auctionservice.service.BidService;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class BidServiceImpl implements BidService {

    private final BidRepository bidRepository;
    private final ConversionService conversionService;

    @Override
    public Mono<BidDto> makeBid(BidDto bidDto) {
        return bidRepository.save(Objects.requireNonNull(conversionService.convert(bidDto, Bid.class)))
            .map(this::convertResponse);
    }

    private BidDto convertResponse(Bid bid) {
        var bidDto = BidDto.builder().build();
        BeanUtils.copyProperties(bid, bidDto);
        return bidDto;
    }
}
