## Structure
Our project exposes 2 rest apis: user-api for all that is creation of users, authentications, getting jwt tokens and the other is the actual movies api.

## Functionnalities
### Admin
An admin can get all users, create a user, delete a user, get a user based on his username and block / unblock a user. A blocked user cannot access the api.
### User
A user can registrate, authenticate, change his password and update his profile.
### Movie
A user can create a movie, delete a movie, update a movie, get the movies he created, find a movie based on his id and get all roles of a given movie.
### Actor
A user can create an actor, delete an actor, update an actor, get the actors he created, find an actor based on his id, get all roles of a given actor, create a role and fire an actor from his role in a movie.
