package ie.stephen.services;

import ie.stephen.dao.RoleDao;
import ie.stephen.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImplementation implements RoleService {
    @Autowired
    RoleDao roleDao;

    @Override
    public Role save(Role role) {
        return roleDao.save(role);
    }
}
