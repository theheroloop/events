package com.theheroloop.events;

import static com.theheroloop.events.MessageFieldType.*;

/**
 * Supported event fields.
 * @see https://docs.google.com/document/d/13m0TToGTwJQUKCE6i9p5K2KKAPno9rew70phk6fx7Us/edit#heading=h.9r38y0ortv0k
 */
public enum MessageField
{

  EVENT_TYPE_FIELD( "eventType", EVENT_TYPE ),

  SERVER_UTC_TIMESTAMP_FIELD( "serverUtcTimestamp", NUMBER_TYPE ),

  HERO_ID_FIELD   ( "heroId"   , STRING_TYPE ),
  LOOPER_ID_FIELD ( "looperId" , STRING_TYPE ),
  REQUEST_ID_FIELD( "requestId", STRING_TYPE ),

  LOCATION_FIELD    ( "location"   , LOCATION_TYPE ),
  OLD_LOCATION_FIELD( "oldLocation", LOCATION_TYPE ),

  CHAT_MESSAGE_FIELD( "chatMessage", CHAT_MESSAGE_TYPE );

  ////////////////////////////////////////////////////////////////////////////
  // Instance

  private final String _name;
  private final MessageFieldType _fieldType;

  private MessageField( String name, MessageFieldType fieldType )
  {
    _name = name;
    _fieldType = fieldType;
  }

  ////////////////////////////////////////////////////////////////////////////
  // Public API

  public String fieldName()
    { return _name; }

  public MessageFieldType fieldType()
    { return _fieldType; }

  @Override
  public String toString()
    { return _name; }

  ////////////////////////////////////////////////////////////////////////////
  // Public API
  
  public boolean validateRequiredFieldOnMessage( Message message )
  {
    var value = message.get( _name );

    if( value == null )
      return false;

    return _fieldType.validateValue( value );
  }

}
