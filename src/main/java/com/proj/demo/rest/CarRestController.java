package com.proj.demo.rest;
import com.proj.demo.entity.Car;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CarRestController {

    private List<Car> theCars;

    // Define @PostConstruct to load the car data ... only oance!

    @PostConstruct
    public void loadData(){
        theCars = new ArrayList<>();

        theCars.add(new Car("Volvo", "CX7"));
        theCars.add(new Car("Skoda", "Octavia VRS"));
        theCars.add(new Car("Land Rover", "Range Rover Vogue"));

    }


    // Define endpoint for /cars - return a list of cars
    @GetMapping("/cars")
    public List<Car> getCar(){
        return theCars;
    }

    // Define endpoint or "/students/{studentId}- return student at index
    @GetMapping("/cars/{carId}")
    public Car getCar (@PathVariable int carId){

        // just index into the list  ... keep it simple now

        // check the carId againlist size
        if(carId >= theCars.size() || (carId < 0)){
            throw new CarNotFoundException("Student id not found - " + carId);
        }
        return theCars.get(carId);
    }

        // add an exception handle using @ExceptionHandle
    @ExceptionHandler
    public ResponseEntity<CarErrorResponse> handleException(CarNotFoundException exc){

      // create a CarErrorResponse
        CarErrorResponse error = new CarErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

      // return ResponseEntity

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }













}
