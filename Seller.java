public class Seller {
    private AutoShow autoShow;
    final int timeoutCar = 3000;
    final int timeoutSell = 1000;

    public Seller(AutoShow autoShow) {
        this.autoShow = autoShow;
    }

    public synchronized void receiveCar() {
        try {
            Thread.sleep(timeoutCar);
            System.out.println("Производитель Toyota выпустил 1 авто");
            autoShow.getCars().add(new Car());
            notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized Car sellCar() {
        try {
            System.out.println(Thread.currentThread().getName() + " зашел в автосалон");
            Thread.sleep(timeoutSell);
            while (autoShow.getCars().size() == 0) {
                System.out.println("Автомобилей нет в наличии");
                wait();
            }
            System.out.println(Thread.currentThread().getName() + " уехал на новеньком авто ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return autoShow.getCars().remove(0);
    }
}
