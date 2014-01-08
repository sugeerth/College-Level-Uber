
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class empdetail extends JFrame {

    public static void main(String args[]) {

        new empdetail(2);
    }
    JTable tab;
    JPanel tpan = new JPanel();
    JPanel bpan = new JPanel();
    String header[] = {"Name", "Vacancy", "Salary"};
    Object data[][] = new String[15][3];
    JButton check = new JButton("OK");
    JButton back = new JButton("BACK");
    int i, j, ki;

    public empdetail(int k) {


        this.ki = k;


        setVisible(true);
        setSize(600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().add(tpan, BorderLayout.CENTER);
        getContentPane().add(bpan, BorderLayout.SOUTH);
        tpan.setBackground(Color.ORANGE);
        bpan.setBackground(Color.ORANGE);
        bpan.add(check);
        check.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
                String name = JOptionPane.showInputDialog("CAN WE HAVE YOUR FEEDBACK ABOUT THIS APPLICATION\n\n");
                JOptionPane.showMessageDialog(null, "THANK YOU DO ACCESS OUR APPLICATION SOME OTHER TIME :-)");
                System.exit(0);
            }
        });
        bpan.add(back);
        back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
                setVisible(false);
                new login();

            }
        });

        try {
            //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            //Connection conn = DriverManager.getConnection("jdbc:odbc:go");
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection conn=DriverManager.getConnection("jdbc:mysql:///go","root","");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from company where Field=" + ki);
            System.out.println("the ki value is" + ki);
            while (rs.next()) {
                System.out.println(ki);
                for (j = 0; j <= 2; j++) {
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
        pack();

    }
}
