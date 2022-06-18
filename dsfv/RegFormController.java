package application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
public class RegFormController 
{
	@FXML
    private Button bt1;
	
	@FXML
    private TextField tflog;

    @FXML
    private TextField tfpass; 
    @FXML
    private GridPane bp;
    
	public void login(ActionEvent ae)
	{
		if (Db.selectUser(tflog.getText(),tfpass.getText()))
		{
			CreateWin();
		}
		else
		{

			System.out.println("Проверьте правильность логина и пароля");
		}
	}
	
	public void CreateWin()
	{
		try 
		{
		Stage stage = new Stage();
		GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("MainForm.fxml"));
		Scene scene = new Scene(root,500,500);
		stage.setScene(scene);
		stage.initOwner((Stage) bt1.getScene().getWindow());
		Stage end = ((Stage) bt1.getScene().getWindow());
		end.hide();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();
		
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
}
