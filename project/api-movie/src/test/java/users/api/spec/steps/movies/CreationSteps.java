package users.api.spec.steps.movies;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import movies.ApiException;
import movies.api.MoviesApi;
import movies.api.dto.Movie;
import okhttp3.*;
import users.api.spec.helpers.Environment;
import util.JwtUtil;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CreationSteps {

    // To format the request body we send to the authentications endpoint in order to authenticate the user.
    private static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    private Environment environment;
    private MoviesApi moviesApi;

    private OkHttpClient client;
    private Gson gson;

    public CreationSteps(Environment environment) {
        this.environment = environment;
        this.moviesApi = this.environment.getMoviesApi();
        this.client = this.environment.getHttpClient();

        this.gson = new Gson();
    }
    @Given("^there is an application server$")
    public void there_is_a_Users_server() throws Throwable {
        assertNotNull(moviesApi);
    }

    @Given("^I have a jwt token$")
    public void iHaveAJwtToken() throws ApiException {
        String randomUsername = "user" + System.currentTimeMillis();
        //this.environment.setLastToken(getJwtTokenByAuthenticatingToUsersApi("user1"));
        this.environment.setLastToken(new JwtUtil().createToken(randomUsername, false, false));
        this.moviesApi.getApiClient().setApiKey("Bearer " + this.environment.getLastToken());
    }

    @Given("^I have a valid movie payload$")
    public void iHaveAMoviePayload() {
        this.environment.setTestMovie(new Movie());
        this.environment.getMovie().setTitle("The Movie");
    }

    @When("^I POST it to the /movies endpoint$")
    public void iPOSTItToTheMoviesEndpoint() {
        try {
            this.environment.setLastApiResponse(this.moviesApi.createMovieWithHttpInfo(this.environment.getMovie()));
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

    @When("^I UPDATE it in the /movies/moviesId endpoint$")
    public void iUPDATEItInTheMoviesMoviesIdEndpoint() {
        try {
            this.environment.setLastApiResponse(this.moviesApi.updateMovieWithHttpInfo(this.environment.retrieveLastCreatedResourceId(), this.environment.getMovie()));
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

    @Then("^I receive a (\\d+) status code$")
    public void iReceiveAStatusCode(int expectedCode) {
        assertEquals(expectedCode, this.environment.getLastStatusCode());
    }

    /**
     * Get the jwt token by authenticating directly to the users api server.
     * @param username username
     * @return jwt token when authenticated
     * @throws ApiException if couldn't authenticate
     */
    private String getJwtTokenByAuthenticatingToUsersApi(String username) throws ApiException {
        RequestBody body = RequestBody.create("{\"username\": \"" +
                username + "\", \"password\":\"password\"}", JSON);
        Request request = new Request.Builder()
                .url("http://localhost:6060/api/authentications/")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String jwtToken = response.body().string();
            JsonObject jsonObject = gson.fromJson(jwtToken, JsonObject.class);
            return jsonObject.get("token").getAsString();
        } catch (NullPointerException | IOException e) {
            throw new ApiException("Could not retrieve jwt token from the authentications endpoint");
        }
    }
}
