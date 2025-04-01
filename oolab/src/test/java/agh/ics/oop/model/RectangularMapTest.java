package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {

    @Test
    void placeTest(){
        //given
        WorldMap map = new RectangularMap(5,5);
        Animal animal1 = new Animal();
        Animal animal2 = new Animal();
        boolean place1 = false;
        boolean place2 = false;
        //when
        try{
            map.place(animal1);
        }
        catch (IncorrectPositionException e) {
            place1 = true;
        }
        try{map.place(animal2);}
        catch (IncorrectPositionException e) {
            place2 = true;
        }
        assertFalse(place1);
        assertTrue(place2);
    }

    @Test
    void movingTest(){
        //given
        WorldMap map = new RectangularMap(5,5);
        Animal animal1 = new Animal();
        try{map.place(animal1);}
        catch (IncorrectPositionException e) {
            throw new RuntimeException("Blad w testach " + e);
        }
        //when
        map.move(animal1, MoveDirection.FORWARD);
        //then
        assertEquals(animal1.getPosition(),new Vector2d(2,3));
        assertEquals(map.objectAt(animal1.getPosition()), animal1);
        assertTrue(map.objectAt(animal1.getPosition()) == animal1);
        assertFalse(map.isOccupied(new Vector2d(2,2)));
        assertTrue(map.isOccupied(new Vector2d(2,3)));
        assertTrue(map.canMoveTo(new Vector2d(2,2)));
        assertFalse(map.canMoveTo(new Vector2d(2,3)));
    }

    @Test
    void objectAtTest(){
        //given
        WorldMap map = new RectangularMap(5,5);
        Animal animal1 = new Animal(new Vector2d(2,3));
        Animal animal2 = new Animal(new Vector2d(4,4));
        //when
        try {
            map.place(animal1);
            map.place(animal2);
        }
        catch (IncorrectPositionException e) {
            throw new RuntimeException("Blad w testach " + e);
        }
        //then
        assertTrue(map.objectAt(new Vector2d(2,3)) == animal1);
        assertTrue(map.objectAt(new Vector2d(4,4)) == animal2);
        assertTrue(map.objectAt(new Vector2d(3,3)) == null);
    }

    @Test
    void canMoveToTest(){
        //given
        WorldMap map = new RectangularMap(5,5);
        Animal animal1 = new Animal();
        try{map.place(animal1);}
        catch (IncorrectPositionException e) {
            throw new RuntimeException("Blad w testach " + e);
        }
        //then
        assertTrue(map.canMoveTo(new Vector2d(2,3)));
        assertFalse(map.canMoveTo(new Vector2d(2,2)));
        assertTrue(map.canMoveTo(new Vector2d(0,0)));
        assertFalse(map.canMoveTo(new Vector2d(-1,0)));
        assertFalse(map.canMoveTo(new Vector2d(0,-1)));
        assertFalse(map.canMoveTo(new Vector2d(-1,-1)));
        assertFalse(map.canMoveTo(new Vector2d(5,4)));
        assertFalse(map.canMoveTo(new Vector2d(5,5)));
        assertFalse(map.canMoveTo(new Vector2d(4,5)));
        assertTrue(map.canMoveTo(new Vector2d(4,4)));
    }

    @Test
    void getElementsTest(){
        //given
        WorldMap map = new RectangularMap(5,5);
        Animal animal1 = new Animal(new Vector2d(1,1));
        Animal animal2 = new Animal(new Vector2d(2,2));
        Animal animal3 = new Animal(new Vector2d(3,3));
        List<WorldElement> animals = List.of(animal1, animal2, animal3);
        //then
        assertEquals(new ArrayList<>(), map.getElements());
        //when
        try{
            map.place(animal1);
            map.place(animal2);
            map.place(animal3);
        }catch(IncorrectPositionException e){
            throw new RuntimeException("Blad w testach " + e);
        }
        //then
        assertEquals(animals, map.getElements());
        assertTrue(map.getElements().get(0) == animal1);
    }
}