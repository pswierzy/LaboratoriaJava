package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.model.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;

import java.util.List;

import static javafx.geometry.Pos.CENTER;

public class SimulationPresenter implements MapChangeListener{

    WorldMap worldMap;
    @FXML
    private TextField moves;
    @FXML
    private Label moveDescription;
    @FXML
    private GridPane mapGrid;

    public void setWorldMap(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    private void clearGrid() {
        mapGrid.getChildren().retainAll(mapGrid.getChildren().getFirst()); // hack to retain visible grid lines
        mapGrid.getColumnConstraints().clear();
        mapGrid.getRowConstraints().clear();
    }
    public void drawMap() {
        clearGrid();

        Boundary boundary = worldMap.getCurrentBounds();

        Label cornerLabel = new Label("y/x");
        cornerLabel.setStyle("-fx-alignment: center;");
        mapGrid.add(cornerLabel, 0, 0);

        for (int i = 0; i <= boundary.upperRight().getX() - boundary.downLeft().getX(); i++) {
            Label xLabel = new Label("" + (boundary.downLeft().getX() + i));
            xLabel.setFont(Font.font(14));
            xLabel.setStyle("-fx-alignment: center;");
            mapGrid.add(xLabel, i+1, 0);
        }

        for (int i = 0; i <= boundary.upperRight().getY() - boundary.downLeft().getY(); i++) {
            Label yLabel = new Label("" + (boundary.upperRight().getY() - i));
            yLabel.setFont(Font.font(14));
            yLabel.setStyle("-fx-alignment: center;");
            mapGrid.add(yLabel, 0, i+1);
        }

        for(WorldElement element: worldMap.getElements()){
            Vector2d position = element.getPosition();
            Label inLabel = new Label(element.toString());
            inLabel.setFont(Font.font(30));
            inLabel.setStyle("-fx-alignment: center;");
            mapGrid.add(inLabel, position.getX()-boundary.downLeft().getX()+1, -position.getY()+boundary.upperRight().getY()+1);
        }

        double CELL_WIDTH = 30;
        double CELL_HEIGHT = 30;

        mapGrid.getColumnConstraints().clear();
        mapGrid.getRowConstraints().clear();

        for (int i = 0; i <= boundary.upperRight().getX() - boundary.downLeft().getX()+1; i++) {
            ColumnConstraints colConstraints = new ColumnConstraints(CELL_WIDTH);
            colConstraints.setHalignment(HPos.CENTER);
            mapGrid.getColumnConstraints().add(colConstraints);
        }

        for (int i = 0; i <= boundary.upperRight().getY() - boundary.downLeft().getY()+1; i++) {
            RowConstraints rowConstraints = new RowConstraints(CELL_HEIGHT);
            rowConstraints.setValignment(VPos.CENTER);
            mapGrid.getRowConstraints().add(rowConstraints);
        }

        mapGrid.setAlignment(CENTER);
    }


    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        Platform.runLater(() -> {
            drawMap();
            moveDescription.setText(message);
        });
    }

    public void onSimulationStartClicked(ActionEvent actionEvent) {
        List<MoveDirection> directions = OptionsParser.parse(moves.getText().split(" "));

        Simulation simulation = new Simulation(directions, worldMap);
        SimulationEngine simulationEngine = new SimulationEngine(List.of(simulation));
        simulationEngine.runAsync();
    }
}
