package cinema.service.impl;

import cinema.dao.MovieDao;
import cinema.exceptions.DataProcessingException;
import cinema.lib.Inject;
import cinema.lib.Service;
import cinema.model.Movie;
import cinema.service.MovieService;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Inject
    private static MovieDao movieDao;

    @Override
    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    @Override
    public List<Movie> getAll() throws DataProcessingException {
        return movieDao.getAll();
    }
}
