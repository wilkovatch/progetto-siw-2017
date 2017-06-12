package it.uniroma3.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.spring.model.Autore;
import it.uniroma3.spring.model.Opera;
import it.uniroma3.spring.repository.AutoreRepository;
import it.uniroma3.spring.repository.OperaRepository;

@Service
public class OperaService {

    @Autowired
    private OperaRepository operaRepository; 

    public Iterable<Opera> findAll() {
        return this.operaRepository.findAll();
    }
    
    public Iterable<Opera> findByAutore(Autore a) {
        return this.operaRepository.findByAutore(a);
    }

    @Transactional
    public void add(final Opera opera) {
        this.operaRepository.save(opera);
    }

    public void remove(final Long idOpera) {
        this.operaRepository.delete(idOpera);
    }
    
	public Opera findbyId(Long id) {
		return this.operaRepository.findOne(id);
	}

}
