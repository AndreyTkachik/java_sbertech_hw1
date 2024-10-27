package org.example;

import java.util.*;

public class CarGarage implements Garage {
    private final Map<Integer, Car> carsById = new HashMap<>();
    private final Map<Integer, Owner> ownerById = new HashMap<>();
    private final Map<Integer, HashSet<Car>> carsByOwner = new HashMap<>();
    private final Map<String, HashSet<Car>> carsByBrand = new HashMap<>();
    private final Set<Car> carsSortedByVelocity =
            new TreeSet<>(Comparator.comparingInt(Car::getMaxVelocity).reversed()
                    .thenComparingInt(Car::getCarId));
    private final TreeSet<Car> carsSortedByPower =
            new TreeSet<>(Comparator.comparingInt(Car::getPower).reversed()
                    .thenComparingInt(Car::getCarId));

    @Override
    public Collection<Owner> allCarsUniqueOwners() {
        return ownerById.values();
    }

    @Override
    public Collection<Car> topThreeCarsByMaxVelocity() {
        List<Car> answer = new ArrayList<>();
        for (Car car: carsSortedByVelocity) {
            if (answer.size() < 3) {
                answer.add(car);
            }
        }
        return answer;
    }

    @Override
    public  Collection<Car> allCarsOfBrand(String brand) {
        return carsByBrand.getOrDefault(brand, new HashSet<>());
    }

    @Override
    public Collection<Car> carsWithPowerMoreThan(int power) {
        Car to = null;
        for (Car car : carsSortedByPower) {
            if (car.getPower() < power) {
                to = car;
                break;
            }
        }
        return carsSortedByPower.headSet(to);
    }

    @Override
    public Collection<Car> allCarsOfOwner(Owner owner) {
        if (owner == null) {
            return Collections.emptySet();
        }
        return carsByOwner.getOrDefault(owner.getOwnerId(), new HashSet<>());
    }

    @Override
    public int meanOwnersAgeOfCarBrand(String brand) {
        int answer = 0;
        Set<Integer> usedOwners = new HashSet<>();
        for (Car car : allCarsOfBrand(brand)) {
            if (!usedOwners.contains(car.getOwnerId())) {
                answer += ownerById.get(car.getOwnerId()).getAge();
                usedOwners.add(car.getOwnerId());
            }
        }
        if (allCarsOfBrand(brand).isEmpty()) {
            return 0;
        }
        return answer / allCarsOfBrand(brand).size();
    }

    @Override
    public int meanCarNumberForEachOwner() {
        if (ownerById.isEmpty()) {
            return 0;
        }
        return carsById.size() / ownerById.size();
    }

    @Override
    public Car removeCar(int carId) {
        if (!carsById.containsKey(carId)) {
            return null;
        }
        Integer tmp_owner_id = carsById.get(carId).getOwnerId();
        Car answer = removeFromIdAndOwner(carId, tmp_owner_id);
        removeFromBrandAndSets(answer);
        return answer;
    }

    private void removeFromBrandAndSets(Car answer) {
        carsByBrand.get(Objects.requireNonNull(answer).getBrand()).remove(answer);
        carsSortedByVelocity.remove(answer);
        carsSortedByPower.remove(answer);
    }

    private Car removeFromIdAndOwner(int carId, Integer tmp_owner_id) {
        Car answer = null;
        if(carsByOwner.get(tmp_owner_id).contains(carsById.get(carId))) {
            carsByOwner.get(tmp_owner_id).remove(carsById.get(carId));
            if (carsByOwner.get(tmp_owner_id).isEmpty()) {
                carsByOwner.remove(tmp_owner_id);
                ownerById.remove(tmp_owner_id);
            }
            answer = carsById.get(carId);
            carsById.remove(carId);
        }
        return answer;
    }

    @Override
    public void addNewCar(Car car, Owner owner) {
        if (owner == null || car == null) {
            return;
        }
        carsById.putIfAbsent(car.getCarId(), car);
        ownerById.putIfAbsent(owner.getOwnerId(), owner);
        carsByOwner.computeIfAbsent(owner.getOwnerId(), _ -> new HashSet<>()).add(car);
        carsByOwner.get(car.getOwnerId()).add(car);
        carsByBrand.computeIfAbsent(car.getBrand(), _ -> new HashSet<>()).add(car);
        carsByBrand.get(car.getBrand()).add(car);
        carsSortedByVelocity.add(car);
        carsSortedByPower.add(car);
    }
}
