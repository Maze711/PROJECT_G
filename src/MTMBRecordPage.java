import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MTMBRecordPage extends JPanel {

    public MTMBRecordPage() {
        initialize();
    }

    private void initialize() {
        // Define your custom font styles
        Font PrimaryFont = FontLoader.getFont("Primary", 64);
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
        setLayout(null);

        // Header
        JPanel InsideRecordPanel = new JPanel();
        InsideRecordPanel.setBounds(0, 0, 736, 70);
        InsideRecordPanel.setLayout(null);

        JLabel Record = new JLabel("Records");
        Record.setBounds(30, 23, 189, 36);
        Record.setFont(SecondaryFont);
        InsideRecordPanel.add(Record);
        RecordPanel.setLayout(null);

        JLabel lblYear = new JLabel("Year");
        lblYear.setFont(SemiB);
        lblYear.setBounds(56, 65, 61, 33);
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 100, 716, 657);
		RecordPanel.add(scrollPane);
    }
}
