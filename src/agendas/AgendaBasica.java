
package agendas;

import java.util.ArrayList;


public class AgendaBasica extends Agenda {
    ArrayList<String> usuarios_reserva;

    public AgendaBasica(String propietario, String descripcion) {
        super(propietario, descripcion);
        this.usuarios_reserva = new ArrayList<String>();
    }
    
    protected Turno reservar(String usuario){
        Turno turnoAntiguo = null;
        for(Turno turno : this.turnos){
            if(turnoAntiguo == null){
                if(!this.consultarTurno(turno)){
                    turnoAntiguo = turno;
                }
            }else 
                if(turnoAntiguo.getFecha().isAfter(turno.getFecha())){
                if(this.consultarTurno(turno)){
                    turnoAntiguo = turno;
                }
            }
        }
        
        if(turnoAntiguo != null){
            turno_usuario.put(usuario, turnoAntiguo);
        }
        return turnoAntiguo;
    }
    
    
}
