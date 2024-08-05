package model;

public class GameVO {
    private String id;
    private String name;
    private int views;
    private double price;
    private int purchases;
    private int discount;
    private double rating;

    public GameVO() {
    }
    
    public GameVO(String id, String name, int views, double price, int purchases, int discount, double rating) {
        this.id = id;
        this.name = name;
        this.views = views;
        this.price = price;
        this.purchases = purchases;
        this.discount = discount;
        this.rating = rating;
    }
    
    //Getters and Setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
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
            return "O jogo possui alto custo beneficio";
        } else if(costBenefit <= 40.0) {
            return "O jogo possui um custo beneficio mediano";
        } else {
            return "O jogo possui baixo custo beneficio";
        }
    }

    public String calcPopularity() {
        double buyRate = ((double) getPurchases() / getViews()) * 100;

        if(buyRate >= 70.0) {
            return "O jogo possui alta popularidade";
            
        } else if(buyRate >= 35.0) {
            return "O jogo possui uma popularidade mediana";
        } else {
            return "O jogo possui baixa popularidade";
        }
    }
}
