package penghuni;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import config.DatabaseConnection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TambahPenghuniFrame extends javax.swing.JFrame {
    private PenghuniFrame parent;
    public TambahPenghuniFrame(PenghuniFrame parent) {
        
        this.parent=parent;
        initComponents();
        setLocationRelativeTo(null);
        tampilKamar();
    }
    
    private void tampilKamar() {
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(
             "SELECT nomor_kamar FROM kamar WHERE status_kamar = 'Kosong'");
         ResultSet rs = pstmt.executeQuery()) {
        cbKamar.removeAllItems();
        while (rs.next()) {
            cbKamar.addItem(rs.getString("nomor_kamar"));
        }
    } 
    catch (SQLException e) {  JOptionPane.showMessageDialog(null, "Gagal menampilkan kamar: " + e.getMessage());
    }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMain = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
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
        txtTglMasuk = new javax.swing.JTextField();
        btnSimpanPenghuni = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelMain.setBackground(new java.awt.Color(90, 120, 150));
        panelMain.setPreferredSize(new java.awt.Dimension(950, 540));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 22)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Tambah Penghuni");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(250, 252, 255));
        jLabel2.setText("Tambahkan data Penghuni");

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

        txtTglMasuk.setBackground(new java.awt.Color(250, 252, 255));
        txtTglMasuk.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtTglMasuk.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 230, 240), 1, true));

        btnSimpanPenghuni.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSimpanPenghuni.setForeground(new java.awt.Color(90, 120, 150));
        btnSimpanPenghuni.setText("Simpan");
        btnSimpanPenghuni.setBorderPainted(false);
        btnSimpanPenghuni.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSimpanPenghuni.setFocusPainted(false);
        btnSimpanPenghuni.addActionListener(this::btnSimpanPenghuniActionPerformed);

        javax.swing.GroupLayout panelMainLayout = new javax.swing.GroupLayout(panelMain);
        panelMain.setLayout(panelMainLayout);
        panelMainLayout.setHorizontalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMainLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(11, 11, 11))))
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(btnBatal)
                        .addGap(68, 68, 68)
                        .addComponent(btnSimpanPenghuni)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMainLayout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMainLayout.createSequentialGroup()
                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMainLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(64, 64, 64))
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(9, 9, 9))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMainLayout.createSequentialGroup()
                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(label1))
                        .addGap(18, 18, 18)))
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbKamar, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTglMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbJenisKel, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNoHP, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36))
        );
        panelMainLayout.setVerticalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 62, Short.MAX_VALUE))
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label1))))
                .addGap(31, 31, 31)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNoHP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbJenisKel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbKamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTglMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(44, 44, 44)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpanPenghuni)
                    .addComponent(btnBatal))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMain, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
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

    private void btnSimpanPenghuniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanPenghuniActionPerformed
        String nama = txtNama.getText().trim();
        String no_hp = txtNoHP.getText().trim();
        String jenis_kelamin = cbJenisKel.getSelectedItem().toString();
        String nomorKamar = cbKamar.getSelectedItem().toString();
        String tanggal_masuk = txtTglMasuk.getText().trim();
        String status_penghuni = "Aktif";
        
        Penghuni penghuni = new Penghuni(nama, no_hp, jenis_kelamin, nomorKamar, tanggal_masuk, status_penghuni );
        
        // Validasi
        if (penghuni.getNama().isEmpty()
            || penghuni.getNoHp().isEmpty()
            || penghuni.getTanggalMasuk().isEmpty()
            || penghuni.getNomorKamar().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Semua field harus diisi!");
        return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            //  Cek apakah kamar sudah ada di tabel kamar
            String sqlCekKamar = "SELECT status_kamar FROM kamar WHERE nomor_kamar = ?";
            String statusKamar = null;
            boolean kamarAda = false;

            try (PreparedStatement psCek = conn.prepareStatement(sqlCekKamar)) {
                psCek.setString(1, nomorKamar);
                ResultSet rs = psCek.executeQuery();
                if (rs.next()) {
                    kamarAda = true;
                    statusKamar = rs.getString("status_kamar");
                }
            }

            // Jika kamar belum ada, buat baru dengan status 'Kosong'
            if (!kamarAda) {
                String sqlBuatKamar = "INSERT INTO kamar (nomor_kamar, tipe_kamar, harga, status_kamar) VALUES (?, ?, ?, ?)";
                try (PreparedStatement psBuat = conn.prepareStatement(sqlBuatKamar)) {
                    psBuat.setString(1, nomorKamar);
                    psBuat.setString(2, "Standar");  // tipe default, bisa disesuaikan
                    psBuat.setInt(3, 0);             // harga default, nanti bisa diupdate
                    psBuat.setString(4, "Kosong");
                    psBuat.executeUpdate();
                }
                statusKamar = "Kosong";
            }

            // Cegah double booking jika kamar sudah Terisi
            if ("Terisi".equals(statusKamar)) {
                JOptionPane.showMessageDialog(this, "Kamar " + nomorKamar + " sudah terisi oleh penghuni lain!",
                    "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Insert penghuni baru
            String sqlInsert = "INSERT INTO penghuni (nama_penghuni, no_hp, jenis_kelamin, nomor_kamar, tanggal_masuk, status_penghuni) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sqlInsert)) {
                pstmt.setString(1, penghuni.getNama());
                pstmt.setString(2, penghuni.getNoHp());
                pstmt.setString(3, penghuni.getJenisKelamin());
                pstmt.setString(4, penghuni.getNomorKamar());
                pstmt.setString(5, penghuni.getTanggalMasuk());
                pstmt.setString(6, penghuni.getStatusPenghuni());
                pstmt.executeUpdate();
            }

            // Update status kamar menjadi 'Terisi'
            String sqlUpdateKamar = "UPDATE kamar SET status_kamar = 'Terisi' WHERE nomor_kamar = ?";
            try (PreparedStatement psUpdate = conn.prepareStatement(sqlUpdateKamar)) {
                psUpdate.setString(1, nomorKamar);
                psUpdate.executeUpdate();
            }

            JOptionPane.showMessageDialog(this, "Data Penghuni berhasil ditambahkan!");
            if (parent != null) parent.loadData();
            dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan data: " + e.getMessage());
        }
    }//GEN-LAST:event_btnSimpanPenghuniActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnSimpanPenghuni;
    private javax.swing.JComboBox<String> cbJenisKel;
    private javax.swing.JComboBox<String> cbKamar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel label1;
    private javax.swing.JPanel panelMain;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNoHP;
    private javax.swing.JTextField txtTglMasuk;
    // End of variables declaration//GEN-END:variables
}
