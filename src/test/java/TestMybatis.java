import com.winter.dao.TestMapper;
import com.winter.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.io.Reader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:applicationContext.xml")
public class TestMybatis {

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    public SqlSession getSession() throws IOException {
        //根据xml配置文件, 创建一个sqlSessionFactory对象
//        String resource = "applicationContext.xml";
//        Reader reader = Resources.getResourceAsReader(resource);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        //获取sqlSession实例, 直接执行已经映射的sql语句
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession;
    }

    @Test
    public void test() throws IOException {
        SqlSession sqlSession = getSession();
        TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
        User user = testMapper.selectById(1000);
        System.out.println(user);
        sqlSession.close();
    }

}
