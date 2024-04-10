package pb.auctionservice.service.impl;

import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
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

    @Override
    public Mono<List<BidDto>> getBidsByAuctionId(Long auctionId) {
        return bidRepository.findAllByAuctionId(auctionId)
            .collectList()
            .map(this::convertResponse);
    }

    private BidDto convertResponse(Bid bid) {
        var bidDto = BidDto.builder().build();
        BeanUtils.copyProperties(bid, bidDto);
        return bidDto;
    }

    @SuppressWarnings("unchecked")
    private List<BidDto> convertResponse(List<Bid> bids) {
        var sourceType = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(Bid.class));
        var targetType = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(BidDto.class));
        return (List<BidDto>) conversionService.convert(bids, sourceType, targetType);
    }
}
