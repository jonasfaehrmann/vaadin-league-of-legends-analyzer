package de.leuphana.backend;

import org.springframework.data.jpa.repository.JpaRepository;

import de.leuphana.backend.data.entity.HistoryItem;

public interface HistoryItemRepository extends JpaRepository<HistoryItem, Long> {
}
