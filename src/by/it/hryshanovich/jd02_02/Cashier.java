package by.it.hryshanovich.jd02_02;

public class Cashier implements Runnable {
    private final String name;

    Cashier(int number) {
        name = "\tCashier №" + number;
    }

    private boolean isWait;

    public boolean isWait() {
        return isWait;
    }

    public void setWait(boolean wait) {
        isWait = wait;
    }

    @Override
    public void run() {
     /*   if(Dispatcher.countBuyersInQueue>25) {
            System.out.println("------------kek");
            startWorking();
        } */
        System.out.printf("%s opened\n", this);
        while (!Dispatcher.marketIsClosed()) {
            Buyer buyer = QueueBuyers.extract();
            if (buyer != null) {
                System.out.printf("%s started to service %s\n", this, buyer);
                Helper.mySleep(Helper.getRandom(2000, 5000));
                System.out.printf("%s finished to service %s\n", this, buyer);
                if(Dispatcher.countBuyersInQueue > 0 )
                    Dispatcher.decreaseCountBuyersInQueue();
                synchronized (buyer) {
                    buyer.setWait(false);
                    buyer.notifyAll();
                }
            } else {
                //???????
                Helper.mySleep(1);
            }
        }
        System.out.printf("%s closed\n", this);
     //   goToQueue();

    }

 /*   public void startWorking() {

    }
    public void goToQueue() {
        synchronized (this) {
            QueueCashiers.add(this);
            isWait = true;
            while (isWait) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    } */

    @Override
    public String toString() {
        return name;
    }
}
