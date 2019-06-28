package com.todo.gitapp.repository;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryBranch;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

import java.util.List;

public interface ApiRepository {

    @GET("/user/repos")
    Call<List<Repository>> listRepos(@Header("Authorization") String accessToken,
                                     @Header("Accept") String apiVersionSpec);

    @GET("/users/{username}/repos")
    Call<List<RepositoryBranch>> listReposByUser(@Header("Authorization") String accessToken,
                                                 @Header("Accept") String apiVersionSpec, @Path("username") String username);
}
