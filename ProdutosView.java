import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
public class ProdutosView extends JInternalFrame implements ActionListener{

    
   public static JButton jb,jb2,jb3,jb4;
   public static Container ctnApp;//criando Container
   public static ImageIcon imgApp;//Criar Imagem
   public static JLabel lblApp;//Criar Titulo
   public static JLabel lblBusca;
    public static JTextField txtBusca;
    public static JButton btnBusca;
   public static String strTopo[] = {"Código","Nome","Modelo", "Tipo", "Marca","Preço"};
   public static JScrollPane scrClientes; //barra de rolagem da tabela
   public static JTable tblClientes;//tabela
   public static DefaultTableModel mdlClientes;//Classe que gerencia o conteudo da tabela
  
   
   public static SistemaView objSistema;
    ProdutosView() {

        super("Produtos");       
      
        
        jb  = new JButton ("Salvar"); //(new ImageIcon("img/.png")); 
        setLayout(null);
        jb.setBounds(10, 10, 100, 60);
        //jb.addActionListener(this);
        add(jb);
        
        jb2 = new JButton("Consultar");
        setLayout(null);
        jb2.setBounds(150, 10, 100, 60);
        add(jb2);
        
        jb3 = new JButton("Alterar");
        setLayout(null);
        jb3.setBounds(290, 10, 100, 60);
        add(jb3);
        
        jb4 = new JButton("Cancelar");
        setLayout(null);
        jb4.setBounds(440, 10, 100, 60);
        add(jb4);

        tblClientes = new JTable();
        scrClientes = new JScrollPane(tblClientes);
        mdlClientes = (DefaultTableModel) tblClientes.getModel();
        
        lblBusca = new JLabel("Busca Rápida:");
        lblBusca.setBounds(10, 90, 100, 20);
        this.add(lblBusca);
        
        txtBusca = new JTextField();
        txtBusca.setBounds(100, 90, 400, 20);
        this.add(txtBusca);
        
        btnBusca = new JButton("Pesquisar");
        btnBusca.addActionListener(this);
        btnBusca.setBounds(530, 90, 100, 20);
        this.add(btnBusca);
                
        
        

        
        for (int i = 0; i < strTopo.length; i++) {
            mdlClientes.addColumn(strTopo[i]);
        }
        
        scrClientes.setBounds(10, 120,700, 350);
        this.add(scrClientes); 
        
        
        
        this.setIconifiable(true);
        this.setClosable(true);
        this.setSize(950, 550);
        this.show();

        
        
                 
        
    }

            
           
          public void actionPerformed(ActionEvent evt) {  
            
          }
         
        
    
}
