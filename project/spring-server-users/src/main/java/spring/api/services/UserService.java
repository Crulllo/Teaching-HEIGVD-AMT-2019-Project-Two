package spring.api.services;

import spring.api.exceptions.ApiException;
import spring.api.exceptions.AuthenticationException;
import spring.api.exceptions.BadRequestException;
import spring.api.exceptions.NotFoundException;
import spring.model.JwtToken;
import spring.model.ProfileUpdate;
import spring.model.User;

import java.net.URI;
import java.util.List;

public interface UserService {

    /**
     * Convert to entity and save to db.
     * @param user the user dto
     * @return Uri location of the newly created user resource in the api
     * @throws BadRequestException
     */
    URI saveUser(User user) throws BadRequestException;

    /**
     * Delete user from db by username
     * @param username username of the user
     */
    void deleteUser(String username) throws NotFoundException;

    /**
     * Get all user in db
     * @return the list of users
     * @throws NotFoundException if no users found
     */
    List<User> getAllUsers() throws NotFoundException;

    /**
     * Get a user by his username
     * @param username username of the user
     * @return user
     * @throws NotFoundException if no user with username provided
     */
    User getUserById(String username) throws NotFoundException;

    /**
     * Authenticate user with credentials provided and return jwt token.
     * @param user the user dto containing his credentials
     * @return the jwt token when authenticated
     * @throws NotFoundException user was not found
     * @throws AuthenticationException if credentials could not be verified
     */
    JwtToken authenticateUser(User user) throws ApiException;

    /**
     * CHange password of user.
     * @param username of user
     * @param newPassword new password of user
     */
    void changePassword(String username, String newPassword) throws NotFoundException, BadRequestException;

    /**
     * Update user's profile instance
     * @param username username of the user
     * @param profileUpdate the profile info updates
     * @throws NotFoundException if no user with username was found
     * @throws BadRequestException if profile update was badly constructed
     */
    void updateProfile(String username, ProfileUpdate profileUpdate) throws NotFoundException, BadRequestException;

    /**
     * Method reserved only for the super user system administrator. Used to initialize the admin entity in the db.
     */
    void makeAdmin();
}
