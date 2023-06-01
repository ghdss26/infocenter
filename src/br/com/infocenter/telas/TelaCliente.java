/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package br.com.infocenter.telas;

import java.sql.*; 
import br.com.infocenter.dao.ModuloConexao; 
import javax.swing.JOptionPane;

// a linha abaixo importa recursos da biblioteca sqlite 
import net.proteanit.sql.DbUtils;

/**
 *
 * @author gustavo
 */
public class TelaCliente extends javax.swing.JInternalFrame {

    Connection conexao = null; 
    PreparedStatement pst = null; 
    ResultSet rs = null; 
    
    public TelaCliente() {
        initComponents();
        conexao = ModuloConexao.conector(); 
    }
    
     private void adicionar() {
         
         String sql = "INSERT INTO clientes(nomecli, endcli, fonecli, emailcli) VALUES(?, ?, ?, ?)"; 
         
         try {
             
             pst = conexao.prepareStatement(sql);
             pst.setString(1, txtCliNome.getText()); 
             pst.setString(2, txtCliEndereco.getText()); 
             pst.setString(3, txtCliTelefone.getText()); 
             pst.setString(4, txtCliEmail.getText()); 
             
           
             // Validação dos campos 
             if ((txtCliNome.getText().isEmpty()) 
                     || (txtCliEndereco.getText().isEmpty()) 
                     || (txtCliTelefone.getText().isEmpty()) 
                     || txtCliEmail.getText().isEmpty())   {
                 
                 JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
                 
             } else {
                      
             int adicionado = pst.executeUpdate(); 
             
             System.out.println(adicionado);
             
                if (adicionado > 0) {

                   JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso");

                   txtCliNome.setText(null); 
                   txtCliEndereco.setText(null); 
                   txtCliTelefone.setText(null); 
                   txtCliEmail.setText(null); 
                   
                }
             }
             
         } catch (Exception e) {
             
             JOptionPane.showMessageDialog(null, e);
         }
     }
     
     private void pesquisar_cliente() {
         
         String sql = "SELECT * FROM clientes WHERE nomecli like ?";
         
         try {
             
             pst = conexao.prepareStatement(sql);
             
             // passando o conteúdo da caixa de pesquisar 
             pst.setString(1, txtCliPesquisar.getText() + "%");
             rs = pst.executeQuery(); 
             
             // rs2xml.jar 
             tblClientes.setModel(DbUtils.resultSetToTableModel(rs));
             
         } catch (Exception e) {
             
             JOptionPane.showMessageDialog(null, e);
         }
     }
     
