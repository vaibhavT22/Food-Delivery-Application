package com.vt.DAO;

import java.util.List;

import com.vt.model.Menu;

public interface MenuDAO {
	void addMenu(Menu menu);
	Menu getMenu(int menuId);
	void updateMenu(Menu menu);
	void deleteMenu(int menuId);
	List<Menu> getAllMenu(int restaurantId);
	
}
