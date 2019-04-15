import javax.swing.*;
import java.awt.*;
import java.util.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.JLabel.*;
public class OrcamentoView extends JInternalFrame{

    public static String strTopo[]={"Quantidade:","Nome do Produto:","Valor Unit.", "Total:"};
    public static Container appCobertura;
    public static JScrollPane scrProdutos; //barra de rolagem da tabela
    public static JTable tblProdutos;//tabela
    public static DefaultTableModel mdlProdutos;
    public static JLabel lblcliente, end, tel;
    public static JLabel lblBanner;
    public static JTextField nomeCliente;
    
    public OrcamentoView() {
        
        super("Orçamento");
        
        appCobertura = new Container();
        appCobertura.setLayout(null);
        this.add(appCobertura);
        
        lblBanner = new JLabel(new ImageIcon("img/novais.jpg"));
        lblBanner.setBounds(30, 5, 500, 500);
        appCobertura.add(lblBanner);  
         
        
        lblcliente = new JLabel("Cliente:");
        lblcliente.setFont(new Font(null, Font.ROMAN_BASELINE, 20));
        lblcliente.setBounds(560, 8, 100, 100);
        appCobertura.add(lblcliente);
        
        tblProdutos = new JTable();
        scrProdutos = new JScrollPane(tblProdutos);
        mdlProdutos = (DefaultTableModel)tblProdutos.getModel();
        
        nomeCliente = new JTextField();
        nomeCliente.setBounds(660, 43, 400, 30);
        appCobertura.add(nomeCliente);
        
        end = new JLabel("Endereço:");
        end.setFont(new Font(null, Font.ROMAN_BASELINE,20));
        end.setBounds(560, 43, 100, 80);
        appCobertura.add(end);
        
        //Inserindo elementos no topo da tabela
        for(int i = 0; i< strTopo.length;i++){
            mdlProdutos.addColumn(strTopo[i]);
        }
        
        
       // mldProdutos.addMouseListener(new MouseAdapter() {
         //   public void mouseClicked (MouseEvent evt){
           //String codigo = tblProdutos.getValueAt(tblProdutos.getSelectedRow(),0).toString();
        
        
        scrProdutos.setBounds(560,100,490,400);
        appCobertura.add(scrProdutos);


        
        
        this.setIconifiable(true);
        this.setClosable(true);
        this.setSize(1100, 560);
        this.show();
        
    }
    
    
    
}
