import java.io.Console;
import java.io.PrintWriter;

public class main {
    public static void main( String[] args) {
        Console console = System.console(); 
        int i42 = 42;
        int i43 = 43;
        console.printf("42: %d", i42);
        console.flush();  
        console.printf(", 43: %d, and your favorite fruit? ", i43);
        String answer = console.readLine();    
        System.out.printf("I like %s too\n", answer);
        answer = console.readLine("Is it ok if I eat a %s now? ", answer);
        System.out.printf("Your answer: %s\n", answer);
        
        System.out.print("Tell me a secret: ");
        char secret[] = console.readPassword();
        String ss = new String(secret);
        System.out.printf("Your secret: %s\n", ss);
        
        System.out.print("Tell me a 2nd secret: ");
        secret = console.readPassword();
        ss = new String(secret);
        System.out.printf("Your 2nd secret: %s\n", ss);
        
        secret = console.readPassword("Roses are %s, violets are %s. Tell me a 3rd secret: ", "red", "blue");
        ss = new String(secret);
        System.out.printf("Your 3rd secret: %s\n", ss);
    }
}
