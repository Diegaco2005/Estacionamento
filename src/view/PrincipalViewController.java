package view;


import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDateTime;

import Main.MainApp;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Login;
import model.Movimento;
import model.Parametros;
import model.Veiculo;
import util.MaskTextField;

public class PrincipalViewController {
	@FXML
    private Label loginLb;
    @FXML
    private Label vagas;
    @FXML
    private Label vrHora;
    @FXML
    private Label vrHoraAdicional;
    @FXML
    private MaskTextField placaField;

    // Reference to the main application.
    private MainApp mainApp;

    public PrincipalViewController(){
    }


    public void showDetails(){
    	Login login = mainApp.getLogin();
    	Parametros parametros = mainApp.getParametros();
    	Integer vagasOcupadas = mainApp.getVagasOcup();
    	if(login != null && parametros != null){
    		DecimalFormat df = new java.text.DecimalFormat("#,###,##0.00");
    		this.loginLb.setText(login.getlogin());
    		Integer vagasSobrando = parametros.getVagas() - vagasOcupadas;
    		if(vagasSobrando > 0){
    			this.vagas.setText(vagasSobrando.toString());
    		}else{
    			this.vagas.setText("LOTADO");
    		}

    		this.vrHora.setText("R$ "+df.format(parametros.getValorEntrada()));
    		this.vrHoraAdicional.setText("R$ "+df.format(parametros.getValorHora()));
    	}else{
    		this.loginLb.setText("~");
    		this.vagas.setText("0");

    	}
    }



    @FXML
    private void initialize(){
    	placaField.setMask("LLL-NNNN");
    	placaField.textProperty().addListener((ov, oldValue, newValue) -> {
    		placaField.setText(newValue.toUpperCase());
    	});
    }

    /**
     * É chamado pela aplicação principal para dar uma referência de volta a si mesmo.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;


    }

    @FXML
    private void handleMovimento() throws SQLException {
    	//GET PLACA DO TEXT FIELD

    	if(isInputValid()){
    		String placaConsulta = placaField.getText();
    		Veiculo veiculoConsulta = new Veiculo();
        	//VERIFICA CARRO CADASTRADO
        	ObservableList<Veiculo> veiculoData = mainApp.getVeiculoData();
        	boolean controleCadastrado = false;
        	//VERIFICA SE O VEICULO ESTA CADASTRADO
        	for(Veiculo veiculo: veiculoData){
        		if(placaConsulta.compareTo(veiculo.getPlaca()) == 0){
        			controleCadastrado = true;
        			veiculoConsulta = veiculo;
        		}
        	}

        	if(!controleCadastrado){
        		//CADASTRA NOVO VEICULO
        		Veiculo tempVeiculo = new Veiculo(placaConsulta);
                boolean okClicked = mainApp.showVeiculoAddDialog(tempVeiculo);
                if (okClicked) {
                	mainApp.getVeiculoData().add(tempVeiculo);
                	tempVeiculo.salve();
                	veiculoConsulta = tempVeiculo;
                }else{
                	veiculoConsulta = null;
                }
        	}
        	if(veiculoConsulta != null){
        		//VERIFICA ENTRADA OU SAIDA
            	ObservableList<Movimento> movimentoData = mainApp.getMovimentoData();
            	boolean controleEntrada = true;
            	placaConsulta = veiculoConsulta.getPlaca();
            	Movimento movimentoConsulta = new Movimento();
            	for(Movimento movimento: movimentoData){
            		String placaMovimento = movimento.getVeiculo().getPlaca();
            		//VERIFICA ENTRADA OU SAIDA
            		//System.out.println(movimento.verificarSaidaPendente());
            		if(placaMovimento.equals(placaConsulta)){
            			if(movimento.verificarSaidaPendente()){
            				controleEntrada = false;
                			movimentoConsulta = movimento;
            			}

            		}
            	}


            	//CADASTRA ENTRADA OU SAIDA
        		if(controleEntrada){
            		//ENTRANDO - ADD MOVIMENTO
           			movimentoConsulta.setVeiculo(veiculoConsulta);
    		   		boolean okClicked = mainApp.showConfirmeMovimentoDialogController(movimentoConsulta);
    		        if (okClicked){
    	        		mainApp.getMovimentoData().add(movimentoConsulta);
    	        		movimentoConsulta.salveEntrada();
    	        		Integer vagasOcup = mainApp.getVagasOcup();
    	        		vagasOcup++;
    	        		mainApp.setVagasOcup(vagasOcup);
    	        		showDetails();
    	        		//System.out.print(LocalDateTime.now());
    		        }
            	}else{
            		//SAINDO - ADD HORA DE SAIDA
            		movimentoConsulta.setSaida(LocalDateTime.now());
            		//mainApp.getMovimentoData().add(movimentoConsulta);
            		Duration duracao = movimentoConsulta.verificaHoras();
            		//System.out.println(duracao.toHours());
            		Double valorContabilizado = 0.01;
            		Parametros parametros = mainApp.getParametros();
            		valorContabilizado = parametros.getValorEntrada();
            		valorContabilizado += (parametros.getValorHora()*duracao.toHours());
            		movimentoConsulta.setValor(valorContabilizado);
            		boolean okClicked = mainApp.showConfirmeMovimentoDialogController(movimentoConsulta);
    		        if (okClicked) {

    	        		//System.out.print(LocalDateTime.now());
    	        		movimentoConsulta.salveSaida();
    	        		Integer vagasOcup = mainApp.getVagasOcup();
    	        		vagasOcup--;
    	        		mainApp.setVagasOcup(vagasOcup);
    	        		showDetails();
    	        		//System.out.println("SAIU");
    		        }else{
    		        	movimentoConsulta.setSaida(movimentoConsulta.getEntra());
    		        	movimentoConsulta.setValor(0.00);
    		        }

            	}

        	}
    	}


    }
    private boolean isInputValid() {
    	String errorMessage = "";
        if (placaField.getText() == null || placaField.getText().length() == 0) {
            errorMessage += "Placa é obrigatoria!\n";
        }
        if(placaField.getLength() < 8 ){
        	errorMessage += "Placa inválido!\n";
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
    @FXML
    private void handleOpcoes() throws SQLException{

        boolean okClicked = mainApp.showOpcoesView();
        if (okClicked) {
        	Parametros parametros = mainApp.getParametros();
        	try {
				parametros.salva();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	Login login = mainApp.getLogin();
        	showDetails();
        	// Mostra a mensagem de erro.
            Alert alert = new Alert(AlertType.CONFIRMATION);
              alert.setTitle("Sucesso!");
              alert.setHeaderText("Valores alterado com sucesso");
              alert.setContentText("As alterações foram salvas!");
            alert.showAndWait();
        }
    }
    @FXML
    private void handleSair(){
    	mainApp.getPrimaryStage().close();

    }

}
