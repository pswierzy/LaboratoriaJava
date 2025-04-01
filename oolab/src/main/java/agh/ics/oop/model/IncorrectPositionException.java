package agh.ics.oop.model;

public class IncorrectPositionException extends Exception {
    public IncorrectPositionException(Vector2d vector2d) {
        super("You can't put an animal at " + vector2d);
    }
}