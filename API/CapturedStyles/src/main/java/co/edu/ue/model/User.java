package co.edu.ue.model;

import java.io.Serializable;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable/* , UserDetails*/ {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_user")
	private int idUser;

	@Enumerated(EnumType.STRING)
	@Column(name="usr_active", nullable = false)
	private Status usrActive;

	@Column(name="usr_apellidos")
	private String usrApellidos;

	@Column(name="usr_direccion")
	private String usrDireccion;
	
	@Column(name="usr_email")
	private String usrEmail;

	@Column(name="usr_fechaInicio")
	private Timestamp usrFechaInicio;

	@Column(name="usr_identificacion")
	private String usrIdentificacion;

	@Column(name="usr_nombres")
	private String usrNombres;

	@Column(name="usr_password")
	private String usrPassword;

	@Column(name="usr_telefono")
	private String usrTelefono;

	@Column(name="usr_username", nullable = false)
	private String usrUsername;

	@Column(name="role")
	@Enumerated(EnumType.STRING)
	private Role role;
	
	public enum Status {
		activo,
		suspendido,
		baneado
	}
	public enum Role{
		ADMIN,
		USER
	}


}