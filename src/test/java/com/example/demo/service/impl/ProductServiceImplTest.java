package com.example.demo.service.impl;

import com.example.demo.dao.ProductRepository;
import com.example.demo.entity.Product;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    @Mock
    ProductRepository productRepository;
    @InjectMocks
    ProductServiceImpl productServiceImpl;

    @Test
    void saveProduct() {
        //given
        Product product =new Product(
                1,
                "savon",
                12.5,
                "testt"
        );
        //when
        productServiceImpl.saveProduct(product);
        ArgumentCaptor<Product> productArgumentCaptor= ArgumentCaptor.forClass(Product.class);
        //then
        verify(productRepository).save(productArgumentCaptor.capture());
        Product value = productArgumentCaptor.getValue();
        assertThat(value).isEqualTo(product);
    }

    @Test
    void canGetProduct() {
        Product product =new Product(
                1,
                "savon",
                12.5,
                "testt"
        );
        when(productRepository.findById(1)).thenReturn(java.util.Optional.of(product));
        Product product1 = productServiceImpl.getProduct(1);
        assertEquals(product.getName(),product1.getName());
    }
    @Test
    void willThrowWhenUserNotFoundException() {
        Product product =new Product(
                1,
                "savon",
                12.5,
                "testt"
        );
        given(productRepository.findById(anyInt())).willReturn(Optional.empty());
        assertThatThrownBy(() -> productServiceImpl.getProduct(product.getId()))
                .isInstanceOf(ProductNotFoundException.class)
                .hasMessageContaining(String.format("product with id : %d not found",product.getId()));
    }


    @Test
    void getAllProduct() {
        productServiceImpl.getAllProduct();
        verify(productRepository).findAll();
    }

    @Test
    void canUpdateProduct() {
        Product product =new Product();
        product.setId(1);
        product.setName("savon");
        Product productUpdate =new Product(
                1,
                "test",
                10.5,
                "testt"
        );
        when(productRepository.findById(1)).thenReturn(java.util.Optional.of(product));
        Product product1 = productServiceImpl.getProduct(1);
        product1.setName(productUpdate.getName());
        product1.setPrice(productUpdate.getPrice());
        product1.setDescription(productUpdate.getDescription());
        productServiceImpl.updateProduct(product1);
        verify(productRepository).save(product1);
    }

    @Test
    void canDeleteProduct() {
        Product product=new Product();
        product.setId(1);
        product.setName("savon");
        when(productRepository.findById(1)).thenReturn(java.util.Optional.of(product));
        Boolean aBoolean = productServiceImpl.deleteProduct(product.getId());
        verify(productRepository).delete(product);
        assertTrue(aBoolean);
    }
    @Test
    void willThrowWhenProductCanNotDelete() {
        Product product=new Product();
        product.setId(1);
        product.setName("savon");
       given(productRepository.findById(anyInt())).willReturn(Optional.empty());
       assertThatThrownBy(() -> productServiceImpl.deleteProduct(product.getId()))
               .isInstanceOf(ProductNotFoundException.class)
               .hasMessageContaining(String.format("product with id : %d not found",product.getId()));
    }
}