package agh.ics.oop;

import agh.ics.oop.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimulationTest {

    @Test
    void isOrientationGood(){
        //given
        WorldMap map = new RectangularMap(5, 5);
        Animal animal1 = new Animal(new Vector2d(1,1));
        Animal animal2 = new Animal(new Vector2d(2,2));
        Animal animal3 = new Animal(new Vector2d(3,3));
        String[] array = {"f","r","l",  "f","r","l",  "f","r","l",  "f","r","b"};
        List<MoveDirection> directions = OptionsParser.parse(array);
        List<Animal> animals = new ArrayList<>(Arrays.asList(animal1, animal2, animal3));
        //when
        Simulation simulation = new Simulation(directions, animals);
        simulation.run();
        //then
        assertEquals(animal1.getDirection(), MapDirection.NORTH);
        assertEquals(animal2.getDirection(), MapDirection.NORTH);
        assertEquals(animal3.getDirection(), MapDirection.EAST);
    }

    @Test
    void isMovingGood(){
        //given
        WorldMap map = new RectangularMap(5, 5);
        Animal animal1 = new Animal(new Vector2d(1,1));
        Animal animal2 = new Animal(new Vector2d(2,2));
        Animal animal3 = new Animal(new Vector2d(3,3));
        String[] array = {"f","r","r",  "r","r","f",  "b","r","f",  "f","r","l"};
        List<MoveDirection> directions = OptionsParser.parse(array);
        List<Animal> animals = new ArrayList<>(Arrays.asList(animal1, animal2, animal3));
        //when
        Simulation simulation = new Simulation(directions, animals);
        simulation.run();
        //then
        assertEquals(animal1.getPosition(), new Vector2d(1,2));
        assertEquals(animal2.getPosition(), new Vector2d(2,2));
        assertEquals(animal3.getPosition(), new Vector2d(4,3));
    }

    @Test
    void staysInMap(){
        //given
        WorldMap map = new RectangularMap(5, 5);
        Animal animal1 = new Animal(new Vector2d(1,1));
        Animal animal2 = new Animal(new Vector2d(2,2));
        Animal animal3 = new Animal(new Vector2d(3,3));
        Animal animal4 = new Animal(new Vector2d(4,4));
        String[] array = {"l","b","r","f",  "f","b","f","f",  "f","b","f","f",  "f","b","f","f", "f","b","f","f"};
        List<MoveDirection> directions = OptionsParser.parse(array);
        List<Animal> animals = new ArrayList<>(Arrays.asList(animal1, animal2, animal3, animal4));
        //when
        Simulation simulation = new Simulation(directions, animals);
        simulation.run();
        //then
        assertEquals(animal1.getPosition(), new Vector2d(0,1));
        assertEquals(animal2.getPosition(), new Vector2d(2,0));
        assertEquals(animal3.getPosition(), new Vector2d(4,3));
        assertEquals(animal4.getPosition(), new Vector2d(4,4));
    }

    @Test
    void doesCollisionWork(){
        //given
        WorldMap map = new RectangularMap(5, 5);
        Animal animal1 = new Animal(new Vector2d(2,1));
        Animal animal2 = new Animal(new Vector2d(2,3));
        String[] array = {"f","b","f","b",  "r","r","r","r",  "f","b","f","b", "r", "f", "r", "f", "f", "f"};
        List<MoveDirection> directions = OptionsParser.parse(array);
        List<Animal> animals = new ArrayList<>(Arrays.asList(animal1, animal2));
        //when
        Simulation simulation = new Simulation(directions, animals);
        simulation.run();
        //then
        assertEquals(animal1.getPosition(), new Vector2d(2,1));
        assertEquals(animal2.getPosition(), new Vector2d(2,2));
    }


}