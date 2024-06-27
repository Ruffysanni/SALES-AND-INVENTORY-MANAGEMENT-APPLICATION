package com.INGRYD.INGRYD_CRM.configuration;
import com.INGRYD.INGRYD_CRM.model.Customer;
import com.INGRYD.INGRYD_CRM.model.SalesRep;
import com.INGRYD.INGRYD_CRM.repository.CustomerRepository;
import com.INGRYD.INGRYD_CRM.repository.SalesRepRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final CustomerRepository customerRepository;
    private final SalesRepRepository salesRepRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            UserDetails userDetails = null;
            // Check if the user exists in the Customer repository
            Optional<Customer> customerOptional = customerRepository.findByUsername(username);
            if (customerOptional.isPresent()) {
                Customer customer = customerOptional.get();
                userDetails = User.builder()
                        .username(customer.getUsername())
                        .password(customer.getPassword())
                        .roles("CUSTOMER")
                        .build();
            } else {
                // If not found in Customer repository, check in SalesRep repository
                Optional<SalesRep> salesRepOptional = salesRepRepository.findByUsername(username);
                if (salesRepOptional.isPresent()) {
                    SalesRep salesRep = salesRepOptional.get();
                    userDetails = User.builder()
                            .username(salesRep.getUsername())
                            .password(salesRep.getPassword())
                            .roles("SALES_REP")
                            .build();
                }
            }

            if (userDetails == null) {
                throw new UsernameNotFoundException(STR."Username not found: \{username}");
            }
            return userDetails;
        };
    }

}
