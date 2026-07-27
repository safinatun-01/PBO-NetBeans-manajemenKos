package kamar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import config.DatabaseConnection;
import config.UIConfig;
import java.awt.Color;
import java.awt.Window;
import javax.swing.JOptionPane;
import java.sql.SQLException;


public class KamarFrame extends javax.swing.JFrame {
    private dashboard.DashboardFrame dashboardFrame = null;
    private penghuni.PenghuniFrame penghuniFrame;
    private pembayaran.PembayaranFrame pembayaranFrame;
    private laporan.LaporanFrame laporanFrame;
    
    public KamarFrame() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        settingDesign();
        loadData();
    }

    private void settingDesign() {
        // Style header tabel
        UIConfig.setHeaderTabel(tblKamar);
        // Style field pencarian
        txtCariKamar.setForeground(new Color(150,150,170)); // Mengubah warna placeholder menjadi abu
    }

    private void tampilDataKamar() {
        DefaultTableModel model = (DefaultTableModel) tblKamar.getModel();
        model.setRowCount(0);
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM kamar");
            ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Object[] row = {
                    rs.getString("id_kamar"),
                    rs.getString("nomor_kamar"),
                    rs.getString("tipe_kamar"),
                    rs.getString("harga"),
                    rs.getString("status_kamar")
                };
                model.addRow(row);
            }
        } 
        catch (SQLException e) {    JOptionPane.showMessageDialog(null, "Gagal menampilkan data: " + e.getMessage());
    }
}
    
private void tampilStatistikKamar() {
    try (Connection conn = DatabaseConnection.getConnection()) {
        // Total kamar
        String sqlTotal = "SELECT COUNT(*) AS total FROM kamar";
        try (PreparedStatement pstmt = conn.prepareStatement(sqlTotal);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) lblTotalKamarAngka.setText(rs.getString("total"));
        }
        // Kamar kosong
        String sqlKosong = "SELECT COUNT(*) AS kosong FROM kamar WHERE status_kamar = 'Kosong'";
        try (PreparedStatement pstmt = conn.prepareStatement(sqlKosong);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) lblKamarKosongAngka.setText(rs.getString("kosong"));
        }
        // Kamar terisi
        String sqlTerisi = "SELECT COUNT(*) AS terisi FROM kamar WHERE status_kamar = 'Terisi'";
        try (PreparedStatement pstmt = conn.prepareStatement(sqlTerisi);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) lblKamarTerisiAngka.setText(rs.getString("terisi"));
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Gagal menampilkan statistik: " + e.getMessage());
    }
}
    
