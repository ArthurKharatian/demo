package com.example.demo.controllers;


import com.example.demo.model.entity.House;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.ConfigurableMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
public class HouseControllerTest {

    House house = new House();

    {
        house.setId(2L);
        house.setBuiltDate("1999");
        house.setName("House2");
    }


    MockMvc mockMvc;
    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    ObjectMapper objectMapper;

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");


    @Before
    public void setUp() {
        ConfigurableMockMvcBuilder builder =
                MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
                        .alwaysDo(document(" {method-name}/{step}/"))
                        .apply(documentationConfiguration(this.restDocumentation));
        this.mockMvc = builder.build();
    }


    @Test
    @SneakyThrows
    public void createHouse() {
        String content = objectMapper.writeValueAsString(house);
        System.out.println(content);
        String uri = "/houses";
        mockMvc.perform(post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.builtDate").value("1999"))
                .andExpect(jsonPath("$.name").value("House2"))
                .andDo(document(uri.replace("/", "\\")));

    }

    @Test
    public void updateHouse() {
    }

    @Test
    public void getHouse() {
    }

    @Test
    public void deleteHouse() {
    }

    @Test
    public void addToUser() {
    }

    @Test
    public void getAllHouses() {
    }
}