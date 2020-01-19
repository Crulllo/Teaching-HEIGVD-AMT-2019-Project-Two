# Model

## api-user
![user](https://github.com/Crulllo/Teaching-HEIGVD-AMT-2019-Project-Two/blob/master/docs/api-user.png)<br>
A user has a username used to identify him, a password used to access his account and an email. He also has a full name composed by his firstname and lastname. He can be an admin and if so, he can block / unblock others users.

## api-movie
![movie](https://github.com/Crulllo/Teaching-HEIGVD-AMT-2019-Project-Two/blob/master/docs/api-movie.png)<br>
An actor has an id, a firstname and a lastname. Expertise can be theater, television or film. He also has an owner.<br>
A movie has an id, a title, a director and a studio. It also has an amount used to produce it, an amount corresponding to its revenue, a rating and an owner.<br>
An actor can play in multiples movies and a movie can have multiple actors in it. A role indicates which actor acted in which movie. It has his own id, the id of the actor and the movie, a name, the number of awards he got and if he has been awarded or not.

## Data transfer Objects
Data transfer objects are the data we transfer in our apis. They only contain the relevant information we need from our entities.

For example, the User DTO has only the username, firstname, lastname email and hashed password but we don't need to know that a user is admin or blocked, these are for implementation purposes.

## Spring data Repository
A quick note on the relationship: we don't use a many to many relationship between movies and actors. We preferred a oneToMany from each side so that the role can have its own id, and that way we don't have the limitation that an actor can only have one role in a fil.
