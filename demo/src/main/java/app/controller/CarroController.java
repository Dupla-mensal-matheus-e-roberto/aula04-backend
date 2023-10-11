package app.controller;

import app.dto.CarroDTO;
import app.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carro")
@CrossOrigin(origins = "http://localhost:4200")
public class CarroController {

    @Autowired
    private CarroService carroService;

    @GetMapping
    private ResponseEntity<List<CarroDTO>> listAll(){
        try {
            List<CarroDTO> lista = carroService.listAll();
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    private ResponseEntity<CarroDTO> save(@RequestBody CarroDTO carroDTO) {
        try {
            CarroDTO carrosalvo = carroService.save(carroDTO);
            return new ResponseEntity<>(carrosalvo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    private ResponseEntity<CarroDTO> update(@RequestBody CarroDTO carroDTO, @PathVariable Long id){
        try{
            CarroDTO carroEditar = carroService.update(carroDTO, id);
            return new ResponseEntity<>(carroEditar, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    private ResponseEntity<String> delete(@PathVariable Long id){
        try{
            String carroDeletar = carroService.delete(id);
            return new ResponseEntity<>(carroDeletar, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("erro")
    private ResponseEntity<List<CarroDTO>> exemploErro(){
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

}
