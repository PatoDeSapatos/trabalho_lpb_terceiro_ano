package model;

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

    public GameVO() {
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

    public String calcDiscount() {
        double discountValue = getPrice() * ((double) getDiscount() / 100);
        return String.format("%.2f", getPrice() - discountValue);
    }

    public String calcCostBenefit() {
        double costBenefit = getPrice() / getRating();

        if (costBenefit <= 20.0) {
            return "Alto";
        } else if(costBenefit <= 40.0) {
            return "Mediano";
        } else {
            return "Baixo";
        }
    }

    public String calcPopularity() {
        double buyRate = ((double) getPurchases() / getViews()) * 100;

        if(buyRate >= 70.0) {
            return "Alta";
        } else if(buyRate >= 35.0) {
            return "Mediana";
        } else {
            return "Baixa";
        }
    }
}
