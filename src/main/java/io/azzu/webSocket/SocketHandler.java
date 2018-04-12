package io.azzu.webSocket;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
@Scope("singleton")
public class SocketHandler extends TextWebSocketHandler {

    List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    public void dispatchMessage(String message) {
        for(WebSocketSession webSocketSession : sessions) {
            try {
                System.out.print(webSocketSession.getId() + " ");
                webSocketSession.sendMessage(new TextMessage(message));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message)
            throws InterruptedException, IOException {
        System.out.println(message.toString());
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        System.out.println("Session opened: " + session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session,  CloseStatus status) throws Exception {
        sessions.remove(session);
        System.out.println("Session closed: " + session.getId() + " (" + status.toString() + ")");
    }
}
