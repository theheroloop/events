package com.theheroloop.events.timezones;

import static org.junit.Assert.assertEquals;

import java.time.DayOfWeek;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

import com.theheroloop.events.Location;

import org.junit.BeforeClass;
import org.junit.Test;

public class TimezonesTest
{

  private static Timezones _timezones;

  ////////////////////////////////////////////////////////////////////////////
  // Static methods

  public static Instant instantOf( int year, int month, int day, int hour )
  {
    @SuppressWarnings( "deprecation" )
    var date = Date.UTC( year - 1900, month, day, hour, 0, 0 );

    return Instant.ofEpochMilli( date );
  }

  ////////////////////////////////////////////////////////////////////////////
  // Setup

  @BeforeClass
  public static void beforeAll()
    { _timezones = Timezones.create(); }

  ////////////////////////////////////////////////////////////////////////////
  // Test API

  @Test
  public void testGetDayOfWeekForTimezone()
  {
    var instant = instantOf( 2020, Calendar.NOVEMBER, 2, 12 );
    var mexico = Location.for_( "19.38,-99.18" );

    var time = _timezones.zonedDateTimeFor( instant, mexico );
    var dayOfWeek = time.getDayOfWeek();

    assertEquals( DayOfWeek.MONDAY, dayOfWeek );
    assertEquals( 0, dayOfWeek.ordinal() );

    assertEquals( 6, time.getHour() );
  }

  @Test
  public void testGetDayOfWeekForTimezoneBoundary()
  {
    var instant = instantOf( 2020, Calendar.NOVEMBER, 2, 1 );
    var mexico = Location.for_( "19.38,-99.18" );

    var time = _timezones.zonedDateTimeFor( instant, mexico );
    var dayOfWeek = time.getDayOfWeek();

    assertEquals( DayOfWeek.SUNDAY, dayOfWeek );
    assertEquals( 6, dayOfWeek.ordinal() );

    assertEquals( 19, time.getHour() );
  }

  @Test
  public void testGetDayOfWeekForTimezoneLocalMidnight()
  {
    var instant = instantOf( 2020, Calendar.NOVEMBER, 2, 6 );
    var mexico = Location.for_( "19.38,-99.18" );

    var time = _timezones.zonedDateTimeFor( instant, mexico );
    var dayOfWeek = time.getDayOfWeek();

    assertEquals( DayOfWeek.MONDAY, dayOfWeek );
    assertEquals( 0, dayOfWeek.ordinal() );

    assertEquals( 0, time.getHour() );
  }

  @Test
  public void testGetDayOfWeekForTimezone2()
  {
    var instant = instantOf( 2020, Calendar.NOVEMBER, 2, 12 );
    var sweeden = Location.for_( "59.33,18.07" );

    var time = _timezones.zonedDateTimeFor( instant, sweeden );
    var dayOfWeek = time.getDayOfWeek();

    assertEquals( DayOfWeek.MONDAY, dayOfWeek );
    assertEquals( 0, dayOfWeek.ordinal() );

    assertEquals( 13, time.getHour() );
  }

}
