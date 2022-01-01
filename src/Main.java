import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final int CREATE_USER_CASE = 1;
        final int LOGIN_CASE = 2;
        final int FINISH_PROGRAM_CASE = 3;
        final int POST_NEW_PROPERTY_CASE = 1;
        final int REMOVE_PROPERTY_CASE = 2;
        final int SHOW_ALL_PROPERTIES_CASE = 3;
        final int SHOW_ALL_USER_PROPERTIES_CASE = 4;
        final int SEARCH_PROPERTY_CASE = 5;
        final int EXIT_TO_MAIN_MENU_CASE = 6;
        RealEstate realEstate = new RealEstate();
        Scanner scanner = new Scanner(System.in);
        boolean stopTheProgram = false;
        while (!stopTheProgram) {
            System.out.println("Please enter a number according to the index of the wanted action as shown below to continue: \n" +
                    "1. Create an account. \n" +
                    "2. Login to an exiting account. \n" +
                    "3.Close the program. ");

            int userChoice = scanner.nextInt();
            scanner.nextLine();
            switch (userChoice) {
                case CREATE_USER_CASE: {
                    realEstate.createUser();
                    System.out.println("Account created successfully!");
                    break;
                }
                case LOGIN_CASE: {
                    User user = realEstate.login();
                    if (user == null) {
                        System.out.println("you have entered wrong username/ password... back to main menu it is then...");
                        break;
                    } else {
                        boolean breakLoop = false;
                        while (!breakLoop) {
                            int chosenOption = 0;
                            System.out.println("Please enter a number according to the index of the wanted action as shown below to continue: \n" +
                                    "1. Post new property \n" +
                                    "2. Remove posted property \n" +
                                    "3. Show all the properties in the system \n" +
                                    "4. Show all the properties posted by you \n" +
                                    "5. Search property by parameters \n" +
                                    "6. Logout and exist to main menu");
                            chosenOption = scanner.nextInt();
                            scanner.nextLine();
                            switch (chosenOption) {
                                case POST_NEW_PROPERTY_CASE: {
                                    boolean didItSucceed = realEstate.postNewProperty(user);
                                    if (!didItSucceed) {
                                        System.out.println(" you have reached the maximum amount of posts/ entered an invalid input....");
                                        break;
                                    } else {
                                        System.out.println("Property posted successfully! ");
                                        break;
                                    }
                                }
                                case REMOVE_PROPERTY_CASE: {
                                    realEstate.removeProperty(user);
                                    break;
                                }
                                case SHOW_ALL_PROPERTIES_CASE: {
                                    realEstate.printAllProperties();
                                    break;
                                }
                                case SHOW_ALL_USER_PROPERTIES_CASE: {
                                    realEstate.printUserProperties(user);
                                    break;
                                }
                                case SEARCH_PROPERTY_CASE: {
                                    Property[] relevantProperties = realEstate.search();
                                    if (relevantProperties != null) {
                                        System.out.println(" ");
                                        for (int i = 0; i < relevantProperties.length; i++) {
                                            System.out.println(relevantProperties[i]);
                                            System.out.println(" ");
                                        }
                                    } else {
                                        System.out.println("there is no property matching to your search :(");
                                    }
                                    break;
                                }
                                case EXIT_TO_MAIN_MENU_CASE: {
                                    breakLoop = true;
                                    break;
                                }
                            }
                        }
                        break;
                    }
                }
                case FINISH_PROGRAM_CASE: {
                    stopTheProgram = true;
                    break;
                }
            }
        }
    }
}
