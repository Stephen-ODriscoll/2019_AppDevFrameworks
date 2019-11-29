package ie.stephen.services;

import ie.stephen.model.RegisteredUser;

import java.util.List;

public interface RegisteredUserService {
    RegisteredUser save(RegisteredUser registeredUser);
    RegisteredUser findByEmail(String email);
    List<RegisteredUser> getAllRegisteredUsers();
}
