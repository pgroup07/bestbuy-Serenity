package com.bestbuy.Serenity.model;

public class ProductPojo {
    private String name;
    private String type;
    private String upc;
    private double price;
    private String description;
    private String model;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public static ProductPojo getProjectPojo(String name, String type, String upc, double price, String description, String model) {
        ProductPojo projectPojo = new ProductPojo();
        projectPojo.setName(name);
        projectPojo.setType(type);
        projectPojo.setUpc(upc);
        projectPojo.setPrice(price);
        projectPojo.setDescription(description);
        projectPojo.setModel(model);
        return projectPojo;
    }
}
