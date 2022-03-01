package io.tacsio.rabbitmq.topic;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

import java.util.Random;

public class RabbitReceiver {


    @RabbitListener(queues = "#{autoDeleteQueue1.name}")
    public void receiver1(String in) throws InterruptedException {
        receive(in, 1);
    }

    @RabbitListener(queues = "#{autoDeleteQueue2.name}")
    public void receiver2(String in) throws InterruptedException {
        receive(in, 2);
    }

    public void receive(String in, int receiver) throws InterruptedException {
        StopWatch watch = new StopWatch();
        watch.start();

        System.out.println("instance " + receiver + " [x] Received '" + in + "'");

        doWork();
        watch.stop();
    }


    private void doWork() throws InterruptedException {
        var random = new Random();
        var ts = random.nextInt(1000, 5000);
        Thread.sleep(ts);
    }

}
