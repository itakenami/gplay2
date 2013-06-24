package models;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints;
import play.db.ebean.Model;

@Entity 
public class Cargo extends Model {

	private static final long serialVersionUID = 1L;

	@Id
    public Long id;
    
    @Constraints.Required(message = "Este campo é obrigatório")
    public String nome;
        
    public static Model.Finder<Long,Cargo> find = new Model.Finder<Long,Cargo>(Long.class, Cargo.class); 
    
    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Cargo c: Cargo.find.orderBy("nome").findList()) {
            options.put(c.id.toString(), c.nome);
        }
        return options;
    }
    
    @Override
    public String toString() {
    	return nome;
    }
    
}

