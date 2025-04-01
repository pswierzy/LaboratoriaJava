package agh.ics.oop.model;

import java.util.Iterator;
import java.util.Random;

public class RandomPositionGenerator implements Iterable<Vector2d>, Iterator<Vector2d> {
    private final int maxX;
    private final int maxY;
    private int amountOfFreePositions;
    private boolean[][] used;
    private final int count;
    private int done;

    public RandomPositionGenerator(int maxX, int maxY, int count) {
        this.maxX = maxX;
        this.maxY = maxY;
        this.count = count;
        amountOfFreePositions = (maxX+1) * (maxY+1);
        done = 0;
        used = new boolean[maxX+1][maxY+1];
    }

    @Override
    public Iterator<Vector2d> iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        return done<count;
    }

    @Override
    public Vector2d next() {
        Random rand = new Random();
        int position = rand.nextInt(amountOfFreePositions);
        amountOfFreePositions--;
        done++;
        int counter = 0;
        for (int y = 0; y <= maxY; y++) {
            for (int x = 0; x <= maxX; x++) {
                if (counter == position && !used[x][y])
                {
                    used[x][y] = true;
                    return new Vector2d(x, y);
                }
                if (!used[x][y]) counter++;
            }
        }
        return null;
    }
}
