package br.com.cyborg;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainIntTest {

    @Test
    void slowTest() throws InterruptedException {
        Thread.sleep(20000);
    }
}