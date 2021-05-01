package com.elaborato.algoritmorsa.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.json.JSONArray;
import org.json.JSONObject;

@Entity
@Table(name="docente")
public class Docente {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int matricola;
    private String nome;
    private String cognome;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "citta_id")
    private Citta citta;
    
    @OneToMany(mappedBy = "docente", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Materia> pratica;
    
    public Docente(){
    }
    
    public Docente(String nome,String cognome){
    	this.nome = nome;
    	this.cognome= cognome;
    }
    
    
    public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}


    
    public Set<Materia> getPratica() {
		return pratica;
	}

	public void setPratica(Set<Materia> pratica) {
		this.pratica = pratica;
	}

	public String toString(){
    	String info = "";
        JSONObject jsonInfo = new JSONObject();
        jsonInfo.put("name",this.nome);
        jsonInfo.put("cognome",this.cognome);
        jsonInfo.put("matricola",this.matricola);
        JSONArray productArray = new JSONArray();
        if(this.pratica != null){
            this.pratica.forEach(product->{
                JSONObject subJson = new JSONObject();
                subJson.put("name", product.getArgomento());
                productArray.put(subJson);
            });
        }
        jsonInfo.put("products", productArray);
        info = jsonInfo.toString();
        return info;
    }
}