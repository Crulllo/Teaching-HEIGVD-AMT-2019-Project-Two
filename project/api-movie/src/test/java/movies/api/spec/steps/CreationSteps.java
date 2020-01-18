package movies.api.spec.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import movies.ApiException;
import movies.ApiResponse;
import movies.api.MoviesApi;
import movies.api.dto.Movie;
import movies.api.spec.helpers.Environment;
import okhttp3.OkHttpClient;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import static org.junit.Assert.*;

public class CreationSteps {

    private static Logger log = LoggerFactory.getLogger(CreationSteps.class);

    private Environment environment;
    private MoviesApi api;

    OkHttpClient client;

    public CreationSteps(Environment environment) {
        this.environment = environment;
        this.api = environment.getMoviesApi();
        this.client = environment.getHttpClient();
    }

    @Given("^there is a Movies server$")
    public void there_is_a_Movies_server() throws Throwable {
        assertNotNull(api);
    }

    /*
    @Given("^I have a user payload with username and password$")
    public void iHaveAUserPayloadWithUsernameAndPassword() {
        User user = new users.api.dto.User();
        String timestamp = String.valueOf(System.currentTimeMillis());
        user.setUsername("testUser" + timestamp);
        user.setPassword("testPassword");
        this.environment.setTestUser(user);
    }

    @When("^I POST it to the /registrations endpoint$")
    public void i_POST_it_to_the_users_endpoint() throws Throwable {
        try {
            this.environment.setLastApiResponse(api.registerUserWithHttpInfo(environment.getTestUser()));
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
    public void i_receive_a_status_code(int expectedCode) throws Throwable {
        assertEquals(expectedCode, this.environment.getLastStatusCode());
    }
    */
}
