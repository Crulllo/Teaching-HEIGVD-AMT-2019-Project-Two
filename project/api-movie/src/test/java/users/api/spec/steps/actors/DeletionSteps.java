package users.api.spec.steps.actors;

import cucumber.api.java.en.When;
import movies.ApiException;
import movies.api.ActorsApi;
import users.api.spec.helpers.Environment;

public class DeletionSteps {
    private Environment environment;
    private ActorsApi actorsApi;

    public DeletionSteps(Environment environment) {
        this.environment = environment;
        this.actorsApi = this.environment.getActorsApi();
    }


    @When("^I DELETE it in the /actors/actorsId endpoint$")
    public void iDELETEItInTheActorsActorsIdEndpoint() {
        try {
            this.environment.setLastApiResponse(this.actorsApi.deleteActorWithHttpInfo(this.environment.retrieveLastCreatedResourceId()));
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
