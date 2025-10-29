package tests;

public class User  implements Observer{

    private String name;
    private String age;
    private String address;
    private User(BuildUser buildUser){

        this.name = buildUser.name;
        this.age = buildUser.age;
        this.address =buildUser.address;


    }

    @Override
    public void update(String news) {

        System.out.println("📩 למשתמש " + name + " (" + address +    news + ") נשלח עדכון:");

    }

    public static class   BuildUser {
        private String name;
        private String age;
        private String address;

        public BuildUser name(String name){

            this.name = name;
          return this;
        }

        public BuildUser age(String age){


            this.age = age;
            return this;
        }

        public BuildUser address(String address){

            this.address = address;
            return this;
        }


        public User Build(){

            return  new User(this);

        }



    }
    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }
    public String toString() {
        return "name: "  + this.getName() +    " age: "  + this.getAge() +  " address: "  + this.getAddress()   ;
    }

}







