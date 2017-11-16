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

    private Character statusSelecionado;

    private int mesSelecionado;

    private int anoSelecionado;

    private int periodoSelecionado;

    private Date dataInicio;

    private Date dataFim;

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

    public Character getStatusSelecionado() {
        return statusSelecionado;
    }

    public void setStatusSelecionado(Character statusSelecionado) {
        this.statusSelecionado = statusSelecionado;
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

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
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
        if (lancamento.getIsDespesa() == 'S') {
            return "- R$ " + lancamento.getValor();
        } else {
            return "+ R$ " + lancamento.getValor();
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
        if (lancamento.getIsDespesa() == 'S') {
            if (lancamento.getIsPago() == 'S') {
                return "Marcar Como Não Pago";
            } else {
                return "Marcar Como Pago";
            }
        } else {
            if (lancamento.getIsPago() == 'S') {
                return "Marcar Como Não Recebido";
            } else {
                return "Marcar Como Recebido";
            }
        }
    }
    
    public String getLabelStatusQuandoStatusNaoDefinido() {
        if (tipoLancamentoSelecionado == null) {
            return "Selecione";
        } else {
            if (tipoLancamentoSelecionado == 'S') {
                return "Pago / A pagar";
            } else {
                return "Recebido / A receber";
            }
        }
    }

    public String getLabelStatusQuandoEfetuado() {
        if (tipoLancamentoSelecionado == null) {
            return "Pago / Recebido";
        } else {
            if (tipoLancamentoSelecionado == 'S') {
                return "Pago";
            } else {
                return "Recebido";
            }
        }

    }

    public String getLabelStatusQuandoNaoEfetuado() {
        if (tipoLancamentoSelecionado == null) {
            return "A pagar / A receber";
        } else {
            if (tipoLancamentoSelecionado == 'S') {
                return "A pagar";
            } else {
                return "A receber";
            }
        }
    }

    public void listarLancamentos() {
        lancamentos = repositorioLancamentos.retornaTodosLancamentos();
        calculaValorTotal();
    }

    public void filtrarLancamentos() {
        if (mesSelecionado == 0 && anoSelecionado == 0 && (periodoSelecionado == 0 || periodoSelecionado == 1)) {
            lancamentos = repositorioLancamentos.retornaLancamentos(inputPesquisa, tipoLancamentoSelecionado, statusSelecionado);
            calculaValorTotal();
        } else {
            if (periodoSelecionado != 0) {
                filtrarLancamentosPorPeriodo();
            } else {
                filtrarLancamentosPorMesEAno();
            }
        }
    }

    public void filtrarLancamentosPorMesEAno() {
        periodoSelecionado = 0;
        lancamentos = repositorioLancamentos.retornaLancamentosPorMesEAno(mesSelecionado, anoSelecionado, inputPesquisa, tipoLancamentoSelecionado, statusSelecionado);
        calculaValorTotal();
    }

    public void filtrarLancamentosPorPeriodo() {
        mesSelecionado = 0;
        anoSelecionado = 0;

        if (periodoSelecionado == 1) {
            lancamentos = repositorioLancamentos.retornaLancamentos(inputPesquisa, tipoLancamentoSelecionado, statusSelecionado);
        } else {
            if (periodoSelecionado == 4) {
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('dlg-intervalo-personalizado').show()");
            } else {
                lancamentos = repositorioLancamentos.retornaLancamentosPorPeriodo(periodoSelecionado, inputPesquisa, tipoLancamentoSelecionado, statusSelecionado);
            }
        }

        calculaValorTotal();
    }

    public void filtrarLancamentosPorIntervaloPersonalizado() {
        lancamentos = repositorioLancamentos.retornaLancamentosPorIntervalo(dataInicio, dataFim, inputPesquisa, tipoLancamentoSelecionado, statusSelecionado);
    }

    private int getQuantidadeDeDias(int mes) {
        if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) {
            return 31;
        }
        if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
            return 30;
        }
        if (mes == 2) {
            return 28;
        }
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
            if (lancamento.getIsDespesa() == 'S') {
                total = total.subtract(lancamento.getValor());
            } else {
                total = total.add(lancamento.getValor());
            }
        }

        setValorTotal(total);
    }
}
