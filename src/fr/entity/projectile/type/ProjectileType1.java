package fr.entity.projectile.type;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.entity.projectile.Projectile;
import fr.util.Rectangle;

public class ProjectileType1 extends Projectile implements Rectangle {
// Ce projectile avance selon l'angle donne
// en oscillant de gauche a droite (avec une periode de p ou 128 par defaut).
	
	private double spawnX;// X d'apparition du projectile.
	private double spawnY;// Y d'apparition du projectile.
	private int amplitude;// Amplitude du sinus.
	private int period;// Periode du sinus.
	
	public ProjectileType1(double x, double y, double angle, double speed) {
		super(x, y, angle, speed);
		spawnY = y;
		amplitude = 64;
		period = 128;
	}
	
	public ProjectileType1(double x, double y, double angle, double speed, int period){
		super(x, y, angle, speed);
		spawnY = y;
		amplitude = 64;
		this.period = period;
	}
	
	public void move(int delta) { 
		y += speedY*delta;
		x = spawnX + amplitude*Math.sin((spawnY-y)*(2*Math.PI)/128.0);
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.green);
		g.fillRect((float)x, (float)y, (float)width, (float)height);
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		move(delta);
	}
}
