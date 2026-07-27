
package laporan;

import java.sql.*;
import java.awt.*;
import java.io.*;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import config.DatabaseConnection;
import config.UIConfig;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

public class LaporanFrame extends javax.swing.JFrame {
    private DefaultTableModel modelTabel;
    
    // Referensi ke frame lain (untuk navigasi setVisible(false))
    private dashboard.DashboardFrame dashboardFrame;
    private kamar.KamarFrame kamarFrame;
    private penghuni.PenghuniFrame penghuniFrame;
    private pembayaran.PembayaranFrame pembayaranFrame;
    
    public LaporanFrame() {
        initComponents();
        setLocationRelativeTo(null);
        
        modelTabel = new DefaultTableModel(new String[]{"Nama Penghuni", "Kamar", "Tanggal Bayar", "Jumlah (Rp)", "Status"}, 0) {
        @Override
        public boolean isCellEditable(int row, int col) { return false; }
        };
        tblLaporanPembayaran.setModel(modelTabel);
        
        UIConfig.setHeaderTabel(tblLaporanPembayaran); //set header tabel
        loadData(); // muat data default (bulan/tahun sekarang)
    }
 
    private void loadData() {
        modelTabel.setRowCount(0);
        String bulan = cbBulan.getSelectedItem().toString();
        String tahun = cbTahun.getSelectedItem().toString();

        // Bangun query dinamis
        StringBuilder sql = new StringBuilder( "SELECT ph.nama_penghuni, ph.nomor_kamar, p.tanggal_bayar, p.jumlah, p.status " 
                + "FROM pembayaran p JOIN penghuni ph ON p.id_penghuni = ph.id_penghuni WHERE 1=1");
    
        if (!bulan.equals("Semua")) {  sql.append(" AND p.tagihan_bulan = ?"); }
        if (!tahun.equals("Semua")) { sql.append(" AND YEAR(p.tanggal_bayar) = ?"); }
        sql.append(" ORDER BY p.tanggal_bayar DESC");

        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql.toString())) {
        
            int paramIndex = 1;
            if (!bulan.equals("Semua")) {
                ps.setString(paramIndex++, bulan);  }
            if (!tahun.equals("Semua")) {
                ps.setInt(paramIndex++, Integer.parseInt(tahun));   }
        
            ResultSet rs = ps.executeQuery();
            long total = 0;
            while (rs.next()) {
                String nama = rs.getString("nama_penghuni");
                String kamar = rs.getString("nomor_kamar");
                String tgl = rs.getDate("tanggal_bayar").toString();
                int jumlah = rs.getInt("jumlah");
                String status = rs.getString("status");
                modelTabel.addRow(new Object[]{nama, kamar, tgl, formatRupiah(jumlah), status});
                total += jumlah;
            }
            lblTotal.setText("Total: " + formatRupiah((int) total));
        }
        catch (SQLException e) {  JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
}

    private String formatRupiah(int angka) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        return formatter.format(angka);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMain = new javax.swing.JPanel();
        panelSidebar3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnDashboard3 = new javax.swing.JButton();
        btnDataKamar3 = new javax.swing.JButton();
        btnDataPenghuni3 = new javax.swing.JButton();
        btnPembayaran3 = new javax.swing.JButton();
        btnLogout3 = new javax.swing.JButton();
        btnLaporan3 = new javax.swing.JButton();
        panelContent = new config.RoundedPanel();
        lblHeader = new javax.swing.JLabel();
        panelFilter = new javax.swing.JPanel();
        month = new javax.swing.JLabel();
        cbBulan = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        cbTahun = new javax.swing.JComboBox<>();
        btnTampilkan = new javax.swing.JButton();
        btnPdf = new javax.swing.JButton();
        btnExcel = new javax.swing.JButton();
        ScrollPane = new javax.swing.JScrollPane();
        tblLaporanPembayaran = new javax.swing.JTable();
        roundedPanel1 = new config.RoundedPanel();
        lblTotal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelMain.setBackground(new java.awt.Color(176, 206, 240));
        panelMain.setPreferredSize(new java.awt.Dimension(950, 540));

        panelSidebar3.setBackground(new java.awt.Color(90, 120, 150));
        panelSidebar3.setAutoscrolls(true);
        panelSidebar3.setPreferredSize(new java.awt.Dimension(120, 413));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 28)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("KOSKITA");

        btnDashboard3.setBackground(new java.awt.Color(245, 247, 252));
        btnDashboard3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnDashboard3.setForeground(new java.awt.Color(50, 60, 80));
        btnDashboard3.setText("Dashboard");
        btnDashboard3.setBorderPainted(false);
        btnDashboard3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDashboard3.setFocusPainted(false);
        btnDashboard3.addActionListener(this::btnDashboard3ActionPerformed);

        btnDataKamar3.setBackground(new java.awt.Color(245, 247, 252));
        btnDataKamar3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnDataKamar3.setForeground(new java.awt.Color(50, 60, 80));
        btnDataKamar3.setText("Data Kamar");
        btnDataKamar3.setBorderPainted(false);
        btnDataKamar3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDataKamar3.setFocusPainted(false);
        btnDataKamar3.addActionListener(this::btnDataKamar3ActionPerformed);

        btnDataPenghuni3.setBackground(new java.awt.Color(245, 247, 252));
        btnDataPenghuni3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnDataPenghuni3.setForeground(new java.awt.Color(36, 52, 71));
        btnDataPenghuni3.setText("Data Penghuni");
        btnDataPenghuni3.setBorderPainted(false);
        btnDataPenghuni3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDataPenghuni3.setFocusPainted(false);
        btnDataPenghuni3.setPreferredSize(new java.awt.Dimension(125, 29));
        btnDataPenghuni3.addActionListener(this::btnDataPenghuni3ActionPerformed);

        btnPembayaran3.setBackground(new java.awt.Color(245, 247, 252));
        btnPembayaran3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnPembayaran3.setForeground(new java.awt.Color(36, 52, 71));
        btnPembayaran3.setText("Pembayaran");
        btnPembayaran3.setBorderPainted(false);
        btnPembayaran3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPembayaran3.setFocusPainted(false);
        btnPembayaran3.addActionListener(this::btnPembayaran3ActionPerformed);

        btnLogout3.setBackground(new java.awt.Color(245, 247, 252));
        btnLogout3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnLogout3.setForeground(new java.awt.Color(50, 60, 80));
        btnLogout3.setText("Logout");
        btnLogout3.setBorderPainted(false);
        btnLogout3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogout3.setFocusPainted(false);
        btnLogout3.addActionListener(this::btnLogout3ActionPerformed);

        btnLaporan3.setBackground(new java.awt.Color(176, 206, 240));
        btnLaporan3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnLaporan3.setForeground(new java.awt.Color(40, 60, 90));
        btnLaporan3.setText("Laporan");
        btnLaporan3.setBorderPainted(false);
        btnLaporan3.setFocusPainted(false);

        javax.swing.GroupLayout panelSidebar3Layout = new javax.swing.GroupLayout(panelSidebar3);
        panelSidebar3.setLayout(panelSidebar3Layout);
        panelSidebar3Layout.setHorizontalGroup(
            panelSidebar3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSidebar3Layout.createSequentialGroup()
                .addContainerGap(60, Short.MAX_VALUE)
                .addGroup(panelSidebar3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSidebar3Layout.createSequentialGroup()
                        .addGroup(panelSidebar3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLogout3, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelSidebar3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnDataPenghuni3, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                                .addComponent(btnDataKamar3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnDashboard3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnPembayaran3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnLaporan3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(60, 60, 60))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSidebar3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(75, 75, 75))))
        );
        panelSidebar3Layout.setVerticalGroup(
            panelSidebar3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSidebar3Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(jLabel1)
                .addGap(55, 55, 55)
                .addComponent(btnDashboard3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDataKamar3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDataPenghuni3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPembayaran3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLaporan3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLogout3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );

        lblHeader.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        lblHeader.setForeground(new java.awt.Color(40, 60, 90));
        lblHeader.setText("Laporan Pembayaran");

        panelFilter.setBackground(new java.awt.Color(240, 245, 250));
        panelFilter.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 15, 10, 15));
        panelFilter.setPreferredSize(new java.awt.Dimension(950, 100));

        month.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        month.setForeground(new java.awt.Color(40, 60, 90));
        month.setText("Bulan");

        cbBulan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbBulan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Semua", "Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember" }));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(40, 60, 90));
        jLabel2.setText("Tahun");

        cbTahun.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbTahun.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Semua", "2025", "2026", "2027", "2028", "2029", "2030" }));

        btnTampilkan.setBackground(new java.awt.Color(40, 70, 120));
        btnTampilkan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnTampilkan.setForeground(new java.awt.Color(255, 255, 255));
        btnTampilkan.setText("Tampilkan");
        btnTampilkan.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 15, 5, 15));
        btnTampilkan.addActionListener(this::btnTampilkanActionPerformed);

        javax.swing.GroupLayout panelFilterLayout = new javax.swing.GroupLayout(panelFilter);
        panelFilter.setLayout(panelFilterLayout);
        panelFilterLayout.setHorizontalGroup(
            panelFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFilterLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(panelFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbBulan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(month))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbTahun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(85, 85, 85)
                .addComponent(btnTampilkan)
                .addContainerGap())
        );
        panelFilterLayout.setVerticalGroup(
            panelFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFilterLayout.createSequentialGroup()
                .addGroup(panelFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFilterLayout.createSequentialGroup()
                        .addGroup(panelFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(month)
                            .addComponent(jLabel2))
                        .addGap(12, 12, 12)
                        .addGroup(panelFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbBulan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbTahun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelFilterLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(btnTampilkan)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnPdf.setBackground(new java.awt.Color(170, 70, 60));
        btnPdf.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnPdf.setForeground(new java.awt.Color(255, 255, 255));
        btnPdf.setText("Export PDF");
        btnPdf.setFocusPainted(false);
        btnPdf.addActionListener(this::btnPdfActionPerformed);

        btnExcel.setBackground(new java.awt.Color(40, 100, 80));
        btnExcel.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnExcel.setForeground(new java.awt.Color(255, 255, 255));
        btnExcel.setText("Export Excel");
        btnExcel.setFocusPainted(false);
        btnExcel.addActionListener(this::btnExcelActionPerformed);

        tblLaporanPembayaran.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblLaporanPembayaran.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nama Penghuni", "Nomor Kamar", "Tanggal Bayar", "Jumlah", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblLaporanPembayaran.setGridColor(new java.awt.Color(210, 220, 230));
        tblLaporanPembayaran.setRowHeight(30);
        tblLaporanPembayaran.setSelectionBackground(new java.awt.Color(210, 225, 245));
        tblLaporanPembayaran.setShowHorizontalLines(true);
        tblLaporanPembayaran.setShowVerticalLines(true);
        ScrollPane.setViewportView(tblLaporanPembayaran);
        if (tblLaporanPembayaran.getColumnModel().getColumnCount() > 0) {
            tblLaporanPembayaran.getColumnModel().getColumn(0).setResizable(false);
            tblLaporanPembayaran.getColumnModel().getColumn(1).setResizable(false);
            tblLaporanPembayaran.getColumnModel().getColumn(2).setResizable(false);
            tblLaporanPembayaran.getColumnModel().getColumn(3).setResizable(false);
            tblLaporanPembayaran.getColumnModel().getColumn(4).setResizable(false);
        }

        roundedPanel1.setBackground(new java.awt.Color(196, 216, 240));

        lblTotal.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(20, 90, 110));
        lblTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotal.setText("Total Rp ");
        lblTotal.setToolTipText("");
        lblTotal.setMaximumSize(new java.awt.Dimension(0, 0));
        lblTotal.setMinimumSize(new java.awt.Dimension(250, 30));
        lblTotal.setPreferredSize(new java.awt.Dimension(250, 30));

        javax.swing.GroupLayout roundedPanel1Layout = new javax.swing.GroupLayout(roundedPanel1);
        roundedPanel1.setLayout(roundedPanel1Layout);
        roundedPanel1Layout.setHorizontalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        roundedPanel1Layout.setVerticalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelContentLayout = new javax.swing.GroupLayout(panelContent);
        panelContent.setLayout(panelContentLayout);
        panelContentLayout.setHorizontalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContentLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelContentLayout.createSequentialGroup()
                        .addComponent(lblHeader)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 110, Short.MAX_VALUE)
                        .addComponent(btnPdf)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelContentLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelFilter, javax.swing.GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE)
                            .addComponent(ScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE))))
                .addContainerGap(52, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelContentLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(roundedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(154, 154, 154))
        );
        panelContentLayout.setVerticalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContentLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHeader)
                    .addComponent(btnPdf)
                    .addComponent(btnExcel))
                .addGap(36, 36, 36)
                .addComponent(panelFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(roundedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelMainLayout = new javax.swing.GroupLayout(panelMain);
        panelMain.setLayout(panelMainLayout);
        panelMainLayout.setHorizontalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addComponent(panelSidebar3, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(panelContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );
        panelMainLayout.setVerticalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(panelContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
            .addComponent(panelSidebar3, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelMain, javax.swing.GroupLayout.PREFERRED_SIZE, 1083, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMain, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDashboard3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDashboard3ActionPerformed
        if (dashboardFrame == null) {
            dashboardFrame = new dashboard.DashboardFrame();    }
        dashboardFrame.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnDashboard3ActionPerformed

    private void btnDataKamar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDataKamar3ActionPerformed
        if (kamarFrame == null) {
            kamarFrame = new kamar.KamarFrame();}
        kamarFrame.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnDataKamar3ActionPerformed

    private void btnDataPenghuni3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDataPenghuni3ActionPerformed
        if (penghuniFrame == null) penghuniFrame = new penghuni.PenghuniFrame();
        penghuniFrame.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnDataPenghuni3ActionPerformed

    private void btnLogout3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogout3ActionPerformed
        // Tutup semua window yang terbuka
        Window[] windows = Window.getWindows();
        for (Window window : windows) { window.dispose();   }
        new Login.LoginFrame().setVisible(true);
    }//GEN-LAST:event_btnLogout3ActionPerformed

    private void btnExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelActionPerformed
        if (modelTabel.getRowCount() == 0) {
        JOptionPane.showMessageDialog(this, "Tidak ada data untuk diexport.");
        return;
        }
        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new File("laporan_pembayaran.csv"));
        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try (PrintWriter pw = new PrintWriter(chooser.getSelectedFile())) {
                // Header
                for (int i = 0; i < modelTabel.getColumnCount(); i++) {
                    pw.print(modelTabel.getColumnName(i) + ";");
                }
                pw.println();
                // Data
                for (int row = 0; row < modelTabel.getRowCount(); row++) {
                    for (int col = 0; col < modelTabel.getColumnCount(); col++) {
                        pw.print(modelTabel.getValueAt(row, col) + ";");
                    }
                    pw.println();
                }
                JOptionPane.showMessageDialog(this, "Export CSV berhasil!");
            } 
            catch (IOException e) { JOptionPane.showMessageDialog(this, "Gagal export: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_btnExcelActionPerformed

    private void btnPdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPdfActionPerformed
        if (modelTabel.getRowCount() == 0) {
        JOptionPane.showMessageDialog(this, "Tidak ada data untuk diexport.");
        return;
    }
    
    JFileChooser chooser = new JFileChooser();
    chooser.setSelectedFile(new File("laporan_pembayaran.pdf"));
    if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);
            
            PDPageContentStream cs = new PDPageContentStream(document, page);
            
            // Judul (menggunakan font bold dari Standard14Fonts)
            cs.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 16);
            cs.beginText();
            cs.newLineAtOffset(50, 750);
            cs.showText("Laporan Pembayaran");
            cs.endText();
            
            // Filter info
            cs.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
            cs.beginText();
            cs.newLineAtOffset(50, 730);
            cs.showText("Bulan: " + cbBulan.getSelectedItem() +
                        "  Tahun: " + cbTahun.getSelectedItem() );
            cs.endText();
            
            // Header tabel
            int y = 700;
            cs.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 10);
            String[] headers = {"Nama Penghuni", "Kamar", "Tgl Bayar", "Jumlah", "Status"};
            int x = 50;
            for (String header : headers) {
                cs.beginText();
                cs.newLineAtOffset(x, y);
                cs.showText(header);
                cs.endText();
                x += 90;
            }
            
            // Garis bawah header
            cs.setLineWidth(1f);
            cs.moveTo(50, y - 5);
            cs.lineTo(50 + 450, y - 5);
            cs.stroke();
            
            // Data
            cs.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
            int rowY = y - 25;
            for (int row = 0; row < modelTabel.getRowCount(); row++) {
                x = 50;
                for (int col = 0; col < modelTabel.getColumnCount(); col++) {
                    String value = modelTabel.getValueAt(row, col).toString();
                    if (value.length() > 20) value = value.substring(0, 17) + "...";
                    cs.beginText();
                    cs.newLineAtOffset(x, rowY);
                    cs.showText(value);
                    cs.endText();
                    x += 90;
                }
                rowY -= 20;
                if (rowY < 50) {
                    cs.close();
                    page = new PDPage(PDRectangle.A4);
                    document.addPage(page);
                    cs = new PDPageContentStream(document, page);
                    // Set font lagi untuk halaman baru
                    cs.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
                    rowY = 750;
                }
            }
            
            // Total
            cs.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 12);
            cs.beginText();
            cs.newLineAtOffset(50, rowY - 10);
            cs.showText(lblTotal.getText());
            cs.endText();
            
            cs.close();
            document.save(chooser.getSelectedFile());
            JOptionPane.showMessageDialog(this, "Export PDF berhasil!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal export PDF: " + e.getMessage());
        }
    }
    }//GEN-LAST:event_btnPdfActionPerformed

    private void btnPembayaran3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPembayaran3ActionPerformed
        if (pembayaranFrame == null) pembayaranFrame = new pembayaran.PembayaranFrame();
        pembayaranFrame.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnPembayaran3ActionPerformed

    private void btnTampilkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTampilkanActionPerformed
        loadData();
    }//GEN-LAST:event_btnTampilkanActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new LaporanFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ScrollPane;
    private javax.swing.JButton btnDashboard3;
    private javax.swing.JButton btnDataKamar3;
    private javax.swing.JButton btnDataPenghuni3;
    private javax.swing.JButton btnExcel;
    private javax.swing.JButton btnLaporan3;
    private javax.swing.JButton btnLogout3;
    private javax.swing.JButton btnPdf;
    private javax.swing.JButton btnPembayaran3;
    private javax.swing.JButton btnTampilkan;
    private javax.swing.JComboBox<String> cbBulan;
    private javax.swing.JComboBox<String> cbTahun;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblHeader;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel month;
    private config.RoundedPanel panelContent;
    private javax.swing.JPanel panelFilter;
    private javax.swing.JPanel panelMain;
    private javax.swing.JPanel panelSidebar3;
    private config.RoundedPanel roundedPanel1;
    private javax.swing.JTable tblLaporanPembayaran;
    // End of variables declaration//GEN-END:variables
}
