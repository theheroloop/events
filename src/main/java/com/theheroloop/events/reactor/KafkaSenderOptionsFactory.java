package com.theheroloop.events.reactor;

import java.util.HashMap;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import reactor.kafka.sender.SenderOptions;

public class KafkaSenderOptionsFactory
{

  public SenderOptions<String, String>
    createOptionsFor( String bootstrapServers, String clientId )
  {
    var properties = new HashMap<String, Object>()
    {{
      put( ProducerConfig.BOOTSTRAP_SERVERS_CONFIG     , bootstrapServers       );
      put( ProducerConfig.CLIENT_ID_CONFIG             , clientId               );
      put( ProducerConfig.ACKS_CONFIG                  , "all"                  );
      put( ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG  , StringSerializer.class );
      put( ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class );
    }};

    return SenderOptions.<String, String>create( properties );
  }

}
