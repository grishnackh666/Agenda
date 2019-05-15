
package agendas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public abstract class Agenda {
    protected String propietario;
    protected String descripcion;
    protected ArrayList<Turno> turnos;
    HashMap<String, Turno> turno_usuario = new HashMap<String, Turno>();

    public Agenda(String propietario, String descripcion) {
        this.propietario = propietario;
        this.descripcion = descripcion;
        this.turnos = new ArrayList<Turno>();
    }
    
    
    public boolean añadirTurno(LocalDate fecha, String franja){
        Turno turno = new Turno(fecha, franja);
        boolean sw = false;
        for(Turno to : this.turnos){
            if(to.equals(turno)){
                sw = true;
            }
        }
        if(!sw){
            this.turnos.add(turno);
            return true;
        }else{
            return false;
        }
        
    }
    
    
    public void ajustarDias(int numero){
        for(Turno turno : this.turnos){
            if(numero > 0){
                turno.setFecha(turno.getFecha().minusDays(numero));
                
            }else{
                turno.setFecha(turno.getFecha().plusDays(numero));
                
            }
        }
    }
    
    
    public ArrayList<Turno> consultarTurno(LocalDate fecha){
        ArrayList<Turno> returnTurno = new ArrayList<Turno>();
        for(Turno to : this.turnos){
            if(to.getFecha().isEqual(fecha)){
                returnTurno.add(to);
            }
        }
        return returnTurno;
    }
    
    
  
    public boolean reservar(String usuario, Turno turno){
        boolean retorno = false;
        for(Turno to: this.turnos){
            if(to.equals(turno)){
                if(!this.consultarTurno(turno)){
                    turno_usuario.put(usuario, turno);
                    retorno = true;
                }
            }
        }
        return retorno;
    }
    
    
    
    protected abstract Turno reservar(String usuario);
    
    
    public String consultarUsuario(Turno turno){
        String usuario = null;
        for(Map.Entry<String,Turno> to : turno_usuario.entrySet()){
            if(turno.equals(to.getValue())){
                usuario = to.getKey();
            }
        }
        return usuario;
    }
    
    
    
    public boolean consultarTurno(Turno turno){
        boolean retorno = false;
        for(Map.Entry<String,Turno> to : turno_usuario.entrySet()){
            if(to.getValue().equals(turno)){
                retorno = true;
            }
        }
        return retorno;
    }
    
    
    
    public boolean cancelarReserva(Turno turno, String usuario){
        String eliminado = null;
        for(Map.Entry<String, Turno> to : turno_usuario.entrySet()){
            System.out.println(to.getKey()+" = "+usuario);
            System.out.println(to.getValue().getFranja()+" = "+turno.getFranja());
            if(Objects.equals(to.getKey(), usuario) && to.getValue().equals(turno)){
                eliminado = to.getKey();
            }
        }
        if(eliminado != null){
            turno_usuario.remove(eliminado);
            System.out.println("cancelado");
            return true;
        }else{
            return false;
        }
    }

    public String getPropietario() {
        return propietario;
    }
    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public ArrayList<Turno> getTurnos() {
        return turnos;
    }
    public void setTurnos(ArrayList<Turno> turnos) {
        this.turnos = turnos;
    }
    
    
}
