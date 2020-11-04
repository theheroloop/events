package com.theheroloop.events.reactor;

import static com.theheroloop.events.EventType.HEROES_AVAILABLE_NEAR_LOOPER;
import static com.theheroloop.events.MessageField.EVENT_TYPE_FIELD;
import static com.theheroloop.events.MessageField.LOOPER_ID_FIELD;

import com.theheroloop.events.MessageBuilder;
import com.theheroloop.events.json.JsonDeserializer;

import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class OutgoingRecordAdapterTest
{

  @Test
  public void testSendMessages() throws InterruptedException
  {
    var message = MessageBuilder.for_( HEROES_AVAILABLE_NEAR_LOOPER )
    . setLooperId( "1023" )
    . build();
    var inputFlux = Flux.just( message );
    var expectedTopic = HEROES_AVAILABLE_NEAR_LOOPER.topic().topicName();

    var outputFlux = OutgoingRecordAdapter.outputFluxFor( inputFlux );

    StepVerifier
    . create( outputFlux )
    . expectNextMatches( record ->
      expectedTopic.equals( record.topic() ) &&
      isExpectedJsonMessage( record.value() )
    )
    . expectComplete()
    . verify()
    ;
  }

  private boolean isExpectedJsonMessage( String json )
  {
    var fields = JsonDeserializer.extract( json );
    var expectedEventType = HEROES_AVAILABLE_NEAR_LOOPER.eventName();

    return fields != null &&
      expectedEventType.equals( fields.get( EVENT_TYPE_FIELD.fieldName() ) ) &&
      "1023"           .equals( fields.get( LOOPER_ID_FIELD.fieldName () ) )
    ;
  }

}
