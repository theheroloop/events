package com.theheroloop.events;

/**
 * Recognized skills for heroes in the hero loop.
 * There may be others defined in the database fields.
 */
public enum Skill
{

  DRIVER  ,
  PICKER  ,
  SHOPPER ;

  @Override
  public String toString()
    { return name().toLowerCase(); }

}
