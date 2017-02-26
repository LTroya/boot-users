package com.company.controllers;

import com.company.MedicalAppApplication;
import com.company.entities.User;
import com.company.exceptions.UserNotFoundException;
import com.company.factories.UserFactory;
import com.company.repositories.UserRepository;
import com.company.requests.NewUserForm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * @user siti2017
 * @date 14/02/17
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MedicalAppApplication.class)
@WebAppConfiguration
public class UserControllerTest {
    private final Logger LOGGER = LoggerFactory.getLogger(UserControllerTest.class);

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    private MockMvc mockMvc;
    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    private NewUserForm newUserForm;
    private List<User> userList = new ArrayList<>();

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {
        this.mappingJackson2HttpMessageConverter = Arrays.stream(converters)
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("The json message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

        this.userRepository.deleteAllInBatch();

        this.userList.add(userRepository.save(UserFactory.newUser()));
        this.userList.add(userRepository.save(UserFactory.newUser()));
        this.userList.add(userRepository.save(UserFactory.newUser()));

        this.newUserForm = UserFactory.newUserForm();
    }

    @Test
    public void getAllUsers() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.content", hasSize(3)))
                .andExpect(jsonPath("$.content[0].id", is(this.userList.get(0).getId().intValue())))
                .andExpect(jsonPath("$.content[0].name", is(this.userList.get(0).getName())))
                .andExpect(jsonPath("$.content[0].lastName", is(this.userList.get(0).getLastName())))
                .andExpect(jsonPath("$.content[0].username", is(this.userList.get(0).getUsername())))
                .andExpect(jsonPath("$.content[0].email", is(this.userList.get(0).getEmail())))
                .andExpect(jsonPath("$.content[0].phone", is(this.userList.get(0).getPhone())))
                .andExpect(jsonPath("$.content[0].sex", is(this.userList.get(0).getSex().name())))
                .andExpect(jsonPath("$.content[0].country", is(this.userList.get(0).getCountry())))
                .andExpect(jsonPath("$.content[1].id", is(this.userList.get(1).getId().intValue())))
                .andExpect(jsonPath("$.content[1].name", is(this.userList.get(1).getName())))
                .andExpect(jsonPath("$.content[1].lastName", is(this.userList.get(1).getLastName())))
                .andExpect(jsonPath("$.content[1].username", is(this.userList.get(1).getUsername())))
                .andExpect(jsonPath("$.content[1].email", is(this.userList.get(1).getEmail())))
                .andExpect(jsonPath("$.content[1].phone", is(this.userList.get(1).getPhone())))
                .andExpect(jsonPath("$.content[1].sex", is(this.userList.get(1).getSex().name())))
                .andExpect(jsonPath("$.content[1].country", is(this.userList.get(1).getCountry())))
                .andExpect(jsonPath("$.content[2].id", is(this.userList.get(2).getId().intValue())))
                .andExpect(jsonPath("$.content[2].name", is(this.userList.get(2).getName())))
                .andExpect(jsonPath("$.content[2].lastName", is(this.userList.get(2).getLastName())))
                .andExpect(jsonPath("$.content[2].username", is(this.userList.get(2).getUsername())))
                .andExpect(jsonPath("$.content[2].email", is(this.userList.get(2).getEmail())))
                .andExpect(jsonPath("$.content[2].phone", is(this.userList.get(2).getPhone())))
                .andExpect(jsonPath("$.content[2].sex", is(this.userList.get(2).getSex().name())))
                .andExpect(jsonPath("$.content[2].country", is(this.userList.get(2).getCountry())));

    }

    @Test
    public void getUserWithValidId() throws Exception {
        mockMvc.perform(get("/users/" + this.userList.get(0).getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id", is(this.userList.get(0).getId().intValue())))
                .andExpect(jsonPath("$.name", is(this.userList.get(0).getName())))
                .andExpect(jsonPath("$.lastName", is(this.userList.get(0).getLastName())))
                .andExpect(jsonPath("$.username", is(this.userList.get(0).getUsername())))
                .andExpect(jsonPath("$.email", is(this.userList.get(0).getEmail())))
                .andExpect(jsonPath("$.phone", is(this.userList.get(0).getPhone())))
                .andExpect(jsonPath("$.sex", is(this.userList.get(0).getSex().name())))
                .andExpect(jsonPath("$.country", is(this.userList.get(0).getCountry())));
    }

    @Test
    public void getUserWithInvalidId() throws Exception {
        mockMvc.perform(get("/users/1000"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.messages[*].key", hasItem("exception.users.not_found")));
    }

    @Test
    public void createUserWithValidAttributes() throws Exception {
        String userJson = objectMapper.writeValueAsString(this.newUserForm);

        mockMvc.perform(post("/users")
                .content(userJson)
                .contentType(contentType))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(notNullValue())));
    }

    @Test
    public void createUserWithInvalidAttributes() throws Exception {
        String userJson = objectMapper.writeValueAsString(new NewUserForm());

        mockMvc.perform(post("/users")
                .content(userJson)
                .contentType(contentType))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.messages[*].key", hasItem("error.name.notnull")))
                .andExpect(jsonPath("$.messages[*].key", hasItem("error.lastName.notnull")))
                .andExpect(jsonPath("$.messages[*].key", hasItem("error.email.notnull")))
                .andExpect(jsonPath("$.messages[*].key", hasItem("error.username.notnull")))
                .andExpect(jsonPath("$.messages[*].key", hasItem("error.password.notnull")))
                .andExpect(jsonPath("$.messages[*].key", hasItem("error.country.notnull")));
    }

    @Test
    public void createUserWithExistingEmail() throws Exception {
        NewUserForm newUserForm = UserFactory.newUserForm();
        newUserForm.setEmail(this.userList.get(0).getEmail());

        String userJson = objectMapper.writeValueAsString(newUserForm);

        mockMvc.perform(post("/users")
                .content(userJson)
                .contentType(contentType))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.messages[*].key", hasItem("exception.users.duplicate_email")));
    }

    @Test
    public void createUserWithExistingUsername() throws Exception {
        NewUserForm newUserForm = UserFactory.newUserForm();
        newUserForm.setUsername(this.userList.get(0).getUsername());

        String userJson = objectMapper.writeValueAsString(newUserForm);

        LOGGER.info("User to save: " + userJson);

        mockMvc.perform(post("/users")
                .content(userJson)
                .contentType(contentType))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.messages[*].key", hasItem("exception.users.duplicate_username")));
    }

    @Test
    public void updateUser() throws Exception {
        User userForm = this.userList.get(0);
        userForm.setName("Luis");
        userForm.setLastName("Troya");
        userForm.setPassword("secret");

        String userJson = objectMapper.writeValueAsString(userForm);

        mockMvc.perform(put("/users/" + userForm.getId())
                .content(userJson)
                .contentType(contentType))
                .andExpect(status().isNoContent());
    }

    @Test
    public void updateNotFoundUser() throws Exception {
        Long id = 1000L;

        User userForm = this.userList.get(0);
        userForm.setId(id);

        String userJson = objectMapper.writeValueAsString(userForm);

        mockMvc.perform(put("/users/" + id)
                .content(userJson)
                .contentType(contentType))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateUserWithNotMatchIds() throws Exception {
        Long id = 1000L;

        User userForm = this.userList.get(0);

        String userJson = objectMapper.writeValueAsString(userForm);

        mockMvc.perform(put("/users/" + id)
                .content(userJson)
                .contentType(contentType))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteUser() throws Exception {
        mockMvc.perform(delete("/users/" + this.userList.get(0).getId()))
                .andExpect(status().isNoContent());
    }

    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}