package pe.edu.cibertec.patitas_backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.patitas_backend.dto.LoginRequestDTO;
import pe.edu.cibertec.patitas_backend.service.AutenticacionService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class AutenticacionServiceImpl implements AutenticacionService {

    @Autowired
    ResourceLoader resourceLoader;

    @Override
    public String[] validarUsuario(LoginRequestDTO loginRequestDTO) throws IOException {

        String[] datosUsuario = null;
        Resource resource = resourceLoader.getResource("classpath:usuarios.txt");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {

            String linea;
            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(";");
                if (loginRequestDTO.tipoDocumento().equals(datos[0]) &&
                        loginRequestDTO.numeroDocumento().equals(datos[1]) &&
                        loginRequestDTO.password().equals(datos[2])) {

                    datosUsuario = new String[2];
                    datosUsuario[0] = datos[3]; // Recuperar nombre
                    datosUsuario[1] = datos[4]; // Recuperar correo

                }

            }

        } catch (IOException e) {

            datosUsuario = null;
            throw new IOException(e);

        }

        return datosUsuario;

    }

}
