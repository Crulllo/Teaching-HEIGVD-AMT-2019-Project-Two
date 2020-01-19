package users.api.spec.steps.actors;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import movies.ApiException;
import movies.api.ActorsApi;
import movies.api.dto.Actor;
import users.api.spec.helpers.Environment;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class ReadSteps {
    private Environment environment;
    private ActorsApi actorsApi;

    private int pageSizeTest = 5;

    public ReadSteps(Environment environment) {
        this.environment = environment;
        this.actorsApi = this.environment.getActorsApi();
    }

    @When("^I FIND it in the /actors/actorsId endpoint$")
    public void iFindItInTheActorsActorsIdEndpoint() {
        try {
            this.environment.setLastApiResponse(this.actorsApi.findActorByIdWithHttpInfo(this.environment.retrieveLastCreatedResourceId()));
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

    @When("^I GET all actors$")
    public void iGETAllActors() {
        try {
            this.environment.setLastApiResponse(this.actorsApi.getActorsWithHttpInfo(0, 100));
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

    @Then("^I receive a list of actors$")
    public void iReceiveAListOfActors() {
        assertNotNull(this.environment.getLastApiResponse().getData());
        List<Actor> actors = new ArrayList<>((List<Actor>) this.environment.getLastApiResponse().getData());
        assertFalse(actors.isEmpty());
        assertEquals(actors.get(0).getFirstname(), "Bradley");
        assertEquals(actors.get(0).getLastname(), "Pitts");
        assertEquals(actors.get(0).getExpertise(), Actor.ExpertiseEnum.FILM);
    }

    @When("^I GET all actors with pagination parameters$")
    public void iGETAllActorsWithPaginationParameters() {
        try {
            this.environment.setLastApiResponse(this.actorsApi.getActorsWithHttpInfo(0, pageSizeTest));
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

    @Then("^I receive a list of actors with the specified pagination size$")
    public void iReceiveAListOfActorsWithTheSpecifiedPaginationSize() {
        assertNotNull(this.environment.getLastApiResponse().getData());
        List<Actor> actors = new ArrayList<>((List<Actor>) this.environment.getLastApiResponse().getData());
        assertFalse(actors.isEmpty());
        assertTrue(actors.size() <= pageSizeTest);
    }
}
