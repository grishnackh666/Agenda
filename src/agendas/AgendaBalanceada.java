
package agendas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class AgendaBalanceada extends Agenda {
    
    private HashMap<LocalDate, Integer> balance = new HashMap<LocalDate, Integer>();

    public AgendaBalanceada(String propietario, String descripcion) {
        super(propietario, descripcion);
    }

    @Override
    public boolean añadirTurno(LocalDate fecha, String franja) {
        int contador = 0;
        if(super.añadirTurno(fecha, franja)){
            if(balance.size() > 0){
                for(Map.Entry<LocalDate, Integer> to : balance.entrySet()){
                    if(to.getKey().isEqual(fecha)){
                        balance.replace(to.getKey(), to.getValue()+1);
                        contador++;
                    }
                }
                if(contador == 0){
                    balance.put(fecha, 1);
                }
            }else{
                balance.put(fecha, 1);
            }
        }
        return true;
    }
    
    
    
    
    public LocalDate consultarDia(){
        LocalDate retorno_fecha = null;
        Map.Entry<LocalDate, Integer> diaMayor = null;
        for(Map.Entry<LocalDate, Integer> to : balance.entrySet()){
            if(diaMayor == null){
                diaMayor = to;
            }else{
                if(diaMayor.getValue() <= to.getValue()){
                    diaMayor = to;
                }
            }
        }
        return diaMayor.getKey();
    }
    
    @Override
    protected Turno reservar(String usuario){
        Turno returnTurno = null;
        LocalDate fecha_mayor = this.consultarDia();
        ArrayList<Turno> turnos_dia = this.consultarTurno(fecha_mayor);
        for(Turno turnos : turnos_dia){
            if(!this.consultarTurno(turnos)){
                turno_usuario.put("usuario", turnos);
                returnTurno = turnos;
                for(Map.Entry<LocalDate, Integer> to : balance.entrySet()){
                    if(to.getKey().isEqual(turnos.getFecha())){
                        balance.replace(to.getKey(), to.getValue()-1);
                    }
                }
                break;
            }
        }
        return returnTurno;
    }
    
       
}
