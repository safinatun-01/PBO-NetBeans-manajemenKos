package kamar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import config.DatabaseConnection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TambahKamarFrame extends javax.swing.JFrame {
    private KamarFrame parent;
    public TambahKamarFrame(KamarFrame parent) {
        this.parent = parent;
        initComponents(); 
        setLocationRelativeTo(null);    //Menampilkan Layar ditengah
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMain = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnBatal = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        label1 = new javax.swing.JLabel();
        cbTipeKamar = new javax.swing.JComboBox<>();
        txtNomorKamar = new javax.swing.JTextField();
        txtHarga = new javax.swing.JTextField();
        btnSimpan = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelMain.setBackground(new java.awt.Color(90, 120, 150));
        panelMain.setPreferredSize(new java.awt.Dimension(950, 540));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 22)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Tambah Data Kamar");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tambahkan data kamar baru");

        btnBatal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnBatal.setForeground(new java.awt.Color(90, 120, 150));
        btnBatal.setText("Batal");
        btnBatal.setBorderPainted(false);
        btnBatal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBatal.setFocusPainted(false);
        btnBatal.addActionListener(this::btnBatalActionPerformed);

        jLabel6.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Harga");

        jLabel5.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Tipe Kamar");

        label1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        label1.setForeground(new java.awt.Color(255, 255, 255));
        label1.setText("Nomor Kamar");

        cbTipeKamar.setBackground(new java.awt.Color(250, 252, 255));
        cbTipeKamar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbTipeKamar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Standar", "VIP", "Exclusive" }));

        txtNomorKamar.setBackground(new java.awt.Color(250, 252, 255));

        txtHarga.setBackground(new java.awt.Color(250, 252, 255));
        txtHarga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtHargaKeyReleased(evt);
            }
        });

        btnSimpan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSimpan.setForeground(new java.awt.Color(90, 120, 150));
        btnSimpan.setText("Simpan");
        btnSimpan.setBorderPainted(false);
        btnSimpan.addActionListener(this::btnSimpanActionPerformed);

        javax.swing.GroupLayout panelMainLayout = new javax.swing.GroupLayout(panelMain);
        panelMain.setLayout(panelMainLayout);
        panelMainLayout.setHorizontalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMainLayout.createSequentialGroup()
                .addGap(0, 93, Short.MAX_VALUE)
                .addComponent(btnBatal)
                .addGap(54, 54, 54)
                .addComponent(btnSimpan)
                .addGap(102, 102, 102))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMainLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label1)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtHarga)
                            .addComponent(cbTipeKamar, 0, 196, Short.MAX_VALUE)
                            .addComponent(txtNomorKamar))))
                .addGap(41, 41, 41))
            .addGroup(panelMainLayout.createSequentialGroup()
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(jLabel2))
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelMainLayout.setVerticalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNomorKamar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label1))
                .addGap(37, 37, 37)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbTipeKamar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(37, 37, 37)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(57, 57, 57)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpan)
                    .addComponent(btnBatal))
                .addGap(76, 76, 76))
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

    private void txtHargaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHargaKeyReleased
        String input = txtHarga.getText().replaceAll("[^\\d]", "");
        if (!input.isEmpty()) { // Format ulang jadi ada titiknya
            try {
                long angka = Long.parseLong(input);
                txtHarga.setText(String.format("%,d", angka).replace("," , "."));
            }
            catch (NumberFormatException e) {
                // Abaikan error jika input terlalu panjang
            } }
            else {  txtHarga.setText(""); // Kosongkan jika dihapus semua
            }
    }//GEN-LAST:event_txtHargaKeyReleased

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // Ambil data dari form
        String nomor = txtNomorKamar.getText().trim().toUpperCase(); // .toUpperCase() memaksa inputan otomatis jadi huruf KAPITAL         // Nomor kamar, hapus spasi kiri/kanan
        String tipe = cbTipeKamar.getSelectedItem().toString(); // Tipe kamar dari combobox
        String hargaStr = txtHarga.getText().trim().replace(".", "");

        // Validasi nomor kamar tidak boleh kosong -----
        if (nomor.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nomor kamar harus diisi!");
            return; // Hentikan proses
        }

        // Validasi format Hanya boleh Huruf A-Z dan Angka 0-9, maksimal 4 karakter
        if (!nomor.matches("^[A-Z]{1,3}[0-9]{1,3}$")) {
            JOptionPane.showMessageDialog(this, "Format kamar tidak valid! Gunakan maksimal 4 karakter huruf/angka tanpa spasi. Contoh: A1, B2.");
            return; // Berhenti, jangan simpan ke database
        }

        // Validasi harga harus angka positif
        int harga = 0;
        try {   harga = Integer.parseInt(hargaStr); // Ubah string menjadi integer
            if (harga <= 0) { JOptionPane.showMessageDialog(this, "Harga harus berupa angka positif!");
                return;
            }}
            catch (NumberFormatException e) {   JOptionPane.showMessageDialog(this, "Harga harus berupa angka!");
                return;
            }

            // Simpan ke database
            try (Connection conn = DatabaseConnection.getConnection()) {

                // Cek apakah nomor kamar sudah ada
                String cekSql = "SELECT COUNT(*) FROM kamar WHERE nomor_kamar = ?";
                try (PreparedStatement psCek = conn.prepareStatement(cekSql)) {
                    psCek.setString(1, nomor);
                    ResultSet rs = psCek.executeQuery();
                    rs.next();
                    if (rs.getInt(1) > 0) { // Jika sudah ada
                        JOptionPane.showMessageDialog(this, "Nomor kamar sudah ada! Gunakan nomor lain.");
                        return;
                    }}

                    // Insert data kamar baru
                    String sql = "INSERT INTO kamar (nomor_kamar, tipe_kamar, harga, status_kamar) VALUES (?, ?, ?, ?)";
                    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                        pstmt.setString(1, nomor);
                        pstmt.setString(2, tipe);
                        pstmt.setInt(3, harga);            // Harga dalam bentuk int
                        pstmt.setString(4, "Kosong");      // Status awal selalu Kosong
                        pstmt.executeUpdate();              // Eksekusi INSERT
                    }

                    // Beri pesan sukses dan refresh tabel di KamarFrame
                    JOptionPane.showMessageDialog(this, "Data kamar berhasil disimpan!");
                    if (parent != null) {
                        parent.loadData(); // Panggil method refresh milik KamarFrame
                    }
                    dispose(); // Tutup form tambah
                }
                catch (SQLException e) {   JOptionPane.showMessageDialog(this, "Gagal menyimpan data: " + e.getMessage());
                    e.printStackTrace(); // Untuk debugging jika diperlukan
               }
            
    }//GEN-LAST:event_btnSimpanActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JComboBox<String> cbTipeKamar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel label1;
    private javax.swing.JPanel panelMain;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtNomorKamar;
    // End of variables declaration//GEN-END:variables
}
