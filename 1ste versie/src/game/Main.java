package game;

import static game.Monster.*;

public class Main {

	public static void main(String[] args) {
		// construction of monster 1
		Monster monster1 = new Monster("Sven", 23, 100, 30);
		// construction of monster 2
		Monster monster2 = new Monster("Donaat", 23, 100, 30);

		/**
		 * With this method we fight thill one of the monsters is dead. The monster that can start is choosen by a random number.
		 * If one of the opponent is dead at the end of the cycle, he will be terminated
		 * 
		 * @pre		Both of the monsters should be a life
		 * @effect	The monsters are hitting each other.
		 * @effect	At the end we check if the monster is dead.
		 * 
		 */
		while (monster1.isTerminated() == false	&& monster2.isTerminated() == false) {
			int randomFight = getRandomInt(2) + 1;
			if (randomFight == 2) {
				monster2.hitting(monster1);
				monster1.hitting(monster2);
			}
			if (randomFight == 1) {
				monster1.hitting(monster2);
				monster2.hitting(monster1);
			}

			monster1.checkIsDead(monster2);
		

		}

		if (monster1.isTerminated()) {

			System.out.println("Monster 2 wins!");
			System.out.println("Name: " + monster2.getName() + 
					", Protection: " + monster2.getProtection() + 
					", Hitpoints / HitpointsBorn:" + monster2.getHitpoints() + "/" + monster2.getHitpointsBorn() + 
					", Damage: " + monster2.getDamage());

		}
		if (monster2.isTerminated()) {

			System.out.println("Monster 1 wins!");
			System.out.println("Name: " + monster1.getName() + 
					", Protection: " + monster1.getProtection() + 
					", Hitpoints/HitpointsBorn: " + monster1.getHitpoints() + "/"+ monster1.getHitpointsBorn() + 
					", Damage: " + monster1.getDamage());
		}

	}
}