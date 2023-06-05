
package phonebook;
import java.util.Scanner;
/**
 *
 * @author Manskers
 */
public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ConsoleIO io = new ConsoleIO();
        io.startPhoneBook();
        io.loadPhoneBook();
        io.startMenu();
        
        
    }
    
}
