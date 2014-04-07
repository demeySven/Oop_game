package game;

import java.util.*;

import be.kuleuven.cs.som.annotate.*;

/**
 * This class represents a monster and all of its properties. A monster has a
 * name, can fight, contains hitpoints, strenght and protection.
 * 
 * @author Sven de Mey & Donaat Kestemont
 * @version 1.0
 */

public class Monster {

	/**
	 * Creates a monster with a given name, protection, strenght, damage and maximum of hitpoints.
	 * @param	name
	 *			The name of the monster.           
	 * @param	protection
	 * 			The protection of the monster.
	 * @param	hitpointsBorn
	 * 			The hitpoints of the monster when it's created.
	 * @param	damage
	 * 			The amount of damage a monster can afflict to the other monster.
	 * @pre		The name has specific requirements so the method setName.
	 * 			| checkName(name)
	 * @pre		The protection has to be between 1 and 40.
	 * 			| setProtection(protection)
	 * @pre		The damage has to be between 1 and 40.
	 * 			| setDamage(damage)
	 * @post	The name of the monster is set to 'name'.
	 * @post	The protection is set to 'protection'.
	 * @post	The hitpointsBorn are set to their maximum value in 'hitpointsBorn'.
	 * @post	The hitpoints are set with the value getHitpointsBorn, monster has full life. 
	 * @post 	The strength is set to 'strength'. This strength is a random number and after infinitive trails the mean is 10.
	 * @post	The damage is set to 'damage'. 
	 */
	public Monster(String name, int protection, int hitpointsBorn, int damage) {
		setName(name);
		setProtection(protection);
		setHitpointsBorn(hitpointsBorn);
		setHitpoints(getHitpointsBorn());
		setStrength();
		setDamage(damage);
	}

	
	/**
	 * A string to store the name
	 */
	private String name;

	
	/**
	 * An inspector to check the name of the monster
	 * 
	 * @return	Returns the name of the monster.
	 */
	@Basic
	protected String getName() {
		return name;
	}

	
	/**
	 * This method sets the name of the monster.
	 * @param		name
	 * 				This name will be set if it does not throws an exception.		
	 * @throws		IllegalArgumentException: when the name doesn't follow the rules.
	 */
	protected void setName(String name) throws IllegalArgumentException {
		if (checkName(name))
			this.name = name;
	}
	
	
	/**
	 * This method checks of the given name is valid for this monster
	 * 
	 * 
	 * @param 	name
	 * 			This is the name the method should check
	 * @throws	If the name does not start with a capital and it contains something else than a-z, A-Z, 0-9, space or ' it should throw an exception.
	 * @return	If the name is correct, this method returns a boolean "true". 
	 * @pre		the name has to have a capital
	 * 			| character < 'A' && character > 'Z'
	 * @pre		the name cannot contains another character than a-z, A-Z, 0-9, space or '.
	 * 			| !((character >= 'A' && character <= 'Z') || (character >= 'a' && character <= 'z') || (character >= '0' && character <= '9') || (character == '\'') || (character == ' '))
	 */
	@Raw
	public boolean checkName(String name) {
		char character = name.charAt(0);
		if (character < 'A' && character > 'Z') {
			throw new IllegalArgumentException(
					"The name should start with a capital");
		}

		for (int i = 1; i < name.length(); i++) {
			character = name.charAt(i);
			if (!((character >= 'A' && character <= 'Z')
					|| (character >= 'a' && character <= 'z')
					|| (character >= '0' && character <= '9')
					|| (character == '\'') || (character == ' '))) {
				throw new IllegalArgumentException(
						"The name uses a illegal character, you should read the terms and conditions");
			}
		}

		return true;

	}

	
	/**
	 * An integer that contains the value of protection.
	 */
	private int protection;

	/**
	 * This inspector returns the value stored in protection.
	 * 
	 * @return		The protection of the monster
	 */
	@Basic
	protected int getProtection() {
		return protection;
	}

	/**
	 * This tests that the protection is a valid.
	 * 
	 * @Param	protection 
	 * 			This is the number that will be set if the conditions are met.
	 * @Pre		The protection can never be lower than 1(minProtection) and higher than maxProtection (=40 at the moment) and it should be a prime number.
	 * 			|protection >= minProtection && protection <= maxProtection && isPrime(protection) == true.
	*/
	@Raw
	@Model
	protected boolean isValidProtection(int protection){
		return (protection >= minProtection 
				&& protection <= maxProtection && isPrime(protection) == true);
	}
	
	/**
	 * This sets the protection to a specific number.
	 * 
	 * @param 	protection
	 * 			This is the number that will be set if the conditions are met.
	 * @note We tried to do it with an assert and not with a if-loop, but like that it didn't work.
	 * 
	 */
	@Raw
	 protected void setProtection(int protection) {
		if(isValidProtection(protection) == true){
			this.protection = protection;
		}
			
	}
	
