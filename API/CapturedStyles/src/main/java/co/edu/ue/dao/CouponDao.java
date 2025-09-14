package co.edu.ue.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.edu.ue.model.Coupon;
import co.edu.ue.model.Coupon.Valid;

@Repository
public class CouponDao  implements ICouponDao{
	
	@Autowired
	ICouponJpa jpa;

	@Override
	public Coupon getById(int id) {
		return jpa.findById(id).orElse(null);
	}

	@Override
	public List<Coupon> getAllCoupon() {
		return jpa.findAll();
	}

	@Override
	public Coupon newCoupon(Coupon coupon) {
		return jpa.save(coupon);
	}

	@Override
	public Coupon editCoupon(Coupon coupon, int id) {
		return jpa.save(coupon);
	}

	@Override
	public void deleteCoupon(Coupon coupon, int id) {
		jpa.delete(coupon);
	}

	@Override
	public List<Coupon> getByValid(Valid valid) {
		return jpa.findByCupValid(valid);
	}
	
	

}
