package com.theheroloop.events.reactor;

import static com.theheroloop.events.json.JsonEscape.q;
import static com.theheroloop.events.EventType.CHAT_MESSAGE_SENT;
import static com.theheroloop.events.Topic.THL_EVENTS;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.HashSet;

import com.theheroloop.events.EventType;

import org.junit.*;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import reactor.core.publisher.Flux;
import reactor.kafka.receiver.*;
import reactor.test.StepVerifier;

public class KafkaMessageSourceTest
{

  private static final String SAMPLE_VALID_MESSAGE = q(
    "{ 'eventType': 'chat-message-sent', 'looperId': 'ABC123', 'heroId': 'H1', 'requestId': 'R1', 'chatMessage': {} }"
  );

  @Mock KafkaReceiver<String, String> _receiver;
  @Mock ReceiverRecord<String, String> _sampleReceiverRecord;
  @Mock ReceiverOffset _sampleReceiverOffset;

  @Rule public MockitoRule mockitoRule = MockitoJUnit.rule(); 

  @Before
  public void beforeEach()
  {
    when( _sampleReceiverRecord.receiverOffset() )
    . thenReturn( _sampleReceiverOffset )
    ;
    when( _sampleReceiverRecord.key() ).thenReturn( null );

    when( _receiver.receive() )
    . thenReturn( Flux.just(_sampleReceiverRecord) );
  }

  @Test
  public void testValidMessage()
  {
    var supported = new HashSet<EventType>()
    {{
      add( CHAT_MESSAGE_SENT );
    }};

    when( _sampleReceiverRecord.topic() ).thenReturn( THL_EVENTS.topicName() );
    when( _sampleReceiverRecord.value() ).thenReturn( SAMPLE_VALID_MESSAGE );

    var source = new KafkaMessageSource( _receiver, supported );

    var flux = source.run();
    assertNotNull( flux );

    StepVerifier
    . create( flux )
    . expectNextMatches( message ->
      message.offset() == 0    &&
      message.key()    == null &&
      message.payload().equals( SAMPLE_VALID_MESSAGE )
    )
    . expectComplete()
    . verify()
    ;
  }

  @Test
  public void testNoJsonMessage()
  {
    var supported = new HashSet<EventType>()
    {{
      add( CHAT_MESSAGE_SENT );
    }};

    when( _sampleReceiverRecord.topic() ).thenReturn( THL_EVENTS.topicName() );
    when( _sampleReceiverRecord.value() ).thenReturn( "gato" );

    var source = new KafkaMessageSource( _receiver, supported );

    var flux = source.run();
    assertNotNull( flux );

    StepVerifier
    . create( flux )
    . expectComplete()
    . verify()
    ;
  }

}
