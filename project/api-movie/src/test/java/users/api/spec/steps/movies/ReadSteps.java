package users.api.spec.steps.movies;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import movies.ApiException;
import movies.api.MoviesApi;
import movies.api.dto.Movie;
import users.api.spec.helpers.Environment;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class ReadSteps {
    private Environment environment;
    private MoviesApi moviesApi;

    private int pageSizeTest = 5;

    public ReadSteps(Environment environment) {
        this.environment = environment;
        this.moviesApi = this.environment.getMoviesApi();
    }

    @When("^I FIND it in the /movies/moviesId endpoint$")
    public void iFindItInTheMoviesMoviesIdEndpoint() {
        try {
            this.environment.setLastApiResponse(this.moviesApi.findMovieByIdWithHttpInfo(this.environment.retrieveLastCreatedResourceId()));
            this.environment.setLastApiCallThrewException(false);
            this.environment.setLastApiException(null);
            this.environment.setLastStatusCode(this.environment.getLastApiResponse().getStatusCode());
        } catch (ApiException e) {
            this.environment.setLastApiResponse(null);
            this.environment.setLastApiCallThrewException(true);
            this.environment.setLastApiException(e);
            this.environment.setLastStatusCode(this.environment.getLastApiException().getCode());
        }
    }

    @When("^I GET all movies$")
    public void iGETAllMovies() {
        try {
            this.environment.setLastApiResponse(this.moviesApi.getMoviesWithHttpInfo(0, 100));
            this.environment.setLastApiCallThrewException(false);
            this.environment.setLastApiException(null);
            this.environment.setLastStatusCode(this.environment.getLastApiResponse().getStatusCode());
        } catch (ApiException e) {
            this.environment.setLastApiResponse(null);
            this.environment.setLastApiCallThrewException(true);
            this.environment.setLastApiException(e);
            this.environment.setLastStatusCode(this.environment.getLastApiException().getCode());
        }
    }

    @Then("^I receive a list of movies$")
    public void iReceiveAListOfMovies() {
        assertNotNull(this.environment.getLastApiResponse().getData());
        List<Movie> movies = new ArrayList<>((List<Movie>) this.environment.getLastApiResponse().getData());
        assertFalse(movies.isEmpty());
        assertEquals(movies.get(0).getTitle(), "The Movie");
    }

    @When("^I GET all movies with pagination parameters$")
    public void iGETAllMoviesWithPaginationParameters() {
        try {
            this.environment.setLastApiResponse(this.moviesApi.getMoviesWithHttpInfo(0, pageSizeTest));
            this.environment.setLastApiCallThrewException(false);
            this.environment.setLastApiException(null);
            this.environment.setLastStatusCode(this.environment.getLastApiResponse().getStatusCode());
        } catch (ApiException e) {
            this.environment.setLastApiResponse(null);
            this.environment.setLastApiCallThrewException(true);
            this.environment.setLastApiException(e);
            this.environment.setLastStatusCode(this.environment.getLastApiException().getCode());
        }
    }

    @Then("^I receive a list of movies with the specified pagination size$")
    public void iReceiveAListOfMoviesWithTheSpecifiedPaginationSize() {
        assertNotNull(this.environment.getLastApiResponse().getData());
        List<Movie> movies = new ArrayList<>((List<Movie>) this.environment.getLastApiResponse().getData());
        assertFalse(movies.isEmpty());
        assertTrue(movies.size() <= pageSizeTest);
    }
}
