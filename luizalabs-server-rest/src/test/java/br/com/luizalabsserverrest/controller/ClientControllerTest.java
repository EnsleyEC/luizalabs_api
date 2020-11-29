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
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.zalando.problem.ProblemModule;
import org.zalando.problem.violations.ConstraintViolationProblemModule;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ClientController.class)
@ActiveProfiles("test")
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
        given(service.findAll()).willReturn(list);

        this.mockMvc.perform(get(Constants.ROUTE_CLIENT))
                .andExpect(status().isOk());
    }

    @Test
    void shouldCreateAddFavoriteProducts() throws Exception{

        FavoriteProductsRequest fpRequest = new FavoriteProductsRequest();
        Set<Long> ids = new HashSet<>();
        ids.add(1L);
        ids.add(2L);
        fpRequest.setClientId(1L);
        fpRequest.setFavoriteProductsIds(ids);

        this.mockMvc.perform(post(Constants.ROUTE_CLIENT + Constants.ROUTE_ADD_FAVORITE_PRODUCTS_BY_CLIENT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(fpRequest)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldCreateNewClient() throws Exception{

        given(service.save(any(ClientEntity.class))).willAnswer((invocation) -> invocation.getArgument(0));

        this.mockMvc.perform(post(Constants.ROUTE_CLIENT + Constants.ROUTE_SAVE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(list.get(0))))
                .andExpect(status().isOk());
    }

    @Test
    void shouldFetchById() throws Exception{

        Long userId = 1L;
        ClientEntity clientEntity = list.get(0);

        given(service.findById(1L)).willReturn(Optional.of(clientEntity));

        this.mockMvc.perform(get(Constants.ROUTE_CLIENT + Constants.ROUTE_ID, userId))
                .andExpect(status().isOk())
                ;
    }

}
