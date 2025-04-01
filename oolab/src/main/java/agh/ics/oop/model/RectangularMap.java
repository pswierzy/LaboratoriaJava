package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap extends AbstractWorldMap{

    private final int height;
    private final int width;

    public RectangularMap(int height, int width) {
        this.height = height;
        this.width = width;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if (isOccupied(position)) return false;
        int x = position.getX();
        int y = position.getY();
        return (x<width && x>=0 && y<height && y>=0);
    }

    @Override
    public Boundary getCurrentBounds(){
        return new Boundary(new Vector2d(0,0), new Vector2d(width-1,height-1));
    }

}
