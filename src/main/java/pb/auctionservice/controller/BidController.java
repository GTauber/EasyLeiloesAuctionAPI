package pb.auctionservice.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pb.auctionservice.service.BidService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auction")
@AllArgsConstructor
@Slf4j
public class BidController {

    private final BidService bidService;

}
