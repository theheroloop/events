package com.theheroloop.events;

/**
 * Represents a change in availability:
 * <ul>
 *   <li> of heroes
 *   <li> of heroes near loopers, or
 *   <li> or some other resource
 * </ul>
 */
public enum AvailabilityChange
{
  NO_CHANGE, NOW_AVAILABLE, NOW_UNAVAILABLE;

  public static final AvailabilityChange for_( int previousValue, int newValue )
  {
    return previousValue == newValue ? NO_CHANGE     :
           previousValue == 0        ? NOW_AVAILABLE : NOW_UNAVAILABLE ;
  }

}
