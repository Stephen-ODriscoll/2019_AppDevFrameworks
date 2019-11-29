package ie.stephen.services;

import ie.stephen.model.RegisteredUser;

import java.util.List;

public interface RegisteredUserService {
    RegisteredUser save(RegisteredUser registeredUser);
    List<RegisteredUser> getAllRegisteredUsers();
}
