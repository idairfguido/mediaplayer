/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author IdairFGuido
 */
public class Formatar {

    public static final int CPF_MASK = 0;
    public static final int CNPJ_MASK = 1;
    public static final int RG_MASK = 2;
    public static final int IE_PR_MASK = 3;
    public static final int FONE_MASK = 4;
    public static final int AGENCIA_MASK = 5;
    public static final int CONTA_MASK = 6;
    public static final int CEP_MASK = 7;
    public static final int DATE_MASK = 8;
    public static final int MOEDA_MASK = 9;
    public static final int PORCENTAGEM_MASK = 10;

    /**
     *
     * * Tipos disponíneis
     * <pre>
     * * Mascara para CPF (<code>Formatar.CPF_MASK = 0</code>)
     * * Máscara para CNPJ (<code>Formatar.CNPJ_MASK = 1</code>)
     * * Máscara para RG (<code>Formatar.RG_MASK = 2</code>)
     * * Máscara para Inscrição Estadual do Paraná (<code>Formatar.IE_PR_MASK = 3</code>)
     * * Máscara para Telefone ou Celular (<code>Formatar.FONE_MASK = 4</code>)
     * * Máscara para Agencia Bancária (<code>Formatar.AGENCIA_MASK = 5</code>)
     * * Máscara para Conta Bancária (<code>Formatar.CONTA_MASK = 6</code>)
     * * Máscara para CEP (<code>Formatar.CEP_MASK = 7</code>)
     * * Máscara para Data (<code>Formatar.DATE_MASK = 8</code>)
     * * Máscara para Moeda (<code>Formatar.MOEDA_MASK = 9</code>)
     * </pre>
     *
     * @param tipo = valor inteiro correspondente a mascara.
     * @return <code>DefaultFormatterFactory</code>
     */
    public static DefaultFormatterFactory getMascara(int tipo) {
        DefaultFormatterFactory defaultFormatterFactory = null;
        try {
            switch (tipo) {
                case CPF_MASK:
                    defaultFormatterFactory = new DefaultFormatterFactory(new MaskFormatter("###.###.###-##"));
                    break;
                case CNPJ_MASK:
                    defaultFormatterFactory = new DefaultFormatterFactory(new MaskFormatter("##.###.###/####-##"));
                    break;
                case RG_MASK:
                    defaultFormatterFactory = new DefaultFormatterFactory(new MaskFormatter("#.###.###-#"));
                    break;
                case IE_PR_MASK:
                    defaultFormatterFactory = new DefaultFormatterFactory(new MaskFormatter("########-##"));
                    break;
                case FONE_MASK:
                    defaultFormatterFactory = new DefaultFormatterFactory(new MaskFormatter("(##) ####-####"));
                    break;
                case AGENCIA_MASK:
                    defaultFormatterFactory = new DefaultFormatterFactory(new MaskFormatter("####"));
                    break;
                case CONTA_MASK:
                    defaultFormatterFactory = new DefaultFormatterFactory(new MaskFormatter("##### #"));
                    break;
                case CEP_MASK:
                    defaultFormatterFactory = new DefaultFormatterFactory(new MaskFormatter("##.###-###"));
                    break;
                case DATE_MASK:
                    defaultFormatterFactory = new DefaultFormatterFactory(new MaskFormatter("##/##/####"));
                    break;
                case MOEDA_MASK:
                    defaultFormatterFactory = new DefaultFormatterFactory(new MaskFormatter("¤ ###,###,##0.00"));
                    break;
                case PORCENTAGEM_MASK:
                    defaultFormatterFactory = new DefaultFormatterFactory(new MaskFormatter("#0%"));
                    break;
                default: {
                }
            }
            return defaultFormatterFactory;
        } catch (ParseException ex) {
            DGLogger.print(Formatar.class, DGLogger.TIPO_ERROR, ex);
            return defaultFormatterFactory;
        }

    }

    public static String replace(String valor, char charAntigo, char charNovo) {
        char[] cs = valor.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            char c = cs[i];
            if (c == charAntigo) {
                cs[i] = charNovo;
            }
        }
        valor = new String(cs);

        return valor;
    }

    public static String doubleToPorcentagem(Double valor) {
        return String.format("%.2f%%", valor);
    }

    public static Double porcentagemToDouble(String valor) {
        String temp = "";
        for (int i = 0; i < valor.toCharArray().length; i++) {
            char c = valor.toCharArray()[i];
            if (c != '%') {
                temp += c;
            }
        }
        String replace = Formatar.replace(temp, ',', '.');
        return Double.parseDouble(replace);
    }

    public static String doubleToString(Double valor) {
        String valorStr = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(valor);
        return valorStr;
    }

    public static Double stringToDouble(String valor) {
        Double retorno = 0d;
        try {
            retorno = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).parse(valor).doubleValue();
        } catch (ParseException ex) {
            Logger.getLogger(Formatar.class.getName()).log(Level.SEVERE, null, ex);
            DGLogger.print(Formatar.class, DGLogger.TIPO_ERROR, ex);
        } finally {
            return retorno;
        }
    }

    public static LocalDate convertToLocalDate(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static Date convertToDate(LocalDate dateToConvert) {
        return java.util.Date.from(dateToConvert.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    public static String dateToString(Date data) {
        return new SimpleDateFormat("dd/MM/yyyy").format(data);
    }

    public static String timeStampToString(Timestamp timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getDefault());
        return dateFormat.format(timestamp);
    }
    
    public static Double valorToDouble(String valor){
        String replace = valor.replace(".", "").replace("R$", "").replace("%", "").replace(",", ".").replace(" ", "");
        return Double.parseDouble(replace);
    }

}
