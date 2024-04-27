import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceDemo {


    public static void main(String[] args) throws InterruptedException {

        Runnable r1= () -> {
           // while(true) {
                System.out.println("In runnable 1 " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
           // }
        };

        Runnable r2= () -> {
         //   while (true) {
                System.out.println("In runnable 2 " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
          //  }
        };

        ExecutorService executorService= Executors.newFixedThreadPool(10);

        executorService.submit(r1);
        executorService.submit(r2);

       // executorService.awaitTermination(100, TimeUnit.MILLISECONDS);

    }
}
