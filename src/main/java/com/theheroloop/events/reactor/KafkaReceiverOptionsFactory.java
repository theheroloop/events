package com.theheroloop.events.reactor;

import java.util.HashMap;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;

import reactor.kafka.receiver.ReceiverOptions;

public class KafkaReceiverOptionsFactory
{

  /////////////////////////////////////////////////////////////////////////////
  // Public API

  public ReceiverOptions<String, String> createOptionsFor
    ( String bootstrapServers, String clientId, String groupId )
  {
    var properties = new HashMap<String, Object>()
    {{
      put( ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG        , bootstrapServers         );
      put( ConsumerConfig.CLIENT_ID_CONFIG                , clientId                 );
      put( ConsumerConfig.GROUP_ID_CONFIG                 , groupId                  );
      put( ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG   , StringDeserializer.class );
      put( ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG , StringDeserializer.class );
      put( ConsumerConfig.AUTO_OFFSET_RESET_CONFIG        , "latest"                 );
    }};

    return ReceiverOptions.create( properties );
  }

}
