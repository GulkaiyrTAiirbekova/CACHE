package FastFoodRestaurant;

public class FastFoodRestaurant {
    int burger;
    public int getBurger(){
        return burger;
    }
    public void setBurger(int burger){
        this.burger = burger;
    }
    int fries;
    public int getFries(){
        return fries;
    }
    public void setFries(int fries){
        this.fries = fries;
    }
    int coke;
    public int getCoke(){
        return coke;
    }
    public void setCoke(int coke){
        this.coke = coke;
    }
    int toy;
    public int getToy(){
        return toy;
    }
    public void setToy(int toy){
        this.toy = toy;
    }

    @Override
    public String toString(){
        return "FastFoodRestaurant{" +
                "burger = " + burger +
                ",fries = " + fries +
                ", coke = " + coke +
                ", toy = " + toy +
                '}' ;
    }
}