# Model

## api-user
![user](https://github.com/Crulllo/Teaching-HEIGVD-AMT-2019-Project-Two/blob/master/docs/api-user.png)

## api-movie
![movie](https://github.com/Crulllo/Teaching-HEIGVD-AMT-2019-Project-Two/blob/master/docs/api-movie.png)

## Data transfer Objects
Data transfer objects are the data we transfer in our apis. They only contain the relevant information we need from our entities.

For example, the User DTO has only the username, firstname, lastname email and hashed password but we don't need to know that a user is admin or blocked, these are for implementation purposes.

## Spring data Repository
A quick note on the relationship: we don't use a many to many relationship between movies and actors. We preferred a oneToMany from each side so that the role can have its own id, and that way we don't have the limitation that an actor can only have one role in a fil.
