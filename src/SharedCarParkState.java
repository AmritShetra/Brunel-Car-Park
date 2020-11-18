class SharedCarParkState {

    private int carsParked = 0;
    private int carsQueued = 0;
    private String message = "";
    private boolean accessing = false;

    // Attempt to acquire a lock
    synchronized void acquireLock() throws InterruptedException {
        String thread = Thread.currentThread().getName();
        System.out.println(thread + " is trying to acquire a lock!");
        while (accessing) {
            System.out.println(thread + " waiting to get a lock...");
            wait();
        }
        accessing = true;
        System.out.println(thread + " has a lock!");
    }

    // Release the lock when thread is finished
    synchronized void releaseLock() {
        String thread = Thread.currentThread().getName();
        accessing = false;
        notifyAll();
        System.out.println(thread + " released a lock!");
    }

    // Check the input process (e for enter, l for leave), and edit shared variables as necessary.
    synchronized String processInput(String input) {
        switch (input) {
            case "e":
                message = enter();
                break;
            case "l":
                if (carsParked == 0) {
                    message = "The car park is empty, so no cars can leave.";
                    break;
                }
                else {
                    message = leave();
                    break;
                }
        }
        return message;
    }

    /**
     * If there are already 5 cars parked, add one to the queue. Else, increment carsParked.
     * @return A string showing the state of the car park.
     */
    private synchronized String enter() {
        if (carsParked == 5) {
            carsQueued += 1;
            return("The car park is full, so you have been placed in the queue at no. " + carsQueued);
        }
        else {
            carsParked += 1;
            return("A car has entered the car park. Cars parked = " + carsParked);
        }
    }

    /**
     * If there are cars queued, replace the leaving car with one from the queue. Else, decrement carsParked.
     * @return A string showing the state of the car park.
     */
    private synchronized String leave() {
        if (carsQueued > 0) {
            carsQueued -= 1;
            return("A car has left the car park and one from the queue has joined. " +
                    "Current queue: " + carsQueued + ". Cars parked = " + carsParked);
        }
        else {
            carsParked -= 1;
            return("A car has left the car park. Cars parked = " + carsParked);
        }
    }

}
