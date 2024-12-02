package com.ened.natskv.controller;

import com.ened.natskv.service.KeyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class KeyValueController {
    @Autowired
    private KeyValueService keyValueService;

    @GetMapping("/kv/{key}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<String> getValue(@PathVariable("key") String key) {
        return keyValueService.getValue(key)
                .map(v-> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Revision", String.valueOf(v.value()))
                        .body(v.key())
                    )
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
