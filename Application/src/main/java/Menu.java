import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;



public class Menu {
    
    private static final Scanner sc = new Scanner(System.in);

    public static void welcomePage(){
        System.out.println("        :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        System.out.println("        :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        System.out.println("        ::::::::::::::::::::::::::::::::::::::::::::::-=*%@@#+-::::::::::::::::::::::::::::::::::");
        System.out.println("        ::::::::::::::::::::::::::::::::::::::::--+@@@@@@@@@@@@@@@@#---::::::::::::::::::::::::::");
        System.out.println("        :::::::::::::::::::::::::::::::::::-#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*-:::::::::::::::::::::");
        System.out.println("        :::::::::::::::::::::::::::::=#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%*-:::::::::::::");
        System.out.println("        ::::::::::::::::::::::-=*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@#-:::::::::::::");
        System.out.println("        ::::::::::::::::--+@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@#*****-:::::::::::::");
        System.out.println("        ::::::::::::+@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%##********-:::::::::::::");
        System.out.println("        :::::::::::-@%*%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@###************-:::::::::::::");
        System.out.println("        :::::::::::-@*====++#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@###******####****%@@@=:::::::::::::");
        System.out.println("        ::::::::::::@#==========+*%@@@@@@@@@@@@@@@@@@@@@@@**************##**@@@@:::::::::::::::::");
        System.out.println("        ::::::::::::=@+==============+*#@@@@@@@@@@@@@#***************#*#@@@@@@@@@#-::::::::::::::");
        System.out.println("        :::::::::::::-#@@@%+===============+*@@@%*****************#@@@@@@@@@@@@@@@@%=:::::::::::::");
        System.out.println("        :::::::::=*%%%%%@@@@@@%*+=============+***************#@@@@@@@%@@%@@@%@@@%%#-:::::::::::::");
        System.out.println("        :::::::::#%#%##%@@@@%%%@@@@%+=========+***********#@@@@@@@@%%###*********+++:::::::::::::");
        System.out.println("        :::::::::+%++*####%%+#%%%#%%@@@@%+====+++*****@@@@@@%%#*******+*+++***##%%%@%-:::::::::::");
        System.out.println("        :::::::::-%*+++*####%+#%@@@@@@@@@@@@@%*+*%@@@@@@@@@@@%%%%%%%@@@@@@@@@@@%%#+-:::::::::::::");
        System.out.println("        :::::::::-=%%++==***##+*########%@@@@@@@@@@@@@@@@@@@@@@@@%@@@@@@@@@@@@%%%@@@@@@*=-:::::::");
        System.out.println("        ::::::-%%%%%%%*++=+**###%%%%%%@@@@@@@@@@@@@@@@@@@@%%%%%%%%%%%%%%%%%%@@@@@@@@@@@@@@@*:::::");
        System.out.println("        ::::::-@*%%%%%%%*+==+*####*******##%@@@@@@@%%%%%%%%%#######%%@@@@@@@@@@@@@@%#**@@@@#:::::");
        System.out.println("        ::::::-%%***%%%%%#+===+***###%@@%%%%%%%%%%%%%%%%%#%%%%@@@@@@@@%@@%%%%%%#****#@@@@@+:::::::::");
        System.out.println("        :::::%@@@@@@@%*+=+%%@*+=+#########*+*%@@@@@@@@@@@@@@@@%@%%%@%**++**%@@@@@@@=:::::::::::::");
        System.out.println("        :::::-*@@@@@@@@@%*++*%%*+++*##%@@@@%%%%%%%%%%%%%%%%%%%%%#****#@@@@@@@@@@=:::::::::::::::");
        System.out.println("        :::::::::=#@@@@@@@%%+++*#@@@%%%%%%%%%%%%%%%%%%%%%%@######@@@@@@@@@@@%=:::::::::::::::::::");
        System.out.println("        :::::::::::::-#@@@@@@%#+=+*%%%%%%%%%%%%%%%%%@@%####%%@@@@@@@@@@@@@@@%-::::::::::::::::::::");
        System.out.println("        ::::::::::::::::--*@@@@%%*+=+*%%%%%%%%%@@%###%%@@@@@@@@@@@@@@@*-:::::::::::::::::::::::::");
        System.out.println("        :::::::::::::::::::::-*@@@%%*==+*%%%#*##%%@@@@@@@@@@@@@@@@@*-:::::::::::::::::::::::::::::");
        System.out.println("        :::::::::::::::::::::::::-#@@%%*==**#%@@@@@@@@@@@@@@@@@@+::::::::::::::::::::::::::::::::");
        System.out.println("        :::::::::::::::::::::::::::::-*%%%@@@@@@@@@@@@@@@@@@@-:::::::::::::::::::::::::::::::::::");
        System.out.println("        ::::::::::::::::::::::::::::::::--+@@@@@@@@@@@@@@%-::::::::::::::::::::::::::::::::::::::");
        System.out.println("        :::::::::::::::::::::::::::::::::::::-+%@@@@@@#-:::::::::::::::::::::::::::::::::::::::::");
        System.out.println("        ::::::::::::::::::::::::::::::::::::::::::+*-:::::::::::::::::::::::::::::::::::::::::::::");
        System.out.println("        :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        System.out.printf("\n\n\n");
        System.out.println("    d8b          888                                      d8b                   888    d8b                  ");
        System.out.println("    Y8P          888                                      Y8P                   888    Y8P                  ");
        System.out.println("                 888                                                            888                         ");
        System.out.println("    888 88888b.  888  888 88888b.d88b.   8888b.   .d88b.  888 88888b.   8888b.  888888 888  .d88b.  88888b. ");
        System.out.println("    888 888 \"88b 888 .88P 888 \"888 \"88b     \"88b d88P\"88b 888 888 \"88b     \"88b 888    888 d88\"\"88b 888 \"88b");
        System.out.println("    888 888  888 888888K  888  888  888 .d888888 888  888 888 888  888 .d888888 888    888 888  888 888  888");
        System.out.println("    888 888  888 888 \"88b 888  888  888 888  888 Y88b 888 888 888  888 888  888 Y88b.  888 Y88..88P 888  888");
        System.out.println("    888 888  888 888  888 888  888  888 \"Y888888  \"Y88888 888 888  888 \"Y888888  \"Y888 888  \"Y88P\"  888  888");
        System.out.println("                                                      888                                                   ");
        System.out.println("                                                 Y8b d88P                                                   ");
        System.out.println("                                                  \"Y88P\"                                                    ");
        System.out.println("\n\n");
        System.out.println("""
                               Find Everything You Need at Inkmagination Stationery
                              Your One-Stop Shop for Quality Stationery Essentials
                               Welcome to Inkmagination Stationery, Where Quality Meets Convenience
                           
                           """);
        pressEnterToContinue();
    }

    public static void pressEnterToContinue() {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Press Enter to continue...");
        try {
            input.readLine();
        } catch (IOException e) {
        }
    }

    public static int mainMenuPage(){
        clearConsole();
        String[] options = {"Log In", "Sign Up (Member ONLY)","Exit"};

        System.out.println("Stationary Inventory System\n");

        return getOption(options);
    }

    public static int loginMenuPage(){
        clearConsole();
        String[] options = {"Staff Login", "Customer Login", "Back"};

        return getOption(options);
    }


    public static void clearConsole() {
        try {
        if (System.getProperty("os.name").contains("Windows")) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); 
        }
        else {
            System.out.print("\033\143");
        }
    } catch (IOException | InterruptedException ex) {}

    }


