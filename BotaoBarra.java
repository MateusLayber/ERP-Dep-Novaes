import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//Cada botao da Barra de Ferramentas
//será uma instancia da classe atual;
public class BotaoBarra extends AbstractAction {
    
    public BotaoBarra(int tmpId, ImageIcon tmpIcone, String tmpDesc){
        super(tmpDesc,tmpIcone);
        
        this.putValue("id", tmpId);        
        this.putValue(SHORT_DESCRIPTION, tmpDesc);        
        
    }
    
    public void actionPerformed(ActionEvent evt){
        
        //Clicando no 1º botao da barra de ferramentas do modulo1
        if((int)getValue("id") == 0){
            
        }
        
    }//fechando actionPerformed
}//fechando classe
