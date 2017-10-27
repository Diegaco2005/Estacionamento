package view;

import Main.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Movimento;
import model.Veiculo;

public class ConfirmeMovimentoDialogController {
	@FXML
    private Label entradaLabel;
    @FXML
    private Label saidaLabel;
    @FXML
    private Label placaLabel;
    @FXML
    private Label marcaLabel;
    @FXML
    private Label modelLabel;
    @FXML
    private Label corLabel;
    @FXML
    private Label valorLabel;


    private Stage dialogStage;
	private Movimento movimento;
	private boolean okClicked = false;



    public ConfirmeMovimentoDialogController(){

    }
    @FXML
	private void initialize(){
	}public void setDialogStage(Stage dialogStage){
		this.dialogStage = dialogStage;
	}
	public void setMovimento(Movimento movimento){
		this.movimento = movimento;
		//placaField.setText(movimento.getPlaca());

	}
	public boolean isOkClicked(){
		return okClicked;
	}

	@FXML
	private void handleOk(){


            okClicked = true;
            dialogStage.close();


	}
	@FXML
    private void handleCancel() {
        dialogStage.close();
    }
}
