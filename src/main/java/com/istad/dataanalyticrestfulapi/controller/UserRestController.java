package com.istad.dataanalyticrestfulapi.controller;

import com.istad.dataanalyticrestfulapi.model.Request.UserRequest;
import com.istad.dataanalyticrestfulapi.model.User;
import com.istad.dataanalyticrestfulapi.model.UserAccount;
import com.istad.dataanalyticrestfulapi.service.UserService;
import com.istad.dataanalyticrestfulapi.util.Response;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserRestController {

    private final UserService userService;
    UserRestController(UserService userService){
        this.userService = userService;
    }

    private boolean isUserExists(int id) {
        User user = userService.findUserByID(id);
        return user != null;
    }
    private Response<User> userNotFound(int id){
        return Response.<User>notFound().setMessage("Cannot find user with id "+id).setSuccess(false).setStatus(Response.Status.NOT_FOUND);
    }

    @GetMapping("/all-users")
    public Response<List<User>> getAllUser(){
        try{
            List<User> users = userService.allUsers();
            return Response.<List<User>>ok().setMessage("Successfully retrieved all users!").setPayload(users);
        } catch (Exception exception) {
            return Response.<List<User>>exception().setMessage("Fail to retrieve all users!").setSuccess(false);
        }
    }


    @PostMapping("/new-user")
    public Response <User> createUser(@RequestBody UserRequest request, int userId){
       try{
//           User obj = new User().setUserId(1).setUsername()
//           UserRequest UserRequest;
           int affectedRow = userService.createNewUser(request);
            if(affectedRow>0){
                User response = new User().setUsername(request.getUsername()).setAddress(request.getAddress()).setGender(request.getGender()).setUserId(userId);

                return Response.<User>createSuccess().setPayload(response).setMessage("create user Successfully").setSuccess(true);
            }else{
                return Response.<User>bedRequest().setMessage("bed request!");
            }
       }catch (Exception ex){
           return Response.<User>exception().setMessage("Exceotion occur! failed to create a new user").setSuccess(false);

       }
    }

    @PutMapping("/{user}")
    public Response<User> updateUser(@PathVariable int id, @RequestBody User user){
        try{
            if(isUserExists(id)){
                user.setUserId(id);
                userService.updateUser(user, id);
                return Response.<User>updateSuccess().setPayload(user).setMessage("Successfully updated a user with id "+id);
            } else {
                return userNotFound(id);
            }
        } catch (Exception exception){
            return Response.<User>exception().setSuccess(false).setMessage("Fail to update a user with id "+id);
        }

    }

    @DeleteMapping("/deleteUser/{id}")
    public Response<List<Response>> removeUser(@PathVariable("id") int id){
        try {
            int deleteUser = userService.removeUser(id);
            if (deleteUser>0){
                return Response.<List<Response>>deleteSuccess().setMessage("Successfully Delete account by Id !!!");
            }else {
                return Response.<List<Response>>notFound().setMessage("Undefined the Id to delete!!!!");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return Response.<List<Response>>exception().setMessage("Failed to remove the user !!!!");
        }
    }

    @GetMapping("/all-user-accounts")
    public Response<List<UserAccount>> getAllUserAccounts(){
        try {
            List<UserAccount> userAccounts = userService.getAllUserAccount();
            return Response.<List<UserAccount>>ok().setPayload(userAccounts).setMessage("Successfully retrieve all user accounts");
        } catch (Exception exception){
            return Response.<List<UserAccount>>ok().setMessage("Failed to retrieve all user accounts!").setSuccess(false);
        }
    }

}