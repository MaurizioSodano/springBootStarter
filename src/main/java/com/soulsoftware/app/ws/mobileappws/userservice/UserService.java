package com.soulsoftware.app.ws.mobileappws.userservice;

import com.soulsoftware.app.ws.mobileappws.ui.model.request.UserDetailsRequestModel;
import com.soulsoftware.app.ws.mobileappws.ui.model.response.UserRest;

public interface UserService {
    UserRest createUser(UserDetailsRequestModel userDetails);
}
