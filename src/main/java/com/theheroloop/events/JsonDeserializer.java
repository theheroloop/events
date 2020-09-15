package com.theheroloop.events;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonDeserializer
{

  private static final ObjectMapper MAPPER = new ObjectMapper();

  @SuppressWarnings( "unchecked" )
  public static Map<String, Object> extract( String payload )
  {
    try {
      return MAPPER.readValue( payload, HashMap.class );
    }
    catch( RuntimeException e ) {
      throw e;
    }
    catch( Exception e ) {
      throw new RuntimeException( e );
    }
  }

}
