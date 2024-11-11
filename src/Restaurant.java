import java.util.concurrent.Semaphore;

public class Restaurant {
    private Semaphore tables;

    public Restaurant(int seats){
        this.tables = new Semaphore(seats, true);
    }

    public void tryToSit(int customerId, String entrance) throws InterruptedException {
        if(this.tables.tryAcquire()){
            System.out.println("Customer " + customerId + " from " + entrance + " seated.");
        }
        else{
            System.out.println("Customer " + customerId + " from " + entrance + " waiting for a table.");
            tables.acquire();
            System.out.println("Customer " + customerId + " from " + entrance + " seated after waiting.");
        }
    }

    public void leave(int customerId, String entrance){
        System.out.println("Customer " + customerId + " from " + entrance + " left.");
        tables.release();
    }
}
