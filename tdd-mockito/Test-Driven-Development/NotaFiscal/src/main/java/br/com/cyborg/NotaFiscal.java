package br.com.cyborg;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotaFiscal {

    private String cliente;
    private double valor;
    private Calendar data;

}
