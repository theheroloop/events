package com.theheroloop.events;

import static com.theheroloop.events.EventType.HEROES_AVAILABLE_NEAR_LOOPER;
import static com.theheroloop.events.EventType.REQUEST_CANCELED;
import static com.theheroloop.events.MessageField.*;

import static org.junit.Assert.*;

import com.theheroloop.events.json.JsonDeserializer;

import org.junit.Test;

public class MessageTest
{

  @Test
  public void testMessageToJson()
  {
    var message = MessageBuilder.for_( HEROES_AVAILABLE_NEAR_LOOPER )
    . setLooperId( "1023" )
    . setHeroId( "H1" )
    . setRequestId( "23" )
    . build();

    var json = message.toJson();

    var map = JsonDeserializer.extract( json );

    assertNotNull( map );
    assertEquals( "1023", map.get( LOOPER_ID_FIELD.fieldName() ) );
    assertEquals( "H1", map.get( HERO_ID_FIELD.fieldName() ) );
    assertEquals( "23", map.get( REQUEST_ID_FIELD.fieldName() ) );
  }

  @Test
  public void testIsValidRequestCanceledMessageWithJustRequiredFields()
  {
    var message = MessageBuilder.for_( REQUEST_CANCELED )
    . setLooperId( "1023" )
    . setRequestId( "23" )
    . build();

    var valid = message.isValid();

    assertTrue( valid );
  }

  @Test
  public void testIsValidRequestCanceledMessageWithAdditionalHeroIdField()
  {
    var message = MessageBuilder.for_( REQUEST_CANCELED )
    . setHeroId( "33" )
    . setLooperId( "1023" )
    . setRequestId( "23" )
    . build();

    var valid = message.isValid();

    assertTrue( valid );
  }

}
