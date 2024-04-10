package pb.auctionservice.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nonnull;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import pb.auctionservice.models.dto.BidDto;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class BidWebSocketHandler implements WebSocketHandler {

    private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
    private final ObjectMapper objectMapper;

    public BidWebSocketHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Nonnull
    @Override
    public Mono<Void> handle(@Nonnull WebSocketSession session) {
        sessions.add(session);
        return session.send(session.receive()
            .map(msg -> handleIncomingMessage(msg, session))
            .onErrorResume(e -> Mono.just(session.textMessage("Error processing your request.")))
            .doFinally(sig -> sessions.remove(session))
        );
    }

    public void broadcastUpdate(BidDto bidDto) {
        for (WebSocketSession session : sessions) {
            try {
                session.send(Mono.just(session.textMessage(objectMapper.writeValueAsString(bidDto)))).subscribe();
            } catch (JsonProcessingException e) {
                log.error(e.getMessage());
            }
        }
    }

    private WebSocketMessage handleIncomingMessage(WebSocketMessage msg, WebSocketSession session) {
        try {
            BidDto bidDto = objectMapper.readValue(msg.getPayloadAsText(), BidDto.class);
            broadcastUpdate(bidDto);
            return session.textMessage("Bid received");
        } catch (JsonProcessingException e) {
            log.error("Error parsing BidDto: [{}]", e.getMessage());
            return session.textMessage("Error processing bid");
        }
    }

}
