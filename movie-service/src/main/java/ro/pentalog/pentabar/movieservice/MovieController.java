package ro.pentalog.pentabar.movieservice;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.pentalog.pentabar.movieservice.feign.ReviewsFeignClient;
import ro.pentalog.pentabar.movieservice.model.MovieDTO;
import ro.pentalog.pentabar.movieservice.model.MovieReview;
import ro.pentalog.pentabar.movieservice.repository.Movie;
import ro.pentalog.pentabar.movieservice.repository.MovieRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("movies")
public class MovieController {
    private final MovieRepository movieRepository;
    private final ReviewsFeignClient reviewsFeignClient;

    public MovieController(MovieRepository movieRepository, ReviewsFeignClient reviewsFeignClient) {
        this.movieRepository = movieRepository;
        this.reviewsFeignClient = reviewsFeignClient;
    }

    @GetMapping
    public ResponseEntity<List<MovieDTO>> getMovies() {
        List<Movie> movies = movieRepository.findAll();
        List<MovieDTO> movieDTOS = movies.stream().map(movie -> {
            CollectionModel<MovieReview> movieReviews = reviewsFeignClient.getMovieReviews(movie.getId());
            return new MovieDTO(movie, movieReviews.getContent());
        }).collect(Collectors.toList());
        return ResponseEntity.ok(movieDTOS);
    }

    @GetMapping("/{movieID}")
    public ResponseEntity<MovieDTO> getMovie(@PathVariable("movieID") Long movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new EntityNotFoundException("Movie not found"));
        CollectionModel<MovieReview> movieReviews = reviewsFeignClient.getMovieReviews(movieId);
        return ResponseEntity.ok(new MovieDTO(movie, movieReviews.getContent()));
    }
}
