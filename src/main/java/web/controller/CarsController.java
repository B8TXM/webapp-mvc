package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Car;
import web.service.CarService;

import java.util.Arrays;
import java.util.List;

@Controller
public class CarsController {

    private final CarService service;

    public CarsController(CarService service) {
        this.service = service;
    }

    @GetMapping("/cars")
    public String listCars(@RequestParam(required = false) Integer count, ModelMap model) {
        model.addAttribute("cars", service.getCars((count == null) ? 5 : count));
        return "cars";
    }

}
