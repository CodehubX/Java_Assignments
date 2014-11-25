package Moje.MessageRabbitMq;

import com.rabbitmq.client.QueueingConsumer;

public class ReceiverThread implements Runnable {
    public QueueingConsumer consumer;

    public ReceiverThread(QueueingConsumer consumer) {
        super();
        this.consumer = consumer;
    }

    public void run() {
        QueueingConsumer.Delivery delivery = null;
        while (true) {
            try {
                delivery = consumer.nextDelivery();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String message = new String(delivery.getBody());
            //                String userID = message.split(":")[0];
            //                userID.equalsIgnoreCase(String.valueOf(this.userID));
            //                System.out.print(tm.toString() + "::::" + consumer.getConsumerTag() + "::::'" + message + "'\n");
            System.out.println(message);
        }
    }
}
