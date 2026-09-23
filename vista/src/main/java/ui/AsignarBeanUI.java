package ui;

import helper.AsignarHelper;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import mx.desarrollo.entity.Administrador;
import mx.desarrollo.entity.Asignacion;
import mx.desarrollo.entity.UnidadAprendizaje;
import mx.desarrollo.entity.Profesor;


import java.io.Serializable;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

@Named ("AsignarUI")
@ViewScoped
public class AsignarBeanUI implements Serializable {


    private AsignarHelper helper;
    private List<Profesor> listaProfesores;
    private List<UnidadAprendizaje> listaUnidades;
    private List<Asignacion> listaAsignaciones;
    private Asignacion asignacion;
    private Integer idProfesorElegido, idUnidadElegida;



    private Integer idAsignacionSelec;


    private String diaSeleccionado;
    private String tipoSeleccionado;

    private String horaInicioTexto;
    private String horaFinTexto;

    @Inject
    private LoginBeanUI loginUI;

    @PostConstruct

    public void init(){
        try {
            helper = new AsignarHelper();
            this.listaProfesores = helper.obtenerProfesores();
            this.listaUnidades = helper.obtenerUnidades();
            this.listaAsignaciones=helper.obtenerAsignaciones();
            asignacion = new Asignacion();
        }catch (Throwable ex) {
            System.err.println("ERROR EN HIBERNATE: " + ex);
            ex.printStackTrace();
        }
    }

    public void borrarAsignacionBD (){
        try{
            if(idAsignacionSelec==null){mensajeError("No selecciono ninguna Asignacion");}

            helper.borrarAsignacion(idAsignacionSelec);

            this.listaAsignaciones = helper.obtenerAsignaciones();
            idAsignacionSelec =null;

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Exito","Unidad Desasignada"));
        }catch (Exception ex){
            mensajeError("No se pudo borrar la Asignacion");
        }
    }

    public List<Profesor> getListaProfesores (){
        return listaProfesores;
    }

    public List<UnidadAprendizaje> getListaUnidades (){
        return listaUnidades;
    }

    public List<Asignacion> getListaAsignaciones () {return listaAsignaciones;}

    public void setListaUnidades(List<UnidadAprendizaje> listaUnidades){
        this.listaUnidades = listaUnidades;

    }

    private void mensajeError(String textoMensaje){
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", textoMensaje));
    }

