package br.com.cyborg;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
//@AllArgsConstructor
public class Retangulo {

    private int base;
    private int altura;

    public Retangulo(int base, int altura) {
        this.base = base;
        this.altura = altura;
    }

    public int calcularArea(){
        return base * altura;
    }

    public int calcularPerimetro(){
        return 2*base + 2*altura;
    }

}
