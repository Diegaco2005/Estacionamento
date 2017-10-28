package Main;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Movimento;
import model.MovimentoDAO;
import model.Parametros;
import model.ParametrosDAO;
import model.Veiculo;
import model.VeiculoDAO;
import view.ConfirmeMovimentoDialogController;
import view.PrincipalViewController;
import view.VeiculoAddDialogController;

public class MainApp extends Application {

	 private Stage primaryStage;
	 private BorderPane rootLayout;
	 private ObservableList<Movimento> movimentoData = FXCollections.observableArrayList();
	 private ObservableList<Veiculo> veiculoData = FXCollections.observableArrayList();
	 private Parametros paramentos;

	 public MainApp() throws SQLException{
        // Add some sample data
		 VeiculoDAO veiculodao = new VeiculoDAO();
		 this.veiculoData = veiculodao.selectAll();
		 MovimentoDAO movimentodao = new MovimentoDAO();
		 this.movimentoData = movimentodao.selectAll();

		 ParametrosDAO parametrosdao = new ParametrosDAO();
		 this.paramentos = parametrosdao.acessa();
		 //System.out.println(LocalDateTime.of(1800, 1, 12, 0, 0));
		 for(Movimento moviemnto : movimentoData){
			 System.out.println(moviemnto.getSaida().toString());
		 }
    }
	 /**
	 * Retorna os dados como uma observable list de Movimento.
	 * @return
	 */
	public ObservableList<Movimento> getMovimentoData() {
	    return movimentoData;
	}
	public ObservableList<Veiculo> getVeiculoData() {
	    return veiculoData;
	}
	public Parametros getParametros() {
	    return paramentos;
	}

	@Override
	public void start(Stage primaryStage) {

		 this.primaryStage = primaryStage;
	        this.primaryStage.setTitle("Estacionamento");

	        initRootLayout();

	        showPrincipalOverview();
	}
    /**
     * Inicializa o root layout (layout base).
     */
    public void initRootLayout() {
        try {
            // Carrega o root layout do arquivo fxml.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Mostra a scene (cena) contendo o root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean showVeiculoAddDialog(Veiculo veiculo){
    	try{
    		// Carrega o arquivo fxml e cria um novo stage para a janela popup
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(MainApp.class.getResource("../view/VeiculoAddDialog.fxml"));
    		AnchorPane page = (AnchorPane) loader.load();

    		// Cria o palco dialogStage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Define a pessoa no controller.
            VeiculoAddDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setVeiculo(veiculo);

            // Mostra a janela e espera até o usuário fechar.
            dialogStage.showAndWait();

            return controller.isOkClicked();

    	}catch(IOException e) {
            e.printStackTrace();
            return false;
    	}
    }
    public boolean showConfirmeMovimentoDialogController(Movimento movimento){
    	try{
    		// Carrega o arquivo fxml e cria um novo stage para a janela popup
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(MainApp.class.getResource("../view/ConfirmeMovimentoDialog.fxml"));
    		AnchorPane page = (AnchorPane) loader.load();

    		// Cria o palco dialogStage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Confirme");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Define a pessoa no controller.
            ConfirmeMovimentoDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMovimento(movimento);

            // Mostra a janela e espera até o usuário fechar.
            dialogStage.showAndWait();

            return controller.isOkClicked();

    	}catch(IOException e) {
            e.printStackTrace();
            return false;
    	}
    }
    /**
     * Mostra o person overview dentro do root layout.
     */
    public void showPrincipalOverview() {
        try {
            // Carrega o person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/principalView.fxml"));
            AnchorPane principalView = (AnchorPane) loader.load();

            // Define o person overview dentro do root layout.
            rootLayout.setCenter(principalView);

            // Dá ao controlador acesso à the main app.
            PrincipalViewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retorna o palco principal.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

	public static void main(String[] args) {
		launch(args);
	}
}
