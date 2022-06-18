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
    private Button bt2;
    
	@FXML
    private TextField tflog;

    @FXML
    private TextField tfpass; 

    
    
    ;
	public void login(ActionEvent ae)
	{
		System.out.println(Db.selectUser(tflog.getText(),tfpass.getText()));
		if (Db.selectUser(tflog.getText(),tfpass.getText()).contains("a"))
		{
			CreateWin();
		}
		else if (Db.selectUser(tflog.getText(),tfpass.getText()).contains("pr"))
		{
			
		}
		else
		{

			System.out.println("Проверьте правильность логина и пароля");
		}
	}
	
	public void CreateWinReg()
	{
		try 
		{
		Stage stage = new Stage();
		GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("SignupFormController.fxml"));
		Scene scene = new Scene(root,1500,800);
		stage.setScene(scene);
		stage.initOwner((Stage) bt2.getScene().getWindow());
		Stage end = ((Stage) bt2.getScene().getWindow());
		end.hide();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();
		
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	public void CreateWin()
	{
		try 
		{
		Stage stage = new Stage();
		GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("MainForm.fxml"));
		Scene scene = new Scene(root,1250,750);
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
