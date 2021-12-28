import java.util.Properties;
import java.util.Scanner;

public class RealEstate {
    User[] users = null;
    Property[] properties = null;
    Address[] addresses = null;

    public RealEstate() {
        Address address1 = new Address("Jerusalem", "Kiryat HaYovel");
        Address address2 = new Address("Ashekelon", "Barnea");
        Address address3 = new Address("Savion", "Magal St");
        Address address4 = new Address("Tel Aviv-Yafo", "Sderot Ben Gurion");
        Address address5 = new Address("Tel Aviv-Yafo", "Arlozorov St");
        Address address6 = new Address("Sderot", "Derech HaAliya");
        Address address7 = new Address("Kiryat Gat", "Sderot Lachish");
        Address address8 = new Address("Ber Sheva", "Ramot");
        Address address9 = new Address("Eilat", "Arava");
        Address address10 = new Address("Ashekelon", "Agamit St");
        this.addresses = new Address[]{address1, address2, address3, address4, address5, address6, address7, address8, address9, address10};
    }

    public Property[] search() {
        final int NUMBER_OF_CRITERIA = 5;
        Scanner scanner = new Scanner(System.in);
        if (properties == null) {
            System.out.println("looks like no one has posted anything yet... post something so you could use this function...");
        } else {
            System.out.println("do you want properties for rent or sale? enter 1 for sale, 0 for rent and -999 for 'it doesn't matter'");
            int answerIsForRent = scanner.nextInt();
            scanner.nextLine();
            System.out.println("what is the wanted house category? enter 1 for regular apartment, enter 2 for penthouse apartment, enter 3 for private house and -999 for 'it doesn't matter'");
            int answerHouseCategory = scanner.nextInt();
            scanner.nextLine();
            System.out.println("what is the wanted number of rooms? if it doesn't matter enter -999");
            int answerWantedNumberOfRooms = scanner.nextInt();
            scanner.nextLine();
            System.out.println("what is the maximum price you want? enter -999 for 'it doesn't matter' ");
            int answerMaxPrice = scanner.nextInt();
            scanner.nextLine();
            System.out.println("what is the minimum price you want? enter -999 for 'it doesn't matter' ");
            int answerMinPrice = scanner.nextInt();
            scanner.nextLine();
            Property[] temp = new Property[properties.length];
            int howManyRelevantProperties = 0;
            boolean answerBooleanIsForRent = true;
            if (answerIsForRent == 1) {
                answerBooleanIsForRent = false;
            }
            for (int i = 0; i < properties.length; i++) {
                if (properties[i].isForRental() == answerBooleanIsForRent || answerIsForRent == -999) {
                    if ((answerHouseCategory == properties[i].getHouseCategory() || answerHouseCategory == -999) && (answerWantedNumberOfRooms == properties[i].getHowManyRooms() || answerWantedNumberOfRooms == -999) && (answerMaxPrice > properties[i].getPrice() || answerMaxPrice == -999) && (answerMinPrice < properties[i].getPrice() || answerMinPrice == -999)) {
                        temp[howManyRelevantProperties] = properties[i];
                        howManyRelevantProperties++;
                    }
                }
            }
            Property[] relevantProperties = new Property[howManyRelevantProperties];
            for (int i = 0; i < relevantProperties.length; i++) {
                relevantProperties[i] = temp[i];
            }
            return relevantProperties;
        }
        return null;
    }

    public void printUserProperties(User user) {
        if (properties != null) {
            System.out.println(" ");
            Property[] userProperties = getPropertiesByUser(user);
            for (int i = 0; i < userProperties.length; i++) {
                System.out.println(userProperties[i]);
                System.out.println(" ");
            }
            if (userProperties.length == 0) {
                System.out.println("you haven't posted anything yet...");
            }
        } else {
            System.out.println("there's nothing here yet");
        }
    }

    public void printAllProperties() {
        if (properties != null) {
            System.out.println(" ");
            for (int i = 0; i < properties.length; i++) {
                System.out.println(properties[i]);
                System.out.println(" ");
            }
        } else {
            System.out.println("there's nothing here yet");
        }
    }

