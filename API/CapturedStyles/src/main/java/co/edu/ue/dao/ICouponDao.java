package co.edu.ue.dao;

import java.util.List;

import co.edu.ue.model.Coupon;
import co.edu.ue.model.Coupon.Valid;

public interface ICouponDao {
	List<Coupon> getAllCoupon();
	List<Coupon> getByValid(Valid valid);
	Coupon getById(int id);
	Coupon newCoupon(Coupon coupon);
	Coupon editCoupon(Coupon coupon, int id);
	void deleteCoupon(Coupon coupon, int id);
}
