package ui;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import mx.desarrollo.entity.Asignacion;
import mx.desarrollo.entity.Profesor;
import mx.desarrollo.facade.FacadeProfesor;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Named("consultaUI")
@ViewScoped
public class ConsultaUI implements Serializable {

    private List<Profesor> profesores;
    private String busqueda;
    private Profesor seleccionado;

    @PostConstruct
    public void init(){
        FacadeProfesor facadeProfesor = new FacadeProfesor();
        profesores = new ArrayList<>(facadeProfesor.findAll());
        profesores.sort(Comparator.comparing(Profesor::getNombre));
        if(!profesores.isEmpty()){
            seleccionado = profesores.get(0);
        }
    }

    public List<Profesor> getProfesoresFiltrados(){
        if(busqueda == null || busqueda.isBlank()){
            return profesores;
        }
        String filtro = busqueda.toLowerCase();
        List<Profesor> resultado = new ArrayList<>();
        for(Profesor p : profesores){
            String nombreCompleto = (p.getNombre() + " " + p.getAppaterno() + " " + p.getApmaterno()).toLowerCase();
            if(nombreCompleto.contains(filtro)){
                resultado.add(p);
            }
        }
        return resultado;
    }

    public void seleccionar(Profesor p){
        this.seleccionado = p;
    }

    public int totalAsignaciones(Profesor p){
        return p.getAsignaciones().size();
    }

    public double totalHoras(Profesor p){
        double total = 0;
        for(Asignacion a : p.getAsignaciones()){
            total += Duration.between(a.getHoraInicio(), a.getHoraFin()).toMinutes() / 60.0;
        }
        return total;
    }

    public String gruposAsignados(Profesor p){
        List<String> grupos = new ArrayList<>();
        for(Asignacion a : p.getAsignaciones()){
            String grupo = a.getGrupo();
            if(!grupos.contains(grupo)){
                grupos.add(grupo);
            }
        }
        return String.join(", ", grupos);
    }

    public String unidadesAsignadas(Profesor p){
        List<String> unidades = new ArrayList<>();
        for(Asignacion a : p.getAsignaciones()){
            String nombreUnidad = a.getIdUnidad().getNombre();
            if(!unidades.contains(nombreUnidad)){
                unidades.add(nombreUnidad);
            }
        }
        return String.join(", ", unidades);
    }

    public List<String> getDias(){
        return Arrays.asList("Lunes","Martes","Miercoles","Jueves","Viernes","Sabado");
    }

    public List<LocalTime> getHoras(){
        List<LocalTime> lista = new ArrayList<>();
        LocalTime t = LocalTime.of(7,0);
        while(!t.isAfter(LocalTime.of(22,0))){
            lista.add(t);
            t = t.plusHours(1);
        }
        return lista;
    }

    public boolean estaOcupado(String dia, LocalTime hora){
        return !claseEn(dia, hora).isEmpty();
    }

    public String claseEn(String dia, LocalTime hora){
        if(seleccionado == null){
            return "";
        }
        for(Asignacion a : seleccionado.getAsignaciones()){
            if(a.getDiaSemana().equalsIgnoreCase(dia)
                    && !hora.isBefore(a.getHoraInicio())
                    && hora.isBefore(a.getHoraFin())){
                return a.getIdUnidad().getNombre();
            }
        }
        return "";
    }

    public List<Profesor> getProfesores() {
        return profesores;
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public Profesor getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Profesor seleccionado) {
        this.seleccionado = seleccionado;
    }

}
