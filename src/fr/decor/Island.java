package fr.decor;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.util.Movable;

public class Island extends Movable{
	
	
	private Image image;

	private int compteur;
	public Island(double x,double y)
	{
		this.x=x;
		this.y=y;
		speedY=0.7;
		try {
			image=new Image("sprites/island1.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		image=image.getScaledCopy((int) (Math.random()*200)+200,(int) (Math.random()*200)+200);
		image.rotate((float) (Math.random()*360));
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawImage(image,(float) x, (float)y);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		y+=5;
		if(y>600)Decor.islands.remove(this);
		

		compteur+=1;
		
	}

}
