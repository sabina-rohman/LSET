package uk.lset;

public class Passenger {
    private String name;
    private int age;
    private boolean hasRailCard;

    public Passenger(String name, int age, boolean hasRailCard){
        this.name = name;
        this.age = age;
        this.hasRailCard = hasRailCard;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean getHasRailCard() {
        return hasRailCard;
    }

    public double getDiscountRate(){
        if(age >= 60){
            return 0.3;
        }else if(age >= 16 && age <= 25 && hasRailCard){
            return 0.2;
        } else if(age < 16){
            return 0.5;
        }else{
            return 0;
        }
    }

    public String getDiscountString(){
        if(getDiscountRate() == 0.3){
            return "30%";
        }else if(getDiscountRate() == 0.2){
            return "20%";
        }else if(getDiscountRate() == 0.5){
            return "50%";
        }else{
            return "Zero discount";
        }
    }

    @Override
    public String toString() {
        return "Passenger: " +
                "name='" + name + '\'' +
                ", age=" + age +
                ", hasRailCard=" + hasRailCard;
    }
}
