package co.edu.ue.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.edu.ue.model.User;
import co.edu.ue.model.User.Status;

@Repository
public class UserDao implements IUserDao{

	@Autowired
	IUserJpa jpa;
	
	@Override
	public User registerUser(User user) {
		return jpa.save(user);
	}

	@Override
	public User updateUser(User user) {
		return jpa.save(user);
	}

	@Override
	public List<User> allUser() {
		return jpa.findAll();
	}

	@Override
	public User searchById(int id) {
		return jpa.findById(id).orElse(null);
	}

	@Override
	public Optional<User> searchByUsername(String username) {
		return jpa.findByUsrUsername(username);
	}

	@Override
	public List<User> searchByStatus(Status status) {
		return jpa.findByUsrActive(status);
	}


	@Override
	public boolean existByUsername(String username) {
		return jpa.existsByUsrUsername(username);
	}
	
	@Override
	public User existByEmail(String email) {
		return jpa.findByUsrEmail(email);
	}

	@Override
	public void deleteUser(int id) {
		jpa.deleteById(id);
	}
	

}
