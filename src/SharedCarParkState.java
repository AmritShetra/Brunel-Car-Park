public class SharedCarParkState {

    private int carsParked;

    SharedCarParkState(int carsParked) {
        this.carsParked = carsParked;
    }

    // Attempt to acquire a lock
    public synchronized void acquireLock() {
        // TODO: Fill this out
    }

    // Release the lock when thread is finished
    public synchronized void releaseLock() {
        // TODO: Fill this out
    }

    // Increment or decrement `carsParked` depending on input
    public synchronized void setCarsParked(String input) {
        // TODO: Make sure that carsParked cannot exceed car park capacity
        //       Should send a message back to signal if the car park is full
        if (input.equals("e")) {
            carsParked += 1;
            System.out.println("Cars parked: " + carsParked);
        }
        else {
            carsParked -= 1;
            System.out.println("Cars parked: " + carsParked);
        }
    }
}
