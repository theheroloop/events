package com.theheroloop.events;

import static com.theheroloop.events.JsonEscape.q;
import static com.theheroloop.events.Topic.*;
import static com.theheroloop.events.EventType.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class IncomingMessageTest
{

  @Test
  public void testChatMessageSentEvent()
  {
    var message = IncomingMessage.for_( THL_EVENTS, q(
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

  @Test
  public void testHelpOfferedEvent()
  {
    var message = IncomingMessage.for_( THL_EVENTS, q(
      "{ 'eventType': 'help-offered', 'looperId': 'L1', 'heroId': 'H1', 'requestId': 'R1' }"
    ));

    assertEquals( 0, message.offset() );
    assertNull( message.key() );
    assertTrue( message.isValid() );
    assertEquals( Topic.THL_EVENTS, message.topic() );
    assertEquals( HELP_OFFERED, message.eventType() );
    assertEquals( "L1", message.looperId() );
    assertEquals( "H1", message.heroId() );
    assertEquals( "R1", message.requestId() );
  }

  @Test
  public void testHelpOfferCanceledEvent()
  {
    var message = IncomingMessage.for_( THL_EVENTS, q(
      "{ 'eventType': 'help-offer-canceled', 'looperId': 'L1', 'heroId': 'H1', 'requestId': 'R1' }"
    ));

    assertEquals( 0, message.offset() );
    assertNull( message.key() );
    assertTrue( message.isValid() );
    assertEquals( Topic.THL_EVENTS, message.topic() );
    assertEquals( HELP_OFFER_CANCELED, message.eventType() );
    assertEquals( "L1", message.looperId() );
    assertEquals( "H1", message.heroId() );
    assertEquals( "R1", message.requestId() );
  }

  @Test
  public void testHelpAccepted()
  {
    var message = IncomingMessage.for_( THL_EVENTS, q(
      "{ 'eventType': 'help-accepted', 'looperId': 'L1', 'heroId': 'H1', 'requestId': 'R1' }"
    ));

    assertEquals( 0, message.offset() );
    assertNull( message.key() );
    assertTrue( message.isValid() );
    assertEquals( Topic.THL_EVENTS, message.topic() );
    assertEquals( HELP_ACCEPTED, message.eventType() );
    assertEquals( "L1", message.looperId() );
    assertEquals( "H1", message.heroId() );
    assertEquals( "R1", message.requestId() );
  }

  @Test
  public void testHelpRejectedEvent()
  {
    var message = IncomingMessage.for_( THL_EVENTS, q(
      "{ 'eventType': 'help-rejected', 'looperId': 'L1', 'heroId': 'H1', 'requestId': 'R1' }"
    ));

    assertEquals( 0, message.offset() );
    assertNull( message.key() );
    assertTrue( message.isValid() );
    assertEquals( Topic.THL_EVENTS, message.topic() );
    assertEquals( HELP_REJECTED, message.eventType() );
    assertEquals( "L1", message.looperId() );
    assertEquals( "H1", message.heroId() );
    assertEquals( "R1", message.requestId() );
  }

  @Test
  public void testRequestCanceledEvent()
  {
    var message = IncomingMessage.for_( THL_EVENTS, q(
      "{ 'eventType': 'request-canceled', 'looperId': 'L1', 'heroId': 'H1', 'requestId': 'R1' }"
    ));

    assertEquals( 0, message.offset() );
    assertNull( message.key() );
    assertTrue( message.isValid() );
    assertEquals( Topic.THL_EVENTS, message.topic() );
    assertEquals( REQUEST_CANCELED, message.eventType() );
    assertEquals( "L1", message.looperId() );
    assertEquals( "H1", message.heroId() );
    assertEquals( "R1", message.requestId() );
  }

  @Test
  public void testRequestFinalizedEvent()
  {
    var message = IncomingMessage.for_( THL_EVENTS, q(
      "{ 'eventType': 'request-finalized', 'looperId': 'L1', 'heroId': 'H1', 'requestId': 'R1' }"
    ));

    assertEquals( 0, message.offset() );
    assertNull( message.key() );
    assertTrue( message.isValid() );
    assertEquals( Topic.THL_EVENTS, message.topic() );
    assertEquals( REQUEST_FINALIZED, message.eventType() );
    assertEquals( "L1", message.looperId() );
    assertEquals( "H1", message.heroId() );
    assertEquals( "R1", message.requestId() );
  }

  @Test
  public void testHeroesAvailableNearLooperEvent()
  {
    var message = IncomingMessage.for_( LOOPER_NOTIFICATIONS, q(
      "{ 'eventType': 'heroes-available-near-looper', 'looperId': 'L1' }"
    ));

    assertEquals( 0, message.offset() );
    assertNull( message.key() );
    assertTrue( message.isValid() );
    assertEquals( Topic.LOOPER_NOTIFICATIONS, message.topic() );
    assertEquals( HEROES_AVAILABLE_NEAR_LOOPER, message.eventType() );
    assertEquals( "L1", message.looperId() );
  }

  @Test
  public void testHeroesUnavailableNearLooperEvent()
  {
    var message = IncomingMessage.for_( LOOPER_NOTIFICATIONS, q(
      "{ 'eventType': 'heroes-unavailable-near-looper', 'looperId': 'L1' }"
    ));

    assertEquals( 0, message.offset() );
    assertNull( message.key() );
    assertTrue( message.isValid() );
    assertEquals( Topic.LOOPER_NOTIFICATIONS, message.topic() );
    assertEquals( HEROES_UNAVAILABLE_NEAR_LOOPER, message.eventType() );
    assertEquals( "L1", message.looperId() );
  }

  @Test
  public void testMatchingRequestNearHeroEvent()
  {
    var message = IncomingMessage.for_( HERO_NOTIFICATIONS, q(
      "{ 'eventType': 'matching-request-near-hero' }"
    ));

    assertEquals( 0, message.offset() );
    assertNull( message.key() );
    assertTrue( message.isValid() );
    assertEquals( MATCHING_REQUEST_NEAR_HERO, message.eventType() );
  }

}
