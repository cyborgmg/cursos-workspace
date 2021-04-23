package br.com.cyborg;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void olaMundo() {
        Main m = new Main();
        assertEquals("ola mundo", m.olaMundo());
    }
}