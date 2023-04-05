package sg.edu.nus.iss.app.CSFAserver.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import sg.edu.nus.iss.app.CSFAserver.services.MovieService;
import sg.edu.nus.iss.app.CSFAserver.models.Comment;
import sg.edu.nus.iss.app.CSFAserver.models.Review;
import sg.edu.nus.iss.app.CSFAserver.repository.MovieRepository;

@Controller
@CrossOrigin("https://csfa-production.up.railway.app")
public class MovieController {
    @Autowired
    MovieService movieSvc;

    @Autowired
    MovieRepository movieRepo;

    @GetMapping(path="/api/search")
    public ResponseEntity<String> getReview(
    @RequestParam(required=true) String name){

        JsonArray result = null;
        Optional<List<Review>> or = this.movieSvc.searchReview(name);
        List<Review> results = or.get(); 
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        for (Review r : results){
            arrBuilder.add(r.toJSON());
        }

        result = arrBuilder.build();
        return ResponseEntity
            .status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .body(result.toString());
    }

    @PostMapping(path="/api/comment")
    public ResponseEntity<String> saveComment(
        @RequestBody Comment comment) {
        Comment r = this.movieRepo.insertComment(comment);
        return ResponseEntity
            .status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .body(r.toJSON().toString());
    }

}
