package games.spaceCops.menus;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import app.AppFont;
import app.AppLoader;

import games.spaceCops.World;

public class ScoresMenu extends BasicGameState {

	private int ID;

	private Font font5;
	private Font font6;

	private String nom = "Scores :";
	private String[] items = { "1. ", "2. ", "3. ", "4. ", "5. ", "",
			" Retour Menu" };

	public int nbrOption = items.length;

	public String[] getItems() {
		return this.items;
	}

	private Image background;
	int selection = nbrOption - 1;

	private boolean firstTime;
	private int[] scoresList = {0, 0, 0, 0, 0};

	public ScoresMenu(int ID) {
		this.ID = ID;
	}

	@Override
	public int getID() {
		return this.ID;
	}

	public void addScoreToList(World world) {
		if (world.getScore() > scoresList[4]) {

			scoresList[4] = world.getScore();
			int i = 4; int tmp;
			while (i>0 && scoresList[i]>scoresList[i-1]) {
				tmp = scoresList[i];
				scoresList[i] = scoresList[i-1];
				scoresList[i-1] = tmp;
				i--;
			}
		}

		for (int k=0; k<5; k++)
			items[k] = (k+1)+". "+scoresList[k];
		firstTime = false;
	}

	public void init(GameContainer container, StateBasedGame game) {
		container.setShowFPS(false);
		background = AppLoader.loadPicture("/images/spaceCops/0003.png");

		font5 = AppLoader.loadFont("Courant", AppFont.BOLD, 18); // TODO: trouver une fonte équivalente

		font6 = AppLoader.loadFont("Courant", AppFont.BOLD, 13); // TODO: trouver une fonte équivalente

		firstTime = true;
	}


	public void update(GameContainer container, StateBasedGame game, int delta) {
		Input input = container.getInput();
		if (input.isKeyPressed(Input.KEY_ENTER) || input.isKeyPressed(Input.KEY_SPACE) || input.isKeyPressed(Input.KEY_ESCAPE)) {
			game.enterState(2 /* MainMenu */, new FadeOutTransition(), new FadeInTransition());
		}
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		g.drawImage(background, 0, 0);

		g.setColor(Color.red);
		g.setFont(font5);

		g.drawString(this.nom, 190, 370);

		g.setColor(Color.white);
		g.setFont(font6);

		for (int i = 0; i < nbrOption; i++) {
			if (items[i].length() > 3 && items[i].charAt(3) != '0')
				g.drawString(items[i], 175, 420 + 13 * i);
			else if (items[i].length() > 3)
				g.drawString(items[i].substring(0, 3), 175, 420 + 13 * i);
			else
				g.drawString(items[i], 175, 420 + 13 * i);

		}

		g.drawString(">>", 165, 420 + 13 * selection);
	}

	public void reset() {
		firstTime = true;
	}

}
