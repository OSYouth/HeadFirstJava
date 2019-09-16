import java.util.*;

public class RegretsTest{
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int z = 11;
		System.out.println(Integer.toBinaryString(z));
		byte x = -11;
		System.out.println(Integer.toBinaryString(x));
		int height = -in.nextInt();

		assert (height >0) : "height = " +height ;

		int y = x >>> 2;
		System.out.println(Integer.toBinaryString(y));

		// System.out.println(x);
		// for(int x = 0; x < 10; x++){
		// 	System.out.print(x + " ");
		// }

		StringBuffer sb = new StringBuffer("spring");
		System.out.println(sb.charAt(1));
		sb = sb.delete(3,5);
		System.out.println("sb = " +sb);

		String s = "A2";
		System.out.println(s.toLowerCase());

	}
}