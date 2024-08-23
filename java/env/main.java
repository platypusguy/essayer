import java.util.Properties;
public class main {
    public static void main(String[] args) {
       java.util.Properties prop = System.getProperties();
       prop.list(System.out);
    }
}
