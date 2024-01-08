
public class CarImplementation implements Car {
    private String brand = null;
    private String model = null;
    private int year;
    private float price;
    private float mileage;
    

    public CarImplementation(String brand, String model, int year, float price, float mileage) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
        this.mileage = mileage;

    }

    @Override
    public int compareTo(Car o) {
        if (this.mileage > o.getMileage()) {
            return 1;
        }
        else if (this.mileage < o.getMileage()) {
            return -1;
        }
        else {
            return 0; 
        }
    }
    @Override
    public boolean equals(Object o) {
        if (o instanceof Car) {
            Car c = (Car)o;
            return (c.getPrice() == this.getPrice() && c.getBrand().equals(this.getBrand())
                    && c.getMileage() == this.getMileage() && c.getModel().equals(this.getModel())
                    && c.getYear() == this.getYear());      
        }                     
        else {
            return false;
        }
        
    }

    @Override
    public String getBrand() {
        return this.brand;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public int getYear() {
        return this.year;
    }

    @Override
    public float getPrice() {
        return this.price;
    }

    @Override
    public float getMileage() {
        return this.mileage;
    }
    
    @Override 
    public String toString() {
        return this.brand + "," + this.model + "," + this.year
                + "," + this.price + "," + this.mileage;
    }

}
