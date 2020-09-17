package com.theheroloop.events.json;

import static com.theheroloop.events.json.JsonSupport.MAPPER;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;

public class JsonDeserializer
{

  @SuppressWarnings( "unchecked" )
  public static Map<String, Object> extract( String payload )
  {
    try
      { return MAPPER.readValue( payload, HashMap.class ); }
    catch( JsonProcessingException e )
      { throw new IllegalArgumentException( payload, e ); }
  }

}
