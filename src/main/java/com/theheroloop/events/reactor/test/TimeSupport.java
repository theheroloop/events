package com.theheroloop.events.reactor.test;

import java.time.Instant;
import java.util.Date;

public class TimeSupport
{

  public static Instant instantOf( int year, int month, int day, int hour )
  {
    @SuppressWarnings( "deprecation" )
    var date = Date.UTC( year - 1900, month, day, hour, 0, 0 );

    return Instant.ofEpochMilli( date );
  }

}
