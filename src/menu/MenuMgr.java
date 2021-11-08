package menu;

import filemanager.FileMgr;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuMgr implements FileMgr{
	
	private MenuItemMgr menuItemMgr;
	private PromotionPackageMgr promotionPackageMgr;

	public MenuMgr() {
		// TODO - implement Menu.Menu
		menuItemMgr = new MenuItemMgr();
		promotionPackageMgr = new PromotionPackageMgr();
		load();
	}
	
	public void seeMenu() {
		menuItemMgr.viewMenuItems();
		promotionPackageMgr.viewPackages();;
	}
	
	public void editMenu() {
		System.out.println("Choose Menu Type:\n"
				+ "1)\tMenu\n"
				+ "2)\tPromotions\n"
				+ "3)\tExit");
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		if(choice == 1) {
			menuItemMgr.edit();
		}
		else if(choice == 2) {
			promotionPackageMgr.edit(menuItemMgr.getListOfMenuItems());
		}
		else {
			System.out.println("Exiting to Main Menu");
		}
	}
	
	public MenuItem getMenuItem(int itemId){
	    ArrayList<MenuItem> listOfMenuItems = menuItemMgr.getListOfMenuItems();
	    int i;
	        for(i=0; i<listOfMenuItems.size();i++)
	        {
	            if(listOfMenuItems.get(i).getItemId() == itemId) {
	                return listOfMenuItems.get(i);
	            }
	        }
	    System.out.println(itemId+" not found.");
	    return null;
	}
	public PromotionPackage getPromotionPackage(int packageId){
	    ArrayList<PromotionPackage> listOfPromotion = promotionPackageMgr.getListOfPromotion();
	    int i;
	    for(i=0; i<listOfPromotion.size();i++)
	    {
	        if(listOfPromotion.get(i).getPackageId() == packageId) {
	            return listOfPromotion.get(i);
	        }
	    }
	    System.out.println(packageId+" not found.");
	    return null;
	}

	public void save() {
	    menuItemMgr.save();
	    promotionPackageMgr.save();
	}

	public void load() {
	    menuItemMgr.load();
	    promotionPackageMgr.load();
	}
	

}