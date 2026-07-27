package pembayaran;

import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import config.DatabaseConnection;

public class UpdatePembayaranFrame extends javax.swing.JFrame {
    private PembayaranFrame parent; // Variabel untuk menyimpan referensi ke frame induk (PembayaranFrame)
    private int idPembayaran;       // ID pembayaran yang sedang diedit
    private int idPenghuni;         // menyimpan ID diam-diam
    
    public UpdatePembayaranFrame(PembayaranFrame parent, int idPembayaran, 
                             int idPenghuni, String namaPenghuni, String nomorKamar,
                             String bulan, String tanggal, 
                             int jumlah, String status) {
        
        this.parent =parent;
        this.idPembayaran = idPembayaran;
        this.idPenghuni = idPenghuni;   // Simpan ID-nya di belakang layar
        
        initComponents();
        setLocationRelativeTo(null);
       
        
        // Set nilai lama ke form (data yang akan diedit)
        txtPenghuni.setText(nomorKamar + " - " + namaPenghuni);
        cbBulan.setSelectedItem(bulan);
        txtTanggal.setText(tanggal);
        txtJumlah.setText(String.valueOf(jumlah));
        cbStatus.setSelectedItem(status);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        panelMain = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        label1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jlabel = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbBulan = new javax.swing.JComboBox<>();
        txtPenghuni = new javax.swing.JTextField();
        txtTanggal = new javax.swing.JTextField();
        txtJumlah = new javax.swing.JTextField();
        cbStatus = new javax.swing.JComboBox<>();
        btnBatal = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Update Pembayaran");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelMain.setBackground(new java.awt.Color(90, 120, 150));
        panelMain.setPreferredSize(new java.awt.Dimension(950, 540));

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 22)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Update Pembayaran");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Update data pembayaran kamar");

        label1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        label1.setForeground(new java.awt.Color(255, 255, 255));
        label1.setText("Nama Penghuni");

        jLabel7.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Status");

        jlabel.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        jlabel.setForeground(new java.awt.Color(255, 255, 255));
        jlabel.setText("Jumlah");

        jLabel6.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Tanggal Bayar");

        jLabel5.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Tagihan Bulan");

        cbBulan.setBackground(new java.awt.Color(250, 252, 255));
        cbBulan.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cbBulan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember" }));
        cbBulan.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 230, 240), 1, true));

        txtPenghuni.setBackground(new java.awt.Color(250, 252, 255));
        txtPenghuni.setEnabled(false);

        txtTanggal.setBackground(new java.awt.Color(250, 252, 255));
        txtTanggal.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtTanggal.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 230, 240), 1, true));

        txtJumlah.setBackground(new java.awt.Color(250, 252, 255));
        txtJumlah.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtJumlah.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 230, 240), 1, true));
        txtJumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtJumlahKeyReleased(evt);
            }
        });

        cbStatus.setBackground(new java.awt.Color(250, 252, 255));
        cbStatus.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lunas", "Belum Lunas" }));
        cbStatus.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 230, 240), 1, true));
        cbStatus.setEnabled(false);
        cbStatus.setPreferredSize(new java.awt.Dimension(72, 30));

        btnBatal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnBatal.setForeground(new java.awt.Color(90, 120, 150));
        btnBatal.setText("Batal");
        btnBatal.setBorderPainted(false);
        btnBatal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBatal.setFocusPainted(false);
        btnBatal.addActionListener(this::btnBatalActionPerformed);

        btnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(90, 120, 150));
        btnUpdate.setText("Update");
        btnUpdate.setBorderPainted(false);
        btnUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUpdate.setFocusPainted(false);
        btnUpdate.addActionListener(this::btnUpdateActionPerformed);

        javax.swing.GroupLayout panelMainLayout = new javax.swing.GroupLayout(panelMain);
        panelMain.setLayout(panelMainLayout);
        panelMainLayout.setHorizontalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(btnBatal)
                        .addGap(50, 50, 50)
                        .addComponent(btnUpdate))
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelMainLayout.createSequentialGroup()
                                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(label1)
                                    .addComponent(jLabel6)
                                    .addComponent(jlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addGap(37, 37, 37)
                                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtPenghuni)
                                    .addComponent(cbBulan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtTanggal)
                                    .addComponent(txtJumlah)
                                    .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMainLayout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(53, 53, 53)))))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        panelMainLayout.setVerticalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(44, 44, 44)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label1)
                    .addComponent(txtPenghuni, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel5))
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(cbBulan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(24, 24, 24)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBatal)
                    .addComponent(btnUpdate))
                .addGap(28, 28, 28))
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

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        String teksPenghuni = txtPenghuni.getText();
        if (teksPenghuni.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pilih penghuni terlebih dahulu!");
            return;
        }
        // Ambil ID dari txtfield
        String bulan = cbBulan.getSelectedItem().toString(); // Ambil bulan dari ComboBox
        String tanggal = txtTanggal.getText().trim();
        String jumlahStr = txtJumlah.getText().trim().replace(".","");

        if (tanggal.isEmpty() || jumlahStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tanggal dan jumlah harus diisi!");
            return; }

        try {   // Validasi Format
            java.sql.Date.valueOf(tanggal); }
        catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Format tanggal salah! Gunakan YYYY-MM-DD");
            return; }

        int jumlah;
        try {  jumlah = Integer.parseInt(jumlahStr);
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Jumlah harus angka murni!");
            return;
        }

        try {
            Connection conn = DatabaseConnection.getConnection();
            // Ambil harga kamar untuk menentukan status (Lunas/Belum Lunas)
            int tagihan = 0;
            String sqlHarga = "SELECT kamar.harga FROM kamar JOIN penghuni ON penghuni.nomor_kamar = kamar.nomor_kamar WHERE penghuni.id_penghuni = ?";
            try (PreparedStatement ps = conn.prepareStatement(sqlHarga)) {
                ps.setInt(1, idPenghuni);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        tagihan = rs.getInt("harga");
                    }
                }
            }

            // Status otomatis
            String statusBaru = (jumlah >= tagihan) ? "Lunas" : "Belum Lunas";

            // Update data ke database
            String sqlUpdate = "UPDATE pembayaran SET id_penghuni=?, tagihan_bulan=?, tanggal_bayar=?, jumlah=?, status=? WHERE id_pembayaran=?";
            try (PreparedStatement pstmt = conn.prepareStatement(sqlUpdate)) {
                pstmt.setInt(1, idPenghuni);
                pstmt.setString(2, bulan); // Kembali ke format bulan
                pstmt.setDate(3, java.sql.Date.valueOf(tanggal));
                pstmt.setInt(4, jumlah);
                pstmt.setString(5, statusBaru);
                pstmt.setInt(6, idPembayaran); // Pastikan idPembayaran sudah dideklarasikan di class
                pstmt.executeUpdate();

                JOptionPane.showMessageDialog(this, "Data Pembayaran Berhasil Diperbarui!");
                if (parent != null) parent.loadData();
                dispose();
            }
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal memperbarui data: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void txtJumlahKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtJumlahKeyReleased
        String input = txtJumlah.getText().replaceAll("[^\\d]", "");
        if (!input.isEmpty()) { // Format ulang jadi ada titiknya
            try {
                long angka = Long.parseLong(input);
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
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbBulan;
    private javax.swing.JComboBox<String> cbStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jlabel;
    private javax.swing.JLabel label1;
    private javax.swing.JPanel panelMain;
    private javax.swing.JTextField txtJumlah;
    private javax.swing.JTextField txtPenghuni;
    private javax.swing.JTextField txtTanggal;
    // End of variables declaration//GEN-END:variables
}
