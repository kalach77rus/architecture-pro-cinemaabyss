package ru.kalach.events.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.kalach.events.movie.model.Movie;
import ru.kalach.events.movie.model.Payment;
import ru.kalach.events.movie.model.User;
import ru.kalach.events.movie.service.MovieProcessorService;

@RestController
@RequestMapping("/api")
public class EventController {

    @Autowired
    MovieProcessorService movieProcessorService;

    @PostMapping("/movie")
    public void createMovie(@RequestParam String name, @RequestParam int duration) {
        Movie movie = new Movie();
        movie.setName(name);
        movie.setDuration(duration);
        movieProcessorService.sendMovie(movie);
    }

    @PostMapping("/user")
    public void createUser(@RequestParam String name, @RequestParam String email) {
        User user = new User();
        user.setUsername(name);
        user.setEmail(email);
        movieProcessorService.sendUser(user);
    }

    @PostMapping("/payment")
    public void createPayment(@RequestParam long sum) {
        Payment payment = new Payment();
        payment.setSum(sum);
        movieProcessorService.sendPayment(payment);
    }

}
