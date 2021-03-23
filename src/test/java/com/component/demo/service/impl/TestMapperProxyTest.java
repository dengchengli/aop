package com.component.demo.service.impl;

import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.ChainingCredentialsProvider;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: Dely
 * @Date: 2020/10/16 23:52
 */
class TestMapperProxyTest {


    @Test
    void test1() throws Exception{
        String localPath = "J:\\cache\\test\\ggg";
        String remoteUrl = "https://github.com/dengchengli/git-test.git";
        File direcotry = new File(localPath);
        FileRepositoryBuilder builder = new FileRepositoryBuilder();
        Repository repository = builder.setGitDir(new File(localPath + "/.git")).readEnvironment().findGitDir().build();

        Git git = Git.wrap(repository);

        CloneCommand clone = git.cloneRepository();
        clone.setCloneAllBranches(false);
        clone.setBare(false);
        clone.setDirectory(direcotry).setURI(remoteUrl);
        UsernamePasswordCredentialsProvider user = new UsernamePasswordCredentialsProvider("heyu", "*********");
        clone.setCredentialsProvider(user);
        clone.call();
        Ref ref = repository.findRef("refs/remotes/origin/dev" );
        System.out.println("--------- " + repository.isBare());
        System.out.println(repository.getBranch());
        File workTree = repository.getWorkTree();
        System.out.println(workTree.list());
    }
}
