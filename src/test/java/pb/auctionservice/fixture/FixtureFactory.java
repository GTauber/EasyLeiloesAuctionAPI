package pb.auctionservice.fixture;

import java.time.LocalDateTime;
import java.util.List;
import pb.auctionservice.enums.AuctionStatus;
import pb.auctionservice.models.dto.AuctionDto;
import pb.auctionservice.models.dto.BidDto;
import pb.auctionservice.models.dto.ProductDto;
import pb.auctionservice.models.entity.Auction;
import pb.auctionservice.models.entity.Bid;
import pb.auctionservice.models.entity.Product;

public class FixtureFactory {

    public static Product validProduct() {
        return Product.builder()
            .id(1L)
            .uuid("0000")
            .name("Product 1")
            .description("Description 1")
            .model("Model 1")
            .specifications("Specifications 1")
            .imageUrl("http://localhost:8080/image1")
            .createdAt(LocalDateTime.parse("2021-12-01T00:00:00"))
            .updatedAt(LocalDateTime.parse("2021-12-01T00:00:00"))
            .build();
    }

    public static Bid validBid() {
        return Bid.builder()
            .id(1L)
            .uuid("0000")
            .auctionId(1L)
            .bidderUuid("0001")
            .amount(200.0)
            .createdAt(LocalDateTime.parse("2021-12-01T00:00:00"))
            .updatedAt(LocalDateTime.parse("2021-12-01T00:00:00"))
            .build();
    }

    public static Auction validAuction() {
        return Auction.builder()
            .id(1L)
            .uuid("0000")
            .bidWinnerId(0L)
            .productId(1L)
            .auctionOwnerUuid("0001")
            .auctionStartDate(LocalDateTime.parse("2021-12-01T00:00:00"))
            .auctionEndDate(LocalDateTime.parse("2021-12-31T23:59:59"))
            .auctionStatus(AuctionStatus.ACTIVE)
            .product(validProduct())
            .initialPrice(100.0)
            .createdAt(LocalDateTime.parse("2021-12-01T00:00:00"))
            .updatedAt(LocalDateTime.parse("2021-12-01T00:00:00"))
            .bids(List.of(validBid()))
            .build();
    }

    public static ProductDto validProductDto() {
        return ProductDto.builder()
            .name("Product 1")
            .description("Description 1")
            .model("Model 1")
            .specifications("Specifications 1")
            .imageUrl("http://localhost:8080/image1")
            .build();
    }

    public static AuctionDto validAuctionDto() {
        return AuctionDto.builder()
            .productId(1L)
            .auctionStartDate(LocalDateTime.parse("2021-12-01T00:00:00"))
            .auctionEndDate(LocalDateTime.parse("2021-12-31T23:59:59"))
            .auctionStatus(AuctionStatus.ACTIVE)
            .initialPrice(100.0)
            .build();
    }

    public static BidDto validBidDto() {
        return BidDto.builder()
            .auctionId(1L)
            .bidderUuid("0001")
            .amount(200.0)
            .build();
    }
}
