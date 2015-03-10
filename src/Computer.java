import java.io.*;
import java.util.*;

public class Computer implements Serializable {
	private String patternString;
	int wins=0,loss=0,tie=0;
	File patterns = new File("Pattern.txt");
	private HashMap<Pattern, Integer> history = new HashMap<>();
	
	public void storeMovetoHash(char p){
		patternString += p;
		if(patternString.length()>4){
			patternString = patternString.substring(patternString.length()-4, patternString.length());
			Pattern pat = new Pattern(patternString);
			if(history.containsKey(p)){
				history.put(pat, history.get(pat)+1);
			}
			else{
				history.put(pat,1);
			}
		}
		System.out.println("Move stored: "+p);
	}
	
	public char predictMove(){
		Random rand = new Random();
		if(patternString.length()!=4){
			return giveValue(rand.nextInt(3)+1);
		}
		else{
			System.out.println(patternString);
			int max = -1;
			Pattern pat1 = new Pattern(patternString.substring(patternString.length()-3, patternString.length())+"R");
			Pattern pat2 = new Pattern(patternString.substring(patternString.length()-3, patternString.length())+"P");
			Pattern pat3 = new Pattern(patternString.substring(patternString.length()-3, patternString.length())+"S");
			Pattern patternMax = null;
			if(history.containsKey(pat1)){
				if(history.get(pat1)>max){
					max = history.get(pat1);
					patternMax = pat1;
				}
			}
			
			if(history.containsKey(pat2)){
				if(history.get(pat2)>max){
					max = history.get(pat2);
					patternMax = pat2;
				}
			}
			
			if(history.containsKey(pat3)){
				if(history.get(pat3)>max){
					max = history.get(pat3);
					patternMax = pat3;
				}
			}
			
			if(max==-1){
				return giveValue(rand.nextInt(3)+1);
			}
			if(patternString.contains("RRRR")||patternString.contains("PPPP")||patternString.contains("SSSS")){
				return ensureWin(patternMax.getPattern()[patternMax.getPattern().length-1]);
			}
			else{
				return patternMax.getPattern()[patternMax.getPattern().length-1];
			}
		}
	}
	
	public char ensureWin(char prediction){
		System.out.println(prediction);
		switch(prediction){
		case 'R': 
			return 'P';
		case 'P':
			return 'S';
		case 'S':
			return 'R';
		}
		return prediction;
	}
	
	public void gameOutcome(char p, char c){
		//1 = ROCK . 2 = PAPER . 3 = SCISSORS
		if(p==c){
			System.out.println("There was a tie.");
			tie++;
		}
		else if(p=='R'){
			if(c=='P'){
				System.out.println("You lost.");
				loss++;
			}
			if(c=='S'){
				System.out.println("You won.");
				wins++;
			}
		}
		else if(p=='P'){
			if(c=='R'){
				System.out.println("You won.");
				wins++;
			}
			if(c=='S'){
				System.out.println("You lost.");
				loss++;
			}
		}
		else if(p=='S'){
			if(c=='R'){
				System.out.println("You lost.");
				loss++;
			}
			if(c=='P'){
				System.out.println("You won.");
				wins++;
			}
		}
		else{
			System.out.println("There was an error handling the outcome.");
		}
	}
	
	public char giveValue(int d){
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
	
	public int getWins(){
		return wins;
	}
	public int getLoss(){
		return loss;
	}
	public int getTies(){
		return tie;
	}
}
