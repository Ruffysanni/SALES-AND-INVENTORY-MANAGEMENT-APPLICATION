package com.INGRYD.INGRYD_CRM.controller;
import com.INGRYD.INGRYD_CRM.model.SalesRep;
import com.INGRYD.INGRYD_CRM.repository.SalesRepRepository;
import com.INGRYD.INGRYD_CRM.service.SalesRepService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vi/SalesRep")
public class SalesRepController {
// Autowire Sales Representatives Repository,and Sales Representatives Service
    final SalesRepService salesRepService;
    final SalesRepRepository SalesRepRepository;
// Create Constructor for Sales Representatives Repository,and Sales Representatives Service
    public SalesRepController(SalesRepService salesRepService,SalesRepRepository salesRepRepository) {
        this.salesRepService = salesRepService;
        this.SalesRepRepository = salesRepRepository;
    }
// Post mapping to create a new Sales Representative
    @PostMapping("/create")
    public SalesRep createSalesRep(@RequestBody SalesRep salesRep) {
        return salesRepService.addSalesRep(salesRep);
    }
// Put mapping to edit the details of a Sales Representative
    @PutMapping("/update")
    public SalesRep updateSalesRepDetails(@RequestBody SalesRep salesRep) {
        return salesRepService.updateSalesRep(salesRep);
    }
// Delete mapping to delete a Sales Representative
    @DeleteMapping("/delete/{id}")
    public SalesRep deleteSalesRep(@PathVariable long id, @RequestBody SalesRep salesRep) {
        return salesRepService.deleteSalesRep(salesRep,id);
    }
// Get mapping to fetch all the Sales Representatives
    @GetMapping("/listAll")
    public List<SalesRep> getAllReps(){
        return salesRepService.listAllSalesReps();
    }
// Get mapping to fetch details of a Sales Representative
    @GetMapping("/details/{id}")
    public Optional<SalesRep> salesRepDetails(@PathVariable long id){
        return salesRepService.getSalesRepDetails(id);
    }
}
