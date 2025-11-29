package ru.kalach.events.movie.service;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import ru.kalach.events.movie.model.Movie;
import ru.kalach.events.movie.model.MovieResponse;
import ru.kalach.events.movie.model.Payment;
import ru.kalach.events.movie.model.User;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class MovieProcessorService {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${kafka.topics.response:responses}")
    private String responseTopic;

    @Value("${kafka.topics.movie:movie}")
    private String movieTopic;

    @Value("${kafka.topics.users:user}")
    private String usersTopic;

    @Value("${kafka.topics.payments:payment}")
    private String paymentsTopic;

    public void processMovie(Movie movie) {
        log.info("Processing movie: {}", movie);

        // Создаем ответ
        MovieResponse response = new MovieResponse(
                movie.getName(),
                movie.getDuration(),
                "PROCESSED",
                System.currentTimeMillis()
        );

        // Отправляем в топик responses
        sendToResponseTopic(response);
    }

    private void sendToResponseTopic(MovieResponse response) {
        try {
            CompletableFuture<SendResult<String, Object>> future =
                    kafkaTemplate.send(responseTopic, response);

            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    log.info("Successfully sent response to topic '{}': {}", responseTopic, response);
                } else {
                    log.error("Failed to send response to topic '{}': {}", responseTopic, ex.getMessage());
                }
            });
        } catch (Exception e) {
            log.error("Error sending message to Kafka topic '{}': {}", responseTopic, e.getMessage());
        }
    }

    public void sendMovie(Movie movie) {
        try {
            CompletableFuture<SendResult<String, Object>> future =
                    kafkaTemplate.send(movieTopic, movie);

            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    log.info("Successfully sent movie to topic '{}': {}", movieTopic, movie);
                } else {
                    log.error("Failed to send movie to topic '{}': {}", movieTopic, ex.getMessage());
                }
            });
        } catch (Exception e) {
            log.error("Error sending message to Kafka topic '{}': {}", movieTopic, e.getMessage());
        }
    }

    public void sendUser(User user) {
        try {
            CompletableFuture<SendResult<String, Object>> future =
                    kafkaTemplate.send(usersTopic, user);

            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    log.info("Successfully sent user to topic '{}': {}", usersTopic, user);
                } else {
                    log.error("Failed to send user to topic '{}': {}", usersTopic, ex.getMessage());
                }
            });
        } catch (Exception e) {
            log.error("Error sending message to Kafka topic '{}': {}", usersTopic, e.getMessage());
        }
    }

    public void sendPayment(Payment payment) {
        try {
            CompletableFuture<SendResult<String, Object>> future =
                    kafkaTemplate.send(paymentsTopic, payment);

            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    log.info("Successfully sent payment to topic '{}': {}", paymentsTopic, payment);
                } else {
                    log.error("Failed to send payment to topic '{}': {}", paymentsTopic, ex.getMessage());
                }
            });
        } catch (Exception e) {
            log.error("Error sending message to Kafka topic '{}': {}", paymentsTopic, e.getMessage());
        }
    }

}
