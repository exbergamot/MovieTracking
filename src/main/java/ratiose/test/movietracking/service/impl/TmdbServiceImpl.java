package ratiose.test.movietracking.service.impl;

import com.omertron.themoviedbapi.MovieDbException;
import com.omertron.themoviedbapi.methods.TmdbDiscover;
import com.omertron.themoviedbapi.methods.TmdbSearch;
import com.omertron.themoviedbapi.model.discover.Discover;
import com.omertron.themoviedbapi.model.movie.MovieBasic;
import com.omertron.themoviedbapi.model.movie.MovieInfo;
import com.omertron.themoviedbapi.model.person.PersonFind;
import com.omertron.themoviedbapi.tools.HttpTools;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Service;
import ratiose.test.movietracking.service.TmdbService;

import java.time.LocalDate;
import java.util.List;

/**
 * Service responsible for communication with tmdb
 */
@Service
public class TmdbServiceImpl implements TmdbService {

    private static final String API_KEY = "7c6b7ba0389446b1f5c0b31b56c8a556";
    private TmdbSearch search;
    private TmdbDiscover tmdbDiscover;

    public TmdbServiceImpl() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpTools httpTools = new HttpTools(httpClient);
        search = new TmdbSearch(API_KEY, httpTools);
        tmdbDiscover = new TmdbDiscover(API_KEY, httpTools);
    }

    /**
     * Get first page of person data from tmdb by given query
     * @param query - search query
     * @return first page of persons that satisfy given query
     * @throws MovieDbException
     */
    @Override
    public List<PersonFind> findPersonByQuery(String query) throws MovieDbException {
        return findPersonByQuery(query, 1);
    }

    /**
     * Get specified page of person data from tmdb by given query
     * @param query - search query
     * @param page -page that should be returned
     * @return specified page of persons that satisfies given query
     * @throws MovieDbException
     */
    @Override
    public List<PersonFind> findPersonByQuery(String query, int page) throws MovieDbException {
        return search.searchPeople(query, page, false, null)
                .getResults();
    }

    @Override
    public List<MovieInfo> findMovieByQuery(String query) throws MovieDbException {
        return findMovieByQuery(query, 1);
    }

    /**
     * Get first page of person data from tmdb by given query
     * @param query - search query
     * @return first page of persons that satisfy given query
     * @throws MovieDbException
     */
    @Override
    public List<MovieInfo> findMovieByQuery(String query, int page) throws MovieDbException {
        return search.searchMovie(query, page, "en", false, null, null, null)
                .getResults();
    }

    @Override
    public List<MovieBasic> discoverMovies(List<Long> actors, Integer month, Integer year) throws MovieDbException {
        if (actors.isEmpty()) {
            throw new IllegalArgumentException("To get correct recommendation please add at least one favourite actor");
        }
        Discover discover = createDiscoverObject(actors, month, year);
        return tmdbDiscover.getDiscoverMovies(discover).getResults();
    }

    private Discover createDiscoverObject(List<Long> actors, Integer month, Integer year) {
        LocalDate from = LocalDate.of(year, month, 1);
        LocalDate to = from.plusMonths(1).minusDays(1);
        Discover discover = new Discover();
        discover.withPeople(composeActorsString(actors));
        discover.releaseDateGte(from.toString());
        discover.releaseDateLte(to.toString());
        return discover;
    }

    private String composeActorsString(List<Long> actors) {
        StringBuilder builder = new StringBuilder();
        actors.forEach(each -> {
            builder.append(each);
            builder.append(',');
        });
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }
}
