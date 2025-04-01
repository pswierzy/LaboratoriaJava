package agh.ics.oop.model;

public class IncorrectPositionException extends Exception {
    public IncorrectPositionException(Vector2d vector2d) {
        super("Position " + vector2d + " is not correct!");
    }
}