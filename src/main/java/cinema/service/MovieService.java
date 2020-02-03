package cinema.service;

import cinema.exceptions.DataProcessingException;
import cinema.model.Movie;

import java.util.List;

public interface MovieService {
    Movie add(Movie movie);

    List<Movie> getAll() throws DataProcessingException;
}
