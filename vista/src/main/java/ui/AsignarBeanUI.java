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
    private Asignacion asignacion;
    private Integer idProfesorElegido, idUnidadElegida;



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
            asignacion = new Asignacion();
        }catch (Throwable ex) {
            System.err.println("ERROR EN HIBERNATE: " + ex);
            ex.printStackTrace();
        }
    }


    public List<Profesor> getListaProfesores (){
        return listaProfesores;
    }

    public List<UnidadAprendizaje> getListaUnidades (){
        return listaUnidades;
    }

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

            if(asignacion.getHorainicio() == null ||asignacion.getHorafin() == null ){
                mensajeError("Ingrese hora de salida y hora fin");
                return;
            }
           if( asignacion.getHorafin().isBefore(asignacion.getHorainicio()) || asignacion.getHorainicio().isAfter(asignacion.getHorafin() )){
               mensajeError("La hora de clase no es valida");
               return;
           }

           Integer auxIdProfesor = idProfesorElegido;

           List<Asignacion> asignacionesExistentes = helper.obtenerAsignacionesProf(auxIdProfesor);
            LocalTime auxHInicio = asignacion.getHorainicio();
            LocalTime auxHFin = asignacion.getHorafin();

            for(Asignacion i : asignacionesExistentes){
               if(i.getDiasemana().equalsIgnoreCase(asignacion.getDiasemana())){
                   if(auxHInicio.isBefore(i.getHorafin()) && auxHFin.isAfter(i.getHorainicio()))
                   {
                       mensajeError("La hora ingresada se traslapa con otra materia registrada de " + i.getHorainicio() + "-"+ i.getHorafin());
                       return;
                   }
               }

           }


            Administrador administrador = loginUI.getUsuario();
            asignacion.setIdadministrador(administrador);

            Profesor profesorElegido = listaProfesores.stream().filter(p-> p.getId().equals(idProfesorElegido))
                            .findFirst().orElse(null);
            asignacion.setIdprofesor(profesorElegido);

            UnidadAprendizaje unidadElegida = listaUnidades.stream().filter(u -> u.getId().equals(idUnidadElegida))
                            .findFirst().orElse(null);
            asignacion.setIdunidad(unidadElegida);


    asignacion.setGrupo(asignacion.getGrupo());

    asignacion.setIdunidad(asignacion.getIdunidad());
    asignacion.setSemestre(asignacion.getSemestre());

    if(diaSeleccionado==null || diaSeleccionado.isEmpty()){
        mensajeError("Favor de elegir un dia de la semana");
        return;
    }

    asignacion.setDiasemana(diaSeleccionado);
    asignacion.setHorainicio(asignacion.getHorainicio());
    asignacion.setHorafin(asignacion.getHorafin());
    helper.registrarAsignacion(asignacion);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Asignacion Registrada", "La asignacion fue registrada correctamente!"));
        } catch (Exception e){
            e.printStackTrace();
            mensajeError("No se pudo registrar la Asignatura");
        }
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

}

