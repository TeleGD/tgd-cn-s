package fr.entity.character.enemy;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.entity.projectile.Projectile;


public class RotateEnemy extends Enemy{
	
	float rotation;

	public RotateEnemy(double x, double y, double width, double height, int time) {
		super(x, y, width, height,time);
		speedX = 0.3;
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		super.update(container, game, delta);
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.red);
		g.fillRect((float)x,(float)y,(float)width,(float)height);
	}
	
	public void move(int delta){
		moveX(delta);
		if(x<0){
			x = 0;
			speedX = -speedX;
		}
		if(x>800-width){
			x = 800 - width;
			speedX = -speedX;
		}
		moveY(delta);
		if(y<0){
			y = 0;
			speedY = -speedY;
		}
		if(y>600-height){
			y = 600-height;
			speedY = -speedY;
		}
	}
	
	void shoot(){
		new Projectile((double) x+(width/2)-8,(double)y+(height/2)-8,rotation,0.3);
	}


}
