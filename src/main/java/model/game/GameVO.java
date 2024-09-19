package model.game;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GameVO {
    private String id;
    private String name;
    private String iconLink;
    private String bannerLink;
    private int views;
    private double price;
    private int purchases;
    private int discount;
    private double rating;

    public GameVO(String id, String name, String iconLink, String bannerLink, int views, double price, int purchases,
            int discount, double rating) {
        this.id = id;
        this.name = name;
        this.iconLink = iconLink;
        this.bannerLink = bannerLink;
        this.views = views;
        this.price = price;
        this.purchases = purchases;
        this.discount = discount;
        this.rating = rating;
    }

    public GameVO(ResultSet result) throws SQLException {
        this.id = result.getString(1); 
        this.name = result.getString(2);
        this.iconLink = result.getString(3);
        this.bannerLink = result.getString(4);
        this.views = result.getInt(5);
        this.price = result.getDouble(6);
        this.purchases = result.getInt(7);
        this.discount = result.getInt(8);
        this.rating = result.getDouble(9);
    }

    public GameVO() {
        this.id = ""; 
        this.name = "";
        this.iconLink = "";
        this.bannerLink = "";
        this.views = 0;
        this.price = 0;
        this.purchases = 0;
        this.discount = 0;
        this.rating = 3;
    }
    
    //Getters and Setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getIconLink() {
        return iconLink;
    }
    public void setIconLink(String iconLink) {
        this.iconLink = iconLink;
    }

    public String getBannerLink() {
        return bannerLink;
    }

    public void setBannerLink(String bannerLink) {
        this.bannerLink = bannerLink;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getViews() {
        return views;
    }
    public void setViews(int views) {
        this.views = views;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getPurchases() {
        return purchases;
    }
    public void setPurchases(int purchases) {
        this.purchases = purchases;
    }
    public int getDiscount() {
        return discount;
    }
    public void setDiscount(int discount) {
        this.discount = discount;
    }
    public double getRating() {
        return rating;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }

    public double calcDiscount() {
        double discountValue = getPrice() * ((double) getDiscount() / 100);
        return getPrice() - discountValue;
    }

    public String calcCostBenefit() {

        double costBenefit = calcDiscount() / getRating();

        if (costBenefit <= 20.0) {
            return "alto";
        } else if(costBenefit <= 40.0) {
            return "mediano";
        } else {
            return "baixo";
        }
    }

    public String calcPopularity() {
        double buyRate = ((double) getPurchases() / getViews()) * 100;

        if(buyRate >= 70.0) {
            return "alta";
        } else if(buyRate >= 35.0) {
            return "mediana";
        } else {
            return "baixa";
        }
    }
}
