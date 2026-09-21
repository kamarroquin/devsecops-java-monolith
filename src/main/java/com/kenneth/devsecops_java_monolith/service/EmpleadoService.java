package com.kenneth.devsecops_java_monolith.service;

import com.kenneth.devsecops_java_monolith.model.Empleado;
import com.kenneth.devsecops_java_monolith.repository.EmpleadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    public List<Empleado> listar() {
        return empleadoRepository.findAll();
    }

    public Empleado guardar(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    public Empleado buscarPorId(Long id) {
        return empleadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
    }

    public void eliminar(Long id) {
        empleadoRepository.deleteById(id);
    }
}