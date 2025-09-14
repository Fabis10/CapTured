package co.edu.ue.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.ue.model.User;
import co.edu.ue.model.User.Status;
import java.util.List;
import java.util.Optional;


public interface IUserJpa  extends JpaRepository<User, Integer>{
	
	Optional<User> findByUsrUsername(String username);
	
	List<User> findByUsrActive(Status usrActive);
	
	User findByUsrEmail(String email);

	boolean existsByUsrUsername(String usrUsername);
	
	void deleteByIdUser(int idUser);
}
