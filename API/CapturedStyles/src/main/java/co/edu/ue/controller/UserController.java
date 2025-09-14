package co.edu.ue.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ue.model.User;
import co.edu.ue.model.User.Status;
import co.edu.ue.service.IUserService;

@RestController
@RequestMapping(value="usr")
@CrossOrigin(origins = "*")
public class UserController {
	//KeyCloak
	
	@Autowired
	IUserService service;
	
		//Peticiones Get
	
	//Trae todos los usuarios
	@GetMapping(value="user", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> getAllRegister(){
		//1. Env datos adicion en la peticion del servicio
		//Cantidad de registros
		List<User> datos = service.allUser();
		//2. Crear un header para add info adici
		HttpHeaders headers = new HttpHeaders();
		headers.add("cantidad_datos", String.valueOf(datos.size()));
		headers.add("otro_dat", "user");
		return new ResponseEntity<>(datos,headers,HttpStatus.OK);
	}
	
	//Trae un usuario por id
	@GetMapping(value = "user/{idu}")
	public ResponseEntity<User> getUserById(@PathVariable("idu") int id){
		if(service.getById(id) == null) {
				return	new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else return new ResponseEntity<>(service.getById(id),HttpStatus.ACCEPTED);
	}
	
	//Trae un usuario por el username
	@GetMapping(value = "user/nm/{usrnm}")
	public ResponseEntity<Optional<User>> getUserByUsername(@PathVariable("usrnm") String username){
		if(service.existsByUsername(username)) {
			return new ResponseEntity<>(service.getByUsername(username),HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>(service.getByUsername(username),HttpStatus.NOT_FOUND);
		}
	}
	//Trae todos los usuarios por el status
	@GetMapping(value = "user/status/{status}")
	public ResponseEntity<List<User>> getAllStatus(@PathVariable("Status") Status status){
		if(status == Status.activo || status == Status.baneado || status == Status.suspendido) {
			List<User> datos = service.getByStatus(status);
			try {
				return new ResponseEntity<>(datos,HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
		}else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
		//Peticiones POST
	
	//Crea uun ususario
	@PostMapping(value="user", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> registerUser(@RequestBody User user) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Mensaje", "Se agrego exitosamente");
		if(!service.existsByUsername(user.getUsrUsername())) {
			service.addUser(user);
			return new ResponseEntity<>(headers,HttpStatus.CREATED);
		}else{
			return new ResponseEntity<>(HttpStatus.FOUND);
		}
		
	}
	
		//Peticiones PUT
	
	//Actualiza los datos de un usuario
	@PutMapping(value ="user/{usrnm}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> updtUser(@PathVariable("usrnm") String username, @RequestBody User newUpdatedUser){
		User user = service.upUser(username, newUpdatedUser);
		return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
	}
	
	//Actualiza el estado ("baja") un usuario
	@PutMapping(value = "user/active/{usrnm}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> inactiveUser(@PathVariable("usrnm") String username, @RequestBody User active){
		User user = service.statusUser(username, active);
		return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
	}
	
	//Actualiza la contra de un usuario
	@PutMapping(value = "user/password/{usrnm}")
	public ResponseEntity<User> passwordUser(@PathVariable("usrnm") String username, @RequestBody User password){
		User user = service.upUser(username, password);
		return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
	}
	
		//Peticion DELETE
	
	//Elimina un usuario(Solo lo haria el administrador por si hizo un usuario de prueba o alga si)
	@DeleteMapping(value = "user/delete/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable("Id") int id){
		service.deleteUser(id);
		return ResponseEntity.noContent().build();
	}
	

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor: " + ex.getMessage());
    }

}
