/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package br.com.infocenter.telas;

import java.sql.*; 
import br.com.infocenter.dao.ModuloConexao; 
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils; 
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author gustavo
 */
public class TelaOs extends javax.swing.JInternalFrame {
    
    Connection conexao = null; 
    PreparedStatement pst = null; 
    
    ResultSet rs = null; 
    
    // variavel para armazenar o tipo no banco
    private String tipo;

    /**
     * Creates new form TelaOs
     */
    public TelaOs() {
        
        initComponents();
        conexao = ModuloConexao.conector();
    }
    
    private void pesquisar_cliente() {
        
        String sql = "SELECT idcli AS Id, nomecli AS Nome, fonecli AS Fone FROM clientes WHERE nomecli like ?";
        
        try {
            
            pst = conexao.prepareStatement(sql); 
            pst.setString(1, txtCliPesquisar.getText() + "%");
            
            rs = pst.executeQuery(); 
            
            tblClientes.setModel(DbUtils.resultSetToTableModel(rs)); 
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void setar_campos() {
        
        int setar = tblClientes.getSelectedRow(); 
        txtCliId.setText(tblClientes.getModel().getValueAt(setar, 0).toString());
    }
    
    private void emitir_os() {
        
        String sql = "INSERT INTO os(tipo, situacao, equipamento, defeito, servico, tecnico, valor, idcli) VALUES(?, ?, ?, ?, ?, ?, ?, ?)"; 
        
        try {
            
            pst = conexao.prepareStatement(sql); 
            pst.setString(1, tipo);
            pst.setString(2, cboOsSit.getSelectedItem().toString()); 
            pst.setString(3, txtOsEquip.getText()); 
            pst.setString(4, txtOsDef.getText()); 
            pst.setString(5, txtOsServ.getText()); 
            pst.setString(6, txtOsTec.getText()); 
            
            // .replace substitui a vírgula pelo ponto
            pst.setString(7, txtOsValor.getText().replace(",", "."));
            pst.setString(8, txtCliId.getText()); 
            
            // validação dos campos
            if (txtCliId.getText().isEmpty() 
                    || (txtOsEquip.getText().isEmpty() 
                    || (txtOsDef.getText().isEmpty() 
                    || (txtOsServ.getText().isEmpty() 
                    || (txtOsTec.getText().isEmpty() 
                    || (txtOsValor.getText().isEmpty() 
                    || (cboOsSit.getSelectedItem().equals(" "))))))))  {
                
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
                
            } else {
                
                int adicionado = pst.executeUpdate(); 
                
                if(adicionado > 0) {
                    
                   JOptionPane.showMessageDialog(null, "Ordem de Serviço emitido com sucesso"); 
                   
                   recuperarOs();
                    
                   btnOsAdd.setEnabled(false);  
                   btnOsPesquisar.setEnabled(false); 
                   btnOsImprimir.setEnabled(true);
                    
                }
            }
             
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void pesquisar_os() {
        
        String num_os = JOptionPane.showInputDialog("Número da OS"); 
        String sql = "SELECT os, date_format(dataos, '%d/%m/%Y - %H:%i'), tipo, "
                + "situacao, equipamento, defeito, servico, tecnico, valor, idcli FROM os WHERE os= " + num_os; 
        
        try {
            
            pst = conexao.prepareStatement(sql); 
            rs = pst.executeQuery(); 
            
            if (rs.next()) {
                
                txtOs.setText(rs.getString(1));
                txtData.setText(rs.getString(2)); 
                
                String rbtTipo = rs.getString(3); 
                
                if (rbtTipo.equals("Ordem de Serviço")) {
                    
                    rbtOs.setSelected(true); 
                    tipo = "Ordem de Serviço"; 
                    
                } else {
                    
                    rbtOrc.setSelected(true); 
                    tipo="Orçamento"; 
                    
                }
                
                cboOsSit.setSelectedItem(rs.getString(4)); 
                txtOsEquip.setText(rs.getString(5)); 
                txtOsDef.setText(rs.getString(6)); 
                txtOsServ.setText(rs.getString(7)); 
                txtOsTec.setText(rs.getString(8)); 
                txtOsValor.setText(rs.getString(9)); 
                txtCliId.setText(rs.getString(10));
                
                btnOsAdd.setEnabled(false);
                btnOsPesquisar.setEnabled(false);
                txtCliPesquisar.setEnabled(false); 
                tblClientes.setVisible(false); 
                
                btnOsAtualizar.setEnabled(true); 
                btnOsExcluir.setEnabled(true); 
                btnOsImprimir.setEnabled(true); 
                
            } else {
                
                JOptionPane.showMessageDialog(null, "Os não cadastrada"); 
                
            }
        } catch (SQLSyntaxErrorException e) {
            
            JOptionPane.showMessageDialog(null, "OS Inválida");
            System.out.println(e); 
            
        } catch (Exception e2) {
            
            JOptionPane.showMessageDialog(null, e2);
        }
    }
    
    private void alterar_os() {
        
        String sql = "UPDATE os SET tipo = ?, situacao = ?, equipamento = ?, defeito = ?, "
                + "servico = ?, tecnico = ?, valor = ? WHERE os = ?";
        
         try {
            
            pst = conexao.prepareStatement(sql); 
            pst.setString(1, tipo);
            pst.setString(2, cboOsSit.getSelectedItem().toString()); 
            pst.setString(3, txtOsEquip.getText()); 
            pst.setString(4, txtOsDef.getText()); 
            pst.setString(5, txtOsServ.getText()); 
            pst.setString(6, txtOsTec.getText()); 
            
            // .replace substitui a vírgula pelo ponto
            pst.setString(7, txtOsValor.getText().replace(",", "."));
            pst.setString(8, txtOs.getText()); 
            
            // validação dos campos
            if (txtCliId.getText().isEmpty() 
                    || (txtOsEquip.getText().isEmpty() 
                    || (txtOsDef.getText().isEmpty() 
                    || (txtOsServ.getText().isEmpty() 
                    || (txtOsTec.getText().isEmpty() 
                    || (txtOsValor.getText().isEmpty() 
                    || (cboOsSit.getSelectedItem().equals(" "))))))))  {
                
                
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
                
            } else {
                
                int adicionado = pst.executeUpdate(); 
                
                if(adicionado > 0) {
                    
                    JOptionPane.showMessageDialog(null, "Ordem de Serviço alterado com sucesso"); 
                    
                    limpar();
                }
            }
             
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, e); 
            
         
        }
    }
    
    private void excluir_os() {
        
        int confirma = JOptionPane.showConfirmDialog(null, "Tem Certeza que deseja excluir esta OS ", "Atenção", JOptionPane.YES_NO_OPTION);
        
        if(confirma == JOptionPane.YES_OPTION) {
            
            String sql = "DELETE FROM os WHERE os = ?"; 
            
            try {
                
                pst = conexao.prepareStatement(sql); 
                pst.setString(1, txtOs.getText()); 
                
                int apagado = pst.executeUpdate(); 
                
                if (apagado > 0) {
                    
                    JOptionPane.showConfirmDialog(null, "OS excluída com sucesso"); 
                    
                   limpar();
                }
                
            } catch (Exception e) {
                
                JOptionPane.showMessageDialog(null, e); 
                
                 
            }
        }
    }
    
    private void imprimir_os() {
        
        
        int confirma = JOptionPane.showConfirmDialog(null, "Confirma a impressão desta Ordem de Serviço ?", "Atenção", JOptionPane.YES_NO_OPTION);
        
        if (confirma == JOptionPane.YES_OPTION) {
            
            try {
                
                HashMap filtro = new HashMap(); 
                
                filtro.put("os", Integer.parseInt(txtOs.getText())); 
                
                JasperPrint print = JasperFillManager.fillReport(getClass().getResourceAsStream("/reports/os.jasper"), filtro, conexao);
                
                JasperViewer.viewReport(print, false);
                
            } catch (Exception e) {
                
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    
    private void recuperarOs() {
        
        String sql = "SELECT MAX(OS) FROM os"; 
        
        try {
            
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery(); 
            
            if(rs.next()) {
                
                txtOs.setText(rs.getString(1));
            }
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void limpar() {
        
            txtOs.setText(null); 
            txtData.setText(null);
            txtCliPesquisar.setText(null);
            ((DefaultTableModel) tblClientes.getModel()).setRowCount(0); 
            cboOsSit.setSelectedItem(" ");
            txtCliId.setText(null); 
            txtOsEquip.setText(null); 
            txtOsDef.setText(null); 
            txtOsServ.setText(null);
            txtOsTec.setText(null); 
            txtOsValor.setText(null); 

            btnOsAdd.setEnabled(true); 
            txtCliPesquisar.setEnabled(true);
            tblClientes.setVisible(true); 
            
            btnOsAtualizar.setEnabled(false); 
            btnOsExcluir.setEnabled(false); 
            btnOsImprimir.setEnabled(false);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtOs = new javax.swing.JTextField();
        txtData = new javax.swing.JTextField();
        rbtOrc = new javax.swing.JRadioButton();
        rbtOs = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        cboOsSit = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        txtCliPesquisar = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCliId = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtOsEquip = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtOsDef = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtOsServ = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtOsTec = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtOsValor = new javax.swing.JTextField();
        btnOsAtualizar = new javax.swing.JButton();
        btnOsAdd = new javax.swing.JButton();
        btnOsPesquisar = new javax.swing.JButton();
        btnOsImprimir = new javax.swing.JButton();
        btnOsExcluir = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Ordem de Serviço");
        setPreferredSize(new java.awt.Dimension(640, 480));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Número Os");

        jLabel2.setText("Data");

        txtOs.setEditable(false);

        txtData.setEditable(false);

        buttonGroup1.add(rbtOrc);
        rbtOrc.setText("Orçamento");
        rbtOrc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtOrcActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbtOs);
        rbtOs.setText("Ordem de Serviço");
        rbtOs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtOsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(rbtOrc)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(txtOs, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbtOs)
                            .addComponent(jLabel2))
                        .addContainerGap(36, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtOs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(rbtOrc))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(rbtOs)))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jLabel3.setText("Situação");

        cboOsSit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Entrega OK", "Orçamento REPROVADO", "Aguardando Aprovação ", "Aguardando peças ", "Abandonado pelo cliente", "Na bancada", "Retornou" }));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Cliente"));

        txtCliPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCliPesquisarKeyReleased(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infocenter/icones/search.png"))); // NOI18N

        jLabel4.setText("* Id");

        txtCliId.setEditable(false);

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Nome", "Fone"
            }
        ));
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblClientes);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCliId, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txtCliId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addGap(16, 16, 16))
        );

        jLabel6.setText("* Equipamento");

        jLabel7.setText("* Defeito");

        jLabel8.setText("Serviço");

        txtOsServ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOsServActionPerformed(evt);
            }
        });

        jLabel9.setText("Técnico");

        jLabel10.setText("Valor Total");

        txtOsValor.setText("0");

        btnOsAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infocenter/icones/update.png"))); // NOI18N
        btnOsAtualizar.setToolTipText("Atualizar");
        btnOsAtualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOsAtualizar.setEnabled(false);
        btnOsAtualizar.setPreferredSize(new java.awt.Dimension(128, 128));
        btnOsAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOsAtualizarActionPerformed(evt);
            }
        });

        btnOsAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infocenter/icones/create.png"))); // NOI18N
        btnOsAdd.setToolTipText("Adicionar");
        btnOsAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOsAdd.setPreferredSize(new java.awt.Dimension(128, 128));
        btnOsAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOsAddActionPerformed(evt);
            }
        });

        btnOsPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infocenter/icones/search_1.png"))); // NOI18N
        btnOsPesquisar.setToolTipText("Pesquisar");
        btnOsPesquisar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOsPesquisar.setPreferredSize(new java.awt.Dimension(128, 128));
        btnOsPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOsPesquisarActionPerformed(evt);
            }
        });

        btnOsImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infocenter/icones/impressora.png"))); // NOI18N
        btnOsImprimir.setToolTipText("Imprimir Os");
        btnOsImprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOsImprimir.setEnabled(false);
        btnOsImprimir.setPreferredSize(new java.awt.Dimension(128, 128));
        btnOsImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOsImprimirActionPerformed(evt);
            }
        });

        btnOsExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infocenter/icones/delete.png"))); // NOI18N
        btnOsExcluir.setEnabled(false);
        btnOsExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOsExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cboOsSit, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addGap(18, 18, 18)
                            .addComponent(txtOsEquip, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel9)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtOsTec, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel8)
                                .addComponent(jLabel7))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtOsServ, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(txtOsDef, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel10)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtOsValor, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(0, 15, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnOsAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnOsPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnOsAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnOsExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnOsImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(160, 160, 160))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cboOsSit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtOsEquip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtOsTec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtOsDef, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txtOsValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(btnOsExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(txtOsServ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnOsAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnOsPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnOsAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btnOsImprimir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        setBounds(0, 0, 673, 483);
    }// </editor-fold>//GEN-END:initComponents

    private void txtOsServActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOsServActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOsServActionPerformed

    private void btnOsAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOsAtualizarActionPerformed
        // TODO add your handling code here:
        alterar_os();
    }//GEN-LAST:event_btnOsAtualizarActionPerformed

    private void btnOsAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOsAddActionPerformed
        // TODO add your handling code here:
        emitir_os();
    }//GEN-LAST:event_btnOsAddActionPerformed

    private void btnOsPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOsPesquisarActionPerformed
        // Chamando o método consultar
       pesquisar_os();
    }//GEN-LAST:event_btnOsPesquisarActionPerformed

    private void btnOsImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOsImprimirActionPerformed
        // TODO add your handling code here:
       imprimir_os();
    }//GEN-LAST:event_btnOsImprimirActionPerformed

    private void txtCliPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCliPesquisarKeyReleased
        // TODO add your handling code here:
        
        pesquisar_cliente();
    }//GEN-LAST:event_txtCliPesquisarKeyReleased

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        // TODO add your handling code here:
        setar_campos();
    }//GEN-LAST:event_tblClientesMouseClicked

    private void rbtOrcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtOrcActionPerformed
        // TODO add your handling code here:
        tipo = "Orçamento"; 
    }//GEN-LAST:event_rbtOrcActionPerformed

    private void rbtOsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtOsActionPerformed
        // TODO add your handling code here:
        tipo = "Ordem de Serviço";
    }//GEN-LAST:event_rbtOsActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        rbtOrc.setSelected(true);
        tipo = "Orçamento";
    }//GEN-LAST:event_formInternalFrameOpened

    private void btnOsExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOsExcluirActionPerformed
        excluir_os();
    }//GEN-LAST:event_btnOsExcluirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOsAdd;
    private javax.swing.JButton btnOsAtualizar;
    private javax.swing.JButton btnOsExcluir;
    private javax.swing.JButton btnOsImprimir;
    private javax.swing.JButton btnOsPesquisar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboOsSit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JRadioButton rbtOrc;
    private javax.swing.JRadioButton rbtOs;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtCliId;
    private javax.swing.JTextField txtCliPesquisar;
    private javax.swing.JTextField txtData;
    private javax.swing.JTextField txtOs;
    private javax.swing.JTextField txtOsDef;
    private javax.swing.JTextField txtOsEquip;
    private javax.swing.JTextField txtOsServ;
    private javax.swing.JTextField txtOsTec;
    private javax.swing.JTextField txtOsValor;
    // End of variables declaration//GEN-END:variables
}
