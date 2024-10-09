package imani.game;

import org.apache.commons.io.IOUtils;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RleImporter {

    private static Grid grid = new Grid(100, 100);

    public static void importFromClipboard() throws UnsupportedFlavorException, IOException {
        String clipboardText
                = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);

        if (clipboardText == null || clipboardText.isEmpty()) {
            throw new IOException("Clipboard is empty or does not contain valid data.");
        }

        if (clipboardText.startsWith("http://") || clipboardText.startsWith("https://")) {
            importRleFromUrl(clipboardText);
        } else if (new File(clipboardText).exists()) {
            importRleFromFile(clipboardText);
        } else {
            importRleFromText(clipboardText);
        }
    }

    private static void importRleFromUrl(String urlString) throws IOException {
        String rleData = getRleFromURL(urlString);
        applyRleToGrid(rleData);
    }

    private static void importRleFromFile(String filePath) throws IOException {
        String rleData = getRleFromFile(filePath);
        applyRleToGrid(rleData);
    }

    private static void importRleFromText(String rleText) {
        applyRleToGrid(rleText);
    }

    private static String getRleFromURL(String urlString) throws IOException {
        try (InputStream in = new URL(urlString).openStream()) {
            return IOUtils.toString(in, "UTF-8");
        }
    }

    private static String getRleFromFile(String filePath) throws IOException {
        try (FileInputStream fis = new FileInputStream(new File(filePath))) {
            return IOUtils.toString(fis, "UTF-8");
        }
    }

    private static void applyRleToGrid(String rle) {
        grid = parseRle(rle);
    }

    public static Grid parseRle(String rle) {
        String[] lines = rle.split("\n");

        int patternWidth = 0;
        int patternHeight = 0;

        for(String line: lines) {
            if (line.startsWith("x")) {
                Pattern pattern = Pattern.compile("x = (\\d+), y = (\\d+), rule = (.+)");
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    patternWidth = Integer.parseInt(matcher.group(1));
                    patternHeight = Integer.parseInt(matcher.group(2));
                }
               break;
            }
        }

        int offsetX = 50 - patternWidth / 2;
        int offsetY = 50 - patternHeight / 2;

        int x = 0;
        int y = 0;

        for (String line : lines) {
            if (line.startsWith("#") || line.startsWith("x")) {
                continue;
            }

            StringBuilder num = new StringBuilder();
            for (char character : line.toCharArray()) {
                if (Character.isDigit(character)) {
                    num.append(character);
                } else {
                    int count = num.length() > 0 ? Integer.parseInt(num.toString()) : 1;
                    num.setLength(0);

                    switch (character) {
                        default:
                            throw new IllegalArgumentException("Unexpected character: " + character);
                        case 'b':
                            x += count;
                            break;
                        case 'o':
                            for (int i = 0; i < count; i++) {
                                int newX = x + offsetX;
                                int newY = y + offsetY;
                                if (newX < grid.getGameBoard()[0].length && newY < grid.getGameBoard().length) {
                                    grid.enliven(newX, newY);
                                    x++;
                                }
                            }
                            break;
                        case '$':
                            y++;
                            x = 0;
                            break;
                        case '!':
                            return grid;
                    }
                }
            }

        }

        return grid;
    }

    public static Grid getGrid() {
        return grid;
    }

}