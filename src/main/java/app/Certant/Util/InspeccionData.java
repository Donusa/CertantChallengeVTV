package app.Certant.Util;

import app.Certant.domain.Dbidata;

public class InspeccionData {

  
    public static String dbidataApto(Dbidata db){
        if(db.getContaminacion().equals("rechazado") ||
                db.getChasis().equals("rechazado") ||
                db.getDirTrenDelantero().equals("rechazado") ||
                db.getEspejos().equals("rechazado") ||
                db.getFrenos().equals("rechazado") ||
                db.getLuces().equals("rechazado") ||
                db.getPatente().equals("rechazado") ||
                db.getVidrios().equals("rechazado") ||
                db.getSeguridadEmergencia().equals("rechazado") ||
                db.getSuspension().equals("rechazado")){
        return "rechazado";
        }
        if(db.getContaminacion().equals("condicional") ||
                db.getChasis().equals("condicional") ||
                db.getDirTrenDelantero().equals("condicional") ||
                db.getEspejos().equals("condicional") ||
                db.getFrenos().equals("condicional") ||
                db.getLuces().equals("condicional") ||
                db.getPatente().equals("condicional") ||
                db.getVidrios().equals("condicional") ||
                db.getSeguridadEmergencia().equals("condicional") ||
                db.getSuspension().equals("condicional")){
                
            return "condicional";
        }
        return "apto";
    }
    
    public static String dbidataObs(Dbidata db){
        String sb = "";
        sb+="Luces : "+db.getLuces()+"\n";
        sb+="Chasis : "+db.getChasis()+"\n";
        sb+="Contaminacion : "+db.getContaminacion()+"\n";
        sb+="Direccion y Tren delantero : "+db.getDirTrenDelantero()+"\n";
        sb+="Espejos : "+db.getEspejos()+"\n";
        sb+="Frenos : "+db.getFrenos()+"\n";
        sb+="Patente : "+db.getPatente()+"\n";
        sb+="Seguridad y Emergencia : "+db.getSeguridadEmergencia()+"\n";
        sb+="Suspension : "+db.getSuspension()+"\n";
        sb+="Vidrios : "+db.getVidrios()+"\n";
        return sb;
    }
}
