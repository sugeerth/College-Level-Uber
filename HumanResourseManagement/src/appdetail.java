
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class appdetail extends JFrame {

    JTable tab;
    JPanel tpan = new JPanel();
    JPanel bpan = new JPanel();
    //JPanel btpan=new JPanel();
    String header[] = {"Name", "Age", "Sex", "Education", "Co-curriculars", "Salary"};
    Object data[][] = new String[25][6];
    JButton check = new JButton("OK");
    JButton back1 = new JButton("LOGOUT");
    int i, j, ki;
    //back1.addActionListener(this);

    @SuppressWarnings("LeakingThisInConstructor")
    public appdetail(int k) {
        setTitle("AMRITA HUMAN RESOURCE MANAGEMENT SYSTEM    (APPLICANT DETAIL)");

        this.ki = k;
        setVisible(true);
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        getContentPane().add(tpan, BorderLayout.CENTER);
        getContentPane().add(bpan, BorderLayout.SOUTH);
        tpan.setBackground(Color.ORANGE);
        bpan.setBackground(Color.ORANGE);
        bpan.add(check);
        bpan.add(back1);
        //check.addActionListener(this);
        //back1.addActionListener(this);

        try {
            //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            //Connection conn = DriverManager.getConnection("jdbc:odbc:go");
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection conn=DriverManager.getConnection("jdbc:mysql:///go","root","");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from applicant where Field=" + ki);
            while (rs.next()) {
                System.out.println("the value of field is" + ki);
                for (j = 0; j <= 5; j++) {
                    data[i][j] = rs.getString(j + 1);
                }
                i++;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, data[1][0]);
            System.exit(0);
        }
        tab = new JTable(data, header);
        tab.setPreferredScrollableViewportSize(new Dimension(600, 600));
        JScrollPane sp = new JScrollPane(tab);
        tpan.add(sp);
        check.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                //throw new UnsupportedOperationException("Not supported yet.");
                checkActionPerformed(evt);
            }

            private void checkActionPerformed(ActionEvent evt) {
                //throw new UnsupportedOperationException("Not yet implemented");
                String name = JOptionPane.showInputDialog("CAN WE HAVE YOUR FEEDBACK ABOUT THIS APPLICATION\n\n");
                JOptionPane.showMessageDialog(null, "THANK YOU DO ACCESS OUR APPLICATION SOME OTHER TIME :-)");

                System.exit(0);
            }
        });
        back1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
                setVisible(false);
                new login();

            }
        });
        pack();

        try {
            // check.addActionListener();
            // back1.addActionListener((ActionListener) this);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            System.exit(0);
        }
    }

    public static void main(String args[]) {
        new appdetail(1);
    }
}
