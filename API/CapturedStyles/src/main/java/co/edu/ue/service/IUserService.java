package co.edu.ue.service;

import java.util.List;
import java.util.Optional;

import co.edu.ue.model.User;
import co.edu.ue.model.User.Status;

public interface IUserService {
	User addUser(User user);
	User upUser(String username, User user);
	User upPasswordUser(String username,User password);
	User statusUser(String username, User status);
	List<User> allUser();
	User getById(int id);
	Optional<User> getByUsername(String username);
	List<User> getByStatus(Status status);
	boolean existsByUsername(String username);
	void deleteUser(int id);
}
