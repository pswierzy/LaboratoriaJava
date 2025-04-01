package agh.ics.oop;

import agh.ics.oop.model.*;
import agh.ics.oop.presenter.SimulationPresenter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.*;

import java.io.IOException;
import java.util.List;

public class SimulationApp extends Application {

    private void configureStage(Stage primaryStage, BorderPane viewRoot) {
        var scene = new Scene(viewRoot);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Simulation app");
        primaryStage.minWidthProperty().bind(viewRoot.minWidthProperty());
        primaryStage.minHeightProperty().bind(viewRoot.minHeightProperty());
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("simulation.fxml"));
        BorderPane viewRoot = loader.load();

        configureStage(stage, viewRoot);

        SimulationPresenter presenter = loader.getController();

        ConsoleMapDisplay observer = new ConsoleMapDisplay();

        WorldMap worldMap = new GrassField(10);
        presenter.setWorldMap(worldMap);
        worldMap.registerObserver(presenter);
        worldMap.registerObserver(observer);

        stage.show();
    }
}
