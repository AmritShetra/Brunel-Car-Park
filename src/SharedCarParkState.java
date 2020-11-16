public class SharedCarParkState {

    private int carsParked = 0;
    private int carsQueued = 0;
    private boolean accessing = false;

    // Attempt to acquire a lock
    public synchronized void acquireLock() throws InterruptedException {
        String currentThread = Thread.currentThread().getName();
        System.out.println(currentThread + " is trying to acquire a lock!");
        while (accessing) {
            System.out.println(currentThread + " waiting to get a lock...");
            wait();
        }
        accessing = true;
        System.out.println(currentThread + " has a lock!");
    }

    // Release the lock when thread is finished
    public synchronized void releaseLock() {
        accessing = false;
        notifyAll();
        System.out.println(Thread.currentThread().getName() + " released a lock!");
    }

    // Increment or decrement `carsParked` depending on input
    public synchronized String processInput(String input) {
        // If car is entering
        if (input.equals("e")){
            if (carsParked == 5) {
                carsQueued+= 1;
                return("The car park is full, so you have been placed in the queue at no. " + carsQueued);
            }
            else {
                carsParked += 1;
                return("A car has entered the car park. Cars parked = " + carsParked);
            }
        }

        // If car is leaving
        else {
            if (carsParked == 0) {
                return("The car park is empty, so there are no cars that can leave.");
            }
            else {
                if (carsQueued > 0) {
                    carsQueued -= 1;
                    return("A car has left the car park and one from the queue has joined.");
                }
                carsParked -= 1;
                return("A car has left the car park. Cars parked = " + carsParked);
            }

        }
    }
}
