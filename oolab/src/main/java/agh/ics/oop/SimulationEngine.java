package agh.ics.oop;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimulationEngine {
    List<Simulation> simulations;
    LinkedList<Thread> threads = new LinkedList<>();
    ExecutorService executorService = Executors.newFixedThreadPool(4);

    public SimulationEngine(List<Simulation> simulations) {
        this.simulations = simulations;
    }

    public void runSync(){
        for(Simulation simulation : simulations){
            simulation.run();
        }
    }

    public void runAsync(){
        for(Simulation simulation : simulations){
            Thread thread = new Thread(simulation);
            threads.addFirst(thread);
            thread.start();
        }
    }

    public void runAsyncInThreadPool(){
        for(Simulation simulation : simulations){
            executorService.submit(simulation);
        }
        executorService.shutdown();
    }

    public void awaitSimulationsEnd(){
        for(Thread thread : threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
