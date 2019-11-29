package ie.stephen.services;

import ie.stephen.dao.RegisteredUserDao;
import ie.stephen.model.RegisteredUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisteredUserServiceImplementation implements RegisteredUserService {
    @Autowired
    RegisteredUserDao registeredUserDAO;

    @Override
    public RegisteredUser save(RegisteredUser registeredUser) {
        return registeredUserDAO.save(registeredUser);
    }

    @Override
    public boolean existsByEmail(String email) {
        return registeredUserDAO.existsByUserEmail(email);
    }

    @Override
    public RegisteredUser findByEmail(String email) {
        if (existsByEmail(email))
            return registeredUserDAO.findByUserEmail(email);
        return null;
    }

    @Override
    public List<RegisteredUser> getAllRegisteredUsers() {
        return registeredUserDAO.findAll();
    }
}
