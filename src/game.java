import java.util.*;
public class game {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Random rand = new Random();
		boolean gameState = true;
		boolean input = true;
		char pValue;
		char cValue = 0;
		int cChoice;
		int pChoice;
		while(gameState){
			System.out.println("We are playing Rock, Paper, Scissors.\nWhat do you throw?\n1. Rock\n2. Paper\n3. Scissors");
			pChoice = scan.nextInt();
			if(pChoice<=3 && pChoice >= 1){
				cChoice = rand.nextInt(3)+1;
				pValue = giveValue(pChoice);
				Computer.storeMove(pValue);
				gameOutcome(pChoice, cChoice);
				System.out.println("Play again?");
				String con = scan.next();
				if(con.contentEquals("Y")){
					gameState= true;
				}
				else{
					gameState = false;
				}
			}
			else{
				System.out.println("Invalid choice.");
			}
		}
	}
	public static char gameOutcome(int p, int c){
		//1 = ROCK . 2 = PAPER . 3 = SCISSORS
		char result='X';//W,L,T
		if(p==c){
			result = 'T';
		}
		else if(p==1){
			if(c==2){
				result = 'L';
			}
			if(c==3){
				result = 'W';
			}
		}
		else if(p==2){
			if(c==1){
				result = 'W';
			}
			if(c==3){
				result = 'L';
			}
		}
		else if(p==3){
			if(c==1){
				result = 'L';
			}
			if(c==2){
				result = 'W';
			}
		}
		else{
			System.out.println("There was an error handling the outcome.");
		}
		return result;
	}
	
	public static char giveValue(int d){
		char value = 0;
		switch (d){
		case 1:
			value = 'R';
			break;
		case 2: 
			value = 'P';
			break;
		case 3:
			value = 'S';
			break;
		}
		return value;
	}
}