    public void removeProperty(User user) {
        Scanner scanner = new Scanner(System.in);
        Property[] propertiesByUser = getPropertiesByUser(user);
        if (propertiesByUser.length == 0) {
            System.out.println("You haven't posted anything yet...");
        } else {
            System.out.println("you have posted this properties: ");
            for (int i = 0; i < propertiesByUser.length; i++) {
                System.out.println((i + 1) + " " + propertiesByUser[i]);
            }
            System.out.println("type in  a number according to the listed number of the post you would like to remove from the list above");
            int propertyToRemoveIndex = scanner.nextInt();
            scanner.nextLine();
            removePropertyFromList(propertiesByUser[propertyToRemoveIndex]);
            System.out.println("Post removed successfully!");
        }
    }

    public Property[] getPropertiesByUser(User user) {
        Property[] temp = new Property[properties.length];
        int howManyMatching = 0;
        for (int i = 0; i < properties.length; i++) {
            if (properties[i].getPropertyOwner() == user) {
                temp[howManyMatching] = properties[i];
                howManyMatching++;
            }
        }
        Property[] matchingProperties = new Property[howManyMatching];
        for (int i = 0; i < howManyMatching; i++) {
            matchingProperties[i] = temp[i];
        }
        return matchingProperties;
    }

    public boolean postNewProperty(User user) {
        boolean didItSucceed = true;
        Scanner scanner = new Scanner(System.in);
        if (properties != null) {
            if (user.isBroker()) {
                if (getPropertiesByUser(user).length >= 10) {
                    //System.out.println("Sorry... brokers can post ONLY up to 10 properties... you can delete existing posts in the next menu:  ");
                    didItSucceed = false;
                    return didItSucceed;
                }
            } else {
                if (getPropertiesByUser(user).length >= 3) {
                    //System.out.println("Sorry... private users can post ONLY up to 3 properties... you can delete existing posts in the next menu:  ");
                    didItSucceed = false;
                    return didItSucceed;
                }
            }
        }
        boolean didThisCityAppeared = false;
        System.out.println("shown below is the list of supported cities: ");
        for (int i = 0; i < addresses.length; i++) {
            didThisCityAppeared = false;
            for (int j = 0; j < i; j++) {
                if (addresses[i].getCityName().equals(addresses[j].getCityName())) {
                    didThisCityAppeared = true;
                    break;
                }
            }
            if (!didThisCityAppeared) {
                System.out.println("-" + addresses[i].getCityName());
            }
        }
        System.out.println("please enter the city of your property according to the list: ");
        String enteredCity = scanner.nextLine();
        boolean didItMatchWithList = false;
        for (int i = 0; i < addresses.length; i++) {
            if (enteredCity.equals(addresses[i].getCityName())) {
                didItMatchWithList = true;
                break;
            }
        }
        if (!didItMatchWithList) {
            //  System.out.println("entered city not supported");
            didItSucceed = false;
            return didItSucceed;
        } else {
            System.out.println("Shown below is the list of all supported streets in " + enteredCity + ":");
            for (int i = 0; i < addresses.length; i++) {
                if (addresses[i].getCityName().equals(enteredCity)) {
                    System.out.println("-" + addresses[i].getStreetName());
                }
            }
            System.out.println("please enter your property's street according to the list: ");
            String enteredStreet = scanner.nextLine();
            didItMatchWithList = false;
            for (int i = 0; i < addresses.length; i++) {
                if (addresses[i].getCityName().equals(enteredCity) && addresses[i].getStreetName().equals(enteredStreet)) {
                    didItMatchWithList = true;
                    break;
                }
            }
            if (!didItMatchWithList) {
                // System.out.println("entered street not supported/ not from this city");
                didItSucceed = false;
                return didItSucceed;
            } else {
                System.out.println("please enter number according to the list below and your property status: \n" +
                        "1. Regular apartment \n" +
                        "2. Penthouse apartment \n" +
                        "3. Private house ");
                int enteredHouseCategory = scanner.nextInt();
                scanner.nextLine();
                if (enteredHouseCategory > 3 || enteredHouseCategory < 1) {
                    didItSucceed = false;
                    return didItSucceed;
                } else {
                    int floorNumber = -1;
                    if (enteredHouseCategory == 1 || enteredHouseCategory == 2) {
                        System.out.println("What is the property's floor number? ");
                        floorNumber = scanner.nextInt();
                        scanner.nextLine();
                    }
                    System.out.println("How many rooms your property got? enter the answer below: ");
                    int howManyRooms = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("What is your house number?");
                    int houseNumber = scanner.nextInt();
                    scanner.nextLine();
                    boolean isForSale = false;
                    System.out.println("is the property for sale? if yes enter '1' (without the quotation mark), if the property is for rent enter anything else.");
                    String userAnswer = scanner.nextLine();
                    if (userAnswer == "1") {
                        isForSale = true;
                    }
                    System.out.println("how much would you take for the property? (if it is for rental enter monthly rental fee) please enter the answer in numbers below:  ");
                    int price = scanner.nextInt();
                    scanner.nextLine();
                    Address userEnteredAddress = new Address(enteredCity, enteredStreet);
                    Property newProperty = new Property(userEnteredAddress, howManyRooms, price, enteredHouseCategory, !isForSale, houseNumber, floorNumber, user);
                    addProperty(newProperty);
                    didItSucceed = true;
                    return didItSucceed;
                }

            }
        }
    }

