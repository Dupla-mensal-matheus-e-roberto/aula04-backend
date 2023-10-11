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
        List<Carro> lista = carroRepository.findAll();
        List<CarroDTO> listaDTO = new ArrayList<>();

        for(int i=0; i< lista.size(); i++){
            listaDTO.add(this.toCarroDTO(lista.get(i)));
        }

        return listaDTO;
    }

    public CarroDTO save(CarroDTO carroDTO){
        Carro carro = this.toCarro(carroDTO);

        Carro carrosalvo = carroRepository.save(carro);

        return this.toCarroDTO(carrosalvo);
    }

    public CarroDTO update(CarroDTO carroDTO, Long id){
        Carro carro = this.toCarro(carroDTO);

        Carro carrosalvo = carroRepository.findById(id).orElse(null);

        carrosalvo = carro;

        Carro carroeditado = carroRepository.save(carrosalvo);

        return this.toCarroDTO(carroeditado);
    }

    public String delete(Long id){
        Carro carrosalvo = carroRepository.findById(id).orElse(null);

        this.carroRepository.delete(carrosalvo);

        return "Carro deletado com sucesso";
    }

    private CarroDTO toCarroDTO(Carro carro){
        CarroDTO carroDTO = new CarroDTO();
        carroDTO.setId(carro.getId());
        carroDTO.setNome(carro.getNome());
        carroDTO.setAno(carro.getAno());
        return carroDTO;
    }

    private Carro toCarro(CarroDTO carroDTO){
        Carro carro = new Carro();
        carro.setId(carroDTO.getId());
        carro.setNome(carroDTO.getNome());
        carro.setAno(carroDTO.getAno());
        return carro;
    }

}
