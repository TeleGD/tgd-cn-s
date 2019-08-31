package spaceCops;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import spaceCops.entity.character.Player;
import spaceCops.entity.character.enemy.Enemy;
import spaceCops.entity.character.enemy.EnemyGenerator;
import spaceCops.entity.projectile.Projectile;
import spaceCops.menus.GOMenu;
import spaceCops.menus.MissionMenu;
import spaceCops.menus.ScoresMenu;
import spaceCops.decor.Decor;


public class World extends BasicGameState{

	private int ID;
	private int state;

	public enum direction {HAUT,DROITE,BAS,GAUCHE};
	private Player player;
	private ArrayList<Projectile> projectiles;
	private ArrayList<Enemy> enemies;
	private EnemyGenerator enemyGen;
	private Decor decor;

	private int score = 0;
	public static Music Mbackground;

	public World(int ID) {
		this.ID = ID;
		this.state = 0;
	}

	@Override
	public int getID() {
		return this.ID;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		Mbackground = new Music("musics/battle_theme.ogg");
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée à l'apparition de la page */
		if (this.state == 0) {
			this.play (container, game);
		} else if (this.state == 2) {
			this.resume (container, game);
		}
	}

	@Override
	public void leave(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée à la disparition de la page */
		if (this.state == 1) {
			this.pause (container, game);
		} else if (this.state == 3) {
			this.stop (container, game);
			this.state = 0; // TODO: remove
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		/* Méthode exécutée environ 60 fois par seconde */
		decor.render(container, game,g);
		player.render(container, game, g);
		for(int i = 0; i<enemies.size();i++){
			enemies.get(i).render(container, game, g);
		}
		for(int i = 0; i<projectiles.size();i++){
			projectiles.get(i).render(container, game, g);
		}
		g.setColor(Color.white);
		g.drawString(""+getScore(), 10, 50);
		enemyGen.render(container, game, g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		/* Méthode exécutée environ 60 fois par seconde */
		Input input = container.getInput();
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			this.setState(1);
			game.enterState(4 /* Pause */, new FadeOutTransition(), new FadeInTransition());
		}
		player.update(container, game, delta);
		for(int i = 0; i<enemies.size();i++){
			enemies.get(i).update(container, game, delta);
		}
		for(int i = 0; i<projectiles.size();i++){
			projectiles.get(i).update(container, game, delta);
		}
		decor.update(container,game,delta);
		enemyGen.update(container, game, delta);
		//System.out.println(enemies.size());
	}

	public void play(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée une unique fois au début du jeu */
		projectiles = new ArrayList<Projectile>();
		player = new Player(this, (ScoresMenu) game.getState(5) /* ScoresMenu */);
		enemies = new ArrayList<Enemy>();
		enemyGen = new EnemyGenerator(this, (MissionMenu) game.getState(9) /* MissionMenu */);
		enemyGen.init(container,game);
		enemyGen.reset();
		decor = new Decor();
		score = 0;
		Mbackground.loop();
		((GOMenu) game.getState(3) /* GOMenu */).reset();
		((ScoresMenu) game.getState(5) /* ScoresMenu */).reset();
		//Player.reset();
	}

	public void pause(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée lors de la mise en pause du jeu */
	}

	public void resume(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée lors de la reprise du jeu */
	}

	public void stop(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée une unique fois à la fin du jeu */
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getState() {
		return this.state;
	}

	public ArrayList<Enemy> getEnemies(){
		return enemies;
	}
	public ArrayList<Projectile> getProjectiles(){
		return projectiles;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int scoreP) {
		score = scoreP;
	}

}
