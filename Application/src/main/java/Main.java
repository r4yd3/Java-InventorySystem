import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
//import java.time.Month;
import java.util.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.google.api.services.gmail.Gmail;

import Objects.*;

import static org.fusesource.jansi.Ansi.*;

import static Email.Email.*;

import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;


public class Main {

    private static int CATEGORY_INDEX = 5;
    private static int PRODUCT_INDEX = 8;
    private static List<Order> ordersArray = new ArrayList<>();
    private static final List<Payment> paymentsArray = new ArrayList<>();
    private static List<Inventory> inventory = new ArrayList<>();
    private static Category[] categories = new Category[CATEGORY_INDEX];
    private static Product[] products = new Product[PRODUCT_INDEX];
    private static final List<Supplier> suppliers = new ArrayList<>();
    private static final List<Supplier> uniqueSuppliers = new ArrayList<>();
    private static final Scanner sc = new Scanner(System.in);
    private static Staff currentStaff;
    private static final Cart activeCart = new Cart();
    public static Member currentMember;
    public static void main(String[] args)throws IOException, GeneralSecurityException, MessagingException  {

        // --------------------------------- Sample Data ------------------------------------------

        /*categories[0] = new Category("WT", "Writing Tools");
        categories[1] = new Category("PR", "Paper");
        categories[2] = new Category("MS", "Measuring Tools");
        saveCatData();
        */

       /* inventory.add(new Inventory("PG", "Penang"));
        saveInvData(); */

    /*    products[0] = new Product("Ballpoint Pen", "Has ink", 5, 100, categories[0], inventory.get(0), supplier.get(1));
         products[1] = new Product("A4 Paper", "A4 size", 10, 40, categories[1], inventory.get(0), supplier.get(1));
        products[2] = new Product("Pencil", "Writes using graphite", 2.45, 200, categories[0], inventory.get(0),
                 supplier.get(0));
        products[3] = new Product("Fountain Pen", "Woosh like a fountain", 5, 100, categories[0], inventory.get(0),
                 supplier.get(0));
         products[4] = new Product("Foolscap Paper", "Fooling em all", 1.2, 150, categories[1], inventory.get(0),
                supplier.get(0));
        products[5] = new Product("15CM Ruler", "Max length of 15CM with transparent material", 4, 30, categories[2],
         inventory.get(0), supplier.get(0));  
         saveProdData();
         */

     /* List<Staff> staffList = new ArrayList<>(List.of(
            new Staff("admin", "admin", "inkmaginationstore@gmail.com", "admin", "IT", "admin"),
   			new Staff("Ernest Yip Woon Kit", "23MI103", "ernestywk-pm23@student.tarc.edu.my", "iamernest", "Inventory", "Manager"),
    		new Staff("Jamie Tan", "22MI777", "jamietjt-pm23@student.tarc.edu.my", "jam", "Inventory", "Staff"),
    		new Staff("Chan Yi", "19MP053", "inkmaginationstore@gmail.com", "goatyi", "Procurement", "Manager"),
    		new Staff("Allyne", "20MP391", "allynes-pm23@student.tarc.edu.my", "coolasf", "Inventory", "Manager"),
    		new Staff("Muhammad Ali", "24AT001", "inkmaginationstore@gmail.com", "alithebest", "IT", "Admin"),
    		new Staff("John Wick", "23AT634", "inkmaginationstore@gmail.com", "caughtInTheWicked", "IT", "Admin"),
    		new Staff("Cole Palmer", "21SI623", "inkmaginationstore@gmail.com", "IceNFire", "Inventory", "Staff"),
    		new Staff("Po Palah", "24SP002", "inkmaginationstore@gmail.com", "BaldFraud", "Procurement", "Staff")
		));  
		saveStaffData(staffList); */

        /* Payment payment1 = new Payment("PY1", LocalDateTime.of(2020, Month.JANUARY, 12, 6, 2), 101278, "V");
        Payment payment2 = new Payment("PY2", LocalDateTime.of(2021, Month.FEBRUARY, 12, 6, 2), 110569, "M");
        Payment payment3 = new Payment("PY2", LocalDateTime.of(2022, Month.FEBRUARY, 12, 6, 2), 179992, "T");
        Payment payment4 = new Payment("PY2", LocalDateTime.of(2023, Month.FEBRUARY, 12, 6, 2), 182250, "M");
        Payment payment5 = new Payment("PY2", LocalDateTime.of(2024, Month.FEBRUARY, 12, 6, 2), 208880, "T");
        List<Order> order1 = new ArrayList<>(List.of(
            new Order("20C111OR1", LocalDateTime.of(2020, Month.JANUARY, 12, 6, 0), "DELIVERED", payment1, activeCart),
            new Order("20C111OR3", LocalDateTime.of(2022, Month.JANUARY, 12, 6, 0), "DELIVERED", payment3, activeCart),
            new Order("20C111OR4", LocalDateTime.of(2023, Month.JANUARY, 12, 6, 0), "DELIVERED", payment4, activeCart),
            new Order("20C111OR5", LocalDateTime.of(2024, Month.JANUARY, 12, 6, 0), "DELIVERED", payment5, activeCart),
            new Order("20C111OR2", LocalDateTime.of(2021, Month.FEBRUARY, 12, 6, 0), "DELIVERED", payment2, activeCart)
        ));
        List<Order> emptyOrder = new ArrayList<>();
        List<Order> emptyOrder2 = new ArrayList<>();
        List<Order> emptyOrder3 = new ArrayList<>();
		List<Member> memberList = new ArrayList<>(List.of(
        new Member("Freminet", "23C777", "fremi@gmail.com", "fremboy", true, LocalDateTime.of(2024, Month.SEPTEMBER, 24, 0, 0), emptyOrder),
        new Member("member", "member", "inkmaginationstore@gmail.com", "member", true, LocalDateTime.of(2024, Month.SEPTEMBER, 24, 0, 0), emptyOrder2),
        new Member("Julia Roberts", "21C888", "inkmaginationstore@gmail.com", "iloveocean", true, LocalDateTime.of(2021, Month.DECEMBER, 8, 8, 0), emptyOrder3),
        new Member("lolcat", "20C111", "inkmaginationstore@gmail.com", "lolcat", true, LocalDateTime.of(2024, Month.SEPTEMBER, 24, 0, 0), order1)
        ));
        
        saveMemberData(memberList);  */
       
        //--------------------- Initialisations ------------------------------

        // Member Initialisation

    List<Member> memberList = new ArrayList<>();

        // Staff Initialisation

        List<Staff> staffList = new ArrayList<>();

        // Cart Initialisation

        //--------------------- Loaders ------------------------------

        loadCatData();
        loadInvData();
        loadProdData();
        loadStaffData(staffList);
        loadMemberData(memberList);
        loadSupplierData();

        //--------------------- Main Menu ----------------------------

        Menu.welcomePage();

        while (true){
            Menu.clearConsole();
            activeCart.clearCart();
            currentMember = new Member(); // clear it up
            int choice = menuChoice();
            Menu.clearConsole();
            switch (choice) {
                    case 1 ->{
                        int indexNo = staffLogIn(staffList);
                        if (indexNo == -1){
                            break;
                        } 
                            twoFactor(staffList.get(indexNo).getEmail());
                            currentStaff = staffList.get(indexNo);

                       switch (currentStaff.getAccessLevel()) {
                            case 4 -> adminFunctions(staffList, memberList, indexNo);
                            case 3 -> managerFunctions(staffList, memberList, indexNo);
                            default -> staffFunctions(staffList, indexNo);
                        } 

                        }
                   case 2 ->{
                                int indexNo = customerLogIn(memberList);
                                if(indexNo == -1){
                                    break;
                                }
                                currentMember = memberList.get(indexNo);
                                ordersArray = currentMember.getOrders();
                               
                            twoFactor(memberList.get(indexNo).getEmail());
                            memberFunctions(memberList, indexNo);
                        }
                    case 3 -> userSignUp(memberList);
                    default -> {}
                } 
                
        }

    }
   
    // ---------------------------------------- Email -----------------------------------------

    public static void sendEmail(String toEmail, String subject, String bodyText)throws IOException, GeneralSecurityException, MessagingException {

        Gmail service = getGmailService();
        MimeMessage email = createEmail(toEmail, "inkmaginationstore@gmail.com", subject, bodyText);
        sendMessage(service, "me", email);
    }

    // ---------------------------------------- Menu -----------------------------------------
 
    public static void productMenu() {
        boolean choose = true;
        while (choose) {
            if (currentStaff.getAccessLevel() > 2) {

                Menu.clearConsole();  
                    System.out.println("\n1. List All Products");
                    System.out.println("2. List Specific Product");
                    System.out.println("3. Change Product Details");
                    System.out.println("4. Add New Product");
                    System.out.println("5. Delete Product");
                    System.out.println("6. Exit\n");
                    System.out.print(">");
                    try {
                        int num = sc.nextInt();
                        switch (num) {
                            case 1 -> listAllProd();
                            case 2 -> listProdDetails(currentStaff.getAccessLevel());
                            case 3 -> editProdDetails();
                            case 4 -> addNewProd();
                            case 5 -> deleteProd();
                            case 6 -> {return;}
                            default -> {
                                System.out.println("\nInput must be numbers available on the menu only!"); 
                                sc.nextLine();
                                Menu.pressEnterToContinue();
                            }

                        }
                    } catch (InputMismatchException e) {
                        System.out.println("\nInput must be numbers available on the menu only!");
                        sc.nextLine();
                        Menu.pressEnterToContinue();
                    }

                
            } else if (currentStaff.getAccessLevel() == 2) {
            
                Menu.clearConsole();
                System.out.println("\n1. List All Products");
                System.out.println("2. List Specific Product");
                System.out.println("3. Change Product Stock Quantity");
                System.out.println("4. Exit\n");
                System.out.print(">");
                try {
                    int num = sc.nextInt();
                    switch (num) {
                        case 1 -> listAllProd();
                        case 2 -> listProdDetails(currentStaff.getAccessLevel());
                        case 3 -> setProdQuantity();
                        case 4 -> choose = false;
                        default -> System.out.println("\nInput must be numbers available on the menu only!\n");
                    }
                } catch (Exception e) {
                    System.out.println("\nInput must be numbers available on the menu only!\n");
                }
            
            }
        }

    }

    public static void categoryMenu(){
        boolean choose = true;
        while(choose){
            Menu.clearConsole();
        if (currentStaff.getAccessLevel() > 2) {
                System.out.println("\n1. List All Categories");
                System.out.println("2. List Specific Category");
                System.out.println("3. Change Categories Details");
                System.out.println("4. Add New Category");
                System.out.println("5. Delete Category");
                System.out.println("6. Exit\n");
                System.out.print(">");
                int num = 0;
                try {
                    num = sc.nextInt();
                    sc.nextLine();
                
                } catch (Exception e){
                    }
                    switch (num) {
                        case 1 -> listAllCat();
                        case 2 -> listCatDetails();
                        case 3 -> editCatDetails();
                        case 4 -> addNewCat();
                        case 5 -> deleteCat();
                        case 6 -> {return;}
                        default -> {System.out.println("\nInput must be numbers available on the menu only!\n");
                                    Menu.pressEnterToContinue();
                                    sc.nextLine();
                    }
                    }      
        } else if (currentStaff.getAccessLevel() == 2) {
                System.out.println("\n1. List All Categories");
                System.out.println("2. List Specific Category");
                System.out.println("3. Exit\n");
                System.out.print(">");
                int num = 0;
                try {
                    num = sc.nextInt();} catch (Exception e){}
                    switch (num) {
                        case 1 -> listAllCat();
                        case 2 -> listCatDetails();
                        case 3 -> {return;}
                        default -> {System.out.println("\nInput must be numbers available on the menu only!\n");
                        Menu.pressEnterToContinue();
                                    sc.nextLine();}
                    }
                
            
        }}
    }

    public static void inventoryMenu(){
        boolean choose = true;
        while(choose){
            Menu.clearConsole();
        if (currentStaff.getAccessLevel() > 2) {
                System.out.println("\n1. List All Inventories");
                System.out.println("2. List Specific Inventory");
                System.out.println("3. Change Inventories Details");
                System.out.println("4. Add New Inventory");
                System.out.println("5. Delete Inventory");
                System.out.println("6. Exit\n");
                System.out.print(">");
                int num = 0;
                try {
                    num = sc.nextInt(); sc.nextLine();} catch (Exception e){}
                    switch (num) {
                        case 1 -> listAllInv();
                        case 2 -> listInvDetails();
                        case 3 -> editInvDetails();
                        case 4 -> addNewInv();
                        case 5 -> deleteInv();
                        case 6 -> {return;}
                        default -> {System.out.println("\nInput must be numbers available on the menu only!\n");
                        Menu.pressEnterToContinue();
                        sc.nextLine();
                    }
                    }
        
        } else if (currentStaff.getAccessLevel() == 2) {
                System.out.println("\n1. List All Inventories");
                System.out.println("2. List Specific Inventory");
                System.out.println("3. Exit\n");
                System.out.print(">");
                int num = 0;
                try {
                    num = sc.nextInt();} catch (Exception e){}
                    switch (num) {
                        case 1 -> listAllInv();
                        case 2 -> listInvDetails();
                        case 3 -> {return;}
                        default -> {System.out.println("\nInput must be numbers available on the menu only!\n");
                        Menu.pressEnterToContinue();
                        sc.nextLine();
                    }
                    }
        }}
    }
 
    //++++++++++Jamie+++++++++++++
    public static int menuChoice() {
     	
		boolean menuValid = false;
		int choice = 0;
		
		do{
            int menuChoice;
			System.out.print("\nWelcome to Inkmagination Stationery! What would you like to do?\n\n1. Staff Log In\n2. Customer Log In\n3. Sign Up (Customer)\n4. Exit\n\n> ");
			try {
                menuChoice = sc.nextInt();
                sc.nextLine();
                switch (menuChoice) {
                    case 1 -> {
                        choice = 1;
                        menuValid = true;
                            }
                    case 2 -> {
                        choice = 2;
                        menuValid = true;
                            }
                    case 3 -> {
                        choice = 3;
                        menuValid = true;
                            }
                    case 4 -> {
                                    try (sc) {
                                        System.out.println("Exiting the system...");
                                    }
                        System.exit(0);
                            }
    
                    default -> {
                       Menu.clearConsole();
                        System.out.println("\nInvalid choice, please try again.\n");
                            }
                }
            } catch (Exception e) {
                Menu.clearConsole();
                System.out.println("Invalid choice, please try again.");
                sc.nextLine();
                Menu.pressEnterToContinue();
                Menu.clearConsole();
            }
           
    	}while(!menuValid);
		
		return choice;
     }
   
    //----------------------------------------- Staff -------------------------------------------

    private static void loadStaffData(List<Staff> staffList){
        try (FileInputStream fileIn = new FileInputStream("Application/src/main/java/Binaries/stafflist.bin");
         ObjectInputStream in = new ObjectInputStream(fileIn)) {
        @SuppressWarnings("unchecked")
        List<Staff> loadedStaff = (List<Staff>) in.readObject();
        staffList.clear(); 
        staffList.addAll(loadedStaff); // Add loaded staff
        System.out.println("Staff data loaded successfully.");
    } catch (IOException | ClassNotFoundException e) {
        System.err.println("Error loading staff data: " + e.getMessage());
    }
    }		

    private static void saveStaffData(List<Staff> staffList) {
        try (FileOutputStream fileOut = new FileOutputStream("Application/src/main/java/Binaries/stafflist.bin");
        ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
        out.writeObject(staffList); // Serialize the list of staff
        //System.out.println("Staff data saved successfully.");
    } catch (IOException e) {
        System.err.println("Error saving staff data: " + e.getMessage());
    }
    } 

    public static int staffLogIn (List<Staff> staffList){
		boolean valid = false;
		int index = -1;
        int j = 0;
		do{
	
			System.out.print("\nPlease enter your user ID (Enter 'Back' to return to the Main Menu): ");
			String id = sc.nextLine();
			
			if(id.toUpperCase().equals("BACK")){
				break;
			}
			
			System.out.print("\nPlease enter your password: ");
			String password = sc.nextLine();
		
			for (int i = 0; i < staffList.size(); i++) {
                Staff staff = staffList.get(i);
                if (id.equals(staff.getID())) {
                    if (password.equals(staff.getPassword())) {
                        valid = true;
                        index = i;
                        break; 
                    }
                }
            }
        
			
			if (valid == false){                
				Menu.clearConsole();
				System.out.println("\n\nYour ID or password is incorrect! Please try again.\n");
				j++;
			}
			
			if(j == 4){
				System.out.println("Too many attempts. Please try again later. You will be sent to the main menu.\n");
				Menu.pressEnterToContinue();                
				Menu.clearConsole();
				break;
			}
		}while(!valid);
		
		return index;
	}

    //=================== Menus ===================
    public static void adminFunctions (List<Staff> staffList, List<Member>memberList, int indexNo){
	
		boolean valid = false;
			
        String staffName = staffList.get(indexNo).getName();

		do{
            int adminChoice = 0;
            Menu.clearConsole();
			System.out.printf("\nWelcome, %s%s%s!\n\n", ansi().fgBlue(),staffName, ansi().reset()); 
            System.out.print("""
            What would you like to do?

            1. Manage Products
            2. Manage Categories
            3. Manage Inventories
            4. Manage Staff
            5. Manage Members
            6. Manage Suppliers
            7. Manage Orders
            8. Revenue Report
            9. Log Out
            """);
            System.out.print("\n> ");
            try {
                adminChoice = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
            }
			
		
			switch(adminChoice){
				case 1 -> productMenu();
				case 2 -> categoryMenu();
                case 3 -> inventoryMenu();
				case 4 -> staffEdits(staffList);
				case 5 -> memberEdits(memberList);
				case 6 -> supplierEdits();
                case 7 -> viewAllOrders(memberList);
                case 8 -> revenueReport(memberList);
				case 9 -> {
                                    return;
                        }
				default -> {
                                    System.out.println("Invalid choice, please try again.");
                                    sc.nextLine();
                                    Menu.pressEnterToContinue();
                        }
			}
		}while(!valid);
	}

