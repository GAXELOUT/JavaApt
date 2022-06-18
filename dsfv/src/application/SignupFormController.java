package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class SignupFormController {
	@FXML
    private Button ConfirmBtn;


    @FXML
    private Button bt2;
    
    @FXML
    private TextField MakePassTextField;

    @FXML
    private TextField RepeatPassTextField;

    @FXML
    private TextField UsernameTextField;

    @FXML
    void tryConfirmRegistr(ActionEvent event) {    	
    	String username = UsernameTextField.getText();
    	String password = MakePassTextField.getText();
    	String repeatPass = RepeatPassTextField.getText();
    	String regex = "(?!.*[&{}|+])(?=.*[0-9])(?=.*[A-Z]).{4,16}";
    	if (!checkUsername(username)) {
    		if (!password.contains(username) && password.matches(regex)) {
    			if (password.equals(repeatPass)) {
    				String[] params = new String[3];
    				params[0] = username;
    				params[1] = password;
    				params[2] = "pr";
    				addUser(params);
    				Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Successful create user");
                    alert.setContentText("Username " + username + " was created successful");
                    alert.showAndWait();
    			} else {
    				Alert alert = new Alert(AlertType.ERROR);
                	alert.setTitle("Error password equal");
                	alert.setContentText("Password not equals");
                	alert.showAndWait();
    			}
    		} else {
    			Alert alert = new Alert(AlertType.ERROR);
            	alert.setTitle("Error valid password");
            	alert.setContentText(
            			"Password is not valid. Password must: \n" +
            			"- range 4 to 16 symbols; \n" + 
            			"- not contains symbols * & { } | +; \n" +
            			"- a upper case letter must occur at least once; \n" +
            			"- must contain numbers"
            			);
            	alert.showAndWait();
    		}
    	} else {
    		Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Error username");
        	alert.setContentText("Username is exist");
        	alert.showAndWait();
    	}
    }
    
    public boolean checkUsername(String username) {
		ArrayList<String[]> users = new ArrayList<String[]>();
		boolean flag = false;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("src/application/users.txt"));
			String line;
			while ((line = br.readLine()) != null) {
				String[] userParams = line.split(" ");
				users.add(userParams);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for (String[] user : users)
			if (user[0].equals(username)) flag = true;
		
    	return flag;
    }
    
    private void addUser(String[] params) {
    	Db.createUser(params[0], params[1], params[2]);
        } 
	}

	

