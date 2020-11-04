package com.theheroloop.events.reactor;

import static com.theheroloop.events.reactor.OutgoingRecordAdapter.outputFluxFor;

import com.theheroloop.events.Message;

import reactor.core.publisher.Flux;
import reactor.kafka.sender.KafkaSender;

public class KafkaMessageProducer
{

  private final KafkaSender<String, String> _sender;

  public KafkaMessageProducer( KafkaSender<String, String> sender )
    { _sender = sender; }

  public void send( Flux<Message> messages )
  {
    var flux = outputFluxFor( messages );

    _sender.send( flux )
    .subscribe( message ->
    {
      var offset = message.recordMetadata().offset();

      System.out.printf("Message %d sent successfully\n", offset ); // TODO: use logger
    });
  }

  public void close()
    { _sender.close(); }

}
