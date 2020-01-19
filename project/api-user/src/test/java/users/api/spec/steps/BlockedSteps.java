package users.api.spec.steps;

import cucumber.api.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import users.ApiException;
import users.api.AdminApi;
import users.api.UserApi;
import users.api.spec.helpers.Environment;

public class BlockedSteps {

    private static Logger log = LoggerFactory.getLogger(CreationSteps.class);

    private Environment environment;
    private UserApi userApi;
    private AdminApi adminApi;

    public BlockedSteps(Environment environment) {
        this.environment = environment;
        this.userApi = this.environment.getUserApi();
        this.adminApi = this.environment.getAdminApi();
    }

    @When("^admin blocks user$")
    public void i_POST_it_to_the_users_endpoint() throws Throwable {
        try {
            this.environment.setLastApiResponse(
                    this.adminApi.updateUserBlockedStatusWithHttpInfo(this.environment.getTestUser()
                            .getUsername(), true));
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
