package ie.stephen.dao;

import ie.stephen.model.RegisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisteredUserDao extends JpaRepository<RegisteredUser, Integer> {

}
