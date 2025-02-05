package ProjectSoftware;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.*;

public class User extends JFrame {
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private JTextField loginEmailField, signupNameField, signupEmailField;
    private JPasswordField loginPasswordField, signupPasswordField;

    public User() {
        setTitle("Ticket Ordering System");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        JPanel loginPanel = createLoginPanel();
        JPanel signupPanel = createSignUpPanel();

        mainPanel.add(loginPanel, "Login");
        mainPanel.add(signupPanel, "SignUp");

        // Add exit button at the bottom
        JButton exitButton = new JButton("Exit");
        exitButton.setBackground(new Color(220, 53, 69));
        exitButton.setForeground(Color.WHITE);
        exitButton.setFont(new Font("Arial", Font.BOLD, 14));
        exitButton.addActionListener(e -> {
            playSound();
            System.exit(0);
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(30, 39, 46));
        bottomPanel.add(exitButton);

        // Use BorderLayout for the main frame
        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(30, 39, 46));

        JLabel titleLabel = new JLabel("Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(160, 30, 100, 30);
        panel.add(titleLabel);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setBounds(50, 100, 80, 25);
        panel.add(emailLabel);

        loginEmailField = new JTextField();
        loginEmailField.setBounds(130, 100, 200, 30);
        panel.add(loginEmailField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setBounds(50, 150, 80, 25);
        panel.add(passwordLabel);

        loginPasswordField = new JPasswordField();
        loginPasswordField.setBounds(130, 150, 200, 30);
        panel.add(loginPasswordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(130, 200, 200, 40);
        loginButton.setBackground(new Color(0, 123, 255));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.addActionListener(e -> {
            playSound();
            String email = loginEmailField.getText();
            String password = new String(loginPasswordField.getPassword());
            if (email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Add your login logic here
                JOptionPane.showMessageDialog(this, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        panel.add(loginButton);

        JButton switchToSignup = new JButton("Create an Account");
        switchToSignup.setBounds(130, 260, 200, 30);
        switchToSignup.setBackground(new Color(40, 167, 69));
        switchToSignup.setForeground(Color.WHITE);
        switchToSignup.addActionListener(e -> {
            playSound();
            cardLayout.show(mainPanel, "SignUp");
        });
        panel.add(switchToSignup);

        JButton adminLoginButton = new JButton("Admin Login");
        adminLoginButton.setBounds(130, 300, 200, 30);
        adminLoginButton.setBackground(new Color(220, 53, 69));
        adminLoginButton.setForeground(Color.WHITE);
        adminLoginButton.addActionListener(e -> {
            playSound();
            String adminUsername = JOptionPane.showInputDialog(this, "Enter Admin Username:");
            if (adminUsername != null && !adminUsername.trim().isEmpty()) {
                String adminPassword = JOptionPane.showInputDialog(this, "Enter Admin Password:");
                if (adminPassword != null && !adminPassword.trim().isEmpty()) {
                    if (adminUsername.equals("admin") && adminPassword.equals("admin123")) {
                        new Admin();
                    } else {
                        JOptionPane.showMessageDialog(this,
                                "Invalid admin credentials",
                                "Access Denied",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        panel.add(adminLoginButton);

        return panel;
    }

    private JPanel createSignUpPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(30, 39, 46));

        JLabel titleLabel = new JLabel("Sign Up");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(160, 30, 100, 30);
        panel.add(titleLabel);

        JLabel nameLabel = new JLabel("Full Name:");
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setBounds(50, 80, 80, 25);
        panel.add(nameLabel);

        signupNameField = new JTextField();
        signupNameField.setBounds(130, 80, 200, 30);
        panel.add(signupNameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setBounds(50, 130, 80, 25);
        panel.add(emailLabel);

        signupEmailField = new JTextField();
        signupEmailField.setBounds(130, 130, 200, 30);
        panel.add(signupEmailField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setBounds(50, 180, 80, 25);
        panel.add(passwordLabel);

        signupPasswordField = new JPasswordField();
        signupPasswordField.setBounds(130, 180, 200, 30);
        panel.add(signupPasswordField);

        JButton signupButton = new JButton("Sign Up");
        signupButton.setBounds(130, 230, 200, 40);
        signupButton.setBackground(new Color(0, 123, 255));
        signupButton.setForeground(Color.WHITE);
        signupButton.setFont(new Font("Arial", Font.BOLD, 16));
        signupButton.addActionListener(e -> {
            playSound();
            String name = signupNameField.getText();
            String email = signupEmailField.getText();
            String password = new String(signupPasswordField.getPassword());

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                saveUserData(name, email, password);
                JOptionPane.showMessageDialog(this, "Account created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                signupNameField.setText("");
                signupEmailField.setText("");
                signupPasswordField.setText("");
                cardLayout.show(mainPanel, "Login");
            }
        });
        panel.add(signupButton);

        JButton switchToLogin = new JButton("Already have an account?");
        switchToLogin.setBounds(130, 280, 200, 30);
        switchToLogin.setBackground(new Color(108, 117, 125));
        switchToLogin.setForeground(Color.WHITE);
        switchToLogin.addActionListener(e -> {
            playSound();
            cardLayout.show(mainPanel, "Login");
        });
        panel.add(switchToLogin);

        return panel;
    }

    private void saveUserData(String name, String email, String password) {
        try (FileWriter writer = new FileWriter("user.txt", true)) {
            writer.write("Name: " + name + ", Email: " + email + ", Password: " + password + "\n");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving user data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void playSound() {
        try {
            URL soundUrl = getClass().getResource("click.wav");
            if (soundUrl == null) {
                throw new IllegalArgumentException("Sound file not found: click.wav");
            }
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundUrl);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(User::new);
    }
}