	/**
	 * This method checks if a given number is a prim number
	 * 
	 * @param 	number
	 * 			The given number that has to been checked
	 * @Pre		A number is a prime number if it's only divisible by itself and 1.
	 * 
	 * @return	Returns true if it is a prime number so the protection can be set if the other conditions are fullfilled.
	 * 			|	if the number % 2 == 0, than it is not a prime number.
	 * 			|	for each odd number until the number itself:
	 * 			|	if the number % the odd number == 0, it is not a prime number.
	 * @note	If the number is divisible by 2 we don't need to check all the other even numbers, this speeds our method up.
	 * @note	We should make a calculation class for all the generic mathematical code.
	 */
	private boolean isPrime(int number) {
		// check if n is a multiple of 2
		if (number % 2 == 0)
			return false;
		// if not, then just check the odds
		for (int i = 3; i * i <= number; i += 2) {
			if (number % i == 0)
				return false;
		}
		return true;
	}

	/**
	 * An integer conaining the damage.
	 */
	private int damage;

	/**
	 * An inspector who returns the damage.
	 * 
	 * @return	Returns the damage.
	 */
	@Basic
	protected int getDamage() {
		return damage;
	}

	/**
	 * This method sets the damage of a monster
	 * 
	 * @param 	damage
	 * 			This value will be set if it's higher or equal to minDamage and lower than maxDamage
	 * @Pre		The given damage should be lower than maxDamage and higher or equal to minDamage
	 * 			| damage >= minDamage && damage <= maxDamage
	 * @Post	If the given number fullfilled the conditions this number is set. Otherwwise the damage wil be set to 1.
	 */
	protected void setDamage(int damage) {

		if (damage >= minDamage && damage <= maxDamage) {
			this.damage = damage;
		} else {
			this.damage = 1;
		}
	}

	
	/**
	 * An integer that contains the maximum hitpoints.
	 */
	private int hitpointsBorn;
	
	/**
	 * An inspector to check the value of hitpointsBorn
	 * 
	 * @return	Returns the HitpointsBorn
	 */
	@Basic
	protected int getHitpointsBorn() {
		return hitpointsBorn;
	}

	/**
	 * In this method the maximum value of hitpoints is set to hitpointsBorn.
	 * 
	 * @param 	hitpointsBorn
	 * 			The maximum value of hitpoints
	 * @pre		The value of hitpointsBorn can not be lower than 1.
	 * 			| hitpointsBorn <1
	 * @pre		The value of hitpointsBorn can not be lrger than the max value of an integer.
	 * 			| hitpointsBorn>=2147483647
	 * @post	If the conditions are met the value of hitpointsBorn is set.
	 */
	protected void setHitpointsBorn(int hitpointsBorn) throws IllegalArgumentException{

		if (hitpointsBorn < 1) {
			throw new IllegalArgumentException("Your hitpoints are too low");
		} 
		if (hitpointsBorn>=2147483647){
			throw new IllegalArgumentException("Your hitpoints are too high");
		}
		else {
			this.hitpointsBorn = hitpointsBorn;
		}
		
	}

	/**
	 * An integer that contains the value of the hitpoints at runtime level.
	 */
	private int hitpoints;

	
	/**
	 * This is an inspector to get the hitpoints
	 * @return hitpoints
	 */
	@Basic
	protected int getHitpoints() {
		return hitpoints;
	}

	/**
	 * This method sets the hitpoints during the run of the program
	 * 
	 * @param 	hitpoints
	 * 			This value holds the value of hitpoints during runtime.
	 * @Pre		The value of hitpoint can only be set if the new value of hitpoints is lower or equals to hitpointsBorn.
	 * 			| hitpoints <= getHitpointsBorn()
	 * @pre		If new hitpoints are higher than the max value, throw an exception.
	 * 
	 * @effect	If the conditions are met the hitpoints are set.
	 * 			| this.hitpoints = hitpoint
	 * 			
	 */
	protected void setHitpoints(int hitpoints) {
		if(hitpoints > getHitpointsBorn()){
			throw new RuntimeException("impossible to get higher hitpoints than hitpointBorn");
		}
		if(hitpoints <= getHitpointsBorn());
		this.hitpoints = hitpoints;

	}

	/**
	 * An integer that contains the strength
	 */
	private int strength;

	/**
	 * An inspector to check the value of strength.
	 * 
	 * @return The strength of a monster.
	 */
	@Basic
	protected int getStrength() {
		return strength;
	}

	/**
	 * This method generates an normally distributed value with a mean of 10.
	 * 
	 *@Pre  	the Strenght has to be a random number with the mean 10
	 * 			|	randomNumber.nextGaussian() * 15 + 10
	 * @effect The strength is generated during the runtime.
	 * 			|	this.strength = (int) (randomNumber.nextGaussian() * 15 + 10)
	 * 		
	 */
	protected void setStrength() {
		Random randomNumber = new Random();
		this.strength = (int) (randomNumber.nextGaussian() * 15 + 10);
	}

	
	/**
	 * fightingHitpoints is a integer. This value will be compared to the protection of the opponent. 
	 */
	private int fightingHitpoints;

