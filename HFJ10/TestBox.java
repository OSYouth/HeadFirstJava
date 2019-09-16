import java.util.*;

public class TestBox{

	Integer i;
	int j;

	public static void main(String[] args){
		TestBox t = new TestBox();
		t.go();

		Calendar c = Calendar.getInstance();
		c.set(2004,1,7,15,40);
		c.set(c.MONTH,MAY);
		System.out.println("add 35 days # " + c.getTime());
		c.add(c.DATE,35);
		System.out.println("add 35 days # " + c.getTime());
		c.roll(c.DATE,35);
		System.out.println("roll 35 days # " + c.getTime());
		long day1 = c.getTimeInMillis();
		System.out.println(c.getTime());
		day1 = day1 + 1000*60*60;
		c.setTimeInMillis(day1);
		System.out.println("new hour # " + c.get(c.MONTH));
		c.add(c.DATE,35);
		System.out.println("add 35 days # " + c.getTime());
		c.roll(c.DATE,35);
		System.out.println("roll 35 days # " + c.getTime());
		c.set(c.MONTH, 0);
		System.out.println("set to 12 # " + c.getTime());
	}

	public void go(){
		// i = j;
		// System.out.println(j);
		// System.out.println(i);
		// System.out.println(String.format(" I have %.2f, bugs to fix.", 476578.09876));

		// Date today = new Date();
		// System.out.println(String.format("%tc", today));
		// System.out.println(String.format("%tr", today));
		// System.out.println(String.format("%tA, %tb, %td", today,today,today));
		// System.out.println(String.format("%tA, %<tB, %<td",today));


	}
}