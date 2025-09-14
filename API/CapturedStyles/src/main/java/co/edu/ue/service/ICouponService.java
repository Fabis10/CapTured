package co.edu.ue.service;

import java.util.List;

import org.springframework.stereotype.Service;

import co.edu.ue.model.Coupon;
import co.edu.ue.model.Coupon.Valid;

@Service
public interface ICouponService {
	List<Coupon> getAll();
	List<Coupon> getByValid(Valid valid);
	Coupon getById(int id);
	Coupon updtById(int id, Coupon coupon);
	Coupon upStatusCoupon(Valid valid, int id);
	Coupon delete(int id);

}
