package com.exercicio5apicadastroveiculos.repository;

import com.exercicio5apicadastroveiculos.dto.QtyDecadeDTO;
import com.exercicio5apicadastroveiculos.dto.QtyManufacturersDTO;
import com.exercicio5apicadastroveiculos.dto.VehicleDTO;
import com.exercicio5apicadastroveiculos.entity.VehicleEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class VehicleCriteriaRepository {
    @PersistenceContext
    private EntityManager manager;

    public long countSold() {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<VehicleEntity> root = cq.from(VehicleEntity.class);

        cq.select(cb.count(root)).where(cb.isTrue(root.get("sold")));

        return manager.createQuery(cq).getSingleResult();
    }


    public List<VehicleDTO> listLastCreated() {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<VehicleEntity> cq = cb.createQuery(VehicleEntity.class);
        Root<VehicleEntity> root = cq.from(VehicleEntity.class);

        List<Predicate> filters = new ArrayList<>();

        LocalDateTime dataInicio = LocalDateTime.now().minusDays(7);

        filters.add(cb.greaterThanOrEqualTo(root.get("created"), dataInicio));

        cq.where(filters.toArray(new Predicate[0]));
        cq.orderBy(cb.desc(root.get("created")));

        List<VehicleEntity> result = manager.createQuery(cq).getResultList();

        return result.stream()
                .map(VehicleDTO::new)
                .collect(Collectors.toList());

    }

    public List<QtyManufacturersDTO> groupByBrands() {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<VehicleEntity> root = cq.from(VehicleEntity.class);

        Path<String> filter = root.get("brand");

        cq.multiselect(
                filter.alias("brand"),
                cb.count(root).alias("qty")
        ).where(
                cb.and(
                        cb.isNotNull(filter),
                        cb.notEqual(cb.trim(cb.upper(filter)), "")
                )
        ).groupBy(filter).orderBy(cb.asc(filter));

        List<Tuple> data = manager.createQuery(cq).getResultList();

        return data.stream().map(
                c -> new QtyManufacturersDTO(
                        c.get("brand", String.class),
                        c.get("qty", Long.class).intValue()
                )
        ).collect(Collectors.toList());
    }


    public List<QtyDecadeDTO> groupByYear() {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<VehicleEntity>root = cq.from(VehicleEntity.class);

        Expression<Number> filter = cb.prod(
                cb.quot(root.get("year"), 10),
                10
        );

        cq.multiselect(
                filter.alias("year"),
                cb.count(root).alias("qty")
        ).where(
                cb.and(
                        cb.isNotNull(root.get("year"))
                )
        ).groupBy(filter).orderBy(cb.asc(filter));

        List<Tuple> data = manager.createQuery(cq).getResultList();

        return data.stream().map(
                c-> new QtyDecadeDTO(
                        c.get("year", Integer.class),
                        c.get("qty", Long.class)
                )
        ).collect(Collectors.toList());
    }

}
