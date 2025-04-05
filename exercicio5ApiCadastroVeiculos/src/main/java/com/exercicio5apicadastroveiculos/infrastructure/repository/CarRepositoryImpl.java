package com.exercicio5apicadastroveiculos.infrastructure.repository;

import com.exercicio5apicadastroveiculos.domain.model.Car;
import com.exercicio5apicadastroveiculos.domain.repository.CarRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CarRepositoryImpl implements CarRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Car> Listar() {
        return manager.createQuery("from Car", Car.class).getResultList();
    }

    @Override
    public List<Car> FilterList(String marca, Integer ano, String cor, Boolean vendido) {
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

        cq.where(filters.toArray(new Predicate[0]));
        return manager.createQuery(cq).getResultList();

    }

    @Override
    public Car BuscarPorId(Long id) {
        return manager.find(Car.class, id);
    }

    @Transactional
    @Override
    public Car Salvar(Car car) {
        return manager.merge(car);
    }

    @Transactional
    @Override
    public void Deletar(Car car) {
        car = BuscarPorId(car.getId());
        manager.remove(car);
    }
}
