import java.io.*;

public class LoginSystem {
    private static final String FILE_PATH = "C:/Users/User/Desktop/project/Test.txt"; // Replace with the actual file path

    public static void main(String[] args) {
        BufferedReader reader = null;
        boolean loggedIn = false;
        String loggedInUsername = null;

        try {
            reader = new BufferedReader(new FileReader(FILE_PATH));
            String line;
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println(" ===================================");
            System.out.println("||                                 ||");
            System.out.println("||      Welcome to Kirimantab      ||");
            System.out.println("||                                 ||");
            System.out.println(" ===================================");

            System.out.println("");

            while (true) {
                System.out.println("Select an option:");
                System.out.println("1. Login");
                System.out.println("2. Register");
                System.out.println("3. Exit");
                System.out.print("Choice: ");
                String option = inputReader.readLine();

                switch (option) {
                    case "1":
                        // Login
                        loggedInUsername = login(reader, inputReader, loggedInUsername);
                        loggedIn = (loggedInUsername != null);
                        break;
                    case "2":
                        // Register
                        register(inputReader);
                        break;
                    case "3":
                        // Exit
                        System.out.println("Understandable Have A Nice Day. Thanks for using this program.");
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }

                if (loggedIn) {
                    System.out.println("Hi " + loggedInUsername + "! Welcome to the system.");
                    DijkstraAlgorithm.djikstra();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static String login(BufferedReader reader, BufferedReader inputReader, String loggedInUsername) throws IOException {
        String line;

        while (loggedInUsername == null) {
            System.out.print("Username: ");
            String inputUsername = inputReader.readLine();
            System.out.print("Password: ");
            String inputPassword = inputReader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String username = parts[0];
                String password = parts[1];

                if (inputUsername.equals(username) && inputPassword.equals(password)) {
                    System.out.println("Login successful!");
                    loggedInUsername = username;
                    break;
                }
            }

            if (loggedInUsername == null) {
                System.out.println("Invalid username or password");
                System.out.println("Retry login? (yes/no): ");
                String retryChoice = inputReader.readLine();
                if (retryChoice.equalsIgnoreCase("no")) {
                    break;
                }
                reader = new BufferedReader(new FileReader(FILE_PATH)); // Reset the reader
            }
        }

        return loggedInUsername;
    }

    private static void register(BufferedReader inputReader) throws IOException {
        System.out.print("Enter a username: ");
        String username = inputReader.readLine();
        System.out.print("Enter a password: ");
        String password = inputReader.readLine();

        try (FileWriter writer = new FileWriter(FILE_PATH, true);
             BufferedWriter bufferedWriter = new BufferedWriter(writer);
             PrintWriter out = new PrintWriter(bufferedWriter)) {
            out.println(username + "," + password);
            System.out.println("Registration successful!");
            System.out.println();
            
        } catch (IOException e) {
            e.printStackTrace();
            
        }
    }
}
