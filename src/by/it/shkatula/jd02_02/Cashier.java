package by.it.shkatula.jd02_02;

public class Cashier implements Runnable {

    private String name;

    Cashier(int number) {
        name = "\tCashier №" + number;
    }

    @Override
    public void run() {

        System.out.printf("%s opened\n", this);

        while (!Dispatcher.marketIsClosed()) {
            Buyer buyer = QueueBuyers.extract();
            if (buyer != null) {
                    System.out.printf("%s started serving %s\n", this, buyer);
                Helper.mySleep(Helper.getRandom(2000, 5000));
                System.out.printf("%s ends service for %s\n", this, buyer);
                synchronized (buyer){
                    buyer.setWait(false);
                    buyer.notifyAll();
                }
            }
            else{
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
