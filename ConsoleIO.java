
package phonebook;
import java.util.ArrayList;
import static java.util.Comparator.comparing;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author Manskers
 */
public class ConsoleIO {
    
    PhoneBook phoneBook;
    String message;
    
    public void startPhoneBook(){
        phoneBook = new PhoneBook("phoneBook");
            prt("******************************************************");
            prt("********** Welcome to Manskers PhoneBook *************");
            prt("******************************************************\n");
        
    }
    
    /*-----------------------------------------------------------------
    -------------------------------------------------------------------
    -----------------  START MENU  ------------------------------------
    -------------------------------------------------------------------
    -----------------------------------------------------------------*/
    
    public void startMenu(){
    
        
        printMenuHeader("***** PHONEBOOK MENU ******");
        //make a list for my menu
        List<String>startMenu = new ArrayList<>();
            startMenu.add("Add a Person By Field"); 
            startMenu.add("Add a Paste or enter CSV");
            startMenu.add("Search for a Person");
            startMenu.add("Modify Person");
            startMenu.add("Delete a Person");
            startMenu.add("Print PhoneBook");
            startMenu.add("Exit");
        
        //send list to menu maker to get response, get selection back
        int choice = getChoice(startMenu);
        
        //run code based on selection
        switch(choice){
            case 1 ->   addPersonByField();
            case 2 ->   addPersonPasteIn();
            case 3 ->   search();
            case 4 ->   modifyPerson();
            case 5 ->   deletePerson();
            case 6 ->   printBook();
            case 7 ->   exit();
            default ->  exit();
        }
    }
    
    /*-----------------------------------------------------------------
    -------------------------------------------------------------------
    -------------  ADD PERSON BY FIELD  -------------------------------
    -------------------------------------------------------------------
    -----------------------------------------------------------------*/
    
    public void addPersonByField(){
        boolean isIntHouseNum = false;
        boolean isIntZipCode = false;
        boolean isLongPhoneNum = false;
        Scanner scanner = new Scanner(System.in);
        
        //the below string is being made to follow the instructions given in the 
        //phonebook assignment. Each input is added to a CSV string called "person".
        String person = "";
    prt("Enter Last Name Below ");
        person+=scanner.nextLine()+", ";
    prt("Enter First Name Below");
        person+=scanner.nextLine()+", ";
    prt("Enter Middle Initial Below");
        person+=scanner.nextLine()+", ";
        
    //per instructions, requireing number for hous number
    while(isIntHouseNum !=true){
        prt("Enter House / Apt Number Below");
            try{
                person+=Integer.parseInt(scanner.nextLine())+", ";
                isIntHouseNum=true;
            }
            catch(Exception e){
                prt("****Invalid Entry*****\n");
            }
        }
    prt("Enter Street Name Below");
        person+=scanner.nextLine()+", ";
    prt("Enter City Below");
        person+=scanner.nextLine()+", ";
    prt("Enter State Below");
        person+=scanner.nextLine()+", ";
        
    //requiring a number for the zip code
    while(isIntZipCode !=true){
        prt("Enter Zip Code Below");
            try{
                person+=Integer.parseInt(scanner.nextLine())+", ";
                isIntZipCode=true;
            }
            catch(Exception ee){
                prt("****Invalid Entry*****\n");
            }
        }
    //requiring a number for the phone number
    while(isLongPhoneNum !=true){
        prt("Enter Phone Number:");

            try{
                 person+=Long.parseLong(scanner.nextLine())+", ";
                isLongPhoneNum=true;
            }
            catch(Exception eee){
                prt("****Invalid Entry*****\n");
            }
        }
    

    //this whole comma separated string thing is just to follow the directions, 
    //it was not needed in code. But here it is split up
    String[] values = person.split(", ");
    
    //prt(values[0],values[1],values[2],Integer.parseInt(values[3]),values[4],values[5],values[6],Integer.parseInt(values[7]),Long.parseLong(values[8]));
    Person createPerson =new Person (values[0],values[1],values[2],Integer.parseInt(values[3]),values[4],values[5],values[6],Integer.parseInt(values[7]),Long.parseLong(values[8]));
    phoneBook.addPersonToBook(createPerson);
    sort();
    repeat();
    }
    
    
    
    
    /*-----------------------------------------------------------------
    -------------------------------------------------------------------
    ----------  ADD PERSON PASTE OR TYPE CSV  -------------------------
    -------------------------------------------------------------------
    -----------------------------------------------------------------*/
    
