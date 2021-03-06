package com.skowrondariusz.przy100.utility;

import com.skowrondariusz.przy100.model.Question;
import com.skowrondariusz.przy100.model.SongQuestion;
import com.skowrondariusz.przy100.repository.QuestionRepository;
import com.skowrondariusz.przy100.repository.SongRepository;
import com.skowrondariusz.przy100.service.QuestionService;
import com.skowrondariusz.przy100.service.SongDrawingService;
import com.skowrondariusz.przy100.service.SpotifyService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static com.skowrondariusz.przy100.service.SpotifyService.getArtistsAlbums_Sync;


@Component
@ConditionalOnProperty(name = "database.startup", havingValue = "true")
public class DatabaseInitializer implements CommandLineRunner {


    private SpotifyService spotifyService;
    private SongDrawingService songDrawingService;

//    public DatabaseInitializer(QuestionService questionService, SpotifyService spotifyService, SongDrawingService songDrawingService) {
//        this.questionService = questionService;
//        this.spotifyService = spotifyService;
//        this.songDrawingService = songDrawingService;
//    }


//    public DatabaseInitializer(QuestionService questionService, SpotifyService spotifyService, SongDrawingService songDrawingService, SongRepository songRepository) {
//        this.questionService = questionService;
//        this.spotifyService = spotifyService;
//        this.songDrawingService = songDrawingService;
//        this.songRepository = songRepository;
//    }


    public DatabaseInitializer(SpotifyService spotifyService, SongDrawingService songDrawingService) {
        this.spotifyService = spotifyService;
        this.songDrawingService = songDrawingService;
    }

    @Override
    public void run(String... args) {
        System.out.println(SpotifyCredentials.CLIENT_ID);
        System.out.println(SpotifyCredentials.CLIENT_SECRET);
//        var question1 = new Question("question 1 test","aq1", Arrays.asList("q1", "q2", "a3") );
//        var question2 = new Question("question 2 test", "aq1", Arrays.asList("a1", "a2", "a3"));
//        var question3 = new SongQuestion("song question test", "22", Arrays.asList("teest1", "test2", "test2"), "ssa");
//        questionService.saveQuestion(question1);
//        questionService.saveQuestion(question2);
//        questionService.saveQuestion(question3);



        getArtistsAlbums_Sync();
        spotifyService.test();
            songDrawingService.musicQuestionGenerate();
        System.out.println("DATABASE INITIALISED");
        spotifyService.totalCharacters();


    }
}
