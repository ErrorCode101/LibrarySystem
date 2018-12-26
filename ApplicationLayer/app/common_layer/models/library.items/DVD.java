package common_layer.models.library.items;

import common_layer.models.LibraryItem;
import common_layer.models.Person;
import common_layer.models.persons.Actor;
import common_layer.models.persons.Creator;
import util.DateTime;

import java.util.List;

public class DVD extends LibraryItem {

    private String producer;
    private List<String> actors;
    private List<String> languages;
    private List<String> subtitles;

    DVD(){}

    DVD(String ISBN, String title, DateTime publicationDate){
        super(ISBN, title, publicationDate);
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public List<String> getSubtitles() {
        return subtitles;
    }

    public void setSubtitles(List<String> subtitles) {
        this.subtitles = subtitles;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

}