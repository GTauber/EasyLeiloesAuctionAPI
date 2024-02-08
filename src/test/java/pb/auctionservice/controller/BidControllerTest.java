package pb.auctionservice.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pb.auctionservice.service.BidService;

@ExtendWith(MockitoExtension.class)
class BidControllerTest {

    @InjectMocks
    private BidController bidController;

    @Mock
    private BidService bidService;

    @Test
    void makeBid() {

        var response = bidController.makeBid(validBidDto()).block();

        assertNotNull(response);
    }


}