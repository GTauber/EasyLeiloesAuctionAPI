package pb.auctionservice.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static pb.auctionservice.fixture.FixtureFactory.validBidDto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pb.auctionservice.models.dto.BidDto;
import pb.auctionservice.service.BidService;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
class BidControllerTest {

    @InjectMocks
    private BidController bidController;

    @Mock
    private BidService bidService;

    @Test
    void makeBid() {
        when(bidService.makeBid(any(BidDto.class))).thenReturn(Mono.just(validBidDto()));
        var response = bidController.makeBid(validBidDto()).block();

        assertNotNull(response);
    }


}