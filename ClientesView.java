import com.sun.corba.se.impl.orbutil.CorbaResourceUtil;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.text.*; //MaskFormatter
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.filechooser.FileFilter.*;
import java.nio.channels.*;
import java.io.*;
import javax.swing.filechooser.FileNameExtensionFilter;



public class ClientesView extends JInternalFrame implements ActionListener{

    
    public static Container ctnClientes;// container onde são criados e adicionados todos componentes da tela.
    public static String strCampos[] = {"Código:", "Nome Completo:", "CPF:", "CEP:", 
        "Endereço:", "Cidade:", "Estado:", "País:", "Telefone:", "E-mail:"};//Vetor com valores de texto de cada campo da tela.
    public static JLabel lblCampos[],lblTitulo;// vetor de titulos dos campos
    public static JTextField txtCampos[];//vetor da caixa de texto dos campos
    public static JButton btnSalvar,btnConsulta,btnCancelar;//declaração dos botões da tela.
    
    public static MaskFormatter mftCep, mftTelefone;//objeto que permite criar uma mascara para o formato de texto.
    public static JFormattedTextField ftxCep, ftxTelefone;// caixa de texto que aceita texto pre-formatado pela mascara.
    
    
    public static String strTopo[] = {"Código", "Nome", "Cidade", "Telefone"};//Vetor com os dados da linha de titulo da tabela.
    public static JScrollPane scrClientes; //barra de rolagem da tabela
    public static JTable tblClientes;//tabela
    public static DefaultTableModel mdlClientes;//Classe que gerencia o conteudo da tabela
    
    public static ClientesVO tmpCliente = new ClientesVO();// criando um objeto do tipo classe ClientesVO.
    
