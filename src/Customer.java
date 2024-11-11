public class Customer extends Thread{
    private int customerId;
    private String entrance;

    // This stay duration by second
    private int stayDuration;
    private Restaurant restaurant;

    public Customer(int customerId, String entrance, int stayDuration, Restaurant restaurant) {
        this.customerId = customerId;
        this.entrance = entrance;
        this.stayDuration = stayDuration;
        this.restaurant = restaurant;
    }

    @Override
    public void run(){
        try{
            restaurant.tryToSit(this.customerId, this.entrance);
            Thread.sleep(stayDuration * 1000L);
            restaurant.leave(this.customerId, this.entrance);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
