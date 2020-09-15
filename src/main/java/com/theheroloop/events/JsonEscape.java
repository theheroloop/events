package com.theheroloop.events;

public class JsonEscape
{

  public static String q( String in )
    { return in.replaceAll( "'", "\"" ); }

}
