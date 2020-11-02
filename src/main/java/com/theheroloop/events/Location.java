package com.theheroloop.events;

import java.util.regex.Pattern;

/**
 * Represents a valid location object ( latitude / longitude ).
 */
public class Location
{

  private static final Pattern LOCATION =
    Pattern.compile( "(-?\\d+[.]?\\d*),(-?\\d+[.]?\\d*)" );

  ////////////////////////////////////////////////////////////////////////////
  // Static API

  public static boolean isValidLocationSpec( String spec )
  {
    if( spec == null )
      return false;

    var matcher = LOCATION.matcher( spec );

    return matcher.matches();
  }

  public static Location for_( String spec )
  {
    if( spec == null )
      return null;

    var matcher = LOCATION.matcher( spec );

    if( !matcher.matches() )
      return null;

    return new Location( matcher.group(1), matcher.group(2) );
  }

  public static Location for_( Double latitude, Double longitude )
  {
    if( latitude == null || longitude == null )
      return null;

    var latitudeString  = String.format( "%.6f", latitude  );
    var longitudeString = String.format( "%.6f", longitude );

    return new Location( latitudeString, longitudeString );
  }

  ////////////////////////////////////////////////////////////////////////////
  // Instance fields

  private final String _latitude ;
  private final String _longitude;

  ////////////////////////////////////////////////////////////////////////////
  // Constructor

  /**
   * Creates a location with already validated latitude and longitude values.
   */
  private Location( String latitude, String longitude )
  {
    _latitude  = latitude ;
    _longitude = longitude;
  }

  ////////////////////////////////////////////////////////////////////////////
  // Public API

  public String latitude()
    { return _latitude; }

  public String longitude()
    { return _longitude; }

  public double latitudeValue()
    { return Double.parseDouble( _latitude ); }

  public double longitudeValue()
    { return Double.parseDouble( _longitude ); }

  @Override
  public String toString()
    { return String.format( "%s,%s", _latitude, _longitude ); }

}
