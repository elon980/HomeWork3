import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        mainMenu();
    }
    //O(n)
    public static void mainMenu(){
        RealEstate realEstate = new RealEstate();
        int choice;
        do {
            Scanner scanner = new Scanner(System.in);
            do {
                System.out.println("What action do you want to take? \n" +
                        "1. Create User \n" +
                        "2. Login account \n" +
                        "3. Finish");
                choice = scanner.nextInt();
            }while (choice<=0 || choice>=4);
            if(choice == 1){
                realEstate.createUser();
            }
            else if(choice == 2){
                User user=realEstate.Userlogin();
                if (user != null)
                    subMenu(user,realEstate);
            }
            else if(choice == 3){
            }
        }while (choice != 3);

    }
    // O(n)
    public static void subMenu(User user,RealEstate realEstate){
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            do {
                System.out.println("What action do you want to take?\n" +
                        "1. Post a new property \n" +
                        "2. Remove advertising on a property \n" +
                        "3. display all propertys in the system \n"+
                        "4. Show all properties published by the user \n"+
                        "5. Search for a property by parameters \n"+
                        "6. Log out and return to the main menu");
                choice = scanner.nextInt();
            } while (choice>6 || choice<1);
            if(choice == 1){
                if (realEstate.postNewProperty(user))
                    System.out.println("The property saved");
                else
                    System.out.println("The property not saved");
            }
            else if(choice == 2){
                realEstate.removeProperty(user);
            }
            else if(choice == 3){
                realEstate.printAllProperties();
            }
            else if(choice == 4){
                realEstate.printProperties(user);
            }
            else if(choice == 5){
                Property[] result = realEstate.search();
                if (result != null) {
                    for (int i = 0; i < result.length; i++) {
                        System.out.println(result[i]);
                    }
                }
                else System.out.println("not found property");
            }
            else if(choice == 6){
                System.out.println("The user has logged out");
            }
        } while (choice != 6);
    }
}
