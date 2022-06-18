package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;

public class MainFormController {
	@FXML
    private TableView<?> tv1;
	@FXML
    private TableView<?> tv2;
	@FXML
    private Label lb1;
	@FXML
    private TextField prodnum;
	private ObservableList<?> data;
	
	
	@FXML
	public void select1(ActionEvent ae)
	{
		Db.select1(tv1);
	}
	public void select2(ActionEvent ae)
	{
		Db.select2(tv1);
	}
	public void select3(ActionEvent ae)
	{
		Db.select3(tv1);
	}
	public void prodIns(ActionEvent ae)
	{
		data = FXCollections.observableArrayList();
		
		ObservableList<String> row = FXCollections.observableArrayList();
		
		row.add(prodnum.getText());
		
		tv2.getColumns().clear();
		TableColumn col = new TableColumn("Товары");
		
		col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
			
			public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) 
			{
			return new SimpleStringProperty(param.getValue().get(0).toString());
			}
			});
		tv2.getColumns().addAll(col);
		
		data.add(row);
		tv2.setItems(data);

	}
}