     private void alterar() {
        
        String sql = "UPDATE clientes SET  nomecli=?, endcli=?, fonecli=?, emailcli=? WHERE idcli=?"; 
        
        try {
            
            pst = conexao.prepareStatement(sql); 
            pst.setString(1, txtCliNome.getText()); 
            pst.setString(2, txtCliEndereco.getText()); 
            pst.setString(3, txtCliTelefone.getText()); 
            pst.setString(4, txtCliEmail.getText());
            pst.setString(5, txtCliId.getText()); 
          
            // Validação dos campos 
             if ((txtCliNome.getText().isEmpty()) || (txtCliTelefone.getText().isEmpty()))  {
                 
                 JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
                 
             } else {
                      
             int adicionado = pst.executeUpdate(); 
             
             System.out.println(adicionado);
             
                if (adicionado > 0) {

                   JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso");

                   txtCliNome.setText(null); 
                   txtCliEndereco.setText(null); 
                   txtCliTelefone.setText(null); 
                   txtCliEmail.setText(null); 
                   
                }
             }
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
     
     public void setar_campos_clientes() {
         
         int setar = tblClientes.getSelectedRow(); 
         txtCliId.setText(tblClientes.getModel().getValueAt(setar, 0).toString());
         txtCliNome.setText(tblClientes.getModel().getValueAt(setar, 1).toString()); 
         txtCliEndereco.setText(tblClientes.getModel().getValueAt(setar, 2).toString());
         txtCliTelefone.setText(tblClientes.getModel().getValueAt(setar, 3).toString());  
         txtCliEmail.setText(tblClientes.getModel().getValueAt(setar, 4).toString()); 
         
     }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCliCreate = new javax.swing.JButton();
        btnCliAtualizar = new javax.swing.JButton();
        btnCliExcluir = new javax.swing.JButton();
        txtCliPesquisar = new javax.swing.JTextField();
        lblCampo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        lblNome = new javax.swing.JLabel();
        txtCliNome = new javax.swing.JTextField();
        lblEndereco = new javax.swing.JLabel();
        txtCliEndereco = new javax.swing.JTextField();
        lblTelefone = new javax.swing.JLabel();
        txtCliTelefone = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        txtCliEmail = new javax.swing.JTextField();
        btnUsuPesquisar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtCliId = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Clientes");
        setPreferredSize(new java.awt.Dimension(658, 500));

        btnCliCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infocenter/icones/create.png"))); // NOI18N
        btnCliCreate.setToolTipText("Adicionar");
        btnCliCreate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCliCreate.setPreferredSize(new java.awt.Dimension(128, 128));
        btnCliCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliCreateActionPerformed(evt);
            }
        });

        btnCliAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infocenter/icones/update.png"))); // NOI18N
        btnCliAtualizar.setToolTipText("Atualizar");
        btnCliAtualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCliAtualizar.setPreferredSize(new java.awt.Dimension(128, 128));
        btnCliAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliAtualizarActionPerformed(evt);
            }
        });

        btnCliExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infocenter/icones/delete.png"))); // NOI18N
        btnCliExcluir.setToolTipText("Apagar");
        btnCliExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCliExcluir.setPreferredSize(new java.awt.Dimension(128, 128));
        btnCliExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliExcluirActionPerformed(evt);
            }
        });

        txtCliPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCliPesquisarKeyReleased(evt);
            }
        });

        lblCampo.setText("* Campo Obrigatórios");

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblClientes);

        lblNome.setText("* Nome");

        txtCliNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCliNomeActionPerformed(evt);
            }
        });

        lblEndereco.setText("* Endereço");

        lblTelefone.setText("* Telefone");

        lblEmail.setText("* Email");

        txtCliEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCliEmailActionPerformed(evt);
            }
        });

        btnUsuPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infocenter/icones/search.png"))); // NOI18N
        btnUsuPesquisar.setToolTipText("Pesquisar");
        btnUsuPesquisar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuPesquisar.setPreferredSize(new java.awt.Dimension(128, 128));
        btnUsuPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuPesquisarActionPerformed(evt);
            }
        });

        jLabel1.setText("Id Cliente");

        txtCliId.setEnabled(false);
        txtCliId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCliIdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(28, 59, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(txtCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(55, 55, 55)
                                        .addComponent(btnUsuPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(36, 36, 36)
                                        .addComponent(lblCampo)))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtCliId, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(lblNome)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtCliNome, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(lblTelefone)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtCliTelefone)))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(31, 31, 31)
                                            .addComponent(lblEmail))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(33, 33, 33)
                                            .addComponent(lblEndereco)))))
                            .addGap(2, 2, 2))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(btnCliCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(85, 85, 85)
                            .addComponent(btnCliAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(88, 88, 88)
                            .addComponent(btnCliExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(385, 385, 385)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCliEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCliEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(31, 31, 31))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(txtCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(lblCampo)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnUsuPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCliId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNome)
                    .addComponent(txtCliNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEmail)
                    .addComponent(txtCliEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelefone)
                    .addComponent(txtCliTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEndereco)
                    .addComponent(txtCliEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCliAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCliExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnCliCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        setBounds(0, 0, 708, 458);
    }// </editor-fold>//GEN-END:initComponents

    private void txtCliNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCliNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCliNomeActionPerformed

    private void btnCliExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliExcluirActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_btnCliExcluirActionPerformed

    private void btnCliAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliAtualizarActionPerformed
        // TODO add your handling code here:
            alterar();
    }//GEN-LAST:event_btnCliAtualizarActionPerformed

    private void btnCliCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliCreateActionPerformed
        // TODO add your handling code here:
            adicionar();
    }//GEN-LAST:event_btnCliCreateActionPerformed

    private void btnUsuPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuPesquisarActionPerformed
        // Chamando o método consultar
        
    }//GEN-LAST:event_btnUsuPesquisarActionPerformed

    private void txtCliPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCliPesquisarKeyReleased
        // euqnato for digitando 
        pesquisar_cliente();
    }//GEN-LAST:event_txtCliPesquisarKeyReleased

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        // TODO add your handling code here:
        
        setar_campos_clientes();
    }//GEN-LAST:event_tblClientesMouseClicked

    private void txtCliIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCliIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCliIdActionPerformed

    private void txtCliEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCliEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCliEmailActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCliAtualizar;
    private javax.swing.JButton btnCliCreate;
    private javax.swing.JButton btnCliExcluir;
    private javax.swing.JButton btnUsuPesquisar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCampo;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEndereco;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblTelefone;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtCliEmail;
    private javax.swing.JTextField txtCliEndereco;
    private javax.swing.JTextField txtCliId;
    private javax.swing.JTextField txtCliNome;
    private javax.swing.JTextField txtCliPesquisar;
    private javax.swing.JTextField txtCliTelefone;
    // End of variables declaration//GEN-END:variables
}
