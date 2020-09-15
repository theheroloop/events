package com.theheroloop.events;

import static com.theheroloop.events.JsonEscape.q;

import static com.theheroloop.events.EventType.CHAT_MESSAGE_SENT;

import static org.junit.Assert.*;

import org.junit.Test;

public class IncomingMessageTest
{

  @Test
  public void testChatMessageSentEvent()
  {
    var message = IncomingMessage.for_( Topic.THL_EVENTS, q(
      "{ 'eventType': 'chat-message-sent', 'looperId': 'L1', 'heroId': 'H1', 'requestId': 'R1', 'chatMessage': {} }"
    ));

    assertEquals( 0, message.offset() );
    assertNull( message.key() );
    assertTrue( message.isValid() );
    assertEquals( Topic.THL_EVENTS, message.topic() );
    assertEquals( CHAT_MESSAGE_SENT, message.eventType() );
    assertEquals( "L1", message.looperId() );
    assertEquals( "H1", message.heroId() );
    assertEquals( "R1", message.requestId() );
  }

}
