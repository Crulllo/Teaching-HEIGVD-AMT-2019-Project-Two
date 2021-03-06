package users.api.spec.helpers;

import movies.ApiException;
import movies.ApiResponse;
import movies.api.ActorsApi;
import movies.api.MoviesApi;
import movies.api.RolesApi;
import movies.api.dto.Actor;
import movies.api.dto.Movie;
import movies.api.dto.Role;
import okhttp3.OkHttpClient;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class Environment {
    private MoviesApi moviesApi = new MoviesApi();
    private ActorsApi actorsApi = new ActorsApi();
    private RolesApi rolesApi = new RolesApi();

    private OkHttpClient client = new OkHttpClient();

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;

    private String testUserOwner = "ownerUser";
    private String lastToken;

    private Movie testMovie;
    private Actor testActor;
    private Role testRole;

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

    public RolesApi getRolesApi() {
        return rolesApi;
    }

    public ApiResponse getLastApiResponse() {
        return lastApiResponse;
    }

    public void setLastApiResponse(ApiResponse lastApiResponse) {
        this.lastApiResponse = lastApiResponse;
    }

    public ApiException getLastApiException() {
        return lastApiException;
    }

    public void setLastApiException(ApiException lastApiException) {
        this.lastApiException = lastApiException;
    }

    public boolean isLastApiCallThrewException() {
        return lastApiCallThrewException;
    }

    public void setLastApiCallThrewException(boolean lastApiCallThrewException) {
        this.lastApiCallThrewException = lastApiCallThrewException;
    }

    public int getLastStatusCode() {
        return lastStatusCode;
    }

    public void setLastStatusCode(int lastStatusCode) {
        this.lastStatusCode = lastStatusCode;
    }

    public String getTestUserOwner() {
        return testUserOwner;
    }

    public OkHttpClient getHttpClient() {
        return client;
    }

    public String getLastToken() {
        return lastToken;
    }

    public void setLastToken(String lastToken) {
        this.lastToken = lastToken;
    }

    public Movie getMovie() {
        return testMovie;
    }

    public void setTestMovie(Movie testMovie) {
        this.testMovie = testMovie;
    }

    public Actor getActor() {
        return testActor;
    }

    public void setTestActor(Actor testActor) {
        this.testActor = testActor;
    }

    public Role getRole() {
        return testRole;
    }

    public void setTestRole(Role testRole) {
        this.testRole = testRole;
    }

    public Long retrieveLastCreatedResourceId() {
        List<String> result = (List<String>) this.lastApiResponse.getHeaders().get("location");
        String location = result.get(0);
        return Long.parseLong(location.substring(location.length()-1));
    }
}
