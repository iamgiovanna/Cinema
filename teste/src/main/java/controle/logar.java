
package controle;


import conexao.Conexao;
import java.sql.SQLException;
import javax.swing.JOptionPane; 
import javax.sql.*;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import java.awt.*;
import java.text.*;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class logar  extends  JFrame{
      Conexao con_cliente;
    
    JPasswordField tsen;
    JLabel rusu,rsen,rtit,imagem;
    JTextField tusu;
    JButton blogar;
  
    private int tentativasRestantes = 1;
    
    
    
    public logar(){
        
        con_cliente = new Conexao(); 
        con_cliente.conecta();
        
        setTitle("*** Login de Acesso ***");
        Container tela = getContentPane();
        setLayout(null);
        rtit = new JLabel("Acesso ao Sistema");
        rusu = new JLabel("Usuário: ");
        rsen = new JLabel("Senha: ");
        blogar = new JButton("Logar");
        ImageIcon icone = new ImageIcon("src/cinema.JPG");
        imagem = new JLabel(icone);
        
        tusu = new JTextField(30);
        tsen = new JPasswordField(10);
      
      
        
        rtit.setFont(new Font("Comic Sans MS",Font.BOLD,20));
        rusu.setFont(new Font("Comic Sans MS",Font.BOLD,12));
        rsen.setFont(new Font("Comic Sans MS",Font.BOLD,12));
        blogar.setBackground(new Color(184,56,71));
   
        
 
    
    
     blogar.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            
            try{
               String pesquisa = "select * from usuario where usuario like '" + tusu.getText() + "' && senha = " + tsen.getText() + "";
                con_cliente.executaSQL(pesquisa);
                
                if(con_cliente.resultset.first())
                {//ACESSO AO FORM DE CADASTRO
               
                    form mostra = new form();
                    mostra.setVisible(true);
                    dispose(); 
                    
                  
                  } else { // Acesso negado
                JOptionPane.showMessageDialog(null, "\n Usuário não cadastrado!!!!", "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
                tentativasRestantes--;
                if (tentativasRestantes == 0) {
                    JOptionPane.showMessageDialog(null, "Você só tem uma tentativa.", "Tentativas Excedidas", JOptionPane.ERROR_MESSAGE);
                    tusu.setText("");
                    tsen.setText("");
                    tusu.requestFocus();
                    
                   
                }
                if(tentativasRestantes < 0){
                    JOptionPane.showMessageDialog(null," O programa sera encerrado, Tentativas Excedidas","Tentativas Excedidas", JOptionPane.ERROR_MESSAGE); 
                    System.exit(0);
                }
            }
                
                    
                    
                }catch(SQLException errosql){
                    JOptionPane.showMessageDialog(null,"\n Os dados digitados não foram localizados!! :\n "+errosql,"Mensagem do Programa",JOptionPane.INFORMATION_MESSAGE);

            }
            
        }
    });
         
     blogar.setBounds(180,200,100,20);
     tusu.setBounds(150,90,150,20);
     imagem.setBounds(0,0,503,341);
     rusu.setBounds(90,90,150,20);
     rsen.setBounds(90,120,50,20);
     rtit.setBounds(150,30,200,50);
     tsen.setBounds(150,120,150,20);
     
     tela.add(blogar);
     tela.add(tusu);
     tela.add(rusu);
     tela.add(rsen);
     tela.add(rtit);
     tela.add(tsen);
     tela.add(imagem);
     
     setSize(500,380);
     setLocationRelativeTo(null);
     setVisible(true);
    
    }
    
   
    public static void main(String[] args) {
        logar app = new logar();
         app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
        
    }
    
}

    
    

