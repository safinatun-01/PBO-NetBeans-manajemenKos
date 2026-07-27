package Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import config.DatabaseConnection;
import javax.swing.JOptionPane;


public class LoginFrame extends javax.swing.JFrame {    
    public LoginFrame() {
    initComponents();
    setLocationRelativeTo(null);
    setTitle("KOSKITA");    // Mengubah title window
    setResizable(false);    // Mencegah resize window
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        roundedPanel1 = new config.RoundedPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        btnMasuk = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Login/resizee.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(53, 92, 125));
        jLabel2.setText("Selamat Datang !");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(109, 130, 153));
        jLabel3.setText("<html>\nMasuk Untuk Mengakses <br>\nManajemen Kos-Kosan\n</html>");
        jLabel3.setToolTipText("");

        txtUsername.setBackground(new java.awt.Color(246, 251, 255));
        txtUsername.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtUsername.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(184, 223, 255)));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(79, 109, 122));
        jLabel4.setText("Username");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(79, 109, 122));
        jLabel5.setText("Password");

        txtPassword.setBackground(new java.awt.Color(246, 251, 255));
        txtPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(184, 223, 255)));
        txtPassword.addActionListener(this::txtPasswordActionPerformed);

        btnMasuk.setBackground(new java.awt.Color(0, 100, 150));
        btnMasuk.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnMasuk.setForeground(new java.awt.Color(255, 255, 255));
        btnMasuk.setText("MASUK");
        btnMasuk.setBorderPainted(false);
        btnMasuk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMasuk.addActionListener(this::btnMasukActionPerformed);

        javax.swing.GroupLayout roundedPanel1Layout = new javax.swing.GroupLayout(roundedPanel1);
        roundedPanel1.setLayout(roundedPanel1Layout);
        roundedPanel1Layout.setHorizontalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(txtUsername)
                            .addComponent(txtPassword)))
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 62, Short.MAX_VALUE))
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addComponent(btnMasuk)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundedPanel1Layout.setVerticalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(btnMasuk)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 718, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundedPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordActionPerformed

    private void btnMasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMasukActionPerformed
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();

        // 1. PERIKSA APAKAH KOSONG
        if (username.isEmpty() || password.isEmpty()) {      
            JOptionPane.showMessageDialog(null, "Username dan Password harus diisi!");
            return;
        }

        String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";

        // 2. Menggunakan try-with-resources untuk menutup conn, pstmt, dan rs secara otomatis
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) { 
                    JOptionPane.showMessageDialog(this, "Login Berhasil!");
                    this.dispose(); // Menutup frame login
                    new dashboard.DashboardFrame().setVisible(true); // Membuka dashboard
                } 
                else {  JOptionPane.showMessageDialog(this, "Username atau password salah!");   
                }
            } // rs otomatis ditutup di sini
        
        } // pstmt dan conn otomatis ditutup di sini
        catch (SQLException e) {   JOptionPane.showMessageDialog(this, "Error database: " + e.getMessage());
            e.printStackTrace(); // untuk debugging
        }
    }//GEN-LAST:event_btnMasukActionPerformed


    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new LoginFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMasuk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private config.RoundedPanel roundedPanel1;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
