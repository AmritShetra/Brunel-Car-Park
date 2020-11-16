public class SharedCarParkState {

    private int carsParked;
    private boolean accessing = false;

    SharedCarParkState(int carsParked) {
        this.carsParked = carsParked;
    }

    // Attempt to acquire a lock
    public synchronized void acquireLock() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " is trying to acquire a lock!");
        while (accessing) {
            System.out.println(Thread.currentThread().getName() + " waiting to get a lock...");
            wait();
        }
        accessing = true;
        System.out.println(Thread.currentThread().getName() + " has a lock!");
    }

    // Release the lock when thread is finished
    public synchronized void releaseLock() {
        accessing = false;
        notifyAll();
        System.out.println(Thread.currentThread().getName() + " released a lock!");
    }

    // Increment or decrement `carsParked` depending on input
    public synchronized void setCarsParked(String input) {
        // TODO: Send a message back to signal if the car park is full
        //       Make sure that carsParked does not drop below 0

        if (carsParked == 5) {
            System.out.println("There are 5 cars parked, please wait in the queue.");
            return;
        }

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
