package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by SK on 13.06.2016.
 */
public class GithubTests {
    @Test
    public void
    testCommits() throws IOException {
        Github github = new RtGithub("a5453b686274098dbc65e7aeb3780b09d34e48d8");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("SergeyKharkovshchenko", "java_pft")).commits();
        for (RepoCommit commit:commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
            System.out.println(new RepoCommit.Smart(commit).message());
        }
    }
}
