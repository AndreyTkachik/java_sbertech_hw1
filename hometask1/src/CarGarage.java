import java.util.*;

public class CarGarage implements Garage {
    HashMap<Integer, Car> cars_by_id = new HashMap();
    HashMap<Integer, Owner> owner_by_id = new HashMap();
    HashMap<Integer, ArrayList<Car>> cars_by_owner = new HashMap();
    HashMap<String, ArrayList<Car>> cars_by_brand = new HashMap();
    SortedMap<Car, Integer> cars_sorted_by_velocity =
            new TreeMap(Comparator.comparingInt(Car::getMaxVelocity).reversed());

    @Override
    public ArrayList<Owner> allCarsUniqueOwners() {
        ArrayList<Owner> answer = new ArrayList();
        answer.addAll(owner_by_id.values());
        return answer;
    }

    @Override
    public ArrayList<Car> topThreeCarsByMaxVelocity() {
        ArrayList<Car> answer = new ArrayList<>();
        for (Car car: cars_sorted_by_velocity.keySet()) {
            if (answer.size() < 3) {
                answer.add(car);
            }
        }
        return answer;
    }

    @Override
    public ArrayList<Car> allCarsOfBrand(String brand) {
        if (!cars_by_brand.containsKey(brand)) {
            return null;
        }
        return cars_by_brand.get(brand);
    }

    @Override
    public ArrayList<Car> carsWithPowerMoreThan(int power) {
        ArrayList<Car> answer = new ArrayList();
        for (Car car :cars_sorted_by_velocity.keySet()) {
            if (car.getPower() > power) {
                answer.add(car);
            }
        }
        return answer;
    }

    @Override
    public ArrayList<Car> allCarsOfOwner(Owner owner) {
        if (!owner_by_id.containsKey(owner.getOwnerId())) {
            return null;
        }
        return cars_by_owner.get(owner.getOwnerId());
    }

    @Override
    public int meanOwnersAgeOfCarBrand(String brand) {
        int answer = 0;
        for (int indx = 0; indx < cars_by_brand.get(brand).size(); indx++) {
            answer += owner_by_id.get(cars_by_brand.get(brand).get(indx).getOwnerId()).getAge();
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
        for (int indx = 0; indx < cars_by_owner.get(tmp_owner_id).size(); indx++) {
            if(cars_by_owner.get(tmp_owner_id).get(indx).equals(cars_by_id.get(carId))) {
                cars_by_owner.get(tmp_owner_id).remove(indx);
                if (cars_by_owner.get(tmp_owner_id).isEmpty()) {
                    cars_by_owner.remove(tmp_owner_id);
                    owner_by_id.remove(tmp_owner_id);
                }
                answer = cars_by_id.get(carId);
                cars_by_id.remove(carId);
                break;
            }
        }
        for (int indx = 0; indx <
                cars_by_brand.get(Objects.requireNonNull(answer).getBrand()).size(); indx++) {
            if(cars_by_brand.get(Objects.requireNonNull(answer).getBrand()).get(indx).getCarId()
                    == (answer.getCarId())) {
                cars_by_brand.get(Objects.requireNonNull(answer).getBrand()).remove(indx);
                break;
            }
        }
        for (Iterator<Map.Entry<Car, Integer>> it = cars_sorted_by_velocity.entrySet().iterator(); it.hasNext();) {
            Map.Entry<Car, Integer> entry = it.next();
            if (entry.getKey().getCarId() == answer.getCarId()) {
                it.remove();
                break;
            }
        }
        return answer;
    }

    @Override
    public void addNewCar(Car car, Owner owner) {
        cars_by_id.putIfAbsent(car.getCarId(), car);
        owner_by_id.putIfAbsent(owner.getOwnerId(), owner);
        cars_by_owner.putIfAbsent(owner.getOwnerId(), new ArrayList<>());
        cars_by_owner.get(car.getOwnerId()).add(car);
        cars_by_brand.putIfAbsent(car.getBrand(), new ArrayList<>());
        cars_by_brand.get(car.getBrand()).add(car);
        cars_sorted_by_velocity.putIfAbsent(car, cars_sorted_by_velocity.size());
    }
}