    public void guardarAsignacion(){
        try{

            if(idProfesorElegido == null){
                mensajeError("Favor de elegir un profesor");
                return;
            }
            if(idUnidadElegida == null){
                mensajeError("Favor de elegir una Unidad de Aprendizaje");
                return;
            }
            if(asignacion.getGrupo() == null){
                mensajeError("Favor de ingresar Grupo");
                return;
            }
            if(asignacion.getSemestre() == null){
                mensajeError("Ingresar Semestre");
                return;
            }

            if(tipoSeleccionado == null || tipoSeleccionado.isEmpty()){
                mensajeError("Favor de elegir el tipo de sesion (Clase, Taller o Laboratorio)");
                return;
            }

            if(asignacion.getHoraInicio() == null ||asignacion.getHoraFin() == null ){
                mensajeError("Ingrese hora de salida y hora fin");
                return;
            }

           if( asignacion.getHoraFin().isBefore(asignacion.getHoraInicio()) || asignacion.getHoraInicio().isAfter(asignacion.getHoraFin() )){
               mensajeError("La hora de clase no es valida");
               return;
           }

           if(asignacion.getHoraFin().isAfter(LocalTime.of(22, 0, 0)) ||
                   asignacion.getHoraInicio().isBefore(LocalTime.of(7,0,0)))
           {
               mensajeError("Horario fuera del limite establecido");
               return;
           }

            if(asignacion.getHoraInicio().getMinute() != 0|| asignacion.getHoraFin().getMinute()!=0){
                mensajeError("Ingrese unicamente horas \"en punto\" (en cero minutos)");
                return;
            }

            if(diaSeleccionado==null || diaSeleccionado.isEmpty()){
                mensajeError("Favor de elegir un dia de la semana");
                return;
            }

           Integer ayudaIdProfesor = idProfesorElegido;

           List<Asignacion> asignacionesExistentes = helper.obtenerAsignacionesProf(ayudaIdProfesor);
            LocalTime ayudaHInicio = asignacion.getHoraInicio();
            LocalTime ayudaHFin = asignacion.getHoraFin();

            for(Asignacion i : asignacionesExistentes){
               if(i.getDiaSemana().equals(diaSeleccionado)){
                   if(ayudaHInicio.isBefore(i.getHoraFin()) && ayudaHFin.isAfter(i.getHoraInicio()))
                   {
                       mensajeError("La hora ingresada se traslapa con otra materia registrada de " + i.getHoraInicio() + "-"+ i.getHoraFin());
                       return;
                   }
               }

           }

            UnidadAprendizaje unidadElegida = listaUnidades.stream().filter(u -> u.getId().equals(idUnidadElegida))
                            .findFirst().orElse(null);

            if(unidadElegida == null){
                mensajeError("No se encontro la Unidad de Aprendizaje seleccionada");
                return;
            }

            double horasNuevas = Duration.between(ayudaHInicio, ayudaHFin).toMinutes() / 60.0;

            if(horasNuevas > 4){
                mensajeError("Una sola sesion no puede durar mas de 4 horas");
                return;
            }

            double topeTipo;
            switch (tipoSeleccionado){
                case "Clase":
                    topeTipo = unidadElegida.getHorasClase();
                    break;
                case "Taller":
                    topeTipo = unidadElegida.getHorasTaller();
                    break;
                case "Laboratorio":
                    topeTipo = unidadElegida.getHorasLaboratorio();
                    break;
                default:
                    mensajeError("El tipo de sesion seleccionado no es valido");
                    return;
            }

            if(topeTipo > 4){
                topeTipo = 4;
            }

            double horasYaAsignadasTipo = 0;
            for(Asignacion a : listaAsignaciones){
                if(a.getIdUnidad().getId().equals(idUnidadElegida)
                        && a.getGrupo().equals(asignacion.getGrupo())
                        && a.getTipo().equals(tipoSeleccionado)){
                    horasYaAsignadasTipo += Duration.between(a.getHoraInicio(), a.getHoraFin()).toMinutes() / 60.0;
                }
            }

            if(horasYaAsignadasTipo + horasNuevas > topeTipo){
                double horasDisponibles = topeTipo - horasYaAsignadasTipo;
                mensajeError("El grupo " + asignacion.getGrupo() + " de esta unidad ya tiene " + horasYaAsignadasTipo
                        + " hrs asignadas de " + tipoSeleccionado + ". Solo quedan " + horasDisponibles
                        + " hrs disponibles de un tope de " + topeTipo + " hrs para este tipo de sesion.");
                return;
            }

            for(Asignacion a : listaAsignaciones){
                if(a.getIdUnidad().getId().equals(idUnidadElegida)
                        && a.getGrupo().equals(asignacion.getGrupo())
                        && a.getDiaSemana().equals(diaSeleccionado)
                        && !a.getTipo().equals(tipoSeleccionado)){
                    mensajeError("El dia " + diaSeleccionado + " ya tiene registrado " + a.getTipo()
                            + " para el grupo " + asignacion.getGrupo() + ". No se pueden mezclar tipos de sesion (clase, taller, laboratorio) el mismo dia, deben repartirse entre los dias de la semana.");
                    return;
                }
            }

            Administrador administrador = loginUI.getUsuario();
            asignacion.setIdAdministrador(administrador);

            Profesor profesorElegido = listaProfesores.stream().filter(p-> p.getId().equals(idProfesorElegido))
                            .findFirst().orElse(null);
            asignacion.setIdProfesor(profesorElegido);

            asignacion.setIdUnidad(unidadElegida);


    asignacion.setGrupo(asignacion.getGrupo());

    asignacion.setSemestre(asignacion.getSemestre());

    asignacion.setDiaSemana(diaSeleccionado);
    asignacion.setTipo(tipoSeleccionado);
    asignacion.setHoraInicio(asignacion.getHoraInicio());
    asignacion.setHoraFin(asignacion.getHoraFin());
    helper.registrarAsignacion(asignacion);
            this.listaAsignaciones = helper.obtenerAsignaciones();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Asignacion Registrada", "La asignacion fue registrada correctamente!"));
        } catch (Exception e){
            e.printStackTrace();
            mensajeError("No se pudo registrar la Asignatura");
        }
    }

    public void borrarAsignacion(){

    }

    private void reiniciarFormulario(){

        idProfesorElegido = null;
        idUnidadElegida = null;
        if(diaSeleccionado!=null)
        diaSeleccionado =null;
        tipoSeleccionado = null;

        asignacion = new Asignacion();
    }

    public Asignacion getAsignacion(){
        return asignacion;
    }

    public void setAsignacion(Asignacion asignacion){
        this.asignacion = asignacion;
    }

    public void setListaProfesores (List<Profesor> listaProfesores){
        this.listaProfesores = listaProfesores;
    }


    public Integer getIdProfesorElegido() {
        return idProfesorElegido;
    }

    public void setIdProfesorElegido(Integer idProfesorElegido) {
        this.idProfesorElegido = idProfesorElegido;
    }

    public Integer getIdUnidadElegida() {
        return idUnidadElegida;
    }

    public void setIdUnidadElegida(Integer idUnidadElegida) {
        this.idUnidadElegida = idUnidadElegida;
    }

    public String getDiaSeleccionado() {
        return diaSeleccionado;
    }

    public void setDiaSeleccionado(String diaSeleccionado) {
        this.diaSeleccionado = diaSeleccionado;
    }

    public String getTipoSeleccionado() {
        return tipoSeleccionado;
    }

    public void setTipoSeleccionado(String tipoSeleccionado) {
        this.tipoSeleccionado = tipoSeleccionado;
    }

    public Integer getIdAsignacionSelec() {return idAsignacionSelec;}

    public void setIdAsignacionSelec(Integer idAsignacionSelec) {this.idAsignacionSelec = idAsignacionSelec;}
}
