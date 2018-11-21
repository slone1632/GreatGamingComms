package com.greatgaming.comms.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.greatgaming.comms.messages.*;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Serializer {
    private static final String MAGIC_SPLITTER = "~%";
    private static Map<Integer, Type> messageIdToType;
    private static Map<Class, Integer> messageClassToId;
    static {
        LinkedList<Class> messages = new LinkedList<>();
         messages.add(Chat.class);
         messages.add(DisconnectRequest.class);
         messages.add(DisconnectResponse.class);
         messages.add(HeartbeatAcknowledge.class);
         messages.add(HeartbeatRequest.class);
         messages.add(LoginRequest.class);
         messages.add(LoginResponse.class);

        messageClassToId = new HashMap<>();
        messageIdToType = new HashMap<>();
         Integer count = 0;
         for (Class c : messages) {
             messageIdToType.put(count, c);
             messageClassToId.put(c, count++);
         }
    }
    private Gson gson;
    private boolean isInitialized;

    private void initialize() {
        GsonBuilder builder = new GsonBuilder();
        gson = builder.create();
        isInitialized = true;
    }

    public Object deserialize(String message) {
        if (!isInitialized) {
            initialize();
        }
        String[] split = message.split(MAGIC_SPLITTER);
        if (split.length != 2) {
            return null;
        }
        Integer messageCode = Integer.valueOf(split[0]);
        Type clazz = messageIdToType.get(messageCode);
        return gson.fromJson(split[1], clazz);
    }

    public <T> String serialize(Class<T> clazz, Object message) {
        if (!isInitialized) {
            initialize();
        }
        StringBuilder builder = new StringBuilder();
        builder.append(messageClassToId.get(clazz).toString());
        builder.append(MAGIC_SPLITTER);
        String payload = gson.toJson(message);
        builder.append(payload);
        return builder.toString();
    }
}
