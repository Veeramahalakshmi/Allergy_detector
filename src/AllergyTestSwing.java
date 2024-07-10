import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class AllergyTestSwing extends JFrame implements ActionListener {
    ArrayList<Integer> scores = new ArrayList<>();
    ArrayList<String> allergens = new ArrayList<>();
    private int allergyScore = 0;
    private int size = 0;
    private JCheckBox[] allergenCheckboxes;
    private JButton submitButton;

    public AllergyTestSwing() {
        super("Allergy Test");
        setLayout(new GridLayout(8,1));

        try {
            Scanner fileScanner = new Scanner(new File("allergens.txt"));

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(":");
                allergens.add(parts[0]);
                scores.add(Integer.parseInt(parts[1].trim()));
                size++;
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        }

        allergenCheckboxes = new JCheckBox[size];
        for (int i = 0; i < size; i++) {
            allergenCheckboxes[i] = new JCheckBox(allergens.get(i));
            add(allergenCheckboxes[i]);
        }

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        add(submitButton, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            for (int i = 0; i < size; i++) {
                if (allergenCheckboxes[i].isSelected()) {
                    allergyScore += scores.get(i);
                }
            }
            JOptionPane.showMessageDialog(this, "Your allergy score is: " + allergyScore);
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(AllergyTestSwing::new);
    }
}