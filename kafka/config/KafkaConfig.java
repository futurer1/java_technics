import org.springframework.kafka.config.TopicBuilder;
import java.util.Map;

@Configuration
public class KafkaConfiguration {

    @Bean
    NewTopic createTopic() {
        return TopicBuilder.name("product-created-events-topic")
          .partitions(3) // 3 партиции
          .replicas(3) // 3 реплики
          .configs(Map.of("min.insync.replicas", "2")) // минимальное кол-во 2 сервера в синхронизации с лидером (параметр Isr при вызове --describe)
          // рекомендуется на 1 меньше, чем всего реплик
          .build();
    }
}
