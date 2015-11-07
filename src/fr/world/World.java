package fr.world;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import fr.entity.character.Player;
import fr.entity.projectile.Projectile;


public class World extends BasicGameState{
	
	public enum direction {HAUT,DROITE,BAS,GAUCHE};
	private static Player player;
	public static int ID=0;
	private static Projectile p1;
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		player = new Player();
		p1 = new Projectile(400,400,0);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		player.render(container, game, g);
		p1.render(container,game,g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		player.update(container, game, delta);
		p1.update(container, game,delta);
	}
	
	public void keyReleased(int key, char c) {
		player.keyReleased(key, c);
	}


	public void keyPressed(int key, char c) {
		player.keyPressed(key, c);
	}

	@Override
	public int getID() {
		return ID;
	}
	
}
