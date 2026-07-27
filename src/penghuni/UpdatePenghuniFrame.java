package penghuni;

import config.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdatePenghuniFrame extends javax.swing.JFrame {        
    private PenghuniFrame parent;
    private String idPenghuni;
    private String kamarLama;   //menyimpan nomor kamar sebelum diedit
    
    // Constructor menerima data dari PenghuniFrame
    public UpdatePenghuniFrame(PenghuniFrame parent, String id, String nama, String no_hp,String jenis_kel,
        String no_kamar, String tgl_masuk, String status_penghuni) {
        
        this.parent=parent;
        initComponents();
        setLocationRelativeTo(null);
        
        this.kamarLama = no_kamar;
        this.idPenghuni = id;
        
        txtNama.setText(nama);  //isi textbox nama dengan data nama yang dikirim
        txtNoHP.setText(no_hp);
        cbJenisKel.setSelectedItem(jenis_kel);
        txtTglMasuk.setText(tgl_masuk);
        cbStatus.setSelectedItem(status_penghuni);
        
        tampilKamar();
        cbKamar.setSelectedItem(no_kamar);
    }
    
    private void tampilKamar() {
        String sql = "SELECT nomor_kamar FROM kamar WHERE status_kamar = 'Kosong' OR nomor_kamar = ?";
        try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, kamarLama);
            try (ResultSet rs = pstmt.executeQuery()) {
                cbKamar.removeAllItems();
                while (rs.next()) {
                    cbKamar.addItem(rs.getString("nomor_kamar"));
                }
            }
        } 
        catch (SQLException e) {  JOptionPane.showMessageDialog(null, "Gagal Memuat data Kamar: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMain = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnBatal = new javax.swing.JButton();
        label1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        txtNoHP = new javax.swing.JTextField();
        cbJenisKel = new javax.swing.JComboBox<>();
        cbKamar = new javax.swing.JComboBox<>();
        btnUpdatePenghuni = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtTglMasuk = new javax.swing.JTextField();
        cbStatus = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelMain.setBackground(new java.awt.Color(90, 120, 150));
        panelMain.setPreferredSize(new java.awt.Dimension(950, 540));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 22)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Update Penghuni");

        btnBatal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnBatal.setForeground(new java.awt.Color(90, 120, 150));
        btnBatal.setText("Batal");
        btnBatal.setBorderPainted(false);
        btnBatal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBatal.setFocusPainted(false);
        btnBatal.addActionListener(this::btnBatalActionPerformed);

        label1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        label1.setForeground(new java.awt.Color(255, 255, 255));
        label1.setText("Nama");

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("No HP");

        jLabel5.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Jenis Kelamin");

        jLabel6.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Kamar");

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tanggal Masuk");

        txtNama.setBackground(new java.awt.Color(250, 252, 255));
        txtNama.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtNama.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 230, 240)));
        txtNama.setPreferredSize(new java.awt.Dimension(64, 30));

        txtNoHP.setBackground(new java.awt.Color(250, 252, 255));
        txtNoHP.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtNoHP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 230, 240)));
        txtNoHP.setPreferredSize(new java.awt.Dimension(64, 30));

        cbJenisKel.setBackground(new java.awt.Color(250, 252, 255));
        cbJenisKel.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cbJenisKel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Laki-Laki", "Perempuan" }));
        cbJenisKel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 230, 240), 1, true));

        cbKamar.setBackground(new java.awt.Color(250, 252, 255));
        cbKamar.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cbKamar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 230, 240), 1, true));
        cbKamar.setPreferredSize(new java.awt.Dimension(72, 30));

        btnUpdatePenghuni.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnUpdatePenghuni.setForeground(new java.awt.Color(90, 120, 150));
        btnUpdatePenghuni.setText("Update");
        btnUpdatePenghuni.setBorderPainted(false);
        btnUpdatePenghuni.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUpdatePenghuni.setFocusPainted(false);
        btnUpdatePenghuni.addActionListener(this::btnUpdatePenghuniActionPerformed);

        jLabel7.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Status");

        txtTglMasuk.setBackground(new java.awt.Color(250, 252, 255));
        txtTglMasuk.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtTglMasuk.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 230, 240), 1, true));

        cbStatus.setBackground(new java.awt.Color(250, 252, 255));
        cbStatus.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Aktif", "Keluar" }));
        cbStatus.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 230, 240), 1, true));

        javax.swing.GroupLayout panelMainLayout = new javax.swing.GroupLayout(panelMain);
        panelMain.setLayout(panelMainLayout);
        panelMainLayout.setHorizontalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(btnBatal)
                        .addGap(68, 68, 68)
                        .addComponent(btnUpdatePenghuni))
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMainLayout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMainLayout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMainLayout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addGap(18, 18, 18)))
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel7)
                                .addGroup(panelMainLayout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(7, 7, 7)))
                            .addComponent(jLabel6)
                            .addComponent(label1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbKamar, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbJenisKel, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNoHP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTglMasuk)
                    .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44))
        );
        panelMainLayout.setVerticalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(label1))
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNoHP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))))
                .addGap(29, 29, 29)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbJenisKel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbKamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(27, 27, 27)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txtTglMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBatal)
                    .addComponent(btnUpdatePenghuni))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelMain, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMain, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        dispose();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnUpdatePenghuniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdatePenghuniActionPerformed
        String nama = txtNama.getText().trim();
        String no_hp = txtNoHP.getText().trim();
        String jenis_kel = cbJenisKel.getSelectedItem().toString();
        String no_kamar = cbKamar.getSelectedItem().toString();
        String tgl_masuk = txtTglMasuk.getText().trim();
        String status_penghuni = cbStatus.getSelectedItem().toString();

        // Validasi
        if (nama.isEmpty() || no_hp.isEmpty() || tgl_masuk.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama, No HP, dan Tanggal Masuk harus diisi!");
            return; }

        try (Connection conn = DatabaseConnection.getConnection()) {

            // CEK KAMAR SEBELUM UPDATE
            String sqlCek = "SELECT COUNT(*) FROM penghuni WHERE nomor_kamar = ? AND status_penghuni = 'Aktif' AND id_penghuni != ?";
            try (PreparedStatement ps = conn.prepareStatement(sqlCek)) {
                ps.setString(1, no_kamar);
                ps.setInt(2, Integer.parseInt(idPenghuni));
                ResultSet rs = ps.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    JOptionPane.showMessageDialog(this, "Gagal! Kamar ini sudah diisi penghuni aktif lain.");
                    return; // Berhenti di sini
                }
            }

            //  Update penghuni
            String sqlUpdate = "UPDATE penghuni SET nama_penghuni=?, no_hp=?, jenis_kelamin=?, nomor_kamar=?, tanggal_masuk=?, status_penghuni=? WHERE id_penghuni=?";
            try (PreparedStatement pstmt = conn.prepareStatement(sqlUpdate)) {
                pstmt.setString(1, nama);
                pstmt.setString(2, no_hp);
                pstmt.setString(3, jenis_kel);
                pstmt.setString(4, no_kamar);
                pstmt.setString(5, tgl_masuk);
                pstmt.setString(6, status_penghuni);
                pstmt.setString(7, idPenghuni);
                pstmt.executeUpdate();
            }

            // Update Status Kamar Lama (Jika pindah kamar)
            if (!kamarLama.equals(no_kamar)) {
                String sqlKosong = "UPDATE kamar SET status_kamar='Kosong' WHERE nomor_kamar=?";
                try (PreparedStatement pstmtKosong = conn.prepareStatement(sqlKosong)) {
                    pstmtKosong.setString(1, kamarLama);
                    pstmtKosong.executeUpdate();
                }
            }

            //Update status kamar baru sesuai status penghuni
            String statusKamar = status_penghuni.equals("Aktif") ? "Terisi" : "Kosong";
            String sqlKamar = "UPDATE kamar SET status_kamar=? WHERE nomor_kamar=?";
            try (PreparedStatement pstmtKamar = conn.prepareStatement(sqlKamar)) {
                pstmtKamar.setString(1, statusKamar);
                pstmtKamar.setString(2, no_kamar);
                pstmtKamar.executeUpdate();
            }

            JOptionPane.showMessageDialog(this, "Data penghuni berhasil diupdate!");
            if (parent != null) parent.loadData();
            dispose();
        }

        catch (SQLException e) { JOptionPane.showMessageDialog(this, "Gagal update: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnUpdatePenghuniActionPerformed

    public static void main(String args[]) {
    java.awt.EventQueue.invokeLater(() -> {
    });
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnUpdatePenghuni;
    private javax.swing.JComboBox<String> cbJenisKel;
    private javax.swing.JComboBox<String> cbKamar;
    private javax.swing.JComboBox<String> cbStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel label1;
    private javax.swing.JPanel panelMain;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNoHP;
    private javax.swing.JTextField txtTglMasuk;
    // End of variables declaration//GEN-END:variables
}
