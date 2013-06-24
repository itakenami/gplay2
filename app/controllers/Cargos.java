package controllers;

import java.util.List;

import models.Cargo;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import util.Binder;
import views.html.cargos.addForm;
import views.html.cargos.editForm;
import views.html.cargos.index;

public class Cargos extends Controller {

	public static Result index() {
		List<Cargo> cargos = Cargo.find.all();	
		return ok(index.render(cargos));
	}
	
	public static Result edit(Long id) {
		Cargo cargo = Cargo.find.byId(id);
		Form<Cargo> cargoForm = Binder.form(Cargo.class).fill(cargo);
		return ok(editForm.render(id,cargoForm));
	}

	public static Result add() {
		Form<Cargo> cargoForm = Binder.form(Cargo.class);
		return ok(addForm.render(cargoForm));
	}
	
	public static Result save() {
		Form<Cargo> cargoForm = Binder.form(Cargo.class).bindFromRequest();
		
        if(cargoForm.hasErrors()) {
            return badRequest(addForm.render(cargoForm));
        }
        
        Cargo c = cargoForm.get();
	    c.save();
		
		flash("success", "Cargo " + cargoForm.get().nome + " criado com sucesso!!");
		return redirect("/cargos");
	}

	public static Result update(Long id) {
		Form<Cargo> cargoForm = Binder.form(Cargo.class).bindFromRequest();
		
        if(cargoForm.hasErrors()) {
            return badRequest(editForm.render(id,cargoForm));
        }
        
        Cargo c = cargoForm.get();
		c.update(id);
		
		flash("success", "Cargo " + cargoForm.get().nome + " atualizado com sucesso!!");
		return redirect("/cargos");
	}
	
	public static Result delete(Long id) {	
		Cargo cargo = Cargo.find.byId(id);
		
		cargo.delete();
		
		flash("success", "Cargo excluido com sucesso!!!");
		return redirect("/cargos");
	}

}