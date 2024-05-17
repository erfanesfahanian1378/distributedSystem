package mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttPublisher {
    public static final String BROKER_URL = "tcp://localhost:1883";
    private MqttClient client;

    public MqttPublisher(String clientId) throws MqttException {
        this.client = new MqttClient(BROKER_URL, clientId, new MemoryPersistence());
        connect();
    }

    private void connect() throws MqttException {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(10);
        client.connect(options);
    }

    public void publish(String topic, String message, int qos) throws MqttException {
        MqttMessage mqttMessage = new MqttMessage(message.getBytes());
        mqttMessage.setQos(qos);
        client.publish(topic, mqttMessage);
    }

    public void disconnect() throws MqttException {
        if (client != null) {
            client.disconnect();
        }
    }

    public static void main(String[] args) {
        try {
            MqttPublisher publisher = new MqttPublisher("AdminClient");
            publisher.publish("game/start", "The game has started!", 2);
            publisher.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
