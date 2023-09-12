
package controle;

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

public class cliente extends JFrame{
    
       JLabel cod, nome ,data ,tel, email;
       JTextField tcod, tnome, tdata,trg, tcpf;
       JButton primeiro,sair,anterior,proximo,ultimo,novo,gravar,alterar,excluir,limpar,voltar;
       Conexao con_cliente;
       JTable tblclientes;//datagrid
       JScrollPane scp_tabela;//container para o datagrid
       
       public cliente(){
             con_cliente = new Conexao();
           con_cliente.conecta();
        
           Container tela = getContentPane();
           setTitle("Formulário com banco de dados");
           setLayout(null);
          
           nome = new JLabel("Nome:");
           data = new JLabel("Data:");
           tel = new JLabel("cpf:");
           email = new JLabel("rg:");
           cod = new JLabel("Código: ");
           primeiro = new JButton("Primeiro");
           anterior = new JButton("Anterior");
           proximo = new JButton("Próximo");
           ultimo = new JButton("Último");
           novo = new JButton("Novo Registro");
           gravar = new JButton("Gravar");
           alterar = new JButton("Alterar");
           excluir = new JButton("Excluir");
           limpar = new JButton("Limpar");
           sair = new JButton("Sair");
           voltar = new JButton("Voltar");
           tblclientes = new javax.swing.JTable();
           scp_tabela = new javax.swing.JScrollPane();
         
          
            
            
           
         
         
           tcod = new JTextField(5);
           tnome = new JTextField(100);
           tdata = new JTextField(10);
           trg = new JTextField(10);
           tcpf = new JTextField(35);
          
           
           

           
           tblclientes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255,175,184)));
           
           tblclientes.setFont(new java.awt.Font("Arial", 1, 12));
           
           voltar.addActionListener(new ActionListener(){
                 public void actionPerformed(ActionEvent e){
                form outraTela = new form();
                outraTela.setVisible(true);
                dispose(); // Fecha a tela atual
                 }
           });
           
           primeiro.addActionListener(new ActionListener(){
               public void actionPerformed(ActionEvent e){
                   try{
                        con_cliente.resultset.first();
                        mostrar_Dados();
                   }catch(SQLException erro){
                       JOptionPane.showMessageDialog(null, "Não foi possível acessar o primeiro registro");
                   }
               }
           });
           anterior.addActionListener(new ActionListener(){
               public void actionPerformed(ActionEvent e){
                   try{
                        con_cliente.resultset.previous();
                        mostrar_Dados();
                   }catch(SQLException erro){
                       JOptionPane.showMessageDialog(null, "Não foi possível acessar o registro anterior");
                   }
               }
           });
            proximo.addActionListener(new ActionListener(){
               public void actionPerformed(ActionEvent e){
                   try{
                        con_cliente.resultset.next();
                        mostrar_Dados();
                   }catch(SQLException erro){
                       JOptionPane.showMessageDialog(null, "Não foi possível acessar o próximo registro");
                   }
               }
           });
            ultimo.addActionListener(new ActionListener(){
               public void actionPerformed(ActionEvent e){
                   try{
                        con_cliente.resultset.last();
                        mostrar_Dados();
                   }catch(SQLException erro){
                       JOptionPane.showMessageDialog(null, "Não foi possível acessar o último registro");
                   }
               }
           });
            limpar.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                  tcod.setText("");
                  tnome.setText("");
                  tcpf.setText("");
                  tdata.setText("");
                  trg.setText("");
                  tcod.requestFocus();
                  
            }
            });
             novo.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                  tcod.setText("");
                  tnome.setText("");
                  tcpf.setText("");
                  tdata.setText("");
                  trg.setText("");
                  tcod.requestFocus();
                }
                 });
             gravar.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    String nome = tnome.getText();
                    String data = tdata.getText();
                    String rg = trg.getText();
                    String cpf = tcpf.getText();
                    
                      if (nome.isEmpty() || data.isEmpty() || rg.isEmpty() || cpf.isEmpty()) {
                           JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
                    try{
                        String insert_sql="insert into cliente(nome,cpf,rg,data_nasc) values('"+nome + "','" + cpf + "','" + rg + "','" + data + "')";
                        con_cliente.statement.executeUpdate(insert_sql);
                        JOptionPane.showMessageDialog(null,"Gravação realizada com sucesso!!","Mensagem do Programa",JOptionPane.INFORMATION_MESSAGE);
                        
                        con_cliente.executaSQL("SELECT * FROM cliente order by cod_cliente");
                        preencherTabela();
                    }catch(SQLException errosql){
                        JOptionPane.showMessageDialog(null,"\n Erro na gravação: \n"+errosql,"Mensagem do Programa",JOptionPane.INFORMATION_MESSAGE );
                    }
                }
             });
             
           alterar.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    String nome = tnome.getText();
                    String data = tdata.getText();
                    String rg = trg.getText();
                    String cpf = tcpf.getText();
                    String sql="";
                    String msg="";
                    
                    try{
                        if(tcod.getText().equals("")){
                         sql="insert into cliente (nome,rg,cpf,data_nasc)values('"+nome+"','"+rg+"','"+data+ "')";
                         msg="Gravação de um novo registro";
                        }else{
                            sql="update cliente set nome='" + nome + "',rg='" + rg + "', cpf='" + cpf + "', data_nasc='" + data + "' where cod_cliente = " + tcod.getText();
                            msg="Alteração de registro";
                        }
                        con_cliente.statement.executeUpdate(sql);
                        JOptionPane.showMessageDialog(null,"Gravação realizada com sucesso!!","Mensagem do Programa",JOptionPane.INFORMATION_MESSAGE);
                        
                        con_cliente.executaSQL("SELECT * FROM cliente order by cod_cliente");
                        preencherTabela();
                        
                        
                        }catch(SQLException errosql){
                            JOptionPane.showMessageDialog(null,"\n Erro na gravação: \n"+errosql,"Mensagem do Programa",JOptionPane.INFORMATION_MESSAGE );
                        }
                    }
                });
            excluir.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    String sql="";
                    
                    try{
                        int res = JOptionPane.showConfirmDialog(rootPane,"Deseja excluir o registro:","Confirmar Exclusão",JOptionPane.YES_NO_OPTION,3);
                        if(res==0){
                            sql="delete from cliente where cod_cliente = " +tcod.getText();
                            int excluir = con_cliente.statement.executeUpdate(sql);
                            if(excluir==1){
                                JOptionPane.showMessageDialog(null,"Exclusão realizada com sucesso!!","Mensagem do Programa",JOptionPane.INFORMATION_MESSAGE);
                                con_cliente.executaSQL("SELECT * FROM cliente order by cod_cliente");
                                con_cliente.resultset.first();
                                preencherTabela();
                                posicionarRegistro();
                            }
                            else{
                                JOptionPane.showMessageDialog(null,"Operação cancelada pelo usúario!!","Mensagem do Programa",JOptionPane.INFORMATION_MESSAGE);
                                
                            }
                            
                        }
                    }catch(SQLException excecao){
                        JOptionPane.showMessageDialog(null, "Erro na exclusão: "+excecao,"Mensagem do Programa",JOptionPane.INFORMATION_MESSAGE);
                    }  
                    }
                });
             
              sair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int status = JOptionPane.showConfirmDialog(null, "Deseja realmente fechar o programa?",
                        "Mensagem de saída", JOptionPane.YES_NO_OPTION);
                if (status == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
          
           tela.add(nome);
           tela.add(cod);
           tela.add(tblclientes);
           tela.add(scp_tabela);
           tela.add(data);
           tela.add(tel);
           tela.add(email);
           tela.add(tcod);
           tela.add(tnome);
           tela.add(tdata);
           tela.add(trg);
           tela.add(tcpf);
           tela.add(primeiro);
           tela.add(anterior);
           tela.add(proximo);
           tela.add(ultimo);
           tela.add(novo);
           tela.add(gravar);
           tela.add(alterar);
           tela.add(excluir);
           tela.add(sair);
           tela.add(limpar);
           tela.add(voltar);
        

          primeiro.setBackground(Color.white);
          anterior.setBackground(Color.white);
          proximo.setBackground(Color.white);
          ultimo.setBackground(Color.white);
          novo.setBackground(Color.white);
          gravar.setBackground(Color.white);
          alterar.setBackground(Color.white);
          excluir.setBackground(Color.white);
          sair.setBackground(Color.white);
          limpar.setBackground(Color.white);
          voltar.setBackground(Color.white);
          
          
          
           
           tblclientes.setModel(new javax.swing.table.DefaultTableModel(
           new Object [] [] {
               {null, null, null, null, null},
               {null, null, null, null, null},
               {null, null, null, null, null},
               {null, null, null, null, null},
           },
             new String [] {"Código","Nome", "Data Nascimento", "rg","cpf"})
           {
               boolean[] canEdit = new boolean []{
                   false, false, false, false, false};
               
               public boolean isCellEditable(int rowIndex, int columnIndex){
                   return canEdit [columnIndex];}
           
               
               });
           scp_tabela.setViewportView(tblclientes);
             
           tblclientes.setAutoCreateRowSorter(true);// ativa a classifica ordena a tabela
           
           con_cliente.executaSQL("SELECT * FROM cliente order by cod_cliente");
           preencherTabela();
           posicionarRegistro();
       
              }
           
           public void posicionarRegistro(){
               try{
                   con_cliente.resultset.first();
                   mostrar_Dados();
               }catch(SQLException erro){
                   JOptionPane.showMessageDialog(null,erro+"Não foi possível posicionar no primeiro registro: ","Mensagem do Programa",JOptionPane.INFORMATION_MESSAGE);
               
              }
           }

               public void mostrar_Dados(){
                   
                   try{
                       tcod.setText(con_cliente.resultset.getString("cod_cliente"));
                       tnome.setText(con_cliente.resultset.getString("nome"));
                       tdata.setText(con_cliente.resultset.getString("data_nasc"));
                       trg.setText(con_cliente.resultset.getString("rg"));
                       tcpf.setText(con_cliente.resultset.getString("cpf"));
                   }catch(SQLException erro){
                       JOptionPane.showMessageDialog(null,"Não localizou dados: "+erro,"Mensagem do Programa",JOptionPane.INFORMATION_MESSAGE);
                       

                       
                   }
               
           }
              
    
       public void preencherTabela() 
       {
           tblclientes.getColumnModel().getColumn(0).setPreferredWidth(5);
           tblclientes.getColumnModel().getColumn(1).setPreferredWidth(50);
           tblclientes.getColumnModel().getColumn(2).setPreferredWidth(8);
           tblclientes.getColumnModel().getColumn(3).setPreferredWidth(20);
           tblclientes.getColumnModel().getColumn(4).setPreferredWidth(15);
           
           DefaultTableModel modelo=(DefaultTableModel) tblclientes.getModel();
           modelo.setNumRows(0);
           
             try {
                con_cliente.resultset.beforeFirst();
                while(con_cliente.resultset.next()) {
                    modelo.addRow(new Object[] {
                        con_cliente.resultset.getString("cod_cliente"),con_cliente.resultset.getString("nome"),con_cliente.resultset.getString("data_nasc"),con_cliente.resultset.getString("rg"), con_cliente.resultset.getString("cpf")
                    });
                }
            }catch(SQLException erro) {
                JOptionPane.showMessageDialog(null,"\n Erro ao listar dados da tabela!! :\n "+erro,"Mensagem do Programa",JOptionPane.INFORMATION_MESSAGE);
            }
             
             
        
           setSize(1100,700);
           setLocationRelativeTo(null);
           setVisible(true);
       
             
            
     
           nome.setBounds(20,60,50,20);
           data.setBounds(20,90,50,20);
           tel.setBounds(20,120,120,20);
           email.setBounds(20,150,50,20);
           tcod.setBounds(80,30,50,20);
           tnome.setBounds(80,60,150,20);
           voltar.setBounds(420,230,100,20);
           tdata.setBounds(80,90,100,20);
           trg.setBounds(80,120,150,20);
           tcpf.setBounds(80,150,150,20);
           primeiro.setBounds(20,230,100,20);
           anterior.setBounds(120,230,100,20);
           proximo.setBounds(220,230,100,20);
           ultimo.setBounds(320,230,100,20);
           novo.setBounds(520,230,150,20);
           gravar.setBounds(660,230,100,20);
           alterar.setBounds(750,230,100,20);
           excluir.setBounds(850,230,100,20);
           limpar.setBounds(950,230,100,20);
           sair.setBounds(900,800,100,20);
           tblclientes.setBounds(150,300,750,200);
           scp_tabela.setBounds(150,300,750,200);
           cod.setBounds(20,30,50,20);

          
       
       
       
}
     
}
     
    
            
      
   
       

 
       
    
    



    

