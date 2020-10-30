package by.it.tarasevich.jd02_03;

public class Cashier implements Runnable {
    private final String name;

    private final Dispatcher dispatcher;


    Cashier(int number, Dispatcher dispatcher) {
         name ="\t Cashier №"+ number;

        this.dispatcher = dispatcher;
    }

    @Override
    public void run() {
        System.out.printf("%s  opened\n", this);

        while (!dispatcher.marketIsCloused()){
            Buyer buyer = QueueBuyers.extract();
            if (buyer != null){
                System.out.printf("%s started to sevice %s\n", this, buyer);
                Helper.mySleep(Helper.getRandom(2000,5000));
                System.out.printf("%s finished to sevice %s\n", this, buyer);
                synchronized (buyer){
                    buyer.setWait(false);
                    buyer.notifyAll();
                }

            }
            else {
                Helper.mySleep(1);
            }
        }
        System.out.printf("%s closed\n", this);
    }

    @Override
    public String toString() {
        return name;
    }
}