    public void addPersonPasteIn(){
        prt("**PASTE YOUR INFORMATION BELOW**");
        System.out.println("Paste Format: FIRST MIDDLE LAST,Street Address, City, State, Zip, Phone Number");
        Scanner scanner = new Scanner(System.in);
            String fullName;
            String firstName;
            String lastName;
            String middleName;
            String remaining;
            String person;
            String input = scanner.nextLine();
        
        //dealing with pasted in full names when the project demands first, middle, and last name parameters
        int firstComma = input.indexOf(",");
        
        //extracting the full name from the pasted string
        fullName = input.substring(0,firstComma);
        firstName = fullName.substring(0,fullName.indexOf(" "))+", ";
        lastName = fullName.substring(fullName.lastIndexOf(" "),fullName.length())+", ";
        middleName = fullName.substring(fullName.indexOf(" "),fullName.lastIndexOf(" "))+", ";
        
        //grabbing the remaining data in the input string
        remaining = input.substring(firstComma+2,input.length());
        remaining = remaining.replaceFirst(" ",", ");
            person = lastName.trim();
            person += firstName;
            person += middleName;
            person += remaining;
        //removing spaces that cause problems in the parse
        person =person.replace(", ",",");

    String[] values = person.split(",");
    try {
    Person createPerson =new Person (values[0],values[1],values[2],Integer.parseInt(values[3]),values[4],values[5],values[6],Integer.parseInt(values[7]),Long.parseLong(values[8]));
    phoneBook.addPersonToBook(createPerson);
    }catch(Exception e){
    	prt("\n>>>>>>>>>>>>>>> SORRY <<<<<<<<<<<<<<<<<<<");
    	prt("Your entry doesnt meet the data standards");
    	prt(">>>>>>>>>>> NOTHING ADDED <<<<<<<<<<<<<<<\n");
    }
    sort();
    repeat();
    }
    
   
    /*-----------------------------------------------------------------
    -------------------------------------------------------------------
    ---------------------  SEARCH  ------------------------------------
    -------------------------------------------------------------------
    -----------------------------------------------------------------*/
    
    public void search(){
        
        
         //prompt to repeeat
        printMenuHeader("\nPlease Tell me how you want to search?");
            List <String> searchType = new ArrayList<>();
                searchType.add("Last Name");
                searchType.add("First Name");
                searchType.add("Phone Number");
                searchType.add("Any Match");
                searchType.add("Exit");
        int choice = getChoice(searchType);
            
            //Switch statement that runs code based on choice
            switch(choice){
                case 1 ->   searchLast();
                case 2 ->   searchFirst();
                case 3 ->   searchNumber(); 
                case 4 ->   searchAll();
                case 5 ->   exit(); 
                default ->  exit();     
            }                
    repeat();
    }
    
    /*-----------------------------------------------------------------
    -------------------------------------------------------------------
    ---------------------  SEARCH LAST  -------------------------------
    -------------------------------------------------------------------
    -----------------------------------------------------------------*/
    
    public int searchLast(){
    
        prt("Please enter the Last Name you want to search for");
        int recordsFound=0;
        int index = 0;
        
        Scanner scanner = new Scanner(System.in);
        String search = scanner.nextLine();
    
        prt("\n*************************************************");
        prt("***********  Search Term: "+search+"  ****************");
        prt("*************************************************");

        
        prt("\n*************************************************");
        prt("************* CALCULATING RESULTS ****************");
        prt("**************************************************\n");
    
    for (Person p: phoneBook.getPeople()){
    index++;
    if(p.getLastName().toUpperCase().equals(search.toUpperCase())){
        prt("--->("+index+")<--- RECORD NUMBER\n"+p);recordsFound++;
    }
    }
    return recordsFound;
    }
    
