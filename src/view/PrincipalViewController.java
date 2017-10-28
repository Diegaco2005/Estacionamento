package view;

import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

import Main.MainApp;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Login;
import model.Movimento;
import model.Parametros;
import model.Veiculo;

public class PrincipalViewController {
	@FXML
    private Label login;
    @FXML
    private Label vagas;
    @FXML
    private TextField placaField;

    // Reference to the main application.
    private MainApp mainApp;

    public PrincipalViewController(){
    	 //firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
         //lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
    }


    public void showDetails(Login login, String vagas){
    	if(login != null){
    		this.login.setText(login.getlogin());
    		this.vagas.setText(vagas);
    	}else{
    		this.login.setText("1~");
    		this.vagas.setText("0");

    	}
    }



    @FXML
    private void initialize(){
    	showDetails(null, null);


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
    private void handleAddEMovimento() {

    	Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Nenhuma seleção");
        alert.setHeaderText("Nenhuma Pessoa Selecionada");
        alert.setContentText("Por favor, selecione uma pessoa na tabela.");

        alert.showAndWait();

    }
    @FXML
    private void handleMovimento() throws SQLException {
    	//GET PLACA DO TEXT FIELD
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

        		if((placaConsulta == placaMovimento) && (movimento.verificarSaidaPendente())){
        			controleEntrada = false;
        			movimentoConsulta = movimento;
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
	        		System.out.print(LocalDateTime.now());
		        }

        	}else{
        		//SAINDO - ADD HORA DE SAIDA
        		movimentoConsulta.setSaida(LocalDateTime.now());
        		//mainApp.getMovimentoData().add(movimentoConsulta);
        		Duration duracao = Duration.between(movimentoConsulta.getEntra(), movimentoConsulta.getSaida());
        		System.out.println(duracao.toHours());
        		Double valorContabilizado = 0.01;
        		Parametros parametros = mainApp.getParametros();
        		valorContabilizado = parametros.getValorEntrada();
        		valorContabilizado += (parametros.getValorHora()*duracao.toHours());
        		movimentoConsulta.setValor(valorContabilizado);
        		boolean okClicked = mainApp.showConfirmeMovimentoDialogController(movimentoConsulta);
		        if (okClicked) {

	        		//System.out.print(LocalDateTime.now());
	        		movimentoConsulta.salveSaida();
	        		System.out.println("SAIU");
		        }else{
		        	movimentoConsulta.setSaida(LocalDateTime.of(1800, 1, 12, 0, 0));
		        	movimentoConsulta.setValor(0.00);
		        }

        	}

    	}

    }
/*
    @FXML
    private void handleEditPerson() {
    	Veiculo selectedVeiculo = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                showPersonDetails(selectedPerson);
            }

        } else {
            // Nada seleciondo.
            Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Nenhuma seleção");
                alert.setHeaderText("Nenhuma Pessoa Selecionada");
                alert.setContentText("Por favor, selecione uma pessoa na tabela.");
                alert.showAndWait();
        }
    }*/
}
