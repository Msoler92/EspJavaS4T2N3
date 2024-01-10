package cat.itacademy.barcelonactiva.solereina.manel.s04.t02.n03.model.services;

import cat.itacademy.barcelonactiva.solereina.manel.s04.t02.n03.model.domain.Fruita;
import cat.itacademy.barcelonactiva.solereina.manel.s04.t02.n03.model.exceptions.NotFoundException;
import cat.itacademy.barcelonactiva.solereina.manel.s04.t02.n03.model.repository.FruitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FruitaService {

    @Autowired
    FruitaRepository fruitaRepository;

    public Fruita save(Fruita fruita) throws IllegalArgumentException, OptimisticLockingFailureException {
        Fruita returnObject;
        returnObject = fruitaRepository.save(fruita);
        return returnObject;
    }

    public Fruita findById(int id) throws NotFoundException {
        Optional<Fruita> fruitaData = fruitaRepository.findById(id);

        if (fruitaData.isEmpty()) {
            throw new NotFoundException();
        }
        return fruitaData.get();
    }

    public Fruita update(int id, Fruita fruita) throws NotFoundException {
        Fruita fruitaData = findById(id);
        fruitaData.setNom(fruita.getNom());
        fruitaData.setQuantitatQuilos(fruita.getQuantitatQuilos());
        return save(fruitaData);
    }

    public List<Fruita> findAll() {
        return new ArrayList<>(fruitaRepository.findAll());
    }

    public void deleteById(int id) throws NotFoundException, IllegalArgumentException {
        findById(id);
        fruitaRepository.deleteById(id);
    }
}
