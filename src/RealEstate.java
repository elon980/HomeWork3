import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RealEstate {

    private User[] users;
    private Property[] properties;
    private City[] cities;

    private static final int MAX_AD_MEDIATOR = 5;
    private static final int MAX_AD_REGULAR = 2;
    private static final int CITY_LOCATED_INVALID = 100;
    private static final int REGULAR_APARTMENT = 1;
    private static final int PENTHOUSE_APARTMENT = 2;
    private static final int PRIVATE_APARTMENT = 3;
    private static final int FOR_RENT = 1;
    private static final int FOR_SALE = 2;



    public RealEstate() {
        this.cities = new City[]{
                new City("Tel aviv", "center", new String[]{"rotchild", "king david"}),
                new City("ashkelon", "south", new String[]{"eli cohen", "hahovlim"}),
                new City("ashdod", "south", new String[]{"hayalomim", "knan"}),
                new City("hedera", "Hasharon", new String[]{"bialik", "herzel"}),
                new City("zfat", "north", new String[]{"har miron", "hadglim"}),
                new City("eilat", "negev", new String[]{"argman", "horev"}),
                new City("ramt gan", "center", new String[]{"zabootinzki", "petel"}),
                new City("ber sheva", "negev", new String[]{"alfasi", "rambam"}),
                new City("tiberias", "north", new String[]{"ein bokek", "bialik"}),
                new City("natanya", "Hasharon", new String[]{"amlia", "niza"})
        };
    }
    // O(1)
    void printCitiesName() {
        for (int i = 0; i < cities.length; i++) {
            System.out.println(cities[i]);
        }
    }

// O(n)
    void createUser() {
        System.out.println("Enter User Name");
        Scanner scanner = new Scanner(System.in);
        String userName = scanner.nextLine();

        if (this.users != null) {
            for (int i = 0; i < this.users.length; i++) {
                if (this.users[i].getUserName().equals(userName)) {
                    System.out.println("The username is taken, enter a new username");
                    userName = scanner.nextLine();
                    i = -1;
                }
            }
        }

        boolean checkPassword = true;
        String passwordRegex = "^(?=.*[0-9])(?=.*[$%_]).{5,}$";
        Pattern passwordPattern = Pattern.compile(passwordRegex);
        String password;

        do {
            System.out.println("Enter a password");
            password = scanner.nextLine();
            Matcher m = passwordPattern.matcher(password);

            if (m.matches()) {
                checkPassword = false;
            } else {
                System.out.println("The password must be at least 5 characters long and contain % or $ or _ and contains at least one digit");
            }
        }
        while (checkPassword);

        String phoneNumberRegex = "^[0][5][0-9]{8}$";
        Pattern phoneNumberPattern = Pattern.compile(phoneNumberRegex);
        boolean checkPhoneNumber = true;
        String phoneNumber;

        do {
            System.out.println("Enter phone number");
            phoneNumber = scanner.nextLine();
            Matcher m = phoneNumberPattern.matcher(phoneNumber);

            if (m.matches()) {
                checkPhoneNumber = false;
            } else {
                System.out.println("The phone number must start with 05 and contains 10 digits");
            }
        }
        while (checkPhoneNumber);

        String userType;
        boolean checkUserType = true;

        do {
            System.out.println("Enter if you are mediator or regular user?");
            userType = scanner.nextLine();

            if (userType.toLowerCase().equals("mediator") || userType.toLowerCase().equals("regular")) {
                checkUserType = false;
            } else {
                System.out.println("You should enter only mediator or regular");
            }
        }
        while (checkUserType);

        this.addUser(userName, password, phoneNumber, userType);
    }
// O(1)
    private void addUser(String userName, String password, String phone, String userType) {
        User newUser = new User(userName, password, phone, userType);
        if (this.users != null) {
            User[] newUsers = Arrays.copyOf(this.users, this.users.length + 1);
            newUsers[this.users.length] = newUser;
            this.users = newUsers;
        } else {
            this.users = new User[1];
            this.users[0] = newUser;
        }
    }
// O(n)
    User Userlogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your user name");
        String userName = scanner.nextLine();
        System.out.println("Enter your password");
        String password = scanner.nextLine();
        if (this.users != null) {
            for (int i = 0; i < users.length; i++) {
                if (this.users[i].getUserName().equals(userName)) {
                    if (this.users[i].getPassword().equals(password))
                        return users[i];
                }
            }
        }
        System.out.println("Login details are incorrect");
        return null;
    }
