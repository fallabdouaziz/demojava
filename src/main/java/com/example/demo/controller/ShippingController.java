package com.example.demo.controller;

import com.example.demo.dao.ShippingRepository;
import com.example.demo.entity.Shipping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/shipping")
public class ShippingController {
    private ShippingRepository shippingRepository;

    public ShippingController(ShippingRepository shippingRepository) {
        this.shippingRepository = shippingRepository;
    }
    @GetMapping("")
    public List<Shipping> getAll(){
        return shippingRepository.findAll();
    }
}
