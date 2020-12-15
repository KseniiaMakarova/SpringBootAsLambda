package com.lambda.boot.controller;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import com.amazonaws.services.apigatewaymanagementapi.AmazonApiGatewayManagementApi;
import com.amazonaws.services.apigatewaymanagementapi.model.PostToConnectionRequest;
import com.lambda.boot.model.Chat;
import com.lambda.boot.repository.ConnectionIdRepository;
import com.lambda.boot.service.ChatService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {
    private static final Logger LOGGER = LogManager.getLogger(ChatController.class);
    @Autowired
    private AmazonApiGatewayManagementApi client;
    @Autowired
    private ChatService chatService;
    @Autowired
    private ConnectionIdRepository connectionIdRepository;

    @RequestMapping(path = "/chat", method = RequestMethod.POST)
    public String createChat(HttpServletRequest request) {
        chatService.createNewChat();
        return "new chat was created!";
    }

    @RequestMapping(path = "/ws", method = RequestMethod.POST)
    public String wsConnection(@RequestBody Map<String,Object> body) {
        String connectionId = (String)body.get("connectionId");
        connectionIdRepository.put("1", connectionId);
        LOGGER.info("BODY: " + body);
        return "{\"connectionId\": \"" + connectionId + "\", \"body\": {\"message\": \"all is fine\"}}";
    }

    @RequestMapping(path = "/chat", method = RequestMethod.GET)
    public List<Chat> getChats() {
        return chatService.getAll();
    }

    @RequestMapping(path = "/send", method = RequestMethod.GET)
    public String sendMessage() {
        PostToConnectionRequest request = new PostToConnectionRequest();
        String connectionId = connectionIdRepository.getByKey("1");
        request.withConnectionId(connectionId);
        LOGGER.info("CONNECTION ID: "+ connectionId);
        String message = String.format("{\n" +
            "  \"event\": \"message\",\n" +
            "  \"data\": [\n" +
            "    {\n" +
            "      \"id\": \"1\",\n" +
            "      \"text\": \"hello from server %d\"\n" +
            "    }" +
            "  ]\n" +
            "}", new Random().nextInt());
        ByteBuffer messageResponse = mapStringToByteBuffer(message);
        request.withData(messageResponse);
        client.postToConnection(request);
        return "done - 200 OK with connection id - " + connectionId;
    }

    private ByteBuffer mapStringToByteBuffer(String message) {
      Charset charset = Charset.forName("UTF-8");
      CharsetEncoder encoder = charset.newEncoder();
      ByteBuffer buffer = null;
      try {
        buffer = encoder.encode(CharBuffer.wrap(message));
      } catch (CharacterCodingException e) {
        LOGGER.error("Error while encoding", e);
      }
      return buffer;
    }
}
