package co.edu.ue.dao;

import java.util.List;
import java.util.Optional;

import co.edu.ue.model.User;
import co.edu.ue.model.User.Status;


public interface IUserDao {
	
	User registerUser(User user);
	User updateUser(User user);
	User searchById(int id);
	Optional<User> searchByUsername(String username);
	List<User> allUser();
	List <User> searchByStatus(Status status);
	User existByEmail(String email);
	boolean existByUsername(String username);
	void deleteUser(int id);
}
