package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class World {
    public static void main(String[] args) {

        List<MoveDirection> directions = OptionsParser.parse(args);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(2,3));
        ConsoleMapDisplay observer = new ConsoleMapDisplay();

        List<Simulation> SimList = new ArrayList<>();
        for(int i=0;i<5000;i++){
            WorldMap map = new RectangularMap(5, 5);
            map.registerObserver(observer);
            Simulation sim = new Simulation(positions, directions, map);
            SimList.add(sim);
        }
        SimulationEngine SimEng = new SimulationEngine(SimList);
        SimEng.runAsyncInThreadPool();
        SimEng.awaitSimulationsEnd();

        System.out.println("KONIEC DZIALANIA SYSTEMU");
    }
}
