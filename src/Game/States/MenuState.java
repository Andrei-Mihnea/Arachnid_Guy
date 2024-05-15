package Game.States;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MenuState extends JFrame {


    public MenuState() {
        System.out.println("LOL");
        // Set the title of the JFrame
        setTitle("Game Menu");

        // Set the layout manager to BorderLayout
        setLayout(new BorderLayout());

        // Create a JLabel for the title "Menu" and set its alignment
        JLabel titleLabel = new JLabel("Menu", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // Create a JButton for the "Play" button
        JButton playButton = new JButton("Play");
        playButton.setFont(new Font("Arial", Font.BOLD, 36));

        // Create a JPanel to center the button at the bottom
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.add(playButton);

        add(buttonPanel, BorderLayout.CENTER);

        // Add ActionListener to the playButton
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the play button click event
                //JOptionPane.showMessageDialog(Menu.this, "Play button clicked!");
            }
        });

        // Set the size of the JFrame
        setSize(400, 300);

        // Set the default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Center the JFrame on the screen
        setLocationRelativeTo(null);
    }

    public void Menu() {


    }

    private void startGame() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.remove(this);
        frame.revalidate();

    }
}
