import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class busqueda {
    private JTextField cedBus;
    private JTextField emaBus;
    private JTextField nomBus;
    private JTextField celBus;
    private JButton buscarButton;
    private JButton actualizarButton;
    private JPanel bus;
    private JButton eliminarButton;
    PreparedStatement pd;
    TextPrompt tp = new TextPrompt("Nombre",nomBus);
    TextPrompt tp1 = new TextPrompt("Celular",celBus);

    public busqueda() {
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection cx;
                try {
                    cx = getConecction();
                    String qr = "select * from estd where IDEst = "+cedBus.getText()+";";
                    Statement s = cx.createStatement();
                    ResultSet rs = s.executeQuery(qr);
                    System.out.println(rs);
                    while(rs.next()) {
                        nomBus.setText(rs.getString(2));
                        celBus.setText(rs.getString(3));
                        emaBus.setText(rs.getString(4));
                    }
                    cx.close();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection ct;
                try {
                    String qr = "Update estd set NomEst = ?, CelEst = ?, EmailEst = ? where IDEst = "+cedBus.getText();
                    ct = getConecction();
                    pd = ct.prepareStatement(qr);
                    pd.setString(1, nomBus.getText());
                    pd.setString(2, celBus.getText());
                    pd.setString(3, emaBus.getText());
                    pd.executeUpdate();
                    System.out.println(pd);
                    int res = pd.executeUpdate();
                    if (res > 0) {
                        JOptionPane.showMessageDialog(null,"Persona actualizada correctamente");
                    } else {
                        JOptionPane.showMessageDialog(null,"Error");
                    }
                    ct.close();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection cn;
                try {
                    String qr = "delete from estd where IDEst ="+cedBus.getText();
                    cn = getConecction();
                    pd = cn.prepareStatement(qr);
                    pd.executeUpdate();
                    int res = pd.executeUpdate();
                    if (res > 0) {
                        JOptionPane.showMessageDialog(null,"Persona eliminada  correctamente");
                    } else {
                        JOptionPane.showMessageDialog(null,"Error");
                    }
                    cn.close();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }


    public static Connection getConecction() {
        Connection cn = null;
        String base = "estudiantesN"; //Nombre de la BD
        String url = "jdbc:mysql://localhost:3306/" + base; //Direccion, puerto y nombre BD
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

    public static void main(String[] args) {
        JFrame jf = new JFrame("busqueda");
        jf.setContentPane(new busqueda().bus);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.pack();
        jf.setVisible(true);
        jf.setResizable(false);
        jf.setLocationRelativeTo(null);
    }
}
