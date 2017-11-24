/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Uteis;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Thiago
 */
import java.awt.Color;
import java.text.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Uteis {

    public static final Color CORERROR = Color.RED;
    public static final Color CORMSG = Color.BLUE;
    public static final int TAMANHOMINSENHA = 6;

    public Uteis() {
    }

    public static String formatarMoeda(String valor) {
        valor = validaIsNumerico(valor);

        final Locale BRAZIL = new Locale("pt", "BR");
        final DecimalFormatSymbols REAL = new DecimalFormatSymbols(BRAZIL);
        final DecimalFormat DINHEIRO_REAL = new DecimalFormat("###,###,##0.00", REAL);
        String x = valor.replace(",", ".");
        return DINHEIRO_REAL.format(Double.valueOf(x));
    }
    /*
     * valida valor verificando se existe texto nos dados passados
     */

    public static String formatarMoedaN(String valor) {
        valor = validaIsNumerico(valor);

        String x;
        final Locale BRAZIL = new Locale("pt", "BR");
        final DecimalFormatSymbols REAL = new DecimalFormatSymbols(BRAZIL);
        final DecimalFormat DINHEIRO_REAL = new DecimalFormat("###,###,##0.00", REAL);
        try {
            x = valor.replace("\\.", "").replace(",", ".");
            DINHEIRO_REAL.format(Double.valueOf(x));
        } catch (Exception ex) {
            x = "0.00";
        }
        return DINHEIRO_REAL.format(Double.valueOf(x));
    }
    /*
     * valida valores com 3 casas decimais
     */

    public static String formatarValor3Decimais(String dados) {
        String retorno;
        dados = validaIsNumerico(dados);
        DecimalFormat df = new DecimalFormat("###,###,##0.000");
        try {
            double valor = Double.parseDouble(dados.replaceAll("\\.", "").replaceAll(",", "."));
            retorno = (df.format(valor));
        } catch (Exception ex) {
            retorno = "0,000";
        }
        return retorno;
    }

    public static String formatarValor3Decimais(double dados) {
        String retorno;
        DecimalFormat df = new DecimalFormat("###,###,##0.000");
        try {
//            double valor = dados;
            retorno = (df.format(dados));
        } catch (Exception ex) {
            retorno = "0,000";
        }
        return retorno;
    }

    /*
     * valida se valores sao numericos Decimais
     */
    public static String validaIsNumerico(String dados) {
        dados = dados.replaceAll("\\.", "").replaceAll(",", ".");
        try {
            Double.parseDouble(dados);
        } catch (Exception ex) {
            dados = "0.00";
        }
        return dados.replaceAll(",", "").replaceAll("\\.", ",");

    }

    public static String validaIsInteiro(String dados) {

        try {
            Integer.parseInt(dados);
        } catch (Exception ex) {
            dados = "0";
        }
        return dados;

    }

    public static boolean IsNumerico(String dados) {
        String valor = dados;
        boolean isNumerico = false;
        if (!dados.equals("")) {
            try {
                Long.parseLong(dados);
                isNumerico = true;
            } catch (Exception ex) {
                isNumerico = false;
            }
        }
        return isNumerico;

    }

    public static Double StringDouble(String valorPrm) {
        String valor = Uteis.removeChar(valorPrm, '.');
        valor = valor.replace(',', '.');
        double valorD = Double.parseDouble(valor);
        return valorD;
    }

    public static Double stringDouble(String valorPrm) {
        String valor = Uteis.removeChar(valorPrm, '.');
        valor = valor.replace(',', '.');
        double valorD = Double.parseDouble(valor);
        return valorD;
    }

    public static String formatarMoeda(double valor) {

        final Locale BRAZIL = new Locale("pt", "BR");
        final DecimalFormatSymbols REAL = new DecimalFormatSymbols(BRAZIL);
        final DecimalFormat DINHEIRO_REAL = new DecimalFormat("###,###,##0.00", REAL);

        return DINHEIRO_REAL.format(valor);
    }

    /**
     * Formata a quantidade de decimais de um Float
     *
     * @param valor - numero a ser formatado
     * @param qtdeDecimal - quantidade de casas decimais
     * @return - String
     */
    public static String formatarDecimal(Float valor, int qtdeDecimal) {
        DecimalFormat format = new DecimalFormat();
        format.setMaximumFractionDigits(qtdeDecimal);
        format.setMinimumFractionDigits(qtdeDecimal);

        return format.format(valor);
    }

    /**
     * Formata a quantidade de decimais de um Double
     *
     * @param valor - numero a ser formatado
     * @param qtdeDecimal - quantidade de casas decimais
     * @return - String
     */
    public static String formatarDecimal(Double valor, int qtdeDecimal) {
        DecimalFormat format = new DecimalFormat();
        format.setMaximumFractionDigits(qtdeDecimal);
        format.setMinimumFractionDigits(qtdeDecimal);

        return format.format(valor);
    }

    public static java.sql.Date getDataJDBC(java.util.Date dataConverter) {
        if (dataConverter == null) {
            dataConverter = new Date();
        }
        java.sql.Date dataSQL = new java.sql.Date(dataConverter.getTime());
        return dataSQL;
    }

    public static String getDataFormatoBD(java.util.Date dataConverter) {
        return getData(dataConverter, "bd");
    }

    public static String getData(java.util.Date dataConverter, String padrao) {
        if (dataConverter == null) {
            return ("");
        }
        String dataStr;
        if (padrao.equals("bd")) {
            Locale aLocale = new Locale("pt", "BR");
            SimpleDateFormat formatador = new SimpleDateFormat("yyyy.MM.dd", aLocale);
            dataStr = formatador.format(dataConverter);
        } else {
            //DateFormat formatador = DateFormat.getDateInstance(DateFormat.SHORT);
            Locale aLocale = new Locale("pt", "BR");
            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy", aLocale);
            dataStr = formatador.format(dataConverter);
        }
        return (dataStr);
    }

    public static String getDataTime(java.util.Date dataConverter) {
        if (dataConverter == null) {
            return ("");
        }
        String dataStr;

        //DateFormat formatador = DateFormat.getDateInstance(DateFormat.SHORT);
        Locale aLocale = new Locale("pt", "BR");
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy' - 'HH:mm:ss", aLocale);
        dataStr = formatador.format(dataConverter);

        return (dataStr);
    }
    /*
     * data no padrao americano usado para gerar xml
     */

    public static String getDataPadraoAmericano(java.util.Date dataConverter) {
        if (dataConverter == null) {
            return ("");
        }
        String dataStr;

        //DateFormat formatador = DateFormat.getDateInstance(DateFormat.SHORT);
        Locale aLocale = new Locale("pt", "BR");
        SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd", aLocale);
        dataStr = formatador.format(dataConverter);

        return (dataStr);
    }

    public static boolean comparaData(Date d1, Date d2) {
        Calendar data1 = GregorianCalendar.getInstance();
        data1.setTime(d1);

        Calendar data2 = GregorianCalendar.getInstance();
        data1.setTime(d2);
        return (data1.get(Calendar.DAY_OF_MONTH) == data2.get(Calendar.DAY_OF_MONTH)
                && data1.get(Calendar.MONTH) == data2.get(Calendar.MONTH)
                && data1.get(Calendar.YEAR) == data2.get(Calendar.YEAR));
    }

    public static String getData(java.util.Date dataConverter) {
        return (getData(dataConverter, ""));
    }

    /*
     * formata data no padrao yyyy-mm-dd para dd-mm-yyyy
     */
    public static String getFormataData(String YYYY_MM_DD) {
        Date data;
        String dataBanco = "";
        try {
            data = new SimpleDateFormat("yyyy-MM-dd").parse(YYYY_MM_DD);
            dataBanco = new SimpleDateFormat("dd/MM/yyyy").format(data);

        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return dataBanco;
    }

    public static Date getDataFatura(String data) throws Exception {
        StringBuilder d = new StringBuilder(data);
        d.insert(6, "20");
        return getDate(d.toString());
    }

    public static java.util.Date getDate(String data) throws Exception {
        java.util.Date valorData = null;
        DateFormat formatador = DateFormat.getDateInstance(DateFormat.SHORT);
        valorData = formatador.parse(data);
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int hora = cal.get(Calendar.HOUR_OF_DAY);
        int minuto = cal.get(Calendar.MINUTE);
        int segundo = cal.get(Calendar.SECOND);

        cal.setTime(valorData);
        cal.set(Calendar.HOUR_OF_DAY, hora);
        cal.set(Calendar.MINUTE, minuto);
        cal.set(Calendar.SECOND, segundo);

        return cal.getTime();
    }

    public static java.util.Date getDate(String data, Locale local) throws Exception {
        java.util.Date valorData = new Date();
        if (local == null) {
            DateFormat formatador = DateFormat.getDateInstance(DateFormat.SHORT);
            valorData = formatador.parse(data);
        } else {
            DateFormat formatador = DateFormat.getDateInstance(DateFormat.SHORT, local);
            valorData = formatador.parse(data);
        }
        return valorData;
    }

    public static String getAnoDataAtual() {
        Locale aLocale = new Locale("pt", "BR");
        Date hoje;
        String hojeStr;
        DateFormat formatador;
        formatador = DateFormat.getDateInstance(DateFormat.SHORT, aLocale);
        hoje = new Date();
        hojeStr = formatador.format(hoje);
        return (hojeStr.substring(hojeStr.lastIndexOf("/") + 1));
    }

    public static String getDataAtual() {
        Date hoje = new Date();
        return (Uteis.getData(hoje));
    }

    public static String getHoraAtual() {
        Locale aLocale = new Locale("pt", "BR");
        Date hoje;
        DateFormat formatador;
        formatador = DateFormat.getTimeInstance(DateFormat.SHORT, aLocale);
        hoje = new Date();

        String horaStr;
        horaStr = formatador.format(hoje);
        return (horaStr);
    }

    public static Date getDateTime(Date data, int hora, int minuto, int segundo) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        cal.set(Calendar.HOUR_OF_DAY, hora);
        cal.set(Calendar.MINUTE, minuto);
        cal.set(Calendar.SECOND, segundo);
        return cal.getTime();
    }

    public static Date getDataMesInicio(int mes, int ano) {
        Date data = new Date();
        Calendar c = GregorianCalendar.getInstance();
        c.setTime(data);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.MONTH, mes);
        c.set(Calendar.YEAR, ano);
        return c.getTime();
    }

    public static Date getDataMesFim(int mes, int ano) {
        Date data = new Date();
        Calendar c = GregorianCalendar.getInstance();
        c.setTime(data);
        c.set(Calendar.MONTH, mes);
        c.set(Calendar.YEAR, ano);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        return c.getTime();
    }

    public static String formatZerosEsquerdoStr(String strPrm, int tamanhaPrm) {
        if (strPrm.length() < tamanhaPrm) {
            int x = tamanhaPrm - strPrm.length();
            String aux = "";
            for (int i = 1; i <= x; i++) {
                aux = aux + "0";
            }
            strPrm = aux + strPrm;
        }
        return strPrm;
    }

    public static void setLog(Logger logg, String str, Boolean error) {
        logg.setLevel(Level.ALL);
        if (error) {
            logg.log(Level.WARNING, str);
        } else {
            logg.log(Level.INFO, str);
        }
    }

    public static String removeChar(String s, char c) {
        String r = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != c) {
                r += s.charAt(i);
            }
        }
        return r;
    }

    /**
     * Realiza a validacao do CPF.
     *
     * @param strCPF n�mero de CPF a ser validado
     * @return true se o CPF for valido e false se nao for valido
     */
    public static boolean isCPF(String strCpf) {

        strCpf = Uteis.removeChar(strCpf, '.');
        strCpf = Uteis.removeChar(strCpf, '-');
        strCpf = strCpf.trim();

        if (strCpf.length() != 11 || strCpf.equals("00000000000") || strCpf.equals("11111111111")
                || strCpf.equals("22222222222") || strCpf.equals("33333333333") || strCpf.equals("44444444444")
                || strCpf.equals("55555555555") || strCpf.equals("66666666666") || strCpf.equals("77777777777")
                || strCpf.equals("88888888888") || strCpf.equals("99999999999")) {
            return false;
        }

        int d1, d2;
        int digito1, digito2, resto;
        int digitoCPF;
        String nDigResult;

        d1 = d2 = 0;
        digito1 = digito2 = resto = 0;

        for (int nCount = 1; nCount < strCpf.length() - 1; nCount++) {
            digitoCPF = Integer.valueOf(
                    strCpf.substring(nCount - 1, nCount)).intValue();

            /*
             * multiplique a ultima casa por 2 a seguinte por 3 a seguinte por 4
             * e assim por diante.
             */
            d1 = d1 + (11 - nCount) * digitoCPF;

            /*
             * para o segundo digito repita o procedimento incluindo o primeiro
             * digito calculado no passo anterior.
             */
            d2 = d2 + (12 - nCount) * digitoCPF;
        }

        //Primeiro resto da divis�o por 11.
        resto = (d1 % 11);

        /*
         * Se o resultado for 0 ou 1 o digito � 0 caso contr�rio o digito � 11
         * menos o resultado anterior.
         */
        if (resto < 2) {
            digito1 = 0;
        } else {
            digito1 = 11 - resto;
        }

        d2 += 2 * digito1;

        //Segundo resto da divis�o por 11.
        resto = (d2 % 11);

        /*
         * Se o resultado for 0 ou 1 o digito � 0 caso contr�rio o digito � 11
         * menos o resultado anterior.
         */
        if (resto < 2) {
            digito2 = 0;
        } else {
            digito2 = 11 - resto;
        }

        //Digito verificador do CPF que est� sendo validado.
        String nDigVerific = strCpf.substring(
                strCpf.length() - 2, strCpf.length());

        //Concatenando o primeiro resto com o segundo.
        nDigResult = String.valueOf(digito1) + String.valueOf(digito2);

        /*
         * comparar o digito verificador do cpf com o primeiro resto + o segundo
         * resto.
         */
        return nDigVerific.equals(nDigResult);
    }

    public static boolean isCNPJ(String str_cnpj) {

        str_cnpj = Uteis.removeChar(str_cnpj, '.');
        str_cnpj = Uteis.removeChar(str_cnpj, '/');
        str_cnpj = Uteis.removeChar(str_cnpj, '-');
        str_cnpj = str_cnpj.trim();

        if (str_cnpj.length() != 14) {
            return false;
        }

        int soma = 0, aux, dig;
        String cnpj_calc = str_cnpj.substring(0, 12);

        char[] chr_cnpj = str_cnpj.toCharArray();

        /*
         * Primeira parte
         */
        for (int i = 0; i < 4; i++) {
            if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9) {
                soma += (chr_cnpj[i] - 48) * (6 - (i + 1));
            }
        }
        for (int i = 0; i < 8; i++) {
            if (chr_cnpj[i + 4] - 48 >= 0 && chr_cnpj[i + 4] - 48 <= 9) {
                soma += (chr_cnpj[i + 4] - 48) * (10 - (i + 1));
            }
        }
        dig = 11 - (soma % 11);

        cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);

        /*
         * Segunda parte
         */
        soma = 0;
        for (int i = 0; i < 5; i++) {
            if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9) {
                soma += (chr_cnpj[i] - 48) * (7 - (i + 1));
            }
        }
        for (int i = 0; i < 8; i++) {
            if (chr_cnpj[i + 5] - 48 >= 0 && chr_cnpj[i + 5] - 48 <= 9) {
                soma += (chr_cnpj[i + 5] - 48) * (10 - (i + 1));
            }
        }
        dig = 11 - (soma % 11);
        cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);

        return str_cnpj.equals(cnpj_calc);
    }

    /**
     * Gera o numero de matricula.
     *
     * @param ano, semestre, codigo do curso e codigo do aluno
     * @return o numero da matricula
     */
    public static String gerarNumeroMatricula(Integer ano, Integer semestre, Integer cursoId, Integer alunoId) {
        Integer d1;
        Integer digito1, resto;
        Integer digitoMatricula;
        String nDigResult;
        String strMatricula;

        d1 = new Integer(0);
        digito1 = new Integer(0);
        resto = new Integer(0);
        strMatricula = String.valueOf(ano).substring(2, 4) + String.valueOf(semestre)
                + formatZerosEsquerdoStr(String.valueOf(cursoId), 4) + formatZerosEsquerdoStr(String.valueOf(alunoId), 5);

        for (int nCount = 1; nCount < strMatricula.length() - 1; nCount++) {
            digitoMatricula = Integer.valueOf(
                    strMatricula.substring(nCount - 1, nCount)).intValue();

            /*
             * multiplique a ultima casa por 2 a seguinte por 3 a seguinte por 4
             * e assim por diante.
             */
            d1 = d1 + (11 - nCount) * digitoMatricula;

        }

        //Primeiro resto da divis�o por 11.
        resto = (d1 % 13);

        /*
         * Se o resultado for 0 ou 1 o digito � 0 caso contr�rio o digito � 11
         * menos o resultado anterior.
         */
        if (resto < 2) {
            digito1 = 0;
        } else {
            digito1 = 11 - resto;
        }

        strMatricula += digito1;

        return strMatricula;
    }

    public static Date addDias(String dateStr, int dias) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date date;
        try {
            date = df.parse(dateStr);
        } catch (Exception e) {
            date = new Date();
        }
        Date nova_data;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, dias);
        nova_data = calendar.getTime();
        Date data = Uteis.getDate(df.format(nova_data));
        return data;

    }

    public static String removeAcentos(String acentos) {

        //letras minusculas
        acentos = acentos.replaceAll("[èéêë]", "e");
        acentos = acentos.replaceAll("[ûùú]", "u");
        acentos = acentos.replaceAll("[íïîì]", "i");
        acentos = acentos.replaceAll("[àâãá]", "a");
        acentos = acentos.replaceAll("[ôóõ]", "o");
        acentos = acentos.replaceAll("[ç‡]", "c");

        //letras maiusculas   
        acentos = acentos.replaceAll("[ÈÉÊË]", "E");
        acentos = acentos.replaceAll("[ÛÙÚ]", "U");
        acentos = acentos.replaceAll("[ÍÏÎÌ]", "I");
        acentos = acentos.replaceAll("[ÀÂÃÁ]", "A");
        acentos = acentos.replaceAll("[ÔÓÕÒ]", "O");
        acentos = acentos.replaceAll("[Ç€‡]", "C");

        return acentos;
    }

    public static long calcDiasEntreDatas(String data1, String data2) {
        try {
            // Dando um exemplo: quantos dias se passam desde 07/09/1822 até 05/06/2006?  
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            df.setLenient(false);
            Date d1 = df.parse(data1);
            //System.out.println(d1);
            Date d2 = df.parse(data2);
            //System.out.println(d2);
            long dt = (d2.getTime() - d1.getTime()) + 3600000; // 1 hora para compensar horário de verão  
            return (dt / 86400000L); // passaram-se 67111 dias  
        } catch (Exception ex) {
            return 0;
        }
    }

    public static long calcDiasEntreDatas(Date d1, Date d2) {
        try {
            // Dando um exemplo: quantos dias se passam desde 07/09/1822 até 05/06/2006?  
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            df.setLenient(false);
            //Date d1 = df.parse(data1);
            //System.out.println(d1);
            //Date d2 = df.parse(data2);
            //System.out.println(d2);
            long dt = (d2.getTime() - d1.getTime()) + 3600000; // 1 hora para compensar horário de verão  
            return (dt / 86400000L); // passaram-se 67111 dias  
        } catch (Exception ex) {
            return 0;
        }
    }

    public static int getIdade(String dataNasc) throws Exception {
        try {

            final int NUMERO_INTEIRO_ZERO = 0;
            // Data de hoje. 
            GregorianCalendar agora = new GregorianCalendar();
            int ano = NUMERO_INTEIRO_ZERO,
                    mes = NUMERO_INTEIRO_ZERO, dia = NUMERO_INTEIRO_ZERO;

            // Data do nascimento. 
            GregorianCalendar nascimento = new GregorianCalendar();
            int anoNasc = NUMERO_INTEIRO_ZERO,
                    mesNasc = NUMERO_INTEIRO_ZERO, diaNasc = NUMERO_INTEIRO_ZERO;

            // Idade. 
            int idade = NUMERO_INTEIRO_ZERO;

            if (!dataNasc.equals("")) {
                nascimento.setTime(Uteis.getDate(dataNasc));

                ano = agora.get(Calendar.YEAR);
                mes = agora.get(Calendar.MONTH) + 1;
                dia = agora.get(Calendar.DAY_OF_MONTH);

                anoNasc = nascimento.get(Calendar.YEAR);
                mesNasc = nascimento.get(Calendar.MONTH) + 1;
                diaNasc = nascimento.get(Calendar.DAY_OF_MONTH);

                idade = ano - anoNasc;

                // Calculando diferencas de mes e dia. 
                if (mes < mesNasc) {
                    idade--;
                } else if (mes == mesNasc) {
                    if (dia < diaNasc) {
                        idade--;
                    }
                }

                //if ((mes == mesNasc) && (dia == diaNasc))
                //    JOptionPane.showMessageDialog(null, "Da os Parabens para ele.");
                // Ultimo teste, idade "negativa". 
                if (idade < NUMERO_INTEIRO_ZERO) {
                    idade = NUMERO_INTEIRO_ZERO;
                }

            }
            return idade;

        } catch (Exception ex) {
            throw new Exception("Error ao calcular idade: " + ex.getMessage());
        }
    }

    public static int getIdade(Date dataNasc) throws Exception {
        try {

            final int NUMERO_INTEIRO_ZERO = 0;
            // Data de hoje. 
            GregorianCalendar agora = new GregorianCalendar();
            int ano = NUMERO_INTEIRO_ZERO,
                    mes = NUMERO_INTEIRO_ZERO, dia = NUMERO_INTEIRO_ZERO;

            // Data do nascimento. 
            GregorianCalendar nascimento = new GregorianCalendar();
            int anoNasc = NUMERO_INTEIRO_ZERO,
                    mesNasc = NUMERO_INTEIRO_ZERO, diaNasc = NUMERO_INTEIRO_ZERO;

            // Idade. 
            int idade = NUMERO_INTEIRO_ZERO;

            if (dataNasc != null) {
                nascimento.setTime(dataNasc);

                ano = agora.get(Calendar.YEAR);
                mes = agora.get(Calendar.MONTH) + 1;
                dia = agora.get(Calendar.DAY_OF_MONTH);

                anoNasc = nascimento.get(Calendar.YEAR);
                mesNasc = nascimento.get(Calendar.MONTH) + 1;
                diaNasc = nascimento.get(Calendar.DAY_OF_MONTH);

                idade = ano - anoNasc;

                // Calculando diferencas de mes e dia. 
                if (mes < mesNasc) {
                    idade--;
                } else if (mes == mesNasc) {
                    if (dia < diaNasc) {
                        idade--;
                    }
                }

                //if ((mes == mesNasc) && (dia == diaNasc))
                //    JOptionPane.showMessageDialog(null, "Da os Parabens para ele.");
                // Ultimo teste, idade "negativa". 
                if (idade < NUMERO_INTEIRO_ZERO) {
                    idade = NUMERO_INTEIRO_ZERO;
                }

            }
            return idade;

        } catch (Exception ex) {
            throw new Exception("Error ao calcular idade: " + ex.getMessage());
        }
    }

    public static String getSenha(char pw[]) {
        String senha = "";
        for (int a = 0; a < pw.length; a++) {
            senha += pw[a];
        }
        return senha;
    }

    /*
     * parse double converte uma string de valorr para um double removendo Virgula(,) por ponto(.)
     */
    public static double parseDouble(String valor) {
        valor = validaIsNumerico(valor);
        return Double.parseDouble(valor.replaceAll("\\.", "").replaceAll(",", "."));

    }

    public static float parseFloat(String valor) {
        valor = validaIsNumerico(valor);
        return Float.parseFloat(valor.replaceAll("\\.", "").replaceAll(",", "."));

    }

    /*
     * parse retorna no formato 1000.00 ao inves de (1,000.00)
     */
    public static String parseString(double valor) {
        return String.valueOf(valor).replaceAll(",", "").replaceAll("\\.", ",");
    }

    public static String horaAtual(Date hora) {
        hora = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(hora);
    }
}
