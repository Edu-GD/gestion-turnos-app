package turnopro.utils;

import turnopro.controllers.FachadaControllers;

public class IdProgresivoGenerator {
    public static Long generarSiguiente(FachadaControllers fachada){
        Long maxId = fachada.obtenerMaximoIdentificadorProgresivo();

        if (maxId == null){
            return 1L;
        }
        return maxId + 1;
    }
}
