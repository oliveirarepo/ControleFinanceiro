/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Teste;

import java.awt.print.PrinterJob;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.JobName;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.JOptionPane;

/**
 *
 * @author Th
 */
public class Impressao {

    // variavel estatica porque será utilizada por inumeras threads  
    private static PrintService impressora;

    public Impressao() {
        detectaImpressoras();
//        System.out.println("Quantas impressora" + printService.length);
//        PrintService impressoraPadrao = PrintServiceLookup.lookupDefaultPrintService();
//        System.out.println("A  impressora padrao é " + impressoraPadrao.getName());
        DocFlavor docFlavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
        HashDocAttributeSet hashDocAttributeSet = new HashDocAttributeSet();
        try {
            FileInputStream fileInputStream = new FileInputStream("E:\\ControleFinanceiro\\ControleFinanceiro\\impresso.txt");
            Doc doc = new SimpleDoc(fileInputStream, docFlavor, hashDocAttributeSet);
            PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
//            PrintService printServico = ServiceUI.printDialog(null, 300, 200, new PrintService[]{impressora}, impressora, docFlavor, printRequestAttributeSet);
//            if (printServico != null) {
            DocPrintJob docPrintJob = impressora.createPrintJob();
            //madar imprimir documento
            try {
                docPrintJob.print(doc, printRequestAttributeSet);
            } catch (PrintException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
//            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }

    public static void main(String[] args) {
        try {
            //        new Impressao();
            print("Carne", "LaserJet Professiona");
        } catch (PrintException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void print(String toPrint, String printerName) throws PrintException {
        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
        aset.add(OrientationRequested.PORTRAIT);
        aset.add(new JobName("Impressao", null));

        PrintService printer = null;
        for (PrintService p : PrinterJob.lookupPrintServices()) {
            if (p.getName().contains(printerName)) {
                printer = p;
                break;
            }
        }

        DocPrintJob docPrint = printer.createPrintJob();
        InputStream stream = new ByteArrayInputStream(toPrint.getBytes());
        DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
        Doc doc = new SimpleDoc(stream, flavor, null);
        docPrint.print(doc, aset);
    }

    // O metodo verifica se existe impressora conectada e a  
    // define como padrao.  
    public void detectaImpressoras() {

        try {

            DocFlavor df = DocFlavor.SERVICE_FORMATTED.PRINTABLE;
            PrintService[] ps = PrintServiceLookup.lookupPrintServices(df, null);
            for (PrintService p : ps) {

                System.out.println("Impressora encontrada: " + p.getName());

                if (p.getName().contains("LaserJet Professiona") || p.getName().contains("Generic")) {

                    System.out.println("Impressora Selecionada: " + p.getName());
                    impressora = p;
                    break;

                }

            }

//            imprime("thiago");
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());

        }

    }

    public synchronized boolean imprime(String texto) {

        // se nao existir impressora, entao avisa usuario  
        // senao imprime texto  
        if (impressora == null) {

            String msg = "Nennhuma impressora foi encontrada. Instale uma impressora padrão \r\n(Generic Text Only) e reinicie o programa.";
            System.out.println(msg);

        } else {

            try {

                DocPrintJob dpj = impressora.createPrintJob();
                InputStream stream = new ByteArrayInputStream(texto.getBytes());

//                DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
                DocFlavor flavor = new DocFlavor(texto, texto);
                Doc doc = new SimpleDoc(stream, flavor, null);
//                HashDocAttributeSet atributos = new HashDocAttributeSet();
//                atributos.add(new MediaPrintableArea(50, 50, 100, 100, MediaPrintableArea.MM));
                dpj.print(doc, null);
                return true;
            } catch (PrintException e) {

                e.printStackTrace();

            }

        }

        return false;

    }
}
