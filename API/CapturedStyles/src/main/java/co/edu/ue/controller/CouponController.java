package co.edu.ue.controller;

import co.edu.ue.model.Coupon;
import co.edu.ue.service.ICouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "orden")
public class CouponController {

    @Autowired
    ICouponService service;

    @GetMapping(value = "")
    public ResponseEntity<List<Coupon>> conseguirTodos(){
        List<Coupon> data = service.getAll();
        return new ResponseEntity<>(data,HttpStatus.ACCEPTED);
    }


}
