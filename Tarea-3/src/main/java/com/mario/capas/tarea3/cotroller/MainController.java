package com.mario.capas.tarea3.cotroller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	@RequestMapping("/ingresar")
	public String index() {
		/*
		 * No es necesario poner la extension .html a index, ya que el metodo
		 * templateResolver.setSuffix(".html") sirve para asumir dicha extension. Como
		 * solo vamos a redirigir a la pagina index.html no es necesario un objeto
		 * ModelAndView, al devolver un tipo de dato String, Spring automaticamente
		 * asume que es una vista la que se quiere devolver.
		 */
		return "index";
	}

	@RequestMapping("/parametros")
	public ModelAndView parametros2(@RequestParam String name, @RequestParam String lname, @RequestParam String bdate,
			@RequestParam String dicc, @RequestParam String coll, @RequestParam String hphone,
			@RequestParam String mphone) throws ParseException {
		ModelAndView mav = new ModelAndView();

		List<String> errList = new ArrayList<>();

		Date bDate = new SimpleDateFormat("yyyy-MM-DD").parse(bdate);
		Date lDate = new SimpleDateFormat("DD/MM/yyyy").parse("01/01/2003");

		if (name.length() > 25 || name.isEmpty()) {
			errList.add("El campo Nombres debe de tener como mínimo 1 carácter y máximo 25 caracteres</p>");
		}
		if (lname.length() > 25 || lname.isEmpty()) {
			errList.add("El campo Apellidos debe de tener como mínimo 1 carácter y máximo 25 caracteres");
		} else if (bDate.before(lDate)) {
			errList.add("La Fecha de Nacimiento no puede ser menor al 1 de enero de 2003");
		}
		if (bdate.length() > 25 || bdate.isEmpty()) {
			errList.add("El campo Lugar de Nacimiento debe de tener como mínimo 1 carácter y máximo 25 caracteres");
		}
		if (bDate.after(lDate)) {

		}
		if (coll.length() > 100 || coll.isEmpty()) {
			errList.add("El campo Intituto o Colegio debe de tener como mínimo 1 carácter y máximo 100 caracteres");
		}

		if (hphone.length() != 8) {
			errList.add("El campo Telefono fijo debe de tener 8 números exactamente");
		}

		if (mphone.length() != 8) {
			errList.add("El campo Telefono Movil debe de tener 8 números exactamente");
		}

		mav.addObject("errores", errList);

		if (errList.isEmpty()) {
			mav.setViewName("/ingresadoconexito");
		} else {
			mav.setViewName("/error");
		}

		return mav;
	}

}
