package org.example;

import java.util.*;

public class CarGarage implements Garage {
    HashMap<Integer, Car> cars_by_id = new HashMap<>();
    HashMap<Integer, Owner> owner_by_id = new HashMap<>();
    HashMap<Integer, HashSet<Car>> cars_by_owner = new HashMap<>();
    HashMap<String, HashSet<Car>> cars_by_brand = new HashMap<>();
    SortedSet<Car> cars_sorted_by_velocity =
            new TreeSet<>(Comparator.comparingInt(Car::getMaxVelocity).reversed());
    SortedSet<Car> cars_sorted_by_power =
            new TreeSet<>(Comparator.comparingInt(Car::getPower).reversed());

    @Override
    public Collection<Owner> allCarsUniqueOwners() {
        ArrayList<Owner> answer = new ArrayList<>();
        answer.addAll(owner_by_id.values());
        return answer;
    }

    @Override
    public Collection<Car> topThreeCarsByMaxVelocity() {
        ArrayList<Car> answer = new ArrayList<>();
        for (Car car: cars_sorted_by_velocity) {
            if (answer.size() < 3) {
                answer.add(car);
            }
        }
        return answer;
    }

    @Override
    public  Collection<Car> allCarsOfBrand(String brand) {
        if (!cars_by_brand.containsKey(brand)) {
            return null;
        }
        return cars_by_brand.get(brand);
    }

    @Override
    public Collection<Car> carsWithPowerMoreThan(int power) {
        ArrayList<Car> answer = new ArrayList<>();
        for (Car car : cars_sorted_by_power) {
            if (car.getPower() >= power) {
                answer.add(car);
            } else {
                break;
            }
        }
        return answer;
    }

    @Override
    public Collection<Car> allCarsOfOwner(Owner owner) {
        if (!owner_by_id.containsKey(owner.getOwnerId())) {
            return null;
        }
        return cars_by_owner.get(owner.getOwnerId());
    }

    @Override
    public int meanOwnersAgeOfCarBrand(String brand) {
        int answer = 0;
        for (Car car : cars_by_brand.get(brand)) {
            answer += owner_by_id.get(car.getOwnerId()).getAge();
        }
        return answer / cars_by_brand.get(brand).size();
    }

    @Override
    public int meanCarNumberForEachOwner() {
        return cars_by_id.size() / owner_by_id.size();
    }

    @Override
    public Car removeCar(int carId) {
        if (!cars_by_id.containsKey(carId)) {
            return null;
        }
        Integer tmp_owner_id = cars_by_id.get(carId).getOwnerId();
        Car answer = null;
        if(cars_by_owner.get(tmp_owner_id).contains(cars_by_id.get(carId))) {
            cars_by_owner.get(tmp_owner_id).remove(cars_by_id.get(carId));
            if (cars_by_owner.get(tmp_owner_id).isEmpty()) {
                cars_by_owner.remove(tmp_owner_id);
                owner_by_id.remove(tmp_owner_id);
            }
            answer = cars_by_id.get(carId);
            cars_by_id.remove(carId);
        }
        cars_by_brand.get(Objects.requireNonNull(answer).getBrand()).remove(answer);
        cars_sorted_by_velocity.remove(answer);
        cars_sorted_by_power.remove(answer);
        return answer;
    }

    @Override
    public void addNewCar(Car car, Owner owner) {
        cars_by_id.putIfAbsent(car.getCarId(), car);
        owner_by_id.putIfAbsent(owner.getOwnerId(), owner);
        cars_by_owner.putIfAbsent(owner.getOwnerId(), new HashSet<>());
        cars_by_owner.get(car.getOwnerId()).add(car);
        cars_by_brand.putIfAbsent(car.getBrand(), new HashSet<>());
        cars_by_brand.get(car.getBrand()).add(car);
        cars_sorted_by_velocity.add(car);
        cars_sorted_by_power.add(car);
    }
}
