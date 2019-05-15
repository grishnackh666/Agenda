
package agendas;

import java.time.LocalDate;
import java.util.ArrayList;



public class Agendas {

    
    
    public static void main(String[] args) {
        
        AgendaBasica basic1 = new AgendaBasica("enrique", "tutorias");
        basic1.añadirTurno(LocalDate.of(2018 , 12, 12), "09:30 – 10:00 y 10:30 – 11:00");
        basic1.añadirTurno(LocalDate.of(2018 , 12, 13), "10:30 – 11:00");
        
        AgendaBalanceada balance1 = new AgendaBalanceada("enrique", "revision de examen");
        balance1.añadirTurno(LocalDate.of(2018 , 12, 12), "12:00 – 12:30 y 13:30 – 14:00");
        balance1.añadirTurno(LocalDate.of(2018 , 12, 13), "11:00 – 11:30, 12:30 – 13:00 y 13:00 –13:30");
        
        ArrayList<Agenda> agendas = new ArrayList<Agenda>();
        agendas.add(basic1);
        agendas.add(balance1);
        
        
        for(Agenda to : agendas){
            System.out.println("descripcion: " + to.getDescripcion());
            System.out.println("numero de turnos: " + to.consultarTurno(LocalDate.of(2018 , 12, 13)).size());
            System.out.println("turno: "+to.reservar("juan").getFranja());
            System.out.println("turno: "+to.reservar("juan").getFranja());
            
            for(Turno tot : to.getTurnos()){
                if(to.consultarTurno(tot)){
                    System.out.println("ocupado: " + tot.getFranja());
                }
            }
            to.cancelarReserva(to.turno_usuario.get(to.turno_usuario.keySet().toArray()[0]), "juan");
        }
    }
    
}
