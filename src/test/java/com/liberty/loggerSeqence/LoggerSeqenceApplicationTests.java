package com.liberty.loggerSeqence;

import com.liberty.loggerSeqence.contrller.LibertyController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoggerSeqenceApplicationTests {

    @Autowired
    private LibertyController libertyController;

    @Test
    public void contextLoads() throws ExecutionException, InterruptedException {
        int threads = 5;
        CountDownLatch countDownLatch = new CountDownLatch(threads);
        ExecutorService threadPool = Executors.newFixedThreadPool(threads);
        FutureTask[] tasks = new FutureTask[threads];
        for (int i = 0; i < threads; i++) {
            FutureTask task = new FutureTask(new Callable(){
                @Override
                public Object call() throws Exception {
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    libertyController.add();
                    return null;
                }
            });
            tasks[i] = task;
            threadPool.submit(task);
            countDownLatch.countDown();
        }
        for (int i = 0;  i < threads; i++) {
            System.out.println(tasks[i].get());
        }
    }

}
