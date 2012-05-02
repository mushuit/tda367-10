package tetrix.core;

public class ThemeHandler {

	private static String theme = "img/";
	
	public static void setNormalTheme() {
		theme = "img/";
	}
	
	public static void setUndergroundTheme() {
		theme = "img/underground/";
	}
	
	public static String getTheme() {
		return theme;
	}
}
