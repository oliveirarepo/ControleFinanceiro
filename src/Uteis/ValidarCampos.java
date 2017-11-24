/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Uteis;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * classe que valida valores de campo
 *
 * @author Thiago
 */
public class ValidarCampos {

    /**
     * valida se o campo está em branco lança excessão caso esteja em branco
     *
     * @param campo vampo a ser validado
     * @param msg nome do campo
     * @throws java.lang.Exception caso valor campo seja vazio
     */
    public static void validarCampoObrigatorio(JTextField campo, String msg) throws Exception {
        if (campo.getText().isEmpty()) {
            campo.requestFocus();
            throw new Exception("E Campo " + msg + " e Obrigatório!");
        }
    }

    /**
     * valida se o campo está em branco lança excessão caso esteja em branco
     *
     * @param campo TextArea a ser validado
     * @param msg nome do campo
     * @throws java.lang.Exception caso valor campo seja vazio
     */
    public static void validarCampoObrigatorio(JTextArea campo, String msg) throws Exception {
        if (campo.getText().isEmpty()) {
            campo.requestFocus();
            throw new Exception("E Campo " + msg + " e Obrigatório!");
        }
    }

    /**
     * valida o limite de caractere minimo de um campo
     *
     * @param campo
     * @param msg
     * @param limiteTamanho
     * @throws Exception
     */
    public static void validarCampoObrigatorio(JTextField campo, String msg, int limiteTamanho) throws Exception {
        if (campo.getText().length() < limiteTamanho) {
            campo.requestFocus();
            throw new Exception("E Campo " + msg + " e invalido!");
        }
    }

    public static void validarValores(boolean validacao, String campo) throws Exception {
        if (validacao) {
            throw new Exception("O valor " + campo + " e invalido");
        }
    }

    /**
     * valida valor que nãop pode ser nem zero nem inferior a zero <=
     *
     * @param campo
     * @param msg o valor do campo 'campo' e invalido
     * @throws Exception
     */
    public static double validarValorObrigatorio(JTextField campo, String msg) throws Exception {
        if (campo.getText().isEmpty() || Uteis.parseDouble(campo.getText()) <= 0) {
            campo.requestFocus();
            throw new Exception("O valor do campo " + msg + " e invalido!");
        }
        return Uteis.parseDouble(campo.getText());
    }

    /**
     * se valor for negativo será retornado um erro
     *
     * @param campo
     * @param msg
     * @throws Exception
     */
    public static void validarValorNegativoInvalido(JTextField campo, String msg) throws Exception {
        if (campo.getText().isEmpty() || Uteis.parseDouble(campo.getText()) < 0) {
            campo.requestFocus();
            throw new Exception("O valor do campo " + msg + " e invalido!");
        }
    }

    /**
     * se o valor for igual a null irá retornar uma excessao
     *
     * @param campo
     * @param msg
     * @throws Exception
     */
    public static void validarComboBoxNull(JComboBox campo, String msg) throws Exception {
        if (campo.getSelectedItem() == null) {
            throw new Exception("O valor do campo " + msg + " e invalido!");
        }
    }

    /**
     * valida a chave primaria de algum campo
     *
     * @param valor
     * @param msg nome do campo
     * @throws Exception o vampo X e invalida
     */
    public static void validarInteiroObrigatorio(int valor, String msg) throws Exception {
        if (valor <= 0) {
            throw new Exception("O " + msg + " Informado e invalido!");
        }
    }

    public static void validarInteiroObrigatorio(JTextField campo, String msg) throws Exception {
        if (getValorCampo(campo) == 0) {
            throw new Exception("O " + msg + " Informado e invalido!");
        }
    }

    /**
     * retorna o valor do campo convertido para inteiro, se der erro retorna
     * zero(0)
     *
     * @param campo
     * @return
     */
    public static int getValorCampo(JTextField campo) {
        try {
            return Integer.valueOf(campo.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static int getValorCampo(String valor) {
        try {
            return Integer.valueOf(valor);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static int ValidarStringObrigatoria(String valor, String msgErro) throws NumberFormatException {
        try {
            return Integer.valueOf(valor);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(msgErro + " " + e.getMessage());
        }
    }

//    public static void validarCPF_CNPJ(JTextField cpf, String msg) throws Exception {
//        if (!Uteis.validaCNPJ_CPF(cpf.getText())) {
//            throw new Exception(msg);
//        }
//    }

    /**
     * adiciona focus listerner no campo
     *
     * @param campo
     */
    public static void addFocusListener(JTextField... campo) {
        FieldListener fl = new FieldListener();
        for (JTextField j : campo) {
            j.addFocusListener(fl);
        }
    }

    /**
     * adiciona focus listerners para campos com formatação de valor Monetario
     *
     * @param campo
     */
    public static void addFocusListenerValorMonetario(JTextField... campo) {
        FieldListneterFormatandoValorMonetario fl = new FieldListneterFormatandoValorMonetario();
        for (JTextField j : campo) {
            j.addFocusListener(fl);
        }
    }

    /**
     * confira o item selecionado em uma jTable para que não seja preciso clicar
     * fora do item selecionado
     *
     * @param jTable
     */
    public static void tableConfirmaSelecao(JTable jTable) {
        if (jTable.getCellEditor() != null && jTable.getSelectedRow() >= 0) {
            jTable.setValueAt((jTable.getCellEditor().getCellEditorValue()), jTable.getSelectedRow(), jTable.getSelectedColumn());
            jTable.editCellAt(0, 0);//solucao do erro de index
        }
    }

}
