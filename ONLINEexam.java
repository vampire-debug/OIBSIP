import java.util.*;

public class OnlineExamSystem {
    
    // Dummy user data
    private static Map<String, String> users = new HashMap<>();
    private static String loggedInUser;
    private static Map<Integer, Integer> answers = new HashMap<>();
    private static int timeRemaining = 10; // seconds for exam

    static {
        users.put("user1", "password1");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Login Functionality
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        if (login(username, password)) {
            System.out.println("Login successful!\n");
            
            // Update Profile and Password
            System.out.print("Update name (or press Enter to skip): ");
            String newName = scanner.nextLine();
            System.out.print("Update password (or press Enter to skip): ");
            String newPassword = scanner.nextLine();
            updateProfile(newName, newPassword);
            
            // Select answers for MCQs
            selectAnswer(scanner);
            
            // Start Timer and Auto-submit exam
            startTimer();

        } else {
            System.out.println("Invalid login!");
        }
        
        // Logout
        logout();
        scanner.close();
    }

    // Login functionality
    public static boolean login(String username, String password) {
        if (users.containsKey(username) && users.get(username).equals(password)) {
            loggedInUser = username;
            return true;
        }
        return false;
    }

    // Update profile and password
    public static void updateProfile(String newName, String newPassword) {
        if (!newPassword.isEmpty()) {
            users.put(loggedInUser, newPassword);
        }
        if (!newName.isEmpty()) {
            System.out.println("Profile updated: Name: " + newName);
        }
    }

    // MCQ Answer Selection
    public static void selectAnswer(Scanner scanner) {
        System.out.println("\nAnswer MCQ:");
        System.out.println("1. What is 2 + 2? \n1) 3 \n2) 4 \n3) 5 \n4) 6");
        System.out.print("Select option (1-4): ");
        int answer1 = scanner.nextInt();
        answers.put(1, answer1);
        
        System.out.println("2. What is the capital of France? \n1) Berlin \n2) Madrid \n3) Paris \n4) Rome");
        System.out.print("Select option (1-4): ");
        int answer2 = scanner.nextInt();
        answers.put(2, answer2);
    }

    // Timer and Auto-submit
    public static void startTimer() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (timeRemaining > 0) {
                    System.out.println("Time remaining: " + timeRemaining + " seconds");
                    timeRemaining--;
                } else {
                    submitExam();
                    timer.cancel();
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    public static void submitExam() {
        System.out.println("\nTime's up! Exam submitted.");
        System.out.println("Your answers: " + answers);
    }

    // Logout functionality
    public static void logout() {
        System.out.println("User logged out.");
    }
}
