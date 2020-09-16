package com.theheroloop.events.reactor;

import java.util.Collections;
import java.util.Set;

import com.theheroloop.events.EventType;
import com.theheroloop.events.IncomingMessage;

import reactor.core.publisher.Flux;
import reactor.kafka.receiver.KafkaReceiver;

public class KafkaMessageSource
{

  private final KafkaReceiver<String, String> _receiver;
  private final Set<EventType> _supportedEventTypes;

  private final ReactorIncomingMessageFactory _factory =
    new ReactorIncomingMessageFactory();

  KafkaMessageSource
  (
    KafkaReceiver<String, String> receiver,
    Set<EventType> supportedEventTypes
  )
  {
    _receiver = receiver;
    _supportedEventTypes = Collections.unmodifiableSet( supportedEventTypes );
  }

  public Flux<IncomingMessage> run()
  {
    return _receiver.receive()
    . map( _factory::create )
    . filter( message ->
      message != null   &&
      message.isValid() &&
      _supportedEventTypes.contains( message.eventType() )
    );
  }

}
