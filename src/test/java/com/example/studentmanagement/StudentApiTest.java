package com.example.studentmanagement;
import org.junit.jupiter.api.*;import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;import org.springframework.boot.test.context.SpringBootTest;import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;import com.example.studentmanagement.repository.StudentRepository;import javax.sql.DataSource;import java.util.*;
/** Vérification HTTP et persistance sur le vrai MySQL. */
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentApiTest {
    @Autowired TestRestTemplate http;@Autowired StudentRepository repository;@Autowired DataSource source;
    @BeforeEach void clear(){repository.deleteAll();}
    Map<String,String> data(String name,String date){return Map.of("nom",name,"prenom","Mohamed","dateNaissance",date);}
    @Test void mysqlReel()throws Exception{try(var c=source.getConnection()){assertEquals("MySQL",c.getMetaData().getDatabaseProductName());}}
    @Test void endpointsEtStatistiques(){var created=http.postForEntity("/students/save",data("LACHGAR","1985-09-01"),Map.class);assertEquals(201,created.getStatusCode().value());int id=((Number)created.getBody().get("id")).intValue();http.postForEntity("/students/save",data("Ali","1985-05-02"),Map.class);http.postForEntity("/students/save",data("Amal","1990-06-01"),Map.class);assertEquals(3,http.getForObject("/students/count",Integer.class));assertEquals(3,http.getForObject("/students/all",List.class).size());var years=http.getForObject("/students/byYear",List.class);assertEquals(List.of(1985,2),years.get(0));assertEquals(List.of(1990,1),years.get(1));assertEquals(204,http.exchange("/students/delete/"+id,HttpMethod.DELETE,HttpEntity.EMPTY,String.class).getStatusCode().value());assertEquals(404,http.exchange("/students/delete/"+id,HttpMethod.DELETE,HttpEntity.EMPTY,String.class).getStatusCode().value());}
    @Test void validationEtSwagger(){assertEquals(400,http.postForEntity("/students/save",data("","1985-09-01"),String.class).getStatusCode().value());var spec=http.getForEntity("/v3/api-docs",String.class);assertEquals(200,spec.getStatusCode().value());assertTrue(spec.getBody().contains("/students/byYear"));}
}
