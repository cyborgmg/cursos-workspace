package br.com.cyborg;

import java.util.Calendar;
import java.util.List;

public class GeradorDeNotaFiscal {

    private NFDao dao;

    private SAP sap;

    private List<AcaoAposGerarNota> acoes;

    public GeradorDeNotaFiscal() {}

    public GeradorDeNotaFiscal(List<AcaoAposGerarNota> acoes) {
        this.acoes = acoes;
    }

    public GeradorDeNotaFiscal(NFDao dao) {
        this.dao = dao;
    }


    public GeradorDeNotaFiscal(NFDao dao, SAP sap) {
        this.dao = dao;
        this.sap = sap;
    }

    public NotaFiscal gera(Pedido pedido) {
        NotaFiscal nf = new NotaFiscal(
                pedido.getCliente(),
                pedido.getValorTotal() * 0.94,
                Calendar.getInstance());
        for(AcaoAposGerarNota acao : acoes) {
            acao.executa(nf);
        }
        return nf;
    }

}