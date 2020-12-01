package br.com.luizalabsserverrest.controller;

import br.com.luizalabsserverrest.controller.request.AccountRequest;
import br.com.luizalabsserverrest.security.JwtAuthenticationEntryPoint;
import br.com.luizalabsserverrest.security.JwtTokenUtil;
import br.com.luizalabsserverrest.service.JwtUserDetailsService;
import br.com.luizalabsserverrest.util.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;
import org.zalando.problem.ProblemModule;
import org.zalando.problem.violations.ConstraintViolationProblemModule;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtUserDetailsService jwtUserDetailsService;

    @MockBean
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    @MockBean
    private AuthenticationManager authenticationManager;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {

        objectMapper.registerModule(new ProblemModule());
        objectMapper.registerModule(new ConstraintViolationProblemModule());

    }

    @Test
    void shouldSignIn()
    {

        // prepare
        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setPassword("teste");
        accountRequest.setUsername("teste");
        String token = "Bearer Jfdljfjdsqpiwekljdapfoidsajflkajdklfoiqujlq";


        // logic
        when(jwtTokenUtil.generateToken(null)).thenReturn(token);

        // test/check
        try {
            this.mockMvc.perform(post(Constants.ROUTE_SIGN_IN)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(accountRequest)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.token", is(token)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void shouldNotSignIn()
    {

        // prepare
        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setPassword("teste");
        accountRequest.setUsername("teste");
        String token = "Bearer Jfdljfjdsqpiwekljdapfoidsajflkajdklfoiqujlq";


        // logic
        when(authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(accountRequest.getUsername()
                        ,accountRequest.getPassword()))).thenThrow(BadCredentialsException.class);

        // test/check
        try {
            this.mockMvc.perform(post(Constants.ROUTE_SIGN_IN)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(accountRequest)))
                    .andExpect(status().isBadRequest());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
