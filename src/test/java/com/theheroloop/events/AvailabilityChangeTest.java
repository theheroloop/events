package com.theheroloop.events;

import static com.theheroloop.events.AvailabilityChange.*;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AvailabilityChangeTest
{

  @Test
  public void testNowAvailable()
  {
    assertEquals( NOW_AVAILABLE, AvailabilityChange.for_(0, 1) );
    assertEquals( NOW_AVAILABLE, AvailabilityChange.for_(0, 2) );
    assertEquals( NOW_AVAILABLE, AvailabilityChange.for_(0, 199) );
  }

  @Test
  public void testNowUnavailable()
  {
    assertEquals( NOW_UNAVAILABLE, AvailabilityChange.for_(1, 0) );
    assertEquals( NOW_UNAVAILABLE, AvailabilityChange.for_(2, 0) );
    assertEquals( NOW_UNAVAILABLE, AvailabilityChange.for_(100, 0) );
  }

  @Test
  public void testNoChange()
  {
    assertEquals( NO_CHANGE, AvailabilityChange.for_(0, 0) );
    assertEquals( NO_CHANGE, AvailabilityChange.for_(1, 1) );
    assertEquals( NO_CHANGE, AvailabilityChange.for_(2, 2) );
    assertEquals( NO_CHANGE, AvailabilityChange.for_(3, 3) );
  }

}
