package pb.auctionservice.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static pb.auctionservice.fixture.FixtureFactory.validBid;
import static pb.auctionservice.fixture.FixtureFactory.validBidDto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;
import pb.auctionservice.models.dto.BidDto;
import pb.auctionservice.models.entity.Bid;
import pb.auctionservice.repository.BidRepository;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
class BidServiceImplTest {

    @InjectMocks
    private BidServiceImpl bidService;

    @Mock
    private BidRepository bidRepository;

    @Mock
    private ConversionService conversionService;

    @Test
    void makeBid() {
        when(bidRepository.save(any())).thenReturn(Mono.just(validBid()));
        when(conversionService.convert(any(BidDto.class), eq(Bid.class))).thenReturn(validBid());

        var response = bidService.makeBid(validBidDto()).block();
        assertNotNull(response);
    }
}