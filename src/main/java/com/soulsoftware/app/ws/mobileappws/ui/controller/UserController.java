package com.soulsoftware.app.ws.mobileappws.ui.controller;

import com.soulsoftware.app.ws.mobileappws.exceptions.UserServiceException;
import com.soulsoftware.app.ws.mobileappws.ui.model.request.UpdateUserDetailsRequestModel;
import com.soulsoftware.app.ws.mobileappws.ui.model.request.UserDetailsRequestModel;
import com.soulsoftware.app.ws.mobileappws.ui.model.response.UserRest;
import com.soulsoftware.app.ws.mobileappws.userservice.impl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/users") //http://localhost:8080/users/
public class UserController {

    Map<String, UserRest> users;

    @GetMapping()
    public String getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
                           @RequestParam(value = "limit", defaultValue = "50") int limit) {
        return "get users was called page=" + page + " limit=" + limit;
    }

    @GetMapping(path = "/{userId}",
            produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            })
    public ResponseEntity<UserRest> getUser(@PathVariable String userId) {


        if (true) {
            throw new UserServiceException("A user service exception is thrown");
        }
        if (users != null && users.containsKey(userId)) {
            return new ResponseEntity<>(users.get(userId), HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(consumes = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
    }, produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetails) {

        UserRest returnValue = new UserServiceImpl().createUser(userDetails);
        return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK);
    }

    @PutMapping(path = "/{userId}",
            consumes = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            }, produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<UserRest> updateUser(@PathVariable String userId, @Valid @RequestBody UpdateUserDetailsRequestModel userDetails) {
        if (users != null && users.containsKey(userId)) {
            UserRest storedUser = users.get(userId);
            storedUser.setFirstName(userDetails.getFirstName());
            storedUser.setLastName(userDetails.getLastName());
            return new ResponseEntity<>(storedUser, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        if (users != null && users.containsKey(userId)) {
            UserRest storedUser = users.remove(userId);

            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
