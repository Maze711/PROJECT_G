import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class MTMBRecordPage extends JPanel {

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
        lblYear.setBounds(10, 65, 241, 33);
        RecordPanel.add(lblYear);
        add(RecordPanel);

        // Create New Button
        RoundButton btnNewButton = new RoundButton("Create New +", 8, Color.decode("#FFBA42"));
        btnNewButton.setFont(Bold2);
        btnNewButton.setBounds(530, 11, 171, 38);
        RecordPanel.add(btnNewButton);

        // Action listener for the Create New button
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create and display the CreateNew window
                CreateNew createNewWindow = new CreateNew();
                createNewWindow.frame.setVisible(true);
            }
        });

        RoundTxtField SearchBar = new RoundTxtField(18, new Color(132, 132, 132), 1);
        SearchBar.setText("Search");
        SearchBar.setBounds(10, 11, 292, 38);
        SearchBar.setFont(Bold2);
        RecordPanel.add(SearchBar);
        SearchBar.setColumns(10);

        // Create a JScrollPane for TableArchive
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 109, 736, 648);
        RecordPanel.add(scrollPane);

        JPanel TableArchive = new JPanel();
        TableArchive.setLayout(new GridLayout(0, 2, 10, 10)); // 2 columns, with horizontal and vertical gaps
        scrollPane.setViewportView(TableArchive);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Get table names from the database
        MTMBDBArchive dbArchive = new MTMBDBArchive();
        String[] tableNames = dbArchive.getTableNames();

        // Iterate over the table names and create panels
        for (String tableName : tableNames) {
            JPanel panel = new JPanel();
            panel.setBorder(new CompoundBorder(new LineBorder(Color.BLACK, 2), new EmptyBorder(20, 20, 20, 10))); // Add an empty border with 10 pixels margin
            panel.setPreferredSize(new Dimension(301, 214)); // Set fixed size of each panel
            panel.setMaximumSize(new Dimension(301, 214)); // Set maximum size to ensure fixed size
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Align components vertically
            Color baseColor = new Color(132, 132, 132);
            Color lighterColor = new Color(baseColor.getRed(), baseColor.getGreen(), baseColor.getBlue(), 63); // 63 corresponds to 25% alpha (0.25 * 255 = 63.75, rounded to nearest integer)
            panel.setBackground(lighterColor);
            TableArchive.add(panel);
            TableArchive.setPreferredSize(new Dimension(716, (int) (Math.ceil(tableNames.length / 2.0) * (214 + 10)))); // Set the preferred size of TableArchive

            JLabel ArchiveTitle = new JLabel(tableName);
            ArchiveTitle.setFont(PrimaryFont32);
            panel.add(ArchiveTitle);

            JLabel RecordLabel = new JLabel("Record");
            RecordLabel.setFont(Bold);
            panel.add(RecordLabel);
        }
    }
}
