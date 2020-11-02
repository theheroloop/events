package com.theheroloop.events;

import static org.junit.Assert.*;

import org.junit.Test;

public class LocationTest
{

  private static final double DELTA = 0.000001;

  @Test
  public void testCreateValidLocation()
  {
    var location = Location.for_( "12.2333,34.34343" );

    assertNotNull( location );
    assertEquals( "12.2333", location.latitude() );
    assertEquals( "34.34343", location.longitude() );
    assertEquals( 12.2333, location.latitudeValue(), DELTA );
    assertEquals( 34.34343, location.longitudeValue(), DELTA );
  }

  @Test
  public void testCreateValidLocation2()
  {
    var location = Location.for_( "10.1,23.3" );

    assertNotNull( location );
    assertEquals( "10.1", location.latitude() );
    assertEquals( "23.3", location.longitude() );
    assertEquals( 10.1, location.latitudeValue(), DELTA );
    assertEquals( 23.3, location.longitudeValue(), DELTA );
  }

  @Test
  public void testCreateValidLocationNegatives()
  {
    var location = Location.for_( "-12.2333,-34.34343" );

    assertNotNull( location );
    assertEquals( "-12.2333", location.latitude() );
    assertEquals( "-34.34343", location.longitude() );
    assertEquals( -12.2333, location.latitudeValue(), DELTA );
    assertEquals( -34.34343, location.longitudeValue(), DELTA );
  }

  @Test
  public void testCreateValidLocationInteger()
  {
    var location = Location.for_( "12,34" );

    assertNotNull( location );
    assertEquals( "12", location.latitude() );
    assertEquals( "34", location.longitude() );
    assertEquals( 12, location.latitudeValue(), DELTA );
    assertEquals( 34, location.longitudeValue(), DELTA );
  }

  @Test
  public void testInvalidLocations()
  {
    assertNull( Location.for_( "1234" ) );
    assertNull( Location.for_( "12.1,xxxx" ) );
    assertNull( Location.for_( "" ) );
    assertNull( Location.for_( null ) );
  }

  @Test
  public void testCreateValidLocationFromDoubles()
  {
    var location = Location.for_( 12.2333, 34.34343 );

    assertNotNull( location );
    assertEquals( "12.233300", location.latitude() );
    assertEquals( "34.343430", location.longitude() );
    assertEquals( 12.2333, location.latitudeValue(), DELTA );
    assertEquals( 34.34343, location.longitudeValue(), DELTA );
  }

  @Test
  public void testHandleNullValues()
  {
    assertNull( Location.for_( 12.2333, null    ) );
    assertNull( Location.for_( null   , 12.2333 ) );
    assertNull( Location.for_( null   , null    ) );
  }

}
