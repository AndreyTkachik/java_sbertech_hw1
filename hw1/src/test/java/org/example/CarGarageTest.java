package org.example;

import java.util.ArrayList;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class CarGarageTest {

    @org.junit.jupiter.api.Test
    void allCarsUniqueOwners() {
        CarGarage carGarage = new CarGarage();
        ArrayList<Owner> owners = new ArrayList<>();
        Car tmp_car = new Car(1,"A", "A", 1, 1, 1);
        Owner tmp_owner1 = new Owner("A", "A", 1, 1);
        owners.add(tmp_owner1);
        carGarage.addNewCar(tmp_car, tmp_owner1);
        tmp_car = new Car(2,"dfsfA", "sdfsdfA", 100, 145, 12);
        Owner tmp_owner2 = new Owner("A1313", "A", 1, 12);
        owners.add(tmp_owner2);
        carGarage.addNewCar(tmp_car, tmp_owner2);
        tmp_car = new Car(10,"ssdfA", "aasA", 11, 14, 16);
        Owner tmp_owner3 = new Owner("42424A", "A", 1, 16);
        owners.add(tmp_owner3);
        carGarage.addNewCar(tmp_car, tmp_owner3);
        tmp_car = new Car(114,"A", "Aweq", 114, 111, 13);
        Owner tmp_owner4 = new Owner("A54", "A", 1, 13);
        owners.add(tmp_owner4);
        carGarage.addNewCar(tmp_car, tmp_owner4);
        tmp_car = new Car(15,"dfsfA", "Aadadadad", 120, 25, 1);
        carGarage.addNewCar(tmp_car, tmp_owner1);
        tmp_car = new Car(222,"222A", "A", 200, 200, 114);
        Owner tmp_owner5 = new Owner("A", "A", 1, 114);
        owners.add(tmp_owner5);
        carGarage.addNewCar(tmp_car, tmp_owner5);
        owners.sort(Comparator.comparingInt(Owner::getOwnerId));
        var temp = carGarage.allCarsUniqueOwners();
        temp.sort(Comparator.comparingInt(Owner::getOwnerId));
        assertEquals(temp, owners);
    }

    @org.junit.jupiter.api.Test
    void topThreeCarsByMaxVelocity() {
        CarGarage carGarage = new CarGarage();
        ArrayList<Car> cars = new ArrayList<>();
        Car tmp_car = new Car(1,"A", "A", 1, 1, 1);
        Owner tmp_owner = new Owner("A", "A", 1, 1);
        carGarage.addNewCar(tmp_car, tmp_owner);
        tmp_car = new Car(2,"dfsfA", "sdfsdfA", 100, 145, 12);
        tmp_owner = new Owner("A1313", "A", 1, 12);
        carGarage.addNewCar(tmp_car, tmp_owner);
        tmp_car = new Car(10,"ssdfA", "aasA", 11, 14, 16);
        tmp_owner = new Owner("42424A", "A", 1, 16);
        carGarage.addNewCar(tmp_car, tmp_owner);
        tmp_car = new Car(114,"A", "Aweq", 114, 111, 13);
        tmp_owner = new Owner("A54", "A", 1, 13);
        cars.add(tmp_car);
        carGarage.addNewCar(tmp_car, tmp_owner);
        tmp_car = new Car(15,"dfsfA", "Aadadadad", 120, 25, 1);
        tmp_owner = new Owner("A", "A", 1, 1);
        carGarage.addNewCar(tmp_car, tmp_owner);
        cars.add(tmp_car);
        tmp_car = new Car(222,"222A", "A", 200, 200, 114);
        tmp_owner = new Owner("A", "A", 1, 114);
        carGarage.addNewCar(tmp_car, tmp_owner);
        cars.add(tmp_car);
        cars.sort(Comparator.comparingInt(Car::getMaxVelocity).reversed());
        assertEquals(carGarage.topThreeCarsByMaxVelocity(), cars);
    }

    @org.junit.jupiter.api.Test
    void allCarsOfBrand() {
        CarGarage carGarage = new CarGarage();
        ArrayList<Car> cars = new ArrayList<>();
        Car tmp_car = new Car(1,"A", "A", 1, 1, 1);
        Owner tmp_owner = new Owner("A", "A", 1, 1);
        carGarage.addNewCar(tmp_car, tmp_owner);
        tmp_car = new Car(2,"dfsfA", "sdfsdfA", 100, 145, 12);
        tmp_owner = new Owner("A1313", "A", 1, 12);
        cars.add(tmp_car);
        carGarage.addNewCar(tmp_car, tmp_owner);
        tmp_car = new Car(10,"ssdfA", "aasA", 11, 14, 16);
        tmp_owner = new Owner("42424A", "A", 1, 16);
        carGarage.addNewCar(tmp_car, tmp_owner);
        tmp_car = new Car(114,"A", "Aweq", 114, 111, 13);
        tmp_owner = new Owner("A54", "A", 1, 13);
        carGarage.addNewCar(tmp_car, tmp_owner);
        tmp_car = new Car(15,"dfsfA", "Aadadadad", 120, 25, 1);
        tmp_owner = new Owner("A", "A", 1, 1);
        carGarage.addNewCar(tmp_car, tmp_owner);
        cars.add(tmp_car);
        tmp_car = new Car(222,"222A", "A", 200, 200, 114);
        tmp_owner = new Owner("A", "A", 1, 114);
        carGarage.addNewCar(tmp_car, tmp_owner);
        cars.sort(Comparator.comparingInt(Car::getMaxVelocity).reversed());
        var temp = carGarage.allCarsOfBrand("dfsfA");
        temp.sort(Comparator.comparingInt(Car::getMaxVelocity).reversed());
        assertEquals(temp, cars);
    }

    @org.junit.jupiter.api.Test
    void carsWithPowerMoreThan() {
        ArrayList<Car> cars = new ArrayList<>();
        CarGarage carGarage = new CarGarage();
        Car tmp_car = new Car(1,"A", "A", 1, 1, 1);
        Owner tmp_owner = new Owner("A", "A", 1, 1);
        carGarage.addNewCar(tmp_car, tmp_owner);
        tmp_car = new Car(2,"dfsfA", "sdfsdfA", 100, 145, 12);
        tmp_owner = new Owner("A1313", "A", 1, 12);
        cars.add(tmp_car);
        carGarage.addNewCar(tmp_car, tmp_owner);
        tmp_car = new Car(10,"ssdfA", "aasA", 11, 14, 16);
        tmp_owner = new Owner("42424A", "A", 1, 16);
        carGarage.addNewCar(tmp_car, tmp_owner);
        tmp_car = new Car(114,"A", "Aweq", 114, 111, 13);
        tmp_owner = new Owner("A54", "A", 1, 13);
        carGarage.addNewCar(tmp_car, tmp_owner);
        tmp_car = new Car(15,"dfsfA", "Aadadadad", 120, 25, 1);
        tmp_owner = new Owner("A", "A", 1, 1);
        carGarage.addNewCar(tmp_car, tmp_owner);
        tmp_car = new Car(222,"222A", "A", 200, 200, 114);
        tmp_owner = new Owner("A", "A", 1, 114);
        cars.add(tmp_car);
        carGarage.addNewCar(tmp_car, tmp_owner);
        cars.sort(Comparator.comparingInt(Car::getMaxVelocity).reversed());
        var temp = carGarage.carsWithPowerMoreThan(115);
        temp.sort(Comparator.comparingInt(Car::getMaxVelocity).reversed());
        assertEquals(temp, cars);
    }

    @org.junit.jupiter.api.Test
    void allCarsOfOwner() {
        CarGarage carGarage = new CarGarage();
        ArrayList<Car> cars = new ArrayList<>();
        Car tmp_car = new Car(1,"A", "A", 1, 1, 1);
        Owner tmp_owner = new Owner("A", "A", 1, 1);
        cars.add(tmp_car);
        carGarage.addNewCar(tmp_car, tmp_owner);
        tmp_car = new Car(2,"dfsfA", "sdfsdfA", 100, 145, 12);
        tmp_owner = new Owner("A1313", "A", 1, 12);
        carGarage.addNewCar(tmp_car, tmp_owner);
        tmp_car = new Car(10,"ssdfA", "aasA", 11, 14, 16);
        tmp_owner = new Owner("42424A", "A", 1, 16);
        carGarage.addNewCar(tmp_car, tmp_owner);
        tmp_car = new Car(114,"A", "Aweq", 114, 111, 13);
        tmp_owner = new Owner("A54", "A", 1, 13);
        carGarage.addNewCar(tmp_car, tmp_owner);
        tmp_car = new Car(15,"dfsfA", "Aadadadad", 120, 25, 1);
        tmp_owner = new Owner("A", "A", 1, 1);
        cars.add(tmp_car);
        carGarage.addNewCar(tmp_car, tmp_owner);
        cars.sort(Comparator.comparingInt(Car::getMaxVelocity).reversed());
        var temp = carGarage.allCarsOfOwner(tmp_owner);
        temp.sort(Comparator.comparingInt(Car::getMaxVelocity).reversed());
        assertEquals(temp, cars);
    }

    @org.junit.jupiter.api.Test
    void meanOwnersAgeOfCarBrand() {
        CarGarage carGarage = new CarGarage();
        Car tmp_car = new Car(1,"A", "A", 1, 1, 1);
        Owner tmp_owner = new Owner("A", "A", 14, 1);
        carGarage.addNewCar(tmp_car, tmp_owner);
        tmp_car = new Car(2,"dfsfA", "sdfsdfA", 100, 145, 12);
        tmp_owner = new Owner("A1313", "A", 1, 12);
        carGarage.addNewCar(tmp_car, tmp_owner);
        tmp_car = new Car(10,"ssdfA", "aasA", 11, 14, 16);
        tmp_owner = new Owner("42424A", "A", 1, 16);
        carGarage.addNewCar(tmp_car, tmp_owner);
        tmp_car = new Car(114,"A", "Aweq", 114, 111, 13);
        tmp_owner = new Owner("A54", "A", 1, 13);
        carGarage.addNewCar(tmp_car, tmp_owner);
        tmp_car = new Car(15,"dfsfA", "Aadadadad", 120, 25, 1);
        tmp_owner = new Owner("A", "A", 14, 1);
        carGarage.addNewCar(tmp_car, tmp_owner);
        tmp_car = new Car(222,"222A", "A", 200, 200, 114);
        tmp_owner = new Owner("A", "A", 1, 114);
        carGarage.addNewCar(tmp_car, tmp_owner);
        assertEquals(carGarage.meanOwnersAgeOfCarBrand("dfsfA"), 7);
    }

    @org.junit.jupiter.api.Test
    void meanCarNumberForEachOwner() {
        CarGarage carGarage = new CarGarage();
        Car tmp_car = new Car(1,"A", "A", 1, 1, 1);
        Owner tmp_owner = new Owner("A", "A", 1, 1);
        carGarage.addNewCar(tmp_car, tmp_owner);
        tmp_car = new Car(2,"dfsfA", "sdfsdfA", 100, 145, 12);
        tmp_owner = new Owner("A1313", "A", 1, 12);
        carGarage.addNewCar(tmp_car, tmp_owner);
        tmp_car = new Car(10,"ssdfA", "aasA", 11, 14, 16);
        tmp_owner = new Owner("42424A", "A", 1, 16);
        carGarage.addNewCar(tmp_car, tmp_owner);
        tmp_car = new Car(114,"A", "Aweq", 114, 111, 13);
        tmp_owner = new Owner("A54", "A", 1, 13);
        carGarage.addNewCar(tmp_car, tmp_owner);
        tmp_car = new Car(15,"dfsfA", "Aadadadad", 120, 25, 1);
        tmp_owner = new Owner("A", "A", 1, 1);
        carGarage.addNewCar(tmp_car, tmp_owner);
        tmp_car = new Car(222,"222A", "A", 200, 200, 114);
        tmp_owner = new Owner("A", "A", 1, 114);
        carGarage.addNewCar(tmp_car, tmp_owner);
        assertEquals(carGarage.meanCarNumberForEachOwner() ,1);
    }

    @org.junit.jupiter.api.Test
    void removeCar() {
        CarGarage carGarage = new CarGarage();
        Car tmp_car = new Car(1,"A", "A", 1, 1, 1);
        Owner tmp_owner = new Owner("A", "A", 1, 1);
        carGarage.addNewCar(tmp_car, tmp_owner);
        tmp_car = new Car(222,"222A", "A", 200, 200, 114);
        tmp_owner = new Owner("A", "A", 1, 114);
        carGarage.addNewCar(tmp_car, tmp_owner);
        assertEquals(carGarage.removeCar(222), tmp_car);
    }

    @org.junit.jupiter.api.Test
    void addNewCar() {
        CarGarage carGarage1 = new CarGarage();
        CarGarage carGarage2 = new CarGarage();
        Car tmp_car = new Car(1,"A", "A", 1, 1, 1);
        Owner tmp_owner = new Owner("A", "A", 1, 1);
        carGarage1.addNewCar(tmp_car, tmp_owner);
        carGarage2.addNewCar(tmp_car, tmp_owner);
        tmp_car = new Car(2,"dfsfA", "sdfsdfA", 100, 145, 12);
        tmp_owner = new Owner("A1313", "A", 1, 12);
        carGarage1.addNewCar(tmp_car, tmp_owner);
        carGarage2.addNewCar(tmp_car, tmp_owner);
        assertEquals(carGarage1.topThreeCarsByMaxVelocity(), carGarage2.topThreeCarsByMaxVelocity());
    }
}