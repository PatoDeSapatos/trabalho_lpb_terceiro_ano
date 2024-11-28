package model.game;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import model.user.UserVO;

@Entity
public class GameVO {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@ManyToOne(optional = false)
    private UserVO user;
    private String name;
    private String iconLink;
    private String bannerLink;
    private int views;
    private double price;
    private int purchases;
    private int discount;
    private double rating;

    public GameVO(int id, UserVO user, String name, String iconLink, String bannerLink, int views, double price, int purchases,
            int discount, double rating) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.iconLink = iconLink;
        this.bannerLink = bannerLink;
        this.views = views;
        this.price = price;
        this.purchases = purchases;
        this.discount = discount;
        this.rating = rating;
    }

    public GameVO() {
        this.id = 0; 
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
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public UserVO getUser() {
        return user;
    }
    public void setUser(UserVO user) {
        this.user = user;
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
