package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import javax.swing.text.Position;
import java.awt.*;
import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 30;
    private static final int HEIGHT = 30;
    private static final long SEED = 83298;
    private static final Random RANDOM = new Random(SEED);

    public static class Position {
        public int x; // the x-axis of position

        public int y; // the y-axis of position
        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /** Generate the hexagon in the world at position p with size of s and have TETile of t.
     *
     * @param world the world you want to generate the hexagon
     * @param p the position of the hexagon which specifies the lower left corner of the hexagon
     * @param s the size of the hexagon
     * @param t the TETile choose to display the hexagon
     */
    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {
        // The bottom part of the hexagon
        for (int i = 0; i < s; ++i) { // The line number of hexagon start at bottom of 0
            // The start position of the current line
            Position current = new Position(p.x - i, p.y + i);

            // (s + i * 2) is the length of the i line
            for (int j = 0; j < s + i * 2; ++j){
                world[current.x + j][current.y] = t;
            }
        }

        // The beginning position of another part of hexagon
        Position next = new Position(p.x - s + 1, p.y + s);

        // The upper part of the hexagon
        for (int i = 0; i < s; ++i) { // The line number of hexagon start at bottom of 0
            // The start position of the current line
            Position current = new Position(next.x + i, next.y + i);

            // (s + (s - i - 1) is the length of the i line
            for (int j = 0; j < s + (s - i - 1) * 2; ++j) {
                world[current.x + j][current.y] = t;
            }
        }
    }

    public static void main(String[] args) {
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        // paint 19 hexagons
        int size = 3;
        Position position = calculateBeginPosition(size);

        TETile t = Tileset.FLOWER;
        // The left side of the hexagon
        for (int i = 0; i < size; ++i) {
            t = changeTETileColor(t);
            drawColumnAtPosition(world, position, size, t, i);
            position = calculatePositionOfNextHexagon(position, size);
        }

        position = toRightPosition(position, size);

        // The right side of the hexagon
        for (int i = size - 1; i > 0; --i) {
            t = changeTETileColor(t);
            position = calculatePositionOfNextHexagonRight(position, size);
            drawColumnAtPosition(world, position, size, t, i - 1);
        }

        // draws the world to the screen
        ter.renderFrame(world);
    }

    private static void drawColumnAtPosition(TETile[][] world, Position position, int size, TETile t, int column) {
        TETile tDiff = null;
        TETile temp = tDiff;
        for (int i = 0; i < column + size; ++i) {
            tDiff = changeTETileStyle(t);
            if (temp == tDiff) {
                tDiff = changeTETileColor(tDiff);
            }
            Position next = new Position(position.x, position.y + (size * 2 * i));
            addHexagon(world, next, size, tDiff);
            temp = tDiff;
        }
    }

    private static TETile changeTETileStyle(TETile t) {
        int randomInt = RANDOM.nextInt(9);
        return switch (randomInt) {
            case 0 -> Tileset.WALL;
            case 1 -> Tileset.FLOWER;
            case 2 -> Tileset.MOUNTAIN;
            case 3 -> Tileset.GRASS;
            case 4 -> Tileset.PLAYER;
            case 5 -> Tileset.LOCKED_DOOR;
            case 6 -> Tileset.WATER;
            case 7 -> Tileset.TREE;
            default -> Tileset.SAND;
        };

    }

    private static Position calculateBeginPosition(int size) {
        int centerNumber = HEIGHT / (size * 2);
        int beginColumnNumber = centerNumber - size + 1;
        int Y = (beginColumnNumber - 1) * size;

        int leftNumberOfHexagon = centerNumber - size;
        int lengthOfHexagon = leftNumberOfHexagon * 2 * (2 * size - 1) + size + (size - 1) * 2;
        int leftSideLength = (WIDTH - lengthOfHexagon) / 2;
        int X = leftSideLength + size;

        return new Position(X, Y);
    }

    private static Position calculatePositionOfNextHexagon(Position current, int size) {
        int x = (current.x - 1) + size * 2;
        int y = (current.y - size);
        return new Position(x, y);
    }

    private static Position toRightPosition(Position current, int size) {
        int x = (current.x - size * 2) + 1;
        int y = (current.y + size);
        return new Position(x, y);
    }

    private static Position calculatePositionOfNextHexagonRight(Position current, int size) {
        int x = (current.x - 1) + size * 2;
        int y = (current.y + size);
        return new Position(x, y);
    }

    private static TETile changeTETileColor(TETile t) {
        // Create a slight different TETile with t
        int dr = 200, dg = 200, db = 200;
        return TETile.colorVariant(t, dr, dg, db, RANDOM);
    }
}
