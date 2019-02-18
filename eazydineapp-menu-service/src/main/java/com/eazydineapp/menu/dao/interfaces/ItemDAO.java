package com.eazydineapp.menu.dao.interfaces;


import com.eazydineapp.menu.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemDAO extends JpaRepository<Item,Long> {


    List<Item> findAllByMenu_IdOrderByCategoryAscPriceAsc(Long menuId);
    List<Item> findAllByMenu_IdOrderByCategoryAscNameAsc(Long menuId);

}
