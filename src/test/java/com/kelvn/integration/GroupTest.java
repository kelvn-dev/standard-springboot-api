package com.kelvn.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.kelvn.dto.api.ApiPageableResponse;
import com.kelvn.dto.request.GroupRequestDTO;
import com.kelvn.dto.response.GroupResponseDTO;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;



public class GroupTest extends BaseTest<GroupRequestDTO, GroupResponseDTO> {
  final String apiUrl = "/api/v1/groups";
  GroupRequestDTO requestDTO;
  GroupResponseDTO responseDTO;

  @Test
  @Order(1)
  public void testCreate() throws Exception {
    requestDTO = new GroupRequestDTO().setName("Test");
    requestEntity = new HttpEntity<GroupRequestDTO>(requestDTO, headers);
    responseEntity = restTemplate.exchange(
      createURLWithPort(apiUrl),
      HttpMethod.POST,
      requestEntity,
      GroupResponseDTO.class
    );

    responseDTO = (GroupResponseDTO) responseEntity.getBody();
    assertNotNull(responseDTO);
    assertNotNull(responseDTO.getId());
    assertEquals("Test", responseDTO.getName());
    id = responseDTO.getId();
  }

  @Test
  @Order(2)
  public void testGetById() throws Exception {
    requestEntity = new HttpEntity<>(null, headers);
    responseEntity = restTemplate.exchange(
      createURLWithPort(apiUrl.concat("/").concat(id.toString())),
      HttpMethod.GET,
      requestEntity,
      GroupResponseDTO.class
    );

    responseDTO = (GroupResponseDTO) responseEntity.getBody();
    assertNotNull(responseDTO);
    assertEquals(id, responseDTO.getId());
  }

  @Test
  @Order(3)
  public void testUpdateById() throws Exception {
    requestDTO.setName("updated");
    requestEntity = new HttpEntity<>(requestDTO, headers);
    responseEntity = restTemplate.exchange(
      createURLWithPort(apiUrl.concat("/").concat(id.toString())),
      HttpMethod.PUT,
      requestEntity,
      GroupResponseDTO.class
    );

    responseDTO = responseEntity.getBody();
    assertNotNull(responseDTO);
    assertEquals("updated", responseDTO.getName());
  }

  @Test
  @Order(4)
  public void testGetlist() throws Exception {
    requestEntity = new HttpEntity<>(null, headers);
    apiPageableResponseEntity = restTemplate.exchange(
      createURLWithPort(apiUrl),
      HttpMethod.GET,
      requestEntity,
      ApiPageableResponse.class
    );

    apiPageableResponse = apiPageableResponseEntity.getBody();
    assertNotNull(apiPageableResponse);
    responseDTOList = mapper.convertValue(apiPageableResponse.getData(), new TypeReference<List<GroupResponseDTO>>() { });
    assertThat(responseDTOList)
      .extracting(GroupResponseDTO::getId, GroupResponseDTO::getName)
      .containsOnlyOnce(tuple(id, "updated"));
  }

  @Test
  @Order(5)
  public void testDeleteById() throws Exception {
    requestEntity = new HttpEntity<>(null, headers);
    responseEntity = restTemplate.exchange(
      createURLWithPort(apiUrl).concat("/").concat(id.toString()),
      HttpMethod.DELETE,
      requestEntity,
      GroupResponseDTO.class
    );
    assertEquals(200, responseEntity.getStatusCodeValue());
  }
}
