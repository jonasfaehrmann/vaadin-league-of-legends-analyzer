package de.leuphana.backend;

import org.springframework.data.jpa.repository.JpaRepository;

import de.leuphana.backend.data.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
