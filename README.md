# Teaching-HEIGVD-AMT-2019-Project-Two

## Crüll Loris, Selim Stéphane

### Deployment
Our application is deployed in docker containers using a docker compose. For convenience, we put a script `execute_project_rest.sh` that will execute the application directly. It will build the maven projects and run the docker containers and after it's done will perform a docker prune of the system and the volumes to clean up the system and free up some memory.
### Functionnal aspects
### [Structure](docs/Structure.md)
### [Model](docs/Model.md)
### [Context](docs/Context.md)

### Implementation
The implementation of most of the api was done through the swagger open api spec and the springfox code generation. That's why if you go to the home page of our apis (the context path `/api` of our docker containers) you will find the swagger documentation of that api.

The implementation went through multiple phases of a combination of top down and bottom up. Top down means we use the open api spec to generate the skeleton of our controllers in our target folders, and we implement them after. The bottom up was when we focused on the controller functionality and adapted the specification to it. We went through that process again when we did the cucumber tests.
### [Users](docs/implementation/api-users-implementation/implementation-users.md)
### [Movies](docs/implementation/api-movies-implementation/implementation-movies.md)

### Testing
### [PerformanceTests](docs/PerformanceTests.md)

### Known bugs and limitations
