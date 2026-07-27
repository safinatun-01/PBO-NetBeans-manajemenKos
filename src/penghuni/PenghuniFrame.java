package penghuni;

import java.sql.Connection;
import java.sql.PreparedStatement;
import config.DatabaseConnection;
import config.UIConfig;
import java.sql.ResultSet;
import java.awt.Window;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public final class PenghuniFrame extends javax.swing.JFrame {
    
    private dashboard.DashboardFrame dashboardFrame=null;
    private pembayaran.PembayaranFrame pembayaranFrame;
    private kamar.KamarFrame kamarFrame;
    private laporan.LaporanFrame laporanFrame;
    
    public PenghuniFrame() {
        initComponents();
        setLocationRelativeTo(null);
        settingDesign(); 
        loadData();
    }
    
    private void settingDesign() {
        UIConfig.setHeaderTabel(tblPenghuni);
    }
    
    public void loadData() {
        tampilDataPenghuni();
        totalPenghuni();
    }
    
    public void totalPenghuni() {
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmtTotal = conn.prepareStatement("SELECT COUNT(*) AS total FROM penghuni");
            ResultSet rsTotal = pstmtTotal.executeQuery();
            PreparedStatement pstmtAktif = conn.prepareStatement("SELECT COUNT(*) AS aktif FROM penghuni WHERE status_penghuni='Aktif'");
            ResultSet rsAktif = pstmtAktif.executeQuery();
            PreparedStatement pstmtKamarTerisi = conn.prepareStatement("SELECT COUNT(*) AS terisi FROM kamar WHERE status_kamar='Terisi'");
            ResultSet rsTerisi = pstmtKamarTerisi.executeQuery()) {
        
            if (rsTotal.next()) lblTotalPenghuniAngka.setText(rsTotal.getString("total"));
            if (rsAktif.next()) lblPenghuniAktif.setText(rsAktif.getString("aktif"));
            if (rsTerisi.next()) lblKamarTerisi.setText(rsTerisi.getString("terisi"));
        
        } 
        catch (SQLException e) {  JOptionPane.showMessageDialog(this, "Gagal memuat statistik: " + e.getMessage());
        }
    }
    
    private void tampilDataPenghuni() {
        DefaultTableModel model = (DefaultTableModel) tblPenghuni.getModel();
        model.setRowCount(0);
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM penghuni");
            ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id_penghuni"),
                    rs.getString("nama_penghuni"),
                    rs.getString("no_hp"),
                    rs.getString("jenis_kelamin"),
                    rs.getString("nomor_kamar"),
                    rs.getString("tanggal_masuk"),
                    rs.getString("status_penghuni")
                });
            }
        }
        catch (SQLException e) {    JOptionPane.showMessageDialog(null, "Gagal menampilkan data: " + e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMain = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnDashboard = new javax.swing.JButton();
        btnDataKamar = new javax.swing.JButton();
        btnDataPenghuni = new javax.swing.JButton();
        btnPembayaran = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        btnLaporan = new javax.swing.JButton();
        roundedPanel1 = new config.RoundedPanel();
        lblTotalPenghuniText = new javax.swing.JLabel();
        lblTotalPenghuniAngka = new javax.swing.JLabel();
        roundedPanel2 = new config.RoundedPanel();
        jLabel4 = new javax.swing.JLabel();
        lblPenghuniAktif = new javax.swing.JLabel();
        roundedPanel3 = new config.RoundedPanel();
        jLabel6 = new javax.swing.JLabel();
        lblKamarTerisi = new javax.swing.JLabel();
        panelDataPenghuni = new config.RoundedPanel();
        lblDaftarPenghuni = new javax.swing.JLabel();
        btnUpdate = new javax.swing.JButton();
        ScrollPanePenghuni = new javax.swing.JScrollPane();
        tblPenghuni = new javax.swing.JTable();
        btnHapusPenghuniu = new javax.swing.JButton();
        btnTambahPenghuni = new javax.swing.JButton();
        roundedPanel = new config.RoundedPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelMain.setBackground(new java.awt.Color(176, 206, 240));
        panelMain.setPreferredSize(new java.awt.Dimension(950, 540));

        jPanel2.setBackground(new java.awt.Color(90, 120, 150));
        jPanel2.setAutoscrolls(true);
        jPanel2.setPreferredSize(new java.awt.Dimension(120, 413));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 28)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("KOSKITA");

        btnDashboard.setBackground(new java.awt.Color(245, 247, 252));
        btnDashboard.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnDashboard.setForeground(new java.awt.Color(50, 60, 80));
        btnDashboard.setText("Dashboard");
        btnDashboard.setBorderPainted(false);
        btnDashboard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDashboard.setFocusPainted(false);
        btnDashboard.addActionListener(this::btnDashboardActionPerformed);

        btnDataKamar.setBackground(new java.awt.Color(245, 247, 252));
        btnDataKamar.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnDataKamar.setForeground(new java.awt.Color(50, 60, 80));
        btnDataKamar.setText("Data Kamar");
        btnDataKamar.setBorderPainted(false);
        btnDataKamar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDataKamar.setFocusPainted(false);
        btnDataKamar.addActionListener(this::btnDataKamarActionPerformed);

        btnDataPenghuni.setBackground(new java.awt.Color(176, 206, 240));
        btnDataPenghuni.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnDataPenghuni.setForeground(new java.awt.Color(36, 52, 71));
        btnDataPenghuni.setText("Data Penghuni");
        btnDataPenghuni.setBorderPainted(false);
        btnDataPenghuni.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDataPenghuni.setFocusPainted(false);
        btnDataPenghuni.setPreferredSize(new java.awt.Dimension(143, 40));

        btnPembayaran.setBackground(new java.awt.Color(245, 247, 252));
        btnPembayaran.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnPembayaran.setForeground(new java.awt.Color(50, 60, 80));
        btnPembayaran.setText("Pembayaran");
        btnPembayaran.setBorderPainted(false);
        btnPembayaran.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPembayaran.setFocusPainted(false);
        btnPembayaran.addActionListener(this::btnPembayaranActionPerformed);

        btnLogout.setBackground(new java.awt.Color(245, 247, 252));
        btnLogout.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(50, 60, 80));
        btnLogout.setText("Logout");
        btnLogout.setBorderPainted(false);
        btnLogout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogout.setFocusPainted(false);
        btnLogout.addActionListener(this::btnLogoutActionPerformed);

        btnLaporan.setBackground(new java.awt.Color(245, 247, 252));
        btnLaporan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnLaporan.setForeground(new java.awt.Color(36, 52, 71));
        btnLaporan.setText("Laporan");
        btnLaporan.setBorderPainted(false);
        btnLaporan.setFocusPainted(false);
        btnLaporan.addActionListener(this::btnLaporanActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnDataKamar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDashboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDataPenghuni, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPembayaran, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLaporan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(60, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(76, 76, 76))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(jLabel1)
                .addGap(45, 45, 45)
                .addComponent(btnDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDataKamar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDataPenghuni, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLaporan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        lblTotalPenghuniText.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        lblTotalPenghuniText.setForeground(new java.awt.Color(60, 70, 90));
        lblTotalPenghuniText.setText("Total Penghuni");

        lblTotalPenghuniAngka.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        lblTotalPenghuniAngka.setForeground(new java.awt.Color(90, 120, 150));
        lblTotalPenghuniAngka.setText("0");

        javax.swing.GroupLayout roundedPanel1Layout = new javax.swing.GroupLayout(roundedPanel1);
        roundedPanel1.setLayout(roundedPanel1Layout);
        roundedPanel1Layout.setHorizontalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(lblTotalPenghuniText))
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(lblTotalPenghuniAngka)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        roundedPanel1Layout.setVerticalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblTotalPenghuniText)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotalPenghuniAngka)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(60, 70, 90));
        jLabel4.setText("Penghuni AKtif");

        lblPenghuniAktif.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        lblPenghuniAktif.setForeground(new java.awt.Color(90, 120, 150));
        lblPenghuniAktif.setText("0");

        javax.swing.GroupLayout roundedPanel2Layout = new javax.swing.GroupLayout(roundedPanel2);
        roundedPanel2.setLayout(roundedPanel2Layout);
        roundedPanel2Layout.setHorizontalGroup(
            roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel2Layout.createSequentialGroup()
                .addGroup(roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel2Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel4))
                    .addGroup(roundedPanel2Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(lblPenghuniAktif)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        roundedPanel2Layout.setVerticalGroup(
            roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel2Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPenghuniAktif)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jLabel6.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(60, 70, 90));
        jLabel6.setText("Kamar Terisi");

        lblKamarTerisi.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        lblKamarTerisi.setForeground(new java.awt.Color(90, 120, 150));
        lblKamarTerisi.setText("0");

        javax.swing.GroupLayout roundedPanel3Layout = new javax.swing.GroupLayout(roundedPanel3);
        roundedPanel3.setLayout(roundedPanel3Layout);
        roundedPanel3Layout.setHorizontalGroup(
            roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel3Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(lblKamarTerisi)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel3Layout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(44, 44, 44))
        );
        roundedPanel3Layout.setVerticalGroup(
            roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel3Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblKamarTerisi)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        panelDataPenghuni.setBackground(new java.awt.Color(235, 242, 252));
        panelDataPenghuni.setPreferredSize(new java.awt.Dimension(300, 229));

        lblDaftarPenghuni.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        lblDaftarPenghuni.setForeground(new java.awt.Color(35, 45, 70));
        lblDaftarPenghuni.setText("Daftar Penghuni");

        btnUpdate.setBackground(new java.awt.Color(90, 120, 150));
        btnUpdate.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("Edit");
        btnUpdate.setBorderPainted(false);
        btnUpdate.addActionListener(this::btnUpdateActionPerformed);

        ScrollPanePenghuni.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        tblPenghuni.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblPenghuni.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nama Penghuni", "no HP", "Jenis Kelamin", "no Kamar", "Tanggal Masuk", "Status Penghuni"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPenghuni.setGridColor(new java.awt.Color(235, 240, 248));
        tblPenghuni.setMinimumSize(new java.awt.Dimension(75, 120));
        tblPenghuni.setRowHeight(30);
        tblPenghuni.setSelectionBackground(new java.awt.Color(215, 228, 255));
        tblPenghuni.setSelectionForeground(new java.awt.Color(35, 55, 90));
        tblPenghuni.setShowGrid(false);
        tblPenghuni.setShowHorizontalLines(true);
        tblPenghuni.setShowVerticalLines(true);
        ScrollPanePenghuni.setViewportView(tblPenghuni);
        if (tblPenghuni.getColumnModel().getColumnCount() > 0) {
            tblPenghuni.getColumnModel().getColumn(0).setResizable(false);
            tblPenghuni.getColumnModel().getColumn(1).setResizable(false);
            tblPenghuni.getColumnModel().getColumn(2).setResizable(false);
            tblPenghuni.getColumnModel().getColumn(3).setResizable(false);
            tblPenghuni.getColumnModel().getColumn(4).setResizable(false);
            tblPenghuni.getColumnModel().getColumn(5).setResizable(false);
            tblPenghuni.getColumnModel().getColumn(6).setResizable(false);
        }

        btnHapusPenghuniu.setBackground(new java.awt.Color(220, 55, 80));
        btnHapusPenghuniu.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        btnHapusPenghuniu.setForeground(new java.awt.Color(255, 255, 255));
        btnHapusPenghuniu.setText("🗑 Hapus");
        btnHapusPenghuniu.setBorderPainted(false);
        btnHapusPenghuniu.setFocusPainted(false);
        btnHapusPenghuniu.addActionListener(this::btnHapusPenghuniuActionPerformed);

        btnTambahPenghuni.setBackground(new java.awt.Color(90, 120, 150));
        btnTambahPenghuni.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTambahPenghuni.setForeground(new java.awt.Color(255, 255, 255));
        btnTambahPenghuni.setText("+ Tambah ");
        btnTambahPenghuni.setBorderPainted(false);
        btnTambahPenghuni.setFocusPainted(false);
        btnTambahPenghuni.addActionListener(this::btnTambahPenghuniActionPerformed);

        javax.swing.GroupLayout panelDataPenghuniLayout = new javax.swing.GroupLayout(panelDataPenghuni);
        panelDataPenghuni.setLayout(panelDataPenghuniLayout);
        panelDataPenghuniLayout.setHorizontalGroup(
            panelDataPenghuniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDataPenghuniLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(panelDataPenghuniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDataPenghuniLayout.createSequentialGroup()
                        .addComponent(lblDaftarPenghuni)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDataPenghuniLayout.createSequentialGroup()
                        .addGroup(panelDataPenghuniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelDataPenghuniLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnTambahPenghuni)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnUpdate)
                                .addGap(60, 60, 60)
                                .addComponent(btnHapusPenghuniu))
                            .addComponent(ScrollPanePenghuni, javax.swing.GroupLayout.DEFAULT_SIZE, 728, Short.MAX_VALUE))
                        .addGap(18, 18, 18))))
        );
        panelDataPenghuniLayout.setVerticalGroup(
            panelDataPenghuniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDataPenghuniLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lblDaftarPenghuni)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDataPenghuniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHapusPenghuniu)
                    .addComponent(btnUpdate)
                    .addComponent(btnTambahPenghuni))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ScrollPanePenghuni, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );

        roundedPanel.setBackground(new java.awt.Color(90, 120, 150));

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Data Penghuni");

        jLabel3.setBackground(new java.awt.Color(30, 41, 59));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Kelola informasi penghuni kos dengan mudah");

        javax.swing.GroupLayout roundedPanelLayout = new javax.swing.GroupLayout(roundedPanel);
        roundedPanel.setLayout(roundedPanelLayout);
        roundedPanelLayout.setHorizontalGroup(
            roundedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanelLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(roundedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addContainerGap(482, Short.MAX_VALUE))
        );
        roundedPanelLayout.setVerticalGroup(
            roundedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanelLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout panelMainLayout = new javax.swing.GroupLayout(panelMain);
        panelMain.setLayout(panelMainLayout);
        panelMainLayout.setHorizontalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelDataPenghuni, javax.swing.GroupLayout.PREFERRED_SIZE, 768, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addComponent(roundedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(131, 131, 131)
                        .addComponent(roundedPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(125, 125, 125)
                        .addComponent(roundedPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMainLayout.createSequentialGroup()
                .addGap(263, 263, 263)
                .addComponent(roundedPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(203, 203, 203))
        );
        panelMainLayout.setVerticalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addComponent(roundedPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundedPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(roundedPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(roundedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22)
                .addComponent(panelDataPenghuni, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMain, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1080, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMain, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDashboardActionPerformed
    if (dashboardFrame == null) dashboardFrame = new dashboard.DashboardFrame();
    dashboardFrame.setVisible(true);
    setVisible(false);
    }//GEN-LAST:event_btnDashboardActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        Window[] windows = Window.getWindows();
        for (Window window : windows) window.dispose();
        new Login.LoginFrame().setVisible(true);
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnDataKamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDataKamarActionPerformed
        if (kamarFrame == null) kamarFrame = new kamar.KamarFrame();
        kamarFrame.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnDataKamarActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
                int baris = tblPenghuni.getSelectedRow();   //ambil baris yang sedang dipilih user
                if(baris == -1){    
                    JOptionPane.showMessageDialog(null, "Pilih data penghuni terlebih dahulu!");
                    return;
                }
        
                String id= tblPenghuni.getValueAt(baris,0).toString();
                String nama= tblPenghuni.getValueAt(baris,1).toString();
                String no_hp= tblPenghuni.getValueAt(baris,2).toString();
                String jenis_kel= tblPenghuni.getValueAt(baris,3).toString();
                String no_kamar= tblPenghuni.getValueAt(baris,4).toString();
                String tgl_masuk= tblPenghuni.getValueAt(baris,5).toString();
                String status_penghuni= tblPenghuni.getValueAt(baris,6).toString();
        
                // Data dikirim ke UpdatePenghuniFrame
                UpdatePenghuniFrame edit= new UpdatePenghuniFrame(this, id, nama, no_hp, jenis_kel, no_kamar, tgl_masuk, status_penghuni);
                edit.setVisible(true);  //menampilkan jendela updatePenghuni
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnHapusPenghuniuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusPenghuniuActionPerformed
        int baris = tblPenghuni.getSelectedRow();
        if (baris == -1) {
            JOptionPane.showMessageDialog(null, "Pilih data dulu!");
            return;
        }

        int konfirmasi = JOptionPane.showConfirmDialog(null, "Yakin ingin menghapus?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (konfirmasi == JOptionPane.YES_OPTION) {
            String id = tblPenghuni.getValueAt(baris, 0).toString();
            String noKamar = tblPenghuni.getValueAt(baris, 4).toString(); 

            // Variabel penanda jika proses di dalam database berhasil
            boolean suksesHapus = false;
            try (Connection conn = DatabaseConnection.getConnection()) {
                conn.setAutoCommit(false); 
                try {
                    
                    // Hapus data dari tabel penghuni
                    try (PreparedStatement pstmtHapus = conn.prepareStatement("DELETE FROM penghuni WHERE id_penghuni=?")) {
                        pstmtHapus.setString(1, id);
                        pstmtHapus.executeUpdate();   
                    }

                    // Update status kamar jadi Kosong di tabel kamar
                    try (PreparedStatement pstmtKamar = conn.prepareStatement("UPDATE kamar SET status_kamar='Kosong' WHERE nomor_kamar=?")) {
                        pstmtKamar.setString(1, noKamar);
                        pstmtKamar.executeUpdate();
                        } 
                    conn.commit(); 
                    suksesHapus = true; // Tandai bahwa proses database sukses 100%
                } 
                catch (SQLException ex) { conn.rollback(); 
                    JOptionPane.showMessageDialog(null, "Gagal hapus, data dikembalikan: " + ex.getMessage());
                } 
                finally {
                conn.setAutoCommit(true);  }
            } 
            catch (SQLException e) { JOptionPane.showMessageDialog(null, "Error Koneksi Database: " + e.getMessage()); 
        }

        // Jika hapus di database sukses, tampilkan notifikasi dan refresh tabel di luar blok koneksi
        if (suksesHapus) { JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
            tampilDataPenghuni();
            totalPenghuni(); 
        } }
    }//GEN-LAST:event_btnHapusPenghuniuActionPerformed

    private void btnPembayaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPembayaranActionPerformed
        if (pembayaranFrame == null) pembayaranFrame = new pembayaran.PembayaranFrame();
        pembayaranFrame.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnPembayaranActionPerformed

    private void btnTambahPenghuniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahPenghuniActionPerformed
        new penghuni.TambahPenghuniFrame(this).setVisible(true);
    }//GEN-LAST:event_btnTambahPenghuniActionPerformed

    private void btnLaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaporanActionPerformed
        if (laporanFrame == null) laporanFrame = new laporan.LaporanFrame();
        laporanFrame.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnLaporanActionPerformed

    public static void main(String args[]) {
         java.awt.EventQueue.invokeLater(() -> new PenghuniFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ScrollPanePenghuni;
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnDataKamar;
    private javax.swing.JButton btnDataPenghuni;
    private javax.swing.JButton btnHapusPenghuniu;
    private javax.swing.JButton btnLaporan;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnPembayaran;
    private javax.swing.JButton btnTambahPenghuni;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblDaftarPenghuni;
    private javax.swing.JLabel lblKamarTerisi;
    private javax.swing.JLabel lblPenghuniAktif;
    private javax.swing.JLabel lblTotalPenghuniAngka;
    private javax.swing.JLabel lblTotalPenghuniText;
    private config.RoundedPanel panelDataPenghuni;
    private javax.swing.JPanel panelMain;
    private config.RoundedPanel roundedPanel;
    private config.RoundedPanel roundedPanel1;
    private config.RoundedPanel roundedPanel2;
    private config.RoundedPanel roundedPanel3;
    private javax.swing.JTable tblPenghuni;
    // End of variables declaration//GEN-END:variables

}
