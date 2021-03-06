package waterEarn;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.shuidi168.earn.dao.IGeneralUserDAO;
import com.shuidi168.earn.dao.IReceiveTaskDAO;
import com.shuidi168.earn.domain.GeneralUser;
import com.shuidi168.earn.util.DateUtil;
import com.shuidi168.earn.util.GeneralInviteCodeUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
@Transactional
public class Test {
	@Autowired
	private IGeneralUserDAO generalUserDAO;
	@Autowired
	private IReceiveTaskDAO receiveTaskDao;
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	@org.junit.Test
	public void testQueryLimit() {
		List<GeneralUser> querylimit = generalUserDAO.querylimit("queryAll", 0, 3, "惠州");
		for (GeneralUser generalUser : querylimit) {
			System.out.println(generalUser);
		}
	}
	@org.junit.Test
	public void testBigDecimal(){
		Timestamp startTimestamp = DateUtil.getStartTimestamp();
		BigDecimal queryAccount = receiveTaskDao.queryAccount("queryTaskByGeneralUserId", startTimestamp,13);
		System.out.println(queryAccount);
		System.out.println(redisTemplate);
	}
	@org.junit.Test
	public void testInviteCode(){
		System.out.println(GeneralInviteCodeUtil.toSerialCode(1));
		System.out.println(GeneralInviteCodeUtil.codeToId(GeneralInviteCodeUtil.toSerialCode(1)));
	}
}
