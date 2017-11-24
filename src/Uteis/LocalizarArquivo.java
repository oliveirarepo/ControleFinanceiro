/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Uteis;

/**
 *
 * @author thaigo
 */
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

public class LocalizarArquivo {

    String uISistema = UIManager.getLookAndFeel().getName();

    public LocalizarArquivo() {
//        try {
//            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
//            Logger.getLogger(LocalizarArquivo.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    JFileChooser chooser;

    /**
     *
     * @param tipoArquivo informe o tipo de arquivo ex arquivos de imagens
     * @param dirInicial diretorio inicial para abrir o sistema
     * @param extensao informe a extensao ex jpg,gif
     * @return retorna a String com a url do Arquivo
     * @throws Exception
     */
    public String selecionaArquivo(String tipoArquivo, String dirInicial, String... extensao) throws Exception {
        chooser = new JFileChooser(new File(dirInicial));
        chooser.setFileFilter(new FileNameExtensionFilter(tipoArquivo, extensao));
        chooser.setAcceptAllFileFilterUsed(false);
        setLookAndFeelSistema();
        if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
            throw new Exception("Arquivo invalido");
        }
        File file = chooser.getSelectedFile();
        if (!file.getAbsolutePath().toLowerCase().endsWith("." + extensao)) {
            file = new File(file.getAbsolutePath());
        }

        return file.toString();
    }

    public String selecionaDiretorio() throws Exception {
        chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.showOpenDialog(null);
        setLookAndFeelSistema();
        return chooser.getSelectedFile().getPath();
    }

    public File[] selecionaMultiplosArquivos(String tipoArquivo, String... extensao) throws Exception {

        chooser = new JFileChooser();

        chooser.setFileFilter(new FileNameExtensionFilter(tipoArquivo, extensao));
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setMultiSelectionEnabled(true);

        setLookAndFeelSistema();
        if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
            throw new Exception("Arquivo invalido");
        }
        File[] file = chooser.getSelectedFiles();
        for (File f : file) {
            if (!f.getAbsolutePath().toLowerCase().endsWith("." + extensao)) {
                f = new File(f.getAbsolutePath());
            }
        }
        return file;
    }

    private void setLookAndFeelSistema() {
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if (uISistema.equals(info.getName())) {
                try {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {

                }
                break;
            }
        }
    }

//    public static void main(String args[]) {
//        LocalizarArquivo l = new LocalizarArquivo();
//        try {
//            System.out.println(l.selecionaDiretorio());
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
}
