/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgparcat.controllers;

import br.com.sgparcat.models.Lancamento;
import br.com.sgparcat.repositories.Lancamentos;
import br.com.sgparcat.services.LancamentoService;
import br.com.sgparcat.util.jsf.FacesUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author pedrohensanches
 */
@Named
@ViewScoped
public class GerenciarLancamentoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Lancamentos repositorioLancamentos;

    @Inject
    private LancamentoService lancamentoService;

    private BigDecimal valorTotal;

    private List<Lancamento> lancamentos;

    //FILTROS
    private String inputPesquisa;

    private Character tipoLancamentoSelecionado;

    private int mesSelecionado;

    private int anoSelecionado;

    private int periodoSelecionado;

    private int mesInicio;

    private int anoInicio;

    private int mesFim;

    private int anoFim;
    
    private List<Integer> anos;

    @PostConstruct
    public void GerenciarLancamentoBean() {
        listarLancamentos();
        anos = Anos();
        periodoSelecionado = 1;
    }

    public List<Lancamento> getLancamentos() {
        return lancamentos;
    }

    public void setLancamentos(List<Lancamento> lancamentos) {
        this.lancamentos = lancamentos;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getInputPesquisa() {
        return inputPesquisa;
    }

    public void setInputPesquisa(String inputPesquisa) {
        this.inputPesquisa = inputPesquisa;
    }

    public Character getTipoLancamentoSelecionado() {
        return tipoLancamentoSelecionado;
    }

    public void setTipoLancamentoSelecionado(Character tipoLancamentoSelecionado) {
        this.tipoLancamentoSelecionado = tipoLancamentoSelecionado;
    }

    public int getMesSelecionado() {
        return mesSelecionado;
    }

    public void setMesSelecionado(int mesSelecionado) {
        this.mesSelecionado = mesSelecionado;
    }

    public int getAnoSelecionado() {
        return anoSelecionado;
    }

    public void setAnoSelecionado(int anoSelecionado) {
        this.anoSelecionado = anoSelecionado;
    }

    public int getPeriodoSelecionado() {
        return periodoSelecionado;
    }

    public void setPeriodoSelecionado(int periodoSelecionado) {
        this.periodoSelecionado = periodoSelecionado;
    }

    public int getMesInicio() {
        return mesInicio;
    }

    public void setMesInicio(int mesInicio) {
        this.mesInicio = mesInicio;
    }

    public int getAnoInicio() {
        return anoInicio;
    }

    public void setAnoInicio(int anoInicio) {
        this.anoInicio = anoInicio;
    }

    public int getMesFim() {
        return mesFim;
    }

    public void setMesFim(int mesFim) {
        this.mesFim = mesFim;
    }

    public int getAnoFim() {
        return anoFim;
    }

    public void setAnoFim(int anoFim) {
        this.anoFim = anoFim;
    }

    public List<Integer> getAnos() {
        return anos;
    }

    public void setAnos(List<Integer> anos) {
        this.anos = anos;
    }
    
    public List<Integer> Anos() {
        List<Integer> years = new ArrayList<>();
        int year = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 1990; i < year + 1; i++) {
            years.add(i);
        }
        return years;
    }

    public String getValorDoLancamento(Lancamento lancamento) {
        if (lancamento.getValor().compareTo(BigDecimal.ZERO) == 1 || lancamento.getValor().compareTo(BigDecimal.ZERO) == 0) {
            return "+ R$ " + lancamento.getValor();
        } else {
            return "- R$ " + lancamento.getValor();
        }
    }

    public String getStatus(Lancamento lancamento) {
        if (lancamento.getIsPago() == 'S' && lancamento.getIsDespesa() == 'S') {
            return "Pago";
        }
        if (lancamento.getIsPago() == 'N' && lancamento.getIsDespesa() == 'S') {
            return "A pagar";
        }
        if (lancamento.getIsPago() == 'S' && lancamento.getIsDespesa() == 'N') {
            return "Recebido";
        }
        if (lancamento.getIsPago() == 'N' && lancamento.getIsDespesa() == 'N') {
            return "A receber";
        }
        return "";
    }

    public String getIconeBotao(Lancamento lancamento) {
        if (lancamento.getIsPago() == 'S') {
            return "ui-icon-minus";
        } else {
            return "ui-icon-check";
        }
    }

    public String getTituloBotao(Lancamento lancamento) {
        if (lancamento.getIsPago() == 'S') {
            return "Marcar Como Não Pago";
        } else {
            return "Marcar Como Pago";
        }
    }

    public void marcarComo(Lancamento lancamento) {
        if (lancamento.getIsPago() == 'S') {
            lancamento.setIsPago('N');
        } else {
            lancamento.setIsPago('N');
        }

        //salvar
    }

    public void listarLancamentos() {
        lancamentos = repositorioLancamentos.retornaTodosLancamentos();
        calculaValorTotal();
    }

    public void filtrarLancamentos() {

    }

    public void filtrarLancamentosPorMesEAno() {
        periodoSelecionado = 0;
        lancamentos = repositorioLancamentos.retornaLancamentosPorMesEAno(mesSelecionado, anoSelecionado, inputPesquisa, tipoLancamentoSelecionado);
        calculaValorTotal();
    }

    public void filtrarLancamentosPorPeriodo() {
        mesSelecionado = 0;
        anoSelecionado = 0;

        if (periodoSelecionado == 1) {
            lancamentos = repositorioLancamentos.retornaLancamentos(inputPesquisa, tipoLancamentoSelecionado);
        } else {
            if (periodoSelecionado == 4) {
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('dlg-intervalo-personalizado').show()");
            } else {
                lancamentos = repositorioLancamentos.retornaLancamentosPorPeriodo(periodoSelecionado, inputPesquisa, tipoLancamentoSelecionado);
            }
        }

        calculaValorTotal();
    }

    public void filtrarLancamentosPorIntervaloPersonalizado() {
        Date dataInicio = null;
        Date dataFim = null;
        GregorianCalendar gc = new GregorianCalendar();

        if (mesInicio != 0 || anoInicio != 0) {
            if (mesInicio != 0 && anoInicio == 0) gc = new GregorianCalendar(2000, mesInicio, 1);
            if (mesInicio != 0 && anoInicio != 0) gc = new GregorianCalendar(anoInicio, mesInicio, 1);
            if (mesInicio == 0 && anoInicio != 0) gc = new GregorianCalendar(anoInicio, 1, 1);
            dataInicio = gc.getTime();
        }
        
        if (mesFim != 0 || anoFim != 0) {
            int diaFim;
            if (mesFim != 0) {
                diaFim = getQuantidadeDeDias(mesFim);
                if (anoFim == 0) gc = new GregorianCalendar(2000, mesFim, diaFim);
                if (anoFim != 0) gc = new GregorianCalendar(anoFim, mesFim, diaFim);
            }else{
                if (anoFim != 0) gc = new GregorianCalendar(anoFim, 12, 31);
            }
            dataFim = gc.getTime();
        }

        lancamentos = repositorioLancamentos.retornaLancamentosPorIntervalo(dataInicio, dataFim, inputPesquisa, tipoLancamentoSelecionado);
    }

    private int getQuantidadeDeDias(int mes) {
        if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) return 31;
        if (mes == 4 || mes == 6 || mes == 9 || mes == 11) return 30;
        if (mes == 2) return 28;
        return 30;
    }

    public void excluir(Lancamento lancamento) {
        lancamentoService.excluir(lancamento);
        lancamentos.remove(lancamento);
        FacesUtil.addInfoMessage("messages", "Lançamento excluido com sucesso!");
        limpar();
        filtrarLancamentos();
        calculaValorTotal();
    }

    public void limpar() {
        inputPesquisa = null;
    }

    private void calculaValorTotal() {
        BigDecimal total = BigDecimal.ZERO;

        for (Lancamento lancamento : lancamentos) {
            if (lancamento.getValor() != null) {
                total = total.add(lancamento.getValor());
            }
        }
        setValorTotal(total);
    }
}
