
public class ProdutosVO {
    
    public int categoria,quantEstoque,quantPacote,status;
    public float valor;
    public String  codigo,nome,Imagem;
    
    
   public ProdutosVO(){
        
        this.setCodigo("");
        this.setCategoria(0);
        this.setQuantEstoque(0);
        this.setValor(0);
        this.setNome(null);
        this.setQuantPacote(0);
        this.setImagem(null); 
        this.setStatus(0);
        
        
    }


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String tmpCodigo) {
        this.codigo = tmpCodigo;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int tmpCategoria) {
        this.categoria = tmpCategoria;
    }

    public int getQuantEstoque() {
        return quantEstoque;
    }

    public void setQuantEstoque(int tmpQuantEstoque) {
        this.quantEstoque = tmpQuantEstoque;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float tmpValor) {
        this.valor = tmpValor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String tmpNome) {
        this.nome = tmpNome;
    }

    public int getQuantPacote() {
        return quantPacote;
    }

    public void setQuantPacote(int tmpQuantPacote) {
        this.quantPacote = tmpQuantPacote;
    }

    public String getImagem() {
        return Imagem;
    }

    public void setImagem(String tmpImagem) {
        this.Imagem = tmpImagem;
    }
    
    public int getStatus() {
        return status;
    }

    public void setStatus(int tmpStatus) {
        this.status = tmpStatus;
    }
    
}
