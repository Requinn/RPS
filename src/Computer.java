import java.util.*;

public class Computer {
	private static int i=0;
	public static void storeMove(char p){
		HashMap<Integer,Character> recent = new HashMap<>();
		recent.put(i, p);
		i++;
		System.out.println("Move stored!: "+p);
	}
}