    /*-----------------------------------------------------------------
    -------------------------------------------------------------------
    ---------------------  SEARCH FIRST  -------------------------------
    -------------------------------------------------------------------
    -----------------------------------------------------------------*/
    
    public int searchFirst(){
        
        prt("Please enter the First Name you want to search for");
        int recordsFound = 0;
        int index = 0;
        Scanner scanner = new Scanner(System.in);
        String search = scanner.nextLine();
    
        prt("\n*************************************************");
        prt("***************  Search: "+search+"  ******************");
        prt("*************************************************");

        
        prt("\n*************************************************");
        prt("************* CALCULATING RESULTS ****************");
        prt("**************************************************\n");
    
    for (Person p: phoneBook.getPeople()){
        index++;
    if(p.getFirstName().toUpperCase().equals(search.toUpperCase())){
        prt("--->("+index+")<--- RECORD NUMBER\n"+p);recordsFound++;
    }
    }
    return recordsFound;
    }
    
    /*-----------------------------------------------------------------
    -------------------------------------------------------------------
    ---------------------  SEARCH NUMBER  -----------------------------
    -------------------------------------------------------------------
    -----------------------------------------------------------------*/
    
    public int searchNumber(){
        
        prt("Please enter the Phone Number you want to search for");
        boolean isLong =false;
        Scanner scanner = new Scanner(System.in);
        long search=0l;
        int index = 0; //index for record selection
        int recordsFound = 0;
        
        
        while(isLong!=true){
            try{
                search = Long.parseLong(scanner.nextLine());
                isLong=true;
            }catch(Exception e){
            prt("You have entered an invalid entry");
            }
        }

        prt("\n*************************************************");
        prt("************** Search: "+search+"  *******************");
        prt("*************************************************");

        
        prt("\n*************************************************");
        prt("************* CALCULATING RESULTS ****************");
        prt("**************************************************\n");

    
    for (Person p: phoneBook.getPeople()){
        index++;
        if(p.getPhoneNumber()==search){
            prt("--->("+index+")<--- RECORD NUMBER\n"+p);recordsFound++;
    }
    }
       return recordsFound; 
    }
    
    
    
    /*-----------------------------------------------------------------
    -------------------------------------------------------------------
    ---------------------  SEARCH ALL  --------------------------------
    -------------------------------------------------------------------
    -----------------------------------------------------------------*/
    
    public int searchAll(){
        Scanner scanner = new Scanner(System.in);
        String searchTerm;
        int index =0; //For record selection
        int recordsFound = 0;
        
        printMenuHeader("Enter your Search Criteria");
        
        searchTerm = scanner.nextLine();//Gets a search term from the user for input
            prt("\n*************************************************");
            prt("***********  Search Term: "+searchTerm+"  ****************");
            prt("*************************************************");
        

        
            prt("\n*************************************************");
            prt("************* CALCULATING RESULTS ****************");
            prt("**************************************************\n");
        
   
            for(Person p: phoneBook.getPeople()){ //Loops through the people added to the phonebook
                index++;
                if(p.toString().toUpperCase().contains(searchTerm.toUpperCase())){
                    //Prints the matches
                    prt("--->("+index+")<--- RECORD NUMBER\n"+p);recordsFound++;
 
                }
        }
      return recordsFound;
    }
    
    /*-----------------------------------------------------------------
    -------------------------------------------------------------------
    ---------------------  MODIFY PERSON  -----------------------------
    -------------------------------------------------------------------
    -----------------------------------------------------------------*/
    
