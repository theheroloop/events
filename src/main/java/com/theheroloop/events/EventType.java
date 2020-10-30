package com.theheroloop.events;

import static com.theheroloop.events.MessageField.*;
import static com.theheroloop.events.Topic.*;
import static com.theheroloop.events.validation.FieldValidation.*;

import com.theheroloop.events.validation.FieldValidation;

/**
 * Supported Kafka event types.
 */
public enum EventType
{

  /**
   * may be discarded in the future in favor of a third-party chat solution
   */
  CHAT_MESSAGE_SENT( THL_EVENTS,
    requires( LOOPER_ID_FIELD  ),
    requires( HERO_ID_FIELD    ),
    requires( REQUEST_ID_FIELD ),
    requires( CHAT_MESSAGE_FIELD )
  ),

  HELP_OFFERED( THL_EVENTS,
    requires( LOOPER_ID_FIELD  ),
    requires( HERO_ID_FIELD    ),
    requires( REQUEST_ID_FIELD )
  ),

  HELP_OFFER_CANCELED( THL_EVENTS,
    requires( LOOPER_ID_FIELD  ),
    requires( HERO_ID_FIELD    ),
    requires( REQUEST_ID_FIELD )
  ),

  HELP_ACCEPTED( THL_EVENTS,
    requires( LOOPER_ID_FIELD  ),
    requires( HERO_ID_FIELD    ),
    requires( REQUEST_ID_FIELD )
  ),

  HELP_REJECTED( THL_EVENTS,
    requires( LOOPER_ID_FIELD  ),
    requires( HERO_ID_FIELD    ),
    requires( REQUEST_ID_FIELD )
  ),

  HERO_LOCATION_UPDATED( THL_EVENTS,
    requires( HERO_ID_FIELD ),
    requiresEither( LOCATION_FIELD, OLD_LOCATION_FIELD )
  ),

  LOOPER_LOCATION_UPDATED( THL_EVENTS,
    requires( LOOPER_ID_FIELD ),
    requires( LOCATION_FIELD  ) // TODO: check if can be unset
  ),

  REQUEST_CREATED( THL_EVENTS,
    requires( LOOPER_ID_FIELD ),
    requires( REQUEST_ID_FIELD )
  ),

  REQUEST_CANCELED( THL_EVENTS,
    requires( LOOPER_ID_FIELD ),
    requires( REQUEST_ID_FIELD )
  ),

  REQUEST_FINALIZED( THL_EVENTS,
    requires( LOOPER_ID_FIELD  ),
    requires( HERO_ID_FIELD    ),
    requires( REQUEST_ID_FIELD )
  ),

  HEROES_AVAILABLE_NEAR_LOOPER( LOOPER_NOTIFICATIONS,
    requires( LOOPER_ID_FIELD  )
  ),
  HEROES_UNAVAILABLE_NEAR_LOOPER( LOOPER_NOTIFICATIONS,
    requires( LOOPER_ID_FIELD  )
  ),

  MATCHING_REQUEST_NEAR_HERO( HERO_NOTIFICATIONS,
    requires( HERO_ID_FIELD ),
    requires( REQUEST_ID_FIELD )
  ),
  REQUEST_NO_LONGER_AVAILABLE( HERO_NOTIFICATIONS,
    requires( HERO_ID_FIELD ),
    requires( REQUEST_ID_FIELD )
  );

  ////////////////////////////////////////////////////////////////////////////
  // Static API

  public static boolean isValid( String eventName )
  {
    for( var eventType : values() )
      if( eventType.eventName().equals( eventName ) )
        return true;

    return false;
  }

  public static EventType for_( String eventName )
  {
    for( var eventType : values() )
      if( eventType.eventName().equals( eventName ) )
        return eventType;

    throw new IllegalArgumentException( eventName );
  }

  ////////////////////////////////////////////////////////////////////////////
  // Instance

  private Topic _topic;
  private String _name;
  private FieldValidation[] _fieldValidations;

  private EventType( Topic topic, FieldValidation... fieldValidations )
  {
    _topic = topic;
    _fieldValidations = fieldValidations;

    _name = name().toLowerCase().replaceAll( "_", "-" );
  }

  ////////////////////////////////////////////////////////////////////////////
  // Public API

  public String eventName()
    { return _name; }

  public Topic topic()
    { return _topic; }

  public boolean isMessageValidForThisType( Message message )
  {
    if( message.eventType() != this )
      return false;

    for( var validation : _fieldValidations )
      if( ! validation.check( message ) )
        return false;

    return true;
  }

  @Override
  public String toString()
    { return _name; }

}
