package com.theheroloop.events;

public enum MessageFieldType
{

  EVENT_TYPE,

  NUMBER_TYPE
  {
    @Override
    public boolean validateValue( Object value )
      { return value != null && value instanceof Number; }
  },

  STRING_TYPE
  {
    @Override
    public boolean validateValue( Object value )
      { return value != null && value instanceof String; }
  },

  LOCATION_TYPE
  {
    @Override
    public boolean validateValue( Object value )
    {
      if(!( value instanceof String ))
        return false;

      return Location.isValidLocationSpec( ( String )value );
    }
  },

  CHAT_MESSAGE_TYPE; // TODO: validate

  /////////////////////////////////////////////////////////////////////////////
  // Public API

  public boolean validateValue( Object value )
    { return true; }

}
