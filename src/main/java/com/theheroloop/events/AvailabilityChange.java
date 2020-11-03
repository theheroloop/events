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
    var oldIsAvailable = previousValue > 0;
    var newIsAvailable = newValue      > 0;

    return oldIsAvailable == newIsAvailable ? NO_CHANGE     :
           newIsAvailable                   ? NOW_AVAILABLE : NOW_UNAVAILABLE ;
  }

}
