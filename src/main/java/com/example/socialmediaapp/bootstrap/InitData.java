package com.example.socialmediaapp.bootstrap;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Log4j2
@Data
@Component
@RequiredArgsConstructor
public class InitData implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        log.atWarn().log("Initializing data");

    }
}
