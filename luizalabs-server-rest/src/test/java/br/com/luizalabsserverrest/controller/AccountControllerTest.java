package br.com.luizalabsserverrest.controller;

import br.com.luizalabsserverrest.model.entity.AccountEntity;
import br.com.luizalabsserverrest.security.JwtAuthenticationEntryPoint;
import br.com.luizalabsserverrest.security.JwtTokenUtil;
import br.com.luizalabsserverrest.service.JwtUserDetailsService;
import br.com.luizalabsserverrest.service.impl.AccountServiceImpl;
import br.com.luizalabsserverrest.util.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.zalando.problem.ProblemModule;
import org.zalando.problem.violations.ConstraintViolationProblemModule;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;


@WebMvcTest(controllers = AccountController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountServiceImpl accountService;

    @MockBean
    private JwtUserDetailsService jwtUserDetailsService;

    @MockBean
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {

        objectMapper.registerModule(new ProblemModule());
        objectMapper.registerModule(new ConstraintViolationProblemModule());

    }

    @Test
    void shouldCreateNewAccount() throws Exception{

        // prepare
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(1L);
        accountEntity.setUsername("teste");
        accountEntity.setPassword("teste");

        // logic
        when(accountService.save(any(AccountEntity.class))).thenReturn(accountEntity);

        // test/check
        this.mockMvc.perform(post(Constants.ROUTE_ACCOUNT + Constants.ROUTE_SAVE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(accountEntity)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is(accountEntity.getUsername())))
                .andExpect(jsonPath("$.id", is(accountEntity.getId().intValue())));

    }

    @Test
    void shouldReturn400WhenCreatingANewAccountWithAnExistingUsername() throws Exception{

        // prepare
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setUsername("teste");
        accountEntity.setPassword("teste");

        // logic
        when(accountService.existsByUsername(accountEntity.getUsername())).thenReturn(true);

        // test/check
        this.mockMvc.perform(post(Constants.ROUTE_ACCOUNT + Constants.ROUTE_SAVE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(accountEntity)))
                .andExpect(status().isBadRequest());

    }

    @Test
    void shouldDeleteAccount() throws Exception{

        // prepare
        Long accountId = 1L;

        // logic
        when(accountService.existsById(accountId)).thenReturn(true);
        doNothing().when(accountService).deleteById(accountId);

        // test/check
        this.mockMvc.perform(delete(Constants.ROUTE_ACCOUNT + Constants.ROUTE_ID, accountId))
                .andExpect(status().isNoContent());

    }

    @Test
    void shouldReturn404WhenDeletingNonExistingAccount() throws Exception{

        // prepare
        Long accountId = 1L;

        // logic
        when(accountService.existsById(accountId)).thenReturn(false);

        // test/check
        this.mockMvc.perform(delete(Constants.ROUTE_ACCOUNT + Constants.ROUTE_ID, accountId))
                .andExpect(status().isNotFound());

    }

}
