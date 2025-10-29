package tests;

public class Temp {



    public static void main(String[] args) {

            Shape d = new Shape("Asher");

        }

    }
class Shape {
    private String type;

    public Shape(String type) {
        this.type = type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}