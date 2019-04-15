import javax.swing.*;
import java.awt.*;

public class AppView extends JFrame{
    public static Container ctnApp;//criando Container
    public static ImageIcon imgApp;//Criar Imagem
    public static JLabel lblApp;//Criar Titulo
    
   public AppView (){
       ctnApp = new Container();//instanciando Container
       ctnApp.setLayout(null);//
       this.add(ctnApp);//
       
       imgApp = new ImageIcon("img/carregando.gif");//instanciando Imagem
       
       lblApp = new JLabel(imgApp);//Instanciando titulo.
       lblApp.setBounds(0,0,759,632);//
       ctnApp.add(lblApp);
      
       
       this.setUndecorated(true);//tira as bordas e a barra de titulo.
       this.setSize(759,632); //define tamanho da janela
       this.setLocationRelativeTo(null);//posiciona a janela no centro.
       this.show();//Mostrar a janela
   } 
    
    
    
}
