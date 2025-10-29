package tests;

import java.util.Objects;

public class Car {
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return fullTank == car.fullTank && numberOfDoors == car.numberOfDoors && engine == car.engine && Objects.equals(color, car.color) && Objects.equals(brand, car.brand) && engineType == car.engineType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullTank, color, brand, numberOfDoors, engine, engineType);
    }
    @Override
    public String toString() {
        return String.format("Car: %s engineType: %s  Color: %s",
                brand, engineType, color);
    }
    public int getFullTank() {
        return fullTank;
    }

    public String getColor() {
        return color;
    }

    public String getBrand() {
        return brand;
    }

    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    public int getEngine() {
        return engine;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    private int fullTank;
    private String color;
    private String brand;
    private int numberOfDoors;
    private int engine;
    private EngineType engineType;

    private Car(BuildCar buildCar) {

        fullTank =buildCar.fullTank ;
        color =buildCar.color ;
        brand =buildCar.brand ;
        numberOfDoors =buildCar.numberOfDoors ;
        engine =buildCar.engine ;
        engineType =buildCar.engineType ;

    }


    public static class BuildCar {
        private int fullTank;
        private String color;
        private String brand;
        private int numberOfDoors;
        private int engine;
        private double price;
        private EngineType engineType;
        public BuildCar(String brand, int engine,EngineType engineType) {
            this.brand = brand;
            this.engineType = engineType;
            this.engine = engine;
        }
        public BuildCar setNumberOfDoors(int numberOfDoors) {
            this.numberOfDoors = numberOfDoors;
            return this;
        }

        public BuildCar setEngine(int engine) {
            this.engine = engine;
            return this;
        }

        public EngineType getEngineType() {
            return engineType;
        }

        public BuildCar setEngineType(EngineType engineType) {
            this.engineType = engineType;
            return this;
        }

        public BuildCar setColor(String color) {
            this.color = color;
            return this;
        }

        public Car buildCar() {
          return  new Car(this);
        }

    }

    public enum EngineType { PETROL, DIESEL, ELECTRIC, HYBRID }

}
