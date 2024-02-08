package pb.auctionservice.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static pb.auctionservice.fixture.FixtureFactory.validAuction;
import static pb.auctionservice.fixture.FixtureFactory.validAuctionDto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;
import pb.auctionservice.models.dto.AuctionDto;
import pb.auctionservice.models.entity.Auction;
import pb.auctionservice.repository.AuctionRepository;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
class AuctionServiceImplTest {

    @InjectMocks
    private AuctionServiceImpl auctionService;

    @Mock
    private AuctionRepository auctionRepository;

    @Mock
    private ConversionService conversionService;

    @Test
    void createAuction() {
        when(auctionRepository.save(any(Auction.class))).thenReturn(Mono.just(validAuction()));
        when(conversionService.convert(any(AuctionDto.class), eq(Auction.class))).thenReturn(validAuction());

        var response = auctionService.createAuction(validAuctionDto()).block();

        assertNotNull(response);

    }
}