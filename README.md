# Teaching-HEIGVD-AMT-2019-Project-Two

## Crüll Loris, Selim Stéphane

### Deployment
Our application is deployed in docker containers using a docker compose. For convenience, we put a script `execute_project_rest.sh` that will execute the application directly. It will build the maven projects and run the docker containers and after it's done will perform a docker prune of the system and the volumes to clean up the system and free up some memory.

### Functionnal aspects
### [Context](docs/Context.md)
### [Structure](docs/Structure.md)
### [Model](docs/Model.md)

### Implementation
### [Users](docs/implementation/api-users-implementation/implementation-users.md)
### [Movies](docs/implementation/api-movies-implementation/implementation-movies.md)

### Testing
### [PerformanceTests](docs/PerformanceTests.md)

### Known bugs and limitations
