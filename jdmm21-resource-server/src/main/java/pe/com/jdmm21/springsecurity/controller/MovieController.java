package pe.com.jdmm21.springsecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pe.com.jdmm21.springsecurity.model.Movie;

import javax.annotation.PostConstruct;

@RestController
public class MovieController {
    public static Movie[] movies = new Movie[4];

    @PostConstruct
    public void initIt() {
        movies[0] = new Movie(1L, "MOVIE1", "GENRE1");
        movies[1] = new Movie(2L, "MOVIE2", "GENRE1");
        movies[2] = new Movie(3L, "MOVIE3", "GENRE1");
        movies[3] = new Movie(4L, "MOVIE4", "GENRE1");
    }

    @RequestMapping(value = "/movie1", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("#oauth2.hasScope('movie') and #oauth2.hasScope('read')")
    public String[] getMovie() {
        return new String[]{
                "Movie x1", "Movie x2", "Movie x3"
        };
    }

    @RequestMapping(value = "/movie", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("#oauth2.hasScope('movie') and #oauth2.hasScope('read')")
    public Movie[] getMovies() {
        initIt();
        return movies;
    }
}
