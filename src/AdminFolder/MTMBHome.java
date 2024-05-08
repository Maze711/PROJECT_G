package AdminFolder;
import java.awt.*;
import javax.swing.*;

import CustomClassLoader.FontLoader;
import DatabaseConnection.MTMBDBCONN;
import UserFolder.MTMBLogin;

import java.sql.*;

public class MTMBHome extends JPanel {

    private final MTMBDBCONN conn = new MTMBDBCONN();

    public MTMBHome(String username) { // Modified constructor to accept username
        initialize(username);
    }

    private void initialize(String username) {
    	
//		CUSTOM FONT FAMILY STYLE
		Font PrimaryFont = FontLoader.getFont("Primary", 64);
		Font SecondaryFont = FontLoader.getFont("Secondary", 24);
		Font PrimaryEBFont = FontLoader.getFont("PrimaryEB32", 24);
		Font SemiB = FontLoader.getFont("SemiB", 24);
		Font PrimaryEB48Font = FontLoader.getFont("PrimaryEB32", 48);
		Font Bold = FontLoader.getFont("Bold", 28);
		Font Bold2 = FontLoader.getFont("Bold", 16);
		
        setBounds(0, 0, 1045, 790);
        setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(0, 0, 736, 768);
        add(panel_1);
        panel_1.setLayout(null);

        // Header
        JPanel panel_2 = new JPanel();
        panel_2.setBounds(0, 0, 736, 70);
        panel_1.add(panel_2);
        panel_2.setLayout(null);

        JLabel txtWelcome = new JLabel("Home");
        txtWelcome.setBounds(30, 23, 189, 36);
        txtWelcome.setFont(PrimaryEBFont);
        panel_2.add(txtWelcome);

        ImageIcon wave = new ImageIcon(MTMBHome.class.getResource("/Icons/Waving Hand Emoji.png"));

        JLabel txtUsertype = new JLabel("Hello Admin: " + username);
        txtUsertype.setFont(Bold2);
        txtUsertype.setBounds(490, 26, 236, 36);
        panel_2.add(txtUsertype);

        JPanel panel_3 = new JPanel();
        panel_3.setBounds(0, 71, 735, 698);
        panel_1.add(panel_3);
        panel_3.setLayout(null);

        JLabel txtCurrentMnt = new JLabel("April 2024");
        txtCurrentMnt.setFont(Bold);
        txtCurrentMnt.setBounds(30, 16, 286, 40);
        panel_3.add(txtCurrentMnt);

        ImageIcon car = new ImageIcon(MTMBHome.class.getResource("/Icons/Car.png"));
        JLabel ITotal = new JLabel("");
        ITotal.setIcon(car);
        ITotal.setBounds(270, 110, 135, 40);
        panel_3.add(ITotal);

        int impoundCount = getImpoundCount();

        JLabel txtTotalVehicle = new JLabel(String.valueOf(impoundCount));
        txtTotalVehicle.setHorizontalAlignment(SwingConstants.CENTER);
        txtTotalVehicle.setForeground(Color.WHITE);
        txtTotalVehicle.setFont(Bold);
        txtTotalVehicle.setBounds(37, 96, 274, 49);
        panel_3.add(txtTotalVehicle);

        JLabel txtTotalDisplay = new JLabel("Total Vehicle Impounded ");
        txtTotalDisplay.setForeground(new Color(255, 255, 255));
        txtTotalDisplay.setHorizontalAlignment(SwingConstants.CENTER);
        txtTotalDisplay.setFont(Bold2);
        txtTotalDisplay.setBounds(37, 140, 274, 40);
        panel_3.add(txtTotalDisplay);

        int releaseCount = getReleaseCount();

        JLabel txtTotalVehicle_2 = new JLabel(String.valueOf(releaseCount));
        txtTotalVehicle_2.setHorizontalAlignment(SwingConstants.CENTER);
        txtTotalVehicle_2.setForeground(Color.WHITE);
        txtTotalVehicle_2.setFont(Bold);
        txtTotalVehicle_2.setBounds(440, 96, 274, 49);
        panel_3.add(txtTotalVehicle_2);

        JLabel lblTotalVehicleReleased = new JLabel("Total Vehicle Released ");
        lblTotalVehicleReleased.setHorizontalAlignment(SwingConstants.CENTER);
        lblTotalVehicleReleased.setForeground(Color.WHITE);
        lblTotalVehicleReleased.setFont(Bold2);
        lblTotalVehicleReleased.setBounds(440, 140, 274, 40);
        panel_3.add(lblTotalVehicleReleased);

        ImageIcon total = new ImageIcon(MTMBHome.class.getResource("/Images/Total.png"));
        JLabel bgTotal_1 = new JLabel("");
        bgTotal_1.setIcon(total);
        bgTotal_1.setBounds(30, 67, 393, 125);
        panel_3.add(bgTotal_1);

        ImageIcon total1 = new ImageIcon(MTMBHome.class.getResource("/Images/Total-1.png"));
        JLabel bgTotal_2 = new JLabel("");
        bgTotal_2.setIcon(total1);
        bgTotal_2.setBounds(436, 67, 270, 125);
        panel_3.add(bgTotal_2);

        JLabel txtInfo = new JLabel("Daily Impounded Records");
        txtInfo.setFont(SemiB);
        txtInfo.setBounds(31, 205, 361, 40);
        panel_3.add(txtInfo);
        
        ImageIcon chart = new ImageIcon(MTMBLogin.class.getResource("/Images/Chart.png"));
        JLabel Chart = new JLabel("");
        Chart.setIcon(chart);
        Chart.setBounds(30, 243, 672, 352);
        panel_3.add(Chart);
    }

    private int getImpoundCount() {
        int count = 0;
        try (Connection connection = conn.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS count FROM april WHERE Status = 'Impounded'")) {
            if (resultSet.next()) {
                count = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    private int getReleaseCount() {
        int count = 0;
        try (Connection connection = conn.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS count FROM april WHERE Status = 'Released'")) {
            if (resultSet.next()) {
                count = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
}
