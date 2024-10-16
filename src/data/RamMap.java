
package data;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import util.Inputer;

public class RamMap extends HashMap<String, Ram>{
    
    public void printByBrand() {
        ArrayList<Ram> tmp = new ArrayList<Ram>();
        String brandSearch = Inputer.inputAString("Input a brand you want to find", true);
        tmp = this.searchByBrand(brandSearch);
        if(tmp.isEmpty()) System.out.println("Nothing to find");
        else {
            for (Ram ram : tmp) {
                ram.printByBrand();
            }   
        }
    }
    
    public void printByBus() {
        ArrayList<Ram> tmp = new ArrayList<Ram>();
        int busSearch = Inputer.inputAnInteger("Input bus speed you want to find", 0);
        tmp = this.searchByBus(busSearch);
        if(tmp.isEmpty()) System.out.println("Nothing to find");
        else {
            for (Ram ram : tmp) {
                ram.printByBus();
            }
        }        
    }
    
    public void printByType() {
        ArrayList<Ram> tmp = new ArrayList<Ram>();
        String typeSearch = Inputer.inputAString("Input a type you want to find", true);
        tmp = this.searchByType(typeSearch);
        if(tmp.isEmpty()) System.out.println("Nothing to print");
        else {
            for (Ram ram : tmp) {
                ram.printByType();
            }
        }
    }
    
    public void printAllDeletedItem() {
        Collection<Ram> tmp = values();
        for (Ram ram : tmp) {
            if(!ram.isActivation())
                System.out.println(ram);
        }
    }
    
    /**
     * the function to update RAM item
     * @param code the code of RAM item you want to update
     * @return RAM item that you have just updated
     */
    public Ram updateRamItem(String code) {
        Ram t = searchByCode(code);
        if(t != null) {
            int bus, quantity;
            String brand;
            bus = Inputer.inputAnInteger("Input new bus (enter 0 to retain the old value)", -1);
            brand = Inputer.inputAString("Input new brand (enter to retain the old value)", false);
            quantity = Inputer.inputAnInteger("Input new quantity (enter 0 to retain the old value)", -1);
            if(bus != 0) t.setBus(bus);
            if(quantity != 0) t.setQuantity(quantity);
            if(!brand.isEmpty()) t.setBrand(brand.toUpperCase());
        }
        return t;
    }
    
    /**
     * deleting item by setting activation of that item inactive(true)
     * @param code code of RAM item that you want to find
     */
    public Ram deleteAItem(String code) {
        Ram t = searchByCode(code);
        if(t != null)
            t.setActivation(false);
        return t;
    }

    /**
     * searching a RAM item in list by its code
     * @param code code of RAM item that you want to find
     * @return a Ram item 
     */
    public Ram searchByCode(String code) {
        Ram rs = null;//bởi vì code không trùng nên đầu ra chỉ là 1 object thôi
        Collection<Ram> t = values();
        for (Ram r : t) {
            if(r.getCode().equalsIgnoreCase(code) && r.isActivation() == true) {
                rs = r;
                break;
            }
        }
        return rs;
    }
    
    /**
     * 
     * @param brand the brand of ram item that you want to find
     * @return a list of ram items that matching with specified brand
     */
    public ArrayList<Ram> searchByBrand(String brand) {
        Collection<Ram> tmp = values(); //do Brand có thể trùng nên đầu ra là List
        ArrayList<Ram> rs = new ArrayList<>();
        for (Ram r : tmp) 
            if(r.getBrand().equalsIgnoreCase(brand) && r.isActivation() == true)
                rs.add(r);
        return rs;
    }
    
    /**
     * finding all of ram items matching with specified bus speed
     * @param bus bus speed that you want to find
     * @return a list of ram items that matching with specified bus speed
     */
    public ArrayList<Ram> searchByBus(int bus) {
        Collection<Ram> tmp = values();
        ArrayList<Ram> rs = new ArrayList<>();
        for (Ram r : tmp) 
            if(r.getBus() == bus && r.isActivation() == true)
                rs.add(r);
        return rs;
    }
    
