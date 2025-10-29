package tests;

public class TestRequest {


    static void sendRequest(RequestConfig config) {

        System.out.println(config.getUrl());
        System.out.println(config.getMethod());
        System.out.println(config.getBody());

    }

    public static void main(String[] args) throws InterruptedException {

        //  RequestConfig config = new RequestConfig.Builder().url("google.com").body("send mail").method("post").port("8080").Build();
        // sendRequest(config);

        User u1 = new User.BuildUser().age("12").address("afula").name("asher").Build();
        System.out.print(u1.toString());
        Car car1 = new Car.BuildCar("BENTLY", 3000, Car.EngineType.PETROL).buildCar();
        Car car2 = new Car.BuildCar("BMW", 2000, Car.EngineType.PETROL).setColor("Red").buildCar();
        User u2 = new User.BuildUser().name("dana").address("Haifa").Build();

        sellCars seller = new sellCars();

        seller.addObserver(u1);
        seller.addObserver(u2);
        seller.addCarForSale(car1);
        Thread.sleep(5000);
        seller.addCarForSale(car2);


    }

}
