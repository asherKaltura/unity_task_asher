package tests;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import com.google.gson.JsonObject;

public class Main {
    public static BiFunction<Integer, Integer, Integer> lengthFunc = (a, b) -> a * b;
    static Supplier<Integer> ss = () -> 10 + (int) (Math.random() * (300 - 10));

    static Calc multi = (a, b) -> a * b;


    @FunctionalInterface
    interface Calc {
        int col(int a, int b);
    }

    private void check() {


    }

    static Consumer<String> printer = System.out::println;
    static  Function<Integer,Integer>  multipale = a-> a*2;

    static  Function<Integer,String>  numAsText = a-> a + "מספר:";

    public static void main(String[] args) {
Test1  c = new Test1();
        System.out.println(c.getSevenOrFive(Test1.FiveOrDeven.Seven));
        System.out.println(c.getSevenOrFive(Test1.FiveOrDeven.Five));

        checkPolindrom("aba");
        checkPolindrom("acfa");



        Consumer<Integer> f = (a) -> {

            a = 2;
        };

        f.accept(4);
        List<Integer> numbersList = Arrays.asList(1, 2, 3);

        System.out.println(transform(numbersList,multipale));
        System.out.println(transform(numbersList,numAsText));
        Supplier<Thread> d = () -> new Thread(() -> System.out.println("Running"));

        Thread t = new Thread(() -> System.out.println("Running"));
t.start();
        printer.accept("asher");
        changeStingToInt dd = (a) -> {

            try {
                return Integer.valueOf(a);
            } catch (NumberFormatException e) {

                return 0;
            }

        };
        Shape d1 = new Shape("Asher");
        changeShape df = (a, b) -> {
            a.setType(b);

        };
        df.changeType(d1, "Desta");
        ;
        System.out.println(dd.run("111s1"));

        ApiRequest n = new ApiRequest();
        n.setBody("{\"userId\":\"999\",\"productId\":\"111\"}");
        Class<Main> cls = Main.class;
        for (Method m : cls.getDeclaredMethods()) {
            System.out.println(m.getName());
        }
        System.out.println(ss.get());
        int ff = 90 + (int) (Math.random() * (101 - 90));
        printer.accept("fff");
        CustomerApiUtil.convertToC(n, r -> {
            r.addProperty("name", "asher");

            r.addProperty("age", 12);
            n.setBody(r.toString());
            ;
        });
        Consumer<String> printer = s -> System.out.println("Consumed: " + s);
        printer.accept("Hello Consumer!"); //

        convertTo(n, r -> {
            r.addProperty("name", "asher");

            r.addProperty("age", 12);
            n.setBody(r.toString());
            ;
        });
    }

    static void convertTo(ApiRequest request, Convert c) {

        c.run(JsonUtils.toJson(request.getBody()));

    }

    static class CustomerApiUtil {


        static void convertTo(ApiRequest request, Convert c) {

            c.run(JsonUtils.toJson(request.getBody()));

        }

        static void convertToC(ApiRequest request, Consumer<JsonObject> c) {

            c.accept(JsonUtils.toJson(request.getBody()));

        }

    }

    interface Convert {
        void run(JsonObject js);

    }


    static class JsonUtils {
        static final com.google.gson.JsonParser parser = new com.google.gson.JsonParser();

        static JsonObject toJson(String json) {
            return parser.parse(json).getAsJsonObject();
        }

        static String toString(JsonObject obj) {
            return obj.toString();
        }
    }


    static class ApiRequest {
        private String body;

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }
    }


    static class Box<T> {
        private T value;

        public void set(T v) {
            value = v;
        }

        public T get() {
            return value;
        }
    }

    @FunctionalInterface
    interface changeShape {

        void changeType(Shape shape, String value);

    }

    static class Shape {
        public void setType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }

        String type;

        Shape(String type) {

            this.type = type;

        }

    }

    static <T, R> List<R> transform(List<T> list, Function<T, R> func) {

        return list.stream().map(func).collect(Collectors.toList());

    }



    static  void checkPolindrom(String str){

        int last=str.length();
        for (int i =0; i <str.length();i++){
            if (str.substring(i,i+1).equalsIgnoreCase(str.substring(last-1,last))){
                last--;
            }else{
                System.out.println("the text "+ str + " not  is Polindrom");
         break;
            }
            if (i==last){
                System.out.println("the text "+ str + " is Polindrom");
                break;
            }
        }


    }



}



interface changeStingToInt {


    int run(String str);

}