    /**
     * The method will ask some information about RAM item to create and add
     * this item to the list
     */
    public void addRamItem() {
        String type, brand, productionDate, code;
        int bus, quantity;
        //type
        type = Inputer.inputAString("Input type of RAM", true).toUpperCase();
        //bus
        bus = Inputer.inputAnInteger("Input bus speed", 0);
        //brand
        brand = Inputer.inputAString("Input brand of RAM", true).toUpperCase();
        //quantity
        quantity = Inputer.inputAnInteger("Input quantity of RAM", -1);
        //productionDate
        int month = Inputer.inputAnIntegerInRange("Input month of production date",
                                                    0, 13);
        int year = Inputer.inputAnIntegerInRange("Input year of production date", 1989, 
                                                    LocalDate.now().getYear() + 1);
        
        productionDate = String.format(month + "/" + year);  
        //code
        code = String.format("RAM" + type + "_" + (this.size() + 1));
        
        this.put(code, new Ram(code, type, bus, brand, quantity, productionDate, true));
    }
    
    /**
     * search RAM items by type of RAM
     * @param type is the type of RAM that you want to search
     * @return a list of RAM 
     */
    public ArrayList<Ram> searchByType(String type) {
        Collection<Ram> tmp = values();
        ArrayList<Ram> rs = new ArrayList<>();
        for (Ram ram : tmp) {
            if(ram.getType().equalsIgnoreCase(type) && ram.isActivation() == true) 
                rs.add(ram);
        }
        return rs;  
    }
    
    /**
     * print all ram items sorted by code, brand and bus
     */
    public void printAllItems() {
        Collection<Ram> tmp = values();
        ArrayList<Ram> rs  = new ArrayList<>();
        for (Ram ram : tmp) 
            if(ram.isActivation())
                rs.add(ram);
        //-----
        Comparator<Ram> lh = new Comparator<Ram>() {
            @Override
            public int compare(Ram o1, Ram o2) {
                if(o1.getType().compareTo(o2.getType()) > 0) return 1;
                else if(o1.getType().compareTo(o2.getType()) == 0) {
                    if(o1.getBus() > o2.getBus()) return 1;
                    else if(o1.getBus() == o2.getBus()) {
                        return (o1.getBrand().compareTo(o2.getBrand()));
                    } else return -1;
                } else return -1;
            }
        };
        //-----
        Collections.sort(rs, lh);
        for (Ram r : rs) 
            System.out.println(r);
        
    }
    
    /**
     * loading data of all RAM items from binary file
     * @param fileName the path file of RAM data 
     */
    public void loadDataFromFile(String fileName) {
        String code, type, brand, productionDate;
        int bus;
        int quantity;
        boolean activation;
        Ram r = null;
        try {
            FileInputStream fIS = new FileInputStream(fileName);
            DataInputStream dIS = new DataInputStream(fIS);
            int quantityOfRam = dIS.readInt();
            for (int i = 0; i < quantityOfRam; i++) {
                code = dIS.readUTF();
                type = dIS.readUTF();
                bus = dIS.readInt();
                brand = dIS.readUTF();
                quantity = dIS.readInt();
                productionDate = dIS.readUTF();
                activation = dIS.readBoolean();
                r = new Ram(code, type, bus, brand, quantity, productionDate, activation);
                put(code, r);
            }
        } catch (IOException e) {
            System.out.println("Loading data to app is wrong");
        }
    }
    
    /**
     * saving data of all RAM items to binary file
     * @param fileName the path file of RAM data 
     */
    public void saveDataToFile(String fileName) {
        Collection<Ram> tmp = values();
        try {
            FileOutputStream fOS = new FileOutputStream(fileName);
            DataOutputStream dOS = new DataOutputStream(fOS);
            dOS.writeInt(this.size());
            for (Ram r : tmp) {
                dOS.writeUTF(r.getCode());
                dOS.writeUTF(r.getType());
                dOS.writeInt(r.getBus());
                dOS.writeUTF(r.getBrand());
                dOS.writeInt(r.getQuantity());
                dOS.writeUTF(r.getProductionDate());
                dOS.writeBoolean(r.isActivation());
            }
            fOS.close();
            dOS.close();
        } catch (IOException e) {
            System.out.println("Saving data to RAM file is wrong");
        }
    }
    
    
//    public static void main(String[] args) {
//        RamMap t = new RamMap();
//        t.loadDataFromFile("BinaryRamFile.txt");
//        t.printAllItems();
//    }
}
