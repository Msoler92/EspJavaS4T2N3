package cat.itacademy.barcelonactiva.solereina.manel.s04.t02.n03.controllers;

import cat.itacademy.barcelonactiva.solereina.manel.s04.t02.n03.model.domain.Fruita;
import cat.itacademy.barcelonactiva.solereina.manel.s04.t02.n03.model.exceptions.NotFoundException;
import cat.itacademy.barcelonactiva.solereina.manel.s04.t02.n03.model.services.FruitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/fruita")
public class FruitaController {

    @Autowired
    FruitaService fruitaService;

    @PostMapping("/add")
    public ResponseEntity<Fruita> createFruita(@RequestBody Fruita fruita) {
        ResponseEntity<Fruita> returnResponse;
        Fruita newFruita;

        try {
            newFruita = fruitaService.save(fruita);
            returnResponse = new ResponseEntity<>(newFruita, HttpStatus.CREATED);
        } catch (IllegalArgumentException | OptimisticLockingFailureException e) {
            returnResponse = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return returnResponse;
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Fruita> updateFruita(@PathVariable("id") int id, @RequestBody Fruita fruita) {
        ResponseEntity<Fruita> returnResponse;
        Fruita fruitaData;

        try {
            fruitaData = fruitaService.update(id, fruita);
            returnResponse = new ResponseEntity<>(fruitaData, HttpStatus.OK);
        } catch (NotFoundException e) {
            returnResponse = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException | OptimisticLockingFailureException e) {
            returnResponse = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return returnResponse;
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Fruita> deleteFruita(@PathVariable("id") int id) {
        ResponseEntity<Fruita> returnResponse;

        try {
            fruitaService.deleteById(id);
            returnResponse = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            returnResponse = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            returnResponse = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return returnResponse;
    }
    @GetMapping("/getOne/{id}")
    public ResponseEntity<Fruita> getOneFruita(@PathVariable("id") int id) {
        ResponseEntity<Fruita> returnResponse;
        Fruita fruitaData;

        try {
            fruitaData = fruitaService.findById(id);
            returnResponse = new ResponseEntity<>(fruitaData, HttpStatus.OK);
        } catch (NotFoundException e) {
            returnResponse = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return returnResponse;
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Fruita>> getAllFruita() {
        ResponseEntity<List<Fruita>> returnResponse;
        List<Fruita> fruitaList = new ArrayList<>(fruitaService.findAll());

        if (fruitaList.isEmpty()) {
            returnResponse = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            returnResponse = new ResponseEntity<>(fruitaList, HttpStatus.OK);
        }
        return returnResponse;
    }
}
