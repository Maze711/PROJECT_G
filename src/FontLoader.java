import java.awt.Font;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class FontLoader {
    private static final String FONTS_DIRECTORY = "Fonts/";
    private static final Map<String, Font> fontMap = new HashMap<>();

    static {
        // Define your font styles here
        fontMap.put("Primary", loadFont("Poppins-ExtraBold.ttf", 64));
        fontMap.put("Secondary", loadFont("Poppins-Medium.ttf", 24));
        fontMap.put("PrimaryEB32", loadFont("Poppins-ExtraBold.ttf", 32));
        // Add more font styles as needed
    }

    public static Font getFont(String name, float size) {
        Font font = fontMap.get(name);
        if (font != null) {
            return font.deriveFont(size);
        } else {
            System.err.println("Font \"" + name + "\" not found!");
            return null;
        }
    }

    private static Font loadFont(String fontFileName, float fontSize) {
        try {
            File fontFile = new File(FONTS_DIRECTORY + fontFileName);
            return Font.createFont(Font.TRUETYPE_FONT, fontFile);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
