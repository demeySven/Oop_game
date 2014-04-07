package game;
import static org.junit.Assert.*;

	import org.junit.Before;
import org.junit.Test;

public class TestClass {
	Monster monster;
	Monster monster2;
	
	@Before
	public void player() throws Exception{
		monster = new Monster("Donaat", 19, 100, 1);
		String name=monster.getName();
		System.out.println("name:"+name);
		int protection=monster.getProtection();
		System.out.println("protection"+protection);
		int strength=monster.getStrength();
		System.out.println("strength"+strength);
		System.out.println("hitpoints"+monster.getHitpoints());
		monster2 = new Monster("Sven", 23, 100, 1);
		String name2=monster2.getName();
		System.out.println("name:"+name2);
		int protection2=monster2.getProtection();
		System.out.println("protection"+protection2);
		int strength2=monster2.getStrength();
		System.out.println("strength"+strength2);
		System.out.println("Hitpoints:"+monster2.getHitpoints());
	}



	@Test
	public void testMonster(){
		assertTrue(monster.getName() == "Donaat");
		assertTrue(monster.getProtection() == 19);
		assertTrue(monster.getHitpointsBorn() == 100);
		assertTrue(monster2.getName() == "Sven");
		assertTrue(monster2.getProtection() == 23);
		assertTrue(monster2.getHitpointsBorn() == 100);
	}
	
	
	@Test
	public void testHit(){
		monster.hitting(monster2);
		System.out.println("setHitpoints van opponent(Sven)"+monster2.getHitpoints());
		assertTrue(monster2.getHitpoints() <= 100);
		System.out.println("FightingHitpoints van attacker(Donaat):"+monster.getFightingHitpoints());
		monster2.hitting(monster);
		assertTrue(monster.getHitpoints()<=100);
			System.out.println("deij!");
	}


}
