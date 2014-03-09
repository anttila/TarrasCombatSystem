package se.modu;
import java.util.Random;
import java.util.Scanner;

public class Combat {
	
	private Random rand;
	
	public Combat(){
		rand = new Random();
	}
	
	public void run(){
		String combat, tenAgain;
		int baseDamage = -1;
		int dicePool = -1;
		int difficulty = -1;
		int success = 0;
		Scanner in = new Scanner(System.in);
		System.out.print("Combat (Y) or Non-combat (N)? ");
		combat = in.next();
		if(combat.equals("Y") || combat.equals("y")){
			System.out.print("Base Weapon Damage (1-10) ");
			baseDamage = in.nextInt();
		}
		System.out.print("Ten again? (Y/N) ");
		tenAgain = in.next();
		System.out.print("Base Dice Pool ");
		dicePool = in.nextInt();
		System.out.print("Difficulty ");
		difficulty = in.nextInt();
		in.close();
		// Input done, logic:
		success = successRolls(dicePool, difficulty, (tenAgain.equals("Y")||tenAgain.equals("y") ));
		
		// Output
		System.out.println();
		if(success == 0){
			System.out.println("Action failed");
		} else if (success < 0){
			System.out.println("Action botched");
		} else if (success >= 1){
			System.out.print("Number of successes: "+success);
			if(combat.equals("y") || combat.equals("Y")){
				System.out.println();
				System.out.print("Damage dice rolled: ");
				int combatSuccess = 0;
				// Rerolls
				for(int i=0;i<(baseDamage+success-1);i++){
					int d10 = rollD10();
					System.out.print(d10+" ");
					if(d10 >= 6){
						combatSuccess++;
					}
				}
				System.out.print("\nDamage successes: "+combatSuccess);
			}
			System.out.println();
		}
	}
	
	private int successRolls(int dicePool, int difficulty, boolean tenAgain){
		int success = 0;
		System.out.println();
		System.out.print("Dice rolled: ");
		for(int i=0;i<dicePool;i++){
			int d10 = rollD10();
			System.out.print(d10+" ");
			if(tenAgain){
				if(d10 >= difficulty && d10 > 1 && d10 < 10){ 
					success++;
				} else if (d10 == 10){
					success += 2;
				} else if (d10 == 1){
					success--;
				}
			} else {
				if(d10 >= difficulty && d10 > 1){
					success++;
				} else if (d10 == 1){
					success--;
				}
			}
		}
		System.out.println();
		return success;
	}
	
	private int rollD10(){
		return rand.nextInt(10)+1;
	}
	public static void main(String[] args) {
		new Combat().run();
	}
}
