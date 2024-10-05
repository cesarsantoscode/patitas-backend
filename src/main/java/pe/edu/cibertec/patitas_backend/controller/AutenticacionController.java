package pe.edu.cibertec.patitas_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.patitas_backend.dto.LoginRequestDTO;
import pe.edu.cibertec.patitas_backend.dto.LoginResponseDTO;
import pe.edu.cibertec.patitas_backend.dto.LogoutRequestDTO;
import pe.edu.cibertec.patitas_backend.dto.LogoutResponseDTO;
import pe.edu.cibertec.patitas_backend.service.AutenticacionService;

import java.time.Duration;
import java.util.Arrays;
import java.util.Date;

@RestController
@RequestMapping("/autenticacion")
public class AutenticacionController {

    @Autowired
    AutenticacionService autenticacionService;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO) {

        try {

            Thread.sleep(Duration.ofSeconds(10));
            String[] datosUsuario = autenticacionService.validarUsuario(loginRequestDTO);
            System.out.println("Resultado: " + Arrays.toString(datosUsuario));
            if (datosUsuario == null) {
                return new LoginResponseDTO("01", "Error: Usuario no encontrado", "", "");
            }
            return new LoginResponseDTO("00", "", datosUsuario[0], datosUsuario[1]);

        } catch (Exception e) {
            return new LoginResponseDTO("99", "Error: Ocurrió un problema", "", "");
        }

    }

    @PostMapping("/logout")
    public LogoutResponseDTO login(@RequestBody LogoutRequestDTO logoutRequestDTO) {

        try {

            Thread.sleep(Duration.ofSeconds(5));
            Date fechaLogout = autenticacionService.cerrarSesionUsuario(logoutRequestDTO);
            System.out.println("Resultado: " + fechaLogout);
            if (fechaLogout == null) {
                return new LogoutResponseDTO(false, null, "Error: No se pudo registrar auditoría");
            }
            return new LogoutResponseDTO(true, fechaLogout, "");

        } catch (Exception e) {
            return new LogoutResponseDTO(false, null, "Error: Ocurrió un problema");
        }

    }

}
