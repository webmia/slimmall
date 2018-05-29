package kr.hkit.slimmall.mapper;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:spring/context-*.xml", 
        "file:src/main/webapp/WEB-INF/config/springmvc/dispatcher-servlet.xml"})
@WebAppConfiguration
public class HomeMapperTest {

	@Autowired
	private HomeMapper homeMapper;
	
	@Test
	public void test() {
		
		String keyword = "상품";
		List<ProductVO> productVOs = homeMapper.selectMainProductVOs(keyword);
		assertNotNull(productVOs);
		
	}

}
