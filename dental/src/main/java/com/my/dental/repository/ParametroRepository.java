package com.my.dental.repository;

import com.my.dental.core.entity.ParametroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ParametroRepository extends JpaRepository<ParametroEntity, Long> {

    Optional<List<ParametroEntity>> findByTipo(String tipo);

    @Query("SELECT p FROM ParametroEntity p WHERE p.tipo IN :tipos")
    Optional<List<ParametroEntity>> findByTipoIn(@Param("tipos") List<String> tipos);

}
