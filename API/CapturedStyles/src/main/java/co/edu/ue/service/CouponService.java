package co.edu.ue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ue.dao.ICouponDao;
import co.edu.ue.model.Coupon;
import co.edu.ue.model.Coupon.Valid;

@Service
public class CouponService implements ICouponService{
	
	@Autowired
	ICouponDao dao;

	@Override
	public List<Coupon> getAll() {
		return dao.getAllCoupon();
	}

	@Override
	public List<Coupon> getByValid(Valid valid) {
		return dao.getByValid(valid);
	}

	@Override
	public Coupon getById(int id) {
		return dao.getById(id);
	}

	@Override
	public Coupon updtById(int id, Coupon coupon) {
		Coupon oldDateCoupon = dao.getById(id);
		
		oldDateCoupon.setCupValid(coupon.getCupValid());
		oldDateCoupon.setCupDiscount(coupon.getCupDiscount());
		
		return dao.editCoupon(coupon, id);
	}

	@Override
	public Coupon upStatusCoupon(Valid valid, int id) {
		return null;
	}

	@Override
	public Coupon delete(int id) {
		return null;
	}

}
