package com.trulis.rps;

import java.util.Random;
import java.util.Scanner;

public class RockPaperScrissors
{
	private User user;
	private Computer computer;
	private int userScore;
	private int computerScore;
	private int numberOfGames;
	
	private enum Move
	{
		ROCK, PAPER, SCRISSORS;
		
		public int compareMoves(Move otherMove)
		{
			if(this == otherMove)
				return 0;
			
			switch(this)
			{
			case ROCK:
				return(otherMove == SCRISSORS ? 1 : -1);
			case PAPER:
				return(otherMove == ROCK ? 1 : -1);
			case SCRISSORS:
				return(otherMove == PAPER ? 1 : -1);
			}
			
			return 0;
		}
	}
	
	public void startGame()
	{
		System.out.println("ROCK, PAPER, SCRISSORS!");
		Move userMove = user.getMove();
		Move computerMove = computer.getMove();
		System.out.println("\nYou played " + userMove + ".");
		System.out.println("Computer played " + computerMove + ".\n");
		
		int compareMoves = userMove.compareMoves(computerMove);
		switch (compareMoves)
		{
		case 0:
			System.out.println("Tie!");
			break;
		case 1:
			System.out.println(userMove + " beats " + computerMove + ". You Won!");
			userScore++;
			break;
		case -1:
			System.out.println(computerMove + " beats " + userMove + ". You lost!");
			computerScore++;
			break;
		}
		numberOfGames++;
		
		if(user.playAgain())
		{
			System.out.println();
			startGame();
		}
		else
		{
			printGameStats();
		}
	}
	
	private void printGameStats() {
	    int wins = userScore;
	    int losses = computerScore;
	    int ties = numberOfGames - userScore - computerScore;
	    double percentageWon = (wins + ((double) ties) / 2) / numberOfGames;
	 
	    System.out.print("+");
	    printDashes(68);
	    System.out.println("+");
	 
	    System.out.printf("|  %6s  |  %6s  |  %6s  |  %12s  |  %14s  |\n",
	            "WINS", "LOSSES", "TIES", "GAMES PLAYED", "PERCENTAGE WON");
	 
	    System.out.print("|");
	    printDashes(10);
	    System.out.print("+");
	    printDashes(10);
	    System.out.print("+");
	    printDashes(10);
	    System.out.print("+");
	    printDashes(16);
	    System.out.print("+");
	    printDashes(18);
	    System.out.println("|");
	 
	    System.out.printf("|  %6d  |  %6d  |  %6d  |  %12d  |  %13.2f%%  |\n",
	            wins, losses, ties, numberOfGames, percentageWon * 100);
	 
	    System.out.print("+");
	    printDashes(68);
	    System.out.println("+");
	}
	
	private class User
	{
		private Scanner inputScanner;
		
		public User()
		{
			inputScanner = new Scanner(System.in);
		}
		
		public Move getMove()
		{
			System.out.println("Rock, paper or scrissors ?");
			
			String userInput = inputScanner.nextLine();
			userInput = userInput.toUpperCase();
			char firstLetter = userInput.charAt(0);
			if(firstLetter == 'R' || firstLetter == 'P' || firstLetter == 'S')
			{
				switch(firstLetter)
				{
				case 'R':
					return Move.ROCK;
				case 'P':
					return Move.PAPER;
				case 'S':
					return Move.SCRISSORS;
				}
			}
			return getMove();
		}
		
		public boolean playAgain()
		{
			System.out.print("Do you want to play again? ");
			String userInput = inputScanner.nextLine();
			userInput = userInput.toUpperCase();
			return userInput.charAt(0) == 'Y';
		}
	}
	
	private class Computer
	{
		public Move getMove()
		{
			Move[] moves = Move.values();
			Random random = new Random();
			int index = random.nextInt(moves.length);
			return moves[index];
		}
	}
	
	public RockPaperScrissors()
	{
		user = new User();
		computer = new Computer();
		userScore = 0;
		computerScore = 0;
		numberOfGames = 0;
	}
	
	private void printDashes(int numberOfDashes)
	{
		for(int i = 0; i < numberOfDashes; i++)
		{
			System.out.print("-");
		}
	}
	
	public static void main (String [] args)
	{
		RockPaperScrissors game = new RockPaperScrissors();
		game.startGame();
	}
}
