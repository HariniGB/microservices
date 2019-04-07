package com.eazydineapp.menu.dto;


public class RestaurantDTO {


    private Long id;
    private String firebaseId;
    private String name;
    private String tagline;
    private String uuid;
    private String city;
    private int zipcode;
    private String cuisine;

    public String getRestaurantbgimage() {
        return restaurantbgimage;
    }

    public void setRestaurantbgimage(String restaurantbgimage) {
        this.restaurantbgimage = restaurantbgimage;
    }

    private Boolean allowpreorder;
    private int numoftables;
    private String phonenumber;
    private String restaurantbgimage;

    public int getNumoftables() {
        return numoftables;
    }

    public void setNumoftables(int numoftables) {
        this.numoftables = numoftables;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }


    public Boolean getAllowpreorder() {
        return allowpreorder;
    }

    public void setAllowpreorder(Boolean allowpreorder) {
        this.allowpreorder = allowpreorder;
    }

    public Long getAvgprice() {
        return avgprice;
    }

    public void setAvgprice(Long avgprice) {
        this.avgprice = avgprice;
    }

    public  Long avgprice;


    public String getFirebaseId() {
        return firebaseId;
    }

    public void setFirebaseId(String firebaseId) {
        this.firebaseId = firebaseId;
    }


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }




    // TODO: Implement Cuisines Enum
//    @ManyToMany(mappedBy="restaurant",cascade = CascadeType.ALL)
//    private EnumSet<Cuisine> cuisines;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

//    public EnumSet<Cuisine> getCuisines() {
//        return cuisines;
//    }
//
//    public void setCuisines(EnumSet<Cuisine> cuisines) {
//        this.cuisines = cuisines;
//    }

    public String getCuisine() { return cuisine; }

    public void setCuisine(String cuisine) { this.cuisine = cuisine; }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

}
