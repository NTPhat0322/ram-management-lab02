
package main;

import data.RamMap;
import data.Ram;
import java.util.ArrayList;
import ui.Menu;
import util.FileName;
import util.Inputer;

public class Main {
    public static void main(String[] args) {
        RamMap ramList = new RamMap();
        ramList.loadDataFromFile(FileName.BINARY_RAM_FILE);
        
        int choice;
        do {
            System.out.println("");
            Menu menu = new Menu("The RAM management system");
            menu.addNewOption("1. Add new RAM item.");
            menu.addNewOption("2. Search RAM item.");
            menu.addNewOption("3. update RAM item information.");
            menu.addNewOption("4. Delete an item.");
            menu.addNewOption("5. Show all items.");
            menu.addNewOption("6. Save to file.");
            menu.addNewOption("7. Quit.");
            menu.printMenu();
            choice = menu.getChoice();
            
            int loop = 0;
            switch(choice) {
                case 1:
                    loop = 0;
                    do {
                        ramList.addRamItem();
                        loop = Inputer.inputAnIntegerInRange("Do you want to add more RAM item(0/1)",
                                                            -1, 2);
                    } while(loop == 1);
                    break;
                case 2:
                    loop = 0;
                    Menu searchMenu = new Menu("Search menu");
                    searchMenu.addNewOption("1. Search by type");
                    searchMenu.addNewOption("2. Search by bus speed");
                    searchMenu.addNewOption("3. Search by brand");
                    searchMenu.addNewOption("4. Show deleted item");
                    do {
                        searchMenu.printMenu();
                        int searchChoice = searchMenu.getChoice();
                        ArrayList<Ram> tmp = new ArrayList<Ram>();
                        switch(searchChoice) {
                            case 1:
                                ramList.printByType();
                                break;
                            case 2:
                                ramList.printByBus();
                                break;
                            case 3:
                                ramList.printByBrand();
                                break;
                            case 4:
                                System.out.println("Deleted item");
                                ramList.printAllDeletedItem();
                        }
                        loop = Inputer.inputAnIntegerInRange("Do you want to continue searching(0/1)",
                                                            -1, 2);
                    } while(loop == 1);
                    break;
                case 3:
                    loop = 0;
                    do {
                        String codeUpdate = Inputer.inputAString("Input the code of RAM item you want to update", true);
                        Ram tUpdate = ramList.updateRamItem(codeUpdate);
                        if(tUpdate == null) {
                            System.out.println("The code does not exist");
                        }else {
                            System.out.println("Update successfully");
                            System.out.println("The information after updating");
                            System.out.println(tUpdate);
                        }
                        loop = Inputer.inputAnIntegerInRange("Do you want to continue updating(0/1)",
                                                            -1, 2);
                    } while (loop == 1);
                    break;
                case 4:
                    loop = 0;
                    do {
                        String codeDel = Inputer.inputAString("Input the code of RAM item that you want to delete", true);
                        Ram t = ramList.searchByCode(codeDel);
                        if(t == null) System.out.println("Your code does not exist");
                        else {
                            System.out.println("RAM item's information you want to delete");
                            System.out.println(t);
                            int cfirm = Inputer.inputAnIntegerInRange("Are you sure to delete(0/1)",
                                                            -1, 2);
                            if(cfirm == 1){
                                ramList.deleteAItem(codeDel);
                                System.out.println("You have just deleted");
                                System.out.println(t);
                            }
                        }
                        loop = Inputer.inputAnIntegerInRange("Do you want to continue deleting(0/1)",
                                                            -1, 2);
                        
                    }while(loop == 1);
                    break;
                case 5:
                    System.out.println("All ram items");
                    ramList.printAllItems();
                    break;
                case 6:
                    ramList.saveDataToFile(FileName.BINARY_RAM_FILE);
                    System.out.println("Saving successfully");
                    break;
                case 7:
                    System.out.println("Thanks for using app, see you next time!!!");
            }
        }while (choice != 7);
    }
}
