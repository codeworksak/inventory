package com.codeworks.inventory.controller;

import com.codeworks.inventory.models.InventoryItem;
import com.codeworks.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/all")
    public ResponseEntity<List<InventoryItem>> getAll()
    {
        return ResponseEntity.ok(inventoryService.getAll());
    }
    @GetMapping("/byid/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id)
    {
        InventoryItem itm= inventoryService.getById(id);
        if(itm ==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Inventory not found");
            else
            return ResponseEntity.ok(itm);
    }
    @GetMapping("/byname/{name}")
    public ResponseEntity<?> getById(@PathVariable String name)
    {
       List< InventoryItem> itms= inventoryService.getByName(name);
        if(itms ==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Inventory with name");
        else
            return ResponseEntity.ok(itms);
    }
    @PostMapping("/add")
     public ResponseEntity<?> add(@RequestBody InventoryItem item)
    {
      InventoryItem itm=  inventoryService.addItem(item);
            URI u = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(itm.getId())
                    .toUri();
        return  ResponseEntity.created(u).body(itm);
    }
    @PutMapping("/reduce/{id}/{qty}")
    public ResponseEntity<Void> reduceStock(@PathVariable UUID id,@PathVariable Integer qty)
    {
        inventoryService.deductStock(id,qty);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
