package com.theheroloop.events;

import static com.theheroloop.events.EventType.*;
import static com.theheroloop.events.Topic.*;
import static com.theheroloop.events.json.JsonEscape.q;

import static org.junit.Assert.*;

import java.util.List;

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

    assertTrue( message.matchesUserId("L1") );
    assertTrue( message.matchesUserId("H1") );
    assertFalse( message.matchesUserId("Tuna") );
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

    assertTrue( message.matchesUserId("L1") );
    assertTrue( message.matchesUserId("H1") );
    assertFalse( message.matchesUserId("Tuna") );
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

    assertTrue( message.matchesUserId("L1") );
    assertTrue( message.matchesUserId("H1") );
    assertFalse( message.matchesUserId("Tuna") );
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

    assertTrue( message.matchesUserId("L1") );
    assertTrue( message.matchesUserId("H1") );
    assertFalse( message.matchesUserId("Tuna") );
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

    assertTrue( message.matchesUserId("L1") );
    assertTrue( message.matchesUserId("H1") );
    assertFalse( message.matchesUserId("Tuna") );
  }

  @Test
  public void testLooperLocationUpdatedEvent()
  {
    var message = IncomingMessage.for_( THL_EVENTS, q(
      "{ 'eventType': 'looper-location-updated', 'looperId': 'L1', 'location': '10.1,23.3' }"
    ));

    assertEquals( 0, message.offset() );
    assertNull( message.key() );
    assertTrue( message.isValid() );
    assertEquals( Topic.THL_EVENTS, message.topic() );
    assertEquals( LOOPER_LOCATION_UPDATED, message.eventType() );
    assertEquals( "L1", message.looperId() );
    assertEquals( "10.1,23.3", message.location().toString() );

    assertTrue( message.matchesUserId("L1") );
    assertFalse( message.matchesUserId("H1") );
    assertFalse( message.matchesUserId("Tuna") );
  }

  @Test
  public void testInvalidLooperLocationUpdatedEvent()
  {
    var message = IncomingMessage.for_( THL_EVENTS, q(
      "{ 'eventType': 'looper-location-updated' }"
    ));

    assertFalse( message.isValid() );
  }

  @Test
  public void testHeroLocationUpdatedEvent()
  {
    var message = IncomingMessage.for_( THL_EVENTS, q(
      "{ 'eventType': 'hero-location-updated', 'heroId': 'H1', 'location': '10.1,23.3', 'oldLocation': '10.3,22.3' }"
    ));

    assertEquals( 0, message.offset() );
    assertNull( message.key() );
    assertTrue( message.isValid() );
    assertEquals( Topic.THL_EVENTS, message.topic() );
    assertEquals( HERO_LOCATION_UPDATED, message.eventType() );
    assertEquals( "H1", message.heroId() );
    assertEquals( "10.1,23.3", message.location().toString() );
    assertEquals( "10.3,22.3", message.oldLocation().toString() );

    List<Location> locations = message.locations();
    assertEquals( 2, locations.size() );
    assertEquals( "10.1,23.3", locations.get(0).toString() );
    assertEquals( "10.3,22.3", locations.get(1).toString() );

    assertFalse( message.matchesUserId("L1") );
    assertTrue( message.matchesUserId("H1") );
    assertFalse( message.matchesUserId("Tuna") );
  }

  @Test
  public void testHeroLocationUpdatedEventNoOldLocation()
  {
    var message = IncomingMessage.for_( THL_EVENTS, q(
      "{ 'eventType': 'hero-location-updated', 'heroId': 'H1', 'location': '10.1,23.3' }"
    ));

    assertEquals( 0, message.offset() );
    assertNull( message.key() );
    assertTrue( message.isValid() );
    assertEquals( Topic.THL_EVENTS, message.topic() );
    assertEquals( HERO_LOCATION_UPDATED, message.eventType() );
    assertEquals( "10.1,23.3", message.location().toString() );
    assertNull( message.oldLocation() );

    List<Location> locations = message.locations();
    assertEquals( 1, locations.size() );
    assertEquals( "10.1,23.3", locations.get(0).toString() );
  }

  @Test
  public void testHeroLocationUpdatedEventNoNewLocation()
  {
    var message = IncomingMessage.for_( THL_EVENTS, q(
      "{ 'eventType': 'hero-location-updated', 'heroId': 'H1', 'oldLocation': '10.3,22.3' }"
    ));

    assertEquals( 0, message.offset() );
    assertNull( message.key() );
    assertTrue( message.isValid() );
    assertEquals( Topic.THL_EVENTS, message.topic() );
    assertEquals( HERO_LOCATION_UPDATED, message.eventType() );
    assertNull( message.location() );
    assertEquals( "10.3,22.3", message.oldLocation().toString() );

    List<Location> locations = message.locations();
    assertEquals( 1, locations.size() );
    assertEquals( "10.3,22.3", locations.get(0).toString() );
  }

  @Test
  public void testInvalidHeroLocationUpdatedEvent()
  {
    var message = IncomingMessage.for_( THL_EVENTS, q(
      "{ 'eventType': 'hero-location-updated' }"
    ));

    assertFalse( message.isValid() );
  }

  @Test
  public void testHeroAvailabilityUpdatedEvent()
  {
    var message = IncomingMessage.for_( THL_EVENTS, q(
      "{ 'eventType': 'hero-availability-updated', 'heroId': 'H1', 'available': true }"
    ));

    assertEquals( 0, message.offset() );
    assertNull( message.key() );
    assertTrue( message.isValid() );
    assertEquals( Topic.THL_EVENTS, message.topic() );
    assertEquals( HERO_AVAILABILITY_UPDATED, message.eventType() );
    assertEquals( "H1", message.heroId() );
    assertTrue( message.available() );
  }

  @Test
  public void testInvalidHeroAvailabilityUpdatedEvent()
  {
    var message = IncomingMessage.for_( THL_EVENTS, q(
      "{ 'eventType': 'hero-availability-updated', 'heroId': 'H1', 'available': 'true' }"
    ));

    assertFalse( message.isValid() );
  }

  @Test
  public void testRequestCreatedEvent()
  {
    var message = IncomingMessage.for_( THL_EVENTS, q(
      "{ 'eventType': 'request-created', 'looperId': 'L1', 'heroId': 'H1', 'requestId': '93' }"
    ));

    assertEquals( 0, message.offset() );
    assertNull( message.key() );
    assertTrue( message.isValid() );
    assertEquals( Topic.THL_EVENTS, message.topic() );
    assertEquals( REQUEST_CREATED, message.eventType() );
    assertEquals( "93", message.requestId() );
    assertEquals( "L1", message.looperId() );
    assertEquals( "H1", message.heroId() );
    assertEquals( "93", message.requestId() );

    assertTrue( message.matchesUserId("L1") );
    assertTrue( message.matchesUserId("H1") );
    assertFalse( message.matchesUserId("Tuna") );
  }

  @Test
  public void testRequestCreatedEventNotValid()
  {
    var message = IncomingMessage.for_( THL_EVENTS, q(
      "{ 'eventType': 'request-created' }"
    ));

    assertFalse( message.isValid() );
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

    assertTrue( message.matchesUserId("L1") );
    assertTrue( message.matchesUserId("H1") );
    assertFalse( message.matchesUserId("Tuna") );
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

    assertTrue( message.matchesUserId("L1") );
    assertTrue( message.matchesUserId("H1") );
    assertFalse( message.matchesUserId("Tuna") );
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

    assertTrue( message.matchesUserId("L1") );
    assertFalse( message.matchesUserId("H1") );
    assertFalse( message.matchesUserId("Tuna") );
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

    assertTrue( message.matchesUserId("L1") );
    assertFalse( message.matchesUserId("H1") );
    assertFalse( message.matchesUserId("Tuna") );
  }

  @Test
  public void testMatchingRequestNearHeroEvent()
  {
    var message = IncomingMessage.for_( HERO_NOTIFICATIONS, q(
      "{ 'eventType': 'matching-request-near-hero', 'heroId': 'H1', 'requestId': '34' }"
    ));

    assertEquals( 0, message.offset() );
    assertNull( message.key() );
    assertTrue( message.isValid() );
    assertEquals( Topic.HERO_NOTIFICATIONS, message.topic() );
    assertEquals( MATCHING_REQUEST_NEAR_HERO, message.eventType() );
    assertEquals( "H1", message.heroId() );
    assertEquals( "34", message.requestId() );

    assertFalse( message.matchesUserId("L1") );
    assertTrue( message.matchesUserId("H1") );
    assertFalse( message.matchesUserId("Tuna") );
  }

  @Test
  public void testRequestNoLongerAvailableEvent()
  {
    var message = IncomingMessage.for_( HERO_NOTIFICATIONS, q(
      "{ 'eventType': 'request-no-longer-available', 'heroId': 'H1', 'requestId': '34' }"
    ));

    assertEquals( 0, message.offset() );
    assertNull( message.key() );
    assertTrue( message.isValid() );
    assertEquals( Topic.HERO_NOTIFICATIONS, message.topic() );
    assertEquals( REQUEST_NO_LONGER_AVAILABLE, message.eventType() );
    assertEquals( "H1", message.heroId() );
    assertEquals( "34", message.requestId() );

    assertFalse( message.matchesUserId("L1") );
    assertTrue( message.matchesUserId("H1") );
    assertFalse( message.matchesUserId("Tuna") );
  }

}
