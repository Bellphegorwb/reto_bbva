package com.retobbva.challenge.jpa;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.retobbva.challenge.model.CurrencyModel;

@Repository
public interface CurrencyRepository extends JpaRepository<CurrencyModel, String>{

	List<CurrencyModel> findByName(@Param("name") String name);
}
