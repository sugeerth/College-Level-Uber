
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class login extends JFrame implements ActionListener {

    public int gflag = 0;
    JPanel butpan = new JPanel();
    JPanel logpan = new JPanel();
    JPanel selpan = new JPanel();
    JPasswordField text2 = new javax.swing.JPasswordField(10);
    JLabel un = new JLabel("Login-ID");
    JTextField usr = new JTextField(10);
    JLabel pd = new JLabel("Password");
    JTextField pwd = new JTextField(10);
    JButton reset = new JButton("Reset");
    JButton log = new JButton("Login");
    JButton join = new JButton("Join");
    JRadioButton app = new JRadioButton("applicants");
    JRadioButton emp = new JRadioButton("employers");
    ButtonGroup bg = new ButtonGroup();
    //public int gflag=0;
    Connection clog;
    Statement st;
    int flag = 0;
    int i = 0;
    private JPanel jPanel1;
    private JLabel jLabel3;
    private JLabel jLabel1;
    private JLabel jLabel2;

    private void initComponents() {
        setTitle("AMRITA HUMAN RESOURCE MANAGEMENT SYSTEM   (AUHRMS)");
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        //  text1 = new javax.swing.JTextField();
        //text2 = new javax.swing.JPasswordField();
        //jButton1 = new javax.swing.JButton();
        //jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        //jPanel1.setBackground(new java.awt.Color(255, 102, 0));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HRM.jpg")));
        jLabel3.setBackground(Color.ORANGE);
        add(jLabel3);
        jLabel3.setVisible(true);
        jLabel3.setSize(400, 400);
        jLabel3.setLocation(500, 500);
        //pack();

    }

    public static void main(String args[]) {
        new login();
    }

    public login() {

        initComponents();



        getContentPane().add(butpan, BorderLayout.SOUTH);
        getContentPane().add(logpan, BorderLayout.WEST);
        getContentPane().add(selpan, BorderLayout.NORTH);
        getContentPane().add(jLabel3, BorderLayout.EAST);

        logpan.add(un);
        logpan.add(usr);
        logpan.add(pd);
        logpan.add(text2);
        logpan.setBackground(Color.ORANGE);

        butpan.add(reset);
        butpan.add(log);
        butpan.add(join);
        butpan.setBackground(Color.ORANGE);

        bg.add(app);
        bg.add(emp);
        setBackground(Color.ORANGE);

        selpan.add(app);
        selpan.add(emp);
        selpan.setBackground(Color.ORANGE);


        setVisible(true);
        setSize(100, 100);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();

        reset.addActionListener(this);
        log.addActionListener(this);
        join.addActionListener(this);



    }

    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source == reset) {
            usr.setText("");
            pwd.setText("");
        } else if (source == join) {
            if (app.isSelected()) {
                new app();
                setVisible(false);
            } else if (emp.isSelected()) {
                new emp();
                setVisible(false);
            }

        } else if (source == log) {

            /*Log in of Applicants start from here*/

            if (app.isSelected()) {
                try {
                    //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                    //System.out.println("DATABASE RECOVERY SUCCESSFULL!");
                    //clog = DriverManager.getConnection("jdbc:odbc:go");
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    clog=DriverManager.getConnection("jdbc:mysql:///go","root","");
                    System.out.println("DATABASE RECOVERY SUCCESSFULL!");
                    st = clog.createStatement();
                    ResultSet rsLog1 = st.executeQuery("select * from applicant");
                    System.out.println("DATABASE RECOVERY SUCCESSFULL!");
                    while (rsLog1.next()) {
                        boolean b1 = rsLog1.getString(8).equals((String) usr.getText());
                        boolean b2 = rsLog1.getString(9).equals((String) text2.getText());
                        if (b1 && b2) {
                            JOptionPane.showMessageDialog(null, "LOGIN SUCCESSFULL Done    \nMr." +rsLog1.getString(1));
                            JOptionPane.showMessageDialog(null, "\t\t     CLICK OK \nThe Companies That have a Vacancy in your field Are:\n");
                            //Remove this line
                            gflag = 0;
                            mainform1 a = new mainform1(gflag, (String) usr.getText(), (String) text2.getText());
                            System.out.println("Retreiving the fields!!");
                            flag = 1;
                            setVisible(false);
                            //gflag=1;
                            break;
                        }
                    }
                    if (flag != 1 && i <= 3) {
                        ++i;
                        JOptionPane.showMessageDialog(null, "Invalid Entry PLEASE TRY AGAIN!!!");
                    } else if (flag != 1 && i > 3) {
                        System.exit(0);
                    } else if (flag == 1) {
                        setVisible(false);
                    }
                } catch (Exception excp) {
                    JOptionPane.showMessageDialog(null, excp);
                    System.exit(0);
                }
            } /*log in of Employers Start from here*/ else if (emp.isSelected()) {
                try {
                    //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                    //clog = DriverManager.getConnection("jdbc:odbc:go");
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
            clog=DriverManager.getConnection("jdbc:mysql:///go","root","");
                    st = clog.createStatement();
                    ResultSet rsLog2 = st.executeQuery("select * from company");

                    while (rsLog2.next()) {
                        boolean b1 = rsLog2.getString(5).equals((String) usr.getText());
                        boolean b2 = rsLog2.getString(6).equals((String) text2.getText());
                        if (b1 && b2) {
                            String name = rsLog2.getString(1);
                            JOptionPane.showMessageDialog(null, "LOGIN SUCCESSFULL Done    \n" + name + "   Firm\nVacany:          " + rsLog2.getString(2));
                            JOptionPane.showMessageDialog(null, "WELCOME " + name + " IT IS A PLEASURE WORKING WITH YOUR GROUP \nThe Selected Applicants that you might be interested in are");
                            //new appdetail(rsLog2.getInt(4));
                            gflag = 1;
                              System.out.println("DATABASE RECOVERY SUCCESSFULL!");
                            mainform1 a = new mainform1(gflag, ((String) usr.getText()), ((String) text2.getText()));
                            flag = 1;
                            setVisible(false);
                            // a.setSize(800, 500);
                            //flag=0;
                            break;
                        }
                    }
                    if (flag != 1 && i <= 3) {
                        ++i;
                        JOptionPane.showMessageDialog(null, "Invalid Entry PLEASE TRY AGAIN SIR");
                    } else if (flag != 1 && i > 3) {
                        System.exit(0);
                    } else if (flag == 1) {
                        setVisible(false);
                    }
                } catch (Exception excp) {
                    System.out.println("not working the emp");
                    JOptionPane.showMessageDialog(null, excp);
                    System.exit(0);
                }
            }
        }
    }
}
