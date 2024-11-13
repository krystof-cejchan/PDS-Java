import java.util.concurrent.ThreadFactory;

public class UkazkaThread {
    public static void main(String[] args) throws InterruptedException {
        Thread virtual = Thread.ofVirtual().start(()->{
            System.out.println("virtualni vlakno");
        });

        Thread thread = new Thread(()->{
            System.out.println("klasicke vlakno");
        });

        ThreadFactory virtualFactory = Thread.ofVirtual().factory();
        Thread vt = virtualFactory.newThread(()->{
            System.out.println("nove virtualni vlakno");
        });
        ThreadFactory platformFactory = Thread.ofPlatform().factory();
        Thread pt = platformFactory.newThread(()->{
            System.out.println("nove klasicke vlakno");
        });

        thread.start();
        vt.start();
        pt.start();
        System.out.println("vsechna vlakna bezi");
        virtual.join();
        thread.join();
        vt.join();
        pt.join();
        System.out.println("vsechna vlakna skoncila!");
    }
}