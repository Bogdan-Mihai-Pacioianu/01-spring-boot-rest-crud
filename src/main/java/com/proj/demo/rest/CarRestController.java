package com.proj.demo.rest;

import com.proj.demo.entity.Car;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CarRestController {

    private List<Car> theCars;

    // Define @PostConstruct to load the car data ... only oance!

    @PostConstruct
    public void loadData() {
        theCars = new ArrayList<>();

        theCars.add(new Car("Volvo", "CX7"));
        theCars.add(new Car("Skoda", "Octavia VRS"));
        theCars.add(new Car("Land Rover", "Range Rover Vogue"));

    }


    // Define endpoint for /cars - return a list of cars
    @GetMapping("/cars")
    public List<Car> getCar() {
        return theCars;
    }

    // Define endpoint or "/students/{studentId}- return student at index
    @GetMapping("/cars/{carId}")
    public Car getCar(@PathVariable int carId) {

        // just index into the list  ... keep it simple now

        // check the carId againlist size
        if (carId >= theCars.size() || (carId < 0)) {
            throw new CarNotFoundException("Student id not found - " + carId);
        }
        return theCars.get(carId);
    }

}
