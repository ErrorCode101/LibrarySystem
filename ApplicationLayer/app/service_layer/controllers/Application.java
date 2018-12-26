package service_layer.controllers;

import business_layer.core.ItemService;
import common_layer.mapper.JsonMapper;
import play.mvc.*;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import java.io.File;

public class Application extends Controller{

    private JsonMapper jsonMapper = new JsonMapper();
    private ItemService item = new ItemService();

    public Result index() {
        return ok("Your new application is ready.");
    }

    public Result jsonOut(){

        ItemService item = new ItemService();
        return ok();
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result jsonIn(){
        return ok();
    }

    public Result upload() {
        Http.MultipartFormData<File> body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart<File> picture = body.getFiles().get(0);
        if (picture != null) {
            String fileName = picture.getFilename();
            String contentType = picture.getContentType();
            File file = picture.getFile();
            return ok("File uploaded");
        } else {
            flash("error", "Missing file");
            return badRequest();
        }
    }

}
