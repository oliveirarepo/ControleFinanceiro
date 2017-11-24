/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlefinanceiro;

import bean.cadastro.Config;
import controle.cadastro.MenuControler;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.File;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Administrador
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static Config config;

    public static void main(String[] args) {
//        System.setProperty("Quaqua.tabLayoutPolicy", "wrap");
        // set the Quaqua Look and Feel in the UIManager
        lerConfig();
        String s = "";
//         s = "de.javasoft.plaf.synthetica.SyntheticaPlainLookAndFeel"; //plain
//          s = "com.jtattoo.plaf.mint.MintLookAndFeel";//mint
         s = "com.jtattoo.plaf.mcwin.McWinLookAndFeel";
//          s = "de.javasoft.plaf.synthetica.SyntheticaBlackMoonLookAndFeel";
//           s = "de.javasoft.plaf.synthetica.SyntheticaSkyMetallicLookAndFeel";
        try {
            javax.swing.UIManager.setLookAndFeel(s);
//            SwingUtilities.updateComponentTreeUI(this);
        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(pruebaLAF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
//            Logger.getLogger(pruebaLAF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
//            Logger.getLogger(pruebaLAF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
//            Logger.getLogger(pruebaLAF.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        String senha = JOptionPane.showInputDialog(null, "Senha", "Atenção", JOptionPane.QUESTION_MESSAGE);
        if (senha.equals("476275")) {
            new MenuControler();
        } else {
            JOptionPane.showMessageDialog(null, "Senha invalida");
        }
    }

    private static void lerConfig() {
        config = new Config();
        XStream xs = new XStream(new DomDriver("UTF-8"));
        File arquivo = new File(System.getProperty("user.dir") + "/config.xml");
        if (!arquivo.exists()) {
            JOptionPane.showMessageDialog(null, "Banco de dados nao configurado!");
        } else {
            config = (Config) xs.fromXML(arquivo);
            System.out.println("leu arquivo de configuração");
        }
    }
}
