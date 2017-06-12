package it.uniroma3.spring.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "autori")
public class Autore {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	@NotNull
	@Size(min = 1)
	private String nome;
	@Column(nullable = false)
	@NotNull
	@Size(min = 1)
	private String cognome;
	@Column(nullable = false)
	@NotNull
	@Size(min = 1)
	private String nazionalita;
	@Column(nullable = false)
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date data_nascita;
	@Temporal(TemporalType.DATE)
	private Date data_morte;
	@OneToMany(mappedBy = "autore", cascade = {CascadeType.REMOVE})
	private List<Opera> opere;

	public Autore() {
	}

	public void addOpera(Opera o){
		opere.add(o);
	}
	
	public void removeOpera(Opera o){
		opere.remove(o);
	}
	
	public List<Opera> getOpere(){
		return opere;
	}
	
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public String getNazionalita() {
		return nazionalita;
	}

	public Date getData_nascita() {
		return data_nascita;
	}

	public Date getData_morte() {
		return data_morte;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public void setNazionalita(String nazionalita) {
		this.nazionalita = nazionalita;
	}
	
	public void setData_nascita(Date dataNascita) {
		this.data_nascita = dataNascita;
	}

	public void setData_morte(Date dataMorte) {
		this.data_morte = dataMorte;
	}

}
