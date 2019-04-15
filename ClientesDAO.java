import java.sql.*;//elementos de manipulação de BD
import java.util.*; //Array de objetos (List, ArrayList)

public class ClientesDAO {

    public static Statement stClientes;  //permite a execução de quaisquer comandos SQL
    public static ResultSet rsClientes;  //armazena o resultado de uma consulta(SELECT)

    public static List<ClientesVO> listarClientes() throws Exception {

        List<ClientesVO> vetorClientes = new ArrayList<ClientesVO>();

        try {
            ConexaoDAO.abreConexao();
        } catch (Exception erro) {
            throw new Exception(erro.getMessage());
        }

        try {

            //Construçao do objeto Statement e ligaçao com a variavel de conexao
            stClientes = ConexaoDAO.connSistema.createStatement();

            //SELECT NO BANCO
            String sqlLista = "Select * from clientes";

            //Executando select e armazenando dados no ResultSet
            rsClientes = stClientes.executeQuery(sqlLista);

            while (rsClientes.next()) {//enquanto houver clientes
                ClientesVO tmpCliente = new ClientesVO();

                tmpCliente.setIdCliente(rsClientes.getInt("id_cliente"));
                tmpCliente.setCodigo(rsClientes.getInt("cod_cliente"));
                tmpCliente.setNome(rsClientes.getString("nome_cliente"));
                tmpCliente.setCidade(rsClientes.getString("cid_cliente"));
                tmpCliente.setTelefone(rsClientes.getString("tel_cliente"));

                vetorClientes.add(tmpCliente);

            }

        } catch (Exception erro) {
            throw new Exception("Falha na listagem de dados. Verifique a sintaxe da instruçao SQL\nErro Original:" + erro.getMessage());
        }

        try {
            ConexaoDAO.fechaConexao();
        } catch (Exception erro) {
            throw new Exception(erro.getMessage());
        }

        return vetorClientes;
    }

    public static ClientesVO consultarCliente(String tmpCodigo) throws Exception {
        ClientesVO tmpCliente = new ClientesVO();

        try {
            ConexaoDAO.abreConexao();
        } catch (Exception erro) {
            throw new Exception(erro.getMessage());
        }

        try {
            //programar aqui
            String sqlConsulta = "select * from clientes where cod_cliente like '" + tmpCodigo + "'";
            // ativando objeto statement para realizar a execução do select
            stClientes = ConexaoDAO.connSistema.createStatement();
            // executando o select e jogando no stClientes
            rsClientes = stClientes.executeQuery(sqlConsulta);

            // verifica se o cliente existe 
            if (rsClientes.next()) {

                tmpCliente.setCodigo(rsClientes.getInt("cod_cliente"));
                tmpCliente.setNome(rsClientes.getString("nome_cliente"));
                tmpCliente.setCpf(rsClientes.getString("cpf_cliente"));               
                tmpCliente.setCep(rsClientes.getInt("cep_cliente"));
                tmpCliente.setEndereco(rsClientes.getString("end_cliente"));
                tmpCliente.setCidade(rsClientes.getString("cid_cliente"));
                tmpCliente.setEstado(rsClientes.getString("estado_cliente"));            
                tmpCliente.setPais(rsClientes.getString("pais_cliente"));
                tmpCliente.setTelefone(rsClientes.getString("tel_cliente"));
                tmpCliente.setEmail(rsClientes.getString("email_cliente"));
              
            } else {
                tmpCliente = null;
            }

        } catch (Exception erro) {
            throw new Exception(erro.getMessage());
        }

        try {
            ConexaoDAO.fechaConexao();
        } catch (Exception erro) {
            throw new Exception(erro.getMessage());
        }

        return tmpCliente;

    }

    public static void cadastrarCliente(ClientesVO tmpCliente) throws Exception {

        try {
            ConexaoDAO.abreConexao();
        } catch (Exception erro) {
            throw new Exception(erro.getMessage());
        }

        try {
            
            String sqlCadastrar = "";
            
             sqlCadastrar+="Insert into clientes(";
             sqlCadastrar+="cod_cliente, nome_cliente,";
             sqlCadastrar+="cpf_cliente ,cep_cliente,";
             sqlCadastrar+="cid_cliente,estado_cliente,";
             sqlCadastrar+="pais_cliente,";
             sqlCadastrar+="tel_cliente,email_cliente)";
             
             sqlCadastrar+="values(";
             sqlCadastrar+=""+tmpCliente.getCodigo()+",";
             sqlCadastrar+="'"+tmpCliente.getNome()+"',";
             sqlCadastrar+="'"+tmpCliente.getCpf()+"',";
             sqlCadastrar+=""+tmpCliente.getCep()+",";
             sqlCadastrar+="'"+tmpCliente.getEndereco()+"',";
             sqlCadastrar+="'"+tmpCliente.getCidade()+"',";
             sqlCadastrar+="'"+tmpCliente.getEstado()+"',";     
             sqlCadastrar+="'"+tmpCliente.getPais()+"',";
             sqlCadastrar+="'"+tmpCliente.getTelefone()+"',";
             sqlCadastrar+="'"+tmpCliente.getEmail()+"')";
             
             //preparando a execução do insert
             stClientes = ConexaoDAO.connSistema.createStatement();
             stClientes.executeUpdate(sqlCadastrar);
             
             
        } catch (Exception erro) {
            throw new Exception("Falha na instrução insert verifique a sintaxe dos campos.\n"+ erro.getMessage());
        }

        try {
            ConexaoDAO.fechaConexao();
        } catch (Exception erro) {
            throw new Exception(erro.getMessage());
        }
    }

}
