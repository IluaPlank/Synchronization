import java.util.ArrayList;
import java.util.List;

class AutoShow {
    final Seller seller = new Seller(this);
    List<Car> cars = new ArrayList<>();

    public Car sellCar(){
        return seller.sellCar();
    }
    public void acceptCar(){
        seller.receiveCar();
    }
    List<Car> getCars(){
        return cars;
    }
}



