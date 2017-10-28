/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.time.LocalDateTime;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 *
 * @author Diego
 */
public class ParametrosViewController {

    private Stage paramStage;

    @FXML
    protected TextField vagas;

    @FXML
    protected TextField vlBase;

    @FXML
    protected TextField vlAdicional;

    @FXML
    protected TextField historico;

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

    /**
     * @return the vagas
     */
    public Integer getVagas() {
        return Integer.parseInt(vagas.toString());
    }

    /**
     * @param tfVagas the vagas to set
     */
    public void setVagas(Integer vagas) {
        this.vagas.setText(vagas.toString());
    }

    /**
     * @return the vlBase
     */
    public Double getVlBase() {
        return Double.parseDouble(vlBase.toString());
    }

    /**
     * @param tfVlBase the vlBase to set
     */
    public void setVlBase(Double vlBase) {
        this.vlBase.setText(vlBase.toString());
    }

    /**
     * @return the vlAdicional
     */
    public Double getVlAdicional() {
        return Double.parseDouble(vlAdicional.toString());
    }

    /**
     * @param tfVlAdicional the vlAdicional to set
     */
    public void setVlAdicional(Double vlAdicional) {
        this.vlAdicional.setText(vlAdicional.toString());
    }

    /**
     * @return the historico
     */
    public String getHistorico() {
        return historico.toString();
    }

    /**
     * @param tfHistorico the historico to set
     */
    public void setHistorico(LocalDateTime historico) {
        this.historico.setText(historico.toString());
    }

}
