package com.theheroloop.events.timezones;

import java.time.*;
import java.util.logging.Logger;

import com.theheroloop.events.Location;

import net.iakovlev.timeshape.TimeZoneEngine;

public class Timezones
{

  public static final Timezones create()
  {
    var engine = TimeZoneEngine.initialize();

    return new Timezones( engine );
  }

  ////////////////////////////////////////////////////////////////////////////
  // Instance fields and Constructor

  private final Logger _logger = Logger.getLogger( getClass().getName() );

  private final TimeZoneEngine _engine;

  public Timezones( TimeZoneEngine engine )
    { _engine = engine; }

  ////////////////////////////////////////////////////////////////////////////
  // API

  public ZonedDateTime zonedDateTimeFor( Instant instant, Location location )
  {
    var zoneId = _zoneIdFor( location );

    return instant.atZone( zoneId );
  }

  ////////////////////////////////////////////////////////////////////////////
  // Private

  private ZoneId _defaultZoneId()
    { return ZoneId.systemDefault(); }

  private ZoneId _zoneIdFor( Location location )
  {
    if( location == null )
      return _defaultZoneId();

    var zoneId =
      _engine.query( location.latitudeValue(), location.longitudeValue() );

    if( ! zoneId.isPresent() )
    {
      _logger.warning( String.format( "No ZoneId found for %s", location ) );

      return _defaultZoneId();
    }

    return zoneId.get();
  }

}
