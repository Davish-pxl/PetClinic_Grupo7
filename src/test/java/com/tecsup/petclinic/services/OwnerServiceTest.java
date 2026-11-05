package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.OwnerDTO;
import com.tecsup.petclinic.exceptions.OwnerNotFoundException;
import lombok.extern.slf4j.Slf4j;
import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.mappers.OwnerMapper;
import com.tecsup.petclinic.mappers.OwnerMapperImpl;
import com.tecsup.petclinic.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
public class OwnerServiceTest {

	@Mock
	private OwnerRepository ownerRepository;

	private final OwnerMapper ownerMapper = new OwnerMapperImpl();

	private OwnerService ownerService;

	@BeforeEach
	void setUp() {
		ownerService = new OwnerServiceImpl(ownerRepository, ownerMapper);
	}

	// =====================================================
	// Integrante A — CRUD básico (rama feature/owner-crud)
	// testCreateOwner, testUpdateOwner, testDeleteOwner
	// =====================================================

	@Test
	public void testCreateOwner() {
		OwnerDTO request = OwnerDTO.builder().firstName("George").lastName("Franklin")
				.address("110 W. Liberty St.").city("Madison").telephone("6085551023").build();
		Owner saved = Owner.builder().id(1).firstName("George").lastName("Franklin")
				.address("110 W. Liberty St.").city("Madison").telephone("6085551023").build();
		when(ownerRepository.save(any(Owner.class))).thenReturn(saved);

		OwnerDTO result = ownerService.create(request);

		assertNotNull(result);
		assertEquals(1, result.getId());
		assertEquals("George", result.getFirstName());
		assertEquals("Franklin", result.getLastName());
		assertEquals("Madison", result.getCity());
		verify(ownerRepository, times(1)).save(any(Owner.class));
	}

	@Test
	public void testUpdateOwner() {
		OwnerDTO request = OwnerDTO.builder().id(1).firstName("George").lastName("Franklin")
				.address("Nueva Av. 123").city("Lima").telephone("999888777").build();
		Owner saved = Owner.builder().id(1).firstName("George").lastName("Franklin")
				.address("Nueva Av. 123").city("Lima").telephone("999888777").build();
		when(ownerRepository.save(any(Owner.class))).thenReturn(saved);

		OwnerDTO result = ownerService.update(request);

		assertEquals(1, result.getId());
		assertEquals("Nueva Av. 123", result.getAddress());
		assertEquals("Lima", result.getCity());
		assertEquals("999888777", result.getTelephone());
		verify(ownerRepository, times(1)).save(any(Owner.class));
	}

	@Test
	public void testDeleteOwner() throws OwnerNotFoundException {
		Owner existing = Owner.builder().id(1).firstName("George").lastName("Franklin")
				.address("110 W. Liberty St.").city("Madison").telephone("6085551023").build();
		when(ownerRepository.findById(1)).thenReturn(Optional.of(existing));

		ownerService.delete(1);

		verify(ownerRepository, times(1)).findById(1);
		verify(ownerRepository, times(1)).delete(existing);
	}

	// =====================================================
	// Integrante B — Búsquedas (rama feature/owner-busquedas)
	// testFindOwnerById, testFindByLastName, testFindByCity
	// =====================================================

	@Test
	public void testFindOwnerById() throws OwnerNotFoundException {
		Owner owner = Owner.builder().id(1).firstName("George").lastName("Franklin")
				.address("110 W. Liberty St.").city("Madison").telephone("6085551023").build();
		when(ownerRepository.findById(1)).thenReturn(Optional.of(owner));

		OwnerDTO result = ownerService.findById(1);

		assertEquals(1, result.getId());
		assertEquals("George", result.getFirstName());
		assertEquals("Franklin", result.getLastName());
		verify(ownerRepository, times(1)).findById(1);
	}

	@Test
	public void testFindByLastName() {
		List<Owner> owners = List.of(
				Owner.builder().id(2).firstName("Betty").lastName("Davis").city("Sun Prairie").build(),
				Owner.builder().id(4).firstName("Harold").lastName("Davis").city("Windsor").build());
		when(ownerRepository.findByLastName("Davis")).thenReturn(owners);

		List<OwnerDTO> result = ownerService.findByLastName("Davis");

		assertEquals(2, result.size());
		assertTrue(result.stream().allMatch(o -> "Davis".equals(o.getLastName())));
		verify(ownerRepository, times(1)).findByLastName("Davis");
	}

	@Test
	public void testFindByCity() {
		List<Owner> owners = List.of(
				Owner.builder().id(6).firstName("Jean").lastName("Coleman").city("Monona").build(),
				Owner.builder().id(7).firstName("Jeff").lastName("Black").city("Monona").build());
		when(ownerRepository.findByCity("Monona")).thenReturn(owners);

		List<OwnerDTO> result = ownerService.findByCity("Monona");

		assertEquals(2, result.size());
		assertTrue(result.stream().allMatch(o -> "Monona".equals(o.getCity())));
		verify(ownerRepository, times(1)).findByCity("Monona");
	}

	// =====================================================
	// Integrante C — Casos de error (rama feature/owner-errores)
	// testFindOwnerById_NotFound, testDeleteOwner_NotFound,
	// testFindByLastName_Empty
	// =====================================================

	@Test
	public void testFindOwnerById_NotFound() {
		int missingId = 999;
		when(ownerRepository.findById(missingId)).thenReturn(Optional.empty());

		assertThrows(OwnerNotFoundException.class, () -> ownerService.findById(missingId));
	}

	@Test
	public void testDeleteOwner_NotFound() {
		int missingId = 999;
		when(ownerRepository.findById(missingId)).thenReturn(Optional.empty());

		assertThrows(OwnerNotFoundException.class, () -> ownerService.delete(missingId));
		verify(ownerRepository, never()).delete(any(Owner.class));
	}

	@Test
	public void testFindByLastName_Empty() {
		String lastName = "SinCoincidencias";
		when(ownerRepository.findByLastName(lastName)).thenReturn(List.of());

		List<OwnerDTO> result = ownerService.findByLastName(lastName);

		assertTrue(result.isEmpty());
	}
}
