package de.leuphana.backend.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Persistable;
import org.springframework.data.repository.CrudRepository;

import de.leuphana.backend.data.entity.AbstractEntity;

public abstract class CrudService<T> {

	protected abstract CrudRepository<T, Integer> getRepository();

	public T save(T entity) {
		return getRepository().save(entity);
	}

	public void delete(Integer id) {
		getRepository().delete(id);
	}

	public T load(Integer id) {
		return getRepository().findOne(id);
	}

	public abstract long countAnyMatching(Optional<String> filter);

	public abstract Page<T> findAnyMatching(Optional<String> filter, Pageable pageable);

}
