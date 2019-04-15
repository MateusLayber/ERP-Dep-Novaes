import java.sql.*;
import java.util.*;

public class ProdutosDAO {
    
    public static Statement stProdutos;
    public static ResultSet rsProdutos;
    
     public static List<ProdutosVO> listarProdutos() throws Exception {
        /* - Método que retorna uma listagem de Produtos
          - Será armazenado em um vetor de ProdutosVO (LIST)
          - Tratando-se de um retorno com varios produtos, a leitura do ResultSet
            será feita através de um laço de repetição
         */

        //criação do vetor para receber os dados da tabela
        List<ProdutosVO> vetorProdutos = new ArrayList<ProdutosVO>();

        try {
            ConexaoDAO.abreConexao();
        } catch (Exception erro) {
            throw new Exception(erro.getMessage());
        }

        try {
            //AQUI VAI O SELECT

            String sqlLista = "";            
            sqlLista = "SELECT * FROM PRODUTOS";
            
            

            //A  linha de codigo a seguir, liga o objeto Statement ao banco de 
            //dados que foi selecionado na classe Connection(connSistema)
            stProdutos = ConexaoDAO.connSistema.createStatement();

            //Executando a instrução SELECT e armazenando os dados no resultSet 
            rsProdutos = stProdutos.executeQuery(sqlLista);

            //
            while (rsProdutos.next()) {
                //leitura de cada linha da tabela e armazenamento na <ARRAY>
                ProdutosVO tmpProdutos = new ProdutosVO();//um objeto produto para cada linha da tabela

                tmpProdutos.setCodigo(rsProdutos.getString("id_produto"));
                tmpProdutos.setNome(rsProdutos.getString("nome_prod"));
                tmpProdutos.setValor(rsProdutos.getFloat("preco_prod"));
                

                //Objeto Produto Preenchido - jogar no vetor
                vetorProdutos.add(tmpProdutos);

            }

        } catch (Exception erro) {
            throw new Exception("Falha na execução da Listagem. Verifique a sintaxe do SELECT e nome de tabelas e campos.\nErro Original:" + erro.getMessage());
        }

        try {
            ConexaoDAO.fechaConexao();
        } catch (Exception erro) {
            throw new Exception(erro.getMessage());
        }

        return vetorProdutos;
    }
     
     
     public static ProdutosVO consultarProduto (int tmpCodigo) throws Exception{
         ProdutosVO tmpProduto = new ProdutosVO();

        try {
            ConexaoDAO.abreConexao();
        } catch (Exception erro) {
            throw new Exception(erro.getMessage());
        }

        try {
            //PROGRAMAR AQUI
            String sqlConsulta = "SELECT * FROM PRODUCTS WHERE id_prod = " + tmpCodigo;

            stProdutos = ConexaoDAO.connSistema.createStatement();
            rsProdutos = stProdutos.executeQuery(sqlConsulta);

            if (rsProdutos.next()) {//se houver registros
                //Preencher o objeto
                tmpProduto.setCodigo(rsProdutos.getString("id_prod"));
                tmpProduto.setNome(rsProdutos.getString("nime_prod"));
                tmpProduto.setValor(rsProdutos.getFloat("preco_prod"));
                

            } else {
                //objeto nulo - retornar objeto vazio
                tmpProduto = null;
            }

        } catch (Exception erro) {
            throw new Exception("Falha na consulta. Verifique a instrução Select.\nErro Original: " + erro.getMessage());
        }

        try {
            ConexaoDAO.fechaConexao();
        } catch (Exception erro) {
            throw new Exception(erro.getMessage());
        }

        return tmpProduto;
     }
}


    

