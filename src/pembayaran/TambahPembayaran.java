
package pembayaran;

import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import config.DatabaseConnection;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;

public class TambahPembayaran extends javax.swing.JFrame {
    private PembayaranFrame parent; // referensi ke frame induk
    public TambahPembayaran(PembayaranFrame parent) {
        
        this.parent = parent;
        initComponents();
        setLocationRelativeTo(null);
        isiComboPenghuni();
        
        // Set bulan default ke bulan saat ini (Indonesia)
        String[] namaBulan = {"Januari", "February", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"};
        int bulanSekarang = java.time.LocalDate.now().getMonthValue() - 1; // 0-index
        cbBulan.setSelectedItem(namaBulan[bulanSekarang]);
        txtTanggal.setText(java.time.LocalDate.now().toString());
    }

    private void isiComboPenghuni() {
        String sql = "SELECT penghuni.id_penghuni, penghuni.nama_penghuni, kamar.nomor_kamar " +
             "FROM penghuni " +
             "JOIN kamar ON penghuni.nomor_kamar = kamar.nomor_kamar " +
             "WHERE penghuni.status_penghuni = 'Aktif'";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                cbPenghuni.addItem(rs.getInt("id_penghuni") + " - " + rs.getString("nama_penghuni") 
                               + " (Kamar " + rs.getString("nomor_kamar") + ")");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal load penghuni: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMain = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnSimpan = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        cbStatus = new javax.swing.JComboBox<>();
        txtJumlah = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jlabel = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbBulan = new javax.swing.JComboBox<>();
        txtTanggal = new javax.swing.JTextField();
        cbPenghuni = new javax.swing.JComboBox<>();
        label1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelMain.setBackground(new java.awt.Color(90, 120, 150));
        panelMain.setPreferredSize(new java.awt.Dimension(950, 540));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 22)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Tambah Pembayaran");

        btnSimpan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSimpan.setForeground(new java.awt.Color(90, 120, 150));
        btnSimpan.setText("Simpan");
        btnSimpan.setBorderPainted(false);
        btnSimpan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSimpan.setFocusPainted(false);
        btnSimpan.addActionListener(this::btnSimpanActionPerformed);

        btnBatal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnBatal.setForeground(new java.awt.Color(90, 120, 150));
        btnBatal.setText("Batal");
        btnBatal.setBorderPainted(false);
        btnBatal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBatal.setFocusPainted(false);
        btnBatal.addActionListener(this::btnBatalActionPerformed);

        cbStatus.setBackground(new java.awt.Color(250, 252, 255));
        cbStatus.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lunas", "Belum Lunas" }));
        cbStatus.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 230, 240), 1, true));
        cbStatus.setEnabled(false);
        cbStatus.setPreferredSize(new java.awt.Dimension(72, 30));
        cbStatus.addActionListener(this::cbStatusActionPerformed);

        txtJumlah.setBackground(new java.awt.Color(250, 252, 255));
        txtJumlah.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtJumlah.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 230, 240), 1, true));
        txtJumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtJumlahKeyReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Status");

        jlabel.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        jlabel.setForeground(new java.awt.Color(255, 255, 255));
        jlabel.setText("Jumlah");

        jLabel6.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Tanggal Bayar");

        jLabel5.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Tagihan Bulan");

        cbBulan.setBackground(new java.awt.Color(250, 252, 255));
        cbBulan.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cbBulan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember" }));
        cbBulan.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 230, 240), 1, true));

        txtTanggal.setBackground(new java.awt.Color(250, 252, 255));
        txtTanggal.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtTanggal.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 230, 240), 1, true));

        cbPenghuni.setBackground(new java.awt.Color(250, 252, 255));
        cbPenghuni.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cbPenghuni.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 230, 240), 1, true));

        label1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        label1.setForeground(new java.awt.Color(255, 255, 255));
        label1.setText("Nama Penghuni");

        javax.swing.GroupLayout panelMainLayout = new javax.swing.GroupLayout(panelMain);
        panelMain.setLayout(panelMainLayout);
        panelMainLayout.setHorizontalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(jLabel1))
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(btnBatal)
                        .addGap(72, 72, 72)
                        .addComponent(btnSimpan)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMainLayout.createSequentialGroup()
                .addGap(0, 37, Short.MAX_VALUE)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlabel)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtTanggal, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtJumlah, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cbStatus, 0, 186, Short.MAX_VALUE))
                            .addComponent(cbBulan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addComponent(label1)
                        .addGap(28, 28, 28)
                        .addComponent(cbPenghuni, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32))
        );
        panelMainLayout.setVerticalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbPenghuni, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label1))
                .addGap(18, 18, 18)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbBulan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(22, 22, 22)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpan)
                    .addComponent(btnBatal))
                .addGap(26, 26, 26))
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

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        if (cbPenghuni.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this, "Pilih penghuni");
            return;
        }

        int idPenghuni = Integer.parseInt(cbPenghuni.getSelectedItem().toString().split(" - ")[0]);
        String bulan = cbBulan.getSelectedItem().toString();
        String tanggal = txtTanggal.getText().trim();
        String jumlahStr = txtJumlah.getText().trim().replace(".", "");

        if (tanggal.isEmpty() || jumlahStr.isEmpty()) {  // CEK TGL DAN NOMINAL ADA ISI ATAU TIDAK
            JOptionPane.showMessageDialog(this, "Tanggal dan jumlah harus diisi");
            return;
        }

        try { java.sql.Date.valueOf(tanggal); }
        catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Format tanggal salah! Gunakan YYYY-MM-DD");
            return;
        }

        int jumlah;
        try { jumlah = Integer.parseInt(jumlahStr); }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Jumlah tidak valid atau angkanya terlalu besar!");
            return;
        }

        try {
            Connection conn = DatabaseConnection.getConnection();
            // Ambil harga kamar (tagihan bulanan )
            String sqlHarga = "SELECT kamar.harga FROM kamar " +
            "JOIN penghuni ON penghuni.nomor_kamar = kamar.nomor_kamar " +
            "WHERE penghuni.id_penghuni = ?";
            int hargaKamar = 0;
            try (PreparedStatement psHarga = conn.prepareStatement(sqlHarga)) {
                psHarga.setInt(1, idPenghuni);
                try (ResultSet rsHarga = psHarga.executeQuery()) {
                    if (rsHarga.next()) {   hargaKamar = rsHarga.getInt("harga");  }
                    else { JOptionPane.showMessageDialog(this, "Harga kamar tidak ditemukan!");
                        return;
                    }
                }
            }

            // Logika otomatis menentukan status berdasarkan uang bulanan yang dimasukkan
            String status = (jumlah >= hargaKamar) ? "Lunas" : "Belum Lunas";

            // Peringatan jika admin memasukkan uang melebihi tagihan ---
            if (jumlah > hargaKamar) {
                int konfirmasiTypo = JOptionPane.showConfirmDialog(this,
                    "Peringatan: Jumlah bayar (Rp " + jumlah + ") LEBIH BESAR dari harga kamar (Rp " 
                            + hargaKamar + ").\n" +
                    "Apakah Anda yakin angka ini sudah benar?",
                    "Konfirmasi Kelebihan Uang",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);

                if (konfirmasiTypo == JOptionPane.NO_OPTION) {
                    return; // Berhenti proses simpan agar admin bisa mengoreksi angka
                }
            }

            // Cek duplikasi pembayaran untuk bulan yang sama
            String cekSql = "SELECT COUNT(*) FROM pembayaran WHERE id_penghuni = ? AND tagihan_bulan = ?";
            try (PreparedStatement cekStmt = conn.prepareStatement(cekSql)) {
                cekStmt.setInt(1, idPenghuni);
                cekStmt.setString(2, bulan);
                try (ResultSet rsCek = cekStmt.executeQuery()) {
                    if (rsCek.next() && rsCek.getInt(1) > 0) {
                        int pilih = JOptionPane.showConfirmDialog(this,
                            "Pembayaran untuk bulan " + bulan + " sudah ada.\nApakah Anda ingin menambah lagi (cicilan)?",
                            "Konfirmasi", JOptionPane.YES_NO_OPTION);
                        if (pilih != JOptionPane.YES_OPTION) {
                            return;
                        }
                    }
                }
            }

            // Insert pembayaran murni per bulan ke database
            String sql = "INSERT INTO pembayaran (id_penghuni, tagihan_bulan, tanggal_bayar, Residential_jumlah, tagihan, status) VALUES (?,?,?,?,?,?)";
            String sqlReal = "INSERT INTO pembayaran (id_penghuni, tagihan_bulan, tanggal_bayar, jumlah, tagihan, status) VALUES (?,?,?,?,?,?)";
            try (PreparedStatement stmt = conn.prepareStatement(sqlReal)) {
                stmt.setInt(1, idPenghuni);
                stmt.setString(2, bulan); // Menyimpan nama bulan asli (Maret, Juni, Agustus, dsb)
                stmt.setDate(3, java.sql.Date.valueOf(tanggal));
                stmt.setInt(4, jumlah);
                stmt.setInt(5, hargaKamar); // Tagihan murni 1 bulan
                stmt.setString(6, status);

                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Data berhasil disimpan");

                if (parent != null) parent.loadData();
                dispose();
            }
        }
        catch (SQLException e) { JOptionPane.showMessageDialog(this, "Gagal simpan: " + e.getMessage());
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        dispose();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void cbStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbStatusActionPerformed

    private void txtJumlahKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtJumlahKeyReleased
        // Ambil teks dan buang semua karakter yang bukan angka (termasuk titik lama/huruf)
        String input = txtJumlah.getText().replaceAll("[^\\d]", "");
        if (!input.isEmpty()) { // Format ulang jadi ada titiknya
            try {
                long angka = Long.parseLong(input);
                // Format pakai titik pemisah ribuan ala Indonesia
                txtJumlah.setText(String.format("%,d", angka).replace(',', '.'));
            }
            catch (NumberFormatException e) {
                // Abaikan error jika input terlalu panjang
            } }
            else {  txtJumlah.setText(""); // Kosongkan jika dihapus semua
            }
    }//GEN-LAST:event_txtJumlahKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JComboBox<String> cbBulan;
    private javax.swing.JComboBox<String> cbPenghuni;
    private javax.swing.JComboBox<String> cbStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jlabel;
    private javax.swing.JLabel label1;
    private javax.swing.JPanel panelMain;
    private javax.swing.JTextField txtJumlah;
    private javax.swing.JTextField txtTanggal;
    // End of variables declaration//GEN-END:variables
}
