package com.iavariav.submission1.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.iavariav.submission1.data.remote.RemoteRepository;
import com.iavariav.submission1.data.remote.entity.MovieEntity;
import com.iavariav.submission1.data.remote.entity.TvShowEntity;
import com.iavariav.submission1.data.remote.response.MovieModel;
import com.iavariav.submission1.data.remote.response.TvShowModel;

import java.util.ArrayList;
import java.util.List;

public class MovieTVRepository implements MovieTVDataSource{
    private volatile static MovieTVRepository INSTANCE = null;

    private final RemoteRepository remoteRepository;

    public MovieTVRepository(@NonNull RemoteRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    public static MovieTVRepository getInstance(RemoteRepository remoteData) {
        if (INSTANCE == null) {
            synchronized (MovieTVRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MovieTVRepository(remoteData);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public LiveData<List<MovieEntity>> getAllMovie() {
        MutableLiveData<List<MovieEntity>> courseResults = new MutableLiveData<>();

        remoteRepository.getMovie(new RemoteRepository.LoadMovieCallback() {
            @Override
            public void onAllCoursesReceived(List<MovieModel> courseResponses) {
                ArrayList<MovieEntity> courseList = new ArrayList<>();
                for (int i = 0; i < courseResponses.size(); i++) {
                    MovieModel response = courseResponses.get(i);
                    MovieEntity course = new MovieEntity(response.getId(),
                            response.getPoster_path(),
                            response.getTitle(),
                            response.getRelease_date(),
                            response.getOverview()
                            );

                    courseList.add(course);
                }
                courseResults.postValue(courseList);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
        return courseResults;
    }

    @Override
    public LiveData<MovieEntity> getAllMovieDetail(String courseId) {
        MutableLiveData<MovieEntity> courseResult = new MutableLiveData<>();

        remoteRepository.getMovie(new RemoteRepository.LoadMovieCallback() {
            @Override
            public void onAllCoursesReceived(List<MovieModel> courseResponses) {
                for (int i = 0; i < courseResponses.size(); i++) {
                    MovieModel response = courseResponses.get(i);
                    if (response.getId().equals(courseId)) {
                        MovieEntity course = new MovieEntity(
                                response.getId(),
                                response.getPoster_path(),
                                response.getTitle(),
                                response.getRelease_date(),
                                response.getOverview()
                        );
                        courseResult.postValue(course);
                    }
                }
            }

            @Override
            public void onDataNotAvailable() {

            }
        });

        return courseResult;
    }

    @Override
    public LiveData<List<TvShowEntity>> getAllTv() {
        MutableLiveData<List<TvShowEntity>> courseResults = new MutableLiveData<>();

        remoteRepository.getTv(new RemoteRepository.LoadTvCallback() {
            @Override
            public void onAllCoursesReceived(List<TvShowModel> courseResponses) {
                ArrayList<TvShowEntity> courseList = new ArrayList<>();
                for (int i = 0; i < courseResponses.size(); i++) {
                    TvShowModel response = courseResponses.get(i);
                    TvShowEntity course = new TvShowEntity(
                            response.getId(),
                            response.getName(),
                            response.getFirstAirDate(),
                            response.getPosterPath(),
                            response.getOverview()
                    );

                    courseList.add(course);
                }
                courseResults.postValue(courseList);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
        return courseResults;
    }

    @Override
    public LiveData<TvShowEntity> getAllTvDetail(String courseId) {
        MutableLiveData<TvShowEntity> courseResult = new MutableLiveData<>();

        remoteRepository.getTv(new RemoteRepository.LoadTvCallback() {
            @Override
            public void onAllCoursesReceived(List<TvShowModel> courseResponses) {
                for (int i = 0; i < courseResponses.size(); i++) {
                    TvShowModel response = courseResponses.get(i);
                    if (response.getId().equals(courseId)) {
                        TvShowEntity course = new TvShowEntity(
                                response.getId(),
                                response.getName(),
                                response.getFirstAirDate(),
                                response.getPosterPath(),
                                response.getOverview()
                        );
                        courseResult.postValue(course);
                    }
                }
            }

            @Override
            public void onDataNotAvailable() {

            }
        });

        return courseResult;
    }


}
