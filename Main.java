public class Main {
    public static void main(String[] args) {
        final AutoShow autoShow = new AutoShow();

        for (int i = 0;i < 11 ; i++){
            new Thread(null, autoShow::sellCar, ("Покупатель " + i)).start();
            new Thread(null, autoShow::acceptCar, "Производитель").start();
        }
    }
}
