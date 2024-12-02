package com.ened.natskv.service;

import com.ened.natskv.util.Pair;
import io.nats.client.JetStreamApiException;
import io.nats.client.KeyValue;
import io.nats.client.api.KeyValueEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;


@Service
public class KeyValueService {
    @Autowired
    private KeyValue keyValueStore;
    public Optional<Pair<String,Long>> getValue(String key) {
        try {
            KeyValueEntry keyValueEntry = keyValueStore.get(key);
            if (keyValueEntry == null || keyValueEntry.getValue() == null) {
                return Optional.empty();
            }
            return Optional.of(new Pair<>(new String(keyValueEntry.getValue()),keyValueEntry.getRevision()));
        } catch (IOException | JetStreamApiException e) {
            throw new RuntimeException(e);
        }
    }
}
