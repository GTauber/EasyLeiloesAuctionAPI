package pb.auctionservice.config;

import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;

@Configuration
@RequiredArgsConstructor
public class WebSocketConfig {

    private final WebSocketHandler webSocketHandler;

    @Bean
    public HandlerMapping webSocketMapping() {
        var handlerMapping = new HashMap<String, WebSocketHandler>();
        handlerMapping.put("/ws/bids", webSocketHandler);
        var mapping = new SimpleUrlHandlerMapping();
        mapping.setUrlMap(handlerMapping);
        mapping.setOrder(10);
        return mapping;
    }
}
