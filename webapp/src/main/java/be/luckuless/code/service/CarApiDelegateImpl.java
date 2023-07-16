package be.luckuless.code.service;

import be.luckuless.code.api.CarsApiDelegate;
import be.luckuless.code.exception.DuplicateItemException;
import be.luckuless.code.model.Car;
import be.luckuless.code.exception.CarNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CarApiDelegateImpl implements CarsApiDelegate {

    private final Map<String, Car> carMap = new HashMap<>();

    @Override
    public ResponseEntity<Car> createCar(Car body) {
        if(carMap.containsKey(body.getModel())){
            throw new DuplicateItemException("Duplicate Car - " + body.getModel());
        }

        carMap.put(body.getModel(), body);

        return new ResponseEntity<>(carMap.get(body.getModel()), HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<Void> deleteCar(String model) {

        if(!carMap.containsKey(model)) {
            throw new CarNotFoundException("Car not found - " + model);
        }

        carMap.remove(model);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @Override
    public ResponseEntity<Car> getCarByModel(String model) {
        if(!carMap.containsKey(model)) {
            throw new CarNotFoundException("Car not found - " + model);
        }

        carMap.remove(model);

        return new ResponseEntity<>(carMap.get(model),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Car> updateCar(String model, Car car) {

        if(!carMap.containsKey(model)) {
            throw new CarNotFoundException("Car not found - " + model);
        }

        carMap.put(model, car);

        return new ResponseEntity<>(carMap.get(model),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Car>> getCars(Integer page, Integer limit) {
        return new ResponseEntity<>(
                carMap.values()
                        .stream()
                        .collect(Collectors.toList()),
                HttpStatus.OK);
    }
}
