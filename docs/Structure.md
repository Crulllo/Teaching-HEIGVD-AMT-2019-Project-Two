## Structure
Our project exposes 2 rest apis: user-api for all that is user management and authentications, and the movies api which handles the movies, actors and roles.
Each has its own project of course. For the user api, the specification and the cucumber tests are done in `api-user` module, while the spring server is in `spring-server-users`. Similarly, the movies spec and cucumber tests are done in `api-movie` and the spring server in `spring-server-movies`.

As for the deployment we have a docker topology in the `docker` folder. The docker compose launches the user server container listening on port 6060, the spring server container listening on port 8081, the mysql database listening on 3306, the phpmyadmin console on port 5506 and the Traefik container on port 2020 for routing.

**Note1** We've had an issue in the launch order of the server containers, that's why their docker images include a `wait_for_it` script that waits for the selected db to launch completely.

**Note2** We've had issues with Traefik, it seems that it recognizes our docker containers if go to the console on `localhost:2020` but we still can't have it route our containers to the localhost with the selected prefixes we0ve set in the docker compose.