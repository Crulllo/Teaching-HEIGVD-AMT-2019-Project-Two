package users.api.spec.steps.roles;

import cucumber.api.java.en.When;
import movies.ApiException;
import movies.api.RolesApi;
import users.api.spec.helpers.Environment;

public class DeletionSteps {
    private Environment environment;
    private RolesApi rolesApi;

    public DeletionSteps(Environment environment) {
        this.environment = environment;
        this.rolesApi = this.environment.getRolesApi();
    }

    @When("^I DELETE it in the /roles/rolesId endpoint$")
    public void iDELETEItInTheRolesRolesIdEndpoint() {
        /*
        try {
            this.environment.setLastApiResponse(this.rolesApi.fireActorWithHttpInfo(this.environment.retrieveLastCreatedResourceId()));
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
}
