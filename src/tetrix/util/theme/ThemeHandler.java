package tetrix.util.theme;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ThemeHandler {

	private static String theme = "img/overworld/";		//default theme
	public static final String BACKGROUND_IMG = "background.png";
	public static final String BACK_IMG = "back.png";
	public static final String CANNON_IMG = "cannon.png";
	public static final String CANNON2_IMG = "cannon2.png";
	public static final String CANNON3_IMG = "cannon3.png";
	public static final String CANNON4_IMG = "cannon4.png";
	public static final String CANNON5_IMG = "cannon5.png";
	public static final String EASY_IMG = "easy.png";
	public static final String EFFECTS_IMG = "effects.png";
	public static final String EXIT_IMG = "exit.png";
	public static final String GAME_BACKGROUND_IMG = "game_background.png";
	public static final String HARD_IMG = "hard.png";
	public static final String HIGHSCORE_IMG = "highscore.png";
	public static final String HOVER_IMG = "menu_hover.png";
	public static final String HOVER_SMALL_IMG = "hover_small.png";
	public static final String MAIN_MENU_IMG = "main_menu.png";
	public static final String MUSIC_IMG = "music.png";
	public static final String NEW_GAME_IMG = "new_game.png";
	public static final String POPUP_IMG = "popup.png";
	public static final String PRESS_ANY_KEY_IMG = "press_any_key.png";
	public static final String QUIT_IMG = "quit.png";
	public static final String RESUME_IMG = "resume.png";
	public static final String SETTINGS_IMG = "settings.png";
	public static final String SLIDE_PIN_IMG = "slide_pin.png";
	public static final String SLIDE_PIN_HOVER_IMG = "slide_pin_hover.png";
	public static final String SLIDER_IMG = "slider.png";
	public static final String SOUND_IMG = "sound.png";
	public static final String START_GAME_IMG = "start_game.png";
	public static final String TETRIX_LOGO_IMG = "tetrix_logo.png";
	public static final String LOCKED_BLOCK_IMG = "block/locked.png";
	public static final String BLUE_BLOCK_IMG = "block/blue.png";
	public static final String GREEN_BLOCK_IMG = "block/green.png";
	public static final String ORANGE_BLOCK_IMG = "block/orange.png";
	public static final String PINK_BLOCK_IMG = "block/pink.png";
	public static final String PURPLE_BLOCK_IMG = "block/purple.png";
	public static final String RED_BLOCK_IMG = "block/red.png";
	public static final String TURQUOISE_BLOCK_IMG = "block/turquoise.png";
	public static final String YELLOW_BLOCK_IMG = "block/yellow.png";

	
	
	public void setCannon(int c){
		;
	}
	
	public static void setUnderworldTheme(){
		theme = "img/underworld/";
	}
	
	public static void setOverworldTheme(){
		theme = "img/overworld/";
	}
	
	public static Image get(String img) throws SlickException{
		return new Image(theme + img);
	}
	
	
}
