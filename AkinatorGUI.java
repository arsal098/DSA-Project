import javax.swing.*;
import java.awt.*;

class AkinatorGUI extends JFrame {

    AkinatorEngine engine = new AkinatorEngine();

    JLabel title;
    JLabel question;
    JLabel confidenceLabel;

    JButton yes, no, restart;

    Color gold = new Color(255, 196, 0);
    Font titleFont = new Font("Segoe UI", Font.BOLD, 30);
    Font questionFont = new Font("Segoe UI", Font.BOLD, 20);
    Font btnFont = new Font("Segoe UI", Font.BOLD, 16);

    AkinatorGUI() {

        engine.build();

        BackgroundPanel bg = new BackgroundPanel("src/background.jpg");
        bg.setLayout(new BorderLayout());
        setContentPane(bg);

        setTitle("Akinator Pro Game");
        setSize(700, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        title = new JLabel("AKINATOR GAME", SwingConstants.CENTER);
        title.setFont(titleFont);
        title.setForeground(gold);

        question = new JLabel(engine.current.data, SwingConstants.CENTER);
        question.setFont(questionFont);
        question.setForeground(Color.WHITE);
        question.setOpaque(true);
        question.setBackground(new Color(25, 30, 55, 180));
        question.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        confidenceLabel = new JLabel("Confidence: 0%", SwingConstants.CENTER);
        confidenceLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        confidenceLabel.setForeground(Color.LIGHT_GRAY);

        yes = new JButton("YES");
        no = new JButton("NO");
        restart = new JButton("RESTART");

        styleButton(yes, new Color(0, 150, 90));
        styleButton(no, new Color(200, 50, 50));
        styleButton(restart, new Color(80, 80, 80));

        JPanel center = new JPanel(new GridLayout(3,1,10,10));
        center.setOpaque(false);
        center.setBorder(BorderFactory.createEmptyBorder(40, 60, 20, 60));
        center.add(title);
        center.add(question);
        center.add(confidenceLabel);

        JPanel buttons = new JPanel();
        buttons.setOpaque(false);
        buttons.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 10));
        buttons.add(yes);
        buttons.add(no);
        buttons.add(restart);

        bg.add(center, BorderLayout.CENTER);
        bg.add(buttons, BorderLayout.SOUTH);

        yes.addActionListener(e -> move(true));
        no.addActionListener(e -> move(false));
        restart.addActionListener(e -> resetGame());

        setVisible(true);
    }

    void styleButton(JButton b, Color c) {
        b.setFont(btnFont);
        b.setBackground(c);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    void move(boolean ans) {

        if (ans) engine.moveYes();
        else engine.moveNo();

        if (engine.isEnd(engine.current)) {
            question.setText("I guess: " + engine.current.data);
            yes.setEnabled(false);
            no.setEnabled(false);
        } else {
            question.setText(engine.current.data);
        }

        confidenceLabel.setText("Confidence: " + engine.getConfidence() + "%");
    }

    void resetGame() {
        engine.reset();
        question.setText(engine.current.data);
        confidenceLabel.setText("Confidence: 0%");
        yes.setEnabled(true);
        no.setEnabled(true);
    }
}