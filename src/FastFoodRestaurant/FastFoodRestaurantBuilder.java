package FastFoodRestaurant;

public class FastFoodRestaurantBuilder {
    private FastFoodRestaurant fastFoodRestaurant;

    public FastFoodRestaurantBuilder() {
        fastFoodRestaurant = new FastFoodRestaurant();
    }

    public FastFoodRestaurantBuilder buildKidsMenu() {
        fastFoodRestaurant.setBurger(1);
        fastFoodRestaurant.setFries(1);
        fastFoodRestaurant.setCoke(1);
        fastFoodRestaurant.setToy(1);
        return this;
    }

    public FastFoodRestaurantBuilder buildChristmasMenu() {
        fastFoodRestaurant.setBurger(fastFoodRestaurant.getBurger() + 12);
        fastFoodRestaurant.setFries(fastFoodRestaurant.getFries() + 14);
        fastFoodRestaurant.setCoke(fastFoodRestaurant.getCoke() + 20);
        // toy is optional
        return this;
    }

    public FastFoodRestaurant build() {
        return fastFoodRestaurant;
    }
}

