/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package br.com.infocenter.telas;

import java.sql.*; 
import br.com.infocenter.dao.ModuloConexao;
import javax.swing.JOptionPane;

/**
 *
 * @author gustavo
 */
public class TelaUsuario extends javax.swing.JInternalFrame {
    
    Connection conexao = null;
    PreparedStatement pst = null; 
    ResultSet rs = null; 
    
    public TelaUsuario() {
        
        initComponents();
        conexao = ModuloConexao.conector(); 
        
    }
    
    private void consultar() {
        
        String sql = "SELECT * FROM usuarios WHERE iduser = ?";
        
        try {
            
            pst = conexao.prepareStatement(sql); 
            pst.setString(1, txtUsuId.getText()); 
            
            
            rs = pst.executeQuery();
            
            if (rs.next()) {
                
                txtUsuNome.setText(rs.getNString(2));
                txtUsuFone.setText(rs.getString(3));
                txtUsuLogin.setText(rs.getString(4)); 
                txtUsuSenha.setText(rs.getString(5)); 
                cboUsuPerfil.setSelectedItem(rs.getString(6));
                
            } else {
                
                // Limpa os campos do formulário
                JOptionPane.showMessageDialog(null, "Usuário não cadastrado"); 
                txtUsuId.setText(null);
                txtUsuNome.setText(null); 
                txtUsuFone.setText(null); 
                txtUsuLogin.setText(null); 
                txtUsuSenha.setText(null); 
               
            }
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, e); 
            txtUsuNome.setText(null); 
            txtUsuFone.setText(null); 
            txtUsuLogin.setText(null); 
            txtUsuSenha.setText(null); 
            cboUsuPerfil.setSelectedItem(null);
        }
    }
    
    private void adicionar() {
         
         String sql = "INSERT INTO usuarios(iduser, usuario, fone, login, senha, perfil) VALUES(?, ?, ?, ?, ?, ?)"; 
         
         try {
             
             pst = conexao.prepareStatement(sql);
             pst.setString(1, txtUsuId.getText()); 
             pst.setString(2, txtUsuNome.getText()); 
             pst.setString(3, txtUsuFone.getText()); 
             pst.setString(4, txtUsuLogin.getText()); 
             pst.setString(5, txtUsuSenha.getText()); 
             pst.setString(6, cboUsuPerfil.getSelectedItem().toString()); 
             
             // Validação dos campos 
             if (((((txtUsuId.getText().isEmpty()) 
                     || (txtUsuNome.getText().isEmpty())) 
                     || (txtUsuFone.getText().isEmpty())) 
                     || (txtUsuLogin.getText().isEmpty())) 
                     || (txtUsuSenha.getText().isEmpty()))  {
                 
                 JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
                 
             } else {
                      
             int adicionado = pst.executeUpdate(); 
             
             System.out.println(adicionado);
             
                if (adicionado > 0) {

                   JOptionPane.showMessageDialog(null, "Usuário adicionado com sucesso");

                   limpar();
                   
                }
             }
             
         } catch (Exception e) {
             
             JOptionPane.showMessageDialog(null, e);
         }
     }
    
    private void limpar() {
        
        txtUsuNome.setText(null); 
        txtUsuFone.setText(null); 
        txtUsuLogin.setText(null); 
        txtUsuSenha.setText(null); 
    }
    
    private void alterar() {
        
        String sql = "UPDATE usuarios SET  usuario=?, fone=?, login=?, senha=?, perfil=? WHERE iduser=?"; 
        
        try {
            
            pst = conexao.prepareStatement(sql); 
            pst.setString(1, txtUsuNome.getText()); 
            pst.setString(2, txtUsuFone.getText()); 
            pst.setString(3, txtUsuLogin.getText()); 
            pst.setString(4, txtUsuSenha.getText()); 
            pst.setString(5, cboUsuPerfil.getSelectedItem().toString()); 
            pst.setString(6, txtUsuId.getText());
            
            // Validação dos campos 
             if (((((txtUsuId.getText().isEmpty()) 
                     || (txtUsuNome.getText().isEmpty())) 
                     || (txtUsuFone.getText().isEmpty())) 
                     || (txtUsuLogin.getText().isEmpty())) 
                     || (txtUsuSenha.getText().isEmpty()))  {
                 
                 JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
                 
             } else {
                      
             int adicionado = pst.executeUpdate(); 
             
             System.out.println(adicionado);
             
                if (adicionado > 0) {

                   JOptionPane.showMessageDialog(null, "Usuário atualizado com sucesso");

                   limpar();
                }
             }
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void remover() {
        
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este usuário", "Atenção", JOptionPane.YES_NO_OPTION);
        
        if (confirma == JOptionPane.YES_OPTION) {
            
            String sql = "DELETE FROM usuarios WHERE iduser=?"; 
            
            try {
                
                pst = conexao.prepareStatement(sql); 
                pst.setString(1, txtUsuId.getText()); 
                
                int apagado = pst.executeUpdate(); 
                
                if(apagado > 0) {
                    
                    JOptionPane.showMessageDialog(null, "Usuário removido com sucesso"); 
                    
                    limpar();
                    
                } 
                
            } catch (Exception e) {
                
                JOptionPane.showMessageDialog(this, e); 
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jToggleButton1 = new javax.swing.JToggleButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtUsuId = new javax.swing.JTextField();
        txtUsuNome = new javax.swing.JTextField();
        txtUsuFone = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtUsuLogin = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtUsuSenha = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        cboUsuPerfil = new javax.swing.JComboBox<>();
        btnUsuCreate = new javax.swing.JButton();
        btnUsuPesquisar = new javax.swing.JButton();
        btnUsuExcluir = new javax.swing.JButton();
        btnUsuAtualizar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jToggleButton1.setText("jToggleButton1");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Usuários");
        setPreferredSize(new java.awt.Dimension(658, 500));

        jLabel1.setText("Id");

        jLabel2.setText("* Nome");

        jLabel4.setText("* Fone");

        jLabel5.setText("* Login");

        jLabel9.setText("* Senha");

        jLabel10.setText("* Perfil");

        cboUsuPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "admin", "user" }));

        btnUsuCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infocenter/icones/create.png"))); // NOI18N
        btnUsuCreate.setToolTipText("Adicionar");
        btnUsuCreate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuCreate.setPreferredSize(new java.awt.Dimension(128, 128));
        btnUsuCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuCreateActionPerformed(evt);
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

        btnUsuExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infocenter/icones/delete.png"))); // NOI18N
        btnUsuExcluir.setToolTipText("Apagar");
        btnUsuExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuExcluir.setPreferredSize(new java.awt.Dimension(128, 128));
        btnUsuExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuExcluirActionPerformed(evt);
            }
        });

        btnUsuAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infocenter/icones/update.png"))); // NOI18N
        btnUsuAtualizar.setToolTipText("Atualizar");
        btnUsuAtualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuAtualizar.setPreferredSize(new java.awt.Dimension(128, 128));
        btnUsuAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuAtualizarActionPerformed(evt);
            }
        });

        jLabel7.setText("* Campos Obrigatórios");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(txtUsuSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(txtUsuFone, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cboUsuPerfil, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtUsuLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtUsuNome, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(60, 77, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(181, 181, 181)
                        .addComponent(btnUsuCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUsuPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUsuAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUsuExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(jLabel1)
                        .addGap(21, 21, 21)
                        .addComponent(txtUsuId, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(111, 111, 111)
                        .addComponent(jLabel7)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(216, 216, 216)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtUsuId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtUsuNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(txtUsuFone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtUsuLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5)))
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtUsuSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(cboUsuPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnUsuCreate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUsuPesquisar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUsuExcluir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUsuAtualizar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        setBounds(0, 0, 678, 429);
    }// </editor-fold>//GEN-END:initComponents

    private void btnUsuPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuPesquisarActionPerformed
        // Chamando o método consultar 
        consultar(); 
    }//GEN-LAST:event_btnUsuPesquisarActionPerformed

    private void btnUsuCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuCreateActionPerformed
        // TODO add your handling code here:
        adicionar(); 
    }//GEN-LAST:event_btnUsuCreateActionPerformed

    private void btnUsuAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuAtualizarActionPerformed
        // TODO add your handling code here:
        alterar();
    }//GEN-LAST:event_btnUsuAtualizarActionPerformed

    private void btnUsuExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuExcluirActionPerformed
        // TODO add your handling code here:
        remover();
    }//GEN-LAST:event_btnUsuExcluirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUsuAtualizar;
    private javax.swing.JButton btnUsuCreate;
    private javax.swing.JButton btnUsuExcluir;
    private javax.swing.JButton btnUsuPesquisar;
    private javax.swing.JComboBox<String> cboUsuPerfil;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JTextField txtUsuFone;
    private javax.swing.JTextField txtUsuId;
    private javax.swing.JTextField txtUsuLogin;
    private javax.swing.JTextField txtUsuNome;
    private javax.swing.JTextField txtUsuSenha;
    // End of variables declaration//GEN-END:variables
}