// O(1)
    public boolean postNewProperty(User user) {
        boolean isPublishAllowed = false;

        if (isMaxPublishExceeded(user)) {
            if (postProperty(user)) {
                isPublishAllowed = true;
            }
        }
        else
            System.out.println("You are not allowed to post, you have run out of quota");

        return isPublishAllowed;
    }
// O(n)
    private boolean isMaxPublishExceeded(User user) {
        boolean canPublish = false;
        int publishCount = 0;

        if (this.properties != null) {
            for (int i = 0; i < this.properties.length; i++) {
                if (this.properties[i].getPublisher().getUserName() == user.getUserName()) {
                    publishCount++;
                }
            }
        }
        else
            canPublish = true;

        if (user.getUserType().toLowerCase().equals("regular") && publishCount < MAX_AD_REGULAR) {
            canPublish = true;
        } else if (user.getUserType().toLowerCase().equals("mediator") && publishCount < MAX_AD_MEDIATOR) {
            canPublish = true;
        }

        return canPublish;
    }
// O(n)
    boolean postProperty(User user) {
        boolean published = false;
        printCitiesName();
        Scanner scanner = new Scanner(System.in);
        System.out.println("In which city is the property located?");
        String cityName = scanner.nextLine();
        City cityLocated = getCityIfExist(cityName);
        if (cityLocated != null) {
            cityLocated.printStreet();
            System.out.println("In which street is the property located?");
            String street = scanner.nextLine();
            if (checkStreet(street, cityLocated)) {
                System.out.println("What is the property type? \n" +
                        "1 – for a regular apartment in an apartment building.\n" +
                        "2 – for a penthouse apartment in an apartment building.\n" +
                        "3 – for a private house.");
                int houseType = scanner.nextInt();
                if (houseType <= PRIVATE_APARTMENT && houseType >= REGULAR_APARTMENT) {
                    int floor = 0;
                    if (houseType == REGULAR_APARTMENT) {
                        System.out.println("What floor is the property on?");
                        floor = scanner.nextInt();
                    }

                    System.out.println("how much room in the property?");
                    int roomNumber = scanner.nextInt();

                    System.out.println("What is the house number?");
                    int houseNumber = scanner.nextInt();

                    int rentOrSale;
                    do {
                        System.out.println("Enter 1 if the property for rent else enter 2 if for sale");
                        rentOrSale = scanner.nextInt();
                    }
                    while (rentOrSale != FOR_RENT && rentOrSale != FOR_SALE);

                    boolean isRenting = rentOrSale == FOR_RENT ? true : false;
                    System.out.println("What is the property price?");
                    int price = scanner.nextInt();

                    addProperty(cityName, street, houseNumber, roomNumber, price, houseType, isRenting, floor, user);
                    published = true;
                } else
                    System.out.println("INVAILD INPUT");
            } else
                System.out.println("The street does not exist in the list");
        } else
            System.out.println("The city does not exist in the list");
        return published;
    }
// O(1)
    private void addProperty(String cityName, String street, int houseNumber, int roomsCount, int price, int type, boolean ifRenting, int floorNumber, User publisher) {
        Property newProperty = new Property(cityName, street, houseNumber, roomsCount, price, type, ifRenting, floorNumber, publisher);

        if (this.properties != null) {
            Property[] newProperties = Arrays.copyOf(this.properties, this.properties.length + 1);
            newProperties[this.properties.length] = newProperty;
            this.properties = newProperties;
        } else {
            this.properties = new Property[1];
            this.properties[0] = newProperty;
        }
    }
// O(n)
    boolean checkStreet(String street, City cityLocated) {
        boolean checkStreet = false;
        for (int i = 0; i < cityLocated.getStreet().length; i++) {
            if (cityLocated.getStreet()[i].toLowerCase().equals(street.toLowerCase()))
                checkStreet = true;
        }
        return checkStreet;
    }