    public static void managerFunctions(List<Staff> staffList, List<Member>memberList, int indexNo){
	
		boolean valid = false;
			
        String staffName = staffList.get(indexNo).getName();
		
		do{
            Menu.clearConsole();
            int managerChoice = 0;
            System.out.printf("\nWelcome, %s%s%s!\n\n", ansi().fgBrightGreen(),staffName, ansi().reset()); 
            System.out.print("""
                What would you like to do?

                1. Manage Products
                2. Manage Categories
                3. Manage Inventories
                4. Manage Staff
                5. Manage Suppliers
                6. Manage Orders
                7. Revenue Report
                8. Log Out
            """);
            System.out.print("\n> ");
            try {
                 managerChoice = sc.nextInt();
                 sc.nextLine();
            } catch (Exception e) {
            }
			switch(managerChoice){
				case 1 -> productMenu();
				case 2 -> categoryMenu();
                case 3 -> inventoryMenu();
				case 4 -> staffEdits(staffList);
				case 5 -> supplierEdits();
                case 6 -> viewAllOrders(memberList);
                case 7 -> revenueReport(memberList);
				case 8 -> {
                                    return;
                        }
				default -> {
                                    System.out.println("Invalid choice, please try again.");
                                    sc.nextLine();
                                    Menu.pressEnterToContinue();
                        }
			}
		}while(!valid);
	}

    public static void staffFunctions(List<Staff> staffList, int indexNo){
		
		boolean valid = false;
			
        String staffName = staffList.get(indexNo).getName();
		
		do{
            Menu.clearConsole();
            int staffChoice = 0;
            System.out.printf("\nWelcome, %s%s%s!\n\n", ansi().fgMagenta(),staffName, ansi().reset()); 
            System.out.print("""
                What would you like to do?

                1. Manage Products
                2. Manage Suppliers
                3. Log Out
            """);
            System.out.print("\n> ");
            try {
                staffChoice = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
            }
			switch(staffChoice){
				case 1 -> productMenu();
				case 2 -> supplierEdits();
				case 3 -> {
                                    return;
                        }
				default -> {
                                    Menu.clearConsole();
                                    System.out.println("Invalid choice, please try again.");
                                    sc.nextLine();
                        }
			}
		}while(!valid);
	}
    
    public static void staffEdits(List<Staff> staffList){
		
		boolean valid = false;
		
		do{
            Menu.clearConsole();
		System.out.print("\nWhat would you like to do?\n1. Show all staff\n2. Search specific staff\n3. Add staff\n4. Edit staff\n5. Delete staff\n6. Return to previous menu\n\n> ");
		int staffChoice = 0;
        try {
            staffChoice = sc.nextInt();
            sc.nextLine();
        } catch (Exception e) {
        }	
			switch(staffChoice){
				case 1 -> {
                                    Menu.clearConsole();
                                    displayStaff(staffList);
                        }
				case 2 -> {
                                    Menu.clearConsole();
                                    searchStaff(staffList);
                        }
				case 3 -> {
                                    Menu.clearConsole();
                                    addStaff(staffList);
                        }
				case 4 -> {
                                    Menu.clearConsole();
                                    editStaff(staffList);
                        }
				case 5 -> {
                                    Menu.clearConsole();
                                    deleteStaff(staffList);
                        }
				case 6 -> {
                                    return;
                        }
				default -> {
                                    System.out.println("Invalid choice, please try again.");
                                    sc.nextLine();	
                                    Menu.pressEnterToContinue();
                        }
			}
		}while(!valid);
	}
    
    //=============================================
    public static void displayStaff(List<Staff> staffList){
        
        for (Staff staff : staffList) {
            if (!staff.getID().equals("")) {
            	staff.currentUserInfo(); 
            }
            	
        }    
        
        Menu.pressEnterToContinue();
        Menu.clearConsole();   
	}
   
    public static void searchStaff(List<Staff> staffList){
		boolean valid = false;
		
		do{
			System.out.print("\n\nPlease enter the ID of the staff you want to search (Enter 'Back' to go to the previous menu): ");
			String idSearch = sc.nextLine();
			
			if(idSearch.equals("Back")){
				Menu.clearConsole();
				valid = true;	
			}
			else{
				for (int i = 0; i < staffList.size(); i++) {
                	Staff staff = staffList.get(i);
                	if (idSearch.equals(staff.getID())) {
                    	staff.currentUserInfo();
                    	valid = true;
                   		Menu.pressEnterToContinue();
                    	break; 
                	}
            	}
            	if(!valid){
            		Menu.clearConsole();
            		System.out.print("The ID you entered is invalid! Please re-enter the ID.");
            	}
			}
        }while(!valid);
	}

    public static void addStaff(List<Staff> staffList){
		boolean valid = false, dptValid = false, posValid = false;
		String idDpt = "", idPos = "", newID, newDpt = "", newPos = "";
		
		do{
			System.out.print("\n\nPlease enter the name: ");
			String newName = sc.nextLine();
			
			System.out.print("\n\nPlease enter the email: ");
			String newMail = sc.nextLine();
			
			do{
				System.out.print("\n\nPlease enter the department of the staff (Inventory, Procurement or IT): ");
				newDpt = sc.nextLine();
				
				
                            switch (newDpt) {
                                case "Inventory" -> {
                                    idDpt = "I";
                                    dptValid = true;
                                }
                                case "Procurement" -> {
                                    idDpt = "P";
                                    dptValid = true;
                                }
                                case "IT" -> {
                                    idDpt = "T";
                                    dptValid = true;
                                }
                                default -> System.out.print("\nThe department you input is not valid!");
                            }
			}while(!dptValid);
			
			do{
				System.out.print("\n\nPlease enter the position of the staff (Admin, Manager or Staff): ");
				newPos = sc.nextLine();
				
                            switch (newPos) {
                                case "Admin" -> {
                                    idPos = "A";
                                    posValid = true;
                                }
                                case "Manager" -> {
                                    idPos = "M";
                                    posValid = true;
                                }
                                case "Staff" -> {
                                    idPos = "T";
                                    posValid = true;
                                }
                                default -> System.out.print("\nThe position you input is not valid!");
                            }
			}while(!posValid);
			
			System.out.print("\n\nPlease enter the password: ");
			String newPassword = sc.nextLine();
			
			System.out.print("\n\nPlease re-enter the password: ");
			String repeatPass = sc.nextLine();
			
			if(newPassword.equals(repeatPass)){
				
				int idNumber = (staffList.size() - 5);
					
				if (staffList.size() < 18){
					newID = "24" + idDpt + idPos + "00" + idNumber;
				}
				else if (staffList.size()<108){
					newID = "24C0" + idDpt + idPos + "0" + idNumber;
				}
				else{
					newID = "24C" + idDpt + idPos + idNumber;
				}
				Staff newStaff = new Staff(newName, newID, newMail, newPassword, newDpt, newPos);
                staffList.add(newStaff);
                valid = true;
				System.out.print("\nYour account has successfully been created!\n");
				saveStaffData(staffList);
				Menu.pressEnterToContinue();
				Menu.clearConsole();
			}
			else{
				Menu.clearConsole();
				System.out.print("Your passwords does not match! Please try again.");
			}
		}while(!valid);
		
	}

    public static void editStaff(List<Staff> staffList){
		boolean valid = false, dptValid = false, posValid = false;
		@SuppressWarnings("unused")
        String idDpt = "", idPos = "", newID, newDpt = "", newPos = "";
		
		do{
			System.out.print("\n\nPlease enter the ID of the staff you want to edit (Enter 'Back' to go to the previous menu): ");
			String idSearch = sc.nextLine();
			
			if(idSearch.equals("Back")){
				Menu.clearConsole();
				valid = true;	
			}
			else{
				for (int i = 0; i < staffList.size(); i++) {
                	Staff staff = staffList.get(i);
                	if (idSearch.equals(staff.getID())) {
                    	do{
							System.out.print("\n\nPlease enter the name: ");
							String newName = sc.nextLine();
			
							System.out.print("\n\nPlease enter the email: ");
							String newMail = sc.nextLine();
			
							do{
								System.out.print("\n\nPlease enter the department of the staff (Inventory, Procurement or IT): ");
								newDpt = sc.nextLine();
				
				
                                                            switch (newDpt) {
                                                                case "Inventory" -> {
                                                                    idDpt = "I";
                                                                    dptValid = true;
                                                                }
                                                                case "Procurement" -> {
                                                                    idDpt = "P";
                                                                    dptValid = true;
                                                                }
                                                                case "IT" -> {
                                                                    idDpt = "T";
                                                                    dptValid = true;
                                                                }
                                                                default -> System.out.print("\nThe department you input is not valid!");
                                                            }
							}while(!dptValid);
			
							do{
								System.out.print("\n\nPlease enter the position of the staff (Admin, Manager or Staff): ");
								newPos = sc.nextLine();
				
                                                            switch (newPos) {
                                                                case "Admin" -> {
                                                                    idPos = "A";
                                                                    posValid = true;
                                                                }
                                                                case "Manager" -> {
                                                                    idPos = "M";
                                                                    posValid = true;
                                                                }
                                                                case "Staff" -> {
                                                                    idPos = "T";
                                                                    posValid = true;
                                                                }
                                                                default -> System.out.print("\nThe position you input is not valid!");
                                                            }
							}while(!posValid);
			
							System.out.print("\n\nPlease enter the password: ");
							String newPassword = sc.nextLine();
			
							System.out.print("\n\nPlease re-enter the password: ");
							String repeatPass = sc.nextLine();
			
							if(newPassword.equals(repeatPass)){
								newID = staff.getID();
								Staff updatedStaff = new Staff(newName, newID, newMail, newPassword, newDpt, newPos);
								staffList.set(i, updatedStaff);
                				valid = true;
								System.out.print("\nYour account has successfully been edited!\n");
								saveStaffData(staffList);
								Menu.pressEnterToContinue();
								Menu.clearConsole();
							}
							else{
								Menu.clearConsole();
								System.out.print("Your passwords does not match! Please try again.");
							}
						}while(!valid); 
                	}
            	}
            	if(!valid){
            		Menu.clearConsole();
            		System.out.print("The ID you entered is invalid! Please re-enter the ID.");
            	}
			}
        }while(!valid);
	}

    public static void deleteStaff(List<Staff> staffList){
		boolean valid = false;
		
		do{
			System.out.print("\n\nPlease enter the ID of the staff you want to delete (Enter 'Back' to go to the previous menu): ");
			String idSearch = sc.nextLine();
			
			if(idSearch.equals("Back")){
				Menu.clearConsole();
				valid = true;	
			}
			else{
				for (int i = 0; i < staffList.size(); i++) {
                	Staff staff = staffList.get(i);
                	if (idSearch.equals(staff.getID())) {
                    	staff.deleteUser();
                    	valid = true;
                    	System.out.print("\nThe staff has successfully been deleted!");
						saveStaffData(staffList);
                   		Menu.pressEnterToContinue();
                   		Menu.clearConsole();
                    	break; 
                	}
            	}
            	if(!valid){
            		Menu.clearConsole();
            		System.out.print("The ID you entered is invalid! Please re-enter the ID.");
            	}
			}
        }while(!valid);
	}

    // ---------------------------------------- Members -----------------------------------------

    public static void memberEdits(List<Member> memberList){

		boolean valid = false;
		
		do{
            Menu.clearConsole();
		System.out.print("What would you like to do?\n1. Show all member\n2. Search specific member\n3. Add member\n4. Edit member\n5. Delete member\n6. View member orders\n7. Return to previous menu\n\n> ");
        int memberChoice = 0;
        try {
            memberChoice = sc.nextInt();
            sc.nextLine();
        } catch (Exception e) {
        } 
		
			switch(memberChoice){
				case 1 -> {
                                    Menu.clearConsole();
                                    displayMember(memberList);
                        }
				case 2 -> {
                                    Menu.clearConsole();
                                    searchMember(memberList);
                        }
				case 3 -> {
                                    Menu.clearConsole();
                                    addMember(memberList);
                        }
				case 4 -> {
                                    Menu.clearConsole();
                                    editMember(memberList);
                        }
				case 5 -> {
                                    Menu.clearConsole();
                                    deleteMember(memberList);
                        }
                case 6 -> {
                    Menu.clearConsole();
                    viewAllOrders(memberList);
                        }
				case 7 -> {
                                    return;
                        }
				default -> {
                                    System.out.println("Invalid choice, please try again.");
                                    sc.nextLine();	
                                    Menu.pressEnterToContinue();
                        }
			}
		}while(!valid);
	}

    private static void saveMemberData(List<Member> memberList) {
        try (FileOutputStream fileOut = new FileOutputStream("Application/src/main/java/Binaries/memberlist.bin");
        ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
        out.writeObject(memberList); // Serialize the list of members
        //System.out.println("Member data saved successfully.");
    } catch (IOException e) {
        System.err.println("Error saving member data: " + e.getMessage());
    }
    }
   
    private static void loadMemberData(List<Member> memberList){
        try (FileInputStream fileIn = new FileInputStream("Application/src/main/java/Binaries/memberlist.bin");
         ObjectInputStream in = new ObjectInputStream(fileIn)) {
        @SuppressWarnings("unchecked")
        List<Member> loadedMember = (List<Member>) in.readObject();
        memberList.clear(); 
        memberList.addAll(loadedMember); // Add loaded members
        System.out.println("member data loaded successfully.");
    } catch (IOException | ClassNotFoundException e) {
        System.err.println("Error loading member data: " + e.getMessage());
    }
    }	

    public static void displayMember(List<Member> memberList){
        
        for (Member member : memberList) {
            if (!member.getID().equals("")) {
            	member.currentUserInfo(); 
            }
            	
        }    
        
        Menu.pressEnterToContinue();
        Menu.clearConsole();   
	}

	public static void searchMember(List<Member> memberList){
		boolean valid = false;
		
		do{
			System.out.print("\n\nPlease enter the ID of the member you want to search (Enter 'Back' to go to the previous menu): ");
			String idSearch = sc.nextLine();
			
			if(idSearch.toUpperCase().equals("BACK")){
				Menu.clearConsole();
				valid = true;	
			}
			else{
				for (int i = 0; i < memberList.size(); i++) {
                	Member member = memberList.get(i);
                	if (idSearch.equals(member.getID())) {
                    	member.currentUserInfo();
                    	valid = true;
                   		Menu.pressEnterToContinue();
                    	break; 
                	}
            	}
            	if(!valid){
            		Menu.clearConsole();
            		System.out.print("The ID you entered is invalid! Please re-enter the ID.");
            	}
			}
        }while(!valid);
	}

    public static void addMember(List<Member> memberList){
		boolean valid = false, memStat = false, memValid = false;
		LocalDateTime newTime = null;
		
		do{
			System.out.print("\n\nPlease enter your name: ");
			String newName = sc.nextLine();
			
			System.out.print("\n\nPlease enter your email: ");
			String newMail = sc.nextLine();
			
			do{
				System.out.print("\n\nIs the customer a member (Y/N): ");
				String newMem = sc.nextLine();
			
                            switch (newMem) {
                                case "Y", "y" -> {
                                    memStat = true;
                                    newTime = LocalDateTime.now();
                                    memValid = true;
                                }
                                case "N", "n" -> memValid = true;
                                default -> System.out.print("\n\nInvalid choice! Please re-enter!");
                            }
			}while(!memValid);
			
			System.out.print("\n\nPlease enter your password: ");
			String newPassword = sc.nextLine();
			
			System.out.print("\n\nPlease re-enter your password: ");
			String repeatPass = sc.nextLine();
			
			if(newPassword.equals(repeatPass)){
				if (memberList.size() < 10){
					String newID = "24C00" + (memberList.size() + 1);
					Member newMember = new Member(newName, newID, newMail, newPassword, memStat, newTime, null);
                	memberList.add(newMember);
				}
				else if (memberList.size()<100){
					String newID = "24C0" + (memberList.size() + 1);
					Member newMember = new Member(newName, newID, newMail, newPassword, memStat, newTime, null);
                	memberList.add(newMember);
				}
				else{
					String newID = "24C" + (memberList.size() + 1);
					Member newMember = new Member(newName, newID, newMail, newPassword, memStat, newTime, null);
                	memberList.add(newMember);
				}
			
				
				System.out.print("\nYour account has successfully been created!\n");
				valid = true;
				saveMemberData(memberList);
				Menu.pressEnterToContinue();
				Menu.clearConsole();
			}
			else{
				Menu.clearConsole();
				System.out.print("Your passwords does not match! Please try again.");
			}
		}while(!valid);
	}

    public static void editMember(List<Member> memberList){
		boolean valid = false, memStat = false, memValid = false;
		LocalDateTime newTime = null;
		String newID;
		do{
			System.out.print("\n\nPlease enter the ID of the member you want to edit (Enter 'Back' to go to the previous menu): ");
			String idSearch = sc.nextLine();
			
			if(idSearch.equals("Back")){
				Menu.clearConsole();
				valid = true;	
			}
			else{
				for (int i = 0; i < memberList.size(); i++) {
                	Member member = memberList.get(i);
                	if (idSearch.equals(member.getID())) {
                    	do{
							System.out.print("\n\nPlease enter the new name: ");
							String newName = sc.nextLine();
			
							System.out.print("\n\nPlease enter the new email: ");
							String newMail = sc.nextLine();
			
							do{
								System.out.print("\n\nIs the customer a member (Y/N): ");
								String newMem = sc.nextLine();
			
                                                            switch (newMem) {
                                                                case "Y", "y" -> {
                                                                    memStat = true;
                                                                    LocalDateTime timeNow = LocalDateTime.now();
                                                                    newTime = timeNow.plusYears(2);
                                                                    memValid = true;
                                                                }
                                                                case "N", "n" -> memValid = true;
                                                                default -> System.out.print("\n\nInvalid choice! Please re-enter!");
                                                            }
							}while(!memValid);
			
							newID = member.getID();
								Member updatedMember = new Member(newName, newID, newMail, member.getPassword(), memStat, newTime, null);
								memberList.set(i, updatedMember);
                				valid = true;
								System.out.print("\nThe account has successfully been edited!\n");
								saveMemberData(memberList);
								Menu.pressEnterToContinue();
								Menu.clearConsole();
						}while(!valid); 
                	}
            	}
            	if(!valid){
            		Menu.clearConsole();
            		System.out.print("The ID you entered is invalid! Please re-enter the ID.");
            	}
			}
        }while(!valid);
	}

