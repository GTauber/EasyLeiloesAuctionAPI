package pb.auctionservice.service.impl;

import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import pb.auctionservice.models.dto.AuctionDto;
import pb.auctionservice.models.entity.Auction;
import pb.auctionservice.repository.AuctionRepository;
import pb.auctionservice.service.AuctionService;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuctionServiceImpl implements AuctionService {

    private final AuctionRepository auctionRepository;
    private final ConversionService conversionService;

    @Override
    public Mono<AuctionDto> createAuction(AuctionDto auctionDto, String userUuid) {
        auctionDto.setAuctionOwnerUuid(userUuid);
        log.info("Creating auction: [{}]", auctionDto);
        return auctionRepository.save(Objects.requireNonNull(conversionService.convert(auctionDto, Auction.class)))
            .map(this::convertResponse);
    }

    @Override
    public Mono<List<AuctionDto>> getAllAuctions() {
        return auctionRepository.findAll()
            .map(this::convertResponse)
            .collectList();
    }

    @Override
    public Mono<List<AuctionDto>> getAuctionsByUserUuid(String userUuid) {
        return auctionRepository.findAllByAuctionOwnerUuid(userUuid)
            .map(this::convertResponse)
            .collectList();
    }

    @Override
    public Mono<List<AuctionDto>> getOtherAuctions(String uuid) {
        return auctionRepository.findAllByAuctionOwnerUuidNot(uuid)
            .map(this::convertResponse)
            .collectList();
    }

    private AuctionDto convertResponse(Auction auction) {
        var auctionDto = AuctionDto.builder().build();
        BeanUtils.copyProperties(auction, auctionDto);
        return auctionDto;
    }
}
