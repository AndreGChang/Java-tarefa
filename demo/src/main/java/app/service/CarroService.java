package app.service;


import app.dto.CarroDTO;
import app.entity.Carro;
import app.repository.CarroRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    public List<CarroDTO> listAll(){
        List<Carro> carro = carroRepository.findAll();
        List<CarroDTO> carroDTO = new ArrayList<>();

        for (Carro value : carro) {
            carroDTO.add(this.toCarroDTO(value));
        }

//        for(int i = 0; i < carro.size(); i++){
//            carroDTO.add(this.toCarroDTO(carro.get(i)));
//        }

        return carroDTO;
    }


    public CarroDTO save(CarroDTO carroDTO){
        Carro carro = this.toCarro(carroDTO);

        Carro carroSalvar = carroRepository.save(carro);
        System.out.println("carro service");

        return this.toCarroDTO(carroSalvar);
    }


    public CarroDTO editar(Long id, CarroDTO carroDTO){

        Carro carroBanco = carroRepository.findById(id).orElse(null);

        Assert.isTrue(carroBanco != null, "carro nao encontrado");

        Carro carro = toCarro(carroDTO);

        Carro carroSalvar = carroRepository.save(carro);

        return this.toCarroDTO(carroSalvar);
    }


    public CarroDTO deletar(Long id){
        Carro carroBanco  = carroRepository.findById(id).orElse(null);

        Assert.isTrue(carroBanco != null, "Carro nao encontrado");

        carroRepository.delete(carroBanco);

        return this.toCarroDTO(carroBanco);

    }

    private Carro toCarro(CarroDTO carroDTO){
        Carro carro = new Carro();

        carro.setId(carroDTO.getId());
        carro.setNome(carroDTO.getNome());
        carro.setAno(carroDTO.getAno());

        return carro;
    }

    private CarroDTO toCarroDTO(Carro carro){
        CarroDTO carroDTO = new CarroDTO();

        carroDTO.setId(carro.getId());
        carroDTO.setNome(carro.getNome());
        carroDTO.setAno(carro.getAno());


        return carroDTO;
    }


}
