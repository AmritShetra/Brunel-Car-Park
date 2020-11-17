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

    // Increment or decrement carsParked depending on input (e for enter, l for leave)
    synchronized String processInput(String input) {
        switch (input) {
            case "e":
                if (carsParked == 5) {
                    carsQueued += 1;
                    message = "The car park is full, so you have been placed in the queue at no. " + carsQueued;
                    break;
                }
                else {
                    carsParked += 1;
                    message = "A car has entered the car park. Cars parked = " + carsParked;
                    break;
                }
            case "l":
                if (carsParked == 0) {
                    message = "The car park is empty, so there are no cars that can leave.";
                    break;
                }
                else if (carsQueued > 0) {
                    // Car leaves, but is replaced by one from the queue (so we don't edit carsParked)
                    carsQueued -= 1;
                    message = "A car has left the car park and one from the queue has joined. Current queue: "
                            + carsQueued + ". Cars parked = " + carsParked;
                    break;
                }
                else {
                    carsParked -= 1;
                    message = "A car has left the car park. Cars parked = " + carsParked;
                    break;
                }
        }

        return message;
    }

}
