package fr.entity.character;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.state.StateBasedGame;

import fr.util.Movable;
import fr.util.Rectangle;
import fr.world.World.direction;

public class Player extends Movable implements Rectangle {

	private direction dir=direction.GAUCHE;
	private boolean ispressedHaut,ispressedBas,ispressedDroite,ispressedGauche;
	private boolean droitegauche=false,hautbas=false;  /* hautbas= true si bas dernier mis, droitegauche= true si droite dernier mis*/
	private boolean upPress = false;
	private boolean downPress = false;
	private boolean leftPress = false;
	private boolean rightPress = false;
	private boolean dash=false;
	private long timeDashInit=0;
	
	private Image imagegauche,imagecentrale,imagedroite,image;
	
	public Player() {
		x = 400;
		y = 500;
		width = 64;
		height = 64;
		speedX = 0;
		speedY = 0;
		isMoving = true;
		try {
			imagedroite=new Image("sprites/ship2.png");
			imagegauche=new Image("sprites/ship0.png");
			imagecentrale=new Image("sprites/ship1.png");
			image=imagecentrale;
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.blue);
		
		
		g.drawImage(image,(float)x,(float)y);
		g.setColor(Color.green);
	}

	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		speedX = 0;
		speedY = 0;
		
		if((upPress && !downPress) || (upPress && downPress && !hautbas)) 
		{
			if(y>300){
				speedY=-0.5;
			}
			
		}
		if((downPress && !upPress) || (upPress && downPress && hautbas)){
			if(y< 600- height){
				speedY=0.5;
			}
		}
		if((leftPress && !rightPress)|| (leftPress && rightPress && !droitegauche))
		{
			if(x>0){
				speedX = -0.5;
				image=imagegauche;
			}
			
		}
		if((!leftPress && rightPress)|| (leftPress && rightPress && droitegauche))
		{
			if(x< 800 - width)
			{

				image=imagedroite;
				speedX = 0.5;
			}
		}
		if(!rightPress && !leftPress)
		{
			image=imagecentrale;
		}
		
		if(dash && System.currentTimeMillis()-timeDashInit>10500){
			dash=false;
		}
		else if(dash && System.currentTimeMillis()-timeDashInit<500){
			speedX*=4;
			speedY*=4;
		}
		
		moveX(delta);
		moveY(delta);
		
	}

	public void keyReleased(int key, char c) {
		
		switch (key){
		
			case Input.KEY_UP:
				upPress=false;
			break;
			
			case Input.KEY_DOWN:
				downPress=false;
			break;
			
			case Input.KEY_LEFT:
				leftPress=false;
			break;
			
			case Input.KEY_RIGHT:
				rightPress=false;
			break;
			
		}
		
		
	}

	public void keyPressed(int key, char c) {
		switch (key){
		
		case Input.KEY_UP:
			upPress=true;
			hautbas=false;
		break;
		
		case Input.KEY_DOWN:
			downPress=true;
			hautbas=true;
		break;
		
		case Input.KEY_LEFT:
			leftPress=true;
			droitegauche=false;
		break;
		case Input.KEY_RIGHT:
			rightPress=true;
			droitegauche=true;
		break;
		case Input.KEY_LSHIFT:
			if(!dash)
			{
				dash=true;
				timeDashInit=System.currentTimeMillis();
			}
		break;

		}
		
	}
	
	



}
