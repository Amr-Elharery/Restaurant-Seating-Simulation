import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant(3);
        String currentDirectory = System.getProperty("user.dir");
        System.out.println("Current directory: " + currentDirectory);

        try (BufferedReader br = new BufferedReader(new FileReader(Paths.get(currentDirectory, "src/customers.txt").toString()))) {
            String line;
            long lastArrivalTime = 0;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                String entrance = parts[0];
                int customerId = Integer.parseInt(parts[1].split(" ")[1]);
                int arrivalTime = Integer.parseInt(parts[2].split(" ")[1]);
                int stayDuration = Integer.parseInt(parts[3].split(" ")[1]);

                Customer customer = new Customer(customerId, entrance, stayDuration, restaurant);

                // Sleep to simulate time delay between arrivals based on arrival time differences
                long delay = (arrivalTime - lastArrivalTime) * 1000;
                Thread.sleep(delay);
                lastArrivalTime = arrivalTime;

                // Start customer thread
                customer.start();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
