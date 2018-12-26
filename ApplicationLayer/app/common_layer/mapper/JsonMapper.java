package common_layer.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import common_layer.models.Person;
import common_layer.models.library.items.Book;
import common_layer.models.library.items.DVD;
import scala.xml.dtd.PEReference;

public class JsonMapper {

    private ObjectMapper jsonObjectMapper = new ObjectMapper();

    public Book toBook(JsonNode json) throws JsonProcessingException {
        return jsonObjectMapper.treeToValue(json, Book.class);
    }

    public DVD toDVD(JsonNode json) throws JsonProcessingException {
        return jsonObjectMapper.treeToValue(json, DVD.class);
    }

    public Person toPerson(JsonNode json) throws JsonProcessingException {
        return jsonObjectMapper.treeToValue(json, Person.class);
    }
}