    public void modifyPerson(){   
        boolean isNumber = false;
        int indexSelection=0;
        int recordsFound=0;
        
        Scanner scanner = new Scanner(System.in);
        
            prt("*********************************************");
            prt("Lets Search for the person you want to Modify");
            prt("*********************************************\n");
        
                printMenuHeader("\nPlease Tell me how you want to search?");
                List <String> searchType = new ArrayList<>();
                    searchType.add("Last Name");
                    searchType.add("First Name");
                    searchType.add("Phone Number");
                    searchType.add("Any Match");
                    searchType.add("Exit");
                int choice = getChoice(searchType);
            
                //Switch statement that runs code based on choice
                switch(choice){
                    case 1 ->   recordsFound = searchLast();
                    case 2 ->   recordsFound = searchFirst();
                    case 3 ->   recordsFound = searchNumber(); 
                    case 4 ->   recordsFound = searchAll();
                    case 5 ->   exit(); 
                    default ->  exit();     
                    }                
        if(recordsFound>0) { 
            prt("*********************************************");
            prt("ENTER THE INDEX NUMBER FOR THE RECORD TO CHANGE");
            prt("*********************************************");
            
        while(isNumber!=true){
            try{
                indexSelection=Integer.parseInt(scanner.nextLine());
                if(indexSelection<= phoneBook.getPeople().size() && indexSelection>0){
                    isNumber=true;}else{prt("Enter a valid index please");}
            }catch(Exception e){
                prt("You have made an invalid entry");
                prt("\nEnter a correct index number");
            }
        }
        indexSelection--;
        System.out.println("*********************************************");
        System.out.println(phoneBook.getPeople().get(indexSelection));
        System.out.println("*********************************************\n");
        printMenuHeader("\nWhat part of the record do you want to change");
                List <String> changeType = new ArrayList<>();
                    changeType.add("Last Name");
                    changeType.add("First Name");
                    changeType.add("Phone Number");
                    changeType.add("House / Appt Number");
                    changeType.add("Street");
                    changeType.add("City");
                    changeType.add("State");
                    changeType.add("Zip Code");
                    changeType.add("Exit");
                    int modChoice = getChoice(changeType);
            
                //Switch statement that runs code based on choice
                switch(modChoice){
                    case 1 ->   changeLast(indexSelection);
                    case 2 ->   changeFirst(indexSelection);
                    case 3 ->   changeNumber(indexSelection); 
                    case 4 ->   changeAddress(indexSelection,4);
                    case 5 ->   changeAddress(indexSelection,5); 
                    case 6 ->   changeAddress(indexSelection,6);
                    case 7 ->   changeAddress(indexSelection,7); 
                    case 8 ->   changeAddress(indexSelection,8);
                    case 9 ->   exit(); 
                    default ->  exit();     
                }
                prt("\n\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                prt(">>>>> Your Requested Modification Has Been Made <<<<<");
                prt("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n");
                sort();
                repeat();
        }else {
        prt("\n\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        prt(">>>>>>>> No Results Found, Nothing to Modify <<<<<<<<");
        prt("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n");
        repeat();}
    }
    
    /*-----------------------------------------------------------------
    -------------------------------------------------------------------
    ---------------------  DELTE PERSON  -----------------------------
    -------------------------------------------------------------------
    -----------------------------------------------------------------*/
    
    public void deletePerson(){
        
        boolean isNumber = false;
        int indexSelection=0;
        int searchTypeValue=0;
        int recordsFound = 0; //used to return a records found count. 0 records = no delete prompt
        
        Scanner scanner = new Scanner(System.in);
        
            prt("*********************************************");
            prt("Lets Search for the person you want to delete");
            prt("*********************************************\n");
        
                printMenuHeader("\nPlease Tell me how you want to search?");
                List <String> searchType = new ArrayList<>();
                    searchType.add("Last Name");
                    searchType.add("First Name");
                    searchType.add("Phone Number");
                    searchType.add("Any Match");
                    searchType.add("Exit");
                searchTypeValue = getChoice(searchType);
            
                //Switch statement that runs code based on choice
                switch(searchTypeValue){
                    case 1 ->   recordsFound = searchLast();
                    case 2 ->   recordsFound = searchFirst();
                    case 3 ->   recordsFound = searchNumber(); 
                    case 4 ->  	recordsFound = searchAll();
                    case 5 ->   exit(); 
                    default ->  exit();     
                    }                
     if(recordsFound>0) {  
            prt("\n***********************************************");
            prt("ENTER THE INDEX NUMBER FOR THE RECORD TO DELETE");
            prt("***********************************************");
        
        while(isNumber!=true){
            try{
                indexSelection=Integer.parseInt(scanner.nextLine());
                if(indexSelection<= phoneBook.getPeople().size() && indexSelection>0){
                    phoneBook.getPeople().remove(indexSelection-1);
                         	isNumber=true;

                    }else{prt("Please input a correct Index Number");}
            }catch(Exception e){
                prt("You have made an invalid entry");
                prt("\nEnter a correct index number");
            }
            }
      	prt("\n\n<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        prt(">>>>>The requested delete option has been completed<<<<<");
    	prt(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n");
        sort();
        repeat();

        }else {
         	prt("\n\n<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        	prt(">>> Your search returned no results, nothing to delete <<<<");
         	prt(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n");
        	repeat();
        	};
    }
    
    /*-----------------------------------------------------------------
    -------------------------------------------------------------------
    ----------------------  PRINT BOOK  -------------------------------
    -------------------------------------------------------------------
    -----------------------------------------------------------------*/
    
    public void printBook(){
        //prints my entire phone book
        for(Person p: phoneBook.getPeople()){
        System.out.println(p);
        }
    
    repeat();
    }
    /*-----------------------------------------------------------------
    -------------------------------------------------------------------
    ----------------------  CHANGE LAST NAME  -------------------------
    -------------------------------------------------------------------
    -----------------------------------------------------------------*/
    
    public void changeLast(int index){
        Scanner scanner = new Scanner(System.in);
            System.out.println("What would you like to change ***"+phoneBook.getPeople().get(index).getLastName()+"*** to?");
                prt("Please enter the new value below");
                    phoneBook.getPeople().get(index).setLastName(scanner.nextLine());
                    phoneBook.getPeople().get(index).setFullName();
        sort();
    }
    
    /*-----------------------------------------------------------------
    -------------------------------------------------------------------
    ----------------------  CHANGE FIRST NAME  -------------------------
    -------------------------------------------------------------------
    -----------------------------------------------------------------*/
    
    public void changeFirst(int index){
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("What would you like to change ***"+phoneBook.getPeople().get(index).getFirstName()+"*** to?");
        prt("Please enter the new value below");
        phoneBook.getPeople().get(index).setFirstName(scanner.nextLine());
                phoneBook.getPeople().get(index).setFullName();
        sort();
    }
    
    /*-----------------------------------------------------------------
    -------------------------------------------------------------------
    ----------------------  CHANGE PHONE NUMBER  -------------------------
    -------------------------------------------------------------------
    -----------------------------------------------------------------*/
    public void changeNumber(int index){
        boolean isLong = false;
        long newNum=0l;
        Scanner scanner = new Scanner(System.in);
        System.out.println("What would you like to change ***"+phoneBook.getPeople().get(index).getPhoneNumber()+"*** to?");
        prt("Please enter the new value below");
        
          while(isLong!=true){ 
              try{
                  newNum=Long.parseLong(scanner.nextLine());
                  isLong=true;
              }catch(Exception e){
                  prt("You have made an invalid entry. Enter a Number Please");
              }
          }
        phoneBook.getPeople().get(index).setPhoneNumber(newNum);
    }
    /*-----------------------------------------------------------------
    -------------------------------------------------------------------
    ----------------------  CHANGE ADDRESS  -------------------------
    -------------------------------------------------------------------
    -----------------------------------------------------------------*/
    public void changeAddress(int index, int modSelection){
        boolean isInt = false;
        boolean isInt2 = false;
        Scanner scanner = new Scanner(System.in);
        switch(modSelection){
            case 4 -> prt("What woud you like to change "+phoneBook.getPeople().get(index).address.getHouseNumber()+" to?");    
            case 5 -> prt("What woud you like to change "+phoneBook.getPeople().get(index).address.getStreet()+" to?");
            case 6 -> prt("What woud you like to change "+phoneBook.getPeople().get(index).address.getCity()+" to?");
            case 7 -> prt("What woud you like to change "+phoneBook.getPeople().get(index).address.getState()+" to?");
            case 8 -> prt("What woud you like to change "+phoneBook.getPeople().get(index).address.getZip()+" to?");
            default-> exit();
        }
        
            if(modSelection==4){
                prt("Please Enter the New House / Apartment Number");
                while(isInt!=true){
                    try{
                        phoneBook.getPeople().get(index).address.setHouseNumber(Integer.parseInt(scanner.nextLine()));
                        isInt=true;
                        }catch(Exception e){
                            prt("Invalid Entry, Please Try Again!");
                        }
                    }
                }
                else if(modSelection==5){
                    prt("Please Enter the new Street Information");
                        phoneBook.getPeople().get(index).address.setStreet(scanner.nextLine());
                    }
                else if(modSelection==6){
                    prt("Please Enter the new City Information");
                        phoneBook.getPeople().get(index).address.setCity(scanner.nextLine());
                    }
                else if(modSelection==7){
                    prt("Please Enter the new State Information");
                        phoneBook.getPeople().get(index).address.setState(scanner.nextLine());
                    }
                else if(modSelection==8){
                    while(isInt2!=true){
                    try{
                        phoneBook.getPeople().get(index).address.setZip(Integer.parseInt(scanner.nextLine()));
                        isInt2=true;
                        }catch(Exception e){
                            prt("Invalid Entry, Please Try Again!");
                        }
                    }
                }
                else{prt("Something Went Wrong, we are out of address mod range");}
 
        
        
        prt("Your Address Modifcation has been completede");
    }
    
    /*-----------------------------------------------------------------
    -------------------------------------------------------------------
    ----------------------  EXIT EXIT  -------------------------------
    -------------------------------------------------------------------
    -----------------------------------------------------------------*/
    
    public void exit(){
        prt("\n//////////////////////////////////////////");
        prt("Thank you for using the Mansker PhoneBook");
        prt("//////////////////////////////////////////");
    }
    
    /*-----------------------------------------------------------------
    -------------------------------------------------------------------
    ------------------------  REPEAT  ---------------------------------
    -------------------------------------------------------------------
    -----------------------------------------------------------------*/
    
    
    public void repeat(){
        //prompt to repeeat
        printMenuHeader("\nWould you like to continue using the Phone Book?");
            List <String> repeat = new ArrayList<>();
                repeat.add("Yes");
                repeat.add("No");
        int choice = getChoice(repeat);
            
            //Switch statement that runs code based on choice
            switch(choice){
                case 1 ->   startMenu();
                case 2 ->   exit();     
                default ->  exit();     
            }                
    }
    
    
    /*-----------------------------------------------------------------
    -------------------------------------------------------------------
    --------------------  PRINT MESSAGE  ----------------------------
    -------------------------------------------------------------------
    -----------------------------------------------------------------*/
    
    public void prt(String message) {
        System.out.println(message);
    }
    
    /*-----------------------------------------------------------------
    -------------------------------------------------------------------
    --------------------  PRINT MENU HEADER  --------------------------
    -------------------------------------------------------------------
    -----------------------------------------------------------------*/
    
    public void printMenuHeader(String message) {
        this.message = message;
        prt("-----------------------------");
        prt(message);
        prt("-----------------------------");
    }
    
    
    /*-----------------------------------------------------------------
    -------------------------------------------------------------------
    --------------  DISPLAY MENU GET CHOICE  --------------------------
    -------------------------------------------------------------------
    -----------------------------------------------------------------*/
    

    public int getChoice(List<String> menu) {
    	//this is the int for the selection made
        int selection = 0;
        
        //for loop to print each element in the menu array and an index number
        for (int i = 0; i < menu.size(); i++) {
            prt("    ("+(i + 1) + ") " + menu.get(i));//
        }
        //prints a message prompting a user to make an entry
        prt("--------------------------------");
        prt("********* Make an entry *********");
        prt("--------------------------------");
        
        //sends the menu to getValue for handling. if the choices need to be
        //printed for the user again, getValue holds the menu to do so.
        selection = getValue(menu);
        if(selection == 0) {
        	selection = getValue(menu);
        }
        
        //this is where the selection value is sent back to the 
        //method that called it
        return selection;
    }
    
    /*-----------------------------------------------------------------
    -------------------------------------------------------------------
    ------------------  GET VALUE FROM USER  --------------------------
    -------------------------------------------------------------------
    -----------------------------------------------------------------*/
    
    public int getValue(List<String> menu){
        boolean isInRange = false;
        int size = menu.size();
        int selection = 0;
        Scanner scanner = new Scanner(System.in);
    
        //while(isInRange!=true){
        try{
            selection = Integer.parseInt(scanner.nextLine());
            if(selection>0 && selection<=size){
            	isInRange=true;
                } else{
                    prt("The number you entered is out of range(GetValueErrorTry)");
                    	selection=0;//sends zero back so we cant get past the if statement in the getChoice menu
                    	printMenuHeader(message);  //prints the last menu header save (for current menu)
                    		getChoice(menu);  //sends the menu list back to be printed again
                }
  
        }catch(Exception e){
            prt("***You have made an Ivalid Entry(getValueErrorCatch)*****\n");
            	selection=0;//sends zero back so we cant get past the if statement in the getChoice menu
            	printMenuHeader(message);  //prints the last menu header save (for current menu)
            		getChoice(menu);  //sends the menu list back to be printed again
         
            }
        //}
        
        return selection;
    }
   
    /*-----------------------------------------------------------------
    -------------------------------------------------------------------
    -----------------------  LOAD PHONE BOOOK  ------------------------
    -------------------------------------------------------------------
    -----------------------------------------------------------------*/
    
        public void loadPhoneBook(){
        //adding some People into the Phone Book
        //Person(String lastName, String firstName, String middleInitial, int houseNumber, String street, String city, String state, int zip, long phoneNumber)
        Person person1 = new Person ("Doe","John"," ",114,"Market St", "St Louis", "MO",63403,6366435698l);
        Person person2 = new Person("Doe","Johne","E",324,"Main St","St Charles","MO",63303, 8475390126l);
        Person person3 = new Person("Doe","John","Michael West",574,"Pole ave","St. Peters","MO",63333,5628592375l);
        Person person4 = new Person("Cena","Roger","John",2100,"Cant See Me Ave.", "St Louis", "MO",63403,6366435698l);
        Person person5 = new Person("Goldberg","William","E",2200,"Spear Time","Springfield","MO",63303, 8475390126l);
        Person person6 = new Person("Lesner","Brock","M",2300,"UFC Champ Ave","Lesterville","MO",63333,5628592375l);
        Person person7 = new Person("Tarsus","Saul","Warrior",2200,"Damascus Street","Tarsus","MO",63303, 8475390126l);
        Person person8 = new Person("Paul","Apostle","Z",2300,"Ephisis Rd","Tarsus","MO",63333,5628592375l);
             
        
           phoneBook.addPersonToBook(person1);           
           phoneBook.addPersonToBook(person2);
           phoneBook.addPersonToBook(person3);
           phoneBook.addPersonToBook(person4);           
           phoneBook.addPersonToBook(person5);
           phoneBook.addPersonToBook(person6);
           phoneBook.addPersonToBook(person7);
           phoneBook.addPersonToBook(person8);
           
        sort();
    }
 public void sort(){
 phoneBook.getPeople().sort(comparing(Person::getLastName).thenComparing(Person::getFirstName));//sorts by last name then first name
 }

}