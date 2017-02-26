package com.company.controllers;

import com.company.entities.User;
import com.company.exceptions.IdsNotMatchException;
import com.company.requests.NewUserForm;
import com.company.requests.UpdateUserForm;
import com.company.services.UserService;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

/**
 * @user siti2017
 * @date 14/02/17
 * <p>
 * GET     /users/       return all users
 * POST    /users/       add a new user
 * GET     /users/{id}   return a specific user
 * PUT     /users/{id}   update a specific user
 * DELETE  /users/{id}   delete a specific user
 */
@RestController
@RequestMapping(value = "/users")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserController {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Qualifier("mysql")
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<User> paged(Pageable pageable) {
        return userService.findAll(pageable);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity store(@Valid @RequestBody NewUserForm newUserForm) {
        User user = userService.store(newUserForm.toUser());
        return new ResponseEntity<Object>(user, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity update(@Valid @RequestBody UpdateUserForm updateUserForm, @PathVariable("id") Long id) {
        if (! Objects.equals(updateUserForm.getId(), id)) {
            throw new IdsNotMatchException();
        }

        User user = userService.update(updateUserForm);

        return new ResponseEntity<Object>(user, HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public ResponseEntity destroy(@PathVariable("id") long id) {
        userService.destroy(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User get(@PathVariable("id") Long id) {
        return userService.findOne(id);
    }
}
