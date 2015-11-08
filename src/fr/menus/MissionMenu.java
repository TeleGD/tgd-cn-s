package fr.menus;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import fr.world.World;

public class MissionMenu extends BasicGameState {

	public static int ID = 9;

	static TrueTypeFont font5;
	static TrueTypeFont font6;

	public static String[] txt1 = new String[2];
	public static String[] txt2 = new String[2];
	public static String[] txt3 = new String[2];
	public static String[] planetName = {"Kepler-770-C", "Utopia", "Balmoran", "Sulituan", "Naeco", "Nihpuad"};
	public static String[] postName = {"g�n�ral 6 �toiles en chef", "capitaine de section", "grand administrateur", "chef artilleur"};
	public static ArrayList<String> textList;

	private String nom = "Mission :";

	private Image background;
	static GameContainer container;
	static StateBasedGame game;

	
	public static ArrayList<String> generateText(int lineSize) {
		ArrayList<String> res = new ArrayList<String>();
		Random r = new Random();
		
		int numMission = r.nextInt(2);
		int numPlanet = r.nextInt(planetName.length);
		int numPost = r.nextInt(postName.length);
		
		String text = txt1[numMission]+planetName[numPlanet]+txt2[numMission]+postName[numPost]+txt3[numMission];
		int lastSpaceIndex = 0;
		int chunkStart = 0;
		String tmp = "";
		
		for (int i=0; i<text.length(); i++) {
			char c = text.charAt(i);
			
			if (c == ' ') {
				lastSpaceIndex = i;
			}
			
			if (i-chunkStart == lineSize) {
				tmp = text.substring(chunkStart, lastSpaceIndex);
				res.add(tmp);
				chunkStart = lastSpaceIndex;
			} 
			
		}

		tmp = text.substring(chunkStart, text.length());
		res.add(tmp);
		
		return res;
	}

	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.container = container;
		container.setShowFPS(false);
		this.game = game;
		background = new Image("sprites/0005.png");

		Font titre5Font = new Font("Courant", Font.BOLD, 18);
		font5 = new TrueTypeFont(titre5Font, false);

		Font titre6Font = new Font("Courant", Font.BOLD, 13);
		font6 = new TrueTypeFont(titre6Font, false);

		txt1[0] = "Depuis plusieurs ann�es, la plan�te ";
		txt2[0] = " vit dans la peur de la menace spatio-terroriste. Sur une des derni�res plan�tes de la galaxie poss�dant encore des arbres naturels fournissant l'oxyg�ne n�cessaire � la vie, l'�conomie inter-stellaire est gangr�n�e par le trafic de v�g�taux. Ces pirates de l'espace poss�dent leur propre arm�e et agissent en bande organis�e, se livrant � toutes sortes d'activit�s anti-�cologiques de grande ampleur. Vous venez d'�tre nomm� au poste de ";
		txt3[0] = " � la t�te de la brigade d'intervention et de pr�vision des attaques. Prenez les devants de votre escadron et faites le m�nage dans cette arm�e de cosmobrigands !";

		txt1[1] = "Des id�alistes sovi�tiques tentent de mettre en place une nouvelle r�volution communiste ! Apr�s la migration de l'humanit� vers la plan�te ";
		txt2[1] = ", des groupuscules arm�s ont tent� de prendre le pouvoir par la force. Gr�ce � la puissance du grand chef supr�me, les camarades tentent d'envahir les bastions de l'ennemi capitaliste depuis leurs vaisseaux spatiaux. Si rien n'est fait pour les arr�ter, la face de ce nouveau monde pourrait changer � jamais ! En tant que ";
		txt3[1] = " de la contre-r�volte capitaliste, vous avez la charge de tuer dans l'oeuf ce soul�vement prol�taire. Soyez fort, le sort de la plan�te est entre vos mains.";
		
		textList = generateText(40);
		
	}

	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {

	}

	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {

		g.drawImage(background, 0, 0);

		g.setColor(Color.red);
		g.setFont(font5);

		g.drawString(this.nom, 90, 235);

		g.setColor(Color.white);
		g.setFont(font6);

		for (int j = 0; j<textList.size(); j++) {
			g.drawString(textList.get(j), 75, 258+13*j);
		}

		g.drawString(">>  Continuer", 75, 255 + 25 + textList.size()*13);

	}
	
	public static void reset() {
		textList = generateText(40);
	}

	@Override
	public void keyPressed(int key, char c) {
		switch (key) {
		case Input.KEY_ENTER:
			game.enterState(World.ID, new FadeOutTransition(),
					new FadeInTransition());
			World.reset();
			break;
		case Input.KEY_ESCAPE:
			game.enterState(MainMenu.ID, new FadeOutTransition(),
					new FadeInTransition());
			break;
		}
	}

	public int getID() {
		return ID;
	}
}
