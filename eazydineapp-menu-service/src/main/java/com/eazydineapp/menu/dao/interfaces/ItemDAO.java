package com.eazydineapp.menu.dao.interfaces;


import com.eazydineapp.menu.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemDAO extends JpaRepository<Item,Long> {


    List<Item> findAllByMenu_IdOrderByCategoryAscPriceAsc(Long menuId);
    List<Item> findAllByMenu_IdOrderByCategoryAscNameAsc(Long menuId);
    List<Item> findAllByMenu_IdOrderByCategory_RankAscPriceAsc(Long menuId);

}
