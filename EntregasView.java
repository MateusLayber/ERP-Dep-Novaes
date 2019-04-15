

import javax.swing.table.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class EntregasView extends JInternalFrame {

    public static String strTopo[]={};
    public static Container appentregas;
    public static JScrollPane scrProdutos; //barra de rolagem da tabela
    public static JTable tblProdutos;//tabela
    public static DefaultTableModel mdlProdutos;
    public static JLabel lblcliente;

    public EntregasView() {

        super("Entregas");

        appentregas = new Container();
        appentregas.setLayout(null);
        this.add(appentregas);

        
        
         tblProdutos = new JTable();
        scrProdutos = new JScrollPane(tblProdutos);
        mdlProdutos = (DefaultTableModel)tblProdutos.getModel();
        
        //Inserindo elementos no topo da tabela
        for(int i = 0; i< strTopo.length;i++){
            mdlProdutos.addColumn(strTopo[i]);
        }
        
        
        //blProdutos.addMouseListener(new MouseAdapter() {
          //  public void mouseClicked (MouseEvent evt){
           //String codigo = tblProdutos.getValueAt(tblProdutos.getSelectedRow(),0).toString();
        
        
        scrProdutos.setBounds(10,120,500,490);
        appentregas.add(scrProdutos);


        
        
        this.setIconifiable(true);
        this.setClosable(true);
        this.setSize(950, 550);
        this.show();
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

}
