package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import play.data.validation.Constraints;
import play.db.ebean.Model;

@Entity 
public class Projeto extends Model {

	private static final long serialVersionUID = 1L;

	@Id
    public Long id;
    
    @Constraints.Required(message = "Informe o nome do projeto")
    public String nome;
    
    @Constraints.Required(message = "Este campo é obrigatório")
    public String descricao;
    
    public Date data_inicio;
    
    public Date data_fim;   
    
    @ManyToMany
    public List<Analista> analistas = new ArrayList<Analista>();
        
    public static Model.Finder<Long,Projeto> find = new Model.Finder<Long,Projeto>(Long.class, Projeto.class);
    
}

