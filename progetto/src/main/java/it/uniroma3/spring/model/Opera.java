package it.uniroma3.spring.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "opere")
public class Opera {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	@NotNull
	@Size(min = 1)
	private String titolo;
	@Column(nullable = false)
	@NotNull
	private Integer anno;
	@Column(nullable = false)
	@NotNull
	@Size(min = 1)
	private String tecnica;
	@Column(nullable = false)
	@NotNull
	@Size(min = 1)
	private String dimensioni;
    @Column(name="immagine")
    private byte[] immagine;
	@ManyToOne
	private Autore autore;

	public Opera() {

	}

	public Long getId() {
		return id;
	}

	public String getTitolo() {
		return titolo;
	}

	public Integer getAnno() {
		return anno;
	}

	public String getTecnica() {
		return tecnica;
	}

	public String getDimensioni() {
		return dimensioni;
	}
	
	public byte[] getImmagine(){
		return this.immagine;
	}
	
	public Autore getAutore(){
		return autore;
	}
	
	public void setTitolo(String titolo) {
		this.titolo=titolo;
	}

	public void setAnno(Integer anno) {
		this.anno=anno;
	}

	public void setTecnica(String tecnica) {
		this.tecnica= tecnica;
	}

	public void setDimensioni(String dimensioni) {
		this.dimensioni=dimensioni;
	}
	
	public void setImmagine(byte[] immagine){
		this.immagine=immagine;
	}
	
	public void setAutore(Autore autore){
		this.autore=autore;
	}

}
