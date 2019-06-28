package com.todo.gitapp.service;

import com.todo.gitapp.config.APIConfiguration;
import com.todo.gitapp.exceptions.CustomeExceptions;
import com.todo.gitapp.repository.ApiRepository;
import org.apache.commons.lang.StringUtils;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryBranch;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GitService implements APIConfiguration {
    public String accessToken;

    private ApiRepository service;

    public GitService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(ApiRepository.class);
        this.accessToken = "token "+ACCESS_TOKEN;
    }

    public List<Repository> getRepositories() throws IOException {
        Call<List<Repository>> retrofitCall = service.listRepos(accessToken, API_VERSION_SPEC);

        Response<List<Repository>> response = retrofitCall.execute();

        if (!response.isSuccessful()) {
            throw new IOException(response.errorBody() != null
                    ? response.errorBody().string() : "Unknown error");
        }

        return response.body();
    }

    public List<RepositoryBranch> getReposByUser(String username) throws IOException{
        if(StringUtils.isEmpty(username)){
            throw  new CustomeExceptions("username should not empty");
        }
        Call<List<RepositoryBranch>> listCall=service.listReposByUser(accessToken,API_VERSION_SPEC,username);
        Response<List<RepositoryBranch>> response = listCall.execute();
        if (!response.isSuccessful()) {
            throw new IOException(response.errorBody() != null
                    ? response.errorBody().string() : "Unknown error");
        }

        return response.body();
    }

    public List<Repository> getSearchRepositoriesByRepoName(String repoName) throws  IOException{
        if(StringUtils.isEmpty(repoName)){
            throw  new CustomeExceptions("Repository should not empty");
        }
        Map<String, Repository> repositoryMap= this.getRepositories().parallelStream().collect(Collectors.toMap(Repository::getName, repository -> repository, (a, b) -> b));
        List<String> collect = repositoryMap.keySet().parallelStream().filter(repo -> repo.equalsIgnoreCase(repoName)).collect(Collectors.toList());
        return collect.parallelStream().map(repositoryMap::get).collect(Collectors.toList());
    }
}
