package pemilikKos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import config.DatabaseConnection;
import java.awt.BorderLayout;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class PemilikKosFrame extends javax.swing.JFrame {
    private DefaultTableModel model;
    private int selectedId = -1;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(PemilikKosFrame.class.getName());

    public PemilikKosFrame() {
        initComponents();
        setTitle("Daftar Pemilik Kos");
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());
        initCRUD();
        loadData();
        
    }
    
     private void initCRUD() {
        model = (DefaultTableModel) tblPemilikKos.getModel();
        loadData();
        
        tblPemilikKos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = tblPemilikKos.getSelectedRow();
                if (row != -1) {
                    selectedId = (int) model.getValueAt(row, 0);
                    txtNama.setText(model.getValueAt(row, 1).toString());
                    txtAlamat.setText(model.getValueAt(row, 2).toString());
                    txtNoHp.setText(model.getValueAt(row, 3).toString());
                }
            }
        });
    }
     
    private void loadData() {
        model.setRowCount(0);
        String sql = "SELECT * FROM pemilik_kos ORDER BY id_pemilik";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id_pemilik"),
                    rs.getString("nama"),
                    rs.getString("alamat"),
                    rs.getString("no_hp")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal load data: " + e.getMessage());
        }
    }
    
    private void clearForm() {
        txtNama.setText("");
        txtAlamat.setText("");
        txtNoHp.setText("");
        selectedId = -1;
        tblPemilikKos.clearSelection();
    }
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        ScrollPane = new javax.swing.JScrollPane();
        tblPemilikKos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnTambah = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtAlamat = new javax.swing.JTextField();
        txtNoHp = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(176, 206, 240));

        tblPemilikKos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblPemilikKos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "Nama", "Alamat", "No hp"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPemilikKos.setGridColor(new java.awt.Color(235, 240, 248));
        tblPemilikKos.setRowHeight(30);
        tblPemilikKos.setSelectionBackground(new java.awt.Color(215, 228, 255));
        tblPemilikKos.setSelectionForeground(new java.awt.Color(35, 55, 90));
        ScrollPane.setViewportView(tblPemilikKos);
        if (tblPemilikKos.getColumnModel().getColumnCount() > 0) {
            tblPemilikKos.getColumnModel().getColumn(0).setResizable(false);
            tblPemilikKos.getColumnModel().getColumn(3).setResizable(false);
        }

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(90, 120, 150));
        jLabel1.setText("DATA PEMILIK KOST");

        btnTambah.setBackground(new java.awt.Color(90, 120, 150));
        btnTambah.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTambah.setForeground(new java.awt.Color(255, 255, 255));
        btnTambah.setText("Tambah");
        btnTambah.setBorderPainted(false);
        btnTambah.setFocusPainted(false);
        btnTambah.addActionListener(this::btnTambahActionPerformed);

        btnEdit.setBackground(new java.awt.Color(90, 120, 150));
        btnEdit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setText("Edit");
        btnEdit.setBorderPainted(false);
        btnEdit.setFocusPainted(false);
        btnEdit.addActionListener(this::btnEditActionPerformed);

        btnHapus.setBackground(new java.awt.Color(255, 255, 255));
        btnHapus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnHapus.setForeground(new java.awt.Color(90, 120, 150));
        btnHapus.setText("Hapus");
        btnHapus.setBorderPainted(false);
        btnHapus.setFocusPainted(false);
        btnHapus.addActionListener(this::btnHapusActionPerformed);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(90, 120, 150));
        jLabel2.setText("Nama Pemilik");

        txtNama.setBackground(new java.awt.Color(235, 242, 252));
        txtNama.setBorder(null);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(90, 120, 150));
        jLabel3.setText("Alamat");

        txtAlamat.setBackground(new java.awt.Color(235, 242, 252));
        txtAlamat.setBorder(null);

        txtNoHp.setBackground(new java.awt.Color(235, 242, 252));
        txtNoHp.setBorder(null);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(90, 120, 150));
        jLabel4.setText("No HP");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(191, 191, 191)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(228, 228, 228)
                        .addComponent(btnHapus)
                        .addGap(18, 18, 18)
                        .addComponent(btnTambah)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEdit))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtAlamat, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
                            .addComponent(txtNoHp)
                            .addComponent(txtNama, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addComponent(ScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtAlamat)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNoHp, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHapus)
                    .addComponent(btnTambah)
                    .addComponent(btnEdit))
                .addGap(18, 18, 18)
                .addComponent(ScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        String nama = txtNama.getText().trim();
        String alamat = txtAlamat.getText().trim();
        String no_hp = txtNoHp.getText().trim();
        if (nama.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama pemilik harus diisi!");
            return;
        }
        String sql = "INSERT INTO pemilik_kos (nama, alamat, no_hp) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nama);
            ps.setString(2, alamat);
            ps.setString(3, no_hp);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan!");
            loadData();
            clearForm();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal tambah: " + e.getMessage());
        }
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        if (selectedId == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data yang akan dihapus!");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Yakin hapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            String sql = "DELETE FROM pemilik_kos WHERE id_pemilik=?";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, selectedId);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
                loadData();
                clearForm();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Gagal hapus: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        if (selectedId == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data yang akan diedit!");
            return;
        }
        String nama = txtNama.getText().trim();
        String alamat = txtAlamat.getText().trim();
        String no_hp = txtNoHp.getText().trim();
        if (nama.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama pemilik kos harus diisi!");
            return;
        }
        String sql = "UPDATE pemilik_kos SET nama=?, alamat=?, no_hp=? WHERE id_pemilik=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nama);
            ps.setString(2, alamat);
            ps.setString(3, no_hp);
            ps.setInt(4, selectedId);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data berhasil diupdate!");
            loadData();
            clearForm();
        } 
        catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal update: " + e.getMessage());
        }
    }//GEN-LAST:event_btnEditActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> new PemilikKosFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ScrollPane;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnTambah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTable tblPemilikKos;
    private javax.swing.JTextField txtAlamat;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNoHp;
    // End of variables declaration//GEN-END:variables
}
