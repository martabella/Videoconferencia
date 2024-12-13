import java.io.Serializable;

public class BellaMartaCar implements Serializable {
    private String brand;
    private String model;
    private int seats;
    private String plate;


    public BellaMartaCar(String brand, String model, int seats, String plate) {
        this.brand = brand;
        this.model = model;
        this.seats = seats;
        this.plate= plate;
    }

    public String getPlate() {
        return plate;
    }

    public String toString() {
        return "Car{" +
                "plate=" + plate +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", seats=" + seats +
                '}';
    }
}
