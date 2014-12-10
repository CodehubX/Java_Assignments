package Moje.MessageRabbitMq;

import com.rabbitmq.client.QueueingConsumer;

// Callable<String>,
public class ReceiverThread implements Runnable {
    public QueueingConsumer consumer;
    public String message;

    public ReceiverThread(QueueingConsumer consumer) {
        super();
        this.consumer = consumer;
    }

    /**
     * only for runnable interface
     * We use callable for returning String for byzantine generals problem
     */
    @Override public void run() {
        QueueingConsumer.Delivery delivery = null;
        while (true) {
            try {
                delivery = consumer.nextDelivery();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String messageADS = new String(delivery.getBody());
            //                String userID = message.split(":")[0];
            //                userID.equalsIgnoreCase(String.valueOf(this.userID));
            //                System.out.print(tm.toString() + "::::" + consumer.getConsumerTag() + "::::'" + message + "'\n");
            System.out.println(messageADS);
            this.message = messageADS;
        }
    }

}
