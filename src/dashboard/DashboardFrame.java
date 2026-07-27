package dashboard;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import config.DatabaseConnection;
import config.UIConfig;
import java.awt.Dimension;
import java.awt.Window;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class DashboardFrame extends javax.swing.JFrame {    
    private kamar.KamarFrame kamarFrame = null;
    private penghuni.PenghuniFrame penghuniFrame;
    private pembayaran.PembayaranFrame pembayaranFrame;
    private laporan.LaporanFrame laporanFrame;
    public DashboardFrame() {
        initComponents();
        btnViewBelumBayar.addActionListener(e -> showBelumBayarList());
        setLocationRelativeTo(null);
        UIConfig.setHeaderTabel(tblPembayaranDashboard);
        tampilDashboard();
        loadPaymentSummary();
        
    }
      
    public void tampilDashboard() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            // TOTAL KAMAR
            String sqlKamar = "SELECT COUNT(*) AS total FROM kamar";
            try (PreparedStatement pst = conn.prepareStatement(sqlKamar);
                ResultSet rs = pst.executeQuery()) {
                if (rs.next()) totalkamar.setText(rs.getString("total"));
            }

            // KAMAR KOSONG
            String sqlKosong = "SELECT COUNT(*) AS kosong FROM kamar WHERE status_kamar='Kosong'";
            try (PreparedStatement pst = conn.prepareStatement(sqlKosong);
                ResultSet rs = pst.executeQuery()) {
                if (rs.next()) KamarKosong.setText(rs.getString("kosong"));
            }

            // TOTAL PENGHUNI
            String sqlPenghuni = "SELECT COUNT(*) AS penghuni FROM penghuni WHERE status_penghuni = 'Aktif'";
            try (PreparedStatement pst = conn.prepareStatement(sqlPenghuni);
                ResultSet rs = pst.executeQuery()) {
                if (rs.next()) TotalPenghuni.setText(rs.getString("penghuni"));
            }

            // Penghuni aktif yang belum pernah bayar
            String sqlBelumBayar = "SELECT COUNT(*) AS jumlah FROM penghuni p " +
                "WHERE p.status_penghuni = 'Aktif' " +
                "AND NOT EXISTS (SELECT 1 FROM pembayaran pb WHERE pb.id_penghuni = p.id_penghuni)";
            try (PreparedStatement pst = conn.prepareStatement(sqlBelumBayar);
                ResultSet rs = pst.executeQuery()) {
                if (rs.next()) lblBelumPernahBayar.setText(rs.getString("jumlah"));
            }
        }
        catch (SQLException e) {    JOptionPane.showMessageDialog(this, "Gagal memuat dashboard: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void loadPaymentSummary() {
        String sqlTotal = "SELECT SUM(jumlah) AS total FROM pembayaran";
        String sqlRecent = "SELECT ph.nama_penghuni, ph.nomor_kamar, p.status, p.tanggal_bayar, p.jumlah " +
                       "FROM pembayaran p JOIN penghuni ph ON p.id_penghuni = ph.id_penghuni " +
                       "ORDER BY p.tanggal_bayar DESC LIMIT 5";

        try {
            //Koneksi dipanggil biasa tanpa ditutup paksa oleh try-with-resources
            Connection conn = DatabaseConnection.getConnection();

            // Ambil Total Pemasukan
            try (PreparedStatement pst = conn.prepareStatement(sqlTotal);
                ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    int total = rs.getInt("total");
                    lbl_totalbayar.setText(String.format("Rp %,d", total)); 
                }
            }

            // Ambil 5 Transaksi Terbaru untuk Tabel Dashboard
            try (PreparedStatement pst = conn.prepareStatement(sqlRecent);
                ResultSet rs = pst.executeQuery()) {
            
                DefaultTableModel model = (DefaultTableModel) tblPembayaranDashboard.getModel();
                model.setRowCount(0); // hapus data lama di tabel sebelum memuat yang baru
            
                while (rs.next()) {
                    Object[] row = {
                        rs.getString("nama_penghuni"),
                        rs.getString("nomor_kamar"),
                        rs.getString("status"),
                        rs.getDate("tanggal_bayar"),
                        rs.getInt("jumlah")
                    };
                    model.addRow(row);
                }
            }
        
        } 
        catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal memuat ringkasan pembayaran: " + e.getMessage());
        }
    }
    
    private void showBelumBayarList() {
        String sql = "SELECT nama_penghuni, nomor_kamar FROM penghuni p " +
                 "WHERE p.status_penghuni = 'Aktif' " +
                 "AND NOT EXISTS (SELECT 1 FROM pembayaran pb WHERE pb.id_penghuni = p.id_penghuni) " +
                 "ORDER BY nama_penghuni";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
        
            // Gunakan JTable untuk tampilan lebih rapi
            DefaultTableModel model = new DefaultTableModel(new String[]{"No", "Nama Penghuni", "Kamar"}, 0);
            int no = 1;
            while (rs.next()) {
                model.addRow(new Object[]{no++, rs.getString("nama_penghuni"), rs.getString("nomor_kamar")});
            }
        
            JTable table = new JTable(model);
            UIConfig.setHeaderTabel(table);
            table.setRowHeight(30);
        
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setPreferredSize(new Dimension(500, 400));
            JOptionPane.showMessageDialog(this, scrollPane, "Daftar Penghuni Belum Pernah Bayar", JOptionPane.INFORMATION_MESSAGE);
        } 
        catch (SQLException e) { JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelMain = new javax.swing.JPanel();
        roundedPanel1 = new config.RoundedPanel();
        jLabel4 = new javax.swing.JLabel();
        totalkamar = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        roundedPanel2 = new config.RoundedPanel();
        jLabel7 = new javax.swing.JLabel();
        KamarKosong = new javax.swing.JLabel();
        roundedPanel3 = new config.RoundedPanel();
        jLabel10 = new javax.swing.JLabel();
        TotalPenghuni = new javax.swing.JLabel();
        roundedPanel4 = new config.RoundedPanel();
        jLabel13 = new javax.swing.JLabel();
        lbl_totalbayar = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnDashboard = new javax.swing.JButton();
        btnDataKamar = new javax.swing.JButton();
        btnDataPenghuni = new javax.swing.JButton();
        btnPembayaran = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btnLaporan = new javax.swing.JButton();
        btnPemilikKos = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        ScrollPane = new javax.swing.JScrollPane();
        tblPembayaranDashboard = new javax.swing.JTable();
        roundedPanel5 = new config.RoundedPanel();
        jLabel8 = new javax.swing.JLabel();
        lblBelumPernahBayar = new javax.swing.JLabel();
        btnViewBelumBayar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(245, 250, 252));

        PanelMain.setBackground(new java.awt.Color(176, 206, 240));

        roundedPanel1.setPreferredSize(new java.awt.Dimension(170, 95));

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(40, 60, 90));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Total Kamar");

        totalkamar.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        totalkamar.setForeground(new java.awt.Color(44, 62, 80));
        totalkamar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalkamar.setText("0");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(120, 130, 140));
        jLabel6.setText("Semua kamar terdaftar");

        javax.swing.GroupLayout roundedPanel1Layout = new javax.swing.GroupLayout(roundedPanel1);
        roundedPanel1.setLayout(roundedPanel1Layout);
        roundedPanel1Layout.setHorizontalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(totalkamar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(28, 28, 28))
        );
        roundedPanel1Layout.setVerticalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalkamar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        roundedPanel2.setPreferredSize(new java.awt.Dimension(170, 95));

        jLabel7.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(40, 60, 90));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Kamar Kosong");

        KamarKosong.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        KamarKosong.setForeground(new java.awt.Color(44, 62, 80));
        KamarKosong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        KamarKosong.setText("0");

        javax.swing.GroupLayout roundedPanel2Layout = new javax.swing.GroupLayout(roundedPanel2);
        roundedPanel2.setLayout(roundedPanel2Layout);
        roundedPanel2Layout.setHorizontalGroup(
            roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel2Layout.createSequentialGroup()
                .addGroup(roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel2Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(KamarKosong, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundedPanel2Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel7)))
                .addGap(0, 37, Short.MAX_VALUE))
        );
        roundedPanel2Layout.setVerticalGroup(
            roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel2Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(KamarKosong)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        roundedPanel3.setFocusCycleRoot(true);
        roundedPanel3.setPreferredSize(new java.awt.Dimension(220, 110));

        jLabel10.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel10.setText("Penghuni");

        TotalPenghuni.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        TotalPenghuni.setForeground(new java.awt.Color(44, 62, 80));
        TotalPenghuni.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TotalPenghuni.setText("0");

        javax.swing.GroupLayout roundedPanel3Layout = new javax.swing.GroupLayout(roundedPanel3);
        roundedPanel3.setLayout(roundedPanel3Layout);
        roundedPanel3Layout.setHorizontalGroup(
            roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel3Layout.createSequentialGroup()
                .addContainerGap(68, Short.MAX_VALUE)
                .addGroup(roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(57, 57, 57))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel3Layout.createSequentialGroup()
                        .addComponent(TotalPenghuni)
                        .addGap(75, 75, 75))))
        );
        roundedPanel3Layout.setVerticalGroup(
            roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel3Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TotalPenghuni)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        roundedPanel4.setPreferredSize(new java.awt.Dimension(220, 110));

        jLabel13.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(40, 60, 90));
        jLabel13.setText("Total Pembayaran");

        lbl_totalbayar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl_totalbayar.setForeground(new java.awt.Color(44, 62, 80));
        lbl_totalbayar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_totalbayar.setText("Rp 0");

        javax.swing.GroupLayout roundedPanel4Layout = new javax.swing.GroupLayout(roundedPanel4);
        roundedPanel4.setLayout(roundedPanel4Layout);
        roundedPanel4Layout.setHorizontalGroup(
            roundedPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel4Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(29, 29, 29))
            .addComponent(lbl_totalbayar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        roundedPanel4Layout.setVerticalGroup(
            roundedPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel4Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_totalbayar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jPanel3.setBackground(new java.awt.Color(90, 120, 150));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Halo, Admin!");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Kelola kos-kosan dengan mudah dan efisien.");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(0, 20, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(90, 120, 150));
        jPanel2.setAutoscrolls(true);
        jPanel2.setDoubleBuffered(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(120, 413));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 28)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("KOSKITA");

        btnDashboard.setBackground(new java.awt.Color(176, 206, 240));
        btnDashboard.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnDashboard.setForeground(new java.awt.Color(44, 62, 80));
        btnDashboard.setText("Dashboard");
        btnDashboard.setBorderPainted(false);
        btnDashboard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDashboard.setFocusPainted(false);
        btnDashboard.setPreferredSize(new java.awt.Dimension(143, 29));

        btnDataKamar.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnDataKamar.setForeground(new java.awt.Color(80, 90, 110));
        btnDataKamar.setText("Data Kamar");
        btnDataKamar.setBorderPainted(false);
        btnDataKamar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDataKamar.setFocusPainted(false);
        btnDataKamar.addActionListener(this::btnDataKamarActionPerformed);

        btnDataPenghuni.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnDataPenghuni.setForeground(new java.awt.Color(80, 90, 110));
        btnDataPenghuni.setText("Data Penghuni");
        btnDataPenghuni.setBorderPainted(false);
        btnDataPenghuni.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDataPenghuni.setFocusPainted(false);
        btnDataPenghuni.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnDataPenghuni.addActionListener(this::btnDataPenghuniActionPerformed);

        btnPembayaran.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnPembayaran.setForeground(new java.awt.Color(80, 90, 110));
        btnPembayaran.setText("Pembayaran");
        btnPembayaran.setBorderPainted(false);
        btnPembayaran.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPembayaran.setFocusPainted(false);
        btnPembayaran.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnPembayaran.addActionListener(this::btnPembayaranActionPerformed);

        btnLogout.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(80, 90, 110));
        btnLogout.setText("Logout");
        btnLogout.setBorderPainted(false);
        btnLogout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogout.setFocusPainted(false);
        btnLogout.addActionListener(this::btnLogoutActionPerformed);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Sistem Manajemen Kos");

        btnLaporan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnLaporan.setForeground(new java.awt.Color(80, 90, 110));
        btnLaporan.setText("Laporan");
        btnLaporan.setBorderPainted(false);
        btnLaporan.setFocusPainted(false);
        btnLaporan.addActionListener(this::btnLaporanActionPerformed);

        btnPemilikKos.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnPemilikKos.setForeground(new java.awt.Color(80, 90, 110));
        btnPemilikKos.setText("Data Pemilik");
        btnPemilikKos.setBorderPainted(false);
        btnPemilikKos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPemilikKos.setFocusPainted(false);
        btnPemilikKos.addActionListener(this::btnPemilikKosActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btnLogout, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnLaporan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnPembayaran, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnDataPenghuni, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnDataKamar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(btnPemilikKos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jLabel1)))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(btnDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPemilikKos, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDataKamar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btnDataPenghuni, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLaporan, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(36, 52, 71));
        jLabel9.setText("Pembayaran Terbaru");

        tblPembayaranDashboard.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblPembayaranDashboard.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nama Penghuni", "Nomor Kamar", "Status", "Tanggal Bayar", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPembayaranDashboard.setGridColor(new java.awt.Color(235, 240, 248));
        tblPembayaranDashboard.setRowHeight(30);
        tblPembayaranDashboard.setSelectionBackground(new java.awt.Color(215, 228, 255));
        tblPembayaranDashboard.setSelectionForeground(new java.awt.Color(35, 55, 90));
        tblPembayaranDashboard.setShowHorizontalLines(true);
        tblPembayaranDashboard.setShowVerticalLines(true);
        ScrollPane.setViewportView(tblPembayaranDashboard);
        if (tblPembayaranDashboard.getColumnModel().getColumnCount() > 0) {
            tblPembayaranDashboard.getColumnModel().getColumn(0).setResizable(false);
            tblPembayaranDashboard.getColumnModel().getColumn(1).setResizable(false);
            tblPembayaranDashboard.getColumnModel().getColumn(2).setResizable(false);
            tblPembayaranDashboard.getColumnModel().getColumn(3).setResizable(false);
            tblPembayaranDashboard.getColumnModel().getColumn(4).setResizable(false);
        }

        jLabel8.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(40, 60, 90));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Penghuni belum bayar");

        lblBelumPernahBayar.setFont(new java.awt.Font("Segoe UI Symbol", 1, 20)); // NOI18N
        lblBelumPernahBayar.setForeground(new java.awt.Color(220, 70, 60));
        lblBelumPernahBayar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBelumPernahBayar.setText("0 ");

        btnViewBelumBayar.setBackground(new java.awt.Color(90, 120, 150));
        btnViewBelumBayar.setFont(new java.awt.Font("Segoe UI Symbol", 1, 13)); // NOI18N
        btnViewBelumBayar.setForeground(new java.awt.Color(255, 255, 255));
        btnViewBelumBayar.setText("Lihat 👁️");
        btnViewBelumBayar.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 15, 5, 15));
        btnViewBelumBayar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnViewBelumBayar.setFocusPainted(false);

        javax.swing.GroupLayout roundedPanel5Layout = new javax.swing.GroupLayout(roundedPanel5);
        roundedPanel5.setLayout(roundedPanel5Layout);
        roundedPanel5Layout.setHorizontalGroup(
            roundedPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnViewBelumBayar)
                .addGap(52, 52, 52))
            .addGroup(roundedPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(roundedPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblBelumPernahBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        roundedPanel5Layout.setVerticalGroup(
            roundedPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel5Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblBelumPernahBayar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnViewBelumBayar)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PanelMainLayout = new javax.swing.GroupLayout(PanelMain);
        PanelMain.setLayout(PanelMainLayout);
        PanelMainLayout.setHorizontalGroup(
            PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelMainLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelMainLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelMainLayout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(PanelMainLayout.createSequentialGroup()
                                .addGroup(PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(ScrollPane, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelMainLayout.createSequentialGroup()
                                        .addGroup(PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(roundedPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(roundedPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                                        .addGroup(PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(roundedPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(roundedPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(89, 89, 89)
                                        .addComponent(roundedPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(51, 51, 51))))))
        );
        PanelMainLayout.setVerticalGroup(
            PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMainLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundedPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundedPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(roundedPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                    .addComponent(roundedPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(33, 33, 33)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDataKamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDataKamarActionPerformed
        if (kamarFrame == null) {
        kamarFrame = new kamar.KamarFrame();    }
        kamarFrame.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnDataKamarActionPerformed

    private void btnDataPenghuniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDataPenghuniActionPerformed
        if (penghuniFrame == null) {
        penghuniFrame = new penghuni.PenghuniFrame();}
        penghuniFrame.setVisible(true);
        setVisible(false);

    }//GEN-LAST:event_btnDataPenghuniActionPerformed

    private void btnPembayaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPembayaranActionPerformed
        if (pembayaranFrame == null) {
        pembayaranFrame = new pembayaran.PembayaranFrame();}
        pembayaranFrame.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnPembayaranActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // Tutup semua window yang terbuka
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            window.dispose();}
        new Login.LoginFrame().setVisible(true);
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnLaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaporanActionPerformed
        if (laporanFrame == null) {
            laporanFrame = new laporan.LaporanFrame(); }
        laporanFrame.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnLaporanActionPerformed

    private void btnPemilikKosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPemilikKosActionPerformed
        new pemilikKos.PemilikKosFrame().setVisible(true);
    }//GEN-LAST:event_btnPemilikKosActionPerformed

    public static void main(String args[]) 
    {
        java.awt.EventQueue.invokeLater(() -> new DashboardFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel KamarKosong;
    private javax.swing.JPanel PanelMain;
    private javax.swing.JScrollPane ScrollPane;
    private javax.swing.JLabel TotalPenghuni;
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnDataKamar;
    private javax.swing.JButton btnDataPenghuni;
    private javax.swing.JButton btnLaporan;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnPembayaran;
    private javax.swing.JButton btnPemilikKos;
    private javax.swing.JButton btnViewBelumBayar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblBelumPernahBayar;
    private javax.swing.JLabel lbl_totalbayar;
    private config.RoundedPanel roundedPanel1;
    private config.RoundedPanel roundedPanel2;
    private config.RoundedPanel roundedPanel3;
    private config.RoundedPanel roundedPanel4;
    private config.RoundedPanel roundedPanel5;
    private javax.swing.JTable tblPembayaranDashboard;
    private javax.swing.JLabel totalkamar;
    // End of variables declaration//GEN-END:variables
}
