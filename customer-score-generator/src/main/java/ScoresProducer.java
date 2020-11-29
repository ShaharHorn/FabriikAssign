import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.Random;

public class ScoresProducer {
    Random rand = new Random();
    static final int number_of_customers = 1000000;
    public ScoresProducer() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

       while (true) {
            int id = 1 + rand.nextInt(number_of_customers);
            String score = String.valueOf(1 + rand.nextInt(100));

            if( id < number_of_customers/3 ) {
                ProducerRecord producerRecord = new ProducerRecord("customers", 0, "11", "111");
                KafkaProducer kafkaProducer = new KafkaProducer(properties);
                kafkaProducer.send(producerRecord);
            }
            else if( id < (number_of_customers*2/3)) {
                ProducerRecord producerRecord1 = new ProducerRecord("customers", 1, String.valueOf(id), score);
                KafkaProducer kafkaProducer1 = new KafkaProducer(properties);
                kafkaProducer1.send(producerRecord1);
            }
            else {
                ProducerRecord producerRecord2 = new ProducerRecord("customers", 2, String.valueOf(id), score);
                KafkaProducer kafkaProducer2 = new KafkaProducer(properties);
                kafkaProducer2.send(producerRecord2);
            }
        }
    }
}
