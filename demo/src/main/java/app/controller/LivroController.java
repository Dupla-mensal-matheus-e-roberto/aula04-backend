package app.controller;


import app.dto.LivroDTO;
import app.entity.Livro;
import app.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livro")
@CrossOrigin(origins = "http://localhost:4200")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping
    private ResponseEntity<List<LivroDTO>> listAll(){
        try{
            List<LivroDTO> lista = livroService.listAll();
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping
    private ResponseEntity<LivroDTO> save(@RequestBody LivroDTO livroDTO){
        try {
            LivroDTO livro = livroService.save(livroDTO);
            return new ResponseEntity<>(livro, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }


    }

    @PutMapping("/{id}")
    private ResponseEntity<LivroDTO> update(@RequestBody LivroDTO livroDTO, @PathVariable Long id){
        try{
            LivroDTO livro = livroService.update(livroDTO, id);
            return new ResponseEntity<>(livro, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<LivroDTO> delete(@PathVariable Long id){
        try{
            LivroDTO livro = livroService.delete(id);
            return new ResponseEntity<>(livro, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("erro")
    private ResponseEntity<List<LivroDTO>> exemploErro(){
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

}
