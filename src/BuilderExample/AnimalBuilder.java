package BuilderExample;

public class AnimalBuilder {
    private Animal  animal;

    public AnimalBuilder(){
        animal = new Animal();
    }

    public AnimalBuilder setAge(int age){
        animal.setAge(age);
        return this;
    }

    public AnimalBuilder setWeight(int weight){
        animal.setWeight(weight);
        return this;
    }

    public AnimalBuilder setHeight (int height){
        animal.setHeight(height);
        return this;
    }

    public AnimalBuilder setName(String name){
        animal.setName(name);
        return this;
    }
    public Animal build(){
        return animal;
    }
}

