public class Main {
    public static void main(String[] args) {
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
        System.out.println(carGarage.removeCar(15));
        System.out.println(carGarage.allCarsOfBrand("dfsfA"));
        System.out.println(carGarage.allCarsOfOwner(tmp_owner));
        System.out.println(carGarage.carsWithPowerMoreThan(115));
        System.out.println(carGarage.allCarsUniqueOwners());
        System.out.println(carGarage.meanCarNumberForEachOwner());
        System.out.println(carGarage.meanOwnersAgeOfCarBrand("dfsfA"));
        System.out.println(carGarage.topThreeCarsByMaxVelocity());
        System.out.println(carGarage.removeCar(1));
    }
}