package com.theheroloop.events.json;

public class JsonEscape
{

  public static String q( String in )
    { return in.replaceAll( "'", "\"" ); }

}
