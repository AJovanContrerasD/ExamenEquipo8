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

           Integer auxIdProfesor = idProfesorElegido;

           List<Asignacion> asignacionesExistentes = helper.obtenerAsignacionesProf(auxIdProfesor);
            LocalTime auxHInicio = asignacion.getHoraInicio();
            LocalTime auxHFin = asignacion.getHoraFin();

            for(Asignacion i : asignacionesExistentes){
               if(i.getDiaSemana().equals(asignacion.getDiaSemana())){
                   if(auxHInicio.isBefore(i.getHoraFin()) && auxHFin.isAfter(i.getHoraInicio()))
                   {
                       mensajeError("La hora ingresada se traslapa con otra materia registrada de " + i.getHoraInicio() + "-"+ i.getHoraFin());
                       return;
                   }
               }

           }




            Administrador administrador = loginUI.getUsuario();
            asignacion.setIdAdministrador(administrador);

            Profesor profesorElegido = listaProfesores.stream().filter(p-> p.getId().equals(idProfesorElegido))
                            .findFirst().orElse(null);
            asignacion.setIdProfesor(profesorElegido);

            UnidadAprendizaje unidadElegida = listaUnidades.stream().filter(u -> u.getId().equals(idUnidadElegida))
                            .findFirst().orElse(null);
            asignacion.setIdUnidad(unidadElegida);


    asignacion.setGrupo(asignacion.getGrupo());

    asignacion.setIdUnidad(asignacion.getIdUnidad());
    asignacion.setSemestre(asignacion.getSemestre());

    if(diaSeleccionado==null || diaSeleccionado.isEmpty()){
        mensajeError("Favor de elegir un dia de la semana");
        return;
    }

    asignacion.setDiaSemana(diaSeleccionado);
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

    public Integer getIdAsignacionSelec() {return idAsignacionSelec;}

    public void setIdAsignacionSelec(Integer idAsignacionSelec) {this.idAsignacionSelec = idAsignacionSelec;}
}

