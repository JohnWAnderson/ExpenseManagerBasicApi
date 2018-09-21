package com.jwa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwa.model.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

}