    public static void editCurrMember(List<Member> memberList){
        Menu.clearConsole();
        System.out.println("============ Fill in your new details =============");
        System.out.print("\nType X to return.");
        boolean valid = false, memStat = false;
        for (int i = 0; i < memberList.size(); i++) {
            Member member = memberList.get(i);
            if (currentMember.getID().equals(member.getID())) {
                do{
                    System.out.print("\n\nPlease enter your new name: ");
                    String newName = sc.nextLine();
                    if (newName.toUpperCase().equals("X")){
                        return;
                    }
                    System.out.print("\n\nPlease enter your new email: ");
                    String newMail = sc.nextLine();
                    if (newMail.toUpperCase().equals("X")){
                        return;
                    }
                    System.out.print("\n\nPlease enter your new password: ");
                    String newPassword = sc.nextLine();
                    if (newPassword.toUpperCase().equals("X")){
                        return;
                    }
                    System.out.print("\n\nPlease re-enter your new password: ");
                    String repeatPass = sc.nextLine();
                    if (repeatPass.toUpperCase().equals("X")){
                        return;
                    }
                    if(newPassword.equals(repeatPass)){
                        Member updatedMember = new Member(newName, member.getID(), newMail, newPassword, memStat, member.getMemberExpiry(), null);
                        memberList.set(i, updatedMember);
                        valid = true;
                        System.out.print("\nYour account has successfully been updated!\n");
                        saveMemberData(memberList);
                        Menu.pressEnterToContinue();
                        Menu.clearConsole();
                    }
                    else{
                        Menu.clearConsole();
                        System.out.print("Your passwords does not match! Please try again.");
                    }
                }while(!valid); 
            }
        }
    }

    public static void deleteMember(List<Member> memberList){
		boolean valid = false;
		
		do{
			System.out.print("\n\nPlease enter the ID of the member you want to delete (Enter 'Back' to go to the previous menu): ");
			String idSearch = sc.nextLine();
			
			if(idSearch.equals("Back")){
				Menu.clearConsole();
				valid = true;	
			}
			else{
				for (int i = 0; i < memberList.size(); i++) {
                	Member member = memberList.get(i);
                	if (idSearch.equals(member.getID())) {
                    	member.deleteUser();
                    	valid = true;
                    	System.out.print("\nThe member has successfully been deleted!");
						saveMemberData(memberList);
                   		Menu.pressEnterToContinue();
                   		Menu.clearConsole();
                    	break; 
                	}
            	}
            	if(!valid){
            		Menu.clearConsole();
            		System.out.print("The ID you entered is invalid! Please re-enter the ID.");
            	}
			}
        }while(!valid);
	}

    public static int customerLogIn( List<Member> memberList){
		boolean valid = false;
		int index = -1;
        int j = 0;
		do{
	
			System.out.print("Please enter your user ID (Enter 'Back' to return to the Main Menu): ");
			String id = sc.nextLine();
			
			if(id.toUpperCase().equals("BACK")){
				return index;
			}
			
			System.out.print("\nPlease enter your password: ");
			String password = sc.nextLine();
        
            for (int i = 0; i < memberList.size(); i++) {
                Member member = memberList.get(i);
                if (id.equals(member.getID())) {
                    if (password.equals(member.getPassword())) {
                        valid = true;
                        index = i;
                        break; 
                    }
                }
            }
       
			if (valid == false){                
				Menu.clearConsole();
				System.out.println("\n\nYour ID or password is incorrect! Please try again.\n");
				j++;
			}
			
			if(j == 4){
				System.out.println("Too many attempts. Please try again later. You will be sent to the main menu.\n");
				Menu.pressEnterToContinue();                
				Menu.clearConsole();
				return index;
			}
		}while(!valid);
		
		return index;
	}
	
	public static void userSignUp(List<Member> memberList){
		boolean valid = false;
		String newID;
		
		do{
			System.out.print("\nPlease enter your name: ");
			String newName = sc.nextLine();
			
			System.out.print("\nPlease enter your email: ");
			String newMail = sc.nextLine();
			
			System.out.print("\nPlease enter your password: ");
			String newPassword = sc.nextLine();
			
			System.out.print("\nPlease re-enter your password: ");
			String repeatPass = sc.nextLine();
			
            LocalDateTime memberExp = LocalDateTime.now().plusYears(2);
            List<Order> emptyOrder = new ArrayList<>();
			if(newPassword.equals(repeatPass)){
				if (memberList.size() < 10){
					newID = "24C00" + (memberList.size() + 1);
					Member newMember = new Member(newName, newID, newMail, newPassword, false, memberExp, emptyOrder);
                	memberList.add(newMember);
				}
				else if (memberList.size()<100){
					newID = "24C0" + (memberList.size() + 1);
					Member newMember = new Member(newName, newID, newMail, newPassword, false, memberExp, emptyOrder);
                	memberList.add(newMember);
				}
				else{
					newID = "24C" + (memberList.size() + 1);
					Member newMember = new Member(newName, newID, newMail, newPassword, false, memberExp, emptyOrder);
                	memberList.add(newMember);
				}
                saveMemberData(memberList);
				System.out.print("\nYour account has successfully been created! Your new ID is: " + newID + ".\n");
				Menu.pressEnterToContinue();
        		return;
			}
			else{
				Menu.clearConsole();
				System.out.print("Your passwords does not match! Please try again.");
			}
		}while(!valid);
	}
	
    //+++++++++++++++++++++++++++++ Allyne ++++++++++++++++++++++++++++++++++++

    //=================== Menus ===================
    public static void memberFunctions (List<Member> memberList, int indexNo){
		Menu.clearConsole();
		boolean custIsLoggedIn = true;

		
		while (custIsLoggedIn){
			Menu.clearConsole();
            System.out.println("=========== Member ==========");
            System.out.println("1. Browse & Purchase Products");
            System.out.println("2. View Orders");
            System.out.println("3. View Account"); // aka Preferences
            System.out.println("4. Logout");

            System.out.print("\nSelect an option: ");
            int option = 0;
            try {
                option = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
            }

			switch(option){
				case 1 -> {
                            Menu.clearConsole();
                            browseNPurchase(memberList);
                        }
				case 2 -> {
                            Menu.clearConsole();
                            viewOrders();
                        }
				case 3 -> {
                    viewCurrMember(memberList);
                        }
				case 4 -> {Menu.clearConsole(); return;}
				default -> {
                            System.out.println("Invalid choice, please try again.");
                            sc.nextLine();
                            Menu.pressEnterToContinue();
                        }	
			}
        }
	}
  
    public static void viewCurrMember(List<Member> memberList){
        while(true){
            Menu.clearConsole();
            System.out.println("\n======== Your Account Info ========");
            currentMember.currentUserInfo();
            System.out.println("\n====================================");
            System.out.println("1. Edit Details");
            System.out.println("2. Renew Membership");
            System.out.println("3. Exit");
            OUTER:
            while(true){
                System.out.print("\n> ");
                int choice = 0;
                try {
                    choice = sc.nextInt();
                sc.nextLine();
                } catch (Exception e) {
                }
                switch(choice){
                    case 1 -> {editCurrMember(memberList); break OUTER;}
                    case 2 -> {updateMemberStatus(memberList);break OUTER;}
                    case 3 -> {return;}
                    default -> {
                        System.out.println("\nInvalid input! Try again.\n");
                        sc.nextLine();
                        Menu.pressEnterToContinue();
                    }
                }
            }
            
        }
    }

    public static void browseNPurchase(List<Member> memberList) {
        boolean browseNbuy = true;
        if (!currentMember.getMemberStatus()){
            System.out.println("\nMembership status expired! Please renew under View Account.\n");
            Menu.pressEnterToContinue();
            return;
        }
        while (browseNbuy) {
            int option = 0;
            Menu.clearConsole();
            System.out.println("======= Browse & Purchase Products ======");
            System.out.println("1. View All Products"); 
            System.out.println("2. Search Specific Product");
            System.out.println("3. Manage Cart"); 
            System.out.println("4. Return to previous menu");
            
            System.out.print("\nSelect an option: ");
            try {
                option = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
            }
            switch (option) {
                case 1 -> {
                    Menu.clearConsole();
                    viewAllProd();
                }
                case 2 -> {
                    Menu.clearConsole();
                    listProdDetails(1);
                }
                case 3 -> {
                    Menu.clearConsole();
                    manageCart(memberList);
                }
                case 4 -> {
                    Menu.clearConsole();
                    browseNbuy = false; // return to the previous menu
      
                }
                default -> {
                    System.out.println("Invalid option. Please try again.");
                    sc.nextLine();
                    Menu.pressEnterToContinue();
                }
            }
        }
    }
           
    public static void viewOrders(){
    
            boolean custIsLoggedIn = true;
            int option2 = 0;
            while (custIsLoggedIn) {
                Menu.clearConsole();
                System.out.println("======= View Orders ======");
                System.out.println("1. View Current Orders");
                System.out.println("2. Order History");
                System.out.println("3. Go back");
    
                System.out.print("\nSelect an option: ");
                try {
                    option2 = sc.nextInt();
                    sc.nextLine();
                } catch (Exception e) {
                }
                  switch (option2) {
                    case 1 -> {
                        Menu.clearConsole();
                        viewCurrentOrders();
                    }
                    case 2 -> {
                        Menu.clearConsole();
                        viewPurchaseHistory();
                    }
                    case 3 -> {
                        Menu.clearConsole();
                        return;
                    }
                    default -> {
                        System.out.println("Invalid option. Please try again.");
                        sc.nextLine();
                        Menu.pressEnterToContinue();
                    }
                }
            }
        }
    
    private static void manageCart(List<Member> memberList) {
            if (activeCart.getItems().isEmpty()) {
                System.out.println("\nThere are no items in your cart.\n");
                Menu.pressEnterToContinue();
            } else {
                boolean manageCart = true;
                while (manageCart) {
                    int option = 0;
                    Menu.clearConsole();
                    displayCart(activeCart);
    
                    System.out.println("\n======= Manage Cart ======");
                    System.out.println("1. Change item quantity"); 
                    System.out.println("2. Clear cart");
                    System.out.println("3. Checkout"); 
                    System.out.println("4. Return to previous menu");
            
                    System.out.print("\nSelect an option: ");
                    try {
                        option = sc.nextInt();
                    sc.nextLine();
                    } catch (Exception e) {
                    }
            
                    switch (option) {
                        case 1 -> {
                            Menu.clearConsole();
    
                            if (activeCart.isEmpty()) {
                                Menu.clearConsole();
                                System.out.println("\nYour cart is empty. Redirecting you to the previous page.");
                                Menu.pressEnterToContinue();
                            } else {
                                changeItemQuantity(activeCart);
                            }
                        }
                        case 2 -> {
                            activeCart.clearCart();
                            Menu.clearConsole();
                            System.out.println("\nYour cart is empty. Redirecting you to the previous page.");
                            Menu.pressEnterToContinue();
                        }
                        case 3 -> {
                            Menu.clearConsole();
                        
                            if (activeCart.isEmpty()) {
                                Menu.clearConsole();
                                System.out.println("\nYour cart is empty. Redirecting you to the previous page.");
                                Menu.pressEnterToContinue();
                            } else {
                                checkout(activeCart, memberList);
                            }
         
                        }
                        case 4 -> {
                            Menu.clearConsole();
                            manageCart = false; // return to the previous menu
                        }
                        default -> {
                            System.out.println("Invalid option. Please try again.");
                            sc.nextLine();
                            Menu.pressEnterToContinue();
                        }
                    }            
                }
            }    
        }
    
        //----------------------------------------------------------- Order Functions ------------------------------------------------------------------------
    
