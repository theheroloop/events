package com.theheroloop.events.json;

import static com.theheroloop.events.json.JsonSupport.MAPPER;

import com.fasterxml.jackson.core.JsonProcessingException;

public class JsonSerializer
{

  public static final String toJson( Object value )
  {
    var writer = MAPPER.writer();

    try
      { return writer.writeValueAsString( value ); }
    catch( JsonProcessingException e )
      { throw new IllegalArgumentException( String.valueOf( value ), e ); }
  }

}
