package users.api.spec.steps.roles;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import movies.ApiException;
import movies.api.RolesApi;
import movies.api.dto.Actor;
import movies.api.dto.Movie;
import movies.api.dto.Role;
import okhttp3.*;
import users.api.spec.helpers.Environment;

import static org.junit.Assert.assertEquals;

public class CreationSteps {

    // To format the request body we send to the authentications endpoint in order to authenticate the user.
    private static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    private Environment environment;
    private RolesApi rolesApi;

    private OkHttpClient client;

    Role role;

    public CreationSteps(Environment environment) {
        this.environment = environment;
        this.rolesApi = this.environment.getRolesApi();
        this.client = this.environment.getHttpClient();
    }

    @Given("^I have a valid role payload$")
    public void iHaveAValidRolePayload() {
        this.environment.setTestRole(new Role());
        this.environment.getRole().setRolename("principal");

        this.environment.setTestMovie(new Movie());
        this.environment.getMovie().setTitle("The Movie");
        this.environment.getRole().setMovieId(this.environment.retrieveLastCreatedResourceId());

        this.environment.setTestActor(new Actor());
        this.environment.getActor().setFirstname("Bradley");
        this.environment.getActor().setLastname("Pitts");
        this.environment.getActor().setExpertise(Actor.ExpertiseEnum.FILM);
        this.environment.getRole().setActorId(this.environment.retrieveLastCreatedResourceId());
    }

    @When("^I POST it to the /roles endpoint$")
    public void iPOSTItToTheRolesEndpoint() {
        try {
            this.environment.setLastApiResponse(this.rolesApi.createRoleWithHttpInfo(this.role));
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
}
