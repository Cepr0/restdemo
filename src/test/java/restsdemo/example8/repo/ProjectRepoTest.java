package restsdemo.example8.repo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import restsdemo.example8.model.Member;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ProjectRepoTest {

    @Autowired
    private ProjectRepo projectRepo;
    
    @Test
    public void getSkillsByProject() throws Exception {
    
        List<String> skills = projectRepo.getSkillsByProject(projectRepo.findOne(1L));
        assertThat(skills.size(), is(5));
    }
    
    @Test
    public void getProjectMembersByProject() throws Exception {
        Stream<Member> members = projectRepo.getProjectMembersByProject(projectRepo.findOne(1L));
        assertThat(members.count(), is(5));
    }
    
    @Test
    public void getMembersWithSkillsByProject() throws Exception {
        Map<Member, Set<Member.MemberSkill>> members = projectRepo.getMembersWithSkillsByProject(projectRepo.findOne(1L));
        members.forEach((key, value) -> assertThat(value.size(), is(3)));
    }
}