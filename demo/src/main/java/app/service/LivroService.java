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
        List<Livro> livros = livroRepository.findAll();
        List<LivroDTO> livroDTOList = new ArrayList<>();

        for(Livro value: livros){
            livroDTOList.add(this.toLivroDTO(value));
        }

        return livroDTOList;
    }

    public LivroDTO save(LivroDTO livroDTO){
        Livro livro = this.toLivro(livroDTO);

        Livro livroSalvar = livroRepository.save(livro);

        return this.toLivroDTO(livroSalvar);
    }

    public LivroDTO editar(Long id, LivroDTO livroDTO){
        Livro livroBanco = livroRepository.findById(id).orElse(null);

        Assert.isTrue(livroBanco != null, "Livro nao encontrado");

        Livro livro = toLivro(livroDTO);

        Livro livroSalvar = livroRepository.save(livro);

        return this.toLivroDTO(livroSalvar);
    }


    public LivroDTO deletar(Long id){
        Livro livroBanco =  livroRepository.findById(id).orElse(null);

        Assert.isTrue(livroBanco != null, "Livro nao encontrado");

        livroRepository.delete(livroBanco);

        return this.toLivroDTO(livroBanco);
    }


    public LivroDTO toLivroDTO(Livro livro){
        LivroDTO livroDTO = new LivroDTO();

        livroDTO.setId(livro.getId());
        livroDTO.setTitulo(livro.getTitulo());
        livroDTO.setAutor(livro.getAutor());

        return livroDTO;
    }

    public Livro toLivro(LivroDTO livroDTO){
        Livro livro = new Livro();

        livro.setId(livroDTO.getId());
        livro.setTitulo(livroDTO.getTitulo());
        livro.setAutor(livroDTO.getAutor());

        return livro;
    }
}
