package pe.com.jdmm21.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pe.com.jdmm21.springsecurity.model.Movie;

@Controller
public class SecuredController {
    @Value("${movie.base-uri}")
    private String movieApiBaseUri;

    @Autowired
    @Qualifier("movieAppRestTemplate")
    private OAuth2RestTemplate movieAppRestTemplate;

    @RequestMapping(value = "/movie", method = RequestMethod.GET)
    public String root() {
        return "redirect:/movie/index";
    }

    @ResponseBody
    @RequestMapping(value = "movie/index", method = RequestMethod.GET)
    public Movie[] index() {
        return movieAppRestTemplate.getForObject(movieApiBaseUri, Movie[].class);
    }


}
