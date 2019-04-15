
public class DepositoControle {

    public static SistemaView objSistema;
    public static ProdutosView appEstrutura;
    public static ClientesView appAlvenaria;
    public static OrcamentoView appCobertura;
    public static CaixaView appcaixa;
    public static EntregasView appentrega;
    public static LoginView appLogin;

    public static AppView objApp;

    public static void main(String[] args) {

        try {
            ConexaoDAO.abreConexao();
        } catch (Exception erro) {
            System.out.println(erro.getMessage());
        }

        try {
            ConexaoDAO.fechaConexao();
        } catch (Exception erro) {
            System.out.println(erro.getMessage());
        }

        try {

            objApp = new AppView();
            Thread.sleep(2000);
            objApp.dispose();
            
            objSistema = new SistemaView();
            
            //appLogin = new LoginView();
            //objSistema.dskJanelas.add(appLogin);

            appEstrutura = new ProdutosView();
            appAlvenaria = new ClientesView("Clientes",0);
            appCobertura = new OrcamentoView();
            appcaixa = new CaixaView();
            appentrega = new EntregasView();

        } catch (Exception erro) {

            System.out.println("Falha no Carregamento.\n"
                    + erro.getMessage());
        }
    }

}
