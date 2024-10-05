package pe.edu.cibertec.patitas_backend.service;

import pe.edu.cibertec.patitas_backend.dto.LoginRequestDTO;
import pe.edu.cibertec.patitas_backend.dto.LogoutRequestDTO;

import java.io.IOException;
import java.util.Date;

public interface AutenticacionService {

    String[] validarUsuario(LoginRequestDTO loginRequestDTO) throws IOException;

    Date cerrarSesionUsuario(LogoutRequestDTO logoutRequestDTO) throws IOException;

}
