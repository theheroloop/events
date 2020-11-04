package com.theheroloop.events.reactor.test;

import static com.theheroloop.events.reactor.OutgoingRecordAdapter.outputFluxFor;

import java.util.*;
import java.util.concurrent.CountDownLatch;

import com.theheroloop.events.*;
import com.theheroloop.events.reactor.*;

import reactor.core.publisher.Flux;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.sender.KafkaSender;

public class IntegrationTestSupport
{

  private final KafkaReceiverOptionsFactory _receiverOptionsFactory =
    new KafkaReceiverOptionsFactory();

  private KafkaSenderOptionsFactory _senderOptionsFactory =
    new KafkaSenderOptionsFactory();

  private final String _name = getClass().getSimpleName();

  private final String _bootstrapServers;
  private final String _groupId;

  /////////////////////////////////////////////////////////////////////////////
  // Constructor

  protected IntegrationTestSupport( String bootstrapServers, String groupId )
  {
    this._bootstrapServers = bootstrapServers;
    this._groupId = groupId;
  }

  /////////////////////////////////////////////////////////////////////////////
  // Protected API

  protected KafkaSender<String, String> createComponentThatWillSendInputMessage()
  {
    var kafkaSenderOptions = _senderOptionsFactory.createOptionsFor
      ( _bootstrapServers, _name + "_sender" );

    return KafkaSender.create( kafkaSenderOptions );
  }

  protected KafkaMessageSource createComponentThatWillRecoverTestOutputMessages
    ( EventType expectedEventType )
  {
    var topics = Arrays.asList( expectedEventType.topic().topicName() );

    // a different client id is needed, or KafkaReceiver will collide with the in-app built one.
    var kafkaReceiverOptions = _receiverOptionsFactory.createOptionsFor
      ( _bootstrapServers, _name + "_receiver", _groupId );

    var receiver =
      KafkaReceiver.create( kafkaReceiverOptions.subscription(topics) );

    var supportedEventTypes = EnumSet.of( expectedEventType );

    return new KafkaMessageSource( receiver, supportedEventTypes );
  }

  protected List<IncomingMessage> startMessageRecovery
    ( KafkaMessageSource source, final CountDownLatch lock )
  {
    var recoveredMessages = new ArrayList<IncomingMessage>();

    source.run()
    .subscribe( recovered ->
    {
      recoveredMessages.add( recovered );
      lock.countDown();
    });

    return recoveredMessages;
  }

  protected void sendMessage
    ( KafkaSender<String, String> sender, Message message )
  {
    var publisherFlux = outputFluxFor( Flux.just(message) );

    sender.send( publisherFlux )
    .subscribe( outgoing ->
    {
      System.out.println("Outgoing message sent successfully" );
    });
  }

}
