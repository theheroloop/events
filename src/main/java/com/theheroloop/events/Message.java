package com.theheroloop.events;

import static com.theheroloop.events.MessageField.*;

import java.util.*;

import com.theheroloop.events.json.JsonSerializer;

/**
 * Base class for both incoming and outgoing messages.
 */
public class Message
{

  private final Map<String, Object> _fields;

  ////////////////////////////////////////////////////////////////////////////
  // Constructor

  public Message( Map<String, Object> fields )
    { _fields = fields; }

  ////////////////////////////////////////////////////////////////////////////
  // Public API

  public final Map<String, Object> fields()
    { return _fields; }

  /**
   * Checks that the message has a valid event type, and required additional
   * fields are provided.
   */
  public boolean isValid()
  {
    String name = getStringField( EVENT_TYPE_FIELD.fieldName() );

    if( name == null && !EventType.isValid( name ) )
      return false;
 
    return eventType().isMessageValidForThisType( this );
  }

  /**
   * Get the valid event type of this message;
   * First ensure that the message is of a valid event type before invoking
   * this method.
   */
  public final EventType eventType()
  {
    String name = getStringField( EVENT_TYPE_FIELD.fieldName() );

    return EventType.for_( name );
  }

  public final String heroId()
    { return getStringField( HERO_ID_FIELD.fieldName() ); }

  public final String looperId()
    { return getStringField( LOOPER_ID_FIELD.fieldName() ); }

  public final String requestId()
    { return getStringField( REQUEST_ID_FIELD.fieldName() ); }

  public boolean available()
  { return getBooleanField( AVAILABLE_FIELD.fieldName() ); }

  public final Location location()
    { return getLocationField( LOCATION_FIELD.fieldName() ); }

  public final Location oldLocation()
    { return getLocationField( OLD_LOCATION_FIELD.fieldName() ); }

  public final boolean getBooleanField( String fieldName )
    { return ( Boolean )_fields.get( fieldName ); }

  public final String getStringField( String fieldName )
    { return( String )_fields.get( fieldName ); }

  public final Location getLocationField( String fieldName )
  {
    var value = getStringField( fieldName );

    return Location.for_( value );
  }

  public boolean has( MessageField field )
    { return _fields.containsKey( field.fieldName() ); }

  public final Object get( String fieldName )
    { return _fields.get( fieldName ); }

  /**
   * Return an array with the locations (new and old) of this message.
   * An empty array may be returned if both locations are missing or are not
   * valid.
   */
  public final List<Location> locations()
  {
    var location    =    location();
    var oldLocation = oldLocation();

    var locations = new ArrayList<Location>();

    if( location != null )
      locations.add( location );
    if( oldLocation != null )
      locations.add( oldLocation );
    
    return locations;
  }

  public boolean matchesLooper( String looperId )
    { return looperId.equals( looperId() ); }

  public boolean matchesHero( String heroId )
    { return heroId.equals( heroId() ); }

  public String toJson()
    { return JsonSerializer.toJson( _fields ); }

  ////////////////////////////////////////////////////////////////////////////
  // Overriden

  @Override
  public String toString()
    { return _fields.toString(); }

}
