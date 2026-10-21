/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Administrator
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.util.Date;
import java.text.SimpleDateFormat;
public class Return extends javax.swing.JFrame {
private static final String DB_URL = "jdbc:mysql://localhost:3306/lms";
private static final String DB_USER = "root";
private static final String DB_PASS = "";
    /**
     * Creates new form Return
     */

    public Return() {
        initComponents();
        
    }
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private void returnBook() {
    String bookId = BookID.getText().trim();
    String studentId = StudentID.getText().trim();

    if (bookId.isEmpty() || studentId.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter both BookID and StudentID.");
        return;
    }

    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
        

        // Step 1: Check if book was issued
        String sqlCheck = "SELECT * FROM bookissue WHERE BookID = ? AND StudentID = ?";
        PreparedStatement psCheck = conn.prepareStatement(sqlCheck);
        psCheck.setString(1, bookId);
        psCheck.setString(2, studentId);
        ResultSet rs = psCheck.executeQuery();

        if (!rs.next()) {
            JOptionPane.showMessageDialog(this, "Book was not issued.");
            return;
        }

        // Assuming: 'rs' is your ResultSet, 'studentId' is the current Student ID, and sdf is already defined

String issueDateStr = rs.getString("Issuedate");
Date issueDate = sdf.parse(issueDateStr);
Date today = new Date();
String returnDateStr = sdf.format(today);

// Calculate days between issue date and today
long diffInMillis = today.getTime() - issueDate.getTime();
long diffInDays = diffInMillis / (1000 * 60 * 60 * 24);

// Fine calculation
int fineAmount = 0;
int daysOverdue = 0;

if (diffInDays > 14) {
    daysOverdue = (int) (diffInDays - 14);
    fineAmount = daysOverdue * 50; // e.g., 50 KSH per day

    // Show Overdue Notification Dialog
    OverdueNotification notify = new OverdueNotification(this, studentId, daysOverdue, fineAmount);
    notify.setVisible(true);
}

// Update Fine and Days Overdue in GUI Text Fields
jTextField2.setText("KSH " + fineAmount);
jTextField1.setText(String.valueOf(daysOverdue));

        
        // Step 2: Insert return record into newbook table
        String insertSQL = "INSERT INTO newbook (BookID, StudentID, Issuedate, Returndate, overduedays, Finecalculate) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement psInsert = conn.prepareStatement(insertSQL);
        psInsert.setString(1, bookId);
        psInsert.setString(2, studentId);
        psInsert.setString(3, issueDateStr);
        psInsert.setString(4, returnDateStr);
        psInsert.setInt(5, daysOverdue);
        psInsert.setInt(6, fineAmount);
        psInsert.executeUpdate();

        // Step 3: Delete from bookissue
        String deleteSQL = "DELETE FROM bookissue WHERE BookID = ? AND StudentID = ?";
        PreparedStatement psDelete = conn.prepareStatement(deleteSQL);
        psDelete.setString(1, bookId);
        psDelete.setString(2, studentId);
        psDelete.executeUpdate();

        // Step 4: Update copies in books table
        String updateSQL = "UPDATE books SET CopiesAvailable = CopiesAvailable + 1 WHERE BookID = ?";
        PreparedStatement psUpdate = conn.prepareStatement(updateSQL);
        psUpdate.setString(1, bookId);
        psUpdate.executeUpdate();

        JOptionPane.showMessageDialog(this, "Book returned and recorded successfully.");

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
}


private void searchIssuedBook() {
    String bookId = BookID.getText().trim();
    String studentId = StudentID.getText().trim();

    if (bookId.isEmpty() || studentId.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter both BookID and StudentID.");
        return;
    }

    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
        String sql = "SELECT Issuedate, Returndate FROM bookissue WHERE BookID = ? AND StudentID = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, bookId);
        stmt.setString(2, studentId);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            String issuedate = rs.getString("Issuedate");
            String returndate = rs.getString("Returndate");

            IssueDate.setText(issuedate);
            ReturnDate.setText(returndate);

        } else {
            JOptionPane.showMessageDialog(this, "No record found for the given BookID and StudentID.");
            IssueDate.setText("");
            ReturnDate.setText("");
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage());
        e.printStackTrace();
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

        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        BookID = new javax.swing.JTextField();
        StudentID = new javax.swing.JTextField();
        IssueDate = new javax.swing.JTextField();
        ReturnDate = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/123456.png"))); // NOI18N
        jLabel5.setText("jLabel5");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(325, 125));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("BookID");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(214, 143, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("StudentID");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(214, 187, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Issuedate");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(214, 233, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Returndate");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(214, 278, -1, -1));

        jButton1.setBackground(new java.awt.Color(255, 255, 204));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/return book png.png"))); // NOI18N
        jButton1.setText("Return");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(295, 402, -1, -1));

        BookID.setBackground(new java.awt.Color(255, 255, 204));
        getContentPane().add(BookID, new org.netbeans.lib.awtextra.AbsoluteConstraints(391, 141, 115, -1));

        StudentID.setBackground(new java.awt.Color(255, 255, 204));
        getContentPane().add(StudentID, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 180, 115, -1));

        IssueDate.setBackground(new java.awt.Color(255, 255, 204));
        getContentPane().add(IssueDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(391, 231, 115, -1));

        ReturnDate.setBackground(new java.awt.Color(255, 255, 204));
        getContentPane().add(ReturnDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(391, 276, 115, -1));

        jButton2.setBackground(new java.awt.Color(255, 255, 204));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/close.jpg"))); // NOI18N
        jButton2.setText("Close");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(553, 402, -1, -1));

        jButton3.setBackground(new java.awt.Color(255, 255, 204));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/search.png"))); // NOI18N
        jButton3.setText("search");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 160, -1, -1));

        jLabel6.setBackground(new java.awt.Color(255, 255, 204));
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Days Overdue");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 310, 100, -1));

        jButton4.setBackground(new java.awt.Color(255, 255, 204));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton4.setText("Calculate Fine");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 350, 130, -1));

        jTextField1.setBackground(new java.awt.Color(255, 255, 204));
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 310, 120, -1));

        jTextField2.setBackground(new java.awt.Color(255, 255, 204));
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 350, 120, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/123456.png"))); // NOI18N
        jLabel7.setText("jLabel7");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        searchIssuedBook();

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        returnBook();
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
         returnBook();
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(Return.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Return.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Return.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Return.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Return().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField BookID;
    private javax.swing.JTextField IssueDate;
    private javax.swing.JTextField ReturnDate;
    private javax.swing.JTextField StudentID;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
