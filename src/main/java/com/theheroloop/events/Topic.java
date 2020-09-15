package com.theheroloop.events;

/**
 * Supported Kafka topics.
 * @see https://docs.google.com/document/d/13m0TToGTwJQUKCE6i9p5K2KKAPno9rew70phk6fx7Us/edit#heading=h.72tcrz5pir5k
 */
public enum Topic
{
  /**
   * Main event channel for The Hero Loop notifications
   */
  THL_EVENTS
  {
    @Override
    boolean messageMatchesUserId( Message message, String userId )
    {
      return message.matchesHero( userId ) || message.matchesLooper( userId );
    }
  },

  /**
   * Notifications that should be sent to looper clients, 
   * produced by the Location match stream processor.
   */
  LOOPER_NOTIFICATIONS
  {
    @Override
    boolean messageMatchesUserId( Message message, String userId )
      { return message.matchesLooper( userId ); }
  },

  /**
   * Notifications that should be sent to hero clients, 
   * produced by the Location match stream processor.
   */
  HERO_NOTIFICATIONS
  {
    @Override
    boolean messageMatchesUserId( Message message, String userId )
      { return message.matchesHero( userId ); }
  };

  ////////////////////////////////////////////////////////////////////////////
  // Static API

  public static boolean isValid( String topicName )
  {
    for( var topic : values() )
      if( topic.topicName().equals( topicName ) )
        return true;

    return false;
  }

  public static Topic for_( String topicName )
  {
    for( var topic : values() )
      if( topic.topicName().equals( topicName ) )
        return topic;

    throw new IllegalArgumentException( topicName );
  }

  ////////////////////////////////////////////////////////////////////////////
  // Instance

  private String _name;

  private Topic()
    { _name = name().toLowerCase().replaceAll( "_", "-" ); }

  public String topicName()
    { return _name; }

  /**
   * Return true if a given input message on this topic matches the provided
   * user id.
   */
  boolean messageMatchesUserId( Message message, String userId )
    { return false; }

  @Override
  public String toString()
    { return _name; }

}