    private static void printOptions(String[] options) {
        for (int i = 0; i < options.length; i++) {
            System.out.println(String.format("%02d. %s", i + 1, options[i]));
        }
        System.out.println(" ");
    }

    private static int getOption(String[] options) {
        printOptions(options);
    
        int choice = 0;
        boolean validInput = false; // Flag to track valid input
    
        while (!validInput) {
            System.out.print("Enter your choice: ");
            try {
                choice = sc.nextInt();
                sc.nextLine(); // Consume the newline character
                if (choice < 1 || choice > options.length) {
                    System.out.print("Please try again: ");
                } else {
                    validInput = true; // Valid input, exit the loop
                }
            } catch (InputMismatchException ex) {
                System.out.print("Please Enter Integer Only. ");
                sc.nextLine(); // Consume the invalid input
            }
        }
    
        clearConsole();
    
        return choice;
    }
    

    public static int staffSupplierMenu(){

        clearConsole();
        String[] options = {"View/Search Supplier", "Edit Supplier","Delete Supplier","Add Supplier"};

        return getOption(options);

    }

    public static int customerSupplierMenu(){

        clearConsole();
        String[] options = {"View/Search Supplier"};

        return getOption(options);

    }


    public static int whichToEdit(){

        clearConsole();
        String[] options = {"Name", "Address", "Email", "Cancel"};

        return getOption(options);

    }


}
