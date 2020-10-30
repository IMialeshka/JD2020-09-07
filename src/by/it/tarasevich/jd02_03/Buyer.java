package by.it.tarasevich.jd02_03;

public class Buyer extends Thread implements IBuyer {
    private  final Dispatcher dispatcher;

    Buyer(int number, Dispatcher dispatcher){
        super("Buyer №" + number);
        this.dispatcher = dispatcher;
        dispatcher.buyerEnterToMarket();
        isWait = false;
    }
    private boolean isWait;

    public boolean isWait(){
        return isWait;
    }
    public  void setWait(boolean wait){
        isWait = wait;
    }



    @Override
    public void run() {
        enterToMarket();
        chooseSomfing();
        goToQueue();
        goOut();
        dispatcher.buyerOutToMarket();
    }

    @Override
    public void enterToMarket() {
        System.out.printf("%s enter market\n",this);
    }

    @Override
    public void chooseSomfing() {
        System.out.printf("%s sterted to choose\n",this);
        int timeout = Helper.getRandom(500,2000);
        Helper.mySleep(timeout);
        System.out.printf("%s finished to choose\n",this);
    }

    @Override
    public void goOut() {
        System.out.printf("%s leaved market\n",this);
    }

    @Override
    public void goToQueue() {
        System.out.printf("%s go to queue\n", this);
        synchronized (this){
            QueueBuyers.add(this);
            isWait = true;
            while (isWait){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
