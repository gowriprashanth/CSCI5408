package com.gosql;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        String filePath = "C:/Users/AVuser/IdeaProjects/GoSQL/src/main/resources/";
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();
        Hashing hash = new Hashing();
        CustomFile customFile = new CustomFile();
        User user;
        int choice;
        while(true) {
            System.out.println("Press 1 for signup \n" + "2 for login \n" + "0 to quit : ");

            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                //Signup
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                password = hash.hashPassword(password);
                System.out.print("set question: ");
                String question = scanner.nextLine();
                System.out.print("Enter answer for question: ");
                String answer = scanner.nextLine();

                boolean signupCheck = userService.createUser(username, password, question, answer);
                if (signupCheck) {
                    System.out.println("Signup successful!");
                    user = new User(username, password, question, answer);
                    customFile.saveUser(user, filePath+username);
                } else {
                    System.out.println("Username already taken. Signup failed.");
                    return;
                    }
                }
                else if (choice == 2) {
                //Login
                System.out.print("Enter username: ");
                String loginUsername = scanner.nextLine();
                System.out.print("Enter password: ");
                String loginPassword = scanner.nextLine();

                user = customFile.readUser(loginUsername,filePath+loginUsername);
                userService.createUser(user.getUsername(), user.getPassword(), user.getQuestion(), user.getAnswer());
                System.out.println("Question:");
                String loginQuestion = userService.getQuestionforUser(loginUsername);
                System.out.println(loginQuestion);
                System.out.print("Enter answer: ");
                String loginAnswer = scanner.nextLine();
                loginPassword = hash.hashPassword(loginPassword);

                int internalChoice;
                String query;

                User logInSession = userService.loginUser(loginUsername, loginPassword, loginQuestion, loginAnswer);
                if (logInSession != null) {
                    CustomLogs customLogs = new CustomLogs();
                    customLogs.createLog(customFile, loginUsername, loginUsername, "user login success");
                    System.out.println("Login successful! Welcome, " + logInSession.getUsername() + "!");

                    while (true) {
                        System.out.println("Please press 1 to enter query or press 0 to exit:");
                        internalChoice = scanner.nextInt();
                        scanner.nextLine();
                        if (internalChoice == 1) {
                            System.out.println("Please enter the query:");
                            query = scanner.nextLine();
                            query = query.replace(";","");
                            System.out.println("query:" + query);
                            //boolean create = customFile.createDatabase("database");
                            QueryAnalyze qA = new QueryAnalyze(query);
                            //System.out.println(create);
                            Query q = new Query();

                            if (qA.getQueryType().equals("CREATE")) {
                                q.CreateQuery(customFile, qA);
                                customLogs.createLog(customFile, loginUsername, loginUsername, query);
                            }

                            if (qA.getQueryType().equals("SELECT")) {
                                q.SelectQuery(customFile, qA, query);
                                customLogs.createLog(customFile, loginUsername, loginUsername, query);
                            }

                            if (qA.getQueryType().equals("DELETE")) {
                                q.DeleteQuery(customFile, qA);
                                customLogs.createLog(customFile, loginUsername, loginUsername, query);
                            }

                            if (qA.getQueryType().equals("INSERT")) {
                                q.InsertQuery(customFile, qA);
                                customLogs.createLog(customFile, loginUsername, loginUsername, query);
                            }

                            if (qA.getQueryType().equals("UPDATE")) {
                                q.UpdateQuery(customFile, qA);
                                customLogs.createLog(customFile, loginUsername, loginUsername, query);
                            }

                        } else if (internalChoice == 0) {
                            System.out.println("Bye!");
                            System.exit(0);
                        } else System.out.println("Enter valid choice");

                    }
                }else {
                    System.out.println("Login failed. Invalid username or password or answer.");
                }


            }
               else {
                System.out.println("Bye!");
                System.exit(0);
            }
        }
    }
}