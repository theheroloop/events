package com.theheroloop.events.reactor;

import com.theheroloop.events.IncomingMessage;
import com.theheroloop.events.IncomingMessageFactory;

import reactor.kafka.receiver.ReceiverRecord;

public class ReactorIncomingMessageFactory extends IncomingMessageFactory
{

  public IncomingMessage create( ReceiverRecord<String, String> record )
  {
    var offset = record.receiverOffset().offset();
    var topicName = record.topic();
    var key = record.key();
    var payload = record.value();

    return create( topicName, offset, key, payload );
  }

}
