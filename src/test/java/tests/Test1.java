package tests;

public class Test1 implements SevenOrFive {


    @Override
    public int getSevenOrFive(FiveOrDeven val) {
        return  val.getVal()==FiveOrDeven.Five.getVal()?FiveOrDeven.Seven.getVal() :FiveOrDeven.Five.getVal() ;
    }

   enum FiveOrDeven {
        Five(5),Seven(7);

       public int getVal() {
           return val;
       }
       private int val;

       FiveOrDeven(int i) {
         val = i;
       }
   }
}
