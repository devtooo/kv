package com.ened.natskv.config;

import io.nats.client.*;
import io.nats.client.api.KeyValueConfiguration;
import io.nats.client.api.KeyValueStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class NastConfig {
    @Value("${app.nats.bucketName}")
    private String natsKvStoreName;

    @Value("${app.nats.server.url}")
    private String natsServerUrl;

    @Bean
    Connection natsConnection() throws IOException, InterruptedException {
        return Nats.connect(natsServerUrl);
    }

    /**
     * Init of the key value store
     * @param natsConnection
     * @return
     * @throws IOException
     * @throws JetStreamApiException
     */
    @Bean
    public KeyValue keyValueStore(Connection natsConnection) throws IOException, JetStreamApiException {
//        KeyValueManagement kvm = natsConnection.keyValueManagement();
//        KeyValueConfiguration kvc = KeyValueConfiguration.builder()
//                .name(natsKvStoreName)
//                .build();
//        KeyValueStatus keyValueStatus = kvm.create(kvc);
        return natsConnection.keyValue(natsKvStoreName);
    }

}
