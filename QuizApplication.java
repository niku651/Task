import java.util.*;

class QuizQuestion {
    String question;
    List<String> options;
    int correctOption;

    public QuizQuestion(String question, List<String> options, int correctOption) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }
}

public class QuizApplication {
    private List<QuizQuestion> questions;
    private int score;
    private Scanner scanner;

    public QuizApplication(List<QuizQuestion> questions) {
        this.questions = questions;
        this.score = 0;
        this.scanner = new Scanner(System.in);
    }

    public void startQuiz() {
        System.out.println("Welcome to the Quiz!");
        System.out.println("You will have 10 seconds to answer each question.");

        for (int i = 0; i < questions.size(); i++) {
            QuizQuestion currentQuestion = questions.get(i);
            System.out.println("\nQuestion " + (i + 1) + ": " + currentQuestion.question);
            displayOptions(currentQuestion.options);

            // Start Timer
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                public void run() {
                    System.out.println("\nTime's up! Moving to the next question.");
                    timer.cancel();
                    timer.purge();
                }
            };
            timer.schedule(task, 10000); // 10 seconds timer

            // Get user input
            int userChoice = getUserChoice(currentQuestion.options.size());
            timer.cancel(); // Cancel the timer if the user answers before the timer expires

            // Check user's answer
            if (userChoice == currentQuestion.correctOption) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect! The correct answer is Option " + currentQuestion.correctOption);
            }
        }

        // Display final score
        System.out.println("\nQuiz completed! Your final score is: " + score + "/" + questions.size());
        displaySummary();
    }

    private void displayOptions(List<String> options) {
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
    }

    private int getUserChoice(int maxOption) {
        int userChoice = -1;

        while (userChoice < 1 || userChoice > maxOption) {
            System.out.print("\nEnter your choice (1-" + maxOption + "): ");
            if (scanner.hasNextInt()) {
                userChoice = scanner.nextInt();
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Consume invalid input
            }
        }

        return userChoice;
    }

    private void displaySummary() {
        System.out.println("\nSummary of Correct/Incorrect Answers:");

        for (int i = 0; i < questions.size(); i++) {
            QuizQuestion currentQuestion = questions.get(i);
            System.out.println("Question " + (i + 1) + ": " + (scoredCorrect(currentQuestion) ? "Correct" : "Incorrect"));
        }
    }

    private boolean scoredCorrect(QuizQuestion question) {
        return getUserChoice(question.options.size()) == question.correctOption;
    }

    public static void main(String[] args) {
        // Sample Quiz Questions
        List<QuizQuestion> quizQuestions = new ArrayList<>();
        quizQuestions.add(new QuizQuestion("What is the capital of France?", Arrays.asList("A. Berlin", "B. Paris", "C. Madrid", "D. Rome"), 2));
        quizQuestions.add(new QuizQuestion("Which planet is known as the Red Planet?", Arrays.asList("A. Venus", "B. Mars", "C. Jupiter", "D. Saturn"), 2));
        quizQuestions.add(new QuizQuestion("What is the largest mammal?", Arrays.asList("A. Elephant", "B. Blue Whale", "C. Giraffe", "D. Gorilla"), 2));

        QuizApplication quizApp = new QuizApplication(quizQuestions);
        quizApp.startQuiz();
    }
}
