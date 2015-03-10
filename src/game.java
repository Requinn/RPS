import java.io.*;
import java.util.*;

public class game {
	private static File computerdat = new File("Computerdata.dat");
	public static void main(String[] args) {
		Random rand = new Random();
		boolean gameState = true;
		int pChoice, difficultyChoice;
		char pValue=0,cValue;
		Computer AI = new Computer();
		System.out.println("Which difficulty would you like to play?\n1. Easy Mode (no play history)\n2. Veteran (loads previous plays)");
		difficultyChoice = checkInt(1,2);
		if(difficultyChoice == 2){
			if(computerdat.exists()){
				System.out.println("Loading file...");
				try{
					ObjectInputStream readin = new ObjectInputStream(new FileInputStream(computerdat));
					AI = (Computer) readin.readObject();
				}catch(FileNotFoundException e){
					System.out.println("File not found!");
				} catch (IOException e) {
					System.out.println("Can not read file!");
				} catch (ClassNotFoundException e) {
					System.out.println("Could not find the class!");
				}
			}
			else{
				System.out.println("Continuing with no file.");
				//AI = new Computer();
			}
		}
		while(gameState){
			System.out.println("We are playing Rock, Paper, Scissors.\nWhat do you throw?\n1. Rock\n2. Paper\n3. Scissors\n4. Show stats\n5. Quit.");
			pChoice = checkInt(1,5);
			if(pChoice==1||pChoice==2||pChoice==3){
				if(pChoice==1){
					pValue = 'R';
				}
				if(pChoice==2){
					pValue = 'P';
				}
				if(pChoice==3){
					pValue = 'S';
				}
				AI.storeMovetoHash(AI.giveValue(pChoice));
				cValue = AI.predictMove();
				System.out.println("You threw "+getThrowName(pChoice)+".\nThe Computer threw "+getThrowName(cValue));
				AI.gameOutcome(pValue, cValue);
			}
			if(pChoice==4){
				System.out.println("Wins: "+AI.getWins()+"\nTies: "+AI.getTies()+"\nLosses: "+AI.getLoss());
			}
			if(pChoice==5){
				System.out.println("Saving game.");
				saveGame(AI);
				System.exit(0);
			}
		}
	}

	public static void saveGame(Computer c){
		try{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(computerdat));
			out.writeObject(c);
			out.close();
		}catch(IOException e){
			System.out.println("Error processing save.");
		}
	}
	public static int checkInt(int low, int high) {
		Scanner into = new Scanner(System.in);
		boolean valid = false;
		int validNum = 0;
		while (!valid) {
			if (into.hasNextInt()) {
				validNum = into.nextInt();
				if (validNum >= low && validNum <= high) {
					valid = true;
				} else {
					System.out.print("Invalid input. Try again: ");
				}
			} else {
				// clear buffer of junk input
				into.next();
				System.out.print("Invalid input. Try again: ");
			}
		}
		return validNum;
	}
	public static String getThrowName(char c){
		String name = "DEFAULT";
		switch (c){
		case 'R':
			name = "ROCK";
			break;
		case 'P': 
			name = "PAPER";
			break;
		case 'S':
			name = "SCISSORS";
			break;
		}
		return name;
	}
	public static String getThrowName(int d){
		String name = "DEFAULT";
		switch (d){
		case 1:
			name = "ROCK";
			break;
		case 2: 
			name = "PAPER";
			break;
		case 3:
			name = "SCISSORS";
			break;
		}
		return name;
	}
}
