
package controle;
import conexao.Conexao;
import java.sql.SQLException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.*;
import conexao.Conexao;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.sql.*;
 
public class form extends JFrame {
     JLabel nome,imagem;
     JButton filme,sala,cli;
     Conexao con_cliente;
     
public form(){
      con_cliente = new Conexao(); 
      con_cliente.conecta();
      setTitle("Opçôes");
      Container tela = getContentPane();
      setLayout(null);
      nome = new JLabel("O que deseja?");
      filme = new JButton("Filme");
      sala = new JButton("Sala");
      cli = new JButton("Cliente");
      ImageIcon icone = new ImageIcon("src/pipoca.png");
     imagem = new JLabel(icone);
      
      
      filme.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            
                    filme mostra = new filme();
                    mostra.setVisible(true);
                    dispose(); 
                    
        }
            
        });
      
       sala.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            
                    sala mostra = new sala();
                    mostra.setVisible(true);
                    dispose(); 
        }
                    
        });
        
           cli.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            
                    cliente mostra = new cliente();
                    mostra.setVisible(true);
                    dispose(); 
        }
                    
        });
           
      
    filme.setBounds(20,40,100,20);
    sala.setBounds(120,40,100,20);
    cli.setBounds(220,40,100,20);
    nome.setBounds(10,20,100,20);
    imagem.setBounds(0,0,770,544);
    filme.setBackground(Color.white);
    sala.setBackground(Color.white);
    cli.setBackground(Color.white);
    
    tela.add(nome);
    tela.add(sala);
    tela.add(cli);
    tela.add(filme);
    tela.add(imagem);
    setSize(500,380);
    setLocationRelativeTo(null);
    setVisible(true);
    
    }
}   


    

     
      
    

   
    
    
        

     