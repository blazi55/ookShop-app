package com.ookshop.application.companyrole;

import com.ookshop.application.tables.CompanyRole;
import com.ookshop.application.tables.User;
import com.ookshop.application.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class CompanyRoleServiceTest {

	@Mock
	private CompanyRoleRepository companyRoleRepository;

	@Mock
	private UserRepository userRepository;

	@Mock
	private CompanyRoleMapper companyRoleMapper;

	@InjectMocks
	private CompanyRoleService companyRoleService;

	// =========================
	// ADMIN ROLE
	// =========================

	@Test
	void shouldRegisterAdminRole() {
		CreateCompanyRole dto = new CreateCompanyRole();
		dto.setEmail("test@mail.com");

		User user = new User();
		CompanyRole role = CompanyRole.builder()
				.role("ADMIN")
				.user(user)
				.build();

		CompanyRoleDto mappedDto = new CompanyRoleDto();

		when(userRepository.findUserByEmail("test@mail.com"))
				.thenReturn(Optional.of(user));

		when(companyRoleRepository.save(any(CompanyRole.class)))
				.thenReturn(role);

		when(companyRoleMapper.toDto(any(CompanyRole.class)))
				.thenReturn(mappedDto);

		CompanyRoleDto result = companyRoleService.registerAdminRole(dto);

		assertNotNull(result);

		ArgumentCaptor<CompanyRole> captor = ArgumentCaptor.forClass(CompanyRole.class);
		verify(companyRoleRepository).save(captor.capture());

		assertEquals("ADMIN", captor.getValue().getRole());
		assertEquals(user, captor.getValue().getUser());
	}

	// =========================
	// USER ROLE
	// =========================

	@Test
	void shouldRegisterUserRole() {
		CreateCompanyRole dto = new CreateCompanyRole();
		dto.setEmail("test@mail.com");

		User user = new User();
		CompanyRoleDto mappedDto = new CompanyRoleDto();

		when(userRepository.findUserByEmail("test@mail.com"))
				.thenReturn(Optional.of(user));

		when(companyRoleMapper.toDto(any(CompanyRole.class)))
				.thenReturn(mappedDto);

		CompanyRoleDto result = companyRoleService.registerUserRole(dto);

		assertNotNull(result);

		ArgumentCaptor<CompanyRole> captor = ArgumentCaptor.forClass(CompanyRole.class);
		verify(companyRoleRepository).save(captor.capture());

		assertEquals("USER", captor.getValue().getRole());
	}

	// =========================
	// ERROR CASE
	// =========================

	@Test
	void shouldThrowWhenUserNotFound() {
		CreateCompanyRole dto = new CreateCompanyRole();
		dto.setEmail("notfound@mail.com");

		when(userRepository.findUserByEmail("notfound@mail.com"))
				.thenReturn(Optional.empty());

		assertThrows(IllegalArgumentException.class,
				() -> companyRoleService.registerAdminRole(dto));
	}
}