import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class main {

    public static void main(String[] args) throws FileNotFoundException, IOException, IllegalAccessException {
        File file = new File("./data.txt");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        while (line != null) {
            System.out.println(line);
            line = br.readLine();
        }
        String name = file.getName();
        System.out.printf("File name: %s\n", name);
        String parent = file.getParent();
        System.out.printf("File parent: %s\n", parent);
        String canonical = file.getCanonicalPath();
        System.out.printf("File canonical: %s\n", canonical);
        dumpObject(file, false);
    }

    public static void dumpObject(Object obj, boolean allFields) throws IllegalAccessException {
        StringBuffer buffer = new StringBuffer();
        Field[] fields;
        Class classobj = obj.getClass();
        System.out.printf("Class name: %s\n", classobj.getName());
        if (allFields)
            fields = classobj.getDeclaredFields();
        else
            fields = classobj.getFields();
        String fname;
        int modifiers;
        Object value;
        for (Field field : fields) {
            fname = field.getName();
            modifiers = field.getModifiers();
            if (Modifier.isPrivate(modifiers)) {
                System.out.printf("field %s: (PRIVATE)\n", fname);
                continue;
            }
            System.out.printf("field: %s, ", fname);
            try {
                value = field.get(obj);
            } catch (IllegalAccessException ex) {
                System.out.printf("IllegalAccessException %s\n", ex.getMessage());
                continue;
            }
            System.out.print("type: ");
            System.out.print(field.getGenericType().getTypeName());
            System.out.print(", value = ");
            System.out.println(value);
        }
    }
}
