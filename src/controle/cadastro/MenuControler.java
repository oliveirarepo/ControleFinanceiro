/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.cadastro;

import bean.cadastro.Config;
import controle.relatorios.RelatoriosControler;
import Uteis.LocalizarArquivo;
import bean.cadastro.Categoria;
import bean.cadastro.ContasPagar;
import bean.cadastro.TipoConta;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import view.principal.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Thaigo
 */
public class MenuControler implements ActionListener {

    private final Menu menu = new Menu();

    public MenuControler() {
        this.menu.addActionListener(this);
        menu.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "relacaoDeContas":
                relacaoDeContas();
                break;
            case "lancarContas":
                lancarContas();
                break;
            case "Categorias":
                Categorias();
                break;
            case "contaFinanceira":
                contaFinanceira();
                break;
            case "consultaTipoDeContas":
                consultaTipoDeContas();
                break;
            case "localizarBanco":
                try {
                    localizarBanco();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                break;
            case "lancamentoDeCategorias":
                lancamentoDeCategorias();
                break;
            case "relatorios":
                relatorios();
                break;
            case "importacaoFatura":
                new ImportacaoFaturaControler();
                break;
        }

    }

    private void relacaoDeContas() {
        new RelacaoContasControler();
    }

    private void lancarContas() {
        new LacamentoContasControler(new ContasPagar(), new ArrayList<ContasPagar>());
    }

    private void Categorias() {
        new CategoriaControler(new Categoria());

    }

    private void contaFinanceira() {
        new ContaControler(null);
    }

    private void consultaTipoDeContas() {
        new TipoContaControler(new TipoConta());
    }

    private void localizarBanco() throws Exception {
        Config config = new Config();
        config.setCaminhoBanco(new LocalizarArquivo().selecionaArquivo("Arquivo Banco de dados", System.getProperty("user.dir"), "FDB"));
        XStream xStream = new XStream(new DomDriver("UTF-8"));
        try (FileOutputStream arquivo = new FileOutputStream(System.getProperty("user.dir") + "/config.xml")) {
            xStream.toXML(config, arquivo);
        }
    }

    private void lancamentoDeCategorias() {
        new CategoriaControler(new Categoria());
    }

    private void relatorios() {
        new RelatoriosControler();
    }
}
