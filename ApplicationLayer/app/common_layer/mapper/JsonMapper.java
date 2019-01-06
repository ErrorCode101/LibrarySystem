package common_layer.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import common_layer.models.Person;
import common_layer.models.library.items.Book;
import common_layer.models.library.items.DVD;
import data_access_layer.models.BookRepository;
import data_access_layer.models.DVDRepository;
import data_access_layer.models.PersonRepository;


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

    public BookRepository toBookRepo(JsonNode json) throws JsonProcessingException {
        return jsonObjectMapper.treeToValue(json, BookRepository.class);
    }

    public DVDRepository toDVDRepo(JsonNode json) throws JsonProcessingException {
        return jsonObjectMapper.treeToValue(json, DVDRepository.class);
    }

    public PersonRepository toPersonRepo(JsonNode json) throws JsonProcessingException {
        return jsonObjectMapper.treeToValue(json, PersonRepository.class);
    }
}
