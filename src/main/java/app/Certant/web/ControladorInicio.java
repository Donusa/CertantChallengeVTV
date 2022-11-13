package app.Certant.web;

import app.Certant.Services.iDbidataService;
import app.Certant.Services.iDuenioService;
import app.Certant.Services.iInspeccionService;
import app.Certant.Services.iVehiculoService;
import app.Certant.Util.InspeccionData;
import app.Certant.domain.Dbidata;
import app.Certant.domain.Duenio;
import app.Certant.domain.Inspeccion;
import app.Certant.domain.Inspector;
import app.Certant.domain.Vehiculo;
import java.sql.Date;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ControladorInicio {

    @Autowired
    private iVehiculoService vehiculoService;

    @Autowired
    private iDuenioService duenioService;

    @Autowired
    private iDbidataService dbidataService;
    
    @Autowired
    private iInspeccionService inspeccionService;
    
    private Duenio duenioActual;
    private Vehiculo vehiculoActual;
    private Inspeccion inspeccionActual;
    
    @GetMapping("/")
    public String inicio() {
        return "index";
    }

    @GetMapping("/ListadoAutomotor")
    public String listadoVehiculos(Model model) {
        var vehiculos = vehiculoService.listarVehiculos();
        model.addAttribute("vehiculos", vehiculos);
        return "ListadoAutomotr";
    }

    @GetMapping("/ListadoDuenios")
    public String listadoPropietarios(Model model) {
        var propietarios = duenioService.listarDuenios();
        model.addAttribute("propietarios", propietarios);
        return "ListadoDuenios";
    }

    @GetMapping("/modificarDuenio")
    public String modificarPropietario(Duenio duenio) {
        return "modificarDuenio";
    }

    @GetMapping("/modificarVehiculo")
    public String modificarVehiculo(Vehiculo vehiculo) {
        return "modificarVehiculo";
    }
    
    @GetMapping("/InspeccionarVehiculo/{idVehiculo}")
    public String realizarInspeccion(Vehiculo vehiculo,Dbidata dbidata, Model model){
        vehiculo = vehiculoService.encontrarVehiculo(vehiculo);
        vehiculoActual = vehiculo;
        var inspecData = inspeccionService.listarInspecciones();
        java.util.Date dateJava = new java.util.Date();
        java.sql.Date date = new Date(dateJava.getTime());
        for(Inspeccion i : inspecData){
            long añosDiff = 
                    ((i.getFechaInspeccion().getTime()-date.getTime())
                        / (1000l * 60 * 60 * 24 * 365));
            if(i.getVehiculo().equals(vehiculoActual)
                    && (i.getFechaInspeccion().compareTo(dateJava)==0
                        || añosDiff<=1 )){
                inspeccionActual = inspeccionService.encontrarInspeccion(i);
                model.addAttribute("inspeccion", inspeccionActual);
                if(i.getInspeccionExcento()==1)return "revision";
                else i.setInspeccionExcento((short)0);
            }
        }
        model.addAttribute("idata",dbidata);
        model.addAttribute("vehiculo", vehiculo);
        return "Inspeccion";
    }
    
    @GetMapping("/VehiculosRegistrados/{idDuenio}")
    public String seleccionVehiculoParaInspeccion(Duenio duenio, Vehiculo vehiculo, Model model) {
        
        duenio = duenioService.encontrarDuenio(duenio);
        var listaVehiculos = vehiculoService.listarVehiculos();
        
        model.addAttribute("duenio",duenio);
        model.addAttribute("listaVehiculos", listaVehiculos);
        return "InspeccionAutomotor";
    }

    @GetMapping("/editar/{idDuenio}")
    public String editar(Duenio duenio, Model model) {
        duenio = duenioService.encontrarDuenio(duenio);
        model.addAttribute("duenio", duenio);
        return "modificarDuenio";
    }

    @GetMapping("/editarVehiculo/{idVehiculo}")
    public String editarVehiculo(Vehiculo vehiculo, Model model) {
        vehiculo = vehiculoService.encontrarVehiculo(vehiculo);

        duenioActual = duenioService.encontrarDuenio(vehiculo.getDuenio());

        model.addAttribute("vehiculo", vehiculo);
        return "modificarVehiculo";
    }
    
    @GetMapping("/nuevo/{idDuenio}")
    public String nuevoVehiculo(Duenio duenio, Vehiculo vehiculo) {
        duenioActual = duenio;
        return "modificarVehiculo";
    }

    @GetMapping("/eliminar/{idVehiculo}")
    public String eliminar(Vehiculo vehiculo) {
        vehiculoService.eliminar(vehiculo);
        return "redirect:/ListadoAutomotor";
    }

    @PostMapping("/guardadoPostRevision")
    public String guardarRevision(Inspeccion inspeccion){
        inspeccionActual.setEstado(inspeccion.getEstado());
        System.out.println(inspeccionActual.getEstado()+"<-inspeccionActual//inspeccion->"+inspeccion.getEstado());
        inspeccionService.guardar(inspeccionActual);
        return "redirect:/";
    }
    
    @PostMapping("/idataManagment")
    public String guardarEvaluacion(Dbidata idata, Vehiculo vehiculo, Inspeccion inspeccion, Inspector inspector){
        vehiculo = vehiculoService.encontrarVehiculo(vehiculoActual);// seteo vehiculo
        
        inspeccion.setDbidata(idata); //seteo la referencia a idata
        
        String estado = InspeccionData.dbidataApto(idata);
        inspeccion.setEstado(estado);
        
        // evaluo el estado para saber si se le otorga la oblea condicional para que este excento de su siguiente inspeccion
        inspeccion.setInspeccionExcento(estado.equals("condicional")?(short)1:(short)0); 
        java.util.Date dateJava = new java.util.Date(); //obtengo fecha
        java.sql.Date dateSQL = new java.sql.Date(dateJava.getTime()); //transformo fecha a formato de sql
        inspeccion.setFechaInspeccion(dateSQL); //inserto la fecha
        inspeccion.setObservaciones(InspeccionData.dbidataObs(idata));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        inspeccion.setInspector(name);
        inspeccion.setVehiculo(vehiculo);
        dbidataService.guardar(idata);
        inspeccionService.guardar(inspeccion);
        return "redirect:/";
    }
    
    @PostMapping("/guardarVehiculo")
    public String guardarVehiculo(@Valid Vehiculo vehiculo, Errors errors) {
        if (errors.hasErrors()) {
            return "modificarVehiculo";
        }
        vehiculo.setDuenio(duenioActual);
        vehiculoService.guardar(vehiculo);
        return "redirect:/ListadoAutomotor";
    }

    @PostMapping("/guardar")
    public String guardarDuenio(@Valid Duenio duenio, Errors errors) {

        try {
            if (errors.hasErrors()) {
                return "modificarDuenio";
            }

            duenioService.guardar(duenio);
            return "redirect:/ListadoDuenios";
        } catch (DataIntegrityViolationException e) {
            duenio.setDoc("");
            return "modificarDuenio"; 
        }
    }
}
