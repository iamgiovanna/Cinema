package controle;

import conexao.Conexao;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

public class sala extends JFrame {

    Conexao con_cliente;
    JTable tblclientes;
    JScrollPane scp_tabela;
    JButton sair, voltar;
   

    public sala() {
        con_cliente = new Conexao();
        con_cliente.conecta();

        Container tela = getContentPane();
        setTitle("Sala");
        sair = new JButton("Sair");
        voltar = new JButton("Voltar");
        setLayout(new BorderLayout()); // Use BorderLayout para organizar os componentes

        tblclientes = new JTable();
        tblclientes.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {"N_sala", "cod_Sala", "Desc_sala", "Localização"}
        ));
        
        scp_tabela = new JScrollPane(tblclientes);

        // Adicione o JScrollPane ao centro da janela
        add(scp_tabela, BorderLayout.CENTER);

        preencherTabela();
        
        sair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int status = JOptionPane.showConfirmDialog(null, "Deseja realmente fechar o programa?",
                        "Mensagem de saída", JOptionPane.YES_NO_OPTION);
                if (status == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        
        voltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                form outraTela = new form();
                outraTela.setVisible(true);
                dispose(); // Fecha a tela atual
            }
        });
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(sair);
        buttonPanel.add(voltar);
        voltar.setBackground(Color.white);
        sair.setBackground(Color.white);

        // Adicione o painel de botões à parte inferior da janela
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(1100, 700);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void preencherTabela() {
        DefaultTableModel modelo = (DefaultTableModel) tblclientes.getModel();
        modelo.setRowCount(0);

        try {
            con_cliente.executaSQL("SELECT * FROM sala order by cod_Sala");
            while (con_cliente.resultset.next()) {
                modelo.addRow(new Object[]{
                    con_cliente.resultset.getString("N_sala"),
                    con_cliente.resultset.getString("cod_Sala"),
                    con_cliente.resultset.getString("Desc_sala"),
                    con_cliente.resultset.getString("Localizacao")
                    
                });
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar dados da tabela: " + erro, "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}
