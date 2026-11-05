package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.OwnerDTO;
import com.tecsup.petclinic.exceptions.OwnerNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class OwnerServiceTest {

	@Autowired
	private OwnerService ownerService;

	// =====================================================
	// Integrante A — CRUD básico (rama feature/owner-crud)
	// testCreateOwner, testUpdateOwner, testDeleteOwner
	// =====================================================

	// =====================================================
	// Integrante B — Búsquedas (rama feature/owner-busquedas)
	// testFindOwnerById, testFindByLastName, testFindByCity
	// =====================================================

	// =====================================================
	// Integrante C — Casos de error (rama feature/owner-errores)
	// testFindOwnerById_NotFound, testDeleteOwner_NotFound,
	// testFindByLastName_Empty
	// =====================================================
}
