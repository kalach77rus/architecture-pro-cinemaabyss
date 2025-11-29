package ru.kalach.events.movie.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import ru.kalach.events.movie.model.Movie;
import ru.kalach.events.movie.model.Payment;
import ru.kalach.events.movie.model.User;

@Component
@Slf4j
public class MovieConsumer {

    @Autowired
    private MovieProcessorService movieProcessorService;

    @KafkaListener(topics = "${kafka.topics.movies:movies}")
    public void listenMovieEvent(
            @Payload Movie movie,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
            @Header(KafkaHeaders.OFFSET) long offset) {

        log.info("Received message from topic '{}', partition {}, offset {}: {}",
                topic, partition, offset, movie);

        try {
            movieProcessorService.processMovie(movie);
        } catch (Exception e) {
            log.error("Error processing movie message: {}", e.getMessage());
        }
    }

    @KafkaListener(topics = "${kafka.topics.users:users}")
    public void listenUserEvent(
            @Payload User user,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
            @Header(KafkaHeaders.OFFSET) long offset) {

        log.info("Received message from topic '{}', partition {}, offset {}: {}",
                topic, partition, offset, user);
    }

    @KafkaListener(topics = "${kafka.topics.payments:payments}")
    public void listenPaymentEvent(
            @Payload Payment payment,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
            @Header(KafkaHeaders.OFFSET) long offset) {

        log.info("Received message from topic '{}', partition {}, offset {}: {}",
                topic, partition, offset, payment);
    }
}
