import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class BellaMartaMain {
    ArrayList<BellaMartaCar> cars = new ArrayList<>();

    public static void main(String[] args) {
        BellaMartaMain main = new BellaMartaMain();
        main.inicio();
    }

    public void inicio(){
        System.out.println("Comienza programa");
        read();
        int opcionMenu = 0;
        final int MAX_NUM_CARS = 5;
        do {
            opcionMenu = getIntFromConsole("Bienvenido. Selecciona una opcion \n [1] Ver coches \n [2] Añadir coche \n [3] Salir", 1, 3);
            switch (opcionMenu){
                case 1:
                    read();
                    if (cars.isEmpty()){
                        System.out.println("No hay coches en el fichero");
                    }
                    break;
                case 2:
                    if (cars.size()==MAX_NUM_CARS){
                        System.out.println("Lo siento. Numero máximo coches conseguido");
                    }else if (addCar()) {
                        write();
                    }
                    break;
            }
        }while(opcionMenu!=3);
        System.out.println("Programa terminado");
    }

    public boolean addCar(){
        String plate;
        String brand;
        String model;
        int seats;


        plate = getStringFromConsole("Introduce matricula: ");
        if (isPlateDuplicated(plate)){
            System.out.println("Lo siento, ya existe esta matrícula");
            return false;
        }else {
            brand = getStringFromConsole("Introduce marca: ");
            model = getStringFromConsole("Introduce modelo");
            seats = getIntFromConsole("Introduce número asientos", 1, 5);

            BellaMartaCar car = new BellaMartaCar(brand, model, seats, plate);
            cars.add(car);
            return true;
        }
    }

    public boolean isPlateDuplicated(String plate){
        for (BellaMartaCar car : cars){
            if (car.getPlate().equals(plate)){
                return true;
            }
        }
        return false;
    }

    /**
     * Solicita valor String introducido por consola que tenga caracteres
     * @param mensaje texto que se muestra por consola para pedir el valor String
     * @return String que contenga caracteres
     */
    public String getStringFromConsole(String mensaje){
        Scanner input = new Scanner(System.in);
        String texto;
        do {
            System.out.println(mensaje);
            texto = input.nextLine();
        }while(texto.isBlank());
        return texto;
    }


    /**
     * Solicita valor entero introducido por consola comprendido entre valueMin y valueMax
     * @param mensaje texto que se muestra por consola para pedir el valor entero
     * @param valueMin valor mínimo entero aceptado
     * @param valueMax valor máximo entero aceptado
     * @return valor entero comprendido entre valueMin y valueMax
     */
    public int getIntFromConsole(String mensaje, int valueMin, int valueMax){
        Scanner input= new Scanner(System.in);
        int value = 0;
        boolean exit = false;
        do{
            System.out.println(mensaje);
            if (input.hasNextInt()){
                value = input.nextInt();
                input.nextLine();
                if (value>=valueMin&&value<=valueMax){
                    exit = true;
                }
            }else{
                System.out.println("Error. Debes introducir un valor entero");
                input.nextLine();
            }
        }while(!exit);
        return value;
    }
    public void loadDummyData(){
        System.out.println("Creando array de objetos car");
        cars.add(new BellaMartaCar("Opel", "Zafira", 5, "XTZ345"));
        cars.add(new BellaMartaCar("Ford", "Fiesta", 5, "XTZ346"));
        cars.add(new BellaMartaCar("Ford", "Focus", 5, "XTZ347"));
        System.out.println("Array creado con "+cars.size()+" elementos");
    }
    //Writing a byte file (objects.data)
    public void write() {

        try(FileOutputStream fileOut = new FileOutputStream("objects.data");
        ObjectOutputStream output = new ObjectOutputStream(fileOut)) {
            for (BellaMartaCar car: cars) {
                output.writeObject(car);
            }

            System.out.println("Operación de escritura en fichero terminada");
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public void read(){
        cars = new ArrayList<>();
        System.out.println("Empezando con operación de lectura de información desde fichero. Mostrando datos....");
        try(FileInputStream fileIn = new FileInputStream("objects.data");
        ObjectInputStream input = new ObjectInputStream(fileIn)) {
            while (true) {
                BellaMartaCar car = (BellaMartaCar) input.readObject();
                System.out.println(car.toString());
                cars.add(car);
            }
        }catch(Exception e){
            //System.out.println(e);
        }
        System.out.println("Operación de lectura terminada");
    }
}