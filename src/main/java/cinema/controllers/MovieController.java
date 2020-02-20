package cinema.controllers;

import cinema.dto.MovieRequestDto;
import cinema.dto.MovieResponseDto;
import cinema.model.Movie;
import cinema.service.MovieService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {
    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping(value = "/add")
    public Movie add(@RequestBody MovieRequestDto movieRequestDto) {
        Movie movie = new Movie();
        movie.setTitle(movieRequestDto.getTitle());
        movie.setDescription(movieRequestDto.getDescription());
        return movieService.add(movie);
    }

    @GetMapping(value = "/all")
    public List<MovieResponseDto> getAllMovies() {
        return movieService.getAll().stream()
                .map(movie -> getMovieDto(movie))
                .collect(Collectors.toList());
    }

    private MovieResponseDto getMovieDto(Movie movie) {
        MovieResponseDto movieResponseDto = new MovieResponseDto();
        movieResponseDto.setDescription(movie.getDescription());
        movieResponseDto.setTitle(movie.getTitle());
        return movieResponseDto;
    }

}
