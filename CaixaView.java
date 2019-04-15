
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.text.*; //MaskFormatter
import javax.swing.filechooser.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;
import java.nio.channels.FileChannel;
import jdk.management.resource.internal.TotalResourceContext;

public class CaixaView extends JInternalFrame implements ActionListener {

    public static Container ctnCaixa;
    public static JLabel lblCodigo, lblQuant, lblPrecoUnit, lblDescProd, lblPrecoTotal, lblTotalPagar;
    public static JLabel lblBanner;
    public static JTextField txtCodigo, txtQuant, txtPrecoUnit, txtDescProd, txtPrecoTotal, txtTotalPagar;
    public static JButton btnReceber;

    public static String strTopo[] = {"Código:", "Nome do Produto:", "Qtd:", " Preço Unitário:", "Preço Total:"};
    public static JScrollPane scrProdutos; //barra de rolagem da tabela
    public static JTable tblProdutos;//tabela
    public static DefaultTableModel mdlProdutos;
    public static ImageIcon icnReceber;
    public static int TotalVenda;

    public CaixaView() {

        super("Caixa");

        TotalVenda = 0;

        ctnCaixa = new Container();
        ctnCaixa.setLayout(null);
        this.add(ctnCaixa);

        lblBanner = new JLabel(new ImageIcon("img/banner1.png"));
        lblBanner.setBounds(10, 10, 980, 80);
        ctnCaixa.add(lblBanner);

        lblCodigo = new JLabel("Código/Nome do Produto:");
        lblCodigo.setFont(new Font(null, Font.ROMAN_BASELINE, 20));
        lblCodigo.setBounds(550, 120, 300, 50);
        ctnCaixa.add(lblCodigo);

        txtCodigo = new JTextField();
        txtCodigo.setBounds(550, 165, 260, 25);
        ctnCaixa.add(txtCodigo);

        lblQuant = new JLabel("Qtd.");
        lblQuant.setFont(new Font(null, Font.ROMAN_BASELINE, 20));
        lblQuant.setBounds(840, 120, 70, 50);
        ctnCaixa.add(lblQuant);

        txtQuant = new JTextField();
        txtQuant.setBounds(820, 165, 70, 25);
        ctnCaixa.add(txtQuant);

        lblPrecoUnit = new JLabel("Preço Unitário:");
        lblPrecoUnit.setFont(new Font(null, Font.ROMAN_BASELINE, 20));
        lblPrecoUnit.setBounds(550, 190, 200, 50);
        ctnCaixa.add(lblPrecoUnit);

        txtPrecoUnit = new JTextField();
        txtPrecoUnit.setBounds(550, 230, 140, 25);
        ctnCaixa.add(txtPrecoUnit);

        lblPrecoTotal = new JLabel("Preço Total:");
        lblPrecoTotal.setFont(new Font(null, Font.ROMAN_BASELINE, 20));
        lblPrecoTotal.setBounds(745, 190, 200, 50);
        ctnCaixa.add(lblPrecoTotal);

        txtPrecoTotal = new JTextField();
        txtPrecoTotal.setBounds(745, 230, 140, 25);
        ctnCaixa.add(txtPrecoTotal);

        lblTotalPagar = new JLabel("Total a Pagar:" + TotalVenda);
        lblTotalPagar.setFont(new Font(null, Font.ROMAN_BASELINE, 28));
        lblTotalPagar.setBounds(700, 290, 190, 50);
        ctnCaixa.add(lblTotalPagar);

        icnReceber = new ImageIcon("img/receber.png");
        btnReceber = new JButton("Receber", icnReceber);
        // btnReceber.addActionListener(this);
        btnReceber.setBounds(720, 400, 150, 50);
        ctnCaixa.add(btnReceber);

        tblProdutos = new JTable();
        scrProdutos = new JScrollPane(tblProdutos);
        mdlProdutos = (DefaultTableModel) tblProdutos.getModel();

        //Inserindo elementos no topo da tabela
        for (int i = 0; i < strTopo.length; i++) {
            mdlProdutos.addColumn(strTopo[i]);
        }

        //blProdutos.addMouseListener(new MouseAdapter() {
        //  public void mouseClicked (MouseEvent evt){
        //String codigo = tblProdutos.getValueAt(tblProdutos.getSelectedRow(),0).toString();
        scrProdutos.setBounds(10, 120, 500, 490);
        ctnCaixa.add(scrProdutos);

        this.setIconifiable(true);
        this.setClosable(true);
        this.setSize(950, 550);
        this.show();

    }

    public void actionPerformed(ActionEvent evt) {
        if(evt.getSource()==btnReceber){
            for(int i=0;i<5;i++){
            TotalVenda = TotalVenda + 15;
            lblTotalPagar.setText(""+TotalVenda);
        }
        }
       
    
    

    }//fechando actionPerformed

    public static void carregarTabela(int tmpTipo, String tmpChave) {

        while (mdlProdutos.getRowCount() > 0) {//enquanto houver linhas
            mdlProdutos.removeRow(0);
        }

        try {
            java.util.List<ProdutosVO> vetorProdutos = new ArrayList<ProdutosVO>();

            vetorProdutos = ProdutosDAO.listarProdutos();

            //String necessaria para preenchimento do DefaultTableModel
            String dados[] = new String[4];

            //Para cada cliente existente dentro do vetor
            for (ProdutosVO produtoAtual : vetorProdutos) {
                //adicionar cliente na JTable

                //preenchendo String com atributos
                dados[0] = produtoAtual.getCodigo();
                dados[1] = produtoAtual.getNome();
                dados[2] = Float.toString(produtoAtual.getValor());
                dados[3] = Integer.toString(produtoAtual.getQuantPacote());

                //add String no defaultTableModel
                mdlProdutos.addRow(dados);

            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }

    }

}
