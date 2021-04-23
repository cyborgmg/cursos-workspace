package br.ce.wcaquino.servicos;

import java.util.Calendar;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.NotaAluguel;
import br.ce.wcaquino.entidades.TipoAluguel;
import br.ce.wcaquino.utils.DateUtils;

public class AluguelService {

	public NotaAluguel alugar(Filme filme, TipoAluguel tipoAluguel) {
		
		if(filme.getEstoque()==0) {
			throw new RuntimeException("Filme sem estoque");
		}
		
		NotaAluguel notaAluguel = new NotaAluguel();
		Calendar cal = DateUtils.getCurrentCalendar();
		
		switch (tipoAluguel) {
		case COMUM:
			notaAluguel.setPreco(filme.getAluguel());
			cal.add(Calendar.DAY_OF_MONTH,1);
			notaAluguel.setPotuacao(1);
			break;
		case EXTENDIDO:
			notaAluguel.setPreco(filme.getAluguel()*2);
			cal.add(Calendar.DAY_OF_MONTH,3);
			notaAluguel.setPotuacao(2);
			break;
		case SEMANAL:
			notaAluguel.setPreco(filme.getAluguel()*3);
			cal.add(Calendar.DAY_OF_MONTH,7);
			notaAluguel.setPotuacao(3);
			break;
		}
		
		notaAluguel.setDataEntrega(cal.getTime());
		
		filme.setEstoque(filme.getEstoque()-1);
		
		return notaAluguel;
	}

	
}
