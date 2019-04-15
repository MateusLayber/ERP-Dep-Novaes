
import javax.swing.table.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class SistemaView extends JFrame implements ActionListener {

    public static Container ctnPrincipal, ctnTopo, ctnTop, ctnMenu;
    public static JLabel lblBanner;
    public static JButton btnMenu[];
    public static JDesktopPane dskJanelas; // abre as janelas 
    public static ProdutosView appEstrutura;
    public static ClientesView appAlvenaria;
    public static OrcamentoView appCobertura;
    public static CaixaView appcaixa;
    public static EntregasView appentregas;

    // public static FuncionariosView appFuncionarios;
    public SistemaView() {

        super("Construção");
        ctnPrincipal = new Container();
        ctnPrincipal.setLayout(new BorderLayout());
        this.add(ctnPrincipal);

        ctnTopo = new Container();
        ctnTopo.setLayout(new GridLayout(1, 1));
        ctnPrincipal.add(ctnTopo, BorderLayout.NORTH);

        lblBanner = new JLabel(new ImageIcon("img/banner.png"));
        ctnTopo.add(lblBanner);

        ctnTop = new Container();
        ctnTop.setLayout(new GridLayout(1, 1));
        ctnPrincipal.add(ctnTop, BorderLayout.WEST);

        ctnMenu = new Container();
        ctnMenu.setLayout(new GridLayout(5, 1));

        ctnTop.add(ctnMenu);

        btnMenu = new JButton[5];
        for (int i = 0; i < btnMenu.length; i++) {
            btnMenu[i] = new JButton(new ImageIcon("img/" + i + ".png"));
            btnMenu[i].addActionListener(this);
            ctnMenu.add(btnMenu[i]);
            btnMenu[i].setBackground(Color.darkGray); // mudar a cor do retangulo
        }

        dskJanelas = new JDesktopPane();
        ctnPrincipal.add(dskJanelas, BorderLayout.CENTER);

        this.setUndecorated(false);//tira as bordas e a barra de titulo.
        //this.setSize(860,480); //define tamanho da janela
        this.setSize(getResolucao().width, getResolucao().height - 35);//Cria a tela Fullscreen.
        this.setLocationRelativeTo(null);//posiciona a janela no centro.
        this.show();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //coloca funções no botão abrir fechar etc.
    public void actionPerformed(ActionEvent evt) {

        if (evt.getSource() == btnMenu[0]) {

            appEstrutura = new ProdutosView();
            dskJanelas.add(appEstrutura);
            btnMenu[0].setEnabled(true);
        
        } else if (evt.getSource() == btnMenu[1]) {
            appAlvenaria = new ClientesView("Clientes",0);

            dskJanelas.add(appAlvenaria);
            btnMenu[1].setEnabled(true);

        } else if (evt.getSource() == btnMenu[2]) {
            appCobertura = new OrcamentoView();

            dskJanelas.add(appCobertura);
            btnMenu[2].setEnabled(true);
        
        } else if (evt.getSource() == btnMenu[3]) {
            appcaixa = new CaixaView();
            dskJanelas.add(appcaixa);

            btnMenu[3].setEnabled(true);
        
        } else if (evt.getSource() == btnMenu[4]) {
            appentregas = new EntregasView();
 
            dskJanelas.add(appentregas);
            btnMenu[4].setEnabled(true);

        }

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                if (fecharJanela() == true) {
                    dispose();
                } else {
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        }
        );
    }

    public static Dimension getResolucao() {
        Toolkit info = Toolkit.getDefaultToolkit();
        Dimension res = info.getScreenSize();

        return res;

    }

    public static boolean fecharJanela() {
        int resp = JOptionPane.showConfirmDialog(null,
                "Deseja realmente fechar?", "Fechar Janela",
                JOptionPane.YES_NO_OPTION);

        if (resp == JOptionPane.YES_OPTION) {
            return true;
        } else {
            return false;
        }
    }
}
