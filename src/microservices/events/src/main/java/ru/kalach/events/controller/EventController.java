package ru.kalach.events.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import ru.kalach.events.movie.model.MovieEvent;
import ru.kalach.events.movie.model.MovieEventRequest;
import ru.kalach.events.movie.model.PaymentEvent;
import ru.kalach.events.movie.model.PaymentEventRequest;
import ru.kalach.events.movie.model.UserEvent;
import ru.kalach.events.movie.model.UserEventRequest;
import ru.kalach.events.movie.service.MovieProcessorService;
import org.springframework.http.ResponseEntity;
import java.util.Map;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    MovieProcessorService movieProcessorService;

    // public void createMovieEvent(@RequestParam String name, @RequestParam int duration) {
    //     Movie movie = new Movie();
    //     movie.setName(name);
    //     movie.setDuration(duration);
    //     movieProcessorService.sendMovie(movie);
    // }

    @PostMapping("/movie")
    public ResponseEntity<?> createMovieEvent(@Valid @RequestBody MovieEventRequest request) {
        MovieEvent event = new MovieEvent();
        event.setId(request.movie_id());
        event.setName(request.title());
        event.setAction(request.action());
        event.setUserId(request.user_id());

        movieProcessorService.sendMovieEvent(event);
        return ResponseEntity.status(201).body(Map.of("status", "success"));
    }

    @PostMapping("/user")
    public ResponseEntity<?> createUserEvent(@Valid @RequestBody UserEventRequest request) {

        UserEvent event = new UserEvent();
        event.setId(request.user_id());
        event.setUsername(request.username());
        event.setAction(request.action());
        event.setTimestamp(request.timestamp());

        movieProcessorService.sendUserEvent(event);
        return ResponseEntity.status(201).body(Map.of("status", "success"));
    }

    // @PostMapping("/payment")
    // public void createPaymentEvent(@RequestParam long sum) {
    //     Payment payment = new Payment();
    //     payment.setSum(sum);
    //     movieProcessorService.sendPayment(payment);
    // }

    @PostMapping("/payment")
    public ResponseEntity<?> createPaymentEvent(@Valid @RequestBody PaymentEventRequest request) {

        PaymentEvent event = new PaymentEvent();
        event.setPaymentId(request.payment_id());
        event.setUserId(request.user_id());
        event.setAmount(request.amount());
        event.setStatus(request.status());
        event.setTimestamp(request.timestamp());
        event.setMethodType(request.method_type());

        movieProcessorService.sendPaymentEvent(event);
        return ResponseEntity.status(201).body(Map.of("status", "success"));
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, Boolean>> health() {
        return ResponseEntity.ok(Map.of("status", true));
    }

}