// O(n)
    City getCityIfExist(String city) {
        City cityLocated = null;
        for (int i = 0; i < this.cities.length; i++) {
            if (this.cities[i].getCityName().toLowerCase().equals(city.toLowerCase())) {
                cityLocated = this.cities[i];
                break;
            }
        }
        return cityLocated;
    }
// O(n)
    void removeProperty(User user) {
        Scanner scanner = new Scanner(System.in);
        if (checkIfPublished(user)) {
            int countProperty = 1;
            for (int i = 0; i < properties.length; i++) {
                if (this.properties[i].getPublisher().getUserName().equals(user.getUserName())) {
                    System.out.println("Property number " + countProperty);
                    System.out.println(this.properties[i]);
                    countProperty++;
                }
            }
            System.out.println("which property do you want to remove:");
            int toRemove = scanner.nextInt();
            Property[] userPropertys = userProperties(countProperty, user);
            newProperty(userPropertys[toRemove - 1]);
        } else
            System.out.println("The user has not published any property");
    }
// O(n)
    private void newProperty(Property toRemove) {
        Property[] newProperties = new Property[this.properties.length - 1];
        int index = 0;
        for (int i = 0; i < this.properties.length; i++) {
            if (!this.properties[i].equals(toRemove)) {
                newProperties[index] = properties[i];
                index++;
            }
        }
        this.properties = newProperties;
        System.out.println("The property has been successfully deleted");
    }
// O(n)
    private Property[] userProperties(int countProperties, User user) {
        Property[] userProperties = new Property[countProperties-1];
        int index = 0;
        for (int i = 0; i < properties.length; i++) {
            if (this.properties[i].getPublisher().getUserName().equals(user.getUserName())) {
                userProperties[index] = this.properties[i];
                index++;
            }
        }
        return userProperties;
    }
// O(n)
    private boolean checkIfPublished(User user) {
        boolean check = false;
        if (properties != null) {
            int publishCount = 0;
            for (int i = 0; i < this.properties.length; i++) {
                if (this.properties[i].getPublisher().getUserName() == user.getUserName())
                    publishCount++;
            }
            if (publishCount != 0)
                check = true;
        }
        return check;
    }
// O(n)
    public void printAllProperties() {
        if (this.properties != null) {
            for (int i = 0; i < this.properties.length; i++) {
                System.out.println(this.properties[i]);
            }
        } else
            System.out.println("No properties exist in the system");
    }
// O(n)
    public void printProperties(User user) {
        for (int i = 0; i < properties.length; i++) {
            if (this.properties[i].getPublisher().getUserName().equals(user.getUserName()))
                System.out.println(this.properties[i]);
        }
    }
// O(n)
    Property[] search(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("For rent 1 or for sale 2:");
        int rentOrSale = scanner.nextInt();
        boolean isRenting = false;
        if (rentOrSale== FOR_RENT)
            isRenting = true;
        System.out.println("What is the property type you search? \n" +
                "1 – for a regular apartment in an apartment building.\n" +
                "2 – for a penthouse apartment in an apartment building.\n" +
                "3 – for a private house.");
        int type = scanner.nextInt();
        System.out.println("how much room numbers?");
        int roomNumber = scanner.nextInt();
        System.out.println("what is the minimum price?");
        int minPrice = scanner.nextInt();
        System.out.println("what is the maximum price?");
        int maxPrice = scanner.nextInt();
        Property[] selectedProperties = null;
        for (int i = 0; i < this.properties.length; i++) {
            Property property = this.properties[i];
            if ((property.isIfRenting()== isRenting || rentOrSale == -999) &&
                    (property.getType() == type || type == -999) &&
                    (property.getRoomsCount() == roomNumber || roomNumber == -999) &&
                    (property.getPrice() >= minPrice || minPrice == -999) &&
                    (property.getPrice() <= maxPrice || maxPrice == -999)){
                if (selectedProperties != null){
                    Property[] temp = Arrays.copyOf(selectedProperties,selectedProperties.length+1);
                    temp[selectedProperties.length+1] = property;
                }
                else {
                    selectedProperties = new Property[1];
                    selectedProperties[0] = property;
                }
            }
        }
        return selectedProperties;
    }
}
