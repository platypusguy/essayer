import java.util.Date;
import java.util.Locale;

public class main {
    public static void main(String[] args) {
        String lamb = "Mary had a little lamb";
		byte bb = 0x30;
		char cc = 'Q';
		double dd = 101.0;
		float ff = 102.0f;
		int ii = 42;
		long jj = 43l;
		short ss = 44;
		boolean zz = true;
		System.out.printf("Compatible: %% %% %02x %f %f %d %d %d\n%s\n", bb, dd, ff, ii, jj, ss, lamb);
		System.out.printf("int variations: %d %o %x %X\n", ii, ii, ii, ii);
		System.out.printf("double variations: %f %e %E\n", dd, dd, dd);
		System.out.printf("double variations differing from JDK: %g %G\n", dd, dd);
		System.out.printf("Incompatible: %S %c (C) %b (B) %h (H) %a (A) %n \n", lamb, cc, zz, ii, dd);
		System.out.println(bb);
		System.out.println(cc);
		System.out.println(dd);
		System.out.println(ff);
		System.out.println(ii);
		System.out.println(jj);
		System.out.println(ss);
		System.out.println(zz);
		
		int intMinus1 = -1;
		System.out.printf("Incompatible print -1 in hex format: %x\n", intMinus1);
		byte byteMinus1 = (byte) -1;
		System.out.printf("Incompatible print -1 in hex format: %x\n", byteMinus1);

		Locale locale = new Locale("Elvish");
		System.out.println(locale);
		System.out.printf("Incompatible locale print as string: %s\n", locale);
		
    }
}
