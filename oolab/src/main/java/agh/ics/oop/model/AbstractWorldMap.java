package agh.ics.oop.model;

import agh.ics.oop.World;
import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;

public abstract class AbstractWorldMap extends Observable implements WorldMap {
    protected Map<Vector2d, Animal> animals = new HashMap<>();
    protected List<MapChangeListener> observers = new ArrayList<>();

    public void registerObserver(MapChangeListener listener) {
        observers.add(listener);
    }
    public void unregisterObserver(MapChangeListener listener) {
        observers.remove(listener);
    }
    public void mapChanged(String message) {
        for (MapChangeListener listener : observers) {
            listener.mapChanged(this, message);
        }
    }

    @Override
    public void place(Animal animal) throws IncorrectPositionException {
        if(!canMoveTo(animal.getPosition())) throw new IncorrectPositionException(animal.getPosition());
        animals.put(animal.getPosition(), animal);
        mapChanged("Placed animal at " + animal.getPosition());
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
            Vector2d oldPosition = animal.getPosition();
            animals.remove(animal.getPosition());
            animal.move(direction, this);
            animals.put(animal.getPosition(), animal);

            if(direction == MoveDirection.RIGHT || direction == MoveDirection.LEFT) mapChanged("Turned animal " + direction + " at " + animal.getPosition());
            else if(oldPosition == animal.getPosition()) mapChanged("Failed to move animal " + direction + " at " + animal.getPosition());
            else mapChanged("Moved animal " + direction + " from " + oldPosition + " to " + animal.getPosition());
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        return animals.get(position);
    }

    @Override
    public List<WorldElement> getElements() {
        return new ArrayList<>(animals.values());
    }

    @Override
    public String toString() {
        return new MapVisualizer(this).draw(getCurrentBounds().downLeft(), getCurrentBounds().upperRight());
    }
}
