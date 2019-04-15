public class ClientesVO {

    /**
     * ***DECLARAÇÃO DOS ATRIBUTOS****
     */
    public  String nome,cpf,endereco ;
    public  String cidade, estado, pais,telefone, email;
    public  int idCliente,codigo,cep;

    /**
     * ***Método Construtor****
     */
    
    public ClientesVO() {
        this.setIdCliente(0);
       this.setCodigo(0);
       this.setNome(null);
       this.setCpf(null);
       this.setCep(0);
       this.setEndereco(null);
       this.setCidade(null);
       this.setEstado(null);
       this.setPais(null);
       this.setTelefone(null);
       this.setEmail(null);     
    }
  
    /**
     * ***Métodos Set e Get****
     */
    
    public int getIdCliente(){
        return this.idCliente;
    }
    
    public void setIdCliente( int tmpIdCliente){
        this.idCliente = tmpIdCliente;
    }
    
    public int getCodigo() {
        return this.codigo;
    }

    public void setCodigo(int tmpCodigo) {
        this.codigo = tmpCodigo;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String tmpNome) {
        this.nome = tmpNome;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String tmpCpf) {
        this.cpf = tmpCpf;
    }

    public int getCep() {
        return this.cep;
    }

    public void setCep(int tmpCep) {
        this.cep = tmpCep;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String tmpEndereco) {
        this.endereco = tmpEndereco;
    }

    public String getCidade() {
        return this.cidade;
    }

    public void setCidade(String tmpCidade) {
        this.cidade = tmpCidade;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String tmpEstado) {
        this.estado = tmpEstado;
    }

    public String getPais() {
        return this.pais;
    }

    public void setPais(String tmpPais) {
        this.pais = tmpPais;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String tmpTelefone) {
        this.telefone = tmpTelefone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String tmpEmail) {
        this.email = tmpEmail;
    }

   

}
