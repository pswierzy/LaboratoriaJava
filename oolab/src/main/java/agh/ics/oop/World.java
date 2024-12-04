package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.Arrays;
import java.util.List;

public class World {
    public static void main(String[] args) {

        List<MoveDirection> directions = OptionsParser.parse(args);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        WorldMap map = new GrassField(10);
        System.out.println(map);
    }
}
