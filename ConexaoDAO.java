import java.sql.*;

public class ConexaoDAO {

    //Classe responsavel pela abertura de Conexao a BD
    public static Connection connSistema;
        
    //Cláusula throws Exception indica a possibilidade de uma exceção na execução do metodo.
    public static void abreConexao() throws Exception{
        
        //String de Conexao ao BD
        try{
            //Comandos de abertura do BANCO
            Class.forName("com.mysql.jdbc.Driver");//identificando biblioteca
            connSistema = DriverManager.getConnection("jdbc:mysql://localhost:3306/sales","root","admin");
            
            System.out.println("Status da Conexão: iniciada");
            
        }catch(Exception erro){
            throw new Exception ("Falha na abertura do Banco. Verifique os caminhos da conexão e biblioteca \nErro Original: " + erro.getMessage());
        }        
        
    }//fechando abreConexao()

    public static void fechaConexao()throws Exception {

        try{
            connSistema.close();//encerrando conexão ao BD
            System.out.println("Status da Conexao: encerrada.");
            
        }catch(Exception erro){
            throw new Exception ("Não existem conexões abertas. Verifique as Strings de Conexão ou se o BD foi aberto. \nErro Original: " + erro.getMessage());
        }
        
    }

}
