package es.organlist.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.organlist.model.dto.ProductDTO;
import es.organlist.model.dto.UserDTO;
import es.organlist.service.UserService;
import es.organlist.service.impl.ProductServiceImpl;
import es.organlist.service.impl.UserServiceImpl;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(MockitoExtension.class)
//@WebMvcTest(controllers = OrganListController.class)
//@SpringBootTest(classes = {OrganListController.class, UserServiceImpl.class})
public class ProductControllerTests {

    private MockMvc mvc;

    @InjectMocks
    ProductController productController;

    @Mock
    ProductServiceImpl productService;

    /*@BeforeEach
    public void setup() {
        // MockMvc standalone approach
        mvc = MockMvcBuilders.standaloneSetup(productController).build();
    }
    @Test
    public void productControllerTest() throws Exception {
        // when
        MockHttpServletResponse response = mvc.perform(
                        get("/products")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }*/
    @Test
    public void organListControllerTest()  {
        List<ProductDTO> productDTOList = new ArrayList<>();
        productDTOList.add(mock(ProductDTO.class));
        when(productService.getProducts()).thenReturn(productDTOList);

        List<ProductDTO> productDTOS = productController.getAllProducts();

        verify(productService, times(1)).getProducts();
        assertThat(productDTOS).isNotNull();
        assertThat(productDTOS.size()).isNotEqualTo(0);
    }

}
