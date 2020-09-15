package com.theheroloop.events;

import static com.theheroloop.events.MessageField.*;

import java.util.HashMap;
import java.util.Map;

public class MessageBuilder
{

  private final Map<String, Object> _fields = new HashMap<>();

  ////////////////////////////////////////////////////////////////////////////
  // Constructor

  private MessageBuilder( EventType eventType )
    { set( EVENT_TYPE_FIELD.fieldName(), eventType ); }

  ////////////////////////////////////////////////////////////////////////////
  // Static API

  public static MessageBuilder for_( EventType eventType )
    { return new MessageBuilder( eventType ); }

  ////////////////////////////////////////////////////////////////////////////
  // Public API

  public MessageBuilder set( String field, Object value )
  {
    _fields.put( field, value );

    return this;
  }

  public MessageBuilder setLooperId( String looperId )
    { return set( LOOPER_ID_FIELD.fieldName(), looperId ); }

  public MessageBuilder setHeroId( String heroId )
    { return set( HERO_ID_FIELD.fieldName(), heroId ); }

  public MessageBuilder setRequestId( String requestId )
    { return set( REQUEST_ID_FIELD.fieldName(), requestId ); }

  public Message build()
    { return new Message( _fields ); }

}
