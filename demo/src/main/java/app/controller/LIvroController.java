package app.controller;

import app.dto.CarroDTO;
import app.dto.LivroDTO;
import app.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livro")
@CrossOrigin(origins = "*")
public class LIvroController {

    @Autowired
    private LivroService livroService;

    @GetMapping
    public ResponseEntity<List<LivroDTO>> listAll(){
        try{
            List<LivroDTO> lista = livroService.listAll();
            return new ResponseEntity<>(lista, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<LivroDTO> save(@RequestBody LivroDTO livroDTO){
        try{
            LivroDTO livroSalvar = livroService.save(livroDTO);
            return new ResponseEntity<>(livroSalvar, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/editar/{id}")
    private ResponseEntity<LivroDTO> editar(@RequestBody LivroDTO livroDTO, @PathVariable("id") Long id){
        try{
            LivroDTO livroEditar = livroService.editar(id,livroDTO);
            return new ResponseEntity<>(livroEditar, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<LivroDTO> deletar(@PathVariable("id") Long id){
        try{
            LivroDTO livroDeletar = livroService.deletar(id);
            return new ResponseEntity<>(livroDeletar,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }



}
