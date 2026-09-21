package com.kenneth.devsecops_java_monolith.repository;

import com.kenneth.devsecops_java_monolith.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
}