package com.exercicio5apicadastroveiculos.infrastructure.repository;

import com.exercicio5apicadastroveiculos.domain.model.Car;
import com.exercicio5apicadastroveiculos.domain.model.QtyDecade;
import com.exercicio5apicadastroveiculos.domain.model.QtyManufacturers;
import com.exercicio5apicadastroveiculos.domain.repository.CarRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarRepositoryImpl implements CarRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Car> listar() {
        return manager.createQuery("from Car", Car.class).getResultList();
    }

    @Override
    public List<Car> filterList(String marca, Integer ano, String cor, Boolean vendido) {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Car> cq = cb.createQuery(Car.class);
        Root<Car> root = cq.from(Car.class);

        List<Predicate> filters = new ArrayList<>();

        if (marca != null) {
            filters.add(cb.equal(root.get("marca"), marca));
        }
        if (ano != null) {
            filters.add(cb.equal(root.get("ano"), ano));
        }
        if (cor != null) {
            filters.add(cb.equal(root.get("cor"), cor));
        }
        if (vendido != null) {
            filters.add(cb.equal(root.get("vendido"), vendido));
        }

        cq.where(filters.toArray(new Predicate[0])).orderBy(cb.asc(root.get("id")));
        return manager.createQuery(cq).getResultList();

    }

    @Override
    public Car buscarPorId(Long id) {
        return manager.find(Car.class, id);
    }

    @Override
    public long countVendidos() {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class); // Corrigido aqui
        Root<Car> root = cq.from(Car.class);

        cq.select(cb.count(root)).where(cb.isTrue(root.get("vendido"))); // Adiciona condição

        return manager.createQuery(cq).getSingleResult();
    }

    @Override
    public List<Car> listarUltimosCadastrados() {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Car> cq = cb.createQuery(Car.class);
        Root<Car> root = cq.from(Car.class);

        List<Predicate> filters = new ArrayList<>();

        LocalDateTime dataInicio = LocalDateTime.now().minusDays(7);

        filters.add(cb.greaterThanOrEqualTo(root.get("created"), dataInicio));

        cq.where(filters.toArray(new Predicate[0]));
        cq.orderBy(cb.desc(root.get("created")));

        return manager.createQuery(cq).getResultList();

    }

    @Override
    public List<QtyManufacturers> groupByMarca() {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<Car> root = cq.from(Car.class);

        Path<String> filter = root.get("marca");

        cq.multiselect(
                filter.alias("marca"),
                cb.count(root).alias("qty")
        ).where(
                cb.and(
                        cb.isNotNull(filter),
                        cb.notEqual(cb.trim(cb.upper(filter)), "")
                )
        ).groupBy(filter).orderBy(cb.asc(filter));

        List<Tuple> data = manager.createQuery(cq).getResultList();

        return data.stream().map(
                c -> new QtyManufacturers(
                        c.get("marca", String.class),
                        c.get("qty", Long.class).intValue()
                )
        ).collect(Collectors.toList());

    }

    @Override
    public List<QtyDecade> groupByAno() {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<Car>root = cq.from(Car.class);

        Expression<Number> filter = cb.prod(
                cb.quot(root.get("ano"), 10),
                10
        );

        cq.multiselect(
                filter.alias("decada"),
                cb.count(root).alias("qty")
        ).where(
                cb.and(
                        cb.isNotNull(root.get("ano"))
                )
        ).groupBy(filter).orderBy(cb.asc(filter));

        List<Tuple> data = manager.createQuery(cq).getResultList();

        return data.stream().map(
                c-> new QtyDecade(
                        c.get("decada", Integer.class),
                        c.get("qty", Long.class)
                )
        ).collect(Collectors.toList());
    }


    @Transactional
    @Override
    public Car salvar(Car car) {
        return manager.merge(car);
    }

    @Transactional
    @Override
    public void deletar(Car car) {
        car = buscarPorId(car.getId());
        manager.remove(car);
    }
}
