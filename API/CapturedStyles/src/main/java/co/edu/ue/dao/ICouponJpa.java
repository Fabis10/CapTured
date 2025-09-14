package co.edu.ue.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.ue.model.Coupon;
import co.edu.ue.model.Coupon.Valid;

import java.util.List;


public interface ICouponJpa extends JpaRepository<Coupon, Integer> {
	List<Coupon> findByCupValid(Valid valid);
	
}
