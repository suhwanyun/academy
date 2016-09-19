import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.comment.config.CommentConfig;
import com.example.comment.repo.CommentRepo;
import com.example.comment.repo.UserRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={CommentConfig.class})
public class RepoTest {

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	CommentRepo commRepo;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		assertThat(userRepo, is(notNullValue()));
		assertThat(commRepo, is(notNullValue()));
	}

}
