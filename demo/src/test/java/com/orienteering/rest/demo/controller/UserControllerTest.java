package com.orienteering.rest.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orienteering.rest.demo.dto.PhotographDTO;
import com.orienteering.rest.demo.dto.UserDTO;
import com.orienteering.rest.demo.model.StatusResponseEntity;
import com.orienteering.rest.demo.security.models.PasswordUpdateRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * Test for UserController class
 */
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    /**
     * Test output serialization of retrieve user
     * @throws Exception
     */
    @Test
    @DirtiesContext
    void retrieveUserInput() throws Exception{
        this.mockMvc.perform(get("/users/1")).andExpect(status().isOk()).andDo(print());
    }

    @Test
    @DirtiesContext
    void retrieveUserOutput() throws Exception{
        Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX").parse("2020-06-19T14:27:28.054+00:00");
        UserDTO user = new UserDTO();
        user.setUserId(1L);
        user.setUserFirstName("Aaron");
        user.setUserLastName("Admin");
        user.setUserEmail("aadmin@1.com");
        user.setUserBio("Bio for A Admin");
        user.setUserDob(date);
        user.setUserPhotographs(new ArrayList<PhotographDTO>());

        this.mockMvc.perform(get("/users/1")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk()).andDo(print());

        StatusResponseEntity expected = new StatusResponseEntity(true, "User Find Successful",user);
        MvcResult response = mockMvc.perform(get("/users/{id}",1L)).andReturn();

        String actual = response.getResponse().getContentAsString();
        assertThat(actual).isEqualTo(objectMapper.writeValueAsString(expected));
    }

    @Test
    @DirtiesContext
    void updateUserPasswordInputFailure() throws Exception {

        PasswordUpdateRequest passwordUpdateRequest = new PasswordUpdateRequest();
        passwordUpdateRequest.setNewPassword("testpass1");
        passwordUpdateRequest.setCurrentPassword("testpass");

        mockMvc.perform(put("/users/{id}/update/password",1L)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(passwordUpdateRequest)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @DirtiesContext
    void updateUserPasswordInputSuccess() throws Exception {

        PasswordUpdateRequest passwordUpdateRequest = new PasswordUpdateRequest();
        passwordUpdateRequest.setNewPassword("testpass1");
        passwordUpdateRequest.setCurrentPassword("testpass");

        mockMvc.perform(put("/users/{id}/update/password",3L)
                .header("authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIzIiwiaWF0IjoxNTk4NzEzODE5LCJleHAiOjE1OTkzMTg2MTl9.NSrJBHFeaWz7vqJ8GMwhJejUWQPdDFZi2aMdc6fNH9XKC_r7n5mHVrK9_9Xl1Uo5E1wNxdFNevC_I0wt3pggww")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(passwordUpdateRequest)))
                .andExpect(status().isOk());
    }

    @Test
    @DirtiesContext
    void updateUserPasswordInputAccessDenied() throws Exception {

        PasswordUpdateRequest passwordUpdateRequest = new PasswordUpdateRequest();
        passwordUpdateRequest.setNewPassword("testpass1");
        passwordUpdateRequest.setCurrentPassword("testpass");

        mockMvc.perform(put("/users/{id}/update/password",1L)
                .header("authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIzIiwiaWF0IjoxNTk4NzEzODE5LCJleHAiOjE1OTkzMTg2MTl9.NSrJBHFeaWz7vqJ8GMwhJejUWQPdDFZi2aMdc6fNH9XKC_r7n5mHVrK9_9Xl1Uo5E1wNxdFNevC_I0wt3pggww")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(passwordUpdateRequest)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DirtiesContext
    void updateUserPasswordOutputWrongPassword() throws Exception {

        PasswordUpdateRequest passwordUpdateRequest = new PasswordUpdateRequest();
        passwordUpdateRequest.setNewPassword("testpass1");
        passwordUpdateRequest.setCurrentPassword("testpasswrong");

        StatusResponseEntity expected = new StatusResponseEntity(true, "Password Incorrect",false);

        MvcResult response = mockMvc.perform(put("/users/{id}/update/password",3L)
                .header("authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIzIiwiaWF0IjoxNTk4NzEzODE5LCJleHAiOjE1OTkzMTg2MTl9.NSrJBHFeaWz7vqJ8GMwhJejUWQPdDFZi2aMdc6fNH9XKC_r7n5mHVrK9_9Xl1Uo5E1wNxdFNevC_I0wt3pggww")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(passwordUpdateRequest)))
                .andExpect(status().isForbidden())
                .andReturn();

        String actual = response.getResponse().getContentAsString();
        assertThat(actual).isEqualTo(objectMapper.writeValueAsString(expected));
    }

    @Test
    @DirtiesContext
    void updateUserPasswordOutputCorrectPassword() throws Exception {
        Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX").parse("2020-06-19T14:27:28.054+00:00");
        UserDTO user = new UserDTO();
        user.setUserId(3L);
        user.setUserFirstName("Timmy");
        user.setUserLastName("Test");
        user.setUserEmail("ttest@3.com");
        user.setUserBio("Bio for T Test");
        user.setUserDob(date);
        user.setUserPhotographs(new ArrayList<PhotographDTO>());


        PasswordUpdateRequest passwordUpdateRequest = new PasswordUpdateRequest();
        passwordUpdateRequest.setNewPassword("testpass1");
        passwordUpdateRequest.setCurrentPassword("testpass");

        StatusResponseEntity expected = new StatusResponseEntity(true, "Password Update Successful",user);

        MvcResult response = mockMvc.perform(put("/users/{id}/update/password",3L)
                .header("authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIzIiwiaWF0IjoxNTk4NzEzODE5LCJleHAiOjE1OTkzMTg2MTl9.NSrJBHFeaWz7vqJ8GMwhJejUWQPdDFZi2aMdc6fNH9XKC_r7n5mHVrK9_9Xl1Uo5E1wNxdFNevC_I0wt3pggww")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(passwordUpdateRequest)))
                .andExpect(status().isOk())
                .andReturn();

        String actual = response.getResponse().getContentAsString();
        assertThat(actual).isEqualTo(objectMapper.writeValueAsString(expected));


    }


    @Test
    @DirtiesContext
    void updateUserInput() throws  Exception{
        Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX").parse("2020-06-19T14:27:28.054+00:00");
        UserDTO user = new UserDTO();
        user.setUserId(1L);
        user.setUserFirstName("Aaron1");
        user.setUserLastName("Admin");
        user.setUserEmail("aadmin@1.com");
        user.setUserBio("Bio for A Admin");
        user.setUserDob(date);
        user.setUserPhotographs(new ArrayList<PhotographDTO>());

        mockMvc.perform(put("/users/{id}/update",1L)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(objectMapper.writeValueAsString(user))));
    }

    @Test
    @DirtiesContext
    void updateUserWPhotoInputSuccessInput() throws  Exception{
        Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX").parse("2020-06-19T14:27:28.054+00:00");
        UserDTO user = new UserDTO();
        user.setUserId(1L);
        user.setUserFirstName("Aaron");
        user.setUserLastName("Admin");
        user.setUserEmail("aadmin@1.com");
        user.setUserBio("Bio for A Admin");
        user.setUserDob(date);
        user.setUserPhotographs(new ArrayList<PhotographDTO>());

        FileInputStream fileInputStream = new FileInputStream(new File("Z:\\Users\\Adam.Command_Center\\Documents\\Queens\\Individual Project\\demo\\uploads\\photographs\\kh.jpg"));
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file","kh.jpg","multipart/form-data",fileInputStream);

        mockMvc.perform(post("/users/{id}/update",1L)
                .content(mockMultipartFile.getBytes())
                .param("user", objectMapper.writeValueAsString(user))
                .contentType(MediaType.MULTIPART_FORM_DATA))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(objectMapper.writeValueAsString(user))));

    }

    @Test
    @DirtiesContext
    void updateUserWPhotoInputSuccessOutput() throws  Exception{
        Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX").parse("2020-06-19T14:27:28.054+00:00");
        UserDTO user = new UserDTO();
        user.setUserId(1L);
        user.setUserFirstName("Aaron");
        user.setUserLastName("Admin");
        user.setUserEmail("aadmin@1.com");
        user.setUserBio("Bio for A Admin");
        user.setUserDob(date);
        user.setUserPhotographs(new ArrayList<PhotographDTO>());

        FileInputStream fileInputStream = new FileInputStream(new File("Z:\\Users\\Adam.Command_Center\\Documents\\Queens\\Individual Project\\demo\\uploads\\photographs\\kh.jpg"));
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file","kh.jpg","multipart/form-data",fileInputStream);

        MvcResult response = mockMvc.perform(post("/users/{id}/update",1L)
                .content(mockMultipartFile.getBytes())
                .param("user", objectMapper.writeValueAsString(user))
                .contentType(MediaType.MULTIPART_FORM_DATA))
                .andDo(print())
                .andReturn();

        StatusResponseEntity expected = new StatusResponseEntity(true, "User Update Successful",user);
        String actual = response.getResponse().getContentAsString();
        assertThat(actual).isEqualTo(objectMapper.writeValueAsString(expected));
    }


    @Test
    @DirtiesContext
    void updateUserOutput() throws Exception{
        Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX").parse("2020-06-19T14:27:28.054+00:00");
        UserDTO user = new UserDTO();
        user.setUserId(1L);
        user.setUserFirstName("Aaron1");
        user.setUserLastName("Admin");
        user.setUserEmail("aadmin@1.com");
        user.setUserBio("Bio for A Admin");
        user.setUserDob(date);
        user.setUserPhotographs(new ArrayList<PhotographDTO>());

        StatusResponseEntity expected = new StatusResponseEntity(true, "User Update Successful",user);

        MvcResult response = mockMvc.perform(put("/users/{id}/update",1L)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)))
                .andReturn();

        String actual = response.getResponse().getContentAsString();
        assertThat(actual).isEqualTo(objectMapper.writeValueAsString(expected));
    }

}