    public User login() {
        User matchingUser = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter your username: ");
        String enteredUsername = scanner.nextLine();
        System.out.println("please enter your password: ");
        String enteredPassword = scanner.nextLine();
        if (users != null) {
            for (int i = 0; i < users.length; i++) {
                if (enteredUsername.equals(users[i].getUserName()) && enteredPassword.equals(users[i].getPassword())) {
                    matchingUser = users[i];
                    break;
                }
            }
            return matchingUser;
        } else {
            return null;
        }
    }

    private void addUser(User userToAdd) {
        User[] newArray;
        if (users == null) {
            newArray = new User[1];
            newArray[0] = userToAdd;
        } else {
            newArray = new User[users.length + 1];
            for (int i = 0; i < users.length; i++) {
                newArray[i] = users[i];
            }
            newArray[users.length] = userToAdd;
        }
        this.users = newArray;
    }

    private void removePropertyFromList(Property propertyToRemove) {
        Property[] newArray;
        int indexOfRemovedItem = -1;
        if (properties != null) {
            newArray = new Property[properties.length - 1];
            for (int i = 0; i < properties.length - 1; i++) {
                if (properties[i] != propertyToRemove) {
                    newArray[i] = properties[i];
                    indexOfRemovedItem = i;
                }
            }
            if (indexOfRemovedItem >= 0) {
                newArray[indexOfRemovedItem] = properties[properties.length - 1];
            }
            this.properties = newArray;
        } else {
            System.out.println("there is no posts available on the system...");
        }
    }

    private void addProperty(Property propertyToAdd) {
        Property[] newArray;
        if (properties == null) {
            newArray = new Property[1];
            newArray[0] = propertyToAdd;
        } else {
            newArray = new Property[properties.length + 1];
            for (int i = 0; i < properties.length; i++) {
                newArray[i] = properties[i];
            }
            newArray[properties.length] = propertyToAdd;
        }
        this.properties = newArray;
    }


    public boolean inFormatPhoneNumberDetector(String phoneNumber) {
        boolean isItInFormat = false;
        boolean doesItStartRight = false;
        boolean isItTheRightSize = false;
        int firstIndex = 0;
        int secondIndex = 1;
        int firstRequiredDigitASCII = 48;
        int secondRequiredDigitASCII = 53;
        boolean isItAllDigits = false;
        for (int i = 0; i < phoneNumber.length(); i++) {
            isItAllDigits = Character.isDigit(phoneNumber.charAt(i));
            if (!isItAllDigits) {
                break;
            }
        }
        if (phoneNumber.charAt(firstIndex) == firstRequiredDigitASCII && phoneNumber.charAt(secondIndex) == secondRequiredDigitASCII) {
            doesItStartRight = true;
        }
        if (phoneNumber.length() == 10) {
            isItTheRightSize = true;
        }
        if (isItAllDigits && isItTheRightSize && doesItStartRight) {
            isItInFormat = true;
        }
        return isItInFormat;
    }

