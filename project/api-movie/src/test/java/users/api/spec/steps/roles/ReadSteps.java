package users.api.spec.steps.roles;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import movies.ApiException;
import movies.api.RolesApi;
import movies.api.dto.Role;
import users.api.spec.helpers.Environment;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class ReadSteps {
    private Environment environment;
    private RolesApi rolesApi;

    private int pageSizeTest = 5;

    public ReadSteps(Environment environment) {
        this.environment = environment;
        this.rolesApi = this.environment.getRolesApi();
    }

    @When("^I GET all roles by movie$")
    public void iGETAllRolesByMovie() {
        /*
        try {
            this.environment.setLastApiResponse(this.rolesApi.);
            this.environment.setLastApiCallThrewException(false);
            this.environment.setLastApiException(null);
            this.environment.setLastStatusCode(this.environment.getLastApiResponse().getStatusCode());
        } catch (ApiException e) {
            this.environment.setLastApiResponse(null);
            this.environment.setLastApiCallThrewException(true);
            this.environment.setLastApiException(e);
            this.environment.setLastStatusCode(this.environment.getLastApiException().getCode());
        }
         */
    }

    @When("^I GET all roles by actor$")
    public void iGETAllRolesByActor() {
        /*
        try {
            this.environment.setLastApiResponse(this.rolesApi.);
            this.environment.setLastApiCallThrewException(false);
            this.environment.setLastApiException(null);
            this.environment.setLastStatusCode(this.environment.getLastApiResponse().getStatusCode());
        } catch (ApiException e) {
            this.environment.setLastApiResponse(null);
            this.environment.setLastApiCallThrewException(true);
            this.environment.setLastApiException(e);
            this.environment.setLastStatusCode(this.environment.getLastApiException().getCode());
        }
         */
    }

    @Then("^I receive a list of roles$")
    public void iReceiveAListOfRoles() {
        assertNotNull(this.environment.getLastApiResponse().getData());
        List<Role> roles = new ArrayList<>((List<Role>) this.environment.getLastApiResponse().getData());
        assertFalse(roles.isEmpty());
        assertEquals(roles.get(0).getRolename(), "principal");
    }

    @When("^I GET all roles with pagination parameters$")
    public void iGETAllRolesWithPaginationParameters() {
        /*
        try {
            this.environment.setLastApiResponse(this.rolesApi.);
            this.environment.setLastApiCallThrewException(false);
            this.environment.setLastApiException(null);
            this.environment.setLastStatusCode(this.environment.getLastApiResponse().getStatusCode());
        } catch (ApiException e) {
            this.environment.setLastApiResponse(null);
            this.environment.setLastApiCallThrewException(true);
            this.environment.setLastApiException(e);
            this.environment.setLastStatusCode(this.environment.getLastApiException().getCode());
        }
        */
    }

    @Then("^I receive a list of roles with the specified pagination size$")
    public void iReceiveAListOfRolesWithTheSpecifiedPaginationSize() {
        assertNotNull(this.environment.getLastApiResponse().getData());
        List<Role> roles = new ArrayList<>((List<Role>) this.environment.getLastApiResponse().getData());
        assertFalse(roles.isEmpty());
        assertTrue(roles.size() <= pageSizeTest);
    }
}
