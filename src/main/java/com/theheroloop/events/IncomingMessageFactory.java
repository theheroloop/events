package com.theheroloop.events;

import java.util.Collections;
import java.util.Map;

import com.theheroloop.events.json.JsonDeserializer;

/**
 * Overridable class for creating IncomingMessage instances.
 */
public class IncomingMessageFactory
{

  public IncomingMessage create
    ( String topicName, long offset, String key, String payload )
  {
    if( !Topic.isValid(topicName) )
      return invalidIncomingMessage( null, offset, key, payload );

    var topic = Topic.for_( topicName );

    return create( topic, offset, key, payload );
  }

  public IncomingMessage create
    ( Topic topic, long offset, String key, String payload )
  {
    try
    {
      var fields = JsonDeserializer.extract( payload );
 
      return create( topic, offset, key, payload, fields );
    }
    catch( Exception e )
      { return invalidIncomingMessage( topic, offset, key, payload ); }
  }

  public IncomingMessage invalidIncomingMessage
    ( Topic topic, long offset, String key, String payload )
  {
    return new IncomingMessage(
      topic, offset, key, payload, Collections.emptyMap()
    );
  }

  /////////////////////////////////////////////////////////////////////////////
  // Overridable API

  protected IncomingMessage create
  (
    Topic topic, long offset, String key, String payload,
    Map<String, Object> fields
  )
  {
    return new IncomingMessage( topic, offset, key, payload, fields );
  }

}
