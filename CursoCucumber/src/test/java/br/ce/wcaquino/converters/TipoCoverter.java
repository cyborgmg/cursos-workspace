package br.ce.wcaquino.converters;

import br.ce.wcaquino.entidades.TipoAluguel;
import cucumber.api.Transformer;

public class TipoCoverter extends Transformer<TipoAluguel> {

	@Override
	public TipoAluguel transform(String value) {
		
		TipoAluguel result;
		
		try {
			
			switch (value) {
			case "EXTENDIDO":
				result = TipoAluguel.EXTENDIDO;
				break;
			case "SEMANAL":
				result =  TipoAluguel.SEMANAL;
				break;
			default :
				result =  TipoAluguel.COMUM;
				break;
			}
			
			return result;
			
		} catch (Exception e) {
			return TipoAluguel.COMUM;
		} 
		
		
	}

}
