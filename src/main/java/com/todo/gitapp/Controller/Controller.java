package com.todo.gitapp.Controller;

import com.todo.gitapp.service.GitService;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryBranch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * Created by Seema Mahlawat
 * Sr. Software Engineer
 */
@RestController
public class Controller {

    @Autowired
    private GitService gitService;

    /**
     *
     * @return list{@link {@link Repository}
     * @throws IOException
     * @implNote :This rest end point  is responsible for return list of git repository for respected user/org.
     */
    @GetMapping("/list")
    public List<Repository> getRepositories() throws IOException {
        return gitService.getRepositories();
    }

    /**
     *
     * @param username
     * @return list{@link {@link RepositoryBranch}
     * @throws IOException
     * @implNote :This rest end point is responsible for return list of git repository by username
     */
    @GetMapping("/searchByUsername/{username}")
    public List<RepositoryBranch> getUserRepositories(@PathVariable String username) throws IOException {
        return gitService.getReposByUser(username);
    }

    /**
     *
     * @param repoName
     * @return list{@link {@link Repository}
     * @throws IOException
     * @implNote :This rest end point is responsible for return list or single data by search repository name
     */
    @GetMapping("searchRepo/{repoName}")
    public List<Repository> getRepositoriesByRepoName(@PathVariable String repoName) throws IOException{
        return gitService.getSearchRepositoriesByRepoName(repoName);
    }
}
