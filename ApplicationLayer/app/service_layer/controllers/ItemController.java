package service_layer.controllers;

import business_layer.core.ItemService;
import business_layer.core_api.IItemsService;
import com.fasterxml.jackson.databind.JsonNode;
import common_layer.mapper.JsonMapper;
import common_layer.models.Person;
import common_layer.models.library.items.Book;
import common_layer.models.library.items.DVD;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

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
            Book book = jsonMapper.toBook(json);
            itemsService.addBook(book);
            return ok();
        }catch (Exception ex){
            return badRequest(ex.getMessage());
        }
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result addDVD(){
        try {
            JsonNode json = request().body().asJson();
            DVD dvd = jsonMapper.toDVD(json);
            itemsService.addDVD(dvd);
            return ok();
        }catch (Exception ex){
            return badRequest(ex.getMessage());
        }
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result addReader(){
        try {
            JsonNode json = request().body().asJson();
            Person reader = jsonMapper.toPerson(json);
            JsonNode jsonR = Json.toJson(itemsService.addReader(reader));
            return ok(jsonR);
        }catch (Exception ex){
            return badRequest(ex.getMessage());
        }
    }

    public Result borrowItem(){
        return ok();
    }

    public Result returnItem(){
        return ok();
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
