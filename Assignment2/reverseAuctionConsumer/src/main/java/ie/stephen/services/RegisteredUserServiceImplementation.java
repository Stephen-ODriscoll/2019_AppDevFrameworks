package ie.stephen.services;

import ie.stephen.dao.RegisteredUserDao;
import ie.stephen.model.RegisteredUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisteredUserServiceImplementation implements RegisteredUserService{
    @Autowired
    RegisteredUserDao registeredUserDAO;

    @Override
    public RegisteredUser save(RegisteredUser registeredUser) {
        return registeredUserDAO.save(registeredUser);
    }

    @Override
    public List<RegisteredUser> getAllRegisteredUsers() {
        return registeredUserDAO.findAll();
    }
}
