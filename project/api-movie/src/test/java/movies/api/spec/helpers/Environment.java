package movies.api.spec.helpers;

import movies.ApiException;
import movies.ApiResponse;
import movies.api.ActorsApi;
import movies.api.MoviesApi;
import movies.api.dto.Actor;
import movies.api.dto.Movie;
import okhttp3.OkHttpClient;

import java.io.IOException;
import java.util.Properties;

public class Environment {
    private MoviesApi moviesApi = new MoviesApi();
    private ActorsApi actorsApi = new ActorsApi();

    private OkHttpClient client = new OkHttpClient();

    private Movie testMovie;
    private Actor testActor;

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;

    public Environment() throws IOException {
        Properties properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream("environment.properties"));
        String url = properties.getProperty("movies.server.url");
        moviesApi.getApiClient().setBasePath(url);
        actorsApi.getApiClient().setBasePath((url));
    }

    public MoviesApi getMoviesApi() {
        return moviesApi;
    }

    public ActorsApi getActorsApi() {
        return actorsApi;
    }

    public OkHttpClient getHttpClient() {
        return client;
    }

    public ApiException getLastApiException() {
        return lastApiException;
    }

    public ApiResponse getLastApiResponse() {
        return lastApiResponse;
    }

    public int getLastStatusCode() {
        return lastStatusCode;
    }

    public boolean isLastApiCallThrewException() {
        return lastApiCallThrewException;
    }

    public void setLastApiResponse(ApiResponse lastApiResponse) {
        this.lastApiResponse = lastApiResponse;
    }

    public void setLastApiException(ApiException lastApiException) {
        this.lastApiException = lastApiException;
    }

    public void setLastApiCallThrewException(boolean lastApiCallThrewException) {
        this.lastApiCallThrewException = lastApiCallThrewException;
    }

    public void setLastStatusCode(int lastStatusCode) {
        this.lastStatusCode = lastStatusCode;
    }

    public Movie getTestMovie() {
        return testMovie;
    }

    public void setTestMovie(Movie testMovie) {
        this.testMovie = testMovie;
    }

    public Actor getTestActor() {
        return testActor;
    }

    public void setTestActor(Actor testActor) {
        this.testActor = testActor;
    }
}
