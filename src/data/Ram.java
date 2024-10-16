
package data;

import java.util.Comparator;

public class Ram{
    private String code;
    private String type;
    private int bus;
    private String brand;
    private int quantity;
    private String productionDate;
    private boolean activation;

    public Ram(String code, String type, int bus, String brand, int quantity, String productionDate, boolean activation) {
        this.code = code;
        this.type = type;
        this.bus = bus;
        this.brand = brand;
        this.quantity = quantity;
        this.productionDate = productionDate;
        this.activation = activation;
    }

    
    /**
     * getter code of ram
     * @return String code
     */
    public String getCode() {
        return code;
    }

    /**
     * setter of ram's code
     * @param code 
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * getter type of ram
     * @return 
     */
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * getter bus of ram
     * @return 
     */
    public int getBus() {
        return bus;
    }

    public void setBus(int bus) {
        this.bus = bus;
    }

    /**
     * getter brand of ram
     * @return 
     */
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * getter quantity of ram
     * @return 
     */
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    

    public String getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    public boolean isActivation() {
        return activation;
    }

    public void setActivation(boolean activation) {
        this.activation = activation;
    }

    @Override
    public String toString() {
        return code + ", " + type + ", "  + bus + ", " + brand + ", " + quantity + ", " + productionDate;
    }
    
    /**
     * print the information of RAM by format code, type, productionDate, quantity
     */
    public void printByType() {
        System.out.println(code + ", " + type + ", " + productionDate + ", " + quantity);
    }
    
    /**
     * print the information of RAM by format code, bus, productionDate, quantity
     */
    public void printByBus() {
        System.out.println(code + ", " + bus + ", " + productionDate + ", " + quantity);
    }
    
    /**
     * print the information of RAM by format code, brand, productionDate, quantity
     */
    public void printByBrand() {
        System.out.println(code + ", " + brand + ", " + productionDate + ", " + quantity);
    }
    
}
