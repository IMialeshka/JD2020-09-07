package by.it.tarasevich.jd02_01;



import java.util.Map;

public class Buyer extends Thread implements IBuyer, IUseBasket {
  //grade buyer
    public Buyer(int number) {

        super("Buyer №" + number);
    }
    private boolean pensioneer=false;

    // sets pensionera
    public void setPensioneer() {
        pensioneer = true;
        System.out.printf("%s is pensioneer \n",this);
    }
   //start market , ochered and dispather
    @Override
    public void run() {
        if (Helper.getRandom(1,4)==1){setPensioneer();}
        enterToMarket();
        takeBasket();
        int numberOfGoods= Helper.getRandom(1,4);
        for (int i = 1; i <= numberOfGoods; i++) {
            chooseSomfing();
            putGoodsToBasket();
        }
        goOut();
        Dispatcher.BUYERS_IN_SHOP--;

    }

    @Override
    public void enterToMarket() {
        System.out.printf("%s enter market\n",this);
    }

    @Override
    public void chooseSomfing() {
        System.out.printf("%s started to choose\n",this);
        int goodInPrice= Helper.priceGoodSize();
        int numberGood= Helper.getRandom(1,goodInPrice);
        Map.Entry<String, Integer> randomGood = Helper.takeOneRandomGood(numberGood);
        System.out.printf("%s take %s for price %d\n",this, randomGood.getKey(), randomGood.getValue());
        int timeout;
        if (pensioneer){
            timeout= (int) (Helper.getRandom(500,2000)*1.5);
        }
        else {
            timeout= Helper.getRandom(500,2000);
        }
        Helper.mySleep(timeout);
        System.out.printf("%s finished to choose\n",this);
    }

    @Override
    public void goOut() {
        System.out.printf("%s left market\n",this);
    }

    @Override
    public String toString() {
        return (this.getName());
    }

    @Override
    public void takeBasket() {
        System.out.printf("%s take basket\n",this);
        int timeout;
        if (pensioneer){
            timeout= (int) (Helper.getRandom(500,2000)*1.5);
        }
        else {
            timeout= Helper.getRandom(500,2000);
        }
        Helper.mySleep(timeout);

    }

    @Override
    public void putGoodsToBasket() {
        System.out.printf("%s put good to basket\n",this);
        int timeout;
        if (pensioneer){
            timeout= (int) (Helper.getRandom(500,2000)*1.5);
        }
        else {
            timeout= Helper.getRandom(500,2000);
            }
        Helper.mySleep(timeout);
    }
}
