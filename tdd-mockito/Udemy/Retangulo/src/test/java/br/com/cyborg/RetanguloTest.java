package br.com.cyborg;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class RetanguloTest {

    private Retangulo retangulo;

    @BeforeEach
    void beforeEach(){
        retangulo = new Retangulo(10,2);
    }

    @Test
    void calcularArea() {

        assertEquals(20, this.retangulo.calcularArea());
    }



    static class Numbers {

        public static boolean isOdd(int number) {
            return number % 2 != 0;
        }
    }
    @ParameterizedTest
    @CsvSource({"10,20,200", "10,30,300"})
    void calcularArea2(int base, int altura, int area ) {

        retangulo = new Retangulo(base,altura);
        assertEquals(area, this.retangulo.calcularArea());
    }

    public static Collection<Object[]> parametros() {
        return Arrays.asList(
                new Object[][]{
                        {10,20,200},
                        {10,30,300},
                        {10,40,400},
                        {10,50,500},
                }
        );
    }

    @ParameterizedTest
    @MethodSource("parametros")
    void calcularArea3(int base, int altura, int area ) {

        retangulo = new Retangulo(base,altura);
        assertEquals(area, this.retangulo.calcularArea());
    }

    static class RetParams{
        int base;
        int altura;
        int area;

        public RetParams(int base, int altura, int area) {
            this.base = base;
            this.altura = altura;
            this.area = area;
        }

        public int getBase() {
            return base;
        }

        public int getAltura() {
            return altura;
        }

        public int getArea() {
            return area;
        }
    }
    public static Collection<RetParams> retParams() {
        return Arrays.asList(
                new RetParams(10,20,200),
                new RetParams(10,30,300),
                new RetParams(10,40,400)
        );
    }

    @ParameterizedTest
    @MethodSource("retParams")
    void calcularArea4(RetParams retParams) {

        retangulo = new Retangulo(retParams.getBase(),retParams.getAltura());
        assertEquals(retParams.getArea(), this.retangulo.calcularArea());
    }

    @Test
    void calcularPerimetro() {

        assertEquals(24, this.retangulo.calcularPerimetro());
    }
}