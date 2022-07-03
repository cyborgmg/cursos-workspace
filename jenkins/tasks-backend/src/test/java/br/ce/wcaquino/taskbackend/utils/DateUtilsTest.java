package br.ce.wcaquino.taskbackend.utils;

import static org.junit.Assert.*;
import org.junit.Test;


import java.time.LocalDate;

public class DateUtilsTest {

    @Test
    public void deve_retornar_true_para_datas_futuras() {

        LocalDate date = LocalDate.of(2030, 01, 01);

        boolean result = DateUtils.isEqualOrFutureDate(date);

        assertTrue(result);

    }

    @Test
    public void deve_retornar_false_para_datas_passadas() {

        LocalDate date = LocalDate.of(2010, 01, 01);

        boolean result = DateUtils.isEqualOrFutureDate(date);

        assertFalse(result);

    }

    @Test
    public void deve_retornar_true_para_datas_atual() {

        LocalDate date = LocalDate.now();

        boolean result = DateUtils.isEqualOrFutureDate(date);

        assertTrue(result);

    }
}