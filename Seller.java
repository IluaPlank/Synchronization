import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Seller {
    Lock lock = new ReentrantLock(true);
    Condition condition = lock.newCondition();

    private AutoShow autoShow;
    final int timeoutCar = 3000;
    final int timeoutSell = 1000;

    public Seller(AutoShow autoShow) {
        this.autoShow = autoShow;
    }

    public void receiveCar() {
        try {
            lock.lock();
            Thread.sleep(timeoutCar);
            System.out.println("Производитель Toyota выпустил 1 авто");
            autoShow.getCars().add(new Car());
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public Car sellCar() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " зашел в автосалон");
            while (autoShow.getCars().size() == 0) {
                System.out.println("Автомобилей нет в наличии");
                condition.await();
            }
            Thread.sleep(timeoutSell);
            System.out.println(Thread.currentThread().getName() + " уехал на новеньком авто ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return autoShow.getCars().remove(0);
    }
}
