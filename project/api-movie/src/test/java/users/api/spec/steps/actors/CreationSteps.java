package users.api.spec.steps.actors;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import movies.ApiException;
import movies.api.ActorsApi;
import movies.api.dto.Actor;
import okhttp3.*;
import users.api.spec.helpers.Environment;

import static org.junit.Assert.assertEquals;

public class CreationSteps {

    // To format the request body we send to the authentications endpoint in order to authenticate the user.
    private static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");


    private Environment environment;
    private ActorsApi actorsApi;

    private OkHttpClient client;

    Actor actor;

    public CreationSteps(Environment environment) {
        this.environment = environment;
        this.actorsApi = this.environment.getActorsApi();
        this.client = this.environment.getHttpClient();
    }

    @Given("^I have a valid actor payload$")
    public void iHaveAValidActorPayload() {
        this.environment.setTestActor(new Actor());
        this.environment.getActor().setFirstname("Bradley");
        this.environment.getActor().setLastname("Pitts");
        this.environment.getActor().setExpertise(Actor.ExpertiseEnum.FILM);
    }

    @When("^I POST it to the /actors endpoint$")
    public void iPOSTItToTheActorsEndpoint() {
        try {
            this.environment.setLastApiResponse(this.actorsApi.createActorWithHttpInfo(this.actor));
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

    @When("^I UPDATE it in the /actors/actorsId endpoint$")
    public void iUPDATEItInTheActorsActorsIdEndpoint() {
        try {
            this.environment.setLastApiResponse(this.actorsApi.updateActorWithHttpInfo(this.environment.retrieveLastCreatedResourceId(), this.environment.getActor()));
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
}
