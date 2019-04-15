
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginView extends JInternalFrame implements ActionListener {

    public static Container ctnLogin;

    public static JLabel lblUsuario, lblSenha;
    public static JTextField txtUsuario;
    public static JPasswordField pwdSenha;
    public static JButton btnEntrar, btnCancelar;

    public LoginView() {
        super("Acesso ao sistema");

        ctnLogin = new Container();
        ctnLogin.setLayout(null);
        this.add(ctnLogin);

        lblUsuario = new JLabel("Nome de usuário:");
        lblUsuario.setBounds(20, 40, 150, 20);
        ctnLogin.add(lblUsuario);

        lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(20, 90, 150, 20);
        ctnLogin.add(lblSenha);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(130, 40, 180, 20);
        ctnLogin.add(txtUsuario);

        pwdSenha = new JPasswordField();
        pwdSenha.setBounds(130, 90, 180, 20);
        ctnLogin.add(pwdSenha);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(this);
        btnCancelar.setBounds(20, 140, 120, 30);
        ctnLogin.add(btnCancelar);

        btnEntrar = new JButton("Entrar");
        btnEntrar.addActionListener(this);
        btnEntrar.setBounds(190, 140, 120, 30);
        ctnLogin.add(btnEntrar);

        this.setResizable(false);
        this.setIconifiable(false);
        this.setLocation(320, 70);

        this.setSize(350, 250);
        this.show();
    }

    public void actionPerformed(ActionEvent evt) {

        if (evt.getSource() == btnEntrar) {

            if (verificaUsuario(txtUsuario.getText(), pwdSenha.getText()) == 1) {

                for (int i = 0; i < SistemaView.btnMenu.length; i++) {
                    SistemaView.btnMenu[i].setEnabled(true);

                }
                
                JOptionPane.showMessageDialog(null, "Bem vindo ao seu Sistema");
                this.dispose();

            } else {
                JOptionPane.showMessageDialog(null, "Dados Inválidos");
            }

        } else if (evt.getSource() == btnCancelar) {

            txtUsuario.setText(null);
            pwdSenha.setText(null);
            dispose();
           

        }

    }

    public static int verificaUsuario(String tmpUsuario, String tmpSenha) {

        if (tmpUsuario.compareTo("admin") == 0) {
            if (tmpSenha.compareTo("123456") == 0) {
                return 1;
            }
        }

        return 0;
    }

}