    //Iniando o metodo construtor
    public ClientesView(String strModulo,int tmpIndice) {
        
        super("Módulo " + strModulo);//tilulo da tela na barra superior

        ctnClientes = new Container();//declarando o inicio do container
        ctnClientes.setLayout(null);//modelo de layout
        this.add(ctnClientes);//adicionando container a classe ClientesView

        lblCampos = new JLabel[strCampos.length];//declarando o inicio dos titulos.
        txtCampos = new JTextField[strCampos.length];//declarando as caixas de textos.
        
        lblTitulo = new JLabel("Cadastro de Clientes:");//Titulo principal da Tela
        lblTitulo.setFont(new Font("Cadastro de Clientes:", Font.ROMAN_BASELINE, 20));// formato de fonte do titulo
        lblTitulo.setBounds(300,15,300,50);//possição do titulo na tela
        ctnClientes.add(lblTitulo);//adicionando o titulo ao container da Tela da classe ClientesView
        

        for (int i = 0; i < lblCampos.length; i++) {//estrutura de repetição para possicionar os demais titulos na tela.
            lblCampos[i] = new JLabel(strCampos[i]);
            lblCampos[i].setBounds(230, 100 + (i * 30), 150, 20);
            ctnClientes.add(lblCampos[i]);

            txtCampos[i] = new JTextField();
            txtCampos[i].setBounds(330, 100 + (i * 30), 280, 20);
            ctnClientes.add(txtCampos[i]);

        }
        btnSalvar = new JButton("Salvar");//criando botão salvar
        btnSalvar.setBounds(230,450,100,20);
        ctnClientes.add(btnSalvar);
        
        btnConsulta = new JButton("Consultar");//criando botão de consulta
        btnConsulta.setBounds(360,450,100,20);
        ctnClientes.add(btnConsulta);
        
        btnCancelar = new JButton("Cancelar");//criando botão de cancelamento
        btnCancelar.setBounds(500,450,100,20);
        //btnCancelar.addActionListener(this);
        ctnClientes.add(btnCancelar);
        
        tblClientes = new JTable();//criando a tabela da tela de clientes
        scrClientes = new JScrollPane(tblClientes);//adicionando a tabela ao Scroll que controla a tabela up e down
        mdlClientes = (DefaultTableModel) tblClientes.getModel();
        
        //Inserindo elementos no topo da tabela
        for (int i = 0; i < strTopo.length; i++) {
            mdlClientes.addColumn(strTopo[i]);
        }

        tblClientes.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent evt) {

                String codigo = tblClientes.getValueAt(tblClientes.getSelectedRow(), 0).toString();

                //*****CHAMAR CONSULTA******
                try {

                    preencherCampos(ClientesDAO.consultarCliente(codigo));

                } catch (Exception erro) {
                    JOptionPane.showMessageDialog(null, erro.getMessage());
                }

            }

        }
        );
        
        scrClientes.setBounds(700, 140, 550, 290);
        ctnClientes.add(scrClientes);
        
         /*try {
            mftCep = new MaskFormatter("#####-###");
            mftCep.setValueContainsLiteralCharacters(false);//ignorar caracteres da mascara
            mftTelefone = new MaskFormatter("(##)#####-####");
            mftTelefone.setValueContainsLiteralCharacters(false);

           // ftxCep = new JFormattedTextField(mftCep);
            //ftxCep.setBounds(330, 220, 280, 20);
            //ctnClientes.add(ftxCep);

            //ftxTelefone = new JFormattedTextField(mftTelefone);
            //ftxTelefone.setBounds(330, 370, 280, 20);
            //ctnClientes.add(ftxTelefone);

        } catch (Exception erro) {

        }*/
         
         carregarTabela();
         
         this.addInternalFrameListener(
                new InternalFrameAdapter() {
            public void internalFrameClosing(InternalFrameEvent evt) {

                if (SistemaView.fecharJanela() == true) {
                    SistemaView.btnMenu[tmpIndice].setEnabled(true);
                    dispose();
                } else {
                    setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
                }

            }

        }
        );
        
        
        
        this.setIconifiable(true);
        this.setClosable(true);
        this.setSize(1400,860);
        this.show();
        
    } //Fechando o metodo construtor
    
     /**
     * *****TRATAMENTO DE AÇÃO DOS BOTÕES******
     */
     public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == btnSalvar) {
            //chamada do metodo de validação
            //if(validaCampos()== true){
            preencherObjetos();
                try {
                    
                    ClientesDAO.cadastrarCliente(tmpCliente);
                    JOptionPane.showMessageDialog(null, "OK");
                    carregarTabela();
                    
                } catch (Exception erro) {
                    
                    JOptionPane.showMessageDialog(null, erro.getMessage());
                   
                }
            
            
            
            //}
            //validaCampos();
        } else if (evt.getSource() == btnCancelar) {

            limparCampos();
            desbloquearCampos(true);

        } else if (evt.getSource() == btnConsulta) {

            FileNameExtensionFilter filtro = new FileNameExtensionFilter("Arquivos de Imagem", "png", "jpg");

            JFileChooser flcImagem = new JFileChooser();

            flcImagem.setFileFilter(filtro);

            int resp = flcImagem.showOpenDialog(this);

            String arquivoX = "", caminhoX = "";
            String caminhoY = "";

            if (resp == JFileChooser.APPROVE_OPTION) {

                caminhoX = flcImagem.getSelectedFile().getPath();
                arquivoX = flcImagem.getSelectedFile().getPath();

                //arquivoY = txtCampos[0].getText()+".png";
                caminhoY = "img\\system\\" /*+ arquivoY*/;
                //copia da imagem
                try {
                    
                    // copia imagem
                    
                    //abre o arquivo de origem
                    FileInputStream flx = new FileInputStream(caminhoX);
                    //prepara o arquivo de destino
                    FileOutputStream fly = new FileOutputStream(caminhoY);
                    //buffer de leitura para os arquivos 
                    FileChannel flcX = flx.getChannel();
                    FileChannel flcY = fly.getChannel();
                    
                    //le o arquivo de origem por completo todos os bytes a patir do byte 0 até o final
                    //copiando para o diretorio final 
                    
                    flcX.transferTo(0, flcX.size(), flcY);
                    
                    //fechando ambos arquivos
                    flx.close();
                    fly.close();
                    
                    //preview da imagem
                    //lblFoto.setIcon(new ImageIcon(caminhoY));

                } catch (Exception erro) {
                    JOptionPane.showMessageDialog(null, erro.getMessage());

                }
            }
        }

    }//fechando actionPerformed
    
   /* public static boolean validaCampos() {
        
        String expEmail = "^(\\w?)+\\@[\\w]+(\\.\\w+)+$";
        String expCodigo = "^\\D{5}$";
        String expCep = "^\\d{5}\\-\\d{3}$";
        String expTelefone = "^\\(\\d{2}\\)\\d{5}-\\d{4}$";

        if (!txtCampos[0].getText().matches(expCodigo)) {
            JOptionPane.showMessageDialog(null, "Código do Cliente inválido. (5 letras)", "ERRO", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (!ftxCep.getText().matches(expCep)) {
            JOptionPane.showMessageDialog(null, "Formato de cep inválido(8 dígitos)", "ERRO", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (!ftxTelefone.getText().matches(expTelefone)) {
            JOptionPane.showMessageDialog(null, "Telefone inválido((XX)XXXXX-XXXX)", "ERRO", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else if(!txtCampos[9].getText().matches(expEmail)){
        JOptionPane.showMessageDialog(null, "Email invalido");
        return false;
        }
                 
        for(int i=0 ;i<txtCampos.length;i ++){
        
            if(i!=3 && i!=9){
        
                if(txtCampos[i].getText().compareTo("")==0){
                
                    JOptionPane.showMessageDialog(null,"Preencha o campo"+strCampos[i]);
                    
                    txtCampos[i].grabFocus();
                    
                    return false;
                }
        
        }
        
        }
        //System.out.println(ftxCep.getValue().toString());
        return true;
    }*/
    
    
    public static void carregarTabela() {

        //Criando array de clientes para receber a listagem dos dados (CLIENTESDAO)
        java.util.List<ClientesVO> vetorClientes = new ArrayList<ClientesVO>();

        //todo metodo criado com throws exception deve ser chamado em um try...catch
        try {

            vetorClientes = ClientesDAO.listarClientes();

            String dados[] = new String[4];
            //Preencher tabela - mdlClientes
            //for responsavel pela varredura do vetor
            for (ClientesVO tmpCliente : vetorClientes) {
                dados[0] = Integer.toString(tmpCliente.getCodigo());
                dados[1] = tmpCliente.getNome();
                dados[2] = tmpCliente.getCidade();
                dados[3] = tmpCliente.getTelefone();

                mdlClientes.addRow(dados);
            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }

    }
    
    public static void preencherCampos(ClientesVO tmpCliente) {

        txtCampos[0] .setText(Integer.toString(tmpCliente.getCodigo()));
        txtCampos[1].setText(tmpCliente.getNome());
        txtCampos[2].setText(tmpCliente.getCpf());
        txtCampos[3].setText(Integer.toString(tmpCliente.getCep()));
        txtCampos[4].setText(tmpCliente.getEndereco());
        txtCampos[5].setText(tmpCliente.getCidade());
        txtCampos[6].setText(tmpCliente.getEstado());
        txtCampos[7].setText(tmpCliente.getPais());
        txtCampos[8].setText(tmpCliente.getTelefone());
        txtCampos[9].setText(tmpCliente.getEmail());
        //txtCampos[11].setText(tmpCliente.getNome());

        //ftxCep.setText(tmpCliente.getCep());
        //ftxTelefone.setText(tmpCliente.getTelefone());
    }
    
    public static void desbloquearCampos(boolean tmpStatus) {

        for (int i = 0; i < txtCampos.length; i++) {

            if (i != 3 && i != 9) {
                // desabilitando caixas de textos
                txtCampos[i].setEditable(tmpStatus);

            }

            ftxCep.setEditable(tmpStatus);
            ftxTelefone.setEditable(tmpStatus);
            btnConsulta.setVisible(tmpStatus);

        }// fechando o desbloquear

    }
    
      public static void limparCampos() {

        for (int i = 0; i < txtCampos.length; i++) {

            if (i != 4 && i != 9) {
                // limpando caixas de textos
                txtCampos[i].setText("");

            }

            ftxCep.setText("");
            ftxTelefone.setText("");

        }

    }
    
    public static void preencherObjetos(){
    
    tmpCliente.setCodigo(Integer.parseInt(txtCampos[0].getText()));
    tmpCliente.setNome(txtCampos[1].getText());
    tmpCliente.setCpf(txtCampos[2].getText());
    tmpCliente.setCep(Integer.parseInt(txtCampos[3].getText()));
    tmpCliente.setEndereco(txtCampos[4].getText());
    tmpCliente.setCidade(txtCampos[5].getText());
    tmpCliente.setEstado(txtCampos[6].getText());
    tmpCliente.setPais(txtCampos[7].getText());
    tmpCliente.setTelefone(txtCampos[8].getText());
    tmpCliente.setEmail(txtCampos[9].getText());
       
    //tmpCliente.setStatus(1);
  //  tmpCliente.setCep(ftxCep.getText());
    //tmpCliente.setTelefone(ftxTelefone.getText());
   
    
    }
    
    
}
