package br.com.luizalabsserverrest.controller;

import br.com.luizalabsserverrest.controller.request.FavoriteProductsRequest;
import br.com.luizalabsserverrest.model.entity.ClientEntity;
import br.com.luizalabsserverrest.model.entity.ProductEntity;
import br.com.luizalabsserverrest.security.JwtAuthenticationEntryPoint;
import br.com.luizalabsserverrest.security.JwtTokenUtil;
import br.com.luizalabsserverrest.service.GenericService;
import br.com.luizalabsserverrest.service.JwtUserDetailsService;
import br.com.luizalabsserverrest.service.impl.ClientServiceImpl;
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

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;

@WebMvcTest(controllers = ClientController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientServiceImpl service;

    @MockBean
    private GenericService<ProductEntity> productEntityGenericService;

    @MockBean
    private JwtUserDetailsService jwtUserDetailsService;

    @MockBean
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private ObjectMapper objectMapper;

    private List<ClientEntity> list;

    @BeforeEach
    public void setup() {
        this.list = new ArrayList<>();
        ClientEntity entitySave = new ClientEntity();
        entitySave.setId(1L);
        entitySave.setEmail("teste@teste.com");
        entitySave.setName("teste2");
        this.list.add(entitySave);

        objectMapper.registerModule(new ProblemModule());
        objectMapper.registerModule(new ConstraintViolationProblemModule());

    }

    @Test
    void shouldFetchAll() throws Exception{

        // logic
        when(service.findAll()).thenReturn(list);

        // test/check
        this.mockMvc.perform(get(Constants.ROUTE_CLIENT))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(list.size())));
    }

    @Test
    void shouldReturn404WhenFindAllResponseIsEmpty() throws Exception{

        // logic
        when(service.findAll()).thenReturn(new ArrayList<>());

        // test/check
        this.mockMvc.perform(get(Constants.ROUTE_CLIENT))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldAddFavoriteProducts() throws Exception{

        // prepare
        FavoriteProductsRequest fpRequest = new FavoriteProductsRequest();
        Set<Long> ids = new HashSet<>();
        ids.add(1L);
        ids.add(2L);
        fpRequest.setClientId(1L);
        fpRequest.setFavoriteProductsIds(ids);

        // logic
        when(service.findById(fpRequest.getClientId())).thenReturn(Optional.of(this.list.get(0)));

        // test/check
        this.mockMvc.perform(post(Constants.ROUTE_CLIENT + Constants.ROUTE_ADD_FAVORITE_PRODUCTS_BY_CLIENT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(fpRequest)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldCreateNewClient() throws Exception{

        // prepare
        ClientEntity clientEntity = this.list.get(0);

        // logic
        when(service.save(any(ClientEntity.class))).thenReturn(clientEntity);

        // test/check
        this.mockMvc.perform(post(Constants.ROUTE_CLIENT + Constants.ROUTE_SAVE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(list.get(0))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is(clientEntity.getEmail())))
                .andExpect(jsonPath("$.name", is(clientEntity.getName())))
                .andExpect(jsonPath("$.id", is(clientEntity.getId().intValue())));

    }

    @Test
    void shouldReturn400WhenCreatingAClientWithAnExistingEmail() throws Exception{

        // prepare
        ClientEntity clientEntity = this.list.get(0);

        // logic
        when(service.existsByEmail(clientEntity.getEmail())).thenReturn(true);

        // test/check
        this.mockMvc.perform(post(Constants.ROUTE_CLIENT + Constants.ROUTE_SAVE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(list.get(0))))
                .andExpect(status().isBadRequest());

    }

    @Test
    void shouldFetchById() throws Exception{

        // prepare
        Long clientId = 1L;
        ClientEntity clientEntity = list.get(0);

        // logic
        when(service.findById(clientId)).thenReturn(Optional.of(clientEntity));

        // test/check
        this.mockMvc.perform(get(Constants.ROUTE_CLIENT + Constants.ROUTE_ID, clientId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is(clientEntity.getEmail())))
                .andExpect(jsonPath("$.name", is(clientEntity.getName())))
                .andExpect(jsonPath("$.id", is(clientEntity.getId().intValue())));
    }

    @Test
    void shouldReturn404WhenFindClientById() throws Exception{

        // prepare
        Long clientId = 1L;

        // logic
        when(service.findById(clientId)).thenReturn(Optional.empty());

        // test/check
        this.mockMvc.perform(get(Constants.ROUTE_CLIENT + Constants.ROUTE_ID, clientId))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldDeleteClient() throws Exception{

        // prepare
        Long clientId = 1L;

        // logic
        when(service.existsById(clientId)).thenReturn(true);
        doNothing().when(service).deleteById(clientId);

        // test/check
        this.mockMvc.perform(delete(Constants.ROUTE_CLIENT + Constants.ROUTE_ID, clientId))
                .andExpect(status().isNoContent());

    }

    @Test
    void shouldReturn404WhenDeletingNonExistingClient() throws Exception{

        // prepare
        Long clientId = 1L;

        // logic
        when(service.existsById(clientId)).thenReturn(false);

        // test/check
        this.mockMvc.perform(delete(Constants.ROUTE_CLIENT + Constants.ROUTE_ID, clientId))
                .andExpect(status().isNotFound());

    }

    @Test
    void shouldUpdateClient() throws Exception {

        // prepare
        Long clientId = 1L;
        ClientEntity clientEntity = list.get(0);
        clientEntity.setId(clientId);

        // logic
        when(service.findById(clientId)).thenReturn(Optional.of(clientEntity));
        when(service.findByEmail(clientEntity.getEmail())).thenReturn(Optional.of(clientEntity));
        when(service.save(any(ClientEntity.class))).thenReturn(clientEntity);

        // test/check
        this.mockMvc.perform(put(Constants.ROUTE_CLIENT + Constants.ROUTE_ID, clientId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clientEntity)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is(clientEntity.getEmail())))
                .andExpect(jsonPath("$.id", is(clientEntity.getId().intValue())))
                .andExpect(jsonPath("$.name", is(clientEntity.getName())));

    }

    @Test
    void shouldReturn404WhenUpdatingNonExistingClient() throws Exception {

        // prepare
        Long clientId = 1L;
        ClientEntity clientEntity = list.get(0);
        clientEntity.setId(clientId);

        // logic
        when(service.findById(clientId)).thenReturn(Optional.empty());

        // test/check
        this.mockMvc.perform(put(Constants.ROUTE_CLIENT + Constants.ROUTE_ID, clientId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clientEntity)))
                .andExpect(status().isNotFound());

    }

}
