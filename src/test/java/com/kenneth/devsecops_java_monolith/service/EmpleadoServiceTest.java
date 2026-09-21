package com.kenneth.devsecops_java_monolith.service;

import com.kenneth.devsecops_java_monolith.model.Empleado;
import com.kenneth.devsecops_java_monolith.repository.EmpleadoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmpleadoServiceTest {

    @Mock
    private EmpleadoRepository empleadoRepository;

    @InjectMocks
    private EmpleadoService empleadoService;

    private Empleado empleado;

    @BeforeEach
    void setUp() {
        empleado = new Empleado(
                "Kenneth",
                "Marroquin",
                "kenneth@test.com",
                "Jefe",
                28000.00
        );

        empleado.setId(1L);
    }

    @Test
    void debeListarEmpleados() {
        when(empleadoRepository.findAll())
                .thenReturn(List.of(empleado));

        List<Empleado> resultado = empleadoService.listar();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Kenneth", resultado.get(0).getNombre());

        verify(empleadoRepository, times(1)).findAll();
    }

    @Test
    void debeGuardarEmpleado() {
        when(empleadoRepository.save(empleado))
                .thenReturn(empleado);

        Empleado resultado = empleadoService.guardar(empleado);

        assertNotNull(resultado);
        assertEquals("Kenneth", resultado.getNombre());
        assertEquals("Marroquin", resultado.getApellido());

        verify(empleadoRepository, times(1)).save(empleado);
    }

    @Test
    void debeBuscarEmpleadoPorId() {
        when(empleadoRepository.findById(1L))
                .thenReturn(Optional.of(empleado));

        Empleado resultado = empleadoService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("kenneth@test.com", resultado.getEmail());

        verify(empleadoRepository, times(1)).findById(1L);
    }

    @Test
    void debeLanzarErrorSiEmpleadoNoExiste() {
        when(empleadoRepository.findById(99L))
                .thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> empleadoService.buscarPorId(99L)
        );

        assertEquals("Empleado no encontrado", exception.getMessage());
    }

    @Test
    void debeEliminarEmpleado() {
        empleadoService.eliminar(1L);

        verify(empleadoRepository, times(1)).deleteById(1L);
    }
}