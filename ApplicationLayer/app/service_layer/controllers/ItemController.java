package service_layer.controllers;

import business_layer.core.ItemService;
import business_layer.core_api.IItemsService;
import com.fasterxml.jackson.databind.JsonNode;
import common_layer.mapper.JsonMapper;
import common_layer.models.Person;
import common_layer.models.library.items.Book;
import common_layer.models.library.items.DVD;
import data_access_layer.models.BookRepository;
import data_access_layer.models.DVDRepository;
import data_access_layer.models.PersonRepository;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import util.DateTime;

public class ItemController extends Controller {

    private JsonMapper jsonMapper = new JsonMapper();
    private IItemsService itemsService = new ItemService();

    public Result getBooks(){
        try {
            JsonNode json = Json.toJson(itemsService.getBooks());
            return ok(json);
        }catch (Exception ex){
            return badRequest(ex.getMessage());
        }
    }

    public Result getDVDs(){
        try {
            JsonNode json = Json.toJson(itemsService.getDVDs());
            return ok(json);
        }catch (Exception ex){
            return badRequest(ex.getMessage());
        }
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result addBook(){
        try {
            JsonNode json = request().body().asJson();
            BookRepository book = jsonMapper.toBookRepo(json);
            return ok(String.valueOf(itemsService.addBook(book)));
        }catch (Exception ex){
            return badRequest(ex.getMessage());
        }
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result addDVD(){
        try {
            JsonNode json = request().body().asJson();
            DVDRepository dvd = jsonMapper.toDVDRepo(json);
            return ok(String.valueOf(itemsService.addDVD(dvd)));
        }catch (Exception ex){
            return badRequest(ex.getMessage());
        }
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result addReader(){
        try {
            JsonNode json = request().body().asJson();
            PersonRepository reader = jsonMapper.toPersonRepo(json);
            JsonNode jsonR = Json.toJson(itemsService.addReader(reader));
            return ok(jsonR);
        }catch (Exception ex){
            return badRequest(ex.getMessage());
        }
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result borrowBook(){
        try {
            JsonNode json = request().body().asJson();
            DateTime date = new DateTime(json.findPath("Date").findPath("Day").asInt(),json.findPath("Date").findPath("Month").asInt(),json.findPath("Date").findPath("Year").asInt());
            itemsService.borrowBook(json.findPath("Id").textValue(),json.findPath("PersonId").textValue(),date);
            return ok();
        }catch (Exception ex){
            return badRequest(ex.getMessage());
        }
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result borrowDVD(){
        try {
            JsonNode json = request().body().asJson();
            DateTime date = new DateTime(json.findPath("Date").findPath("Day").asInt(),json.findPath("Date").findPath("Month").asInt(),json.findPath("Date").findPath("Year").asInt());
            itemsService.borrowDVD(json.findPath("Id").textValue(),json.findPath("PersonId").textValue(),date);
            return ok();
        }catch (Exception ex){
            return badRequest(ex.getMessage());
        }
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result returnBook(){
        try {
            JsonNode json = request().body().asJson();
            itemsService.returnBook(json.findPath("Id").textValue());
            return ok();
        }catch (Exception ex){
            return badRequest(ex.getMessage());
        }
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result returnDVD(){
        try {
            JsonNode json = request().body().asJson();
            itemsService.returnDVD(json.findPath("Id").textValue());
            return ok();
        }catch (Exception ex){
            return badRequest(ex.getMessage());
        }
    }


    public Result generateReport(){
        return ok();
    }

    public Result deleteBook(String id){
        try {
            itemsService.deleteBook(id);
            return ok();
        }catch (Exception ex){
            return badRequest(ex.getMessage());
        }
    }

    public Result deleteDVD(String id){
        try {
            itemsService.deleteDVD(id);
            return ok();
        }catch (Exception ex){
            return badRequest(ex.getMessage());
        }
    }

    public Result getPersons(){
        try {
            JsonNode json = Json.toJson(itemsService.getPersons());
            return ok(json);
        }catch (Exception ex){
            return badRequest(ex.getMessage());
        }
    }
}
