package Moje.MessageRabbitMq;

import com.rabbitmq.client.QueueingConsumer;

import java.util.concurrent.Callable;

// Callable<String>,
public class ReceiverThread implements Callable<String> {
    public QueueingConsumer consumer;

    public ReceiverThread(QueueingConsumer consumer) {
        super();
        this.consumer = consumer;
    }

    /**
     * only for runnable interface
     * We use callable for returning String for byzantine generals problem
     */
    @Override public String call() {
        QueueingConsumer.Delivery delivery = null;
        int i = 0;
        while (true) {
            try {
                delivery = consumer.nextDelivery();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String messageADS = new String(delivery.getBody());
            System.out.println(messageADS);

            return messageADS;
        }
    }

}
