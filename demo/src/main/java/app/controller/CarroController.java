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
@CrossOrigin(origins = "*")
public class CarroController {

    @Autowired
    private CarroService carroService;


    @GetMapping
    public ResponseEntity<List<CarroDTO>> listAll(){
        try{
            List<CarroDTO> lista = carroService.listAll();
            return new ResponseEntity<>(lista, HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping
    private ResponseEntity<CarroDTO> save(@RequestBody CarroDTO carroDTO){
        try{
            CarroDTO carroSalvar = carroService.save(carroDTO);
            return  new ResponseEntity<>(carroSalvar, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/editar/{id}")
    private ResponseEntity<CarroDTO> editar(@RequestBody CarroDTO carroDTO, @PathVariable("id") Long id){
        try{
            CarroDTO carroEditar = carroService.editar(id,carroDTO);
            return new ResponseEntity<>(carroEditar, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<CarroDTO> deletar(@PathVariable("id") Long id){
        try{
            CarroDTO carroDeletar = carroService.deletar(id);
            return new ResponseEntity<>(carroDeletar,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


}
