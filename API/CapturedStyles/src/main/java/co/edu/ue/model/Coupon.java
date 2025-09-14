package co.edu.ue.model;

import java.io.Serializable;


import jakarta.persistence.*;
import lombok.*;


/**
 * The persistent class for the coupons database table.
 * 
 */
@Entity
@Table(name="coupons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NamedQuery(name="Coupon.findAll", query="SELECT c FROM Coupon c")
public class Coupon implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cup_id")
	private int cupId;

	@Column(name="cup_discount")
	private float cupDiscount;

	@Column(name="cup_fire")
	private int cupFire;

	@Enumerated(EnumType.STRING)
	@Column(name="cup_valid")
	private Valid cupValid;

	@ManyToOne
	@JoinColumn(name="id_user")
	private User user;

	public enum Valid{
		si,
		no
	}
}