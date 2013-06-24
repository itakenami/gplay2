package models;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.data.Form;
import play.data.Form.Field;
import play.data.validation.Constraints;
import play.db.ebean.Model;

@Entity 
public class Analista extends Model {

	private static final long serialVersionUID = 1L;

	@Id
    public Long id;
    
    @Constraints.Required(message = "Informe o nome")
    public String nome;
    
    @Constraints.Required(message = "Informe a especialidade")
    public String especialidade;
    
    @ManyToOne
    public Cargo cargo;
        
    public static Model.Finder<Long,Analista> find = new Model.Finder<Long,Analista>(Long.class, Analista.class);
    
    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Analista a: Analista.find.orderBy("nome").findList()) {
            options.put(a.id.toString(), a.nome);
        }
        return options;
    }
    
    
    public static Field lista() {
    	Form<Analista> frm = new Form<Analista>(Analista.class);
    	frm.bind(options(), "analistas");
    	return frm.field("analistas");
    }
    
    @Override
    public String toString() {
    	return nome;
    }
    
    
    
}

