package springboot.demo.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
public class MyConfig {

    @Bean
    public TransportClient client() throws UnknownHostException {
        InetSocketTransportAddress node1 = new InetSocketTransportAddress(
                InetAddress.getByName("192.168.123.123"), 9300
        );
        InetSocketTransportAddress node2 = new InetSocketTransportAddress(
                InetAddress.getByName("192.168.123.123"), 9301
        );
        InetSocketTransportAddress node3 = new InetSocketTransportAddress(
                InetAddress.getByName("192.168.123.123"), 9302
        );

        Settings settings = Settings.builder().put("cluster.name", "elasticsearch-cluster").build();

        TransportClient client = new PreBuiltTransportClient(settings);
        client.addTransportAddress(node1);
        client.addTransportAddress(node2);
        client.addTransportAddress(node3);
        return client;
    }
}
