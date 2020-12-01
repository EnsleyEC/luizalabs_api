package br.com.luizalabsserverrest.controller;

import br.com.luizalabsserverrest.model.entity.ClientEntity;
import br.com.luizalabsserverrest.model.entity.ProductEntity;
import br.com.luizalabsserverrest.security.JwtAuthenticationEntryPoint;
import br.com.luizalabsserverrest.security.JwtTokenUtil;
import br.com.luizalabsserverrest.service.JwtUserDetailsService;
import br.com.luizalabsserverrest.service.impl.ClientServiceImpl;
import br.com.luizalabsserverrest.service.impl.ProductServiceImpl;
import br.com.luizalabsserverrest.util.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.zalando.problem.ProblemModule;
import org.zalando.problem.violations.ConstraintViolationProblemModule;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ProductController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductServiceImpl service;

    @MockBean
    private ClientServiceImpl clientService;

    @MockBean
    private JwtUserDetailsService jwtUserDetailsService;

    @MockBean
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private ObjectMapper objectMapper;

    private List<ProductEntity> list;

    @BeforeEach
    public void setup() {
        this.list = new ArrayList<>();
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(1L);
        productEntity.setBrand("brand");
        productEntity.setImage("https://image-example.com.br");
        productEntity.setPrice(1000.2);
        productEntity.setReviewScore(1000.1);
        this.list.add(productEntity);

        objectMapper.registerModule(new ProblemModule());
        objectMapper.registerModule(new ConstraintViolationProblemModule());

    }

    @Test
    void shouldFetchAllByPageWithData() throws Exception{

        // prepare
        int page = 1;
        Page<ProductEntity> productEntityPage = new PageImpl<>(list);

        // logic
        when(service.findAll(any(Pageable.class))).thenReturn(productEntityPage);

        // test/check
        this.mockMvc.perform(get(Constants.ROUTE_PRODUCT)
                .param("page", String.valueOf(page)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturn404WhenFindAllByPageWithDataResponseIsEmpty() throws Exception{

        // prepare
        int page = 1;
        Page<ProductEntity> productEntityPage = new PageImpl<>(new ArrayList<>());

        // logic
        when(service.findAll(any(Pageable.class))).thenReturn(productEntityPage);

        // test/check
        this.mockMvc.perform(get(Constants.ROUTE_PRODUCT)
                .param("page", String.valueOf(page)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturn400WhenPageIsNegative() throws Exception{

        // prepare
        int page = -1;
        Page<ProductEntity> productEntityPage = new PageImpl<>(new ArrayList<>());

        // logic
        when(service.findAll(any(Pageable.class))).thenReturn(productEntityPage);

        // test/check
        this.mockMvc.perform(get(Constants.ROUTE_PRODUCT)
                .param("page", String.valueOf(page)))
                .andExpect(status().isBadRequest());
    }


    @Test
    void shouldFetchById() throws Exception{

        // prepare
        Long productId = 1L;
        ProductEntity productEntity = list.get(0);

        // logic
        when(service.findById(productId)).thenReturn(Optional.of(productEntity));

        // test/check
        this.mockMvc.perform(get(Constants.ROUTE_PRODUCT + Constants.ROUTE_ID, productId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brand", is(productEntity.getBrand())))
                .andExpect(jsonPath("$.image", is(productEntity.getImage())))
                .andExpect(jsonPath("$.id", is(productEntity.getId().intValue())))
                .andExpect(jsonPath("$.price", is(productEntity.getPrice())))
                .andExpect(jsonPath("$.reviewScore", is(productEntity.getReviewScore())))
                .andExpect(jsonPath("$.title", is(productEntity.getTitle())));
    }

    @Test
    void shouldReturn404WhenFindProductById() throws Exception{

        // prepare
        Long productId = 1L;

        // logic
        when(service.findById(productId)).thenReturn(Optional.empty());

        // test/check
        this.mockMvc.perform(get(Constants.ROUTE_PRODUCT + Constants.ROUTE_ID, productId))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldDeleteProduct() throws Exception{

        // prepare
        Long productId = 1L;

        // logic
        when(service.existsById(productId)).thenReturn(true);
        doNothing().when(service).deleteById(productId);

        // test/check
        this.mockMvc.perform(delete(Constants.ROUTE_PRODUCT + Constants.ROUTE_ID, productId))
                .andExpect(status().isNoContent());

    }

    @Test
    void shouldReturn404WhenDeletingNonExistingProduct() throws Exception{

        // prepare
        Long productId = 1L;

        // logic
        when(service.existsById(productId)).thenReturn(false);

        // test/check
        this.mockMvc.perform(delete(Constants.ROUTE_PRODUCT + Constants.ROUTE_ID, productId))
                .andExpect(status().isNotFound());

    }

    @Test
    void shouldCreateNewProduct() throws Exception{

        // prepare
        ProductEntity productEntity = this.list.get(0);

        // logic
        when(service.save(any(ProductEntity.class))).thenReturn(productEntity);

        // test/check
        this.mockMvc.perform(post(Constants.ROUTE_PRODUCT + Constants.ROUTE_SAVE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productEntity)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brand", is(productEntity.getBrand())))
                .andExpect(jsonPath("$.image", is(productEntity.getImage())))
                .andExpect(jsonPath("$.price", is(productEntity.getPrice())))
                .andExpect(jsonPath("$.reviewScore", is(productEntity.getReviewScore())))
                .andExpect(jsonPath("$.title", is(productEntity.getTitle())));

    }

    @Test
    void shouldReturnProductsListFromClient() throws Exception{

        // prepare
        Long clientId = 1L;
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setId(1L);
        clientEntity.setEmail("teste@gmail.com");
        clientEntity.setName("EnsleyEC");

        // logic
        when(clientService.findById(clientId)).thenReturn(Optional.of(clientEntity));
        when(service.findAndFetchClientEntityListEagerly(clientId)).thenReturn(list);

        // test/check
        this.mockMvc.perform(get(Constants.ROUTE_PRODUCT + Constants.ROUTE_FIND_FAVORITE_PRODUCTS_BY_CLIENT,clientId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(list.size())));

    }

    @Test
    void shouldReturn404WhenFindingProductsListFromANonExistingClient() throws Exception{

        // prepare
        Long clientId = 1L;

        // logic
        when(clientService.findById(clientId)).thenReturn(Optional.empty());

        // test/check
        this.mockMvc.perform(get(Constants.ROUTE_PRODUCT + Constants.ROUTE_FIND_FAVORITE_PRODUCTS_BY_CLIENT,clientId))
                .andExpect(status().isNotFound());

    }


}