         // METHOD TO VIEW CURRENT ORDERS
        private static void viewCurrentOrders() {
        
            System.out.println("================== My Current Orders ===============\n");
            if (ordersArray.isEmpty()) {
                System.out.println("There are no ongoing orders found.");
            }
            for (Order order : ordersArray) {
                // Skip orders with status "Delivered"
                if (!(order.getStatus()).toUpperCase().equals("DELIVERED")) {
                    System.out.println("Order ID: " + order.getID());
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    System.out.println("Order Date: " + order.getOrderDate().format(formatter));
                    System.out.println("Status: " + order.getStatus());
        
                    // Payment details
                    Payment payment = order.getPayment();
                    if (payment != null) {
                        System.out.println(payment.toString());
                    } else {
                        System.out.println("There are no payment details available.");
                    }
        
                    // Items in the activeCart that was ordered
                    Cart cart = order.getCart();
                    System.out.println("Items Purchased");
                    for (Item item : cart.getItems()) {
                        System.out.println("Item: " + item.getProduct().getName());
                        System.out.println("Quantity: " + item.getQuantity());
                    }
                    System.out.println("-----------------------------------------");
                }
            }
        
            // Prompt user to return to the previous menu
            while (true) {
                System.out.print("Enter 'R' to return to the previous menu: ");
                String option = sc.nextLine();
                if (option.equals("R")||option.equals("r")) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter 'R' to return to the previous menu.");
                }
            }
        }
    
        // METHOD TO VIEW PURCHASE HISTORY
        private static void viewPurchaseHistory() {
           
        
            System.out.println("================ My Purchase History ==============\n");
            if (ordersArray.isEmpty()) {
                System.out.println("There are no orders found.");
            }
            for (Order order : ordersArray) {
                // Only show orders with status "Delivered"
                if ((order.getStatus()).equals("DELIVERED")) {
                    System.out.println("Order ID: " + order.getID());
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    System.out.println("Order Date: " + order.getOrderDate().format(formatter));
                    System.out.println("Status: " + order.getStatus());
        
                    // Payment details
                    Payment payment = order.getPayment();
                    if (payment != null) {
                        System.out.println(payment.toString());
                    } else {
                        System.out.println("There are no payment details available.");
                    }
        
                    // Items in the activeCart that was ordered
                    Cart cart = order.getCart();
                    System.out.println("Items Purchased");
                    for (Item item : cart.getItems()) {
                        System.out.println("Item: " + item.getProduct().getName());
                        System.out.println("Quantity: " + item.getQuantity());
                    }
                    System.out.println("-----------------------------------------");
                }
            }
        
            // Prompt user to return to the previous menu
            while (true) {
                System.out.print("Enter 'R' to return to the previous menu: ");
                String option = sc.nextLine();
                if (option.equals("R")||option.equals("r")) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter 'R' to return to the previous menu.");
                }
            }
        }
    
        // METHOD TO CHANGE QTY OF AN ITEM IN CART
        public static void changeItemQuantity(Cart cart) {
    
            displayCart(cart);
    
            System.out.print("\nEnter item no. to change quantity: ");
            int itemNumber = -1;
            try {     
                 itemNumber = sc.nextInt();
            } catch (Exception e) {
            }
            sc.nextLine();
        
            System.out.print("\nEnter new quantity: ");
            int newQuantity = -1;
            try {
                newQuantity = sc.nextInt();
            } catch (Exception e) {
                
            }
            sc.nextLine();
        
            List<Item> tempItem = cart.getItems();
            Item item = new Item();
            try {
                item = tempItem.get(itemNumber - 1);
            } catch (Exception e) {
            } 
            int index = 0;
            for (int i = 0; i < products.length; i++) {
                try {
                    if (products[i].getID().equals(item.getProduct().getID())) {
                       index = i;
                    }
                } catch (Exception e) {
                }
            }
            if (itemNumber > 0 && itemNumber <= tempItem.size()) {
                try {
                    if(newQuantity >= 0 && newQuantity <= products[index].getStockQty()){
                        item.setQuantity(newQuantity);
                        System.out.println("\nQuantity updated.");
                    } else if (newQuantity == 0){
                        tempItem.remove(item);
                        System.out.println("Quantity updated.");
                    } else {
                        System.out.println("Quantity exceeds stock or is in negative.");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Invalid item number.");
            }
            System.out.println();
            Menu.pressEnterToContinue();
        }
    
        // METHOD FOR CHECK OUT
        public static void checkout(Cart cart, List<Member> memberList) {
            while(true){
                Menu.clearConsole();
                System.out.println("======================= Checkout ========================\n");
                displayCart(cart);
            
                System.out.print("Proceed with checkout? (Y/N): ");
                String option = sc.nextLine();
            
                if (option.equalsIgnoreCase("Y")) {
                    Menu.clearConsole();
            
                    String paymentMethod;
                    OUTER:
                    while (true) {
                        System.out.print("\nEnter payment method (T-TouchNGo, V-Visa, M-Mastercard, R-Return): ");
                        paymentMethod = sc.nextLine().toUpperCase();
                        switch (paymentMethod) {
                            case "T", "V", "M" -> {
                                break OUTER;
                            }
                            case"R" -> {
                                return;
                            }
                            default -> System.out.println("\nInvalid payment method. Please try again, or enter 'R' to return to the previous menu.\n");
                        }
                    }
                    
                    boolean paymentSuccessful = false;
                    while(!paymentSuccessful){
                        if (paymentMethod.equals("T")) {
                            System.out.print("\nEnter TouchNGo account number: ");
                            String accNumber = sc.nextLine();
                            String touchNGoPattern = "^[0-9]{10,12}$";//account number must be 10-12 digits, from 0-9
                            if (Pattern.matches(touchNGoPattern, accNumber)) {
                                paymentSuccessful = true;
                            } else if (accNumber.toUpperCase().equals("X")){
                                break; //get out of paymentSuccessful
                            }else {
                                System.out.println("\nInvalid account number. TouchNGo account number should consist of 10 digits without dashes. Please try again, or enter 'R' to return to the previous menu.");
                            }
                        } else {
                            System.out.print("\nEnter card number: ");
                            String cardNumber = sc.nextLine();
                            String cardNumberPattern = "^(?:4[0-9]{12}(?:[0-9]{3})?|5[1-5][0-9]{14})$";
                            if (Pattern.matches(cardNumberPattern, cardNumber)) {
                                System.out.print("\nEnter CVV: ");
                                String cvv = sc.nextLine();
                                String cvvPattern = "^[0-9]{3,4}$";
                                if (Pattern.matches(cvvPattern, cvv)) {
                                    paymentSuccessful = true;
                                } else if (cvv.toUpperCase().equals("X")){
                                    break; //get out of paymentSuccessful
                                }else {
                                    System.out.println("\nInvalid CVV. Please try again. X to cancel.");
                                }
                            } else if (cardNumber.toUpperCase().equals("X")){
                                break; //get out of paymentSuccessful
                            } else {
                                System.out.println("\nInvalid card number. Card number should consist of 13 or 16 digits and without dashes. Please try again, or enter 'R' to return to the previous menu.");
                            }
                        }
                    }
            
                    if (paymentSuccessful) {
                        double amountPaid = calculateTotalPrice(cart);
                        String newPaymentID = generateNewPaymentID();
                        Payment newPayment = new Payment(newPaymentID, LocalDateTime.now(), amountPaid, paymentMethod);
                        paymentsArray.add(newPayment);
                        String newOrderID;
                        try {
                            newOrderID = currentMember.getID() + "ORD" + (ordersArray.size() + 1);
                        } catch (NullPointerException e) {
                            ordersArray = new ArrayList<>();
                            newOrderID = currentMember.getID() + "ORD1";
                        }
                        Order newOrder = new Order(newOrderID, LocalDateTime.now(), "PROCESSING", newPayment, cart);
                            ordersArray.add(newOrder);
                        // Create a deep copy of the ordersArray
                        ArrayList<Order> ordersArrayCopy = new ArrayList<>();
                        for (Order order : ordersArray) {
                            ordersArrayCopy.add(new Order(order));
                        }
                        currentMember.setOrders(ordersArrayCopy);
                        //saveOrdersToFile();
                        saveMemberData(memberList);
                        //for loop here to get rid of product quantities
                        for (Item loopItem: activeCart.getItems()){
                            try {
                                for (Product loopProd : products){
                                    try {
                                        if (loopItem.getProduct().getID().equals(loopProd.getID())){ // could just compare product to product but paranoia so just compare ID
                                            loopProd.setStockQty(loopProd.getStockQty() - loopItem.getQuantity());
                                        }
                                    } catch (Exception e) {
                                    }   
                                }
                            } catch (Exception e) {
                            }
                            
                        }
                        saveProdData();
                            System.out.print("\nWould you like a receipt?(Y/N): ");
                            char getYN = sc.next().charAt(0);
                            if(!verifyYN(getYN, "Would you like a receipt?(Y/N): ")){
                                activeCart.clearCart();
                                return;
                            }
                        generateReceipt(newOrder);
                            activeCart.clearCart();
                        System.out.println("\nPayment successful. Your receipt has been generated.\n");
                        Menu.pressEnterToContinue();
                        break;
                    } 
                } else {
                    System.out.println("Checkout canceled. Returning to previous menu.");
                    break;
                }
            }
        }
    
        // METHOD TO DISPLAY CART ITEMS
    public static void displayCart(Cart cart) {
        System.out.println("\t No. \t\t Item \t\t\t\t Quantity \t\t Subtotal \t\t\t");
        System.out.println("=============================================================================================");
    
        // Display each item with its subtotal
        int index = 1;
        for (Item item : activeCart.getItems()) {
            double subtotal = calculateSubtotal(item);
            System.out.printf("\t %-5d \t\t %-20s \t\t %-12d \t\t %-11.2f \t\t\n", index, item.getProduct().getName(), item.getQuantity(), subtotal);
            System.out.println("---------------------------------------------------------------------------------------------");
            index++;
        }
    
        // Display total price at the bottom
        double totalPrice = calculateTotalPrice(activeCart);
        System.out.printf("\t\t\t\t\t\t\u001B[31mGRAND TOTAL: \t %.2f\u001B[0m\n\n", totalPrice);
    }
    
        // METHOD TO CALC SUBTOTAL FOR EACH ITEM IN CART
        public static double calculateSubtotal(Item item) {
            double price = item.getProduct().getPrice();
            int quantity = item.getQuantity();
            return price * quantity;
        }
    
        // METHOD TO CALC GRAND TOTAL IN CART
        public static double calculateTotalPrice(Cart cart) {
            double totalPrice = 0;
            for (Item item : cart.getItems()) {
                totalPrice += calculateSubtotal(item);
            }
            return totalPrice;
        }
    
        //METHOD TO GENERATE NEW PAYMENT ID INCREMENTALLY
        private static String generateNewPaymentID() {
            if (paymentsArray.isEmpty()) {
                return "PY1";
            } else {
                String lastID = paymentsArray.get(paymentsArray.size() - 1).getID();
                int lastNum = Integer.parseInt(lastID.substring(2)); // Extract the number from ID
                return "PY" + (lastNum + 1); // Increment the number and return the new ID
            }
        }
    
        // METHOD TO WRITE ORDERS INTO BIN FILE
        public static void saveOrdersToFile() {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Application/src/main/java/Binaries/Orders.bin"))) {
                oos.writeObject(ordersArray);
                System.out.println("Orders successfully saved to Orders.bin.");
            } catch (IOException e) {
                System.out.println("Error while saving orders to file: " + e.getMessage());
            }
        }
    
        // METHOD TO LOAD ORDERS FROM BIN FILE
    
        public static void loadOrdersFromFile(){
            try (ObjectInputStream oos = new ObjectInputStream(new FileInputStream("Application/src/main/java/Binaries/Orders.bin"))){
                @SuppressWarnings("unchecked")
                List<Order> tempOrders = (List<Order>) oos.readObject();
                ordersArray.clear();
                ordersArray.addAll(tempOrders);
            } catch (Exception e) {
                System.err.println("Error loading order data: " + e.getMessage());
            }
        }
    
        // METHOD TO GENERATE RECEIPT FOR SUCCESSFULLY PAID ORDER
        public static void generateReceipt(Order order) {

    
        // Create a unique filename for the receipt using the order ID
        String receiptFileName ="Application/src/main/java/Receipt/Receipt_" + order.getID() + ".txt";
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(receiptFileName))) {
            // Write the header and order details
            writer.write("=============== Receipt ===============\n");
            writer.write("Receipt No.: " + order.getID() + "\n\n");
    
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            writer.write("Order Date: " + order.getOrderDate().format(formatter) + "\n");
            writer.write("Status: " + order.getStatus() + "\n\n");
    
            // Write payment details
            Payment payment = order.getPayment();
            if (payment != null) {
                writer.write("Payment Details:\n");
                writer.write(payment.toString() + "\n\n");
            }
    
            // Write details of purchased tempItem
            Cart cart = order.getCart();
            writer.write("Items Purchased:\n");
            for (Item item : cart.getItems()) {
                writer.write("Item: " + item.getProduct().getName() + "\n");
                writer.write("Quantity: " + item.getQuantity() + "\n");
                writer.write("Price per item: " + item.getProduct().getPrice() + "\n");
                writer.write("Subtotal: " + calculateSubtotal(item) + "\n\n");
            }
    
            // Write grand total
            writer.write("=======================================");
            writer.write("\nTotal: " + calculateTotalPrice(cart) + "\n");
            writer.write("=======================================\n");
    
            // Confirm receipt saved
            System.out.println("\nReceipt successfully generated: " + receiptFileName);
    
        } catch (IOException e) {
            System.out.println("Error generating receipt: " + e.getMessage());
        }
    }
    
    //----------------------------------------------------------- Order(Staff) Functions ------------------------------------------------------------------------
    
        // VIEW ACTIVE ORDERS
        public static void viewAllOrders(List<Member> memberList){
            Menu.clearConsole();
              System.out.println("========================================== Member Orders ==========================================\n");
            for (Member member: memberList){
                try {
                    for(Order order: member.getOrders()){
                            System.out.printf("%-6s\t%-30s\t%-6s\t%-15s\t%-10s\n", order.getID(), order.getOrderDate(), member.getID(), member.getName(), order.getStatus());
                        
                    }
                } catch (Exception e) {
                }
                
                
            }
            System.out.println("\n===================================================================================================\n");
            System.out.print("\nChange order status? (Y/N): ");
            char tempYN = sc.next().charAt(0);
                if(!verifyYN(tempYN, "Change order status? (Y/N): ")){
                    return;
                } else {
                    changeOrderStatus(memberList);
                }
            
            Menu.pressEnterToContinue();
        }
    
        // CHANGE ORDER STATUS
    
        public static void changeOrderStatus(List<Member> memberList){
            String tempMemID;
            Menu.clearConsole();
            System.out.println("===================== Change Order Status =====================\n");
            while(true){
                System.out.print("\nEnter member ID (X to return): ");
                tempMemID = sc.next().toUpperCase();
                sc.nextLine();
                System.out.println();
                if(tempMemID.equals("X")){
                    return;
                }
                int memberIndex = -1;
                for (Member member: memberList){
                    if(member.getID().toUpperCase().equals(tempMemID)){
                        memberIndex = memberList.indexOf(member);
                        try {
                            for (Order order: member.getOrders()){
                                System.out.printf("%-6s\t%s\t%s\n", order.getID(), order.getOrderDate(), order.getStatus());
                            }  
                            break;
                        } catch (NullPointerException e) {
                            System.out.println("No orders.");
                            break;
                        }
                            
                    }
                }
               
                if (memberIndex != -1){
                    System.out.print("\nSelect Order ID: ");
                    String tempOID = sc.next().toUpperCase();
                    sc.nextLine();
                    OUTER: // label
                    for (Order order : memberList.get(memberIndex).getOrders()) {
                        if(order.getID().toUpperCase().equals(tempOID)){
                            System.out.println("\nStatuses: PROCESSING, DELIVERING, DELIVERED\n");
                            while(true){
                                System.out.print("Choose one: ");
                                String tempStatus = sc.next().toUpperCase();
                                sc.nextLine();
                                if (tempStatus.equals("X")){ // allow user to cancel
                                    break OUTER; //break out to OUTER label
                                }
                                String status = switch(tempStatus){ // use a rule switch to check if input is correct
                                    case "PROCESSING", "DELIVERING", "DELIVERED" -> tempStatus;
                                    default -> "X";
                                };
                                if (status.equals("X")){ // input wrong
                                    System.out.println("\nWrong input! Try again. X to cancel.\n");
                                } else {
                                    order.setStatus(status);
                                    saveMemberData(memberList);
                                    System.out.println("\nOrder " + order.getID() + " status changed to: " + tempStatus);
                                    break OUTER;
                                }
                            }    
                        }
                    }
                    System.out.print("\nChange another order status? (Y/N): ");
                    char tempYN = sc.next().charAt(0);
                    if (!verifyYN(tempYN, "Change another order status? (Y/N): ")){
                        System.out.println();
                        break;
                    }
                } else {
                    System.out.println("Wrong member ID!");
                }    
            }
        }
    
        public static void revenueReport(List<Member> memberList){
            Menu.clearConsole();
            double revFirstYr = 0, revSecondYr = 0, revThirdYr = 0, revFourthYr = 0;
            int startYr,secondYr, thirdYr, endYr;
            System.out.println("\n========= Revenue Report =========\n");
            System.out.println("Range of report must be four years and earliest start year is 2016. E.g 2020-2024. Enter 8 to cancel.\n");
            while(true){
                while(true){
                    System.out.print("Enter the start year: ");
                    try {
                        startYr = sc.nextInt();
                        sc.nextLine();
                        if (startYr == 8){
                            return;
                        }
                        break;
                    } catch (Exception e) {
                        System.out.println("\nOnly integer values are allowed!\n");
                        sc.nextLine();
                    }
                } 
                while(true){
                    System.out.print("\nEnter the end year: ");
                    try {
                        endYr = sc.nextInt();
                        sc.nextLine();
                        if (startYr == 8){
                            return;
                        }
                        break;
                    } catch (Exception e) {
                        System.out.println("\nOnly integer values are allowed!\n");
                        sc.nextLine();
                    }
                }
                if (endYr - startYr != 4){
                    System.out.println("\nYears are not within range of four years. Try again.\n");
                } else if (endYr > 2024){
                    System.out.println("\nEnd year exceeded current year. Try again.\n");
                } else if (startYr < 2016){
                    System.out.println("\nStart year is earlier than 2016 year. Try again.\n");
                } else {
                    break;
                }
            }
            secondYr = startYr + 1;
            thirdYr = secondYr + 1;
            for (Member loopMember: memberList){
                try {
                    for (Order orders : loopMember.getOrders()) {
                        try {
                            if(orders.getOrderDate().getYear() == startYr){
                                revFirstYr += orders.getPayment().getAmountPaid();
                            } else if (orders.getOrderDate().getYear() == secondYr){
                                revSecondYr += orders.getPayment().getAmountPaid();
                            }else if (orders.getOrderDate().getYear() == thirdYr){
                                revThirdYr += orders.getPayment().getAmountPaid();
                            }else if (orders.getOrderDate().getYear() == endYr){
                                revFourthYr += orders.getPayment().getAmountPaid();
                            }
                        } catch (Exception e) {
                        }  
                    }
                } catch (Exception e) {
                }
                
            }
            Menu.clearConsole();
            System.out.println("\n=============== Revenue Report ===============\n");
            System.out.println("\tYear\t\tRevenue");
            System.out.printf("\t%d\t\t%.2f\n", startYr, revFirstYr);
            System.out.printf("\t%d\t\t%.2f\n", secondYr, revSecondYr);
            System.out.printf("\t%d\t\t%.2f\n", thirdYr, revThirdYr);
            System.out.printf("\t%d\t\t%.2f\n", endYr, revFourthYr);
            System.out.println();
            Menu.pressEnterToContinue();
        }
    
        public static void updateMemberStatus(List<Member> memberList){
            String paymentMethod;
            OUTER:
            while (true) {
                System.out.print("\nEnter payment method (T-TouchNGo, V-Visa, M-Mastercard, R-Return): ");
                paymentMethod = sc.nextLine().toUpperCase();
                switch (paymentMethod) {
                    case "T", "V", "M" -> {
                        break OUTER;
                    }
                    case"R" -> {
                        return;
                    }
                    default -> System.out.println("\nInvalid payment method. Please try again, or enter 'R' to return to the previous menu.\n");
                }
            }    
            boolean paymentSuccessful = false;
            while(!paymentSuccessful){
                if (paymentMethod.equals("T")) {
                    System.out.print("\nEnter TouchNGo account number: ");
                    String accNumber = sc.nextLine();
                    String touchNGoPattern = "^[0-9]{10,12}$";//account number must be 10-12 digits, from 0-9
                    if (Pattern.matches(touchNGoPattern, accNumber)) {
                        paymentSuccessful = true;
                    } else if (accNumber.toUpperCase().equals("X")){
                        break; //get out of paymentSuccessful
                    }else {
                        System.out.println("\nInvalid TouchNGo account number. Please try again. X to cancel.");
                    }
                } else {
                    System.out.print("\nEnter card number: ");
                    String cardNumber = sc.nextLine();
                    String cardNumberPattern = "^(?:4[0-9]{12}(?:[0-9]{3})?|5[1-5][0-9]{14})$";
                    if (Pattern.matches(cardNumberPattern, cardNumber)) {
                        System.out.print("\nEnter CVV: ");
                        String cvv = sc.nextLine();
                        String cvvPattern = "^[0-9]{3,4}$";
                        if (Pattern.matches(cvvPattern, cvv)) {
                            paymentSuccessful = true;
                        } else if (cvv.toUpperCase().equals("X")){
                            break; //get out of paymentSuccessful
                        }else {
                            System.out.println("\nInvalid CVV. Please try again. X to cancel.");
                        }
                    } else if (cardNumber.toUpperCase().equals("X")){
                        break; //get out of paymentSuccessful
                    } else {
                        System.out.println("\nInvalid card number. Please try again. X to cancel.");
                    }
                }
            }
            if (paymentSuccessful){
                currentMember.setMemberStatus(true);
                currentMember.setMemberExpiry(LocalDateTime.now().plusYears(2));
                saveMemberData(memberList);
                System.out.println("\nPayment is successful! Your membership has been renewed.\n");
                Menu.pressEnterToContinue();
            }
        }

    // ----------------------------------------- Products Functions -----------------------------------------
    public static void saveProdData(){
        try{
            Product[] newProducts = new Product[PRODUCT_INDEX];
            for (int i = 0; i < products.length; i++){
            try {
                    newProducts[i] = products[i];
                } catch (Exception e) {
                    break;
                }
            }
            products = newProducts;
        } catch(Exception e){
            System.out.print(e);
        }
        
        try (FileOutputStream fileOut = new FileOutputStream(
                "Application/src/main/java/Binaries/Product.bin");
                ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                    out.writeInt(PRODUCT_INDEX);
            out.writeObject(products); 
        } catch (IOException e) {
            System.err.println("Error saving product data: " + e.getMessage());
            Menu.pressEnterToContinue();
        }
    }

    public static void loadProdData(){
        try(FileInputStream fileIn = new FileInputStream("Application/src/main/java/Binaries/Product.bin");
        ObjectInputStream in = new ObjectInputStream(fileIn)
        )
        {   
            PRODUCT_INDEX = in.readInt();
            products = (Product[]) in.readObject();

            for (int i = PRODUCT_INDEX; i > 0; i--){
                try {
                    if(products[i] != null){
                        Product.setNumOfProd(i+1);
                        break;
                    }  
                } catch (Exception e) {
                }

            }
        } catch (IOException | ClassNotFoundException e){
            System.err.println("Error saving product data: " + e.getMessage());
            Menu.pressEnterToContinue();
        }
    }

    public static int searchProdID() {
        Menu.clearConsole();
        String tempID;
        int index = -1;
        boolean notValidID = true;

        while (notValidID) {
            System.out.print("\nEnter Product ID > ");
            tempID = sc.next();
            sc.nextLine();
            for (int i = 0; i < products.length; i++) {

                try {

                    if (products[i].getID().toUpperCase().equals(tempID.toUpperCase())) {

                        index = i;
                        notValidID = false;

                    } else if (tempID.toUpperCase().equals("X")) {
                        notValidID = false;
                    }

                } catch (Exception e) {

                }

            }
            if (notValidID) {
                System.out.println("\nWrong product ID! Please re-enter! X to return.");
            }
        }

        return index;
    }

    public static int searchProdName() {
        Menu.clearConsole();
        String tempName;
        int index = -1;
        boolean notValidID = true;
        sc.nextLine();
        while (notValidID) {
            System.out.print("\nEnter Product Name: ");
            tempName = sc.nextLine();
            for (int i = 0; i < products.length; i++) {

                try {

                    if (products[i].getName().toUpperCase().equals(tempName.toUpperCase())) {

                        index = i;
                        notValidID = false;

                    } else if (tempName.toUpperCase().equals("X")) {
                        notValidID = false;
                    }

                } catch (Exception e) {

                }

            }
            if (notValidID) {
                System.out.println("\nWrong product Name! Please re-enter! X to return.");
            }
        }

        return index;
    }

    public static void editProdDetails() {

        String tempID, tempName, tempDesc;
        double tempPrice;
        int tempStockQty;
        int i = getProdIndex();
        boolean notValidProp = true;
        boolean validDetails = true;
        if (i == -1) {
            return;
        }
        
        while (notValidProp) {
            System.out.println(products[i].toString());
            System.out.print("\nWhich property to change: ");
            String getProp = sc.next().toUpperCase();
            sc.nextLine();
            System.out.println();
            try {
                ProdProperties prodProperties = ProdProperties.valueOf(getProp);
                switch (prodProperties) {
                    case ID -> {
                        System.out.print("Update ID: ");
                        tempID = sc.next();
                        if (!verifyProdID(tempID.toUpperCase()) && tempID.length() >= 3 && tempID.length() <= 8 || products[i].getID().equals(tempID)) {
                            products[i].setID(tempID);
                        } else {
                            System.out.println(
                                    "\nID is already not available or length is not between 3 to 8 characters.");
                        }
                    }
                    case NAME -> {
                        System.out.print("Update Name: ");
                        sc.nextLine();
                        tempName = sc.nextLine();
                        if (tempName.length() >= 2 && tempName.length() <= 20 || products[i].getName().equals(tempName)) {
                            products[i].setName(tempName);
                        } else {
                            System.out.println("\nName must be between 2 to 20 characters.");

                        }
                    }
                    case DESCRIPTION -> {
                        System.out.print("Update Description: ");
                        sc.nextLine();
                        tempDesc = sc.nextLine();
                        if (tempDesc.length() < 60){
                            products[i].setDescription(tempDesc);
                        } else {
                            System.out.println("\nDescription must be less than 60 characters.");
                        }
                    }
                    case PRICE -> {
                        System.out.print("Update Price: ");
                        try {
                            tempPrice = Double.parseDouble(sc.next());
                            products[i].setPrice(tempPrice);
                        } catch (NumberFormatException e) {
                            System.out.println("\nPrice must be a float or integer!");
                        }
                    }

                    case QUANTITY -> {
                        System.out.print("Update Stock Quantity: ");
                        try {
                            tempStockQty = Integer.parseInt(sc.next());
                            if (tempStockQty < 0){
                                System.out.println("\nStock Quantity must be zero or above.");
                            } else {
                                products[i].setStockQty(tempStockQty);
                            }       
                        } catch (NumberFormatException e) {
                            System.out.println("\nQuantity must be integer!");
                        }
                    }
                    case CATEGORY -> products[i].setCategory(addCatProd());
                    case INVENTORY -> products[i].setInventory(addInvProd());
                    case SUPPLIER -> products[i].setSupplier(addSupProd());
                }
                saveProdData();
            
            } catch (Exception e) {
                System.out.println("Wrong input!");
                validDetails = false;
            }
                String question;
                if (validDetails) {
                    question = "\nChange another property? (Y/N): ";
                    System.out.print(question);
                    } else {
                    question = "\nTry again? (Y/N): ";
                    System.out.print(question);
                }
                char repeat = sc.next().charAt(0);
                System.out.println();
                Menu.clearConsole();
                if (!verifyYN(repeat, question)) { notValidProp = false;}
                    
                
            
        }

    }

    public static void setProdQuantity() {

        int tempStockQty;
        boolean notValidProp = true;
        int i = getProdIndex();
if (i == -1){
    return;
}
System.out.println(products[i].toString() + "\n");

        while (notValidProp) {

            try {

                System.out.print("Change quantity to: ");
                tempStockQty = Integer.parseInt(sc.next());
                sc.nextLine();
                if(tempStockQty < 0){
                   System.out.println("\nStock Quantity must be zero or above.\n");
                }else {
                    products[i].setStockQty(tempStockQty);
                    saveProdData();
                    break;
                }
            } catch (NumberFormatException e) {

                System.out.println("\nQuantity must be integer!\n");

            }
        }

    }

    public static void listProdDetails(int accessLevel) {
        Menu.clearConsole();
        int i = getProdIndex();

        if (i == -1) {
            return;
        }
        Menu.clearConsole();
        System.out.println("\n-----------------------------------------------");
        System.out.println(products[i].toString());
        System.out.println("\n-----------------------------------------------");
        boolean notConfirmed = true;
        while (notConfirmed) {
            if (accessLevel > 1 || products[i].getStockQty() == 0) {
                System.out.println();
                Menu.pressEnterToContinue();
                notConfirmed = false;
            } else {
                    System.out.print("\nWould you like to buy one? (Y/N)> ");
                    char goBack = sc.next().charAt(0);
                    if (!verifyYN(goBack, "Would you like to buy one? (Y/N)> "))  {
                        System.out.println();
                            Menu.pressEnterToContinue();
                            return;
                    }

                buy(i);
                    System.out.print("\nBuy another one? (Y/N) > ");
                    char buyAgain = sc.next().charAt(0);
                    if (verifyYN(buyAgain, "Buy another one? (Y/N) >")) {
                        buy(i);
                    }
                
                return;
            }

        }

    }

    public static void listAllProd() {
        Menu.clearConsole();
        System.out.println("\n-----------------------------------------------");
        for (Product product : products) {
            try {
                if (product != null) {
                    System.out.println(product.toString());
                    System.out.println("\n-----------------------------------------------");
                }
            } catch (Exception e) {
            }
        }
        sc.nextLine();
        System.out.println();
        Menu.pressEnterToContinue();

    }

    public static Category addCatProd() {
        String getProp;
        Category tempCategory = new Category();
        boolean notValidProp = true;
        System.out.println("Categories to choose:");
        for (Category category : categories) {
            try {
                System.out.println(category.toString());
            } catch (Exception e) {
            }
        }

        while (notValidProp) {
            System.out.print("\nChoose a Category (ID): ");
            getProp = sc.next();

            for (Category category : categories) {
                try {
                    if (category.getID().equals(getProp.toUpperCase())) {
                        System.out.println("\nCategory updated!");
                        tempCategory = category;
                        notValidProp = false;
                    } else if (getProp.toUpperCase().equals("X")) {
                        notValidProp = false;
                    }
                } catch (Exception e) {

                }
            }
            if (notValidProp) {
                System.out.println("\nCategory ID not available. Try again or X to return.");
            }

        }
        return tempCategory;
    }

    public static Inventory addInvProd(){
        String getInvID;
        Inventory tempInv = new Inventory();
        boolean notValidInv = true;
        System.out.println("Inventories to choose from: ");
        for(Inventory inventories: inventory){
            try {
                System.out.println("\n" + inventories.toString());
            } catch (Exception e) {
            }
        }
        while(notValidInv){
            System.out.print("\nChoose a Inventory (ID): ");
            getInvID = sc.next();

            for (Inventory inventories : inventory) {
                try {
                    if (inventories.getID().equals(getInvID.toUpperCase())) {
                        System.out.println("\nInventory updated!");
                        tempInv = inventories;
                        notValidInv = false;
                    } else if (getInvID.toUpperCase().equals("X")) {
                        notValidInv = false;
                    }
                } catch (Exception e) {

                }
            }
            if (notValidInv) {
                System.out.println("\nInventory ID not available. Try again or X to return.");
            }
        }

        return tempInv;
    }

    public static Supplier addSupProd(){
        String getSupID;
        Supplier tempSup = new Supplier();
        boolean notValidSup = true;
        System.out.println("Suppliers to choose from: ");
        for (Supplier supplier: suppliers){
            try {
                System.out.println("\n" + supplier.toString());
            } catch (Exception e) {
            }
        }
        while(notValidSup){
            System.out.print("\nChoose a Supplier (ID): ");
            getSupID = sc.next();
            for (Supplier supplier : suppliers) {
                try {
                    if (supplier.getID().equals(getSupID.toUpperCase())) {
                        System.out.println("\nSupplier updated!");
                        tempSup = supplier;
                        notValidSup = false;
                    } else if (getSupID.toUpperCase().equals("X")) {
                        notValidSup = false;
                    }
                } catch (Exception e) {

                }
            }
            if (notValidSup) {
                System.out.println("\nSupplier ID not available. Try again or X to return.");
            }
        }

        return tempSup;
    }

    public static boolean verifyProdID(String ID) {
        boolean idFound = false;

        for (Product product : products) {
            try {
                if (product.getID().equals(ID)) {
                    idFound = true;
                }
            } catch (Exception e) {
            }
        }

        return idFound;
    }

    public static void addNewProd() {
        Menu.clearConsole();
            System.out.print("\nWould you like to add a new product to the existing list?(Y/N) > ");
                char decision = sc.next().charAt(0);
                if (!verifyYN(decision, "Would you like to add a new product to the existing list?(Y/N) > ")) {
                return;
                } 
        Menu.clearConsole();
        boolean notInputAgain = true;
    while(notInputAgain){
        Menu.clearConsole();
            double tempPrice = 0;
            int tempQty = 0;
            boolean[] validInput = {true, true, true, true, true, true, true};
        System.out.println("-------------------- Fill in the details below for the Product --------------------");
        System.out.println("\nName: ");
        System.out.println("Description: ");
        System.out.println("Price: ");
        System.out.println("Stock Quantity: ");
        System.out.println("Category ID: ");
        System.out.println("Inventory ID: ");
        System.out.println("Supplier ID: ");
        System.out.println("\n-----------------------------------------------------------------------------------");
        sc.nextLine();
        System.out.print(ansi().cursor(3, 7));
        String tempName = sc.nextLine();
        System.out.print(ansi().cursor(4, 14));
        String tempDesc = sc.nextLine();
        System.out.print(ansi().cursor(5, 8));
        try {
            tempPrice = sc.nextDouble();
        } catch (Exception e) {
            validInput[2] = false;
        }
        sc.nextLine();
        System.out.print(ansi().cursor(6, 17));
        try {
            tempQty = sc.nextInt();
        } catch (Exception e) {
            validInput[3] = false;
        }
        sc.nextLine();
        System.out.print(ansi().cursor(7, 14));
        String tempCatID = sc.next().toUpperCase();
        sc.nextLine();
        System.out.print(ansi().cursor(8, 15));
        String tempInvID = sc.next().toUpperCase();
        sc.nextLine();
        System.out.print(ansi().cursor(9, 14));
        String tempSupID = sc.next().toUpperCase();
        sc.nextLine();
        System.out.print(ansi().cursor(11, 0));
        if (tempName.length() < 2 || tempName.length() > 20) {
            validInput[0] = false;
        }
        if (tempDesc.length() > 60){
            validInput[1] = false;
        }
        if (tempPrice <= 0 || tempPrice >= 100000){
            validInput[2] = false;
        }
        if (tempQty <= 0 || tempQty >= 10000000){
            validInput[3] = false;
        }
        if (!verifyCatID(tempCatID)){
            validInput[4] = false;
        }
        if(!verifyInvID(tempInvID)){
            validInput[5] = false;
        }
        if(!verifySupID(tempSupID)){
            validInput[6] = false;
        }
        boolean hasError = false;
    for (int i = 0; i < validInput.length; i++){
        if(!validInput[i]){
            if(!hasError){
                System.out.println("\nWrong input or format for: \n");
            }
            switch(i){
                case 0 -> System.out.println("Name (Must be between 2 to 20 characters)");
                case 1 -> System.out.println("Description (Must be less than 60 characters)");
                case 2 -> System.out.println("Price (Must be integer or float value and not exceed 100000)");
                case 3 -> System.out.println("Stock quantity (Must be integer and not exceed 10000000)");
                case 4 -> System.out.println("Category ID (Must exist already)");
                case 5 -> System.out.println("Inventory ID (Must exist already)");
                case 6 -> System.out.println("Supplier ID (Must exist already)");
                
            }
            hasError = true;
        }
    }
    System.out.println("");
   
     if(hasError){
        System.out.print("\nDo you want to key in again? (Y/N)> ");
     char goBack = sc.next().charAt(0);
     if(!verifyYN(goBack, "Do you want to key in again? (Y/N)> ")){
        Menu.clearConsole();
        return;
      } 
     } else if (!hasError){
        Category tempCat = null;
        Inventory tempInv = null;
        Supplier tempSup = null;
        for (Category category: categories){
            if (category.getID().equals(tempCatID)){
                tempCat = category;
                break;
            }
        }
        
        for (Inventory inventoree: inventory){
            if (inventoree.getID().equals(tempInvID)){
                tempInv = inventoree;
                break;
            }
        }
        
        for (Supplier supplier: suppliers){
            if (supplier.getID().equals(tempSupID)){
                tempSup = supplier;
                break;
            }
        }
        
        for (int i = 0; i < products.length; i++) {
            if (products[i] == null || products[i].getID().equals("")) {
                products[i] = new Product(tempName, tempDesc, tempPrice, tempQty, tempCat, tempInv, tempSup);
                break;
            }
        }
        PRODUCT_INDEX++;
        saveProdData();
        boolean keyInAgain = true;
        while(keyInAgain){
            System.out.print("\nDo you want to add another? (Y/N)>");
         char goBack = sc.next().charAt(0);
         if(verifyYN(goBack)){
            keyInAgain = false;
            Menu.clearConsole();
          } else {
            return;
          }
         }

         }
    }
        
    }
 
    public static void deleteProd(){
        boolean notConfirmed = true; 
     while(notConfirmed){
        int i = getProdIndex();
        if(i == -1){
            return;
           }
        System.out.println("\nProduct ID choosen: " + products[i].getID());
     System.out.print("\nDelete? (Y/N)> ");
     char goBack = sc.next().charAt(0);
     if(verifyYN(goBack)){
     products[i].deleteProduct();
     PRODUCT_INDEX--;
     Product.setNumOfProd(Product.getNumOfProd() - 1);
     for(int a = i + 1; a < products.length; a++){
        products[a-1] = products[a];
     }
     saveProdData();
     //for confirmation
     for (int b = 0; b < products.length; b++){
        try {
            if (products[b].getID().equals("")){
                for(int a = b + 1; a < products.length; a++){
                    products[a-1] = products[a];
                    saveProdData();
                 }
                 break;
            }
        } catch (Exception e) {
        }
     }
      } else {
        break;
      }
      boolean deleteAnother = true;
      while(deleteAnother){
        System.out.print("\nDelete another? (Y/N)> ");
        char delAnother = sc.next().charAt(0);
        if(verifyYN(delAnother)){
            break;
        } else {
            return;
        }
      }
    
     }
    }

    public static int getProdIndex(){
        Menu.clearConsole();
        int i = -1;
        boolean notValidSearchType = true;
        OUTER:
        while (notValidSearchType) {
            System.out.print("\nSearch by name or ID? X to cancel. (name/id/X)> ");
            String tempSearch = sc.next();
            try {
                switch (tempSearch.toUpperCase()) {
                    case "NAME" -> {
                        i = searchProdName();
                        break OUTER;
                    }
                    case "ID" -> {
                        i = searchProdID();
                        break OUTER;
                    }
                    case "X" -> {
                        break OUTER;
                    }
                    default -> {
                    }
                }
            }catch (Exception e) {
            }
            System.out.println("\nWrong choice! Must be only 'name' or 'id'. X to quit.");
        }
        return i;

    }

    // ----------------------------------------- Category Functions -----------------------------------------

    public static void saveCatData(){

        try{
            Category[] newCat = new Category[CATEGORY_INDEX];
            for (int i = 0; i < categories.length; i++){
                try {
                    newCat[i] = categories[i];
                } catch (Exception e) {
                    break;
                }
            }
            categories = newCat;
        } catch(Exception e){
            System.out.print(e);
        }
            try (FileOutputStream fileOut = new FileOutputStream(
                "Application/src/main/java/Binaries/Category.bin");
                ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                    out.writeInt(CATEGORY_INDEX);
                out.writeObject(categories); 
                //System.out.println("Category data saved successfully.");
            } catch (IOException e) {
                System.err.println("Error saving category data: " + e.getMessage());
                Menu.pressEnterToContinue();
            }
    }

    public static void loadCatData(){
        try(FileInputStream fileIn = new FileInputStream("Application/src/main/java/Binaries/Category.bin");
        ObjectInputStream in = new ObjectInputStream(fileIn)
        )
        {   
            CATEGORY_INDEX = in.readInt();
            categories = (Category[]) in.readObject();
        } catch (IOException | ClassNotFoundException e){
            System.err.println("Error loading category data: " + e.getMessage());
            Menu.pressEnterToContinue();
        }
    }

    public static int searchCatID() {

        String tempID;
        int index = -1;
        boolean notValidID = true;

        while (notValidID) {
            System.out.print("\nEnter Category ID: ");
            tempID = sc.next().toUpperCase();
            sc.nextLine();
            for (int i = 0; i < categories.length; i++) {
                try {
                    if (categories[i].getID().toUpperCase().equals(tempID)) {
                        index = i;
                        notValidID = false;

                    } else if (tempID.toUpperCase().equals("X")) {
                        notValidID = false;
                    }

                } catch (Exception e) {
                }

            }
            if (notValidID) {
                System.out.println("\nWrong Category ID! Please re-enter! X to return.");
            }
        }
        return index;
    }

    public static int searchCatTitle() {
        String tempTitle;
        int index = -1;
        boolean notValidID = true;

        while (notValidID) {
            System.out.print("\nEnter Category Title: ");
            tempTitle = sc.nextLine();
            for (int i = 0; i < categories.length; i++) {

                try {

                    if (categories[i].getTitle().toUpperCase().equals(tempTitle.toUpperCase())) {

                        index = i;
                        notValidID = false;

                    } else if (tempTitle.toUpperCase().equals("X")) {
                        notValidID = false;
                    }

                } catch (Exception e) {

                }

            }
            if (notValidID) {
                System.out.println("\nWrong category title! Please re-enter! X to return.");
            }
        }
        return index;
    }

    public static void editCatDetails() {

        String tempID, tempTitle, getProp;
        int i = getCatIndex();
        boolean notValidProp = true;

        if (i == -1) {
            return;
        }
        
        while (notValidProp) {
            System.out.println("\nCategory ID: " + categories[i].getID());
        System.out.println("Category Title: " + categories[i].getTitle());
            System.out.print("\nEnter property to be changed (X to return): ");
            getProp = sc.next();
            sc.nextLine();
            boolean validDetails = true;
            switch (getProp.toUpperCase()) {
                case "ID" -> {
                    System.out.print("\nEnter new ID: ");
                    tempID = sc.next().toUpperCase();
                    if (!verifyCatID(tempID) && tempID.length() > 1 && tempID.length() <= 6 || categories[i].getID().equals(tempID)) {
                        // update the category ID in product
                        for (Product loopProd: products){
                            try {
                                if (loopProd.getCategory().getID().equals(categories[i].getID())){
                                    loopProd.getCategory().setID(tempID);
                                }
                            } catch (Exception e) {
                            }    
                        }
                        categories[i].setID(tempID);
                    } else {
                        System.out.println("\nID is already not available or length is not between 3 to 6 characters.");
                    }
                }
                case "TITLE" -> {
                    System.out.print("\nEnter new title: ");
                    sc.nextLine();
                    tempTitle = sc.nextLine();
                    if (tempTitle.length() > 1 || tempTitle.length() < 20) {
                        categories[i].setTitle(tempTitle);
                    } else {
                        System.out.println("\nTitle must be between length of 2 to 20 characters.");
                    }
                }
                case "X" -> {
                    System.out.println("\nReturning...\n");
                    notValidProp = false;
                }
                default -> {
                    System.out.println("\nWrong property entered! Try again.");
                    validDetails = false;
                }
            }
            //now update the product's category to align with changed category
            for (Product loopProd: products){
                try {
                    if (loopProd.getCategory().getID().equals(categories[i].getID())){
                        loopProd.setCategory(categories[i]);
                    }
                } catch (Exception e) {
                }    
            }
            saveCatData();
            saveProdData();
            while (notValidProp) {
                if (validDetails) {
                    System.out.print("\nChange another property? (Y/N): ");
                    } else {
                    System.out.print("\nTry again? (Y/N): ");
                }
                char repeat = sc.next().charAt(0);
                System.out.println();
                Menu.clearConsole();
                if (verifyYN(repeat)) {
                    break;
                } else {
                    notValidProp = false;
                    break;
                }
            }
        }

    }

    public static void listAllCat(){
        Menu.clearConsole();
        System.out.println("\n-----------------------------------------------");
        for (Category categorie : categories) {
            if (categorie != null) {
                System.out.println("\nCategory ID: " + categorie.getID());
                System.out.println("\nCategory Title: " + categorie.getTitle());
                System.out.println("\n-----------------------------------------------");
            }
        }
        System.out.println();
        Menu.pressEnterToContinue();

    }

    public static void listCatDetails(){
        Menu.clearConsole();
        int i = getCatIndex();
        
        if (i == -1) {
            return;
        }
        Menu.clearConsole();
        System.out.println("\n-----------------------------------------------");
        System.out.println("\nCategory ID: " + categories[i].getID());
        System.out.println("\nCategory Title: " + categories[i].getTitle());
        System.out.println("\n-----------------------------------------------");
        System.out.println();
        Menu.pressEnterToContinue();
    }

    public static boolean verifyCatID(String ID) {

        boolean idFound = false;

        for (Category category : categories) {
            try {
                if (category.getID().equals(ID.toUpperCase())) {
                    idFound = true;
                } else {
                }
            } catch (Exception e) {
            }
        }

        return idFound;
    }

    public static void addNewCat() {
        Menu.clearConsole();
        boolean notConfirmed = true;

        while (notConfirmed) {
            System.out.print("\nWould you like to add a new category to the existing list?(Y/N) >");
            try {
                char decision = sc.next().charAt(0);
                if (verifyYN(decision)) {
                    break;
                } else {
                    return;
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Must be only Y or N");
            }
        }
        boolean[] validInput = {true, true};
        Menu.clearConsole();
        boolean notInputAgain = true;
        while(notInputAgain){
            Menu.clearConsole();
            System.out.println("-------------------- Fill in the details below for the Category --------------------");
        System.out.println("\nID: ");
        System.out.println("Title: ");
        System.out.println("\n------------------------------------------------------------------------------------");
        System.out.print(ansi().cursor(3, 5));
        String tempID = sc.next().toUpperCase();
        sc.nextLine();
        System.out.print(ansi().cursor(4, 8));
        String tempTitle = sc.nextLine();
        System.out.print(ansi().cursor(7, 0));
        if (tempID.length() > 6 || tempID.length() < 1) {
            validInput[0] = false;
        }
        if (tempTitle.length() < 2 || tempTitle.length() > 20){
            validInput[1] = false;
        }
        boolean hasError = false;
    for (int i = 0; i < validInput.length; i++){
        if(!validInput[i]){
            if(!hasError){
                System.out.println("\nWrong input or format for: \n");
            }
            switch(i){
                case 0 -> System.out.println("ID (Must be between 1 to 6 characters)");
                case 1 -> System.out.println("Title (Must be between 2 to 20 characters)");
                
            }
            hasError = true;
        }
    }
    System.out.println("");
   
     if(hasError){
        System.out.print("Do you want to key in again? (Y/N)> ");
     char goBack = sc.next().charAt(0);
     if(!verifyYN(goBack, "Do you want to key in again? (Y/N)> ")){
        return;
      } 
     } else if (!hasError){
       
        for (int i = 0; i < categories.length; i++) {
            if (categories[i] == null || categories[i].getID().equals("")) {
                categories[i] = new Category(tempID, tempTitle);
                CATEGORY_INDEX++;
                saveCatData();
                break;
            }
        }
        
        boolean keyInAgain = true;
        while(keyInAgain){
            System.out.print("Do you want to add another? (Y/N)>");
            char goBack = sc.next().charAt(0);
            if(verifyYN(goBack)){
               keyInAgain = false;
               Menu.clearConsole();
             } else {
               return;
             }   
        }
     }

    }
    }
    
    public static void deleteCat(){
        Menu.clearConsole();
        boolean notConfirmed = true; 
        while(notConfirmed){
           int i = getCatIndex();
           if(i == -1){
            return;
           }
           for (Product product: products){
            try {
                if (categories[i].getID().equals(product.getCategory().getID())){
                    System.out.println("\nCategory " + categories[i].getTitle() + " has products in it. Can't proceed to delete.\n");
                    Menu.pressEnterToContinue();
                    return;
                }
            } catch (Exception e) {
            }
           }
           System.out.println("\nCategory ID choosen: " + categories[i].getID());
        System.out.print("\nDelete? (Y/N)> ");
        char goBack = sc.next().charAt(0);
        if(verifyYN(goBack)){
        categories[i].deleteCategory();
        CATEGORY_INDEX--;
        for(int a = i + 1; a < categories.length; a++){
            try {
                categories[a-1] = categories[a];
            }catch (Exception e){

            }
        }
        saveCatData();
        for (int b = 0; b < products.length; b++){
            try {
                if (categories[b].getID().equals("")){
                    for(int c = b + 1; c < categories.length; c++){
                        categories[c-1] = categories[c];
                        saveCatData();
                     }
                     break;
                }
            } catch (Exception e) {
            }
         }
         } else {
           break;
         }
         boolean deleteAnother = true;
         while(deleteAnother){
           System.out.print("\nDelete another? (Y/N)> ");
           char delAnother = sc.next().charAt(0);
           if(verifyYN(delAnother)){
               break;
           } else {
               return;
           }
         }
       
        }
    }

    public static int getCatIndex(){
        Menu.clearConsole();
        int i = -1;
        boolean notValidSearchType = true;
        OUTER:
        while (notValidSearchType) {
            System.out.print("\nSearch by title or ID? X to cancel. (title/id/X)> ");
            String tempSearch = sc.next();
            try {
                sc.nextLine();
                switch (tempSearch.toUpperCase()) {
                    case "TITLE" -> {
                        i = searchCatTitle();
                        break OUTER;
                    }
                    case "ID" -> {
                        i = searchCatID();
                        break OUTER;
                    }
                    case "X" -> {
                        break OUTER;
                    }
                    default -> {
                    }
                }
            }catch (Exception e) {
            }
            System.out.println("\nWrong choice! Must be only 'title' or 'id'. X to quit.");
        }
        return i;
    }
    // ----------------------------------------- Inventory Functions -----------------------------------------
    
    public static void saveInvData(){
        try (FileOutputStream fileOut = new FileOutputStream(
            "Application/src/main/java/Binaries/Inventory.bin");
            ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(inventory); 
            //System.out.println("Inventory data saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving category data: " + e.getMessage());
        }
}

@SuppressWarnings("unchecked")
public static void loadInvData(){
    try(FileInputStream fileIn = new FileInputStream("Application/src/main/java/Binaries/Inventory.bin");
    ObjectInputStream in = new ObjectInputStream(fileIn)
    )
    {   
        inventory = (List<Inventory>) in.readObject();
    } catch (IOException | ClassNotFoundException e){
        System.err.println("Error saving inventory data: " + e.getMessage());
        Menu.pressEnterToContinue();
    }
}
    
    public static boolean verifyInvID(String ID){
        boolean idFound = false;
        for (Inventory inventories: inventory){
            try {
                if(inventories.getID().equals(ID.toUpperCase())){
                    idFound = true;
                }
            }catch (Exception e){}
            
        }
        return idFound;
    }
    
   public static int searchInvID(){
    Menu.clearConsole();
    String tempID;
    int index = -1;
    boolean notValidID = true;

    while (notValidID) {
        System.out.print("\nEnter Inventory ID: ");
        tempID = sc.next().toUpperCase();

        for(Inventory inventories: inventory){
            try {

                if (inventories.getID().toUpperCase().equals(tempID)) {

                    index = inventory.indexOf(inventories);
                    notValidID = false;

                } else if (tempID.toUpperCase().equals("X")) {
                    notValidID = false;
                }

            } catch (Exception e) {

            }
        }
            

        
        if (notValidID) {
            System.out.println("\nWrong Inventory ID! Please re-enter! X to return.");
        }
    }

    return index;
   }
   
    public static void listAllInv(){
        Menu.clearConsole();
        System.out.println("\n-----------------------------------------------");
        for(Inventory inventories: inventory){
            System.out.println("\nInventory ID: " + inventories.getID());
            System.out.println("\nInventory Location: " + inventories.getLocation());
            System.out.println("\n-----------------------------------------------");
        }

        Menu.pressEnterToContinue();
    }

    public static void listInvDetails(){
        Menu.clearConsole();
        int i = searchInvID();
        if (i == -1){
        return;
        }
        System.out.println("\n-----------------------------------------------");
        System.out.println("\nInventory ID: " + inventory.get(i).getID());
        System.out.println("\nInventory Location: " + inventory.get(i).getLocation());
        System.out.println("\n-----------------------------------------------");
            Menu.pressEnterToContinue();
      
    }

    public static void editInvDetails(){

        String tempID, tempLocation, getProp;
        int i = searchInvID();
        boolean notValidProp = true;

        if (i == -1) {
            return;
        }
       
        while (notValidProp) {
            System.out.println("\nInventory ID: " + inventory.get(i).getID());
            System.out.println("Inventory Location: " + inventory.get(i).getLocation());
            System.out.print("\nEnter property to be changed (X to return): ");
            getProp = sc.next();
            boolean validDetails = true;
            switch (getProp.toUpperCase()) {
                case "ID" -> {
                    System.out.print("\nEnter new ID: ");
                    tempID = sc.next().toUpperCase();
                    if (!verifyInvID(tempID) && tempID.length() > 1 && tempID.length() <= 6 || inventory.get(i).getID().equals(tempID)) {
                        // set inv id in product to match
                        for (Product loopProd: products){
                            try {
                                if (loopProd.getInventory().getID().equals(inventory.get(i).getID())){
                                    loopProd.getInventory().setID(tempID);
                                }
                            } catch (Exception e) {
                            }    
                        }
                        inventory.get(i).setID(tempID);
                    } else {
                        System.out.println("\nID is already not available or length is not between 3 to 6 characters.");
                    }
            }
                case "LOCATION" -> {
                    System.out.print("\nEnter new location: ");
                    sc.nextLine();
                    tempLocation = sc.nextLine();
                    if (tempLocation.length() > 1 || tempLocation.length() < 20) {
                        inventory.get(i).setLocation(tempLocation);
                    } else {
                        System.out.println("\nLocation must be between length of 2 to 20 characters.");
                    }
            }
                case "X" -> {
                    System.out.println("\nReturning...\n");
                    notValidProp = false;
            }
                default -> {
                    System.out.println("\nWrong property entered! Try again.");
                    validDetails = false;
            }
            }
            //set inventory in product
            for (Product loopProd: products){
                try {
                    if (loopProd.getInventory().getID().equals(inventory.get(i).getID())){
                        loopProd.setInventory(inventory.get(i));
                    }
                } catch (Exception e) {
                }    
            }
            saveInvData();
            saveProdData();
                if (validDetails) {

                    System.out.print("\nChange another? (Y/N): ");
                    } else {
                    System.out.print("\nTry again? (Y/N): ");
                }
                char repeat = sc.next().charAt(0);
                System.out.println();
                Menu.clearConsole();
                if (!verifyYN(repeat)) {
                notValidProp = false;
                   }
            
        }

    }

    public static void addNewInv(){
        Menu.clearConsole();
        boolean notConfirmed = true;

        while (notConfirmed) {
            System.out.print("\nWould you like to add a new inventory to the existing list?(Y/N) >");
            try {
                char decision = sc.next().charAt(0);
                if (verifyYN(decision)) {
                    break;
                } else {
                    return;
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Must be only Y or N");
            }
        }
        boolean[] validInput = {true, true};
        Menu.clearConsole();
        boolean notInputAgain = true;
        while(notInputAgain){
            Menu.clearConsole();
            System.out.println("-------------------- Fill in the details below for the Inventory --------------------");
        System.out.println("\nID: ");
        System.out.println("Location: ");
        System.out.println("\n-------------------------------------------------------------------------------------");
        System.out.print(ansi().cursor(3, 5));
        String tempID = sc.next().toUpperCase();
        sc.nextLine();
        System.out.print(ansi().cursor(4, 11));
        String tempLocation = sc.nextLine();
        System.out.print(ansi().cursor(7, 0));
        if (tempID.length() > 6 || tempID.length() < 1) {
            validInput[0] = false;
        }
        if (tempLocation.length() < 2 || tempLocation.length() > 20){
            validInput[1] = false;
        }
        boolean hasError = false;
    for (int i = 0; i < validInput.length; i++){
        if(!validInput[i]){
            if(!hasError){
                System.out.println("\nWrong input or format for: \n");
            }
            switch(i){
                case 0 -> System.out.println("ID (Must be between 1 to 6 characters)");
                case 1 -> System.out.println("Location (Must be between 2 to 20 characters)");
                
            }
            hasError = true;
        }
    }
    System.out.println("");
   
     if(hasError){
        System.out.print("\nDo you want to key in again? (Y/N)> ");
     char goBack = sc.next().charAt(0);
     if(!verifyYN(goBack, "Do you want to key in again? (Y/N)> ")){
        return;
      }
     } else if (!hasError){
       
        inventory.add(new Inventory(tempID, tempLocation));
        saveInvData();
        boolean keyInAgain = true;
        while(keyInAgain){
            System.out.print("Do you want to add another? (Y/N)>");
            char goBack = sc.next().charAt(0);
            if(verifyYN(goBack)){
               keyInAgain = false;
               Menu.clearConsole();
             } else {
               return;
             }   
        }
     }

    }
    }
  
    public static void deleteInv(){
        boolean notConfirmed = true; 
        while(notConfirmed){
           int i = searchInvID();
           if(i == -1){
            return;
           }
           for (Product product: products){
            try {
                if (inventory.get(i).getID().equals(product.getInventory().getID())){
                    System.out.println("\nInventory " + inventory.get(i).getLocation() + " has products in it. Can't proceed to delete.\n");
                    Menu.pressEnterToContinue();
                    return;
                }
            } catch (Exception e) {
            }
           }
           System.out.println("\nInventory ID choosen: " + inventory.get(i).getID());
        System.out.print("\nDelete? (Y/N)> ");
        char goBack = sc.next().charAt(0);
        if(verifyYN(goBack)){
        inventory.remove(i);
        saveInvData();
         } else {
           break;
         }
         boolean deleteAnother = true;
         while(deleteAnother){
           System.out.print("\nDelete another? (Y/N)> ");
           char delAnother = sc.next().charAt(0);
           if(verifyYN(delAnother)){
               break;
           } else {
               return;
           }
         }
       
        }
    }

    // ----------------------------------------- Supplier Functions -----------------------------------------
 
	public static void supplierEdits(){
		Menu.clearConsole();
		boolean valid = false;
		
		do{
		System.out.print("What would you like to do?\n1. Show all supplier\n2. Add supplier\n3. Edit supplier\n4. Delete supplier\n5. Return to previous menu\n> ");
			int supplierChoice = sc.nextInt();
		
			switch(supplierChoice){
				case 1:
					Menu.clearConsole();
					viewSupplier();
					break;
				case 2:
					Menu.clearConsole();
					addSupplier();
					break;
				case 3:
					Menu.clearConsole();
					editSupplier();
					break;
				case 4:
					Menu.clearConsole();
					deleteSupplier();
					break;
				case 5:
					valid = true;	
				default:
            		Menu.clearConsole();
                	System.out.println("Invalid choice, please try again.");	
			}
		}while(!valid);	
	}  
    
    public static void staffSupplierEdits(){
		Menu.clearConsole();
		boolean valid = false;
		
		do{
		System.out.print("What would you like to do?\n1. Show all supplier\2. Return to previous menu\n> ");
			int supplierChoice = sc.nextInt();
		
			switch(supplierChoice){
				case 1:
					Menu.clearConsole();
					viewSupplier();
					break;
				case 2:
					valid = true;	
				default:
            		Menu.clearConsole();
                	System.out.println("Invalid choice, please try again.");	
			}
		}while(!valid);	
		
	}
	
	private static void viewSupplier(){
		
        boolean validChoice = false;

        do{
        System.out.println("Would you like to search for specific supplier (Y/N)? (press X to return)\n");
        System.out.print("> ");
        String choiceSearch = sc.nextLine();

        if(choiceSearch.equalsIgnoreCase("Y") || choiceSearch.equalsIgnoreCase("y")){
            searchSupplier();
            validChoice = true;
        }else if(choiceSearch.equalsIgnoreCase("N") || choiceSearch.equalsIgnoreCase("n")){
            Menu.clearConsole();
            displaySupplier();

            validChoice = true;

        }else if (choiceSearch.equalsIgnoreCase("X")) {
            validChoice = true;
        }
        else{
            Menu.clearConsole();
            System.out.println("Please enter a valid choice : ");    
        }
        }while(!validChoice);  

        Menu.pressEnterToContinue();
        Menu.clearConsole();
        System.out.println();

    }
      
    private static void searchSupplier() {
        
        boolean searchAgain = true;
        List<Supplier> searchedSuppliers = new ArrayList<>();  // List to store all searched suppliers
    
        while (searchAgain) {
            Menu.clearConsole();
            System.out.println("=============================================================");
            System.out.println("        Supplier Search Menu           ");
            System.out.println("=============================================================");
            System.out.println("Enter Supplier ID or Name to search (or press X to return): ");
            String search = sc.nextLine();



                if (search.equalsIgnoreCase("X")) {
                    return; 
                }

    
            boolean found = false;
    
            
            for (Supplier supplier : suppliers) {
                if (supplier.getID().equalsIgnoreCase(search) || supplier.getName().equalsIgnoreCase(search)) {
                    Menu.clearConsole();
                    System.out.println("=============================================================");
                    System.out.println("          Supplier Found!             ");
                    System.out.println("=============================================================");
                    System.out.printf("%-20s: %s%n", "Supplier ID", supplier.getID());
                    System.out.printf("%-20s: %s%n", "Name", supplier.getName());
                    System.out.printf("%-20s: %s%n", "Address", supplier.getAddress());
                    System.out.printf("%-20s: %s%n", "Email", supplier.getEmail());
                    System.out.println("=============================================================");
                    searchedSuppliers.add(supplier);  //add found supplier to the list
                    found = true;
                    break;
                }
            }
    
            if (!found) {
                Menu.clearConsole();
                System.out.println("No supplier found.");
            }
    
            String searchAgainChoice;
            do {
                System.out.println("Would you like to search again? (Y/N) or press X to return: ");
                searchAgainChoice = sc.nextLine();
    
                if (searchAgainChoice.equalsIgnoreCase("X")) {
                    return; 
                } else if (searchAgainChoice.equalsIgnoreCase("Y")) {
                    searchAgain = true;
                } else if (searchAgainChoice.equalsIgnoreCase("N")) {
                    searchAgain = false; //stop searching but don't immediately return to the menu
                } else {
                    Menu.clearConsole();
                    System.out.println("Invalid choice! Please enter Y, N, or X.");
                }
            } while (!searchAgainChoice.equalsIgnoreCase("Y") && !searchAgainChoice.equalsIgnoreCase("N") && !searchAgainChoice.equalsIgnoreCase("X"));
        }
        
        //list for unique suppliers (hold duplicates)
        

        for(Supplier supplier : searchedSuppliers){
            boolean exists = false;

            for (Supplier unique : uniqueSuppliers) {
                if (unique.getID().equals(supplier.getID())) {
                    exists = true; //found duplicate
                    break;
                }
            }
            if (!exists) {
                uniqueSuppliers.add(supplier);
            }
        }

        //After they choose "N", show all the suppliers they searched for during this session
        if (!searchedSuppliers.isEmpty()) {
            Menu.clearConsole();
            searchedSupplier();
            
            //Sort the list?
            boolean sort = false;
            do{
                System.out.println("Sort the searched supplier list? (Y/N)");
                String yesNo = sc.nextLine();

                if(yesNo.equalsIgnoreCase("Y")){
                    Menu.clearConsole();
                    boolean validOrder = false;
                    while(!validOrder){
                        
                        searchedSupplier();

                        System.out.println("Choose sorting order: (A)scending or (D)escending");
                        String orderChoice = sc.nextLine();
                        
                        switch (orderChoice) {
                            case "A" -> {
                                uniqueSuppliers.sort(Comparator.comparing(Supplier::getID));
                                //:: shorthand for -> supplier->supplier.getID()
                                System.out.println("Sorting in ascending order");
                                validOrder = true;
                            }
                            case "D" -> {
                                uniqueSuppliers.sort(Comparator.comparing(Supplier::getID).reversed());
                                //reverse is ascending terbalik
                                System.out.println("Sorting in descending order");
                                validOrder = true;
                            }
                            default -> {
                                System.out.println("Invalid choice, enter A or D");
                                validOrder = false;
                            }
                        }
                        Menu.clearConsole();
                    }
                    Menu.pressEnterToContinue();

                    Menu.clearConsole();
                    System.out.println("===================================================================================================================================================");
                    System.out.println("      Suppliers Searched History  ");
                    System.out.println("===================================================================================================================================================");
                    System.out.printf("%-20s | %-30s | %-40s | %-30s%n", "Supplier ID", "Name", "Address", "Email");
                    System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------");

                    
                    for (Supplier supplier : uniqueSuppliers) {
                        
                        System.out.printf("%-20s | %-30s | %-40s | %-30s%n", 
                        supplier.getID(), 
                        supplier.getName(), 
                        supplier.getAddress(), 
                        supplier.getEmail());
                        sort = true;
                    }

                    System.out.println("===================================================================================================================================================");
                    System.out.printf("\nSorted.....");

                }else if(yesNo.equalsIgnoreCase("N")){
                    
                    System.out.println("Returning to Menu");
                    sort = true;
                    
                }
                else{
                    Menu.clearConsole();
                    System.out.println("Invalid choice (Y/N), Enter again!");
                    sort = false;
                }
            }while(!sort);


        } else {
            System.out.println("No suppliers were searched for during this session.");
        }
    
        Menu.pressEnterToContinue();
    }
     
    private static void searchedSupplier(){
        System.out.println("===================================================================================================================================================");
            System.out.println("      Suppliers Searched History  ");
            System.out.println("===================================================================================================================================================");
            System.out.printf("%-20s | %-30s | %-40s | %-30s%n", "Supplier ID", "Name", "Address", "Email");
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------");

            for (Supplier supplier : uniqueSuppliers) {
                
                System.out.printf("%-20s | %-30s | %-40s | %-30s%n", 
                supplier.getID(), 
                supplier.getName(), 
                supplier.getAddress(), 
                supplier.getEmail());
            }

            System.out.println("===================================================================================================================================================");
    }
    
    private static void editSupplier() {
    	
        Supplier supplierToEdit = null;
    
        while (supplierToEdit == null) {
            System.out.print("Enter Supplier ID or Name to edit (X to back): ");
            sc.nextLine();
            String search = sc.nextLine();
    
            if (search.equalsIgnoreCase("X")) {
                return;
            }

            for (Supplier supplier : suppliers) {
                if (supplier.getID().equalsIgnoreCase(search) || supplier.getName().equalsIgnoreCase(search)) {
                    supplierToEdit = supplier; 
                    break;
                }
            }
    
            if (supplierToEdit == null) {
                Menu.clearConsole();
                System.out.println("No supplier found. Please try again.");
            }
        }

        
        Menu.clearConsole(); // Clear the console for a fresh look
        System.out.println("===============================================================");
        System.out.println("                    Supplier Found!                             ");
        System.out.println("===============================================================");
        System.out.printf("%-20s: %s%n", "Supplier ID", supplierToEdit.getID());
        System.out.printf("%-20s: %s%n", "Name", supplierToEdit.getName());
        System.out.printf("%-20s: %s%n", "Address", supplierToEdit.getAddress());
        System.out.printf("%-20s: %s%n", "Email", supplierToEdit.getEmail());
        System.out.println("===============================================================");
        Menu.pressEnterToContinue();

        String originalName = supplierToEdit.getName();
        String originalAddress = supplierToEdit.getAddress();
        String originalEmail = supplierToEdit.getEmail();

        boolean continueEditing = true;
        while (continueEditing) {
            int editChoice = Menu.whichToEdit();

            switch (editChoice) {
            case 1 -> {
                String newName;
                while (true) {
                    System.out.println("Enter new name (X to cancel): ");
                    newName = sc.nextLine();
                    if (newName.equalsIgnoreCase("X")) {
                        return;
                    }
                    
                    if (containsNumber(newName)) {
                        Menu.clearConsole();
                        System.out.println("Supplier name cannot contain a number. Please try again.");
                    } else {
                        supplierToEdit.setName(newName);
                        break;
                    }
                }   }
            case 2 -> {
                System.out.println("Enter new address (X to cancel): ");
                String newAddress = sc.nextLine();
                if (newAddress.equalsIgnoreCase("X")) {
                    return;
                }
                supplierToEdit.setAddress(newAddress);
                }
            case 3 -> {
                String newEmail;
                while (true) {
                    
                    System.out.println("Enter new email (X to cancel): ");
                    newEmail = sc.nextLine();
                    if (newEmail.equalsIgnoreCase("X")) {
                        return;
                    }

                    if (!validEmailFormat(newEmail)) {
                        Menu.clearConsole();
                        System.out.println("Invalid email format! Email must contain '@' and end with .com . Please try again.");
                    } else {
                        supplierToEdit.setEmail(newEmail);
                        break; // Exit the loop if valid email
                    }
                }
                }
            case 4 -> {
                System.out.println("Edit cancelled. Returning to menu.");
                return;
                }
            default -> {
                System.out.println("Invalid choice. Returning to staff menu!");
                return;
                }
        }

        Menu.pressEnterToContinue();

        

        while(true) {
            Menu.clearConsole();
            System.out.println("Do you want to continue editing this supplier's details? (Y/N)");
            String continueEditResponse = sc.nextLine();
            if (continueEditResponse.equalsIgnoreCase("Y")) {
                break; 
            } else if (continueEditResponse.equalsIgnoreCase("N")) {
                continueEditing = false;
                break; 
            } else {
                System.out.println("Invalid option! Please enter 'Y' or 'N'."); 
            }
        }
    }   
        Menu.clearConsole();
        System.out.println("Supplier details updated: " + supplierToEdit);
    
        boolean validInput = false;
    
        while (!validInput) {
            Menu.clearConsole();
            System.out.println("=== Supplier Details Updated ===========================");
            System.out.printf("%-20s: %s%n", "Supplier ID", supplierToEdit.getID());
            System.out.printf("%-20s: %s%n", "Name", supplierToEdit.getName());
            System.out.printf("%-20s: %s%n", "Address", supplierToEdit.getAddress());
            System.out.printf("%-20s: %s%n", "Email", supplierToEdit.getEmail());
            System.out.println("========================================================");
            System.out.println("Save changes? (Y/N) (X to menu): ");
            String saveChanges = sc.nextLine();
    
            if (saveChanges.equalsIgnoreCase("Y")) {
                for (Product loopProd: products){
                    try {
                        if (loopProd.getSupplier().getID().equals(supplierToEdit.getID())){
                            loopProd.setSupplier(supplierToEdit);
                        }
                    } catch (Exception e) {
                    }    
                }
                saveProdData();
                saveSupplierData();
                validInput = true;
            } else if (saveChanges.equalsIgnoreCase("N")) {
                supplierToEdit.setName(originalName);
                supplierToEdit.setAddress(originalAddress);
                supplierToEdit.setEmail(originalEmail);
                System.out.println("Changes discarded! Returning to menu.");
                validInput = true;
                Menu.pressEnterToContinue();
            } else if (saveChanges.equalsIgnoreCase("X")) {
                return;
            } else {
                System.out.println("Invalid option! Please try again.");
            }
        }
    
    }
    
    private static void addSupplier() {
        boolean addAgain; 
        
        do {
            Menu.clearConsole();
            System.out.println("==================================================================================================================================================");
            System.out.println("                                                 ADD A NEW SUPPLIER                                                                               ");
            System.out.println("==================================================================================================================================================");
            System.out.printf("\n\n");

            displaySupplier();
            Menu.pressEnterToContinue();

            boolean validID;
            String ID;
    
            System.out.println("Enter New Supplier Details:");
            System.out.println("---------------------------------------");
    
            //loop validate id
            do {
                
                System.out.print("Enter Supplier ID (Format: SUP001) (X to cancel): ");
                ID = sc.nextLine();
    
                if (ID.equalsIgnoreCase("X")) {
                    return; //exit
                }
    
                if (!ID.matches("(?i)^sup\\d{3}$")) {
                    Menu.clearConsole();
                    displaySupplier();
                    System.out.println("Invalid Supplier ID! It must start with 'SUP' followed by 3 digits.");
                    validID = false; //ID not valid
                } else if (supplierIdExist(ID.toUpperCase())) {
                    Menu.clearConsole();
                    displaySupplier();
                    System.out.println("Supplier ID already exists! Please enter a different ID.");
                    validID = false; //ID exists
                } else {
                    validID = true; //ID valid
                }
            } while (!validID);
    
            boolean validName;
            String name;
    
            //loop name
            do {
                
                System.out.print("Enter Supplier Name (X to cancel): ");
                name = sc.nextLine();
    
                if (name.equalsIgnoreCase("X")) {
                    return; 
                }
    
                if (containsNumber(name)) {
                    Menu.clearConsole();
                    displaySupplier();
                    System.out.println("Supplier Name cannot contain numbers. Please enter again.");
                    validName = false; //Name is not valid
                } else {
                    validName = true; //Name is valid
                }

                
            } while (!validName);
    
            
            System.out.print("Enter Supplier Address (X to cancel): ");
            String address = sc.nextLine();
            if (address.equalsIgnoreCase("X")) {
                return; 
            }
            
            boolean validEmail;
            String email;
            Menu.clearConsole();
            displaySupplier();
            //validate Email
            do {
                System.out.print("Enter Supplier Email (X to cancel): ");
                email = sc.nextLine();
    
                if (email.equalsIgnoreCase("X")) {
                    return; 
                }
    
                if (!validEmailFormat(email)) {
                    Menu.clearConsole();
                    displaySupplier();
                    System.out.println("Invalid email format! Email must contain '@' and ends with .com .");
                    validEmail = false;
                } else {
                    validEmail = true; 
                }
            } while (!validEmail);
    
            //create a new Supplier and add it to the list
            Supplier newSupplier = new Supplier(ID.toUpperCase(), name, address, email);

            

            Menu.clearConsole();
            System.out.println("Updated List of Suppliers:");
            System.out.println("---------------------------------------");
            System.out.printf("%-20s: %s%n", "Supplier ID", newSupplier.getID());
            System.out.printf("%-20s: %s%n", "Name", newSupplier.getName());
            System.out.printf("%-20s: %s%n", "Address", newSupplier.getAddress());
            System.out.printf("%-20s: %s%n", "Email", newSupplier.getEmail());
            System.out.println("---------------------------------------\n");
            
            
            boolean saveData = false; 
            while (!saveData){
                System.out.println("Save changes? (Y/N)");
                String saveChoice = sc.nextLine();
                Menu.clearConsole();

                if(saveChoice.equalsIgnoreCase("Y")){
                    suppliers.add(newSupplier);
                    suppliers.sort(Comparator.comparing(Supplier::getID));
                    saveSupplierData();
                    saveData = true;

                    System.out.println("\nUpdated List of Suppliers:");
                    displaySupplier();
                }
                else if(saveChoice.equalsIgnoreCase("N")){
                    System.out.println("Supplier save Cancelled");
                    saveData = true;
                }
                else{
                    System.out.println("Invalid choice, Enter again!");
                    saveData = false;
                }

            }
            

            do {
                
                System.out.print("Do you want to add another supplier? (Y/N or X to cancel): ");
                String choice = sc.nextLine();
    
                if (choice.equalsIgnoreCase("X")) {
                    return; 
                } else if (choice.equalsIgnoreCase("Y")) {
                    addAgain = true; 
                    break; 
                } else if (choice.equalsIgnoreCase("N")) {
                    addAgain = false; 
                    break;
                } else {
                    Menu.clearConsole();
                    displaySupplier();
                    System.out.println("Invalid input! Please enter 'Y' for Yes, 'N' for No, or 'X' to cancel.");
                }
                
            } while (true); 
    
        } while (addAgain);
        

        System.out.println("\nReturning to main menu...");
    }
    
    private static void saveSupplierData() {
        try (FileOutputStream fileOut = new FileOutputStream("Application/src/main/java/Binaries/supplierlist.bin");
        ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
        out.writeObject(suppliers); // Serialize the list of suppliers
        //System.out.println("Supplier data saved successfully.");
    } catch (IOException e) {
        System.err.println("Error saving supplier data: " + e.getMessage());
    }
    }   	
    
    private static void deleteSupplier() {
    	
        String continueDelete = "N";  //uninitialized error
    
        do {
            Menu.clearConsole();
            System.out.print("Enter Supplier ID or Name to delete (X to back): ");
            String search = sc.nextLine();
    
            if (search.equalsIgnoreCase("X")) {
                return;
            }
    
            Supplier supplierToDelete = null;
            for (Supplier supplier : suppliers) {
                if (supplier.getID().equalsIgnoreCase(search) || supplier.getName().equalsIgnoreCase(search)) {
                    supplierToDelete = supplier;
                    break;
                }
            }
    
            if (supplierToDelete == null) {
                Menu.clearConsole();
                System.out.println("No supplier found. Please try again.");
            } else {
                Menu.clearConsole();
                System.out.println("===============================================================");
                System.out.println("                    Supplier Found!                             ");
                System.out.println("===============================================================");
                System.out.printf("%-20s: %s%n", "Supplier ID", supplierToDelete.getID());
                System.out.printf("%-20s: %s%n", "Name", supplierToDelete.getName());
                System.out.printf("%-20s: %s%n", "Address", supplierToDelete.getAddress());
                System.out.printf("%-20s: %s%n", "Email", supplierToDelete.getEmail());
                System.out.println("===============================================================");
                System.out.println("Are you sure you want to delete this supplier? (Y/N): ");
                String confirmation = sc.nextLine();
    
                if (confirmation.equalsIgnoreCase("Y")) {
                    suppliers.remove(supplierToDelete);
                    saveSupplierData();
                    System.out.println("Supplier deleted successfully.");
                } else {
                    System.out.println("Deletion cancelled.");
                }
            }

            while (true) {
                System.out.println("Would you like to delete another supplier? (Y/N): ");
                continueDelete = sc.nextLine();
    
                if (continueDelete.equalsIgnoreCase("Y") || continueDelete.equalsIgnoreCase("N")) {
                    break; //Valid input then exit the loop
                } else {
                    Menu.clearConsole();
                    System.out.println("Invalid input! Please enter 'Y' for Yes or 'N' for No.");
                }
            }
            
            
        } while (continueDelete.equalsIgnoreCase("Y"));
    
        Menu.pressEnterToContinue();
    }
    
     private static void displaySupplier(){

        System.out.println("Supplier List");
        System.out.printf("+---------+-------------------------------+-------------------------------+-------------------------------+%n");
        System.out.printf("|  ID     |            Name               |           Address             |            Email              |%n");
        System.out.printf("+---------+-------------------------------+-------------------------------+-------------------------------+%n");

        for(Supplier supplier : suppliers){
            System.out.printf("| %-7s | %-29s | %-29s | %-29s |%n", 
            supplier.getID(), 
            supplier.getName(), 
            supplier.getAddress(), 
            supplier.getEmail());
        }
        System.out.printf("+---------+-------------------------------+-------------------------------+-------------------------------+%n");

     }
       
    private static boolean supplierIdExist(String ID) {
    
        for (Supplier supplier : suppliers) {
            if (supplier.getID().equalsIgnoreCase(ID)) {
                return true;
            }
        }
        return false;
    }
     
    private static boolean containsNumber(String name) {
        for (char i:name.toCharArray()) { //create an array to check 1by1
            if (Character.isDigit(i)) {
                return true;
            }
        }
       return false;
    }     
    
    private static boolean validEmailFormat(String email) {
        String emailRegex = "^[A-Za-z0-9._%+-]{1,100}@[A-Za-z0-9.-]{1,100}\\.[A-Za-z]{2,6}$";
        //accept 100 char then @ 100 char \\ends with .com
        return email.matches(emailRegex);
    }
    
    private static void loadSupplierData() {
    try (FileInputStream fileIn = new FileInputStream("Application/src/main/java/Binaries/supplierlist.bin");
         ObjectInputStream in = new ObjectInputStream(fileIn)) {
        @SuppressWarnings("unchecked")
        List<Supplier> loadedSuppliers = (List<Supplier>) in.readObject();
        suppliers.clear(); 
        suppliers.addAll(loadedSuppliers); // Add loaded suppliers
        System.out.println("Supplier data loaded successfully.");
    } catch (IOException | ClassNotFoundException e) {
        System.err.println("Error loading supplier data: " + e.getMessage());
    }
}
	
	public static boolean verifySupID(String ID){
        boolean idFound = false;
        for (Supplier supplier: suppliers){
            try {
                if(supplier.getID().equals(ID.toUpperCase())){
                    idFound = true;
                }
            }catch (Exception e){}
            
        }
        return idFound;
	}
    // ----------------------------------------- Product(Customer) Functions -----------------------------------------

    public static void viewAllProd() {
        
        int catIndexFirst, catIndexSecond;
        System.out.printf("\n                    %s                        \n",
                ansi().bold().fgBrightYellow().a("Format"));
        System.out.printf("\n%s|--------------------------------------------|%s", ansi().fgRed().a(""), ansi().reset());
        System.out.printf("\n%s|            |        |            |         |%s", ansi().fgRed().a(""), ansi().reset());
        System.out.printf("\n%s|%s Product ID %s|%s Name   %s|%s Quantity   %s|%s Price   %s|%s",
                ansi().fgRed().a(""), ansi().reset(),
                ansi().fgRed().a(""), ansi().reset(),
                ansi().fgRed().a(""), ansi().reset(),
                ansi().fgRed().a(""), ansi().reset(),
                ansi().fgRed().a(""), ansi().reset());
        System.out.printf("\n%s|            |        |            |         |%s", ansi().fgRed().a(""), ansi().reset());
        System.out.printf("\n%s|--------------------------------------------|%s\n", ansi().fgRed().a(""),
                ansi().reset());

                // Loop for product catalog here
        for (int catCount = 0; catCount < categories.length; catCount += 2) {
            String[] firstColProd = new String[products.length/2];
            String[] secondColProd = new String[products.length/2];
            try {
                     if (categories[catCount+1] != null) { // to display 2 categories table
                    catIndexFirst = catCount;
                    catIndexSecond = catCount + 1;
                    System.out.print(
                            "\n\n|+++++++++++++++++++++++++++++++++++++++++++|+++++++++++++++++++++++++++++++++++++++++++|");
                    System.out.print(
                            "\n|                                           |                                           |\n");
                    System.out.print("|");
                    // print the categories
                    for (int i = catCount; i < catCount + 2; i++) {
                        System.out.printf("%s      %-37s%s|", ansi().bold().fgBrightYellow().a(""),
                                categories[i].getTitle(), ansi().reset());
                    }

                    System.out.print(
                            "\n|                                           |                                           |\n");
                    System.out.print(
                            "|+++++++++++++++++++++++++++++++++++++++++++|+++++++++++++++++++++++++++++++++++++++++++|\n");
                    // print the products
                    for (@SuppressWarnings("unused") Product product : products) {
                        // check if first column product is available
                        try {
                            for (Product product1 : products) {
                                if (product1.getCategory().getID().equals(categories[catIndexFirst].getID())){
                                    boolean firstProdInc = false;
                                    for (String incProd : firstColProd) {
                                        try {
                                            if (product1.getID().equals(incProd) && product1.getID() != null) {
                                                firstProdInc = true; // first column product already filled
                                                break;
                                            }
                                        } catch (Exception e) {
                                        }
                                    }
        
                                    //-- do another loop here for second column--
                                    int checkLastSecProd = 0, totalNumSecProd = 0;
                                    for (Product checkSecProd : products){
                                        
                                        try {
                                            for (String checkProdCol : secondColProd){
                                                try {
                                                    if (checkSecProd.getID().equals(checkProdCol)){
                                                        checkLastSecProd++;
                                                    }
                                                } catch (Exception e) {
                                                }
                                               
                                            }
                                            if (checkSecProd.getCategory().getID().equals(categories[catIndexSecond].getID())) {
                                                totalNumSecProd++; //total number of products for the first category
                                            }
                                        } catch (Exception e) {
                                        }     
                                    }
    
                                    if (!firstProdInc) {// check if first column not filled
                                        if (checkLastSecProd == totalNumSecProd) {
                                            System.out.print(
                                                    "|      |                     |     |        |                                           |\n"); //empty for second column
                                        } else {
                                            System.out.print(
                                                    "|      |                     |     |        |      |                     |     |        |\n");
                                        }
                                        System.out.printf("| %-4s | %-19s | %-3d | %-6.2f |", product1.getID(),
                                                product1.getName(), product1.getStockQty(), product1.getPrice());
        
                                        for (int i = 0; i < firstColProd.length; i++) {
                                            try {
                                                if (firstColProd[i] == null) {
                                                    firstColProd[i] = product1.getID(); // add the first column product to the array and break
                                                    break;
                                                }
                                            } catch (Exception e) {
                                            }
                                        }
                                        if (checkLastSecProd == totalNumSecProd) {
                                            System.out.print("                                           |");
                                            System.out.print(
                                                    "\n|      |                     |     |        |                                           |");
                                            System.out.print(
                                                    "\n|-------------------------------------------|-------------------------------------------|\n");
                                        }
                                        break;
                                    }
                                }
                            } 
                        } catch (Exception e) {
                        }
                        try {
                            for (Product product2 : products) { // second column product loop
                                if (product2.getCategory().getID().equals(categories[catIndexSecond].getID())){
                                    boolean secondProdInc = false;
                                    for (String incProd : secondColProd) {
                                        try {
                                            if (product2.getID().equals(incProd) && product2.getID() != null) { // check if second product is already in the second column
                                                secondProdInc = true;
                                                break;
                                            }
                                        } catch (Exception e) {
                                        }
                                    }
                                    int checkLastFsProd = 0, totalNumFsProd = 0;
                                    for (Product checkProd : products) {
                                        try {
                                            for (String checkProdCol : firstColProd) {
                                                try {
                                                    if (checkProd.getID().equals(checkProdCol)) {
                                                        checkLastFsProd++; //find out how many products are in the first category already
                                                    }
                                                } catch (Exception e) {
                                                }
                                            }
                                            if (checkProd.getCategory().getID().equals(categories[catIndexFirst].getID())) {
                                                totalNumFsProd++; //total number of products for the first category
                                            }
                                        } catch (Exception e) {
                                        }
                                    }
        
                                    //-- do another loop here for second column--
                                    int checkLastSecProd = 0, totalNumSecProd = 0;
                                    for (Product checkSecProd : products){
                                        
                                        try {
                                            for (String checkProdCol : secondColProd){
                                                try {
                                                    if (checkSecProd.getID().equals(checkProdCol)){
                                                        checkLastSecProd++;
                                                    }
                                                } catch (Exception e) {
                                                }
                                               
                                            }
                                            if (checkSecProd.getCategory().getID().equals(categories[catIndexSecond].getID())) {
                                                totalNumSecProd++; //total number of products for the first category
                                            }
                                        } catch (Exception e) {
                                        }     
                                    }
    
                                    if (!secondProdInc) {
                                        if (checkLastFsProd == totalNumFsProd && totalNumFsProd < totalNumSecProd && checkLastSecProd + 1 == totalNumSecProd){ //check if second product is the last one

                                            System.out.print(
                                                "|                                           |      |                     |     |        |\n");
                                            System.out.printf("|                                           | %-3s | %-19s | %-3d | %-6.2f |", product2.getID(),
                                                product2.getName(), product2.getStockQty(), product2.getPrice());
                                        System.out.print(
                                                "\n|                                           |      |                     |     |        |");
                                        System.out.print(
                                                "\n|-------------------------------------------|-------------------------------------------|\n");

                                        } else {
                                            System.out.printf(" %-4s | %-19s | %-3d | %-6.2f |", product2.getID(),
                                            product2.getName(), product2.getStockQty(), product2.getPrice());
                                    System.out.print(
                                            "\n|      |                     |     |        |      |                     |     |        |");
                                    System.out.print(
                                            "\n|-------------------------------------------|-------------------------------------------|\n");
                                    
                                        }
                                        for (int i = 0; i < secondColProd.length; i++) {
                                            try {
                                                if (secondColProd[i] == null) {
                                                    secondColProd[i] = product2.getID(); //add second column product to array
                                                    break;
                                                }
                                            } catch (Exception e) {
                                            }
                                        }
                                        break;
                                    }
                                    
                                }
                            }
                        } catch (Exception e) {
                        }
                        
                    }

                } else if (categories[catCount] != null){ // display 1 category table only
                    System.out.print("\n\n|+++++++++++++++++++++++++++++++++++++++++++|");
                    System.out.print("\n|                                           |\n");
                    System.out.printf("|%s     %-37s%s |", ansi().bold().fgBrightYellow().a(""),
                            categories[catCount].getTitle(), ansi().reset());
                    System.out.print("\n|                                           |\n");
                    System.out.print("|+++++++++++++++++++++++++++++++++++++++++++|\n");
                    for (Product product1 : products) {
                        boolean secondProdInc = false;
                        for (String incProd : secondColProd) {
                            try {
                                if (product1.getID().equals(incProd) && product1.getID() != null) {
                                    secondProdInc = true;
                                    break;
                                }
                            } catch (Exception e) {
                            }
                        }
                        if (product1.getCategory().getID().equals(categories[catCount].getID()) && !secondProdInc) {
                            System.out.print("|      |                     |     |        |\n");
                            System.out.printf("| %-4s | %-19s | %-3d | %-6.2f |", product1.getID(), product1.getName(),
                                    product1.getStockQty(), product1.getPrice());
                            System.out.print("\n|      |                     |     |        |");
                            System.out.print("\n|-------------------------------------------|\n");
                            for (int i = 0; i < secondColProd.length; i++) {
                                try {
                                    if (secondColProd[i] == null) {
                                        secondColProd[i] = product1.getID();
                                        break;
                                    }
                                } catch (Exception e) {
                                }

                            }
                            break;
                        }
                    }

                }
            } catch (Exception e) {
            }
        }
            System.out.print("\nWould you like to buy one? (Y/N)> ");
            char goBack = sc.next().charAt(0);
            if (!verifyYN(goBack, "Would you like to buy one? (Y/N)> ")) {
                System.out.println();
                Menu.pressEnterToContinue();
                return;
            } 
            buy();
            while (true) { 
                System.out.print("\nBuy another one? (Y/N) > ");
            char buyAgain = sc.next().charAt(0);
            if (verifyYN(buyAgain, "Buy another one? (Y/N) >")) {
                buy();
            } else {
                break;
            }
            }
            
    }

    public static void buy() {
        boolean prodIDNotFound = true;
        boolean validProdID = false;
        boolean outofStock = false;
        while (prodIDNotFound) {
            System.out.print("\nChoose a product ID (X to quit) > ");
            String tempProdID = sc.next();
            if (tempProdID.toUpperCase().equals("X")){
                break;
            }
            for (Product product : products) {
                int quantity;
                boolean repeatQuantity = true;
                try {
                    if (product.getID().equals(tempProdID.toUpperCase()) && product.getStockQty() != 0) {
                        validProdID = true;
                        while (repeatQuantity) {
                            System.out.print("\nQuantity? (0 to quit)> ");
                            try {
                                quantity = Integer.parseInt(sc.next());
                                if (quantity == 0){
                                    break;
                                } else if (quantity > product.getStockQty()) {
                                    throw new ThrownException();
                                } else {
                                    boolean repeatItem = false;
                                    for (Item tempItem : activeCart.getItems()) {
                                        if (tempItem.getProduct().getID().equals(product.getID())) {
                                            tempItem.setQuantity(tempItem.getQuantity() + quantity);
                                            repeatItem = true;
                                        }
                                    }
                                    if (!repeatItem) {
                                        activeCart.addItem(new Item(quantity, product));
                                    }
                                    prodIDNotFound = false;
                                    break;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("\nWrong format entered! Must be integer only.");
                            } catch (ThrownException e) {
                                System.out.println("\nQuantity exceeds stock availability!");
                            }
                        }
                        break;

                    } else if (product.getID().equals(tempProdID.toUpperCase())){
                        outofStock = true;
                        break;
                    }
                } catch (Exception e) {
                    break; // maybe throw a thrownException??
                }

            }
            if (!validProdID) {
                if (!outofStock){
                    System.out.println("\nProduct ID not found! Try again.");
                } else {
                    System.out.println("\nProduct is out of stock! Try again.");
                }
                
            }
        }
    }

    public static void buy(int prodIndex) {
        boolean repeatQuantity = true;
        int quantity;
        while (repeatQuantity) {
            System.out.print("\nQuantity? (0 to quit) > ");
            try {
                quantity = Integer.parseInt(sc.next());
                if (quantity == 0){
                    break;
                }else if (quantity > products[prodIndex].getStockQty()) {
                    throw new ThrownException();
                } else {
                    boolean repeatItem = false;
                    for (Item tempItem : activeCart.getItems()) {
                        if (tempItem.getProduct().getID().equals(products[prodIndex].getID())) {
                            tempItem.setQuantity(tempItem.getQuantity() + quantity);
                            repeatItem = true;
                        }
                    }
                    if (!repeatItem) {
                        activeCart.addItem(new Item(quantity, products[prodIndex]));
                    }
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("\nWrong format entered! Must be integer only.");
            } catch (ThrownException e) {
                System.out.println("\nQuantity exceeds stock availability!");
            }
        }

    }

    // ----------------------------------------- Etc Functions -----------------------------------------

    public static void twoFactor(String email)throws IOException, GeneralSecurityException, MessagingException  {
		boolean faValid = false;
		
		System.out.println("\nAn OTP will be send to your email. Please enter in the OTP that was sent to you.\n");
        int i = 0;

		do{
            int userPass = 0;
			int facPass = (int)(Math.random() * 100000 + 10000);
            String otpPass = "OTP Code: " + facPass;
            //System.out.println(otpPass);
			sendEmail(email, "Inkmagination OTP Login", otpPass);
			System.out.print("\n> ");
            try {
                userPass = sc.nextInt();
            } catch (Exception e) {
            } 
			
			if(userPass == facPass){
				System.out.println("\nYour OTP is valid!\n");
				faValid = true;
                Menu.pressEnterToContinue();
			}
			else {
				System.out.println("\nThe OTP you entered was incorrect! A new OTP will be sent, please enter the new OTP.");
				i++;
                sc.nextLine();
			}
			
			if(i == 4){
				System.out.println("Too many attempts. Please try again later. You will be sent back to the main menu.\n");
				Menu.pressEnterToContinue();               
				Menu.clearConsole();
				return;
			}
		}while (!faValid);
	}

    public static boolean verifyYN(char text) {

        boolean verified = false;
        boolean repeatLoop = true;

        while (repeatLoop) {
            switch (Character.toUpperCase(text)) {
                case 'Y' -> {
                    verified = true;
                    repeatLoop = false;
                }
                case 'N' -> {
                    verified = false;
                    repeatLoop = false;
                }
                default -> {
                    System.out.println("\nInvalid input! Must be only Y or N.");
                    System.out.print("\nAnswer it again. (Y/N)> ");
                    text = sc.next().charAt(0);
                }

            }
        }

        return verified;
    }
    
    public static boolean verifyYN(char text, String question) {

        boolean verified = false;
        boolean repeatLoop = true;

        while (repeatLoop) {
            switch (Character.toUpperCase(text)) {
                case 'Y' -> {
                    verified = true;
                    repeatLoop = false;
                }
                case 'N' -> {
                    verified = false;
                    repeatLoop = false;
                }
                default -> {
                    System.out.println("\nInvalid input! Must be only Y or N.");
                    System.out.print("\n" + question);
                    text = sc.next().charAt(0);
                }

            }
        }

        return verified;
    }

}
// ----------------------------------------- Enums -----------------------------------------

enum ProdProperties {

    ID, NAME, DESCRIPTION, PRICE, QUANTITY, CATEGORY, INVENTORY, SUPPLIER;

}

// ----------------------------------------- Classes -----------------------------------------

class ThrownException extends Exception {

    public ThrownException() {
    }

}

// ----------------------------------------- Troubleshooting -----------------------------------------