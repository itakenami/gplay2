package controllers;

import java.util.List;

import models.Analista;
import models.Projeto;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import util.Binder;
import views.html.projetos.*;

import com.avaje.ebean.Ebean;

public class Projetos extends Controller {
 
	public static Result index() {
		List<Projeto> projetos = Projeto.find.all();	
		return ok(index.render(projetos));
	}

    public static Result buscar() {

        DynamicForm data = Binder.form().bindFromRequest();
        String filtro = data.get("filtro");

        List<Projeto> projetos = Projeto.find.where().ilike("nome","%"+filtro+"%").findList();
        return ok(index.render(projetos));
    }
	
	public static Result edit(Long id) {
		Projeto projeto = Projeto.find.byId(id);
		Form<Projeto> projetoForm = Binder.form(Projeto.class).fill(projeto);
		return ok(editForm.render(id, projetoForm, Analista.options()));
	}

    public static Result view(Long id) {
        Projeto projeto = Projeto.find.byId(id);
        return ok(view.render(projeto));
    }

	public static Result add() {
		Form<Projeto> projetoForm = Binder.form(Projeto.class);
		return ok(addForm.render(projetoForm, Analista.options()));
	}
	
	public static Result save() {
		
		Form<Projeto> projetoForm = Binder.form(Projeto.class).bindFromRequestMulti();
		
        if(projetoForm.hasErrors()) {
            return badRequest(addForm.render(projetoForm, Analista.options()));
        }
        
        Projeto projeto = projetoForm.get();
        
        projeto.save();
        projeto.saveManyToManyAssociations("analistas");
		
		flash("success", "Projeto " + projetoForm.get().nome + " criado com sucesso!!");
		return redirect("/projetos");
	}

	public static Result update(Long id) {

		Form<Projeto> projetoForm = Binder.form(Projeto.class).bindFromRequestMulti();
		
		
        if(projetoForm.hasErrors()) {
            return badRequest(editForm.render(id, projetoForm, Analista.options()));
        }
        
        Projeto projeto = projetoForm.get();
        
        projeto.update(id);
		//Apaga todos os registros da relao
		//Ebean.deleteManyToManyAssociations(projeto,"analistas");
		//projeto.saveManyToManyAssociations("analistas");
		
		flash("success", "Projeto " + projetoForm.get().nome + " atualizado com sucesso!!");
		return redirect("/projetos");
	}
	
	public static Result delete(Long id) {	
		Projeto projeto = Projeto.find.byId(id);
		Ebean.deleteManyToManyAssociations(projeto,"analistas");
		projeto.delete();
		flash("success", "Projeto excluido com sucesso!!!");
		return redirect("/projetos");
	}

}