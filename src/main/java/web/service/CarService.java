package web.service;

import org.springframework.stereotype.Service;
import web.model.Car;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CarService {

    private final List<Car> cars = Arrays.asList(
            new Car("Toyota", "GR86", "S866CW"),
            new Car("Lexus", "LFA", "A433GT"),
            new Car("Volvo", "S60", "N703BT"),
            new Car("Porsche", "911", "N001SW"),
            new Car("Audi", "R8", "S350NC")
    );

    public List<Car> getCars(int count) {
        if (count >= 5) {
            return new ArrayList<>(cars);
        } else if (count < 1) {
            return new ArrayList<>();
        }
        return new ArrayList<>(cars.subList(0, count));
    }
}
