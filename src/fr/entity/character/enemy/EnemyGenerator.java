package fr.entity.character.enemy;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

public class EnemyGenerator {
	
	public void init(GameContainer container,StateBasedGame game){
		new BasicEnemy(400,100,32,32);
	}

}