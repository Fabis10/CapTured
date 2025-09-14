package co.edu.ue.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ue.dao.IUserDao;
import co.edu.ue.model.User;
import co.edu.ue.model.User.Status;

@Service
public class UserService implements IUserService {

	@Autowired
	IUserDao dao;
	
		//Todos los Create
	
	@Override
	public User addUser(User user) {
		return dao.registerUser(user);		
	}

		//Todos los Read
	@Override
	public List<User> allUser() {
		return dao.allUser();
	}
	
	@Override
	public User getById(int id) {
		return dao.searchById(id);
	}
	
	@Override
	public Optional<User> getByUsername(String username) {
		return dao.searchByUsername(username);
	}
	
	@Override
	public List<User> getByStatus(Status status) {
		return dao.searchByStatus(status);
	}
	
		//Todos losUpdate
	
	@Override
	public User upUser(String usrnm, User newDataUser) {
		//hace busqueda de el username para actualizar los campos
		Optional<User> optionalUser = dao.searchByUsername(usrnm);
		
		User existingDataUser = optionalUser.get();
		
		existingDataUser.setUsrNombres(newDataUser.getUsrNombres());
		existingDataUser.setUsrApellidos(newDataUser.getUsrApellidos());
		existingDataUser.setUsrDireccion(newDataUser.getUsrDireccion());
		existingDataUser.setUsrTelefono(newDataUser.getUsrTelefono());
		
		return dao.updateUser(existingDataUser);
	}
	@Override
	public User statusUser(String usrnm, User newDataUser) {
		Optional<User> optionalUser = dao.searchByUsername(usrnm);
		
		User existingDataUser = optionalUser.get();
		
		return dao.updateUser(existingDataUser);
	}
	
	@Override
	public User upPasswordUser(String usrnm,User newPasswordUser) {
		Optional<User> optionalUser = dao.searchByUsername(usrnm);
		
		User existingDataUser = optionalUser.get();
		existingDataUser.setUsrPassword(newPasswordUser.getUsrPassword());
		
		return dao.updateUser(existingDataUser);
}
	
	@Override
	public boolean existsByUsername(String username) {
		return dao.existByUsername(username);
	}

		//El Delete
	
	@Override
	public void deleteUser(int id) {
		dao.deleteUser(id);		
	}
	
}
