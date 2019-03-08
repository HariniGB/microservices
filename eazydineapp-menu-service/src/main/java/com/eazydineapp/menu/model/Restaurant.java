package com.eazydineapp.menu.model;


import javax.persistence.*;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Restaurant {


    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String tagline;


    // TODO: Implement Cuisines Enum
//    @ManyToMany(mappedBy="restaurant",cascade = CascadeType.ALL)
//    private EnumSet<Cuisine> cuisines;
    private String cuisine;

    @OneToMany(mappedBy="restaurant",cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Menu> menus = new HashSet<>();

    @OneToMany(mappedBy="restaurant",cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Category> categories = new HashSet<>();


    @OneToMany(mappedBy="restaurant",cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Table> tables = new HashSet<>();

    private String address1;
    private String address2;
    private String city;
    private String state;
    private String country;
    private int zipcode;


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

    public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }

    public Set<Table> getTables() {
        return tables;
    }

    public void setTables(Set<Table> tables) {
        this.tables = tables;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

}
