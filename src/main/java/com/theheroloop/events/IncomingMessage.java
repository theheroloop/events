package com.theheroloop.events;

import java.util.Collections;
import java.util.Map;

public class IncomingMessage extends Message
{

  private final Topic _topic;
  private final long _offset;
  private final String _key;
  private final String _payload;

  /////////////////////////////////////////////////////////////////////////////
  // Constructor

  public IncomingMessage(
    Topic topic, long offset, String key, String payload,
    Map<String, Object> fields
  )
  {
    super( Collections.unmodifiableMap(fields) );

    _topic = topic;
    _offset = offset;
    _key = key;
    _payload = payload;
  }

  /////////////////////////////////////////////////////////////////////////////
  // Static API

  /**
   * Convenience factory for test purposes.
   */
  public static IncomingMessage for_( Topic topic, String payload )
  {
    var factory = new IncomingMessageFactory();

    return factory.create( topic, 0, null, payload );
  }

  /////////////////////////////////////////////////////////////////////////////
  // Public API

  public Topic topic()
    { return _topic; }

  public long offset()
    { return _offset; }

  public String key()
    { return _key; }

  public String payload()
    { return _payload; }

  public boolean matchesUserId( String userId )
    { return _topic.messageMatchesUserId( this, userId ); }

}
