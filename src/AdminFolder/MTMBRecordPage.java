package AdminFolder;
import javax.swing.*;
import javax.swing.border.*;

import CustomClassLoader.FontLoader;
import CustomClassLoader.RoundButton;
import DatabaseConnection.MTMBDBArchive;
import UserFolder.MTMBLogin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MTMBRecordPage extends JPanel {

    // Define a class-level variable to store the table name
    private String tableName;
    private JPanel TableArchive;
    private JScrollPane scrollPane;

    public MTMBRecordPage() {
        initialize();
    }

    private void initialize() {
        // Define your custom font styles
        Font PrimaryFont = FontLoader.getFont("Primary", 64);
        Font PrimaryFont32 = FontLoader.getFont("Primary", 32);
        Font SecondaryFont = FontLoader.getFont("Secondary", 24);
        Font PrimaryEBFont = FontLoader.getFont("PrimaryEB32", 24);
        Font SemiB = FontLoader.getFont("SemiB", 24);
        Font PrimaryEB48Font = FontLoader.getFont("PrimaryEB32", 48);
        Font Bold = FontLoader.getFont("Bold", 28);
        Font Bold2 = FontLoader.getFont("Bold", 16);

        setBounds(292, 0, 736, 768);
        setLayout(null);

        JPanel RecordPanel = new JPanel();
        RecordPanel.setBounds(0, 0, 736, 768);
        RecordPanel.setLayout(null);

        // Header
        JPanel InsideRecordPanel = new JPanel();
        InsideRecordPanel.setBounds(0, 0, 736, 70);
        InsideRecordPanel.setLayout(null);

        JLabel Record = new JLabel("Records");
        Record.setBounds(30, 23, 189, 36);
        Record.setFont(SecondaryFont);
        InsideRecordPanel.add(Record);
        RecordPanel.setLayout(null);

        JLabel lblYear = new JLabel("Monthly Archive");
        lblYear.setFont(PrimaryEBFont);
        lblYear.setBounds(10, 36, 241, 33);
        RecordPanel.add(lblYear);
        add(RecordPanel);

        // Create New Button
        RoundButton btnNewButton = new RoundButton("Create New +", 8, Color.decode("#FFBA42"));
        btnNewButton.setFont(Bold2);
        btnNewButton.setBounds(543, 36, 171, 38);
        RecordPanel.add(btnNewButton);

        // Action listener for the Create New button
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create and display the CreateNew window
                CreateNew createNewWindow = new CreateNew(MTMBRecordPage.this); // Pass instance of MTMBRecordPage
                createNewWindow.frame.setVisible(true);
            }
        });


        // Create a JScrollPane for TableArchive
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 92, 736, 665);
        RecordPanel.add(scrollPane);

        TableArchive = new JPanel();
        TableArchive.setLayout(new GridLayout(0, 2, 10, 10)); // 2 columns, with horizontal and vertical gaps
        scrollPane.setViewportView(TableArchive);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Refresh tables initially
        refreshTables();
    }

    // Method to format table name (remove white spaces and convert to lowercase)
    private String formatTableName(String tableName) {
        return tableName.replaceAll("\\s", "_").toLowerCase();
    }

    public void refreshTables() {
        // Clear the existing panels in TableArchive
        TableArchive.removeAll();

        // Get table names from the database
        MTMBDBArchive dbArchive = new MTMBDBArchive();
        String[] tableNames = dbArchive.getTableNames();
        Font PrimaryFont = FontLoader.getFont("Primary", 64);
        Font PrimaryFont32 = FontLoader.getFont("Primary", 32);
        Font SecondaryFont = FontLoader.getFont("Secondary", 24);
        Font PrimaryEBFont = FontLoader.getFont("PrimaryEB32", 24);
        Font SemiB = FontLoader.getFont("SemiB", 24);
        Font PrimaryEB48Font = FontLoader.getFont("PrimaryEB32", 48);
        Font Bold = FontLoader.getFont("Bold", 28);
        Font Bold2 = FontLoader.getFont("Bold", 16);
        
        // Iterate over the table names and create panels
        for (String tableName : tableNames) {
            JPanel panel = new JPanel();
            panel.setBorder(new CompoundBorder(new LineBorder(Color.BLACK, 2), new EmptyBorder(20, 20, 20, 10))); // Add an empty border with 10 pixels margin
            panel.setPreferredSize(new Dimension(301, 214)); // Set fixed size of each panel
            panel.setMaximumSize(new Dimension(301, 214)); // Set maximum size to ensure fixed size
            panel.setLayout(new BorderLayout()); // Use BorderLayout for positioning
            Color baseColor = new Color(132, 132, 132);
            Color lighterColor = new Color(baseColor.getRed(), baseColor.getGreen(), baseColor.getBlue(), 63); // 63 corresponds to 25% alpha (0.25 * 255 = 63.75, rounded to nearest integer)
            panel.setBackground(lighterColor);
            TableArchive.add(panel);

            JLabel ArchiveTitle = new JLabel(tableName);
            ArchiveTitle.setFont(PrimaryFont32);
            panel.add(ArchiveTitle, BorderLayout.NORTH);

            JLabel RecordLabel = new JLabel("Record");
            RecordLabel.setFont(Bold);
            panel.add(RecordLabel, BorderLayout.CENTER);

            ImageIcon arrowedButton = new ImageIcon(MTMBRecordPage.class.getResource("/Icons/ArrowedButton.png"));
            JLabel buttoned = new JLabel("");
            buttoned.setIcon(arrowedButton);
            panel.add(buttoned, BorderLayout.SOUTH);
            buttoned.setHorizontalAlignment(SwingConstants.RIGHT);
            buttoned.setVerticalAlignment(SwingConstants.BOTTOM);

            buttoned.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Get the table name from the label
                    String tableName = ArchiveTitle.getText();
                    // Format the table name (remove underscores and convert to lowercase)
                    tableName = formatTableName(tableName);
                    // Create and show the ViewRecords window with the formatted table name
                    ViewRecords viewRecords = new ViewRecords(tableName);
                    viewRecords.getFrame().setVisible(true);
                }

                public void mouseEntered(MouseEvent e) {
                    buttoned.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    buttoned.setCursor(Cursor.getDefaultCursor());
                }
            });
        }

        // Update the layout and repaint the panel
        TableArchive.revalidate();
        TableArchive.repaint();
    }
}
