package app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dto.PessoaDTO;
import app.entity.Pessoa;
import app.repository.PessoaRepository;
import org.springframework.util.Assert;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	public List<PessoaDTO> listAll(){
		List<Pessoa> lista = pessoaRepository.findAll();
		List<PessoaDTO> listaDTO = new ArrayList<>();

		for(int i=0; i<lista.size(); i++) 
			listaDTO.add(this.toPessoaDTO(lista.get(i)));

		return listaDTO;
	}
	
	public PessoaDTO save(PessoaDTO pessoaDTO){
		Pessoa pessoa = this.toPessoa(pessoaDTO);

		Pessoa pessoasalva = pessoaRepository.save(pessoa);

		return this.toPessoaDTO(pessoasalva);
	}

	public PessoaDTO editar(Long id,PessoaDTO pessoaDTO){

		Pessoa pessoaBanco = pessoaRepository.findById(id).orElse(null);

//		Assert.isTrue(pessoaBanco != null, "pessoa nao encontrada");
//		Assert.isTrue(pessoaDTO.getNome().isBlank(), "nome vazio nao pode");
//		Assert.isTrue(pessoaDTO.getIdade() < 0, "idade menos que 0");

		Pessoa pessoa = toPessoa(pessoaDTO);

		Pessoa pessoaSalva = pessoaRepository.save(pessoa);

		return this.toPessoaDTO(pessoaSalva);

	}


	public PessoaDTO deletar(Long id){
		Pessoa pessoaBanco = pessoaRepository.findById(id).orElse(null);
		Assert.isTrue(pessoaBanco != null, "Pessoa nao encontrada");

		pessoaRepository.delete(pessoaBanco);

		return this.toPessoaDTO(pessoaBanco);
	}

	private PessoaDTO toPessoaDTO(Pessoa pessoa) {
		PessoaDTO pessoaDTO = new PessoaDTO();
		pessoaDTO.setId(pessoa.getId());
		pessoaDTO.setNome(pessoa.getNome());
		pessoaDTO.setIdade(pessoa.getIdade());
		return pessoaDTO;
	}
	
	private Pessoa toPessoa(PessoaDTO pessoaDTO) {
		Pessoa pessoa = new Pessoa();
		pessoa.setId(pessoaDTO.getId());
		pessoa.setNome(pessoaDTO.getNome());
		pessoa.setIdade(pessoaDTO.getIdade());
		return pessoa;
	}

}
