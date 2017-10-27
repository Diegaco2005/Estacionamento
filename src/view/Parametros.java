/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 *
 * @author Diego
 */
public class Parametros {

    private Stage paramStage;

    @FXML
    private TextField tfVagas;

    @FXML
    private TextField tfVlBase;

    @FXML
    private TextField tfVlAdicional;

    @FXML
    private TextField tfHistorico;

    @FXML
    private Button btConfirmar;

    @FXML
    private Button btSair;

    @FXML
    private void handleButtonConfirmar(ActionEvent event) {

    }

    @FXML
    private void handleButtonSair(ActionEvent event) {
        paramStage.close();
    }

    public void setStage(Stage paramStage) {
        this.paramStage = paramStage;
    }

}
