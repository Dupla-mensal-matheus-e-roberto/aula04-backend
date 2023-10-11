package app.service;

import app.dto.LivroDTO;
import app.entity.Livro;
import app.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public List<LivroDTO> listAll(){
        List<Livro> lista = livroRepository.findAll();
        List<LivroDTO> listaDTO = new ArrayList<>();

        for (Livro livro : lista) {
            listaDTO.add(this.toLivroDTO(livro));
        }
        return listaDTO;
    }

    public LivroDTO save(LivroDTO livroDTO) {
        Livro livro = this.toLivro(livroDTO);
        return this.toLivroDTO(livroRepository.save(livro));
    }

    public LivroDTO update(LivroDTO livroDTO, Long id){
        Livro livro = this.toLivro(livroDTO);

        Livro livroSalvo = livroRepository.findById(id).orElse(null);

        Assert.isTrue(livroSalvo != null, "Livro Inválido");

        livroSalvo = livro;

        return this.toLivroDTO(livroRepository.save(livroSalvo));

    }

    public LivroDTO delete(Long id){
        Livro livro = livroRepository.findById(id).orElse(null);

        Assert.isTrue(livro != null, "Livro inválido");

        this.livroRepository.delete(livro);

        return toLivroDTO(livro);
    }

    private LivroDTO toLivroDTO(Livro livro){
        LivroDTO livroDTO = new LivroDTO();
        livroDTO.setId(livro.getId());
        livroDTO.setTitulo(livro.getTitulo());
        livroDTO.setAutor(livro.getAutor());
        return livroDTO;
    }

    private Livro toLivro(LivroDTO livroDTO){
        Livro livro = new Livro();
        livro.setId(livroDTO.getId());
        livro.setTitulo(livroDTO.getTitulo());
        livro.setAutor(livroDTO.getAutor());
        return livro;
    }


}
