import org.springframework.kafka.config.TopicBuilder;
import java.util.Map;
import org.apache.kafka.clients.producer.ProducerConfig;

import com.example.producer.service.messaging.event.OrderSendEvent;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/*
// Через конфигурацию можно управлять параметрами кафки
ProducerConfig.BOOTSTRAP_SERVERS_CONFIG = "bootstrap.servers";
ProducerConfig.CLIENT_DNS_LOOKUP_CONFIG = "client.dns.lookup";
ProducerConfig.METADATA_MAX_AGE_CONFIG = "metadata.max.age.ms";
ProducerConfig.METADATA_MAX_IDLE_CONFIG = "metadata.max.idle.ms";
ProducerConfig.BATCH_SIZE_CONFIG = "batch.size";
ProducerConfig.PARTITIONER_ADPATIVE_PARTITIONING_ENABLE_CONFIG = "partitioner.adaptive.partitioning.enable";
ProducerConfig.PARTITIONER_AVAILABILITY_TIMEOUT_MS_CONFIG = "partitioner.availability.timeout.ms";
ProducerConfig.PARTITIONER_IGNORE_KEYS_CONFIG = "partitioner.ignore.keys";
ProducerConfig.ACKS_CONFIG = "acks";
ProducerConfig.LINGER_MS_CONFIG = "linger.ms";
ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG = "request.timeout.ms";
ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG = "delivery.timeout.ms";
ProducerConfig.CLIENT_ID_CONFIG = "client.id";
ProducerConfig.SEND_BUFFER_CONFIG = "send.buffer.bytes";
ProducerConfig.RECEIVE_BUFFER_CONFIG = "receive.buffer.bytes";
ProducerConfig.MAX_REQUEST_SIZE_CONFIG = "max.request.size";
ProducerConfig.RECONNECT_BACKOFF_MS_CONFIG = "reconnect.backoff.ms";
ProducerConfig.RECONNECT_BACKOFF_MAX_MS_CONFIG = "reconnect.backoff.max.ms";
ProducerConfig.MAX_BLOCK_MS_CONFIG = "max.block.ms";
ProducerConfig.BUFFER_MEMORY_CONFIG = "buffer.memory";
ProducerConfig.RETRY_BACKOFF_MS_CONFIG = "retry.backoff.ms";
ProducerConfig.COMPRESSION_TYPE_CONFIG = "compression.type";
ProducerConfig.METRICS_SAMPLE_WINDOW_MS_CONFIG = "metrics.sample.window.ms";
ProducerConfig.METRICS_NUM_SAMPLES_CONFIG = "metrics.num.samples";
ProducerConfig.METRICS_RECORDING_LEVEL_CONFIG = "metrics.recording.level";
ProducerConfig.METRIC_REPORTER_CLASSES_CONFIG = "metric.reporters";
ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION = "max.in.flight.requests.per.connection";
ProducerConfig.RETRIES_CONFIG = "retries";
ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG = "key.serializer";
ProducerConfig.KEY_SERIALIZER_CLASS_DOC = "Serializer class for key that implements the <code>org.apache.kafka.common.serialization.Serializer</code> interface.";
ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG = "value.serializer";
ProducerConfig.SOCKET_CONNECTION_SETUP_TIMEOUT_MS_CONFIG = "socket.connection.setup.timeout.ms";
ProducerConfig.SOCKET_CONNECTION_SETUP_TIMEOUT_MAX_MS_CONFIG = "socket.connection.setup.timeout.max.ms";
ProducerConfig.CONNECTIONS_MAX_IDLE_MS_CONFIG = "connections.max.idle.ms";
ProducerConfig.PARTITIONER_CLASS_CONFIG = "partitioner.class";
ProducerConfig.INTERCEPTOR_CLASSES_CONFIG = "interceptor.classes";
ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG = "enable.idempotence";
ProducerConfig.TRANSACTION_TIMEOUT_CONFIG = "transaction.timeout.ms";
ProducerConfig.TRANSACTIONAL_ID_CONFIG = "transactional.id";
ProducerConfig.SECURITY_PROVIDERS_CONFIG = "security.providers";
*/


@Configuration
public class KafkaConfiguration {

    @Bean
    NewTopic createTopic() {
        return TopicBuilder.name("product-created-events-topic")
          .partitions(3) // 3 партиции
          .replicas(3) // 3 реплики
          .configs(Map.of("min.insync.replicas", "2")) // минимальное кол-во 2 сервера в синхронизации с лидером (параметр Isr при вызове команды --describe из консоли)
          // рекомендуется устанавливать значение на 1 меньше, чем всего реплик
          .build();
    }

    // Фабрика выдает конфиги
    @Bean
    ProducerFactory<String, OrderSendEvent> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    // Получится в контексте бин со своим конфигом вместо дефолтного конфига
    @Bean
    KafkaTemplate<String, OrderSendEvent> kafkaTemplate() {
        return new KafkaTemplate<String, OrderSendEvent>(producerFactory());
    }

    Map<String, Object> producerConfigs() {
        Map<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "2");

        return config;
    }
}
