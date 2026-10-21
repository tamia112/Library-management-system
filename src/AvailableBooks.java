
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Administrator
 */

public class AvailableBooks extends javax.swing.JFrame {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/lms?zeroDateTimeBehavior=CONVERT_TO_NULL";
    private static final String DB_USER = "root"; // Default XAMPP user
    private static final String DB_PASS = ""; // Default XAMPP password (empty)
   private TableRowSorter<DefaultTableModel> rowSorter;
DefaultTableModel model;


    
    
    /**loadAvailableBooks();
     * Creates new form AvailableBooks
     */
    public AvailableBooks() {
        initComponents();
          jTable1.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

    model = (DefaultTableModel) jTable1.getModel();
    rowSorter = new TableRowSorter<>(model);
    jTable1.setRowSorter(rowSorter);

    loadBOOKData(); 
loadAvailableBooks();// OR use loadAvailableBooks(); based on your requirement

    // ActionListener for Search button
    jButton3.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            applyRowFilter();
        }
    });

    // KeyListener for real-time search
    jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            applyRowFilter();
        }
    });
    
    }
    @SuppressWarnings("CallToPrintStackTrace")
    private void loadBOOKData() {
         model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Clear existing table rows

        // Query to get student data
 String sql = " SELECT BookID,  Title, Author, Publisher, Edition, ISBN, Category ,CopiesAvailable FROM books";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // Loop through the result set and add data to the table
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("BookID"), // Assuming StudentID is an integer
                    
                    
                    
                   rs.getString ("Title" ),
        rs.getString("Author"),
        rs.getString("Publisher"),
        rs.getString("Edition"),
       rs.getString ("ISBN"),
       rs.getString ("Category"),
       rs.getString("CopiesAvailable"),

                    
                };
                model.addRow(row);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    private void loadAvailableBooks() {
     model = (DefaultTableModel) jTable1.getModel();
    model.setRowCount(0); // Clear old rows

    String sql = "SELECT * FROM books WHERE BookID NOT IN (SELECT BookID FROM bookissue)";

    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Object[] row = {
                rs.getInt("BookID"),
                rs.getString("Title"),
                rs.getString("Author"),
                rs.getString("Publisher"),
                rs.getString("Edition"),
                rs.getString("ISBN"),
                rs.getString("Category"),
                rs.getInt("CopiesAvailable"),
                rs.getInt("TotalCopies")
            };
            model.addRow(row);
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error loading available books: " + e.getMessage());
    }
}

private void applyRowFilter() {
    String searchText = jTextField1.getText().trim();
    int selectedColumnIndex = jComboBox1.getSelectedIndex();

    // Map ComboBox index to actual table column index
    int tableColumnIndex;
    switch (selectedColumnIndex) {
        case 0: tableColumnIndex = 1; break; // Title
        case 1: tableColumnIndex = 2; break; // Author
        case 2: tableColumnIndex = 4; break; // Edition
        case 3: tableColumnIndex = 6; break; // Category
        default: tableColumnIndex = 1;
    }

    if (searchText.length() == 0) {
        rowSorter.setRowFilter(null); // Reset filter
    } else {
        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText, tableColumnIndex));
    }
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jToggleButton1 = new javax.swing.JToggleButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        jToggleButton1.setText("jToggleButton1");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/new123456.png"))); // NOI18N
        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(325, 125));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Available Books");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 60, -1, -1));

        jTable1.setBackground(new java.awt.Color(255, 255, 204));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "BookID", "Title", "Author", "Publisher", "Edition", "ISBN", "Category", "CopiesAvailable"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 620, 288));

        jButton1.setBackground(new java.awt.Color(255, 255, 204));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/close.jpg"))); // NOI18N
        jButton1.setText("Close");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(571, 398, -1, -1));

        jButton2.setBackground(new java.awt.Color(255, 255, 204));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/issue book.png"))); // NOI18N
        jButton2.setText("Request");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(414, 398, -1, -1));

        jTextField1.setBackground(new java.awt.Color(255, 255, 204));
        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, 290, -1));

        jComboBox1.setBackground(new java.awt.Color(255, 255, 204));
        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Title", " Author", " Edition", "Category" }));
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, -1, -1));

        jButton3.setBackground(new java.awt.Color(255, 255, 204));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/search.png"))); // NOI18N
        jButton3.setText("Search");
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/123456.png"))); // NOI18N
        jLabel4.setText("jLabel4");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents
  
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
         int[] selectedRows = jTable1.getSelectedRows();

        if (selectedRows.length == 0) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please select at least one book.");
            return;
        }

        for (int row : selectedRows) {
            String BookID = jTable1.getValueAt(row, 0).toString();
            String Title = jTable1.getValueAt(row, 1).toString();
            String Author = jTable1.getValueAt(row, 2).toString();
            String Publisher = jTable1.getValueAt(row, 3).toString();
            String Edition = jTable1.getValueAt(row, 4).toString();
            String ISBN = jTable1.getValueAt(row, 5).toString();
            String Category = jTable1.getValueAt(row, 6).toString();

            Bookissue bookissuePage = new Bookissue();
            bookissuePage.setVisible(true);
            bookissuePage.fillBookDetails(BookID, Title, Author, Publisher, Edition, ISBN, Category);
    
    
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AvailableBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AvailableBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AvailableBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AvailableBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AvailableBooks().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables
}
