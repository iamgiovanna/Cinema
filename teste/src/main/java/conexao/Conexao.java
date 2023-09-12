/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexao;
import javax.swing.JOptionPane;
 import java.sql.*;// para execução de comandos SQL no ambiente Java

public class Conexao {
    final private String driver = "com.mysql.cj.jdbc.Driver"; //definição do driver mysql para o acesso aos dados
    final private String url = "jdbc:mysql://localhost/cinema";//acesso ao bd "cinema" no servidor (myAdmin)
    final private String usuario ="root";//usuario do mysql
    final private String senha ="";//senha do mysql
    private Connection conexao; //variavel que armazenara a conexao aberta 
    public Statement statement;// variavel para execucao dos comandos SQL dentro do ambiente java
    public ResultSet resultset;//variavel que armazenara o resultado da execucao de um comando sql
    
    
  
    public boolean conecta(){
         boolean result = true;
         try{
             Class.forName(driver);
             conexao = DriverManager.getConnection(url,usuario,senha);
             JOptionPane.showMessageDialog(null, "Conexão estabelecida", "Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);
         }catch (ClassNotFoundException Driver){
             JOptionPane.showMessageDialog(null,"Driver não localizado"+Driver,"mensagem do programa",JOptionPane.INFORMATION_MESSAGE);
            result = false;
         }catch (SQLException Fonte){
             JOptionPane.showMessageDialog(null,"Fonte de dados não localizada!"+Fonte,"Mensagem do Programa",JOptionPane.INFORMATION_MESSAGE);
             result = false;
         }
         return result;
    }
    

    public void desconecta() {
           try{
             conexao.close();
             JOptionPane.showMessageDialog(null,"Conexão com o banco fechada","Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);
           }catch(SQLException fecha){
             JOptionPane.showMessageDialog(null, "Erro ao fechar o banco","Mensagem do Programa",JOptionPane.INFORMATION_MESSAGE);
             
    }
}
    public void executaSQL(String sql){
        try{
            statement = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            resultset = statement.executeQuery(sql);
          }catch(SQLException excecao){
            JOptionPane.showMessageDialog(null, "Erro no comando SQL! \n Erro:"+excecao,"Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);
    
        }
    }
}
