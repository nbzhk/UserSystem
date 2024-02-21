package com.example.usersystem1.services;

import com.example.usersystem1.entities.Album;
import com.example.usersystem1.entities.Picture;
import com.example.usersystem1.entities.Town;
import com.example.usersystem1.entities.User;
import com.example.usersystem1.repositories.AlbumRepository;
import com.example.usersystem1.repositories.PictureRepository;
import com.example.usersystem1.repositories.TownRepository;
import com.example.usersystem1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class SeedServiceImpl implements SeedService {
    private static final String FILE_PATH = "src\\main\\resources\\files\\";
    private static final String TOWNS_PATH = FILE_PATH + "towns.txt";
    private static final String USERS_PATH = FILE_PATH + "users.txt";
    private static final String PICTURES_PATH = FILE_PATH + "pictures.txt";
    private static final  String ALBUMS_PATH = FILE_PATH + "albums.txt";


    private final TownRepository townRepository;
    private final UserRepository userRepository;
    private final PictureRepository pictureRepository;
    private final AlbumRepository albumRepository;

    @Autowired
    public SeedServiceImpl(TownRepository townRepository, UserRepository userRepository, PictureRepository pictureRepository, AlbumRepository albumRepository) {
        this.townRepository = townRepository;
        this.userRepository = userRepository;
        this.pictureRepository = pictureRepository;
        this.albumRepository = albumRepository;
    }

    @Override
    public void seedTowns() throws IOException {
        for (String line : Files.readAllLines(Path.of(TOWNS_PATH))) {
            String townName = line.split("\\s+")[0];
            String countryName = line.split("\\s+")[1];

            Town town = new Town(townName, countryName);
            this.townRepository.save(town);
        }

    }

    @Override
    public void seedUsers() throws IOException {
        for (String line : Files.readAllLines(Path.of(USERS_PATH))) {
            String[] userData = line.split("\\s+");

            String username = userData[0];
            String password = userData[1];
            String email = userData[2];
            int age = Integer.parseInt(userData[3]);
            String bornIn = userData[4];
            String livingIn = userData[5];
            String firstName = userData[6];
            String lastName = userData[7];

            Town bornTown = townRepository.getDistinctByName(bornIn);
            Town livingTown = townRepository.getDistinctByName(livingIn);

            User user = new User(username, password, email, age, bornTown, livingTown, firstName, lastName);

            userRepository.save(user);

        }

    }

    @Override
    public void seedPictures() throws IOException {
        for (String line : Files.readAllLines(Path.of(PICTURES_PATH))) {
            String title = line.split("\\s+")[0];
            String path = line.split("\\s+")[1];
            String caption = line.split("\\s+")[2];

            Picture picture = new Picture(title, caption, path);

            pictureRepository.save(picture);
        }
    }

    @Override
    public void seedAlbums() throws IOException {
        for (String line : Files.readAllLines(Path.of(ALBUMS_PATH))) {
            String name = line.split("\\s+")[0];
            String backgroundColor = line.split("\\s+")[1];
            boolean isPublic = Boolean.parseBoolean(line.split("\\s+")[2]);

            Album album = new Album(name, backgroundColor, isPublic);

            albumRepository.save(album);
        }

    }
}
