package com.vt.daoimplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.vt.DAO.MenuDAO;
import com.vt.model.Menu;
import com.vt.utility.DBConnection;

public class MenuDAOImpl implements MenuDAO{
	private static final String INSERT_MENU_QUERY="insert into `menu`(`itemName`,`description`,`price`,`ratings`) values(?,?,?); ";
	private static final String GET_MENU_QUERY="select * from `menu` where `menuId` = ?;";
	private static final String UPDATE_MENU_QUERY="UPDATE `menu` SET `restaurantId`=? ,`itemName` = ?, `description` = ?, `price` = ?,  `isAvailable` = ?, `imagePath` = ? WHERE `menuId` = ?";
	private static final String DELETE_MENU_QUERY="delete from `menu` where `menuId` = ?;";
	private static final String GET_ALL_MENU_QUERY="select * from `menu` where `restaurantId`=?;";
	@Override
	public void addMenu(Menu menu) {
		// TODO Auto-generated method stub
		try(Connection con= DBConnection.getConnection();
				PreparedStatement prepareStatement = con.prepareStatement(INSERT_MENU_QUERY);){
				prepareStatement.setString(1, menu.getItemName());
				prepareStatement.setString(2, menu.getDescription());
				prepareStatement.setDouble(3, menu.getPrice());
				int res =prepareStatement.executeUpdate();
				
			
			
		}catch (SQLException e) {
		// TODO: handle exception
		e.printStackTrace();
		}
	}

	@Override
	public Menu getMenu(int menuId) {
		Menu menu=null;
				try(Connection con= DBConnection.getConnection();
						PreparedStatement prepareStatement = con.prepareStatement(GET_MENU_QUERY);){
					
					prepareStatement.setInt(1, menuId);
					ResultSet res= prepareStatement.executeQuery();
					
					if (res.next()) {  
			            menu = extractMenu(res);
			        } else {
			            System.out.println("No menu item found with ID: " + menuId);
			        }
					
					
				}catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
				}
				
		return menu;
	}

	@Override
	public void updateMenu(Menu menu) {
		// TODO Auto-generated method stub

		try(Connection con= DBConnection.getConnection();
				PreparedStatement prepareStatement = con.prepareStatement(UPDATE_MENU_QUERY);){
			
			prepareStatement.setInt(1, menu.getRestaurantId());
			prepareStatement.setString(2, menu.getItemName());
            prepareStatement.setString(3, menu.getDescription());
            prepareStatement.setDouble(4, menu.getPrice());
            prepareStatement.setBoolean(5, menu.isAvailable());
            prepareStatement.setString(6, menu.getImagePath());
            prepareStatement.setInt(7, menu.getMenuId());

            prepareStatement.executeUpdate();
			
		}catch (SQLException e) {
		// TODO: handle exception
		e.printStackTrace();
		}
	}

	@Override
	public void deleteMenu(int menuId) {
		// TODO Auto-generated method stub

		try(Connection con= DBConnection.getConnection();
				PreparedStatement prepareStatement = con.prepareStatement(DELETE_MENU_QUERY);){
			prepareStatement.setInt(1, menuId);
//			prepareStatement.execute();
			int affectedRows = prepareStatement.executeUpdate();
			if(affectedRows==0) {
				throw new SQLException("Deleting menu item failed , no rows affected.");
			}
			
		}catch (SQLException e) {
		// TODO: handle exception
		e.printStackTrace();
		}
	}

	@Override
	public List<Menu> getAllMenu(int restaurantId) {
		// TODO Auto-generated method stub
		
		ArrayList<Menu> menusList = new ArrayList<Menu>();
		
		try(Connection con= DBConnection.getConnection();
				PreparedStatement prepareStatement = con.prepareStatement(GET_ALL_MENU_QUERY);){
			prepareStatement.setInt(1, restaurantId);
			ResultSet res=prepareStatement.executeQuery();
			
			while(res.next()) {
				Menu menu=extractMenu(res);
				menusList.add(menu);
			}
			
		}catch (SQLException e) {
		// TODO: handle exception
		e.printStackTrace();
		}
		return menusList;
	}

	static Menu extractMenu(ResultSet res)throws SQLException {
		// TODO Auto-generated method stub
			int menuId = res.getInt("menuId");
	        int restaurantId = res.getInt("restaurantId");
	        String itemName = res.getString("itemName");
	        String description = res.getString("description");
	        double price = res.getDouble("price");
	        boolean isAvailable = res.getBoolean("isAvailable");
	        String imagePath = res.getString("imagePath");

		 
		 Menu menu=new Menu(menuId, restaurantId, itemName, description, price,  isAvailable, imagePath);
		 return menu;
	}

}
