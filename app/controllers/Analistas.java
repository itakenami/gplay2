package controllers;

import java.util.List;

import models.Analista;
import models.Cargo;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import util.Binder;
import views.html.analistas.addForm;
import views.html.analistas.editForm;
import views.html.analistas.index;

public class Analistas extends Controller {

	public static Result index() {
		List<Analista> analistas = Analista.find.all();	
		return ok(index.render(analistas));
	}
	
	public static Result edit(Long id) {
		Analista analista = Analista.find.byId(id);
		Form<Analista> analistaForm = Binder.form(Analista.class).fill(analista);

		return ok(editForm.render(id,analistaForm, Cargo.options()));
	}

	public static Result add() {
		Form<Analista> analistaForm = Binder.form(Analista.class);
		return ok(addForm.render(analistaForm, Cargo.options()));
	}
	
	public static Result save() {
		Form<Analista> analistaForm = Binder.form(Analista.class).bindFromRequest();
		
        if(analistaForm.hasErrors()) {
            return badRequest(addForm.render(analistaForm, Cargo.options()));
        }
        
        Analista a = analistaForm.get();
	    a.save();
		
		flash("success", "Analista " + analistaForm.get().nome + " criado com sucesso!!");
		return redirect("/analistas");
	}

	public static Result update(Long id) {
		Form<Analista> analistaForm = Binder.form(Analista.class).bindFromRequest();
		
        if(analistaForm.hasErrors()) {
            return badRequest(editForm.render(id,analistaForm, Cargo.options()));
        }
        
        Analista a = analistaForm.get();
		a.update(id);
		
		flash("success", "Analista " + analistaForm.get().nome + " atualizado com sucesso!!");
		return redirect("/analistas");
	}
	
	public static Result delete(Long id) {	
		Analista analista = Analista.find.byId(id);
		analista.delete();
		flash("success", "Analista excluido com sucesso!!!");
		return redirect("/analistas");
	}

}