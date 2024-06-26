package com.INGRYD.INGRYD_CRM.service;
import com.INGRYD.INGRYD_CRM.model.Enum.Role;
import com.INGRYD.INGRYD_CRM.model.SalesRep;
import com.INGRYD.INGRYD_CRM.repository.InvoiceRepository;
import com.INGRYD.INGRYD_CRM.repository.SalesRepRepository;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SalesRepService {

    final InvoiceRepository invoiceRepository;
    final SalesRepRepository salesRepRepository;

    public SalesRepService(SalesRepRepository salesRepRepository, InvoiceRepository invoiceRepository) {
        this.salesRepRepository = salesRepRepository;
        this.invoiceRepository = invoiceRepository;
    }

    public SalesRep addSalesRep(SalesRep salesRep) {
        return salesRepRepository.save(salesRep);
    }
    public SalesRep updateSalesRep(SalesRep salesRep) {
       SalesRep existingSalesRep = salesRepRepository.findById(salesRep.getId())
               .orElseThrow(() -> new ServiceException("Sales Representative not found"));
       existingSalesRep.setName(salesRep.getName());
       existingSalesRep.setUsername(salesRep.getUsername());
       existingSalesRep.setPhoneNumber(salesRep.getPhoneNumber());
       existingSalesRep.setPassword(salesRep.getPassword());
       return salesRepRepository.save(existingSalesRep);
    }
    public SalesRep deleteSalesRep(SalesRep salesRep, Long id) {
        if (!salesRepRepository.existsById(id)) {
            throw new ServiceException("Sales Representative not found");
        }
        salesRepRepository.deleteById(id);
        salesRepRepository.delete(salesRep);
        return salesRep;
    }

    public Optional<SalesRep> getSalesRepDetails(Long id){
        return salesRepRepository.findById(id);
    }

    public List<SalesRep> listAllSalesReps(){
        return salesRepRepository.findAll();
    }
    public List<SalesRep> getSalesRepsByRole(Role role) {
        return salesRepRepository.findSalesRepByRole(role);
    }
    public SalesRep assignRole(Long salesRepId, Role role) {
        SalesRep salesRep = salesRepRepository.findById(salesRepId)
                .orElseThrow(() -> new EntityNotFoundException(STR."SalesRep with id \{salesRepId} not found"));

        salesRep.setRole(role);
        return salesRepRepository.save(salesRep);
    }
}
