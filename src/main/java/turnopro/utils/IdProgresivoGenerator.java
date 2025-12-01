package turnopro.utils;

import turnopro.controllers.FachadaControllers;

public class IdProgresivoGenerator {
    public static Integer generarSiguiente(FachadaControllers fachada){
        Integer maxId = fachada.obtenerMaximoIdentificadorProgresivo();

        if (maxId == null){
            return 1;
        }
        return maxId + 1;
    }
}
