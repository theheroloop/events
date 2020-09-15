package com.theheroloop.events;

import static com.theheroloop.events.Topic.THL_EVENTS;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class IncomingMessageFactoryTest
{

  @Test
  public void testEventWithNoType()
  {
    var factory = new IncomingMessageFactory();

    var message = factory.create( THL_EVENTS, 0, null, "{}" );

    assertFalse( message.isValid() );
  }

  @Test
  public void testNoJsonMessage()
  {
    var factory = new IncomingMessageFactory();

    var message = factory.create( THL_EVENTS, 0, null, "pato" );

    assertFalse( message.isValid() );
  }

}
