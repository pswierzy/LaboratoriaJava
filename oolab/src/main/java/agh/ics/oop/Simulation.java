package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.ArrayList;
import java.util.List;

public class Simulation implements Runnable {

    private final List<Vector2d> startPositions;
    private final List<MoveDirection> moves;
    private List<Animal> animals;
    private WorldMap map;

    public Simulation(List <Vector2d> startPositions, List <MoveDirection> moves, WorldMap map) {
        this.startPositions = startPositions;
        this.moves = moves;
        this.map = map;
        createAnimalList();

        try{
            for (Animal animal : animals) {
                map.place(animal);
            }
        }
        catch (IncorrectPositionException e) {
            throw new RuntimeException(e);
        }
    }

    //Do testów
    public Simulation(int a, List<MoveDirection> moves, List<Animal> animals) {
        this.startPositions = new ArrayList<>();
        this.moves = moves;
        this.animals = animals;
    }


    private void createAnimalList(){
        // Używam ArrayList a nie LinkedList, bo mamy stałą liczbę zwierząt i nie dodajemy nowych
        // a odnosimy się do nich dosyć często

        animals = new ArrayList<>();
        for (int i = 0; i < startPositions.size(); i++) {
           animals.add(new Animal(startPositions.get(i)));
        }
    }


    public void run(){
        int numberOfAnimals = animals.size();
        int currentAnimalIndex = 0;

        for (MoveDirection move : moves) {

            if (currentAnimalIndex == numberOfAnimals) currentAnimalIndex = 0;

            map.move(animals.get(currentAnimalIndex), move);

            currentAnimalIndex++;
        }
    }



}
