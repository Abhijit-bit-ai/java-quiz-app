import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QuizGUI extends JFrame implements ActionListener {

    JLabel questionLabel, resultLabel;
    JButton[] options = new JButton[4];
    JButton restartButton;

    String[] questions = {
        "What is the capital of India?",
        "Which language is used for Android development?",
        "Which company created Java?"
    };

    String[][] answers = {
        {"Mumbai", "Delhi", "Kolkata", "Chennai"},
        {"Python", "Java", "C++", "Swift"},
        {"Microsoft", "Apple", "Sun Microsystems", "Google"}
    };

    int[] correctAnswers = {1, 1, 2};

    int currentQuestion = 0;
    int score = 0;

    JPanel panel;

    QuizGUI() {

        setTitle("Quiz App");
        setSize(500, 350);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        questionLabel = new JLabel("", JLabel.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        questionLabel.setForeground(Color.BLUE);
        add(questionLabel, BorderLayout.NORTH);

        panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2, 10, 10));
        panel.setBackground(Color.LIGHT_GRAY);

        for (int i = 0; i < 4; i++) {
            options[i] = new JButton();
            options[i].setFont(new Font("Arial", Font.PLAIN, 14));
            options[i].setBackground(Color.WHITE);
            options[i].addActionListener(this);
            panel.add(options[i]);
        }

        add(panel, BorderLayout.CENTER);

        resultLabel = new JLabel("", JLabel.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 18));

        restartButton = new JButton("Restart Quiz");
        restartButton.addActionListener(e -> restartQuiz());

        loadQuestion();

        setVisible(true);
    }

    void loadQuestion() {
        if (currentQuestion < questions.length) {
            questionLabel.setText(questions[currentQuestion]);

            for (int i = 0; i < 4; i++) {
                options[i].setText(answers[currentQuestion][i]);
            }
        } else {
            showResult();
        }
    }

    void showResult() {
        getContentPane().removeAll();

        double percentage = (score * 100.0) / questions.length;

        resultLabel.setText("Score: " + score + "/" + questions.length + 
                            " (" + percentage + "%)");

        setLayout(new GridLayout(2, 1));
        add(resultLabel);
        add(restartButton);

        revalidate();
        repaint();
    }

    void restartQuiz() {
        currentQuestion = 0;
        score = 0;
        getContentPane().removeAll();

        setLayout(new BorderLayout());
        add(questionLabel, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);

        loadQuestion();
        revalidate();
        repaint();
    }

    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < 4; i++) {
            if (e.getSource() == options[i]) {

                if (i == correctAnswers[currentQuestion]) {
                    score++;
                }

                currentQuestion++;
                loadQuestion();
            }
        }
    }

    public static void main(String[] args) {
        new QuizGUI();
    }
}