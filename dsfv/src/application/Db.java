package application;

import java.sql.*;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

public class Db 
{
	
	private static final String DB_CONNECTION = "jdbc:mysql://95.131.149.21:3306/mtkp_tbd_71_04";
	private static final String DB_USER = "mtkp_tbd_71_04";
	private static final String DB_PASSWORD = "97061697";
	private static ObservableList<ObservableList> data;
	
	public static String selectUser(String log, String pas)
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (Exception e)
		{
			
		}
		try (Connection conn = DriverManager.getConnection(DB_CONNECTION, DB_USER , DB_PASSWORD)) 
		{
			
			String sql = "SELECT position FROM ur_auth WHERE log = '"+log+"' and pas = '"+pas+"'";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			result.next();
			return(result.getString(1).toString());
		} 
		catch (SQLException ex) 
		{
			ex.printStackTrace();
		}
		return null;

	}
	public static ResultSet select1(TableView tableview)
	{
		
		try (Connection conn = DriverManager.getConnection(DB_CONNECTION, DB_USER , DB_PASSWORD)) 
		{
	
			String sql = "SELECT * FROM Ur_Product";
	
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			Update(result,tableview);
			return result;
	
			} 
		catch (SQLException ex) 
		{
			ex.printStackTrace();
		}
		return null;

	}
	public static String selectprice1()
	{
		
		try (Connection conn = DriverManager.getConnection(DB_CONNECTION, DB_USER , DB_PASSWORD)) 
		{
	
			String sql = "SELECT sum(drugPrice) FROM drugsklad";
	
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			result.next();
			return result.getString(1).toString();
	
			} 
		catch (SQLException ex) 
		{
			ex.printStackTrace();
		}
		return null;

	}
	public static String selectprice2()
	{
		
		try (Connection conn = DriverManager.getConnection(DB_CONNECTION, DB_USER , DB_PASSWORD)) 
		{
	
			String sql = "call price";
	
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			result.next();
			return result.getString(1).toString();
	
			} 
		catch (SQLException ex) 
		{
			ex.printStackTrace();
		}
		return null;

	}
	public static ResultSet select2(TableView tableview)
	{
		
		try (Connection conn = DriverManager.getConnection(DB_CONNECTION, DB_USER , DB_PASSWORD)) 
		{
	
			String sql = "SELECT * FROM Ur_Postavshik";
	
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			Update(result,tableview);
			return result;
	
			} 
		catch (SQLException ex) 
		{
			ex.printStackTrace();
		}
		return null;

	}
	public static ResultSet select3(TableView tableview)
	{
		
		try (Connection conn = DriverManager.getConnection(DB_CONNECTION, DB_USER , DB_PASSWORD)) 
		{
	
			String sql = "SELECT * FROM ur_Generator";
	
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			Update(result,tableview);
			return result;
	
			} 
		catch (SQLException ex) 
		{
			ex.printStackTrace();
		}
		return null;

	}
	static void createUser(String log,String pas, String pos)
	{
		try (Connection conn = DriverManager.getConnection(DB_CONNECTION, DB_USER , DB_PASSWORD)) 
		{
	
			String sql = "INSERT INTO workers (log,pas,position) VALUES  ('"+log+"','"+pas+"','"+pos+"');";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
		}
	
	}
	static void Update(ResultSet rs,TableView tableview)
	{
		data = FXCollections.observableArrayList();
		try 
		{
			tableview.getColumns().clear();
			for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) 
			{
				final int j = i;
				TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
				col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
				
				public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) 
				{
				return new SimpleStringProperty(param.getValue().get(j).toString());
				}
				});
				
				tableview.getColumns().addAll(col);
			
			}
			while (rs.next()) 
			{
				ObservableList<String> row = FXCollections.observableArrayList();
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) 
				{
					row.add(rs.getString(i));
				}
				data.add(row);
			}
		
			tableview.setItems(data);
		} 
		catch (Exception e) 
		{
		
			e.printStackTrace();
		}
		
	}
	
}
