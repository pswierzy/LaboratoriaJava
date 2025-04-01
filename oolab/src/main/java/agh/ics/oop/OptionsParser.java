package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class OptionsParser {

    public static List<MoveDirection> parse(String[] arg){

        List<MoveDirection> moves = new ArrayList<MoveDirection>(arg.length);

        for (String s : arg) {
            moves.add(switch (s) {
                    case "f" -> MoveDirection.FORWARD;
                    case "b" -> MoveDirection.BACKWARD;
                    case "l" -> MoveDirection.LEFT;
                    case "r" -> MoveDirection.RIGHT;
                    default -> throw new IllegalArgumentException(s + " is not a legal move specification!");
            });
        }
        return moves;
    }
}
