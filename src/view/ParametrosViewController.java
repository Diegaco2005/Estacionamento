/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Movimento;
import model.Parametros;
import util.MaskTextField;

/**
 *
 * @author Diego
 */
public class ParametrosViewController {

    private Stage paramStage;

    @FXML
    protected MaskTextField vagas;

    @FXML
    protected MaskTextField vlBase;

    @FXML
    protected MaskTextField vlAdicional;


    @FXML
    private Button btConfirmar;

    @FXML
    private Button btSair;

    private Stage dialogStage;
    private Parametros parametros;
    private boolean okClicked = false;

    @FXML
   	private void initialize(){
    	vagas.setMask("N!");
    	//vlBase.setMask("N!.N!");
    	//vlAdicional.setMask("N!,NN");
   	}
    public ParametrosViewController(){
    }
    public void setParametros(Parametros parametros){
    	DecimalFormat df = new java.text.DecimalFormat("#,###,##0.00");
		this.parametros = parametros;
		vagas.setText(parametros.getVagas().toString());
		vlBase.setText(df.format(parametros.getValorEntrada()));
		vlAdicional.setText(df.format(parametros.getValorHora()));
	}
	public boolean isOkClicked(){
		return okClicked;
	}
	public void setDialogStage(Stage dialogStage){
		this.dialogStage = dialogStage;
	}

    @FXML
    private void handleButtonConfirmar(ActionEvent event) {
    	if (isInputValid()) {
    		this.parametros.setVagas(Integer.parseInt(vagas.getText()));
    		this.parametros.setValorEntrada(Double.parseDouble(vlBase.getText().replace(",", ".")));
    		this.parametros.setValorHora(Double.parseDouble(vlAdicional.getText().replace(",", ".")));


            okClicked = true;
            dialogStage.close();
        }

    }

    @FXML
    private void handleButtonSair(ActionEvent event) {
    	dialogStage.close();
    }

    private boolean isInputValid() {
    	String errorMessage = "";
        if (vagas.getText() == null || vagas.getText().length() == 0) {
            errorMessage += "Numero de vagas é obrigatoria!\n";
        }
        if (vlBase.getText() == null || vlBase.getText().length() == 0) {
            errorMessage += "Valor base é obrigatorio!\n";
        }
        if (vlAdicional.getText() == null || vlAdicional.getText().length() == 0) {
            errorMessage += "Valor da hora é obrigatorio!\n";
        }


        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Mostra a mensagem de erro.
            Alert alert = new Alert(AlertType.ERROR);
                      alert.setTitle("Campos Inválidos");
                      alert.setHeaderText("Por favor, corrija os campos inválidos");
                      alert.setContentText(errorMessage);
                alert.showAndWait();

            return false;
        }
	 }

}