private void cariKamar() {
    DefaultTableModel model = (DefaultTableModel) tblKamar.getModel();
    model.setRowCount(0);
    String keyword = txtCariKamar.getText().trim();
    if (keyword.isEmpty()) {
        loadData(); // jika keyword kosong, tampilkan semua data
        return;
    }
    String sql = "SELECT * FROM kamar WHERE nomor_kamar LIKE ?";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, "%" + keyword + "%");
        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("id_kamar"),
                    rs.getString("nomor_kamar"),
                    rs.getString("tipe_kamar"),
                    rs.getString("harga"),
                    rs.getString("status_kamar")
                };
                model.addRow(row);
            }
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
}
    
    public void loadData() {
    tampilDataKamar();
    tampilStatistikKamar();
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        panelMain = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnDashboard = new javax.swing.JButton();
        btnDataKamar = new javax.swing.JButton();
        btnDataPenghuni = new javax.swing.JButton();
        btnPembayaran = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnLaporan = new javax.swing.JButton();
        roundedPanel1 = new config.RoundedPanel();
        lblTotalKamarText = new javax.swing.JLabel();
        lblTotalKamarAngka = new javax.swing.JLabel();
        roundedPanel2 = new config.RoundedPanel();
        lblKamarKosongText = new javax.swing.JLabel();
        lblKamarKosongAngka = new javax.swing.JLabel();
        roundedPanel3 = new config.RoundedPanel();
        lblKamarTerisiText = new javax.swing.JLabel();
        lblKamarTerisiAngka = new javax.swing.JLabel();
        panelDataKamar = new config.RoundedPanel();
        jLabel4 = new javax.swing.JLabel();
        txtCariKamar = new javax.swing.JTextField();
        btnUpdateKamar = new javax.swing.JButton();
        btnHapusKamar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblKamar = new javax.swing.JTable();
        btnTambahKamar = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        roundedPanel = new config.RoundedPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelMain.setBackground(new java.awt.Color(176, 206, 240));

        jPanel2.setBackground(new java.awt.Color(90, 120, 150));
        jPanel2.setAutoscrolls(true);
        jPanel2.setPreferredSize(new java.awt.Dimension(120, 413));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 28)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("KOSKITA");

        btnDashboard.setBackground(new java.awt.Color(235, 242, 252));
        btnDashboard.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnDashboard.setForeground(new java.awt.Color(70, 90, 110));
        btnDashboard.setText("Dashboard");
        btnDashboard.setBorderPainted(false);
        btnDashboard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDashboard.setFocusPainted(false);
        btnDashboard.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnDashboard.setPreferredSize(new java.awt.Dimension(143, 29));
        btnDashboard.addActionListener(this::btnDashboardActionPerformed);

        btnDataKamar.setBackground(new java.awt.Color(176, 206, 240));
        btnDataKamar.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnDataKamar.setForeground(new java.awt.Color(36, 52, 71));
        btnDataKamar.setText("Data Kamar");
        btnDataKamar.setBorderPainted(false);
        btnDataKamar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDataKamar.setFocusPainted(false);
        btnDataKamar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        btnDataPenghuni.setBackground(new java.awt.Color(235, 242, 252));
        btnDataPenghuni.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnDataPenghuni.setForeground(new java.awt.Color(70, 90, 110));
        btnDataPenghuni.setText("Data Penghuni");
        btnDataPenghuni.setBorderPainted(false);
        btnDataPenghuni.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDataPenghuni.setFocusPainted(false);
        btnDataPenghuni.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnDataPenghuni.addActionListener(this::btnDataPenghuniActionPerformed);

        btnPembayaran.setBackground(new java.awt.Color(235, 242, 252));
        btnPembayaran.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnPembayaran.setForeground(new java.awt.Color(70, 90, 110));
        btnPembayaran.setText("Pembayaran");
        btnPembayaran.setBorderPainted(false);
        btnPembayaran.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPembayaran.setFocusPainted(false);
        btnPembayaran.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnPembayaran.addActionListener(this::btnPembayaranActionPerformed);

        btnLogout.setBackground(new java.awt.Color(235, 242, 252));
        btnLogout.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(70, 90, 110));
        btnLogout.setText("Logout");
        btnLogout.setBorderPainted(false);
        btnLogout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogout.setFocusPainted(false);
        btnLogout.addActionListener(this::btnLogoutActionPerformed);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Sistem Manajemen Kos");

        btnLaporan.setBackground(new java.awt.Color(235, 242, 252));
        btnLaporan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnLaporan.setForeground(new java.awt.Color(70, 90, 110));
        btnLaporan.setText("Laporan");
        btnLaporan.setBorderPainted(false);
        btnLaporan.setFocusPainted(false);
        btnLaporan.addActionListener(this::btnLaporanActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(jLabel1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btnLogout, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnLaporan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnPembayaran, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnDataPenghuni, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnDataKamar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDataKamar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDataPenghuni, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLaporan, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        roundedPanel1.setBackground(new java.awt.Color(248, 250, 252));
        roundedPanel1.setForeground(new java.awt.Color(36, 52, 71));
        roundedPanel1.setPreferredSize(new java.awt.Dimension(170, 95));

        lblTotalKamarText.setBackground(new java.awt.Color(255, 255, 255));
        lblTotalKamarText.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        lblTotalKamarText.setForeground(new java.awt.Color(36, 52, 71));
        lblTotalKamarText.setText("Total Kamar");

        lblTotalKamarAngka.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        lblTotalKamarAngka.setForeground(new java.awt.Color(90, 120, 150));
        lblTotalKamarAngka.setText("-");

        javax.swing.GroupLayout roundedPanel1Layout = new javax.swing.GroupLayout(roundedPanel1);
        roundedPanel1.setLayout(roundedPanel1Layout);
        roundedPanel1Layout.setHorizontalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel1Layout.createSequentialGroup()
                        .addComponent(lblTotalKamarAngka)
                        .addGap(82, 82, 82))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel1Layout.createSequentialGroup()
                        .addComponent(lblTotalKamarText)
                        .addGap(48, 48, 48))))
        );
        roundedPanel1Layout.setVerticalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(lblTotalKamarText)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotalKamarAngka)
                .addContainerGap())
        );

        roundedPanel2.setBackground(new java.awt.Color(248, 250, 252));
        roundedPanel2.setPreferredSize(new java.awt.Dimension(170, 95));

        lblKamarKosongText.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        lblKamarKosongText.setForeground(new java.awt.Color(36, 52, 71));
        lblKamarKosongText.setText("Kamar Kosong");

        lblKamarKosongAngka.setBackground(new java.awt.Color(255, 255, 255));
        lblKamarKosongAngka.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        lblKamarKosongAngka.setForeground(new java.awt.Color(90, 120, 150));
        lblKamarKosongAngka.setText("0");

        javax.swing.GroupLayout roundedPanel2Layout = new javax.swing.GroupLayout(roundedPanel2);
        roundedPanel2.setLayout(roundedPanel2Layout);
        roundedPanel2Layout.setHorizontalGroup(
            roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel2Layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addGroup(roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel2Layout.createSequentialGroup()
                        .addComponent(lblKamarKosongText)
                        .addGap(40, 40, 40))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel2Layout.createSequentialGroup()
                        .addComponent(lblKamarKosongAngka)
                        .addGap(79, 79, 79))))
        );
        roundedPanel2Layout.setVerticalGroup(
            roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel2Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(lblKamarKosongText)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblKamarKosongAngka)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        roundedPanel3.setBackground(new java.awt.Color(248, 250, 252));
        roundedPanel3.setForeground(new java.awt.Color(36, 52, 71));
        roundedPanel3.setFocusCycleRoot(true);
        roundedPanel3.setPreferredSize(new java.awt.Dimension(220, 110));

        lblKamarTerisiText.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        lblKamarTerisiText.setForeground(new java.awt.Color(36, 52, 71));
        lblKamarTerisiText.setText("Kamar Terisi");

        lblKamarTerisiAngka.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        lblKamarTerisiAngka.setForeground(new java.awt.Color(90, 120, 150));
        lblKamarTerisiAngka.setText("0");

        javax.swing.GroupLayout roundedPanel3Layout = new javax.swing.GroupLayout(roundedPanel3);
        roundedPanel3.setLayout(roundedPanel3Layout);
        roundedPanel3Layout.setHorizontalGroup(
            roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel3Layout.createSequentialGroup()
                        .addComponent(lblKamarTerisiAngka)
                        .addGap(83, 83, 83))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel3Layout.createSequentialGroup()
                        .addComponent(lblKamarTerisiText)
                        .addGap(48, 48, 48))))
        );
        roundedPanel3Layout.setVerticalGroup(
            roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel3Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(lblKamarTerisiText)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblKamarTerisiAngka)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        panelDataKamar.setBackground(new java.awt.Color(235, 242, 252));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(36, 52, 71));
        jLabel4.setText("Daftar Kamar");

        txtCariKamar.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        txtCariKamar.setForeground(new java.awt.Color(90, 120, 150));
        txtCariKamar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCariKamar.setText("🔍 Cari nomor kamar");
        txtCariKamar.setBorder(null);
        txtCariKamar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCariKamarFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCariKamarFocusLost(evt);
            }
        });
        txtCariKamar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCariKamarKeyReleased(evt);
            }
        });

        btnUpdateKamar.setBackground(new java.awt.Color(90, 120, 150));
        btnUpdateKamar.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        btnUpdateKamar.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdateKamar.setText("✎ Edit");
        btnUpdateKamar.setBorderPainted(false);
        btnUpdateKamar.addActionListener(this::btnUpdateKamarActionPerformed);

        btnHapusKamar.setBackground(new java.awt.Color(220, 55, 80));
        btnHapusKamar.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        btnHapusKamar.setForeground(new java.awt.Color(255, 255, 255));
        btnHapusKamar.setText("🗑 Hapus");
        btnHapusKamar.setBorderPainted(false);
        btnHapusKamar.setFocusPainted(false);
        btnHapusKamar.addActionListener(this::btnHapusKamarActionPerformed);

        tblKamar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblKamar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Nomor Kamar", "Tipe", "Harga", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKamar.setGridColor(new java.awt.Color(235, 240, 248));
        tblKamar.setRowHeight(30);
        tblKamar.setSelectionBackground(new java.awt.Color(215, 228, 255));
        tblKamar.setSelectionForeground(new java.awt.Color(35, 55, 90));
        tblKamar.setShowHorizontalLines(true);
        tblKamar.setShowVerticalLines(true);
        jScrollPane2.setViewportView(tblKamar);
        if (tblKamar.getColumnModel().getColumnCount() > 0) {
            tblKamar.getColumnModel().getColumn(0).setResizable(false);
            tblKamar.getColumnModel().getColumn(1).setResizable(false);
            tblKamar.getColumnModel().getColumn(2).setResizable(false);
            tblKamar.getColumnModel().getColumn(3).setResizable(false);
            tblKamar.getColumnModel().getColumn(4).setResizable(false);
        }

        javax.swing.GroupLayout panelDataKamarLayout = new javax.swing.GroupLayout(panelDataKamar);
        panelDataKamar.setLayout(panelDataKamarLayout);
        panelDataKamarLayout.setHorizontalGroup(
            panelDataKamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDataKamarLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(panelDataKamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDataKamarLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDataKamarLayout.createSequentialGroup()
                        .addGroup(panelDataKamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 717, Short.MAX_VALUE)
                            .addGroup(panelDataKamarLayout.createSequentialGroup()
                                .addComponent(txtCariKamar, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnUpdateKamar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnHapusKamar)))
                        .addGap(30, 30, 30))))
        );
        panelDataKamarLayout.setVerticalGroup(
            panelDataKamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDataKamarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelDataKamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDataKamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnUpdateKamar)
                        .addComponent(btnHapusKamar))
                    .addComponent(txtCariKamar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75))
        );

        btnTambahKamar.setBackground(new java.awt.Color(90, 120, 150));
        btnTambahKamar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTambahKamar.setForeground(new java.awt.Color(255, 255, 255));
        btnTambahKamar.setText("+ Tambah ");
        btnTambahKamar.setBorderPainted(false);
        btnTambahKamar.addActionListener(this::btnTambahKamarActionPerformed);

        btnRefresh.setBackground(new java.awt.Color(232, 240, 255));
        btnRefresh.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        btnRefresh.setForeground(new java.awt.Color(70, 100, 190));
        btnRefresh.setText("⟳ Refresh");
        btnRefresh.setBorderPainted(false);
        btnRefresh.setFocusPainted(false);
        btnRefresh.addActionListener(this::btnRefreshActionPerformed);

        roundedPanel.setBackground(new java.awt.Color(90, 120, 150));
        roundedPanel.setPreferredSize(new java.awt.Dimension(357, 90));

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Data Kamar");

        jLabel3.setBackground(new java.awt.Color(30, 41, 59));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Kelola informasi Pembayaran kos dengan mudah");

        javax.swing.GroupLayout roundedPanelLayout = new javax.swing.GroupLayout(roundedPanel);
        roundedPanel.setLayout(roundedPanelLayout);
        roundedPanelLayout.setHorizontalGroup(
            roundedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(roundedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6))
                .addContainerGap(504, Short.MAX_VALUE))
        );
        roundedPanelLayout.setVerticalGroup(
            roundedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanelLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout panelMainLayout = new javax.swing.GroupLayout(panelMain);
        panelMain.setLayout(panelMainLayout);
        panelMainLayout.setHorizontalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelMainLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelDataKamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelMainLayout.createSequentialGroup()
                                .addComponent(roundedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(131, 131, 131)
                                .addComponent(roundedPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(93, 93, 93)
                                .addComponent(roundedPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(roundedPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 816, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelMainLayout.createSequentialGroup()
                                .addComponent(btnRefresh)
                                .addGap(18, 18, 18)
                                .addComponent(btnTambahKamar)
                                .addGap(53, 53, 53))))))
        );
        panelMainLayout.setVerticalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addComponent(roundedPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambahKamar)
                    .addComponent(btnRefresh))
                .addGap(20, 20, 20)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundedPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundedPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(panelDataKamar, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDashboardActionPerformed
        if (dashboardFrame == null) dashboardFrame = new dashboard.DashboardFrame();   
        dashboardFrame.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnDashboardActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // Tutup semua window yang terbuka
        Window[] windows = Window.getWindows();
        for (Window window : windows) window.dispose();
        new Login.LoginFrame().setVisible(true);
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnTambahKamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahKamarActionPerformed
        new TambahKamarFrame(this).setVisible(true);
    }//GEN-LAST:event_btnTambahKamarActionPerformed

    private void btnUpdateKamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateKamarActionPerformed
        int baris = tblKamar.getSelectedRow();
        if(baris == -1){
            JOptionPane.showMessageDialog(null, "Pilih data kamar terlebih dahulu!");
            return;
        }
        
        String id= tblKamar.getValueAt(baris,0).toString();
        String nomor= tblKamar.getValueAt(baris,1).toString();
        String tipe= tblKamar.getValueAt(baris,2).toString();
        String harga= tblKamar.getValueAt(baris,3).toString();
        String status= tblKamar.getValueAt(baris,4).toString();
        
        new UpdateKamarFrame(this, id, nomor, tipe, harga, status).setVisible(true);
    }//GEN-LAST:event_btnUpdateKamarActionPerformed

    private void btnHapusKamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusKamarActionPerformed
        // TODO add your handling code here:
        int baris= tblKamar.getSelectedRow();
        if(baris==-1){
            JOptionPane.showMessageDialog(null,  "Pilih data dulu!");
            return;
        }
        // Ambil status kamar dari tabel
        String status = tblKamar.getValueAt(baris, 4).toString();
        if (status.equalsIgnoreCase("Terisi")) {
            JOptionPane.showMessageDialog(null, "Kamar sedang terisi oleh penghuni!\nHapus penghuni terlebih dahulu.",
            "Tidak bisa hapus", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int konfirmasi= JOptionPane.showConfirmDialog(null, "Yakin ingin menghapus?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if(konfirmasi==JOptionPane.YES_OPTION){
            try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement("DELETE FROM kamar WHERE id_kamar=?")) {
                String id = tblKamar.getValueAt(baris, 0).toString();
                pstmt.setString(1, id);
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
                tampilDataKamar();
                tampilStatistikKamar();
            } 
            catch (Exception e) {   JOptionPane.showMessageDialog(null, "Gagal hapus : " + e.getMessage());
            }
        }     
    }//GEN-LAST:event_btnHapusKamarActionPerformed

    private void txtCariKamarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKamarKeyReleased
        cariKamar();    // Jalankan pencarian otomatis
    }//GEN-LAST:event_txtCariKamarKeyReleased

    private void txtCariKamarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCariKamarFocusGained
        if(txtCariKamar.getText().equals("🔍 Cari nomor kamar")){
            txtCariKamar.setText("");   // Menghapus isi textfield, supaya user langsung mengetik
            txtCariKamar.setForeground(new Color(60,60,60));
        }
    }//GEN-LAST:event_txtCariKamarFocusGained

    private void txtCariKamarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCariKamarFocusLost
        if (txtCariKamar.getText().trim().isEmpty()) {
            txtCariKamar.setText("🔍 Cari nomor kamar");
            txtCariKamar.setForeground(new Color(150,150,170));
        }    
    }//GEN-LAST:event_txtCariKamarFocusLost

    private void btnDataPenghuniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDataPenghuniActionPerformed
         if (penghuniFrame == null) penghuniFrame = new penghuni.PenghuniFrame();
        penghuniFrame.setVisible(true);
        setVisible(false);     
    }//GEN-LAST:event_btnDataPenghuniActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        loadData();
        txtCariKamar.setText("🔍 Cari nomor kamar");
        txtCariKamar.setForeground(new Color(150,150,170));
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnPembayaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPembayaranActionPerformed
        if (pembayaranFrame == null) pembayaranFrame = new pembayaran.PembayaranFrame(); 
        pembayaranFrame.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnPembayaranActionPerformed

    private void btnLaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaporanActionPerformed
        if (laporanFrame == null) laporanFrame = new laporan.LaporanFrame();
        laporanFrame.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnLaporanActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new KamarFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnDataKamar;
    private javax.swing.JButton btnDataPenghuni;
    private javax.swing.JButton btnHapusKamar;
    private javax.swing.JButton btnLaporan;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnPembayaran;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnTambahKamar;
    private javax.swing.JButton btnUpdateKamar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblKamarKosongAngka;
    private javax.swing.JLabel lblKamarKosongText;
    private javax.swing.JLabel lblKamarTerisiAngka;
    private javax.swing.JLabel lblKamarTerisiText;
    private javax.swing.JLabel lblTotalKamarAngka;
    private javax.swing.JLabel lblTotalKamarText;
    private config.RoundedPanel panelDataKamar;
    private javax.swing.JPanel panelMain;
    private config.RoundedPanel roundedPanel;
    private config.RoundedPanel roundedPanel1;
    private config.RoundedPanel roundedPanel2;
    private config.RoundedPanel roundedPanel3;
    private javax.swing.JTable tblKamar;
    private javax.swing.JTextField txtCariKamar;
    // End of variables declaration//GEN-END:variables
}
