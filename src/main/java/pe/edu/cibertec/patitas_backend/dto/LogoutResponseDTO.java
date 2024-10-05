package pe.edu.cibertec.patitas_backend.dto;

import java.util.Date;

public record LogoutResponseDTO(Boolean resultado, Date fecha, String mensajeError) {
}
