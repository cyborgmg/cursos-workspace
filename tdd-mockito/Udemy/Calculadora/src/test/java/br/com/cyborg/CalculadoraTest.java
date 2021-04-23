package br.com.cyborg;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CalculadoraTest {

    @Test
    @Order(0)
    void soma1() {

        assertTrue(new Calculadora().soma(10,10)==20);
    }

    @Test
    @Order(1)
    void soma2() {

        assertTrue(new Calculadora().soma(10,10)==20);
    }

    @Test
    @Order(2)
    void soma3() {

        assertTrue(new Calculadora().soma(10,10)==20);
    }

    @Test
    @Order(3)
    void divide() {

        assertThrows(ArithmeticException.class, () -> {
            Calculadora cal = new Calculadora();
            cal.divide(10,0);
        });
    }

    //@Test
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    void timeOut() {

        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {}

        assertTrue(true);
    }

}