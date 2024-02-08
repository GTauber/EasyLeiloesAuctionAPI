package pb.auctionservice.controller;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static pb.auctionservice.fixture.FixtureFactory.validAuctionDto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pb.auctionservice.models.dto.AuctionDto;
import pb.auctionservice.service.AuctionService;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
class AuctionControllerTest {

    @InjectMocks
    private AuctionController auctionController;

    @Mock
    private AuctionService auctionService;

    @Test
    void createAuction() {
        when(auctionService.createAuction(any(AuctionDto.class))).thenReturn(Mono.just(validAuctionDto()));
        var response = auctionController.createAuction(validAuctionDto()).block();

        assertNotNull(response);
    }
}