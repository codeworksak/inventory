package com.codeworks.inventory.service;

import com.codeworks.inventory.models.InventoryItem;
import com.codeworks.inventory.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    public void  deductStock(UUID pid,Integer qty)
    {
        InventoryItem itm = inventoryRepository.getReferenceById(pid);
        if(itm.getQuantity() < qty)
            throw new RuntimeException("Cannot deduct quantity as available quantity is less the requested");
        itm.setQuantity(itm.getQuantity() -qty);
        inventoryRepository.save(itm);
    }
    public void  addStock(UUID pid,Integer qty)
    {
        InventoryItem itm = inventoryRepository.getReferenceById(pid);

        itm.setQuantity(itm.getQuantity() +qty);
        inventoryRepository.save(itm);
    }
    public List<InventoryItem> getAll()
    {
        return  inventoryRepository.findAll();
    }
    public InventoryItem getById(UUID id)
    {
        return  inventoryRepository.getReferenceById(id);
    }
    public List<InventoryItem> getByName(String name)
    {
      return   inventoryRepository.getByNameStartsWith(name);
    }
    public InventoryItem  addItem(InventoryItem item)
    {
       return inventoryRepository.save(item);
    }
    public void  updateName(String name,UUID pid)
    {
        InventoryItem itm = inventoryRepository.getReferenceById(pid);
        itm.setName(name);
        inventoryRepository.save(itm);
    }
}
