package org.builtinquality.pos.repository;

import org.builtinquality.pos.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsRepository extends JpaRepository<ItemEntity, String>{
}