	/**
	 * This is an inspector for fightingHitpoints
	 * @return the fightingHitpoints
	 */
	@Basic
	public int getFightingHitpoints() {
		return fightingHitpoints;
	}

	/**
	 * This is a method to assign fightingHitpoints
	 * 
	 * @effect	A random number is generated. If this number is smaller than the current hitpoints, 
	 * 			this random number is set as fightingHitpoints.
	 * 			|	number = getRandomInt(31)
	 * 			|	if random number < hitpoints:
	 * 			|	this.fightingHitpoints = number
	 * 			Else the fightingHitpoints is equal to the current hitpoints.
	 * 			|	this.fightingHitpoints = hitpoints
	 */
	public void setFightingHitpoints() {
		int number = getRandomInt(31);
		if (number < getHitpoints()) {
			this.fightingHitpoints = number;
		} 
		else 
			this.fightingHitpoints = hitpoints;
	}

	/**
	 * Returns a pseudo-random number between 0 and maxValuePlusOne, exclusive. 
	 * 
	 * @param maxValuePlusOne
	 *            Maximum value. Must be greater than min.
	 * @return Integer between 0 and maxValuePlusOne-1.
	 * @see java.util.Random#nextInt(int)
	 * 
	 * @note We could make a class only for our mathematical methods. This method should be put in it.
	 */
	public static int getRandomInt(int maxValuePlusOne) {

		
		Random rand = new Random();
		int  randomNumber = rand.nextInt(maxValuePlusOne);

		return randomNumber;
	}

	
	
	/**
	 * With this method we make the monsters fighting.
	 * 
	 * @param 	opponent
	 * 			The monster the will be attacked. 
	 * 
	 * @pre		A monster can only attack if its fightingHitpoints is higher or equal to the opponents protection.
	 * 			|	getFightingHitpoints() >= opponent.getProtection()
	 * 
	 * @effect	If the condition is fullfilled, we attack the opponent. The new hitpoints will be set
	 * 			|	opponent.setHitpoints(opponent.getHitpoints() - (int) (getDamage() + (getStrength() - 5) / 3))
	 * 
	 * @catch	If the hitpoints of the opponent exceed the hitpointsBorn, we will catch a RuntimException 
	 * 			and set the hitpoints to the value of hitpointsBorn. 
	 *  
	 * @note 	Problem in the exercise: If the protection (the prime numbers 31 and 37) is too high, we get an infinitive loop. 
	 * 			It's impossible to hit each other if he protection is higher than 
	 * 			the maxValuePlusOne(maximal value that fightingHitpoints can reach). 
	 * 			
	 * 			Solution: lower the protection to maxProtection to 30 or set maxValuePlusOne to 41. 
	 */
	public void hitting(Monster opponent) {
		setFightingHitpoints();
		
		if (getFightingHitpoints() >= opponent.getProtection()) {
			try{
				opponent.setHitpoints(opponent.getHitpoints() - (int) (getDamage() + (getStrength() - 5) / 3));	
				
			
			}
			catch(RuntimeException e){
				setHitpoints(getHitpointsBorn());
				System.out.println("The hitpoints are set to their maximum value!");
			}
		}
		
	}
	
	/**
	 * This method will check if 1 of the 2 opponents has no more life and is dead. 
	 * @param 	opponent
	 * 			The other monster. 
	 * 
	 * @pre 	If the hitpoints of one of the opponents is zero or lower they should be dead.
	 * 			| if getHitpoints() <= 0:
	 * 			| terminate()
	 * 
	 * @post	If the condition is met that monster should be terminated
	 * 			|	if opponent.getHitpoints() <= 0:
	 *			|	opponent.terminate()
	 * 
	 */
	public void checkIsDead(Monster opponent) {
		if (getHitpoints() <= 0) {
			terminate();
		}
		if (opponent.getHitpoints() <= 0) {
			opponent.terminate();
		}
	}
	
		/**
		 * Variable representing the terminated state of this creature.
		 */
		private boolean isTerminated = false;
		
		/**
		 * Returns the terminated state of this creature.
		 * 
		 * @return	The terminated state of this creature.
		 * 			| result == isTerminated
		 */
		@Basic @Raw
		public boolean isTerminated(){
			return isTerminated;
		}
		
		/**
		 * Terminates this creature.
		 * 
		 * @post	This creature will be terminated.
		 * 			| new.isTerminated()
		 */
		public void terminate() {
			this.isTerminated = true;
		}
	
		/**
		 * The maximum value of protection
		 */
		private final int maxProtection = 40;
		
		/**
		 * The minimum value of protection
		 */
		private final int minProtection = 1;
		
		/**
		 * The maximum value of damage
		 */
		private final int maxDamage = 40;
		
		/**
		 * The minumum value of damage
		 */
		private final int minDamage = 1;

}
