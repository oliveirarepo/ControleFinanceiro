/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.cadastro;

import java.util.Date;

/**
 *
 * @author Thiago
 */
public class ContasPagar {

    private int codigo;
    private Date dataVenc;
    private String creedor;
    private int codOperacao;
    private String Operacao;
    private Date dataCompra;
    private float valorCompra;
    private float desconto;
    private float valorPago;
    private char jaPaga;
    private Categoria categoria;
    private TipoConta tipoConta;
    private char importada = 'N';
    private double andamento = 0;
    private int parcela;
    private int totalParcelas;

    public ContasPagar() {

    }

    public void limpar() {
        categoria.limpar();
        tipoConta.limpar();
        dataCompra = null;
        dataVenc = null;
        creedor = null;
    }

    public ContasPagar(int idCodigo, String creedor, int codOperacao, Date dataCompra, Date dataVenc, float valorPago, char jaPaga,
            int idTipoConta, String tipoConta, int idCategoria, String operacao, String categoria, final double parcela, final double totalParcela) {
        this.codigo = idCodigo;
        this.creedor = creedor;
        this.codOperacao = codOperacao;
        this.dataCompra = dataCompra;
        this.dataVenc = dataVenc;
        this.valorPago = valorPago;
        this.jaPaga = jaPaga;
        this.tipoConta = new TipoConta(idTipoConta, tipoConta);
        this.categoria = new Categoria(idCategoria, categoria);
        this.Operacao = operacao;
        this.parcela = (int) parcela;
        this.totalParcelas = (int) totalParcela;
        this.andamento = ((parcela / totalParcela) * 100);
    }

    public int getCODIGO() {
        return codigo;
    }

    public void setCODIGO(int CODIGO) {
        this.codigo = CODIGO;
    }

    public int getCOD_OPERACAO() {
        return codOperacao;
    }

    public void setCOD_OPERACAO(int COD_OPERACAO) {
        this.codOperacao = COD_OPERACAO;
    }

    public String getCreedor() {
        return creedor;
    }

    public void setCreedor(String CREEDOR) {
        this.creedor = CREEDOR;
    }

    public Date getDataCompra() {
        if (dataCompra == null) {
            dataCompra = new Date();
        }
        return dataCompra;
    }

    public void setDataCompra(Date DATACOMPRA) {
        this.dataCompra = DATACOMPRA;
    }

    public Date getDataVenc() {
        if (dataVenc == null) {
            dataVenc = new Date();
        }
        return dataVenc;
    }

    public void setDataVenc(Date dataVenc) {
        this.dataVenc = dataVenc;
    }

    public float getDESCONTO() {
        return desconto;
    }

    public void setDESCONTO(float DESCONTO) {
        this.desconto = DESCONTO;
    }

    public char getJAPAGA() {
        return jaPaga;
    }

    public void setJAPAGA(char JAPAGA) {
        this.jaPaga = JAPAGA;
    }

    public String getOPERACAO() {
        return Operacao;
    }

    public void setOPERACAO(String OPERACAO) {
        this.Operacao = OPERACAO;
    }

    public float getVALORCOMPRA() {
        return valorCompra;
    }

    public void setVALORCOMPRA(float VALORCOMPRA) {
        this.valorCompra = VALORCOMPRA;
    }

    public float getVALORPAGO() {
        return valorPago;
    }

    public void setVALORPAGO(float VALORPAGO) {
        this.valorPago = VALORPAGO;
    }

    public Categoria getCategoria() {
        if (categoria == null) {
            categoria = new Categoria();
        }
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public TipoConta getTipoConta() {
        if (tipoConta == null) {
            tipoConta = new TipoConta();
        }
        return tipoConta;
    }

    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }

    public String getCredorCompara() {
        return creedor.replaceAll(" ", "").replaceAll("/", "").replaceAll("\\(0", "").replaceAll("\\)", "").replaceAll("\\(", "");
    }

    public char getImportada() {
        return importada;
    }

    public void setImportada(char importada) {
        this.importada = importada;
    }

    public double getAndamento() {
        return andamento;
    }

    public void setAndamento(double andamento) {
        this.andamento = andamento;
    }

    public int getParcela() {
        return parcela;
    }

    public void setParcela(int parcela) {
        this.parcela = parcela;
    }

    public int getTotalParcelas() {
        return totalParcelas;
    }

    public void setTotalParcelas(int totalParcelas) {
        this.totalParcelas = totalParcelas;
    }

    public void atualizarAndamento() {
        this.andamento = (((float)parcela / (float)totalParcelas) * 100);
    }
}
