package com.theheroloop.events;

import static com.theheroloop.events.EventType.HEROES_AVAILABLE_NEAR_LOOPER;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MessageBuilderTest
{

  @Test
  public void testMessageBuild()
  {
    var message = MessageBuilder.for_( HEROES_AVAILABLE_NEAR_LOOPER )
    . setLooperId( "1023" )
    . build();

    assertEquals( HEROES_AVAILABLE_NEAR_LOOPER, message.eventType() );
    assertEquals( "1023", message.looperId() );
  }

}
