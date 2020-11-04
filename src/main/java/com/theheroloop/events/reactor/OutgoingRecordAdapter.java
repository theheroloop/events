package com.theheroloop.events.reactor;

import com.theheroloop.events.Message;

import reactor.core.publisher.Flux;
import reactor.kafka.sender.SenderRecord;

/**
 * Reusable class that transforms a The Hero Loop message into a String-based
 * sender record that can be published to Kafka.
 */
public class OutgoingRecordAdapter
{

  public static Flux<SenderRecord<String, String, Long>>
    outputFluxFor( Flux<Message> messages )
  {
    return messages.map( message ->
    {
      var correlation = System.currentTimeMillis();
      var text = message.toJson();
      var topicName = message.eventType().topic().topicName();

      return SenderRecord.create(
        topicName, null, null, (String)null, text, correlation
      );
    });
  }

}
