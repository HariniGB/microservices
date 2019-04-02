package com.eazydineapp.menu.util;

import com.eazydineapp.menu.model.Item;

import java.util.Comparator;

public class ItemComparatorUtil {

    Comparator<Item> itemNameComparator = new Comparator<Item>() {
        @Override
        public int compare(Item o1, Item o2) {
            int comparision = o1.getCategory().getName().compareTo(o2.getCategory().getName());
            if(comparision!=0){
                return comparision;
            }
            return o1.getName().compareTo(o2.getName());
        }
    };

    Comparator<Item> itemPriceComparator = new Comparator<Item>() {
        @Override
        public int compare(Item o1, Item o2) {
            int comparision = o1.getCategory().getName().compareTo(o2.getCategory().getName());
            if(comparision!=0){
                return comparision;
            }
            return o1.getPrice() > o2.getPrice()?1:-1;
        }
    };
}
