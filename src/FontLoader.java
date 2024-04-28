import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class FontLoader {
    private static final Map<String, Font> fontMap = new HashMap<>();

    static {
        // Define your font styles here
        fontMap.put("Primary", loadFont("/Font/Poppins-ExtraBold.ttf", 64));
        fontMap.put("Secondary", loadFont("/Font/Poppins-Medium.ttf", 24));
        fontMap.put("PrimaryEB32", loadFont("/Font/Poppins-ExtraBold.ttf", 32));
        fontMap.put("SemiB", loadFont("/Font/Poppins-SemiBold.ttf", 24));
        fontMap.put("Bold", loadFont("/Font/Poppins-Bold.ttf", 42));
        fontMap.put("Bold16", loadFont("/Font/Poppins-Bold.ttf", 16));
        fontMap.put("ExtraBold", loadFont("/Font/Poppins-ExtraBold.ttf", 24));
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

    private static Font loadFont(String fontFilePath, float Fontize) {
        try (InputStream is = FontLoader.class.getResourceAsStream(fontFilePath)) {
            if (is == null) {
                throw new IOException("Font file not found: " + fontFilePath);
            }
            return Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Fontize);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            return null;
        }
    }
}