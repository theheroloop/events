package com.theheroloop.events;

import static com.theheroloop.events.Skill.*;

import java.util.*;

/**
 * Supported help type values for the hero loop.
 */
public enum HelpType
{
  FOOD     ( PICKER, SHOPPER ),
  TRANSPORT( DRIVER          ),
  MEDICINE ( PICKER, SHOPPER ),
  OTHER;

  /////////////////////////////////////////////////////////////////////////////
  // Static

  public static HelpType for_( String name )
  {
    for( var value : values() )
      if( value.toString().equals(name) )
        return value;

    return OTHER;
  }

  /////////////////////////////////////////////////////////////////////////////
  // Instance

  private final Set<Skill> _skills;

  private HelpType( Skill... skills )
  {
    _skills = Collections.unmodifiableSet(
      new TreeSet<Skill>( Arrays.asList( skills ) )
    );
  }

  /////////////////////////////////////////////////////////////////////////////
  // Public API

  public Set<Skill> neededSkills()
    { return _skills; }

}
