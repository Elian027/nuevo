import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class fr {
    PreparedStatement ps;
    private JPanel pan1;
    private JButton guardarButton;
    private JTextField txtId;
    private JTextField txtNom;
    private JTextField txtCel;
    private JTextField txtEm;
    private JLabel IdEst;
    private JLabel NomEst;
    private JLabel CelEst;
    private JLabel EmailEst;

    public static Connection getConecction() {
        Connection cn = null;
        String base = "estudiantesN"; //Nombre de la BD
        String url = "jdbc:mysql://localhost/" + base; //Direccion, puerto y nombre BD
        String user = "root"; //Usuario
        String pass = "dariel17"; //Contraseña
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection(url,user,pass);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        return cn;
    }
    public fr() {
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection cn;
                try {
                    cn = getConecction();
                    ps = cn.prepareStatement("INSERT INTO estd (IDEst, NomEst, CelEst, EmailEst) values (?,?,?,?)");
                    ps.setString(1, txtId.getText());
                    ps.setString(2, txtNom.getText());
                    ps.setString(3, txtCel.getText());
                    ps.setString(4, txtEm.getText());
                    System.out.println(ps);
                    int res = ps.executeUpdate();
                    if (res > 0) {
                        JOptionPane.showMessageDialog(null,"Persona guardada");
                    } else {
                        JOptionPane.showMessageDialog(null,"Error");
                    }

                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame fr = new JFrame("fr");
        fr.setContentPane(new fr().pan1);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.pack();
        fr.setVisible(true);
        fr.setResizable(false);
        fr.setLocationRelativeTo(null);
    }



}
