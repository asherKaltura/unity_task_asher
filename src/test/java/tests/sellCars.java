package tests;

import java.util.ArrayList;
import java.util.List;

public class sellCars {
private List<Observer> observerList = new ArrayList<>();


    public void addObserver(Observer observer) {
        observerList.add(observer);
    }


    public void removeObserver(Observer observer) {
        observerList.remove(observer);
    }


    public void notifyObservers(String message) {
        for (Observer observer : observerList) {
            observer.update(message);
        }
    }

    public void addCarForSale(Car car) {
        String news = String.format(" new car %s , color %s  ",
                car.getBrand(), car.getColor());
        notifyObservers(news);
    }


}
