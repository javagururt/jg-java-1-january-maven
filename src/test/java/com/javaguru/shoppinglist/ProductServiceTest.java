package com.javaguru.shoppinglist;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @Mock
    private ProductValidationService validationService;

    @InjectMocks
    private ProductService victim;

    @Test
    public void shouldCreateProductSuccessfully() {
        when(repository.save(any())).thenReturn(createProduct(10L));

        long actual = victim.saveProduct(createProduct(null));

        verify(validationService).validate(createProduct(null));

        assertEquals(10L, actual);
    }

    //shouldNotHandleExceptionFromValidationService
    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateInvalidProduct() {
        doThrow(IllegalArgumentException.class)
                .when(validationService).validate(createProduct(null));

        victim.saveProduct(createProduct(null));

        verifyZeroInteractions(repository);
    }

    @Test
    public void shouldFindById() {
        when(repository.findById(1L)).thenReturn(createProduct(1L));

        Product actual = victim.findProductById(1L);

        assertEquals(createProduct(1L), actual);
    }

    private Product createProduct(Long id) {
        Product product = new Product();
        product.setId(id);
        product.setName("TEST NAME");
        product.setPrice(BigDecimal.TEN);
        return product;
    }
}