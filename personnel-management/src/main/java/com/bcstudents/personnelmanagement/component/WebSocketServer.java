package com.bcstudents.personnelmanagement.component;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;



@ServerEndpoint(value = "/imserver/username")
@Component
public class WebSocketServer {
    private static final Logger log = LoggerFactory.getLogger(WebSocketServer.class);
    /**
     * Record following logger
     */
    public static final Map<String, Session> sessionMap = new ConcurrentHashMap<>();
    /**
     * Method called when connection is established successfully
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) {
        sessionMap.put(username, session);
        log.info("NewUserJoined，username={}, TheCurrentNumberOfPeopleOnline：{}", username, sessionMap.size());
        JSONObject result = new JSONObject();
        JSONArray array = new JSONArray();
        result.set("users", array);
        for (Object key : sessionMap.keySet()) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.set("username", key);
            // {"username", "zhang", "username": "admin"}
            array.add(jsonObject);
        }
//        {"users": [{"username": "zhang"},{ "username": "admin"}]}
        sendAllMessage(JSONUtil.toJsonStr(result));  // Send messages to all clients in the backend
    }
    /**
     * Method called on connection close
     */
    @OnClose
    public void onClose(Session session, @PathParam("username") String username) {
        sessionMap.remove(username);
        log.info("OnesConnectionIsDisconnected，remove username={} user session, CurrentOnlineNumber：{}", username, sessionMap.size());
    }
    /**
     * Method called after receiving client message
     * The backend receives the message sent by the client
     * onMessage is a message relay station
     * Accept the json data sent by socket.send on the browser side
     * @param message Message sent by client
     */
    @OnMessage
    public void onMessage(String message, Session session, @PathParam("username") String username) {
        log.info("ServerReceiveUser username={}Message:{}", username, message);
        JSONObject obj = JSONUtil.parseObj(message);
        String toUsername = obj.getStr("to"); // to: MessageSendTo，example: admin
        String text = obj.getStr("text"); // Message Text:  hello
        // {"to": "admin", "text": "hello"}
        Session toSession = sessionMap.get(toUsername); // Get the session based on the "to" username, and then send the message text through the session
        if (toSession != null) {
            // The server side assembles the message again. The assembled message contains the sender and the text content sent.
            // {"from": "zhang", "text": "hello"}
            JSONObject jsonObject = new JSONObject();
            jsonObject.set("from", username);  // from  "zhang"
            jsonObject.set("text", text);  // text same text from above
            this.sendMessage(jsonObject.toString(), toSession);
            log.info("SendToUser username={}，Message：{}", toUsername, jsonObject.toString());
        } else {
            log.info("FailToSend，UsernameUnfounded username={}的session", toUsername);
        }
    }
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("Error");
        error.printStackTrace();
    }
    /**
     * Server Send Message To Client
     */
    private void sendMessage(String message, Session toSession) {
        try {
            log.info("ServerToClient[{}]SendMessage{}", toSession.getId(), message);
            toSession.getBasicRemote().sendText(message);
        } catch (Exception e) {
            log.error("ServerSendMessageToClientFailed", e);
        }
    }
    /**
     * Server Send All Message To Client
     */
    private void sendAllMessage(String message) {
        try {
            for (Session session : sessionMap.values()) {
                log.info("ServerToClient[{}]SendMessage{}", session.getId(), message);
                session.getBasicRemote().sendText(message);
            }
        } catch (Exception e) {
            log.error("ServerSendMessageToClientFailed", e);
        }
    }
}
