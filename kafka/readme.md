Работа с консолью:
https://github.com/futurer1/Linux/blob/master/Kafka.md


Dependencies:
```groovy
// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web
implementation 'org.springframework.boot:spring-boot-starter-web:3.2.1'

// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-test
testImplementation 'org.springframework.boot:spring-boot-starter-test:3.2.1'

// https://mvnrepository.com/artifact/org.springframework.kafka/spring-kafka
implementation 'org.springframework.kafka:spring-kafka:3.1.1'

// https://mvnrepository.com/artifact/org.springframework.kafka/spring-kafka-test
testImplementation 'org.springframework.kafka:spring-kafka-test:3.1.1'
```

Типы ошибок:
- retryable error - ошибка, которая может быть устранена и возникла на время. То есть повтор операции может уже привести к успеху.
- non-retryable - ошибка, которая стабильна и никуда не пропадёт при тех же параметрах вызова.


Handler consumer для обработки входящих event:
```java
@Component
@KafkaListener(topics = "product-added-event-topic") // класс сразу для всех типов событий в топике
public class ProductAddedEventHandler {

    @KafkaHandler
    public void handle(ProductAddedEvent productAddedEvent) { // автоматом сюда будет mapping события этого объекта
        // ...
    }
}
```
