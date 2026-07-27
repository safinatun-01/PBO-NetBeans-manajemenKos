package pembayaran;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import config.DatabaseConnection;
import config.UIConfig;
import java.awt.Color;
import java.awt.Window;
import javax.swing.JOptionPane;

public class PembayaranFrame extends javax.swing.JFrame {
    private DefaultTableModel modelTabel;
    private dashboard.DashboardFrame dashboardFrame = null;
    private penghuni.PenghuniFrame penghuniFrame;
    private kamar.KamarFrame kamarFrame;
    private laporan.LaporanFrame laporanFrame;
    
    public PembayaranFrame() {
        initComponents();
        setLocationRelativeTo(null);
        settingTable();
        loadData(); // isi tabel
        txtCari.setText("🔍 Cari nama / kamar / bulan"); 
    }
    
    private void settingTable() {
        modelTabel = new DefaultTableModel(new String[]{"ID Pembayaran ", "ID Penghuni ", "Penghuni","Kamar","Tagihan Bulan","Tgl Bayar","Jumlah","Status"}, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
            }
        };
        
        tblPembayaran.setModel(modelTabel);
        // Sembunyikan kolom ID Penghuni (indeks 1)
        tblPembayaran.getColumnModel().getColumn(1).setMinWidth(0);
        tblPembayaran.getColumnModel().getColumn(1).setMaxWidth(0);
        tblPembayaran.getColumnModel().getColumn(1).setWidth(0);
        // Styling header
        UIConfig.setHeaderTabel(tblPembayaran);
    }
    
    public void loadData() {    
        modelTabel.setRowCount(0); // hapus isi lama
        String sql = "SELECT pembayaran.id_pembayaran, pembayaran.id_penghuni, "
               + "penghuni.nama_penghuni, penghuni.nomor_kamar, "
               + "pembayaran.tagihan_bulan, pembayaran.tanggal_bayar, pembayaran.jumlah, pembayaran.status "
               + "FROM pembayaran "
               + "JOIN penghuni ON pembayaran.id_penghuni = penghuni.id_penghuni "
               + "ORDER BY pembayaran.tanggal_bayar DESC";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
         
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("id_pembayaran"), 
                    rs.getInt("id_penghuni"), 
                    rs.getString("nama_penghuni"),
                    rs.getString("nomor_kamar"), 
                    rs.getString("tagihan_bulan"), 
                    rs.getDate("tanggal_bayar"), 
                    rs.getInt("jumlah"),
                    rs.getString("status") 
                };
                modelTabel.addRow(row); 
            }
        } 
        catch (SQLException e) {  JOptionPane.showMessageDialog(this, "Gagal load data: " + e.getMessage()); 
    }
    // Panggil updateStatistik() di luar blok try resource database agar datanya ter-update setelah tabel terisi sempurna
    updateStatistik();  
    }
    
    // Pencarian berdasarkan nama penghuni, nomor kamar, atau bulan tagihan
    private void cariData() {
        String keyword = txtCari.getText().trim();
        if (keyword.isEmpty() || keyword.equals("🔍 Cari nama / kamar / bulan")) { loadData(); return; }
        modelTabel.setRowCount(0);
        String sql = "SELECT pembayaran.id_pembayaran, pembayaran.id_penghuni, penghuni.nama_penghuni, penghuni.nomor_kamar, "
           + "pembayaran.tagihan_bulan, pembayaran.tanggal_bayar, pembayaran.jumlah, pembayaran.status "
           + "FROM pembayaran "
           + "JOIN penghuni ON pembayaran.id_penghuni = penghuni.id_penghuni "
           + "WHERE penghuni.nama_penghuni LIKE ? "
           + "OR penghuni.nomor_kamar LIKE ? "
           + "OR pembayaran.tagihan_bulan LIKE ? "
           + "ORDER BY pembayaran.tanggal_bayar DESC";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
                String like = "%" + keyword + "%";
                pstmt.setString(1, like); 
                pstmt.setString(2, like); 
                pstmt.setString(3, like); 
                
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        modelTabel.addRow(new Object[]{
                            rs.getInt("id_pembayaran"),
                            rs.getInt("id_penghuni"),
                            rs.getString("nama_penghuni"),
                            rs.getString("nomor_kamar"),
                            rs.getString("tagihan_bulan"),
                            rs.getDate("tanggal_bayar"),
                            rs.getInt("jumlah"),
                            rs.getString("status")
                        });
                    } }
        } 
        catch (SQLException e) { JOptionPane.showMessageDialog(this, "Gagal cari: " + e.getMessage()); } 
        updateStatistik();
    }
    
    private void updateStatistik() {
        String sqlTotal = "SELECT SUM(jumlah) AS total FROM pembayaran";
        // Menambahkan filter YEAR(tanggal_bayar) = YEAR(CURRENT_DATE)
        String sqlBulanIni = "SELECT SUM(jumlah) AS total FROM pembayaran " +
                     "WHERE MONTH(tanggal_bayar) = MONTH(CURRENT_DATE) " +
                     "AND YEAR(tanggal_bayar) = YEAR(CURRENT_DATE)";
        String sqlLunas = "SELECT COUNT(*) AS lunas FROM pembayaran WHERE status = 'Lunas'";
    
        try (Connection conn = DatabaseConnection.getConnection()) {
            // Total pemasukan keseluruhan
            try (PreparedStatement stmt = conn.prepareStatement(sqlTotal);
                 ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    long total = rs.getLong("total");
                    lblTotalPemasukan.setText(formatRupiah(total)); 
                }
            }
            
            // Total pemasukan murni bulan ini
            try (PreparedStatement stmt = conn.prepareStatement(sqlBulanIni);
                 ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    long totalBulanIni = rs.getLong("total");
                    lblBulanIni.setText(formatRupiah(totalBulanIni)); // Nilai masuk ke card tengah (Bulan Ini)
                }
            }
        
            // Jumlah transaksi Lunas
            try (PreparedStatement stmt = conn.prepareStatement(sqlLunas);
                 ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int lunasCount = rs.getInt("lunas");
                    lblJumlahLunas.setText(String.valueOf(lunasCount));
                }
            }
        } catch (SQLException e) { 
            JOptionPane.showMessageDialog(this, "Gagal memuat statistik: " + e.getMessage());
        }
    }
    
    private String formatRupiah(long nilai) {
        return String.format("Rp %,d", nilai).replace(',', '.');
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMain = new javax.swing.JPanel();
        panelSidebar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnDashboard = new javax.swing.JButton();
        btnDataKamar = new javax.swing.JButton();
        btnDataPenghuni = new javax.swing.JButton();
        btnPembayaran = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        btnLaporan = new javax.swing.JButton();
        roundedPanel1 = new config.RoundedPanel();
        lblTotalPenghuniText = new javax.swing.JLabel();
        lblTotalPemasukan = new javax.swing.JLabel();
        roundedPanel2 = new config.RoundedPanel();
        jLabel4 = new javax.swing.JLabel();
        lblBulanIni = new javax.swing.JLabel();
        roundedPanel3 = new config.RoundedPanel();
        jLabel6 = new javax.swing.JLabel();
        lblJumlahLunas = new javax.swing.JLabel();
        panelPembayaran = new config.RoundedPanel();
        lblDaftarPenghuni = new javax.swing.JLabel();
        ScrollPanePembayaran = new javax.swing.JScrollPane();
        tblPembayaran = new javax.swing.JTable();
        txtCari = new javax.swing.JTextField();
        btnUpdate = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnTambah = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        roundedPanel = new config.RoundedPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelMain.setBackground(new java.awt.Color(176, 206, 240));
        panelMain.setPreferredSize(new java.awt.Dimension(950, 540));

        panelSidebar.setBackground(new java.awt.Color(90, 120, 150));
        panelSidebar.setAutoscrolls(true);
        panelSidebar.setPreferredSize(new java.awt.Dimension(120, 413));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
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

        btnDataPenghuni.setBackground(new java.awt.Color(245, 247, 252));
        btnDataPenghuni.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnDataPenghuni.setForeground(new java.awt.Color(36, 52, 71));
        btnDataPenghuni.setText("Data Penghuni");
        btnDataPenghuni.setBorderPainted(false);
        btnDataPenghuni.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDataPenghuni.setFocusPainted(false);
        btnDataPenghuni.setPreferredSize(new java.awt.Dimension(125, 29));
        btnDataPenghuni.addActionListener(this::btnDataPenghuniActionPerformed);

        btnPembayaran.setBackground(new java.awt.Color(176, 206, 240));
        btnPembayaran.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnPembayaran.setForeground(new java.awt.Color(36, 52, 71));
        btnPembayaran.setText("Pembayaran");
        btnPembayaran.setBorderPainted(false);
        btnPembayaran.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPembayaran.setFocusPainted(false);

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

        javax.swing.GroupLayout panelSidebarLayout = new javax.swing.GroupLayout(panelSidebar);
        panelSidebar.setLayout(panelSidebarLayout);
        panelSidebarLayout.setHorizontalGroup(
            panelSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSidebarLayout.createSequentialGroup()
                .addContainerGap(60, Short.MAX_VALUE)
                .addGroup(panelSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSidebarLayout.createSequentialGroup()
                        .addGroup(panelSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnDataPenghuni, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                                .addComponent(btnDataKamar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnDashboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnPembayaran, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnLaporan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(60, 60, 60))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSidebarLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77))))
        );
        panelSidebarLayout.setVerticalGroup(
            panelSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSidebarLayout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(jLabel1)
                .addGap(55, 55, 55)
                .addComponent(btnDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDataKamar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDataPenghuni, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLaporan, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );

        lblTotalPenghuniText.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        lblTotalPenghuniText.setForeground(new java.awt.Color(60, 70, 90));
        lblTotalPenghuniText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalPenghuniText.setText("Total Pemasukan");

        lblTotalPemasukan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTotalPemasukan.setForeground(new java.awt.Color(36, 52, 71));
        lblTotalPemasukan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalPemasukan.setText("0");

        javax.swing.GroupLayout roundedPanel1Layout = new javax.swing.GroupLayout(roundedPanel1);
        roundedPanel1.setLayout(roundedPanel1Layout);
        roundedPanel1Layout.setHorizontalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTotalPemasukan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel1Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(lblTotalPenghuniText)
                .addGap(17, 17, 17))
        );
        roundedPanel1Layout.setVerticalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblTotalPenghuniText)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotalPemasukan)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(60, 70, 90));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Pemasukan Bulan Ini");

        lblBulanIni.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblBulanIni.setForeground(new java.awt.Color(36, 52, 71));
        lblBulanIni.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBulanIni.setText("0");

        javax.swing.GroupLayout roundedPanel2Layout = new javax.swing.GroupLayout(roundedPanel2);
        roundedPanel2.setLayout(roundedPanel2Layout);
        roundedPanel2Layout.setHorizontalGroup(
            roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(lblBulanIni, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        roundedPanel2Layout.setVerticalGroup(
            roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel2Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblBulanIni)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel6.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(60, 70, 90));
        jLabel6.setText("Jumlah Lunas");

        lblJumlahLunas.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblJumlahLunas.setForeground(new java.awt.Color(36, 52, 71));
        lblJumlahLunas.setText("0");

        javax.swing.GroupLayout roundedPanel3Layout = new javax.swing.GroupLayout(roundedPanel3);
        roundedPanel3.setLayout(roundedPanel3Layout);
        roundedPanel3Layout.setHorizontalGroup(
            roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel3Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(roundedPanel3Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(lblJumlahLunas)))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        roundedPanel3Layout.setVerticalGroup(
            roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel3Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblJumlahLunas)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelPembayaran.setBackground(new java.awt.Color(235, 242, 252));
        panelPembayaran.setPreferredSize(new java.awt.Dimension(300, 229));

        lblDaftarPenghuni.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        lblDaftarPenghuni.setForeground(new java.awt.Color(35, 45, 70));
        lblDaftarPenghuni.setText("Daftar Pembayaran");

        tblPembayaran.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblPembayaran.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Pembayaran", "Nama Penghuni", "Kamar", "Tagihan Bulan", "Tanggal Bayar", "Status", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPembayaran.setGridColor(new java.awt.Color(235, 240, 248));
        tblPembayaran.setRowHeight(30);
        tblPembayaran.setSelectionBackground(new java.awt.Color(215, 228, 255));
        tblPembayaran.setSelectionForeground(new java.awt.Color(35, 55, 90));
        tblPembayaran.setShowHorizontalLines(true);
        tblPembayaran.setShowVerticalLines(true);
        ScrollPanePembayaran.setViewportView(tblPembayaran);
        if (tblPembayaran.getColumnModel().getColumnCount() > 0) {
            tblPembayaran.getColumnModel().getColumn(0).setResizable(false);
            tblPembayaran.getColumnModel().getColumn(1).setResizable(false);
            tblPembayaran.getColumnModel().getColumn(2).setResizable(false);
            tblPembayaran.getColumnModel().getColumn(3).setResizable(false);
            tblPembayaran.getColumnModel().getColumn(4).setResizable(false);
            tblPembayaran.getColumnModel().getColumn(5).setResizable(false);
            tblPembayaran.getColumnModel().getColumn(6).setResizable(false);
        }

        txtCari.setBackground(new java.awt.Color(245, 248, 255));
        txtCari.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        txtCari.setForeground(new java.awt.Color(45, 70, 110));
        txtCari.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCari.setText("🔍 Cari nama / kamar / bulan");
        txtCari.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 228, 240), 1, true));
        txtCari.setPreferredSize(new java.awt.Dimension(131, 19));
        txtCari.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCariFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCariFocusLost(evt);
            }
        });
        txtCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCariKeyReleased(evt);
            }
        });

        btnUpdate.setBackground(new java.awt.Color(90, 120, 150));
        btnUpdate.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("Edit");
        btnUpdate.setBorderPainted(false);
        btnUpdate.addActionListener(this::btnUpdateActionPerformed);

        btnHapus.setBackground(new java.awt.Color(220, 55, 80));
        btnHapus.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        btnHapus.setForeground(new java.awt.Color(255, 255, 255));
        btnHapus.setText("🗑 Hapus");
        btnHapus.setBorderPainted(false);
        btnHapus.setFocusPainted(false);
        btnHapus.addActionListener(this::btnHapusActionPerformed);

        javax.swing.GroupLayout panelPembayaranLayout = new javax.swing.GroupLayout(panelPembayaran);
        panelPembayaran.setLayout(panelPembayaranLayout);
        panelPembayaranLayout.setHorizontalGroup(
            panelPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPembayaranLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(panelPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPembayaranLayout.createSequentialGroup()
                        .addComponent(lblDaftarPenghuni)
                        .addContainerGap(533, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPembayaranLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(panelPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ScrollPanePembayaran, javax.swing.GroupLayout.DEFAULT_SIZE, 699, Short.MAX_VALUE)
                            .addGroup(panelPembayaranLayout.createSequentialGroup()
                                .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnHapus)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnUpdate)))
                        .addGap(15, 15, 15))))
        );
        panelPembayaranLayout.setVerticalGroup(
            panelPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPembayaranLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDaftarPenghuni)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnHapus)
                        .addComponent(btnUpdate)))
                .addGap(18, 18, 18)
                .addComponent(ScrollPanePembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        btnTambah.setBackground(new java.awt.Color(90, 120, 150));
        btnTambah.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTambah.setForeground(new java.awt.Color(255, 255, 255));
        btnTambah.setText("+ Tambah ");
        btnTambah.setBorderPainted(false);
        btnTambah.setFocusPainted(false);
        btnTambah.addActionListener(this::btnTambahActionPerformed);

        btnRefresh.setBackground(new java.awt.Color(232, 240, 255));
        btnRefresh.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        btnRefresh.setForeground(new java.awt.Color(45, 70, 110));
        btnRefresh.setText("⟳ Refresh");
        btnRefresh.setBorderPainted(false);
        btnRefresh.setFocusPainted(false);
        btnRefresh.addActionListener(this::btnRefreshActionPerformed);

        roundedPanel.setBackground(new java.awt.Color(90, 120, 150));

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Data Pembayaran");

        jLabel3.setBackground(new java.awt.Color(30, 41, 59));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Kelola informasi Pembayaran kos dengan mudah");

        javax.swing.GroupLayout roundedPanelLayout = new javax.swing.GroupLayout(roundedPanel);
        roundedPanel.setLayout(roundedPanelLayout);
        roundedPanelLayout.setHorizontalGroup(
            roundedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanelLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(roundedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(panelSidebar, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundedPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelMainLayout.createSequentialGroup()
                                .addComponent(roundedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(roundedPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(101, 101, 101)
                                .addComponent(roundedPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(panelPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 743, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(40, Short.MAX_VALUE))
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnRefresh)
                        .addGap(18, 18, 18)
                        .addComponent(btnTambah)
                        .addGap(28, 28, 28))))
        );
        panelMainLayout.setVerticalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelSidebar, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addComponent(roundedPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambah)
                    .addComponent(btnRefresh))
                .addGap(20, 20, 20)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(roundedPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundedPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundedPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(panelPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMain, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1080, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMain, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDashboardActionPerformed
    if (dashboardFrame == null) {
        dashboardFrame = new dashboard.DashboardFrame();    }
    dashboardFrame.setVisible(true);
    setVisible(false);
    }//GEN-LAST:event_btnDashboardActionPerformed

    private void btnDataKamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDataKamarActionPerformed
        if (kamarFrame == null) {
        kamarFrame = new kamar.KamarFrame();}
        kamarFrame.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnDataKamarActionPerformed

    private void btnDataPenghuniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDataPenghuniActionPerformed
        if (penghuniFrame == null) {
        penghuniFrame = new penghuni.PenghuniFrame();}
        penghuniFrame.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnDataPenghuniActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // Tutup semua window yang terbuka
        Window[] windows = Window.getWindows();
        for (Window window : windows) { window.dispose();   }
        new Login.LoginFrame().setVisible(true);
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void txtCariFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCariFocusGained
        if (txtCari.getText().equals("🔍 Cari nama / kamar / bulan")) {
            txtCari.setText("");
        }
    }//GEN-LAST:event_txtCariFocusGained

    private void txtCariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyReleased
        cariData();
    }//GEN-LAST:event_txtCariKeyReleased

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        int selectedRow = tblPembayaran.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data yang akan diedit!");
            return;
        }
        // ambil id pembayaran dari kolom pertama
        int idPembayaran = (int) modelTabel.getValueAt(selectedRow, 0);
        int idPenghuni = (int) modelTabel.getValueAt(selectedRow, 1);  
        String namaPenghuni = (String) modelTabel.getValueAt(selectedRow, 2);
        String noKamar = (String) modelTabel.getValueAt(selectedRow, 3);
        String bulan = (String) modelTabel.getValueAt(selectedRow, 4);
        java.sql.Date tglBayar = (java.sql.Date) modelTabel.getValueAt(selectedRow, 5);
        int jumlah = (int) modelTabel.getValueAt(selectedRow, 6);
        String status = (String) modelTabel.getValueAt(selectedRow, 7);
        
        new pembayaran.UpdatePembayaranFrame
        (this, idPembayaran, idPenghuni, namaPenghuni, noKamar, bulan, tglBayar.toString(), jumlah, status).setVisible(true);
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        new TambahPembayaran(this).setVisible(true);
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        btnRefresh.setText("Refresh");
        loadData();
        txtCari.setText("🔍 Cari nama / kamar / bulan");
        txtCari.setForeground(new Color(150,150,170));
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        int selectedRow = tblPembayaran.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data yang akan dihapus!");
            return;
        }
        int konfirmasi = JOptionPane.showConfirmDialog(this, "Yakin hapus pembayaran ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (konfirmasi == JOptionPane.YES_OPTION) {
            int idPembayaran = (int) modelTabel.getValueAt(selectedRow, 0);
            try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement("DELETE FROM pembayaran WHERE id_pembayaran=?")) {
                stmt.setInt(1, idPembayaran);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Data berhasil dihapus");
                loadData();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Gagal hapus: "+e.getMessage());
            }
        }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnLaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaporanActionPerformed
        if (laporanFrame == null) {
        laporanFrame = new laporan.LaporanFrame();}
        laporanFrame.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnLaporanActionPerformed

    private void txtCariFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCariFocusLost
        if (txtCari.getText().trim().isEmpty()) {
                txtCari.setText("🔍 Cari nama / kamar / bulan");
                txtCari.setForeground(Color.GRAY);
            }
    }//GEN-LAST:event_txtCariFocusLost

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new PembayaranFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ScrollPanePembayaran;
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnDataKamar;
    private javax.swing.JButton btnDataPenghuni;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnLaporan;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnPembayaran;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel lblBulanIni;
    private javax.swing.JLabel lblDaftarPenghuni;
    private javax.swing.JLabel lblJumlahLunas;
    private javax.swing.JLabel lblTotalPemasukan;
    private javax.swing.JLabel lblTotalPenghuniText;
    private javax.swing.JPanel panelMain;
    private config.RoundedPanel panelPembayaran;
    private javax.swing.JPanel panelSidebar;
    private config.RoundedPanel roundedPanel;
    private config.RoundedPanel roundedPanel1;
    private config.RoundedPanel roundedPanel2;
    private config.RoundedPanel roundedPanel3;
    private javax.swing.JTable tblPembayaran;
    private javax.swing.JTextField txtCari;
    // End of variables declaration//GEN-END:variables

}