    public boolean strongPasswordDetector(String password) {
        char[] requiredChars = {'$', '_', '%'};
        boolean isThereADigit = false;
        for (int i = 0; i < password.length(); i++) {
            isThereADigit = Character.isDigit(password.charAt(i));
            if (isThereADigit) {
                break;
            }
        }
        boolean oneRequiredCharAppears = false;
        for (int i = 0; i < password.length(); i++) {
            for (int j = 0; j < requiredChars.length; j++) {
                if (requiredChars[j] == password.charAt(i)) {
                    oneRequiredCharAppears = true;
                    break;
                }
            }
            if (oneRequiredCharAppears) {
                break;
            }
        }
        boolean isItStrong = false;
        if (oneRequiredCharAppears && isThereADigit) {
            isItStrong = true;
        }
        return isItStrong;
    }

    public void createUser() {
        boolean isItOriginal = true;
        String username = null;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("please enter a username: ");
            username = scanner.nextLine();
            isItOriginal = true;
            if (users != null) {
                for (int i = 0; i < users.length; i++) {
                    if (username.equals(users[i].getUserName())) {
                        isItOriginal = false;
                    }
                }
                if (!isItOriginal) {
                    System.out.println("This username is already taken :( please try again when the next message show up.");
                }
            }
        } while (!isItOriginal);

        String password = null;
        System.out.println("please enter a password \n" +
                "note that your password must contain the following:\n" +
                "-at least one digit\n" +
                "-one of these chars: _ , % , $ (not including the comma)");
        boolean isItStrong = true;
        do {
            password = scanner.nextLine();
            isItStrong = strongPasswordDetector(password);
            if (!isItStrong) {
                System.out.println("your password is too weak:( please try again.");
            }
        } while (!isItStrong);
        String phoneNumber = null;
        boolean isItInFormat = true;
        System.out.println("please enter your phone number");
        do {
            phoneNumber = scanner.nextLine();
            isItInFormat = inFormatPhoneNumberDetector(phoneNumber);
            if (!isItInFormat) {
                System.out.println("you've entered invalid phone number, please enter a only digit, starting with '05', 10 digit long phone number ");
            }
        } while (!isItInFormat);
        boolean broker = false;
        System.out.println("are you broker? if yes enter '1' (without the quotation mark), if you're not a broker enter anything else.");
        String userAnswer = scanner.nextLine();
        if (userAnswer == "1") {
            broker = true;
        }
        User newUser = new User(username, password, phoneNumber, broker);
        addUser(newUser);

    }

    public static void main(String[] args) {
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
                case 1: {
                    realEstate.createUser();
                    System.out.println("Account created successfully!");
                    break;
                }
                case 2: {
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
                                case 1: {
                                    boolean didItSucceed = realEstate.postNewProperty(user);
                                    if (!didItSucceed) {
                                        System.out.println(" you have reached the maximum amount of posts/ entered an invalid input....");
                                        break;
                                    } else {
                                        System.out.println("Property posted successfully! ");
                                        break;
                                    }
                                }
                                case 2: {
                                    realEstate.removeProperty(user);
                                    break;
                                }
                                case 3: {
                                    realEstate.printAllProperties();
                                    break;
                                }
                                case 4: {
                                    realEstate.printUserProperties(user);
                                    break;
                                }
                                case 5: {
                                    Property[] relevantProperties = realEstate.search();
                                    if (relevantProperties != null) {
                                        System.out.println(" ");
                                        for (int i = 0; i < relevantProperties.length; i++) {
                                            System.out.println(relevantProperties[i]);
                                            System.out.println(" ");
                                        }
                                    }
                                    break;
                                }
                                case 6: {
                                    breakLoop = true;
                                    break;
                                }
                            }
                        }
                        break;
                    }
                }
                case 3: {
                    stopTheProgram = true;
                    break;
                }
            }
        }
    }
}
