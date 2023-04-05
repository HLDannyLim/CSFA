package sg.edu.nus.iss.app.CSFAserver.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import sg.edu.nus.iss.app.CSFAserver.models.Review;
import sg.edu.nus.iss.app.CSFAserver.repository.MovieRepository;
@Service
public class MovieService {
    @Value("${movie.api.url}")
    private String movieapiUrl;

    @Value("${movie.api.key}")
    private String moviePublicApiKey;

    @Autowired
    MovieRepository movieRepo;

    public Optional<List<Review>> searchReview(String name){
        List<Review> m = null;

        String finalMovieUrl = UriComponentsBuilder
        .fromUriString(movieapiUrl)
        .queryParam("query", name)
        .queryParam("api-key", moviePublicApiKey)
        .toUriString();
        System.out.println("getMovie finalMovieUrl = "+finalMovieUrl);

        RestTemplate template  = new RestTemplate();
        ResponseEntity<String> resp = null;
        resp = template.getForEntity(finalMovieUrl, String.class);
        System.out.println("resp -->"+resp);
        try {
            m = Review.create(resp.getBody());
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Review review : m) {
            review.setCommentCount(movieRepo.countComments(review.getDisplayTitle()));
        }
        System.out.println(m);
        if(m != null)
            return Optional.of(m);                        
        return Optional.empty();
    }
}
