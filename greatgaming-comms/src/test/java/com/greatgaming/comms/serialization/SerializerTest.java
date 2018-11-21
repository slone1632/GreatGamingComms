package com.greatgaming.comms.serialization;

import com.greatgaming.comms.messages.Chat;
import com.greatgaming.comms.messages.LoginResponse;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import static junit.framework.Assert.assertEquals;

public class SerializerTest extends TestCase {
    public SerializerTest( String testName ) {
        super( testName );
    }

    public static Test suite() {
        return new TestSuite( SerializerTest.class );
    }

    public void testRoundTrip() throws Exception {
        Serializer serializer = new Serializer();

        Chat chat = new Chat();
        chat.message = "Hi";

        String json = serializer.serialize(Chat.class, chat);
        Chat result = (Chat)serializer.deserialize(json);

        assertEquals(chat.message, result.message);
    }

    public void testRoundTripWithBool() throws Exception {
        Serializer serializer = new Serializer();

        LoginResponse chat = new LoginResponse();
        chat.succeeded = true;
        chat.gamePort = 89;

        String json = serializer.serialize(LoginResponse.class, chat);
        LoginResponse result = (LoginResponse)serializer.deserialize(json);

        assertEquals(chat.gamePort, result.gamePort);
        assertEquals(chat.succeeded, result.succeeded);
    }
}
